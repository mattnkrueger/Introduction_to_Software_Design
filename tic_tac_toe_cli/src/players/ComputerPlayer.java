/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

package players;

import java.util.Random;

/*
 * Class:
 *     ComputerPlayer (Child)
 *
 * Extends:
 *     Player (Parent)
 *
 * Purpose:
 *     Computer model of a Tic-Tac-Toe Player.
 *     This class utilizes system input to obtain moves from a Non-Player Computer (NPC) player.
 *     The makeMove (overridden from Player) handles the random selection of a tile in the 3x3 grid by the NPC
 *         at runtime and ensures that a valid selection of row,col has been chosen.
 *
 *     This class extends attributes of base Player class:
 *         - random: Random object to obtain random row, col selection
 *
 *     The method makeMove() to be overridden, exhibiting polymorphism at runtime
 */
public class ComputerPlayer extends Player {
    /* random: Random object to obtain random row, col selection */
    Random random;

    /*
     * Constructor:
     *     ComputerPlayer
     *
     * Purpose:
     *     Set attributes of super (Player) class:
     *         - id -> id
     *         - isHuman -> false
     *     Set attribute random:
     *         - new Random()
     *
     * @param id: player id string
     * @return none
     */
    public ComputerPlayer(String id) {
        super(false, id);
        random = new Random();
    }

    /*
     * Method:
     *     makeMove (ComputerPlayer)
     *
     * Purpose:
     *     Overridden method to acquire a random row,col tile selection from computer player during the game loop.
     *
     *     This method continually quires the computer player for a new tile row,col selection, made using Random object, until a valid selection is made.
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
    public int[] makeMove() throws InterruptedException {
        System.out.println(super.getMakeMoveDirections());
        System.out.println(getID() + " (computer) is thinking...");
        Thread.sleep(1000);

        int row = random.nextInt(3);
        int col = random.nextInt(3);
        int[] tile = {row, col};
        return tile;
    }
}