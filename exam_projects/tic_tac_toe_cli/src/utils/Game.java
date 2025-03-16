/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

package utils;

import players.Player;

/*
 * Class:
 *     Game
 *
 * Purpose:
 *     Define the game logic of Tic-Tac-Toe.
 *
 *     This class utilizes runtime polymorphism (per SWD directions) to call HumanPlayer and
 *         ComputerPlayer code under the mask of their super class Player to hide the implementation
 *         details of each.
 *
 *     This class defines attributes specific to a game of Tic-Tac-Toe:
 *         - players
 *         - board
 *         - inProgress
 *         - gameResult
 *
 *     This class has a single method:
 *         - playGame
 *
 *     Due to the polymorphism, the playGame() method will treat each game mode (single player, multi player, spectator)
 *         as equal under the same code.
 *
 *     Game code is called from the main game loop and does not return to the loop until the game is completed.
 *
 */
public class Game {
    /* players: array of size 2 holding players in the current game */
    private Player[] players;

    /* board: Board object for 'this' game instance */
    private Board board;

    /* inProgress: boolean flag for if the game is still being played */
    private boolean inProgress;

    /* gameResult: string representing the output of the game ("Player1 Win"/"Player2 Win"/"Scratch") */
    private String gameResult;

    /*
     * Constructor:
     *     Game
     *
     * Purpose:
     *     Creates a new game with the two Players passed into the Game initialization.
     *
     *     Creates a fresh board to play the game and set inProgress to true.
     *
     *     Set attributes:
     *         - players -> [player1, player2]
     *         - board -> new Board()
     *         - inProgress -> true
     *
     * @param player1: Player object representing player 1
     * @param player2: Player object representing player 2
     * @return none
     */
    public Game(Player player1, Player player2) {
        players = new Player[2];
        players[0] = player1;
        players[1] = player2;
        board = new Board();

        inProgress = true;
    }

    /*
     * Method:
     *     playGame
     *
     * Purpose:
     *     This method handles the game logic for Tic-Tac-Toe, utilizing Player polymorphism to obtain tile selections, and Board to check for winners.
     *
     *     Game Loop:
     *         1. check for winner (board.isThreeInARow(player) for both players)
     *         2. print current board (board.prettyPrintIndexesAndCurrentBoard())
     *         3. obtain both player's moves (makeMove() for both players
     *
     *     Once the game is over, the board will be printed again along with the game result.
     *
     * @param
     * @return
     */
    public void playGame() throws InterruptedException {
        while (inProgress) {
            // polymorphic game logic
            for (Player player : players) {
                // check if game is over
                if (board.isThreeInARow(player)) {
                    gameResult = player.getID() + " Won!";
                    inProgress = false;
                    break; // exit for loop
                } else if (board.isScratch(players)) {
                    gameResult = "Scratch";
                    inProgress = false;
                    break; // exit for loop
                }

                // print current board
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); // very long escape to simulate pages
                board.prettyPrintIndexesAndCurrentBoard();

                // obtain player move & ensure valid
                boolean moveMade = false;
                while (!(moveMade))  {
                    // obtain tile for new move
                    int[] tile;
                    tile = player.makeMove();

                    // try to add to grid
                    if (board.attemptToUpdateGrid(tile, player)) {
                        System.out.println(player.getID() + " marked tile: " + tile[0] + "," + tile[1] + " as " + player.getLetter());
                        moveMade = true;
                    } else {
                        System.out.println("Invalid Move");
                    }
                }
            }
        }
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); // very long escape to simulate pages
        System.out.println("Game Over!\nResult: " + gameResult);
        board.prettyPrintIndexesAndCurrentBoard();
        System.out.println("Play Again?");
    }
}
