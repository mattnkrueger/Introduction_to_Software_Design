/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

package utils;

import players.Player;

/*
 * Class:
 *     Board
 *
 * Purpose:
 *     This class represents a Tic-Tac-Toe board.
 *     The size of this board is 3x3 (row, column).
 *     This class provides methods to safely add markers, check for winner, and prettily format screen outputs.
 *
 *     Per SWD directions, polymorphism is used to abstract away the type of player playing.
 *     To achieve this, I mask the HumanPlayer or ComputerPlayer under its super Player class.
 *     Player specific code is used to check for board winner.
 */
public class Board {
    /* grid: String array holding player selections ('X'/'O'). Array set to 3x3  */
    private String[][] grid;

    /* rowOneIndexHelp: string representing row one's row,col pairs. */
    private String rowOneIndexHelp = "[ 0,0 | 0,1 | 0,2 ]";

    /* rowOneIndexHelp: string representing row two's row,col pairs. */
    private String rowTwoIndexHelp = "[ 1,0 | 1,1 | 1,2 ]";

    /* rowOneIndexHelp: string representing row three's row,col pairs. */
    private String rowThreeIndexHelp = "[ 2,0 | 2,1 | 2,2 ]";

    /*
     * Constructor:
     *     Board
     *
     * Purpose:
     *     initialize the Board to a 3x3 grid of "-" representing an empty board.
     *
     * @see initializeBoard
     * @param none
     * @return none
     */
    public Board() {
        initializeBoard();
    }

    /*
     * Method:
     *     initializeBoard
     *
     * Purpose:
     *     Create a new grid 3x3 array of strings.
     *     Insert "-" at each index of the 3x3 grid array.
     *     "-" represents an empty tile in the Tic-Tac-Toe board.
     *
     * @param none
     * @return none
     */
    private void initializeBoard() {
        grid = new String[3][3];
        for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = "-"; // '-' identifies an empty tile. this is better visually when printed to screen
            }
        }
    }

    /*
     * Method:
     *     isValidMove
     *
     * Purpose:
     *     Return a boolean for if the requested move is valid.
     *     Invalid: requested tile already chosen; a tile that is not empty placeholder "-".
     *
     * @param tile: integer array of size 2 holding selected [row,col] pair
     * @return boolean: grid[row][col].equals("-") - true if empty ("-") false if not (ex: "X")
     */
    private boolean isValidMove(int[] tile) {
        int row = tile[0];
        int col = tile[1];
        return grid[row][col].equals("-");
    }

    /*
     * Method:
     *     attemptToUpdateGrid
     *
     * Purpose:
     *     This method tries to update the grid with the player marker in the specified location.
     *     If the move is valid, the grid will be updated at selected tile, and 'true' flag returned.
     *     If the move is invalid, the grid will remain the same, and 'false' flag returned.
     *
     * @param tile: integer array of size 2 holding selected [row,col] pair
     * @param player: Player object for player creating the move. This is used to obtain the player's marker.
     * @return boolean: returns boolean flag for if the update to the grid was successful.
     */
    public boolean attemptToUpdateGrid(int[] tile, Player player) {
        if (isValidMove(tile)) {
            int row = tile[0];
            int col = tile[1];
            grid[row][col] = player.getLetter();
            return true;
        } else {
            return false;
        }
    }

    /*
     * Method:
     *     checkDiagonals
     *
     * Purpose:
     *     Return true if any of the diagonals contain three in a diagonal of specified string letter.
     *
     *     This method defines the grid indexes for the two diagonals being checked and checks for instances of letter.
     *
     * @param letter: string letter marker
     * @return boolean: true if three in a diagonal, false if not
     */
    private boolean checkDiagonals(String letter) {
        String ind00 = grid[0][0];
        String ind11 = grid[1][1];
        String ind02 = grid[0][2];
        String ind20 = grid[2][0];
        String ind22 = grid[2][2];

        if (ind00.equals(letter) && ind00.equals(ind11) && ind00.equals(ind22)) {
            return true;
        } else if (ind02.equals(letter) && ind02.equals(ind11) && ind02.equals(ind20)) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * Method:
     *     checkRow
     *
     * Purpose:
     *     Return true if the specified row contains a three in a row of specified letter.
     *
     *     This method loops through columns in specified row and checks for instances of letter.
     *
     * @param index: index of row to be checked
     * @param letter: string letter marker
     * @return boolean: true if three in a row, false if not
     */
    private boolean checkRow(int index, String letter) {
        for (int i = 0; i < grid[0].length; i++) {
            if (!(grid[index][i].equals(letter))) {
                return false;
            }
        }
        return true;
    }

    /*
     * Method:
     *     checkRows
     *
     * Purpose:
     *     Return true any row contains a three in a row of the specified letter
     *
     *     This method loops through rows and checks each row for three in a row.
     *
     * @param letter: string letter marker
     * @return boolean: true if three in a row in any row, false if not in any row
     */
    private boolean checkRows(String letter) {

        for (int i = 0; i < grid[0].length; i++) {
            if (checkRow(i, letter)) {
                return true;
            }
        }
        return false;
    }

    /*
     * Method:
     *     checkColumn
     *
     * Purpose:
     *     Return true if the specified column contains a three in a column of specified letter.
     *
     *     This method loops through columns in specified column and checks for instances of letter.
     *
     * @param index: index of column to be checked
     * @param letter: string letter marker
     * @return boolean: true if three in a column, false if not
     */
    private boolean checkColumn(int index, String letter) {
        for (int i = 0; i < grid[0].length; i++) {
            if (!(grid[i][index].equals(letter))) {
                return false;
            }
        }
        return true;
    }

    /*
     * Method:
     *     checkColumns
     *
     * Purpose:
     *     Return true any row contains a three in a column of the specified letter
     *
     *     This method loops through columns and checks each row for three in a row.
     *
     * @param letter: string letter marker
     * @return boolean: true if three in a column in any column, false if not in any column
     */
    private boolean checkColumns(String letter) {
        for (int i = 0; i < grid[0].length; i++) {
            if (checkColumn(i, letter)) {
                return true;
            }
        }
        return false;
    }

    /*
     * Method:
     *     isThreeInARow
     *
     * Purpose:
     *     Check each way to win Tic-Tac-Toe for specified player:
     *         1. Diagonals
     *         2. Rows
     *         3. Columns
     *
     * @param player: Player object to check for win. Used to obtain the player's marker letter
     * @return boolean: true if player won, else false
     */
    public boolean isThreeInARow(Player player) {
        String letter = player.getLetter();
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
     *     isFilled
     *
     * Purpose:
     *     This method is used to aid determination of a game scratch. The method loops through all indexes
     *         in the grid and returns true if there are no instances of "-" empty markers.
     *
     * @param none
     * @return boolean: true if no "-", false if any "-"
     */
    private boolean isFilled() {
        for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j].equals("-")) {
                    return false;
                }
            }
        }
        return true;
    }

    /*
     * Method:
     *     isScratch
     *
     * Purpose:
     *     Check for 'tie' in the board; no player wins.
     *     If the board is filled, no player has won, then it is a scratch.
     *
     * @param players: array of size 2 holding the players of the current Tic-Tac-Toe game.
     * @return boolean: true if scratch, false if not
     */
    public boolean isScratch(Player[] players) {
        if (isFilled()) {
            for (Player player : players) {
                if (isThreeInARow(player)) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /*
     * Method:
     *     rowToString
     *
     * Purpose:
     *     This method converts specified row in the grid to a string to be used to prettily print the current board to the players.
     *
     * @param row: string of size 3 holding the current markers in the specified row of the board.
     * @return sb.toString: string containing the row as a string built by a StringBuilder.
     */
    private String rowToString(String[] row) {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (int i = 0; i < row.length; i++) {
            if (i < (row.length - 1)) {
                sb.append(row[i] + ", ");
            } else {
                sb.append(row[i]);
            }
        }
        sb.append(" ]");
        return sb.toString();
    }

    /*
     * Method:
     *     prettyPrintIndexesAndCurrentBoard
     *
     * Purpose:
     *     Defines a tabular visual to aid the players and outputs the visual to the screen.
     *     Shows the current board markers (left) and the row,col pair indexes for each tile (right)
     *
     *     Ex:
     *      Current Board: 	|	Board Indexes:
     *      --------------- | ---------------------
     *      [ O, X, X ]		|	[ 0,0 | 0,1 | 0,2 ]
     *      [ X, O, O ]		|	[ 1,0 | 1,1 | 1,2 ]
     *      [ X, O, X ]		|	[ 2,0 | 2,1 | 2,2 ]
     *
     * @param none
     * @return none
     */
    public void prettyPrintIndexesAndCurrentBoard() {
        String[] rowOne = grid[0];
        String[] rowTwo = grid[1];
        String[] rowThree = grid[2];

        StringBuilder sb = new StringBuilder();
        sb.append("Current Board: " + "\t|\t" + "Board Indexes:\n");
        sb.append("--------------- | ---------------------\n");
        sb.append(rowToString(rowOne) + "\t\t|\t" + rowOneIndexHelp + "\n");
        sb.append(rowToString(rowTwo) + "\t\t|\t" + rowTwoIndexHelp + "\n");
        sb.append(rowToString(rowThree) + "\t\t|\t" + rowThreeIndexHelp);
        System.out.println(sb.toString());
    }
}