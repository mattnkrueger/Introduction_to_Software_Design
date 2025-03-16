/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

package players;

import java.util.Scanner;

/*
 * Class:
 *     HumanPlayer (Child)
 *
 * Extends:
 *     Player (Parent)
 *
 * Purpose:
 *     Human model of a Tic-Tac-Toe Player.
 *     This class utilizes system input to obtain moves from human player.
 *     The makeMove (overridden from Player) handles the system input and selection of tile
 *         at runtime and ensures that a valid selection of row,col has been chosen.
 *
 *     This class extends attributes of base Player class:
 *         - scanner: Scanner object for i/o to obtain human input
 *
 *     The method makeMove() to be overridden, exhibiting polymorphism at runtime
 */
public class HumanPlayer extends Player {
    /* scanner: Scanner object to interact with human; set to System.in */
    Scanner scanner;

    /*
     * Constructor:
     *     HumanPlayer
     *
     * Purpose:
     *     Set attributes of super (Player) class:
     *         - id -> id
     *         - isHuman -> true
     *     Set attribute scanner:
     *         - new Scanner(System.in)
     *
     * @param id: player id string
     * @return none
     */
    public HumanPlayer(String id) {
        super(true, id);
        scanner = new Scanner(System.in);
    }

    /*
     * Method:
     *     makeMove (HumanPlayer)
     *
     * Purpose:
     *     Overridden method to acquire user selected row,col tile selection from human player during the game loop.
     *
     *     This method continually quires the human player using Scanner object for a new tile row,col selection, until a valid selection is made.
     *     Once a valid selection is made, it will return the [row,col] pair as an array.
     *
     *     A valid selection is constrained by the size of the Tic-Tac-Toe board: 3x3.
     *     Because arrays are 0-indexed, player guess must be 0 <= r < 3, and 0 <= c < 3.
     *     ex: [2,1]
     *
     * @override Player
     * @param none
     * @return tile: integer array of length 2. Holds row, column selection information.
     */
    @Override
    public int[] makeMove() {
        boolean validAction = false;
        int row;
        int col;
        int[] tile = new int[2];

        System.out.println(super.getMakeMoveDirections());
        while (!validAction) {
            try {
                String tileString = scanner.nextLine();
                String[] indexes = tileString.split(",");
                String rowStr = indexes[0].strip();
                String colStr = indexes[1].strip();
                row = Integer.parseInt(rowStr);
                col = Integer.parseInt(colStr);

                if (row < 0 || row > 2 || col < 0 || col > 2) {
                    throw new Exception();
                } else {
                    tile = new int[]{row, col};
                    validAction = true;
                }
            } catch (Exception e) {
                System.out.println("Invalid tile entered; Try again.");
            }
        }
        return tile;
    }
}