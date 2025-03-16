/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

package game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

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
public class Board {
    /* "this is a attribute" */
    private static String[][] board;

    /* "this is a attribute" */
    private static String boardStatus;

    /* "this is a attribute" */
    private static Color[][] boardColors;

    /* "this is a attribute" */
    private static ArrayList<String> boardLog;

    /* "this is a attribute" */
    private static int playerOneWinCount;

    /* "this is a attribute" */
    private static int playerTwoWinCount;

    /* "this is a attribute" */
    private static int scratchesCount;

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
    public static void initialize() {
        // board array for looping
        board = new String[3][3];

        // color array for gameOverScreen
        boardColors = new Color[3][3];

        // initialize all to "-" to indicated unguessed
        resetBoard();

        // initialize game log
        boardLog = new ArrayList<>();
    }

    public static void newMove(int row, int col, String letter) {
        board[row][col] = letter;
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
    public static String[][] getBoard() {
        return board;
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
    public static Color[][] getBoardColors() {
        return boardColors;
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
    public static void printBoard() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Updated Board: \n");
        for (int i = 0; i < board[0].length; i++) {
            stringBuilder.append("[");
            for (int j = 0; j < board[0].length; j++) {
                String letter = (board[i][j] != null) ? board[i][j] : "-"; // learned how to do one-liner conditional :) thought I could use it here since this is an abstracted method. deprecated; I made some other logic that ensures null is never triggered.
                stringBuilder.append(letter);
                if (j != 2) {
                    stringBuilder.append(", ");
                }
            }
            stringBuilder.append("]");
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder.toString());
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
    private static boolean checkDiagonals(String letter) {
        String ind00 = board[0][0];
        String ind11 = board[1][1];
        String ind02 = board[0][2];
        String ind20 = board[2][0];
        String ind22 = board[2][2];

        if (ind00.equals(letter) && ind00.equals(ind11) && ind00.equals(ind22)) { // check diagonal 1 [0,0], [1,1], [2,2] for letter
            boardColors[0][0] = Color.GREEN;
            boardColors[1][1] = Color.GREEN;
            boardColors[2][2] = Color.GREEN;
            return true;
        } else if (ind02.equals(letter) && ind02.equals(ind11) && ind02.equals(ind20)) { // check diagonal 2 [0,2], [1,1], [2,0] for letter
            boardColors[0][2] = Color.GREEN;
            boardColors[1][1] = Color.GREEN;
            boardColors[2][0] = Color.GREEN;
            return true;
        } else {
            return false;
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
    private static boolean checkRow(int index, String letter) {
        for (int i = 0; i < board[0].length; i++) {
            if (!(board[index][i].equals(letter))) {
                resetColors();
                return false;
            } else {
                boardColors[index][i] = Color.GREEN;
            }
        }
        return true;
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
    private static boolean checkRows(String letter) {

        for (int i = 0; i < board[0].length; i++) {
            if (checkRow(i, letter)) {
                return true;
            }
        }
        return false;
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
    private static boolean checkColumn(int index, String letter) {
        for (int i = 0; i < board[0].length; i++) {
            if (!(board[i][index].equals(letter))) {
                resetColors();
                return false;
            } else {
                boardColors[i][index] = Color.GREEN;
            }
        }
        return true;
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
    private static boolean checkColumns(String letter) {
        for (int i = 0; i < board[0].length; i++) {
            if (checkColumn(i, letter)) {
                return true;
            }
        }
        return false;
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
    private static boolean checkPlayerOneThreeInRow() {
        String letter = "X";
        if (checkDiagonals(letter)) {
            return true;
        } else if (checkRows(letter)) {
            return true;
        } else if (checkColumns(letter)) {
            return true;
        } else {
            return false;
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
    private static boolean checkPlayerTwoThreeInRow() {
        String letter = "O";
        if (checkDiagonals(letter)) {
            return true;
        } else if (checkRows(letter)) {
            return true;
        } else if (checkColumns(letter)) {
            return true;
        } else {
            return false;
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
    public static void checkGameOver() {
        if (checkPlayerOneThreeInRow()) {
            boardStatus = "Player One Won!";
            boardLog.add("Player One");
        } else if (checkPlayerTwoThreeInRow()) {
            boardStatus = "Player Two Won!";
            boardLog.add("Player Two");
        } else {
            // check that board is complete by checking for existence of null items
            for (int i = 0; i < board[0].length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j].equals("-")) {
                        boardStatus = "Playing"; // signify game still playing
                        return;
                    }
                }
            }
            boardStatus = "Scratch!";
            boardLog.add("Scratch");
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
    public static void resetBoard() {
        for (int i = 0; i < board[0].length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = ("-"); // reset BOTH board and colors
                boardColors[i][j] = Color.BLACK;
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
    private static void resetColors() {
        for (int i = 0; i < board[0].length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                boardColors[i][j] = Color.BLACK;
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
    public static void updateScoreCounts() {
        playerOneWinCount = Collections.frequency(boardLog, "Player One");
        playerTwoWinCount = Collections.frequency(boardLog, "Player Two");
        scratchesCount = Collections.frequency(boardLog, "Scratch");
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
    public static int getPlayerOneWinCount() {
        return playerOneWinCount;
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
    public static int getPlayerTwoWinCount() {
        return playerTwoWinCount;
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
    public static int getScratchesCount() {
        return scratchesCount;
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
    public static String getGameStatus() {
        return boardStatus;
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
    public static ArrayList<String> getBoardLog() {
        return boardLog;
    }
}
