/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

package gui.controllers;

import game.Board;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.util.Objects;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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
public class GameController implements Controller {
    private MainProgramController mainProgramController;

    @FXML
    private Text playerOneText;

    @FXML
    private Text playerTwoText;

    @FXML
    private GridPane grid;

    private final BorderPane[][] boardPanes = new BorderPane[3][3];

    private boolean playerOneUp = true;

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
    private void initializeGrid() {
        // initialize grid items by looping through each index in the grid (node)
        //
        // make node a button that can be clicked (by user... non-human objects will interact differently; this makes most sense to me)
        // BorderPane - controls the background of the pane (used for winner)
        //
        // inside each BorderPane, there is a Button Node (allowing for interaction)
        // in each button node, there is a Text Node (representing who 'clicked')
        for (Node node : grid.getChildren()) {
            // initialize grid BorderPane, Button, and Text nodes
            if (node instanceof BorderPane) {
                BorderPane borderPane = (BorderPane) node;

                Integer rowIndex = GridPane.getRowIndex(borderPane);
                Integer colIndex = GridPane.getColumnIndex(borderPane);
                int row = Objects.requireNonNullElse(rowIndex, 0); // ide suggested... used to ensure that index exists, if not -> 0
                int col = Objects.requireNonNullElse(colIndex, 0);

                // add border pane to panes array
                boardPanes[row][col] = borderPane;

                // set button attributes
                Object child = borderPane.getCenter();
                if (child instanceof Button) {
                    Button button = (Button) child;
                    button.setText("");
                    button.setOnAction(this::tileButtonClicked);
                }
            }
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
    private void tileButtonClicked(ActionEvent e) {
        // obtain button & parent pane
        Button clicked = (Button) e.getSource();
        BorderPane parentPane = (BorderPane) clicked.getParent();

        // get indexes
        Integer rowIndex = GridPane.getRowIndex(parentPane);
        Integer colIndex = GridPane.getColumnIndex(parentPane);
        int row = Objects.requireNonNullElse(rowIndex, 0); // ide suggested... used to ensure that index exists, if not -> 0
        int col = Objects.requireNonNullElse(colIndex, 0);

        // logic for who clicked
        String text;
        if (playerOneUp) {
            text = "X";
        } else {
            text = "O";
        }

        // update attributes & disable button
        clicked.setFont(Font.font("System", FontWeight.BOLD, 20));
        clicked.setText(text);
        clicked.setDisable(true);

        // update board
        Board.newMove(row, col, text);

        // switch after clicked
        switchPlayerUp();

        // game logic
        Board.checkGameOver();
        String status = Board.getGameStatus();

        // switch game if necessary
        if (!(status.equals("Playing"))) {
            switchToGameOverScreen();
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
    private void initializeLabels() {
        // TODO :
        // logic to determine the type of player (human / computer)
        String prependOne = "Player 1 - Your Turn!";
        String prependTwo = "Player 2";

        // initial state (see switchPlayerUp())
        playerOneText.setText(prependOne);
        playerTwoText.setText(prependTwo);
        playerOneText.setFill(Color.GREEN);
        playerOneText.setUnderline(true);
        playerTwoText.setFill(Color.GRAY);
        playerTwoText.setUnderline(false);
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
    private void switchPlayerUp() {
        if (playerOneUp) {
            playerTwoText.setText("Player 2 - Your Turn!");
            playerOneText.setText("Player 1");
            playerTwoText.setFill(Color.GREEN);
            playerTwoText.setUnderline(true);
            playerOneText.setFill(Color.GRAY);
            playerOneText.setUnderline(false);

            playerOneUp = false;
        } else {
            playerOneText.setText("Player 1 - Your Turn!");
            playerTwoText.setText("Player 2");
            playerOneText.setFill(Color.GREEN);
            playerOneText.setUnderline(true);
            playerTwoText.setFill(Color.GRAY);
            playerTwoText.setUnderline(false);

            playerOneUp = true;
        }
    }

    /*
     * Method:
     *
     *
     * Purpose:
     *
     * @FXML part of fxml instance
     * @param
     * @return
     */
    @FXML
    public void initialize() {
        initializeGrid();
        initializeLabels();
    }

    /*
     * Method:
     *
     *
     * Purpose:
     *
     * @param
     * @return
     */
    public void switchToGameOverScreen() {
        mainProgramController.setBorderPaneCenter("../fxml/gameOverScreen.fxml");
    }

    /*
     * Method:
     *
     *
     * Purpose:
     *
     *
     * @override
     * @param
     * @return
     */
    @Override
    public void setMainProgramController(MainProgramController mainProgramController) {
        this.mainProgramController = mainProgramController;
    }
}