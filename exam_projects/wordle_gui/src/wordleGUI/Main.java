/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

package wordleGUI;

import game.WebPages;
import wordleGUI.utils.WordleGame;
import spellcheck.SpellChecker;
import wordleGUI.controllers.MainProgramController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * Class:
 *     Main (entry point)
 *
 * Purpose:
 *     This is the launcher for the JavaFX Wordle GUI application.
 *     The runnable main method initializes and starts the GUI.
 *
 * Extends:
 *     Application:
 *         JavaFX application; Launched by 'launch(args),' which points to start method.
 *         Override start method overrides Application to start the JavaFX application.
 */
public class Main extends Application {
    /*
     * Method:
     *
     *
     * Purpose:
     *     This method starts the JavaFX program, loading the 'mainProgram.fxml.'
     *
     *     Defined inside 'mainProgram.fxml,' a main content fxml page is sandwiched between 'navbar.fxml' and 'footer.fxml' panes.
     *     The MainProgramController class is used to control the content shown on 'mainProgram.fxml' as well as the navigation buttons.
     *
     * Steps:
     *     1. load FXML 'mainProgram.fxml' as the root node.
     *     2. obtain the controller of the loaded fxml and set the main content to home.fxml
     *     3. set the JavaFX scene & stage (this.stage comes from super Application class)
     *     4. show the JavaFX application
     *
     * @see mainProgram.fxml
     * @see MainProgramController
     * @overrides Application
     * @param
     * @return
     */
    @Override
    public void start(Stage stage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/mainProgram.fxml"));
            Parent root = loader.load();

            // the code below does not change anything as home.fxml is set inside MainProgramController which gets initialized
            // by loader.load() on "fxml/mainProgram.fxml"
            MainProgramController mainProgramController = (MainProgramController) loader.getController();
            mainProgramController.setBorderPaneCenter("../fxml/home.fxml");

            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.setTitle("S52_WordleGUI_Hard");
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            throw new RuntimeException("Program initialization failed: " + e);
        }
    }

    /*
     * Method:
     *     main
     *
     * Purpose:
     *     This runnable method initializes dependencies for the Wordle GUI and launches the Wordle GUI application:
     *
     *     Initialize:
     *         1. SpellChecker utility class
     *         2. WordleGame utility class
     *         3. WebPages utility class
     *
     *     Launching:
     *         The 'launch(args);' method is used to start the JavaFX application. This code runs the overridden start method of Application class.
     *
     * @see SpellChecker
     * @see WordleGame
     * @see WebPages
     * @param args: user specified program arguments
     * @return none
     */
    public static void main(String[] args) {
        try {
            SpellChecker.initialize();
        } catch (Exception e) {
            throw new RuntimeException("SpellChecker.initialize() failed: " + e);
        }

        try {
            WordleGame.initialize();
        } catch (Exception e) {
            throw new RuntimeException("WordleGame.initialize() failed: " + e);
        }

        try {
            WebPages.initialize();

        } catch (Exception e) {
            throw new RuntimeException("WebPages.initialize() failed: " + e);
        }

        launch(args);
    }
}