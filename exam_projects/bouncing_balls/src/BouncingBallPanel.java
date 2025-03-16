/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * Class:
 *     BouncingBallPanel
 *
 * Purpose:
 *     This class serves as the JPanel object holding the BouncingBall ball objects. This panel is multithreaded,
 *     allowing for multiple instances of BouncingBall to be 'run' to update the location of the balls in the panel.
 *     To achieve this, I used a Java Swing Timer to simulate a refresh rate, upon each timer completion the balls, are executed
 *     as Java Swing Applications ARE INHERENTLY NOT THREAD-SAFE.
 *
 * Multithreaded:
 *     Yes, this JPanel consists of multithreaded code
 *     Timer & SwingUtilities is used to safely update the Java Swing GUI
 *
 * Resources:
 *     Timer: Java Swing Timer (https://docs.oracle.com/javase/tutorial/uiswing/misc/timer.html) (https://docs.oracle.com/javase/8/docs/api/javax/swing/Timer.html)
 *         - Timer links each completion to an ActionEvent (implemented by interface ActionListener)
 *         - actionPerformed() is pointed to after each completion
 *
 */
public class BouncingBallPanel extends JPanel {
    /* balls: Array of BouncingBall objects (20 maximum... see constructor) */
    private BouncingBall[] balls;

    private JButton pauseButton;
    private JButton exitButton;
    private JLabel navbar;
    private JLabel footer;

    private boolean paused;

    /* executorService: Executor service servicing the runnable BouncingBall(s) in balls array*/
    ExecutorService executorService;

    Timer timer;

    /*
     * Constructor:
     *     BouncingBallPanel
     *
     * Purpose:
     *     Initialize the array of BouncingBall(s) and set the size to 100.
     *     Created cached ExecutorService
     *
     * @param none
     * @return none
     */
    public BouncingBallPanel() {
        // set ball array & animation to playing
        balls = new BouncingBall[100];
        paused = false;

        executorService = Executors.newCachedThreadPool();

        // set initial button ('pause') & link action
        pauseButton = new JButton();
        pauseButton.setBackground(Color.GREEN);
        pauseButton.setBorderPainted(true);
        pauseButton.setText("Playing");
        pauseButton.setOpaque(true);
        pauseButton.addActionListener(this::pauseActionButton);

        // exit button
        exitButton = new JButton();
        exitButton.setText("Exit");
        exitButton.setBackground(Color.RED);
        exitButton.setOpaque(true);
        exitButton.addActionListener(this::exitActionButton);

        // set labels
        navbar = new JLabel();
        navbar.setText("Matt Krueger | 23_11_BouncingBalls_Medium");

        JLabel directions = new JLabel();
        directions.setText("Right Click: Add Ball | Left Click: Remove Ball");

        this.add(navbar, BorderLayout.NORTH);
        this.add(pauseButton, BorderLayout.NORTH);
        this.add(exitButton, BorderLayout.NORTH);
        this.add(directions, BorderLayout.SOUTH);
    }

    public void startAnimation() {
        this.timer = new Timer(25, this::animate);
        this.timer.setRepeats(true);
        this.timer.start();
    }

    private void animate(ActionEvent actionEvent) {
        // execute each of the balls while program is not paused
        if (!paused) {
            updateBalls();
            executeBalls();
        }
        repaint();
    }

    public void pauseActionButton (ActionEvent e) {
        // print out to console what action is performed
        JButton button = (JButton) e.getSource();
        String action = button.getText();
        System.out.println(action);

        // map action to update flag & JButton
        if (action.equals("Playing")) {
            paused = true;
            pauseButton.setBackground(Color.ORANGE);
            pauseButton.setText("Paused");
        } else if (action.equals("Paused")) {
            paused = false;
            pauseButton.setBackground(Color.GREEN);
            pauseButton.setText("Playing");
        }
    }

    public void exitActionButton(ActionEvent e) {
        // stop timer
        timer.stop();

        // stop accepting new threads
        executorService.shutdown();

        // system.exit
        System.exit(0);
    }

    /*
     * Method:
     *     addBall
     *
     * Purpose:
     *     Add a new BouncingBall to the array of BouncingBalls.
     *
     *     This is a public method as it is used inside the BouncingBallMouseHandler.
     *     If I were to redo this project, I would make this 'protected', and split the packages so BouncingBallPanel and BouncingBallMouseHandler could interact
     *     in a safer way. This is not the safest, as a new ball could be added from anywhere.
     *
     * @param none
     * @return none
     */
    public void addBall(BouncingBall newBall) {
        // insert ball into array
        int id = newBall.getId();
        balls[id] = newBall;
    }

    /*
     * Method:
     *     removeLastBall
     *
     * Purpose:
     *     Remove the most recent ball from the array of BouncingBalls.
     *
     *     This is a public method as it is used inside the BouncingBallMouseHandler.
     *     If I were to redo this project, I would make this 'protected', and split the packages so BouncingBallPanel and BouncingBallMouseHandler could interact
     *     in a safer way. This is not the safest, as a new ball could be added from anywhere.
     *
     * @param none
     * @return none
     */
    public void removeLastBall() {
        for (int i = 0; i < 100; i++) {
            if (balls[i] == null) { // delete prev
                balls[i-1] = null;
                break;
            } else if (i == 99) { // delete last
                balls[i] = null;
                break;
            }
        }
    }

    /*
     * Method:
     *     getBallCount
     *
     * Purpose:
     *      Return an integer representing the count of non-null BouncingBall(s) in the BouncingBall array.
     *
     * @param none
     * @return int count: number of non-null bouncing balls
     */
    public int getBallCount() {
        int count = 0;
        for (BouncingBall ball : balls) {
            if (ball != null) {
                count++;
            }
        }
        return count;
    }

    /*
     * Method:
     *     paintComponent
     *
     * Purpose:
     *     Update the Java Swing GUI by repainting each of the BouncingBall(s) in the BouncingBall array:
     *         1. clear the screen
     *         2. cast Graphics as Graphics 2D to provide extended functionality (not implemented, but this is best practice)
     *         3. loop through BouncingBall array, obtaining relevant attributes (x & y values are updated)
     *         4. create new oval (ball since I am using same width and height) to depict each BouncingBall
     *
     * @param g: java.awt Graphics object to be cast as a 2D Graphics object.
     * @return
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        for (BouncingBall ball : balls) {
            if (ball != null) {
                Color color = ball.getColor();
                int x = ball.getX();
                int y = ball.getY();
                int radius = ball.getRadius();

                graphics2D.setColor(color);
                graphics2D.fillOval(x, y, radius * 2, radius * 2); // d = 2r
            }
        }
    }

    /*
     * Method:
     *     updateBalls
     *
     * Purpose:
     *     Update each individual ball on the EDT safely:
     *         1. loop through each ball
     *         2. perform bounce logic for each ball to see if the edge of the screen is reached
     *
     * @param none
     * @return none
     */
    private void updateBalls() {
        for (BouncingBall ball : balls) {
            if (ball != null) {
                if ((ball.getY() < 0) && (ball.getySpeed() < 0)) { // top bound (y): x, 0; speed -
                    ball.bounceY();
                } else if ((ball.getY() > this.getHeight() - (2*ball.getRadius())) && (ball.getySpeed() > 0)) { // bottom bound (y) x, height - 2radius ; speed +
                    ball.bounceY();
                }

                if ((ball.getX() < 0) && (ball.getxSpeed() < 0)) { // left bound (x): 0, y ; speed -
                    ball.bounceX();
                } else if ((ball.getX() > this.getWidth() - (2*ball.getRadius())) && (ball.getxSpeed() > 0)) { // right bound (x) width - 2radius, y ; speed +
                    ball.bounceX();
                }
            }
        }
    }

    /*
     * Method:
     *     executeBalls
     *
     * Purpose:
     *     This method executes each ball using the executor service before shutting down.
     *
     * @param none
     * @return none
     */
    private void executeBalls() {
        for (BouncingBall ball : balls) {
            if (ball != null) {
                executorService.submit(ball);
            }
        }
    }

    // Getters (not documented)
    public BouncingBall[] getBalls() {
        return balls;
    }

}