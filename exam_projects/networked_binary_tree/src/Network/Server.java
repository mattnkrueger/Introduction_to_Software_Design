/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

package Network;

import bst.BinarySearchTree;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * Class:
 *     Server (BinarySearchTree)
 *
 * Purpose:
 *     The Server class is endpoint of server for the socket connection.
 *     There is only one instance of this class, but it utilizes multithreading to allow for >1 Client connections
 *     creating a multithreaded server (MTS).
 *
 *     This Server class is responsible for the following:
 *             1. MTS - servicing threads with clients by utilizing ArrayList, ExecutorService, and Worker objects to do this.
 *             2. BST - handles interaction with a single, client-shared binary search tree.
 *
 * Multithreaded:
 *     Yes. This is a multithreaded class with Worker threads processed concurrently.
 *
 * @see BinarySearchTree
 * @see Worker
 * @see Client
 */
public class Server implements Runnable {
    /* port: integer port for endpoint */
    private final int port;

    /* serverSocket: serverSocket used as a listener for new Client connections */
    private ServerSocket serverSocket;

    /* bst: single, client-shared binary search tree */
    private final BinarySearchTree bst;

    /* connections: counter for number of connections; used to assign IDs to each user */
    private int connections;

    /* executorService: ExecutorService to handle the execution of threads (per SWD requirements) */
    private final ExecutorService executorService;

    /* workers: ArrayList of Workers used to broadcast server messages */
    private ArrayList<Worker> workers;


    /*
     * Constructor:
     *     Server
     *
     * Purpose:
     *     The Server constructor takes the port endpoint as its parameter:
     *         1. port
     *     And sets port as well as other attributes:
     *         - connections: 0
     *         - executorService: cached (no set number of threads, caching doesn't drastically improve efficiency for this project)
     *         - bst: new
     *         - workers: new (0 initial)
     *
     * @param port: integer port number
     * @return port:
     */
    public Server(int port) {
        this.port = port;
        connections = 0;
        executorService = Executors.newCachedThreadPool();
        bst = new BinarySearchTree();
        workers = new ArrayList<>();
    }

    /*
     * Method:
     *     incrementConnections
     *
     * Purpose:
     *     add 1 connection when Server accepts new connection
     *
     * @synchronized: added as server is multithreaded
     * @param none
     * @return none
     */
    private synchronized void incrementConnections() {
        connections++;
    }

    /*
     * Method:
     *
     * Purpose:
     *     remove 1 connection when a Worker connection (ultimately Client) is shutdown
     *
     * @synchronized: added as server is multithreaded
     * @param none
     * @return none
     */
    private synchronized void decrementConnections() {
        connections--;
    }

    /*
     * Method:
     *     broadcastAction
     *
     * Purpose:
     *     Broadcast wanted message to all Worker threads.
     *
     * @synchronized: added as server is multithreaded
     * @param message: string message representing action created by user
     * @return none
     */
    public synchronized void broadcastAction(String message) {
        System.out.println("Broadcasting");
        for (Worker worker : workers) {
            if (worker != null) {
                worker.getOutput().println("--- Server >>> " + message);
            }
        }
    }

    /*
     * Method:
     *     bstAction
     *
     * Purpose:
     *     Functionality of the BST/Server interaction.
     *     This method takes the string message from Worker (from client) and parses the formatted message to obtain wanted action.
     *     Then, the action is mapped to apply the action to the single, client-shared BST.
     *
     *      Steps:
     *          1. obtain input
     *          2. parse pre-formatted input string
     *          3. mapping logic -> pass to BST
     *          4. return result back to client
     *
     * @synchronized: added as server is multithreaded
     * @param message: String representing the message from Worker (from Client interaction with CLI)
     * @return result: String representing the result of the BST method operation
     */
    public synchronized String bstAction(String message) {
        String[] splitMessage = message.strip().split(",");
        String id = splitMessage[0];
        String action = splitMessage[1];
        System.out.println("action: " + action);
        int key; // set to this because it is out of range of bst and should never occur with a success message

        String result;
        if (action.contains(":")) {
            String[] splitAction = action.split(":");
            action = splitAction[0];
            String value = splitAction[1];


            // get value -> if not a int -> error
            try {
                key = Integer.parseInt(value);
                if (!(key < 10000 && key > -1000)) { // valid range for bst: -999 -> 9999 (four spots)
                    return "ERROR";
                }
            } catch (NumberFormatException e) {
                return "ERROR";
            }

            if (action.equals("ADD")) { // ex ADD:2
                System.out.println("ADDING");
                result = bst.add(key);
                System.out.println(result);
                return (result.equals("SUCCESS")) ? ("ADD:user " + id + " added " + key + " to the BST.") : ("ERROR");
            } else if (action.equals("REMOVE")) { // ex REMOVE:2
                result = bst.remove(key);
                return (result.equals("SUCCESS")) ? ("REMOVE:user " + id + " removed " + key + " from the BST.") : ("ERROR");
            } else if (action.equals("SEARCH")) { // show tree list
                result = bst.search(key);
                return (result.equals("SUCCESS")) ? ("SEARCH:" + key + " is in the BST") : ("ERROR");
            } else {
                result = "ERROR";
            }

        } else if (action.equals("PRE")) { // preorder traverse
            String bstResult = bst.preOrderTraversal();
            return "TRAVERSE:" + bstResult;
        } else if (action.equals("IN")) { // inorder traverse
            String bstResult = bst.inOrderTraversal();
            return "TRAVERSE:" + bstResult;
        } else if (action.equals("POST")) { // postorder traverse
            String bstResult = bst.postOrderTraversal();
            return "TRAVERSE:" + bstResult;
        } else if (action.equals("SHOW")) { // show current tree
//            String bstResult = bst.visualize();
            String bstResult = "method in progress...";
            return "SHOW:" + bstResult;
        }
        return "ERROR";
    }

    /*
     * Method:
     *     removeWorker
     *
     * Purpose:
     *     This method is called by Worker thread to remove itself from the Server's ArrayList of Workers.
     *
     * @synchronized: added as server is multithreaded
     * @see Worker
     * @param worker: Worker to be removed from ArrayList
     * @return none
     */
    public synchronized void removeWorker(Worker worker) {
        workers.remove(worker);
        decrementConnections();
        broadcastAction("user: " + worker.getID() + " exited the server. (conn: " + workers.size() + ")");
    }

    /*
     * Method:
     *     closeAll
     *
     * Purpose:
     *     Close the listening ServerSocket. This class does not directly have any I/O, only connection.
     *
     * @throws IOException: error occurring during shutdown
     * @param none
     * @return none
     */
    private void closeAll() throws IOException {
        serverSocket.close();
    }

    /*
     * Method:
     *     run (Server)
     *
     * Purpose:
     *     This method begins the MTS.
     *
     *     Steps:
     *          1. create binding serverSocket (start server)
     *          2. listen for new connections
     *          3. bind new connection in a new Worker thread (begin serving)
     *
     * @override Runnable
     * @see Worker
     * @param none
     * @return none
     */
    @Override
    public void run() {
        System.out.println("--- [BST Server Started] ---");
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            Worker worker;
            try {
                Socket connectionSocket = serverSocket.accept();
                incrementConnections();
                System.out.println("*** user: " + connections + " entered the server. (conn: " + connections + ")");
                worker = new Worker(this, connectionSocket, connections);
                workers.add(worker);
                executorService.submit(worker);
            } catch (IOException e) {
                try {
                    closeAll();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    /*
     * Method:
     *     main (Server entry-point)
     *
     * Purpose:
     *     This creates a new server connecting in port 23755 (one of my SWD ports). The server is located on your computer.
     *
     * @param args: cli arguments when program started
     * @return none
     */
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Server server = new Server(23755);
        executor.execute(server);
    }
}
