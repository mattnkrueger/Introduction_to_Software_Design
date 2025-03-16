/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

package gui.controllers;

import game.Board;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import gui.utils.BackgroundColors;
import java.awt.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

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
public class GameOverController implements Controller, Initializable {
    /* "this is a attribute" */
    private MainProgramController mainProgramController;

    /* "this is a attribute" */
    @FXML
    private Button restartGame;

    /* "this is a attribute" */
    @FXML
    private Button exitGame;

    /* "this is a attribute" */
    @FXML
    private Label gameStatus;

    /* "this is a attribute" */
    @FXML
    private GridPane gameOverGrid;

    /* "this is a attribute" */
    @FXML
    private Text scratchText;

    /* "this is a attribute" */
    @FXML
    private Text playerOneText;

    /* "this is a attribute" */
    @FXML
    private Text playerTwoText;

    /*
     * Method:
     *
     *
     * Purpose:
     *
     * @override
     * @param
     * @return
     */
    @Override
    public void setMainProgramController(MainProgramController mainProgramController) {
        this.mainProgramController = mainProgramController;
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
    public void setRestartGame() {
        // logic to restart game
        mainProgramController.setBorderPaneCenter("../fxml/game.fxml");
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
    private void initializeButtons() {
        restartGame.setOnAction(event -> setRestartGame());
        exitGame.setOnAction(event -> System.exit(0));
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
    private void initializeLabels() {
        gameStatus.setText(Board.getGameStatus());
        Board.updateScoreCounts();
        playerOneText.setText("Player 1: " + Board.getPlayerOneWinCount());
        playerTwoText.setText("Player 2: " + Board.getPlayerTwoWinCount());
        scratchText.setText("Scratches: " + Board.getScratchesCount());
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
    private void initializeWinningRow() {
        // initialize grid items by looping through each index in the grid (node)
        //
        // make node a button that can be clicked (by user... non-human objects will interact differently; this makes most sense to me)
        // BorderPane - controls the background of the pane (used for winner)
        //
        // inside each BorderPane, there is a Button Node (allowing for interaction)
        // in each button node, there is a Text Node (representing who 'clicked')
        for (Node node : gameOverGrid.getChildren()) {
            // initialize grid BorderPane, Button, and Text nodes
            if (node instanceof BorderPane) {
                BorderPane borderPane = (BorderPane) node;

                Integer rowIndex = GridPane.getRowIndex(borderPane);
                Integer colIndex = GridPane.getColumnIndex(borderPane);
                int row = Objects.requireNonNullElse(rowIndex, 0); // ide suggested... used to ensure that index exists, if not -> 0
                int col = Objects.requireNonNullElse(colIndex, 0);

                // set button attributes
                Object child = borderPane.getCenter();
                if (child instanceof Button) {
                    String tileText = Board.getBoard()[row][col];
                    String text = (tileText.equals("-")) ? "" : tileText;

                    Button button = (Button) child;
                    button.setFont(Font.font("System", FontWeight.BOLD, 20));
                    button.setText(text);


                    // check if scratch or color
                    int lastGame = Board.getBoardLog().size() - 1;
                    String lastResult = Board.getBoardLog().get(lastGame);

                    Color color = Board.getBoardColors()[row][col];
                    if (lastResult.equals("Scratch")) {
                        button.setBackground(BackgroundColors.getRedBackground());
                    }
                    else if (color.equals(Color.GREEN)) {
                        button.setBackground(BackgroundColors.getGreenBackground());
                    } else {
                        button.setBackground(BackgroundColors.getDefaultBackground());
                    }
                    button.setDisable(true);
                }
            }
        }
        Board.resetBoard();
    }

    /*
     * Method:
     *
     *
     * Purpose:
     *
     * @override
     * @param
     * @return
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeButtons();
        initializeLabels();
        initializeWinningRow();
    }
}