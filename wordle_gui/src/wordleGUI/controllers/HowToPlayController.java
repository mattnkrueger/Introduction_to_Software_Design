/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

package wordleGUI.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/*
 * Class:
 *     HowToPlayController
 *
 * Purpose:
 *     This class defines the user interaction with the 'howToPlay.fxml' screen,
 *         which gives the user an explanation of how the Wordle Game is played out.
 *
 * Attribution:
 *     Rules: New York Times Games (https://www.nytimes.com/2023/08/01/crosswords/how-to-talk-about-wordle.html)
 *
 * Implements:
 *     Controller - links to main program
 */
public class HowToPlayController implements Controller {
    /* backToGame: JavaFX Button to switch to Wordle game. SIDE EFFECT - clicking this button creates new game; it does not go back to current */
    @FXML
    private Button backToGame;

    /* mainProgramController: links this page to the MainProgramController to give access to switch pages */
    private MainProgramController mainProgramController;

    /*
     * Method:
     *     setMainProgramController
     *
     * Purpose:
     *     Link the controller with the parent MainProgramController.
     *
     * @param mainProgramController: MainProgramController object of the JavaFX application
     * @return none
     */
    @Override
    public void setMainProgramController(MainProgramController mainProgramController) {
        this.mainProgramController = mainProgramController;
    }

    /*
     * Method:
     *     switchToGame
     *
     * Purpose:
     *     This method switches the screen to 'game.fxml', starting a fresh Wordle game.
     *
     * @param none
     * @return none
     */
    @FXML
    public void switchBackToGame(ActionEvent e) {
        mainProgramController.setBorderPaneCenter("../fxml/game.fxml");
    }
}
