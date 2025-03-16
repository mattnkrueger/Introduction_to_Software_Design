/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

package wordleGUI.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import wordleGUI.utils.WordleGame;
import java.net.URL;
import java.util.ResourceBundle;

/*
 * Class:
 *     GameOverController
 *
 * Purpose:
 *     This class handles the user interaction with the 'gameOver.fxml' screen.
 *
 *     Included in the game over screen are button options for restarting or exiting the program.
 */
public class GameOverController implements Controller {
    /* mainProgramController: linked controller to the MainProgramController to enable page switch functionality */
    private MainProgramController mainProgramController;

    /* restartGame: JavaFX Button to restart the WordleGame */
    @FXML
    private Button restartGame;

    /* exitGame: JavaFX Button to safely exit the program */
    @FXML
    private Button exitGame;

    /* gameStatus: JavaFX Label giving the result of the previous WordleGame */
    @FXML
    private Label gameStatus;

    /* correctWordGameOver: JavaFX Label for the correct word of the previous WordleGame */
    @FXML
    private Label correctWordGameOver;

    /* guesses: JavaFX TextArea to list the user's guesses for the previous WordleGame */
    @FXML
    private TextArea guesses;

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
     *     setRestartGame
     *
     * Purpose:
     *      This method changes the screen to 'game.fxml'.
     *      Upon changing, a fresh Wordle game is initialized... again, this is the side effect of my poorly thought out utility class WordleGame
     *
     * @param none
     * @return none
     */
    public void setRestartGame() {
        mainProgramController.setBorderPaneCenter("../fxml/game.fxml");
    }

    /*
     * Method:
     *     initializeButtons
     *
     * Purpose:
     *     This method is called when initialized to set the actions of JavaFX Buttons on-screen
     *
     * @param none
     * @return none
     */
    private void initializeButtons() {
       restartGame.setOnAction(event -> setRestartGame());
       exitGame.setOnAction(event -> System.exit(0));
    }


    /*
     * Method:
     *     initializeLabels
     *
     * Purpose:
     *     This method is called when initialized to obtain the correct word, result of Wordle, and user guesses,
     *         putting them into their respective JavaFX nodes.
     *
     * @param none
     * @return none
     */
    private void initializeLabels() {
        correctWordGameOver.setText("Correct Word: " + WordleGame.getCorrectWord());

        if (WordleGame.getGameStatus() == WordleGame.GameStatus.WIN) {
            gameStatus.setText("Congrats! You Won");
        } else if (WordleGame.getGameStatus() == WordleGame.GameStatus.LOSS) {
            gameStatus.setText("Sorry! You Lost");
        } else {
            gameStatus.setText("Error - Unknown GameStatus");
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Your Guesses: \n\n");
        for (int i = 0; i < 6; i++) {
            String guess = WordleGame.getGuesses()[i];
            if (!(guess == null)) {
               stringBuilder.append("Guess " + (i+1) + ": " + guess + "\n");
            }
        }
        guesses.setText(stringBuilder.toString());
    }

    /*
     * Method:
     *     initialize
     *
     * Purpose:
     *     This method is needed to correctly produce the fxml components on the screen.
     *
     *     Since I have already injected the wanted visuals into my fxml file, the initalizable
     *        is called when this controller is loaded to ensure the necessary setup of the UI styles,
     *        and initial states for the screen.
     * @param none
     * @return none
     */
    public void initialize() {
        initializeButtons();
        initializeLabels();
    }
}