/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/*
 * Class:
 *     BouncingBall
 *
 * Purpose:
 *     A 'ball' (circle) that is produced on the parent BouncingBallPanel JPanel.
 *     This class provides methods to 'move' the ball across the screen, calculating
 *     the new x & y locations upon running its thread.
 *
 * Implements:
 *     Runnable
 *
 * @see BouncingBallPanel
 */
public class BouncingBall implements Runnable, ActionListener {
    /* id: 'this' ball's id (0-19) */
    private int id;

    /* color: 'this' ball's awt.Color */
    private Color color;

    /* radius: 'this' ball's radii */
    private int radius;

    /* x: 'this' ball's x loc inside parent JPanel */
    private int x;

    /* y: 'this' ball's y loc inside parent JPanel */
    private int y;

    /* xSpeed: 'this' ball's x speed */
    private int xSpeed;

    /* ySpeed: 'this' ball's y speed */
    private int ySpeed;

    /* timer: Java Swing timer used to animate ball movement */
    Timer timer;

    /*
     * Constructor:
     *     BouncingBall
     *
     * Purpose:
     *     Set all attributes:
     *     id - passed
     *     color - passed
     *     radius - random (10-40)
     *     x - compensates for mouseX offset
     *     y - compensates for mouseY offset
     *     xSpeed - random speed (5-10) with random direction (0,1)
     *     ySpeed - random speed (5-10) with random direction (0,1)
     *     timer - 25 ms timer for ball animation
     *
     * @param id: ball number (0-19)
     * @param color: awt.Color
     * @param mouseX: x location of mouse click
     * @param mouseY: y location of mouse click
     * @return
     */
    public BouncingBall(int id, Color color, int mouseX, int mouseY) {
        Random random = new Random();
        int randomXDir = random.nextInt(2);
        int randomYDir = random.nextInt(2);
        int randomXSpeed = random.nextInt(6) + 5;
        int randomYSpeed = random.nextInt(6) + 5;

        this.id = id;
        this.color = color;

        this.radius = random.nextInt(31) + 10;
        this.x = mouseX - radius;
        this.y = mouseY - radius;

        if (randomXDir == 0) {
            this.xSpeed = randomXSpeed * -1;
        } else {
            this.xSpeed = randomXSpeed;
        }

        if (randomYDir == 0) {
            this.ySpeed = randomYSpeed * -1;
        } else {
            this.ySpeed = randomYSpeed;
        }
    }

    /*
     * Method:
     *     setX
     *
     * Purpose:
     *     change the x location of the ball inside its parent JPanel
     *
     * @param x: ball new x location
     * @return none
     */
    public void setX(int x) {
        this.x = x;
    }

    /*
     * Method:
     *     setY
     *
     * Purpose:
     *     change the y location of the ball inside its parent JPanel
     *
     * @param y: ball new y location
     * @return none
     */
    public void setY(int y) {
        this.y = y;
    }

    /*
     * Method:
     *    bounceX
     *
     * Purpose:
     *    invert the x direction of the ball
     *
     * @param none
     * @return none
     */
    public void bounceX() {
        xSpeed = xSpeed * -1;
    }

    /*
     * Method:
     *    bounceY
     *
     * Purpose:
     *    invert the y direction of the ball
     *
     * @param none
     * @return none
     */
    public void bounceY() {
        ySpeed = ySpeed * -1;
    }

    /*
     * Method:
     *     update
     *
     * Purpose:
     *     set the new x & y locations with the calculated values
     *
     * @see setX()
     * @see setY()
     * @param none
     * @return none
     */
    public void update() {
        setX(x + xSpeed);
        setY(y + ySpeed);
    }

    /*
     * Method:
     *     run
     *
     * Purpose:
     *     threaded method pointing to update
     *
     * @see update()
     * @overrides Runnable
     * @param none
     * @return none
     */
    @Override
    public void run() {
        update();
    }

    /*
     * Method:
     *     actionPerformed
     *
     * Purpose:
     *     This actionPerformed listens for the completion of the animation timer.
     *     Upon each completion, this method updates the ball location.
     *     This is the method responsible for creating the animation.
     *
     * @overrides ActionListener
     * @param ActionEvent e: event (not used, but required for override of ActionListener which listens to the recurring timer)
     * @return none
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        update();
    }

    // Getters (not documented)
    public int getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }

    public int getxSpeed() {
        return xSpeed;
    }

    public int getySpeed() {
        return ySpeed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }
}