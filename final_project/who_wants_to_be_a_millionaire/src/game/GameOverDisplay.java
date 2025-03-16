package game;

import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * The GameOverDisplay is a class that creates a window that will be displayed when users successfully complete the game
 * Once users exit out of this window, the game terminates
 */
public class GameOverDisplay extends JFrame{
    /**
     * Method that displays Game Over JFrame when user completes game
     */
    public GameOverDisplay(int currentLives) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1250,900);

        String filepath;
        if (currentLives > 0) { // win
            setTitle("You are now a millionaire!!!!!!!!!!!!");
            // Load the image
            filepath = "Final_Project_SWD/res/moneyMoneyMoney.jpg";
        } else { // loss
            setTitle("You are NOT a millionaire!!!!!!!!!!!!");
            filepath = "Final_Project_SWD/res/lossDisplay.png";
        }

        try {
            // use buffered image to hold the png to be displayed
            BufferedImage image = ImageIO.read(new File(filepath));
            JLabel imageLabel = new JLabel(new ImageIcon(image)); // attach the image
            getContentPane().add(imageLabel); //add imageLabel to the content pane
        } catch (IOException e) {
            e.printStackTrace();
        }
        setVisible(true);
    }
}
