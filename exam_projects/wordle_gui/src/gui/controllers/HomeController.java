/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

package gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/*
 * Class:
 *     HomeController
 *
 * Purpose:
 *     This class defines the user interaction with the 'home.fxml' screen,
 *         which is first shown when the program is started.
 *
 * Implements:
 *     Controller - links to main program
 */
public class HomeController implements Controller {
    /* playButton: JavaFX button with action to start the WordleGame */
    @FXML
    private Button playButton;

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
    public void switchToGame(ActionEvent e) {
        mainProgramController.setBorderPaneCenter("../fxml/game.fxml");
    }
}