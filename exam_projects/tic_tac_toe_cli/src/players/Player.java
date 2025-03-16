/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

package players;

/*
 * Class:
 *     Player (Parent)
 *
 * Purpose:
 *     Base model of a Tic-Tac-Toe Player.
 *         A Player object should never be directly created.
 *         Additionally, there should be no more than two players (human or computer) at one time.
 *         I have only coded to allow the following player id strings:
 *              - 'Player1'
 *              - 'Player2'
 *
 *     The Player class provides polymorphism for its two children:
 *         1. ComputerPlayer
 *         2. HumanPlayer
 *
 *     This class provides common attributes amongst HumanPlayer and ComputerPlayer:
 *         - isHuman
 *         - id
 *         - letter
 *
 *     The method makeMove() to be overridden by its children, exhibiting polymorphism at runtime
 *         depending on the players in action.
 */
public abstract class Player {
    /* isHuman: boolean for if the player is human, or not (computer) */
    private boolean isHuman;

    /* id: string for the player name (Player1/Player2) */
    private String id;

    /* letter: string for Tic-Tac-Toe marker ("X"/"O") */
    private String letter;

    /*
     * Constructor:
     *     Player
     *
     * Purpose:
     *     Assign parameters to attributes and determines letter.
     *         Player1 -> X
     *         Player2 -> O
     *         Other -> RuntimeException (other combinations not allowed)
     *
     * @param isHuman: boolean for isHuman
     * @param id: string for player (Player1/Player2)
     * @return none
     * @throws RuntimeException
     */
    public Player(boolean isHuman, String id) {
        this.isHuman = isHuman;
        this.id = id;

        if (id.equals("Player1")) {
            letter = "X";
        } else if (id.equals("Player2")) {
                letter = "O";
        } else {
            throw new RuntimeException("id must be Player1 or Player2");
        }
    }

    /*
     * Method:
     *     makeMove (Parent - abstract)
     *
     * Purpose:
     *     Overridden method by children.
     *
     * @param none
     * @return none
     * @throws InterruptedException
     */
    public abstract int[] makeMove() throws InterruptedException;

    /*
     * Method:
     *     getMakeMoveDirections
     *
     * Purpose:
     *     Returns concatenated string requesting a new move.
     *     ex: "Player1 - Enter a new tile to place marker ('X')"
     *
     * @param none
     * @return none
     */
    public String getMakeMoveDirections() {
        String directions = id + " - Enter a new tile to place marker ('" + letter + "')";
        return directions;
    }

    /*
     * Method:
     *     toString
     *
     * Purpose:
     *     Return string representing the unique attributes of Player
     *     ex: "Player:
     *          isHuman: true
     *          ID: Player1
     *          Letter: 'X'"
     *
     *         "Player:
     *          isHuman: false
     *          ID: Player2
     *          Letter: 'O'"
     *
     * @override Object
     * @param none
     * @return none
     */
    @Override
    public String toString() {
        return "\nPlayer: \nisHuman: " + isHuman + "\nID: " + id + "\nLetter: " + "'" + letter + "'";
    }

    // Getters (not documented)
    public String getID() {
        return id;
    }

    public String getLetter() {
        return letter;
    }

    public boolean getIsHuman() {
        return isHuman;
    }
}