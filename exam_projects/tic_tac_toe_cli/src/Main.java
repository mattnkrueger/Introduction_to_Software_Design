/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

import players.*;

import utils.Game;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * Class:
 *     Main (entry point)
 *
 * Purpose:
 *     This class defines the main game loop for the TicTacToe program.
 *
 *     This class defines methods for safely obtaining user actions for game selection.
 *     The class also defines the code blocks to be executed for each game selection inside its runnable method code.
 *
 *     For each user action, a set of Player objects are created and passed into a new Game. The new
 *         Game then executes fully before returning to the main loop.
 *
 *     The Main class defines attributes:
 *         - scanner: Scanner to get user input
 *         - isRunning: flag for program completion
 */
public class Main {
    /* scanner: Scanner object to get user input for game selections; System. in */
    private static final Scanner scanner = new Scanner(System.in);

    /* isRunning: boolean flag for is the program is running */
    private static boolean isRunning = true;

    /*
     * Method:
     *     getUserAction
     *
     * Purpose:
     *     Continually request for a valid integer action from the user and return the integer to map the user action to new game.
     *     If the integer is invalid, the user is asked again for an integer.
     *
     *     Actions:
     *          1. Multi Player (Play against a friend)
     *          2. Single Player (Play against a very dull computer player)
     *          3. Spectator (Watch two very dull computer players duke it out)
     *          4. EXIT PROGRAM
     */
    private static int getUserAction() {
        int actionChosen = 0;
        boolean validAction = false;

        while (!validAction) {
            System.out.println("\nChoose an Game Mode:\n   1. Multi Player (Play against a friend)\n   2. Single Player (Play against a very dull computer player)\n   3. Spectator (Watch two very dull computer players duke it out)\n   4. EXIT PROGRAM\n");
            try {
                actionChosen = scanner.nextInt();
                validAction = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: action must be an Integer.\nEnter a new value:");
                scanner.nextLine();
            }
        }
        return actionChosen;
    }

    /*
     * Method:
     *     main
     *
     * Purpose:
     *     This runnable method defines the main loop for the TicTacToe program.
     *
     *     Main Loop:
     *         1. request user action
     *         2. map user action to code block
     *         - if userAction != 4
     *             3. create new players
     *             4. create new game
     *             5. execute game code
     *         - if userAction = 4:
     *             Exit Program
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Welcome to mnkrueger's S20_TicTacToe_Hard_CLI!");

        int action;
        while (getIsRunning()) {
            action = getUserAction();
            if (action == 1) { // multi-player
                HumanPlayer p1 = new HumanPlayer("Player1");
                HumanPlayer p2 = new HumanPlayer("Player2");
                Game game = new Game(p1, p2);
                game.playGame();
            } else if (action == 2) { // single player
                HumanPlayer p1 = new HumanPlayer("Player1");
                ComputerPlayer p2 = new ComputerPlayer("Player2");
                Game game = new Game(p1, p2);
                game.playGame();
            } else if (action == 3) { // spectator
                ComputerPlayer p1 = new ComputerPlayer("Player1");
                ComputerPlayer p2 = new ComputerPlayer("Player2");
                Game game = new Game(p1, p2);
                game.playGame();
            } else if (action == 4) { // end game
                setRunning(false);
            } else {
                System.out.println("\nInvalid action chosen; Read Directions Carefully.");
            }
        }
    }

    // Setters (not documented)
    private static void setRunning(boolean flag) {
        isRunning = flag;
    }

    // Getters (not documented)
    private static boolean getIsRunning() {
        return isRunning;
    }
}