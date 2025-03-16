/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
 * Class:
 *     Worker (BinarySearchTree)
 *
 * Purpose:
 *     The Worker class handles a connection on a thread included in the MTS Server.
 *
 *     This class is the liaison between the Client and Server, responsible for:
 *         - connection I/O with client (contains connection socket)
 *         - connection with Server (server passed into client upon in constructor); I/O not required as this code lives server-side.
 *
 * Multithreaded:
 *     No, this class itself is not a multithreaded class as it is used only to echo responses (i.e. not controlled by a human via CLI).
 *
 * @see Server
 * @see Client
 */
public class Worker implements Runnable {
    /* server: Server instance of BST program (only one instance) */
    private final Server server;

    /* connection: socket connection to server host & port for bidirectional communication */
    private final Socket connection;

    /* ID: integer representing which user this connection (Client) is */
    private final int ID;

    /* input: BufferedReader to obtain text-based stream in bulk. Manages input communication from Client */
    private BufferedReader input;

    /* output: PrintWriter to write text-based stream to Client without necessity of flushing and try/catching IO */
    private PrintWriter output;

    /*
     * Constructor:
     *     Worker
     *
     * Purpose:
     *     Create new endpoint connection between Client and Server.
     *
     *     A Worker is created by passing the following:
     *         - server: Server creating 'this' worker to enable server-side connection
     *         - connection: fresh socket created by serverSocket.accept()
     *         - id: Client id passed into initialization
     *
     * @see Server
     * @see Client
     * @param none
     * @return none
     */
    public Worker(Server server, Socket connection, int id) {
        this.server = server;
        this.connection = connection;
        this.ID = id;
    }

    /*
     * Method:
     *     processAction
     *
     * Purpose:
     *     This method manages the input stream coming from the Client to map user actions to BST methods.
     *
     *      Steps:
     *          This method takes the input from the Client connection, determining if it is a valid action.
     *              If the action is valid, it then passes it to the Server BST and receives a result string.
     *              Again, this method determines if a valid response, and maps output to send data to wanted location:
     *                  - add/remove -> broadcast to all workers
     *                  - show/list -> send only to Client connected to this Worker
     *
     * @throws IOException: error occurring during I/O processing
     * @see Server
     * @see Client
     * @param none
     * @return none
     */
    private void processAction() throws IOException {
        String message = input.readLine();
        if (message.equals("EXIT")) {
            closeAll();
        } else {
            String result = server.bstAction(ID + "," + message);
            if (result.equals("ERROR")) {
                output.println("Error processing: " + message);
            } else { // success
                // all successful returns have ACTION:RESULT
                String[] split = result.split(":");
                String action = split[0];
                result = split[1];
                if (action.equals("SHOW") || action.equals("TRAVERSE")) { // if SHOW or TRAVERSE, print locally
                    output.println(result);
                } else if (action.equals("ADD") || action.equals("REMOVE")) { // if ADD or REMOVE, broadcast
                    server.broadcastAction(result);
                }
            }
        }
    }

    /*
     * Method:
     *     run
     *
     * Purpose:
     *     This method is called when the new Client/Server connection is created by MTS. It simply obtains I/O and listens for Client actions to process.
     *
     * @override Runnable
     * @param none
     * @return none
     */
    @Override
    public void run() {
        try {
            getIO();

            // welcome message
            output.println("--- Welcome user " + ID + "!");

            // listen loop
            while (true) {
                processAction();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * Method:
     *     getIO
     *
     * Purpose:
     *     Initial call after new Worker is executed, obtaining the methods of communication:
     *         - input: PrintWriter
     *         - output: BufferedReader
     *
     * @throws IOException: error occurring during initialization
     * @param none
     * @return none
     */
    private void getIO() throws IOException {
        output = new PrintWriter(connection.getOutputStream(), true);
        input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    }

    /*
     * Method:
     *     closeAll
     *
     * Purpose:
     *     Safely close client-side-error-producing objects:
     *         1. output
     *         2. input
     *         3. connection (with socket)
     *     Additionally, remove 'this' worker from the Server ArrayList tracking current Workers.
     *
     * @throws IOException: error occurring during shutdown
     * @param none
     * @return none
     */
    public void closeAll() throws IOException {
        try {
            output.close();
            server.removeWorker(this);
            input.close();
        } catch (IOException e) {
            System.out.println("Failed to close worker");
            throw new IOException(e);
        }
    }

    // Getters (not documented)
    public int getID() {
        return ID;
    }

    public PrintWriter getOutput() {
        return output;
    }
}