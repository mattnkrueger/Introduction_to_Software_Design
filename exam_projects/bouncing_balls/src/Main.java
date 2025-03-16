/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

import javax.swing.*;

/*
 * Class:
 *     Main (entry point)
 *
 * Purpose:
 *      This class starts the Java Swing Program by using the SwingUtilities invokeLater, to safely start the
 *      application.
 *
 */
public class Main {
    /*
     * Method:
     *     main
     *
     * Purpose:
     *     This method starts the program using SwingUtilities. It defines a sub-method of runnable run().
     *
     * @see run()
     * @param String[] args: user parameters passed when starting application. (I suggest using the IDE 'start' button as I do nothing with these arguments)
     * @return none
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            /*
             * Method:
             *     run
             *
             * Purpose:
             *     Run the Java Swing program on the Event Dispatch Thread to safely run the multithreaded Bouncing Ball Program.
             *     Creates a new JFrame (BouncingBallFrame) and starts the application.
             *
             * @param none
             * @return none
             */
            @Override
            public void run() {
                // create and show frame holding balls
                BouncingBallFrame frame = new BouncingBallFrame();
                frame.startApplication();
            }
        });
    }
}