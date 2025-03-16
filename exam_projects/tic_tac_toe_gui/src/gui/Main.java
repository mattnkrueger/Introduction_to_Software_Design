/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

package gui;

import game.Board;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import gui.utils.WebPages;
import gui.controllers.MainProgramController;


/*
 * Class:
 *
 *
 * Purpose:
 *
 *
 * Multithreaded:
 *
 *
 */
public class Main extends Application {
    /*
     * Method:
     *
     *
     * Purpose:
     *
     *
     * @param
     * @return
     */
    @Override
    public void start(Stage stage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/mainProgram.fxml"));
            Parent root = loader.load();

            MainProgramController controller = new MainProgramController();
            controller.setBorderPaneCenter("../fxml/home.fxml");

            // Set up the scene and stage
            Scene scene = new Scene(root);
            stage.setScene(scene);

            // Set program icon and additional stage styling
            stage.setTitle("S20_TicTacToe_Hard");
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            throw new RuntimeException("Program initialization failed: " + e);
        }
    }

    /*
     * Method:
     *
     *
     * Purpose:
     *
     *
     * @param
     * @return
     */
    public static void main(String[] args) {
        // initialize Board utility class (all static methods)
        try {
            Board.initialize();
        } catch (Exception e) {
            throw new RuntimeException("Board.initialize() failed: " + e);
        }

        // initialize webpage urls
        try {
            WebPages.initialize();

        } catch (Exception e) {
            throw new RuntimeException("WebPages.initialize() failed: " + e);
        }

        launch(args);
    }
}