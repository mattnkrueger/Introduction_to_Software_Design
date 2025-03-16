/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.util.concurrent.ExecutorService;

/*
 * Class:
 *     BouncingBallFrame
 *
 * Purpose:
 *     This class is the JFrame instance of the Java Swing application.
 *     It serves as the highest level in the hierarchy and is parent to the BouncingBallPanel.
 *     The 'this' keyword references the JFrame class which BouncingBallFrame extends i.e. 'this' is a JFrame object.
 *
 * Extends:
 *     JFrame
 *
 * @see BouncingBallPanel
 */
public class BouncingBallFrame extends JFrame {
    /* HEIGHT: height of the application; final */
    private final int HEIGHT = 500;

    /* WIDTH: width of the application; final */
    private final int WIDTH = 500;

    /* panel: BouncingBallPanel child panel of the application JFrame frame */
    private BouncingBallPanel panel;

    /* executorService: ExecutorService providing method execute() to start threads (per SWD directions) */
    private ExecutorService executorService;

    /*
     * Constructor:
     *     BouncingBallFrame
     *
     * Purpose:
     *     point to initialize to start the Java Swing Application and Bouncing Ball Program
     *
     * @see initialize()
     * @param none
     * @return none
     */
    public BouncingBallFrame() {
        initialize();
    }

    /*
     * Method:
     *     initialize
     *
     * Purpose:
     *     Create the Java Swing JFrame application with the child BouncingBallPanel JPanel.
     *     Link a mouse handler to the child panel to for user interaction with the application.
     *     Create an executor to execute a single thread (BouncingBallPanel).
     *
     * @see BouncingBallPanel
     * @param none
     * @return none
     */
    private void initialize() {
        this.setTitle("23_11_BouncingBalls_Medium");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);

        panel = new BouncingBallPanel();
        panel.setSize(WIDTH, HEIGHT);
        BouncingBallMouseHandler mouseHandler = new BouncingBallMouseHandler(panel);
        panel.addMouseListener(mouseHandler);

        this.add(panel, BorderLayout.CENTER);
    }

    /*
     * Method:
     *     show
     *
     * Purpose:
     *     make 'this' (JFrame application) visible and run the BouncingBallPanel thread
     *
     * @param none
     * @return none
     */
    public void startApplication() {
        this.setResizable(false);
        this.setVisible(true);
        panel.startAnimation();
    }

    // Getters (not documented)
    public BouncingBallPanel getPanel() {
        return panel;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }
}