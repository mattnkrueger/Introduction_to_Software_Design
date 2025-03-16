/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

/*
 * Class:
 *     BouncingBallMouseHandler
 *
 * Purpose:
 *     This class provides an interface (Mouse Events) for user interaction with the Bouncing Ball program.
 *     Linked to the handler is a BouncingBallPanel, which is listened to for user mouse click action.
 *     The only method implemented is mousePressed()
 *
 * Implements:
 *     MouseListener
 */
public class BouncingBallMouseHandler implements MouseListener {
    /* panel: BouncingBallPanel which the handler is listening to */
    private BouncingBallPanel panel;

    /* numberOfBalls: counter variable counting the number of clicks by the user */
    private int numberOfBalls;

    /*
     * Constructor:
     *     BouncingBallMouseHandler
     *
     * Purpose:
     *     Set attributes:
     *         panel - passed
     *         click - 0
     *
     * @param none
     * @return none
     */
    public BouncingBallMouseHandler(BouncingBallPanel panel) {
        this.panel = panel;
        this.numberOfBalls = 0;
    }

    /*
     * Method:
     *     mousePressed
     *
     * Purpose:
     *     Add a new ball to the BouncingBallPanel JPanel if the number of balls if greater than 0 and less than 20.
     *     Each ball has a random color determined by random r, g, and b values.
     *     Updates the counter for numberOfBalls
     *
     * @override MouseListener
     * @param event: MouseEvent 'click'
     * @return none
     */
    @Override
    public void mousePressed(MouseEvent event) {
        int xLoc = event.getX();
        int yLoc = event.getY();

        Random random = new Random();

        if (SwingUtilities.isLeftMouseButton(event) && numberOfBalls < 100) { // if left button, add & increment, if the 20 ball maximum has yet to be reached
            int r = random.nextInt(256);
            int g = random.nextInt(256);
            int b = random.nextInt(256);
            Color color = new Color(r,g,b);

            BouncingBall ball = new BouncingBall(numberOfBalls, color, xLoc, yLoc);
            panel.addBall(ball);;
            numberOfBalls++;
        } else if (SwingUtilities.isRightMouseButton(event) && numberOfBalls > 0) { // if right button, delete last if there are balls on screen
            panel.removeLastBall();
            numberOfBalls--;
        } else {
            System.out.println("Bounds Reached\nballs.length: " + panel.getBallCount());
        }
        panel.repaint();
    }

    // Getters (not documented)
    public BouncingBallPanel getPanel() {
        return panel;
    }

    public int getNumberOfBalls() {
        return numberOfBalls;
    }

    // Other MouseListener events. I will not document these as they are not implemented
    @Override
    public void mouseClicked(MouseEvent event) {
    }

    @Override
    public void mouseReleased(MouseEvent event) {
    }

    @Override
    public void mouseEntered(MouseEvent event) {
    }

    @Override
    public void mouseExited(MouseEvent event) {
    }
}
