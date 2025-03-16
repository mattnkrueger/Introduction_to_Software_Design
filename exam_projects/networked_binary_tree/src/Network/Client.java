/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

package Network;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * Class:
 *     Client (BinarySearchTree)
 *
 * Purpose:
 *     The Client class is endpoint of user for the socket connection.
 *     The connection is handled on a thread of the server inside the Worker class.
 *
 *     Aside from creating the connection with the Server at enpoint (host, port), this class
 *         handles two thread:
 *             1. inputThread - thread for receiving messages from linked Worker & from Server broadcasts
 *             2. outputThread - thread for sending messages to linked Worker (which are ultimately sent to server to interact with the BST)
 *
 * Multithreaded:
 *     Yes. This is a multithreaded class with I/O threads processed concurrently.
 *
 * @see Worker
 * @see Server
 */
public class Client implements Runnable {
    /* connection: socket connection to server host & port for bidirectional communication */
    private Socket connection;

    /* input: BufferedReader to obtain text-based stream to Server in bulk. Manages input communication to server */
    private BufferedReader input;

    /* output: PrintWriter to write text-based stream without necessity of flushing and try/catching IO */
    private PrintWriter output;

    /* host: host string (ex "localhost") for the server's ip */
    private final String host;

    /* port: integer port for endpoint */
    private final int port;

    /* executorService: ExecutorService to handle the execution of threads (per SWD requirements) */
    private ExecutorService executorService;

    /*
     * Constructor:
     *     Client
     *
     * Purpose:
     *     The Client constructor takes the location of the enpoint as its parameters:
     *         1. host str
     *         2. port number
     *     And sets attributes.
     *
     * @param host: String hostname (ex "localhost")
     * @param port: integer port number
     * @param none
     */
    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /*
     * Method:
     *     run
     *
     * Purpose:
     *     This method is called when Worker class is executor, creating an endpoint between new ('this') Client and the Server.
     *
     *     Steps for achieving communication:
     *         1. connect to server to open connection & get I/O
     *         2. create input thread -> add to executor service
     *         3. create output thread -> add to executor service
     *
     *     The multithreaded I/O continuously 'listen' for input from Server & output from the user to send data to the Server.
     *
     * @override Runnable
     * @see connectToServer
     * @param none
     * @return none
     */
    @Override
    public void run() {
        try {
            connectToServer();
            executorService = Executors.newFixedThreadPool(2);

            // input thread to handle messages being sent from server
            Runnable inputThread = new Runnable() {
                @Override
                public void run() {
                    try {
                        // output any messages
                        String message;
                        while ((message = input.readLine()) != null) { // initialization to prevent reading twice (missing fist)
                            if (!(message.equals("EXIT"))) {
                                System.out.println(message);
                            } else {
                                System.out.println("Exiting Program...");
                                closeAll();
                            }
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            };

            // output thread to handle messages from user cli
            Runnable outputThread = new Runnable() {
                @Override
                public void run() {
                    // new BR to read user messages sent to cle
                    try {
                        BufferedReader userCLI = new BufferedReader(new InputStreamReader(System.in));
                        String userMessage;
                        while ((userMessage = userCLI.readLine()) != null)  { // initialization to prevent reading twice (missing fist)
                            if (!(userMessage.equals("EXIT"))) {
                                output.println(userMessage);
                            } else {
                                closeAll();
                            }
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            };

            // add the threads to the service
            executorService.submit(inputThread);
            executorService.submit(outputThread);
        } catch (IOException e) {
            try {
                closeAll();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }

    /*
     * Method:
     *     connectToServer
     *
     * Purpose:
     *     This method initiates the TCP 3 way handshake connection to open communication with Server & obtains the I/O reader/writer.
     *
     * @throws IOException: error occurring during initialization
     * @param none
     * @return none
     */
    public void connectToServer() throws IOException {
        try {
            connect();
            getIO();
        } catch (IOException e) {
            System.out.println("IO Exception: failed to create input/output objects for Client");
            e.printStackTrace();
        }
    }

    /*
     * Method:
     *     connect
     *
     * Purpose:
     *     First step in connectToServer(), initializing a connection with the Server. This then triggers a Worker thread to link to 'this' Client.
     *
     * @throws IOException: error occurring during initialization
     * @see connectToServer
     * @param none
     * @return none
     */
    private void connect() throws IOException {
        try {
            connection = new Socket(host, port);
        } catch (ConnectException e) {
            System.out.println("Failed to connect to " + host + " on " + port);
        }
    }

    /*
     * Method:
     *     getIO
     *
     * Purpose:
     *     Second step in connectToServer(), obtaining the methods of communication:
     *         - input: PrintWriter
     *         - output: BufferedReader
     *
     * @throws IOException: error occurring during initialization
     * @see connectToServer
     * @param none
     * @return none
     */
    private void getIO() throws IOException {
        output = new PrintWriter(new OutputStreamWriter(connection.getOutputStream()), true);
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
     *     After connection dependent objects are closed, exit the program.
     *
     * @throws IOException: error occurring during shutdown
     * @param none
     * @return none
     */
    private void closeAll() throws IOException {
        try {
            System.out.println("Exiting program...");
            output.println("EXIT"); // send exit message to Worker
            output.close();
            input.close();
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Failed to close client");
            throw new IOException(e);
        }
    }

    /*
     * Method:
     *     main (entry-point)
     *
     * Purpose:
     *     This creates a new client connecting to "localhost" in port 23755 (one of my SWD ports) executing on a single thread.
     *
     * @param args: cli arguments when program started
     * @return none
     */
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Client client = new Client("localhost", 23755);
        executorService.execute(client);
    }
}
