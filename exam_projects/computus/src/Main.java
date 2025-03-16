/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

import easter.GregorianEaster;
import easter.JulianEaster;
import utils.Month;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * Class:
 *     Main (entry point)
 *
 * Purpose:
 *     This class is the entry point for the Computus Program.
 *     It defines a game loop to handle user interaction, providing various options for actions the user can take.
 *     The user can view Easter dates for specific year, or Easter dates in 5,700,000-year cycle.
 */
public class Main {
    /* scanner: Scanner instance for user interaction; console input */
    private static final Scanner scanner = new Scanner(System.in);

    /* running: boolean for game loop completion */
    private static boolean running = true;

    /*
     * Method:
     *     getUserAction
     *
     * Purpose:
     *     Handle user interaction with console.
     *     Prints out formatted menu requesting user action.
     *     User action should be an integer, if a valid action is chosen,
     *     the integer action is returned to be handled in the game loop.
     *
     * @param none
     * @return int actionChosen: action integer
     */
    private static int getUserAction() {
        int actionChosen = 0;
        boolean validAction = false;

        while (!validAction) {
            System.out.println("Choose an Action:\n   1. Compute Gregorian Easter for Specified Date\n   2. Compute Julian Easter for Specified Date\n   3. EXIT PROGRAM\n");
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
     *     getGregorianCountsOutput
     *
     * Purpose:
     *     Return the Easter dates for March and April for GregorianEaster
     *
     * @param none
     * @return String "March Dates:\n" + Month.getCountsAsString(3) + "\nApril Dates:\n" + Month.getCountsAsString(4): formated Easter Dates
     */
    private static String getGregorianCountsOutput() {
        return "March Dates:\n" + Month.getCountsAsString(3) + "\nApril Dates:\n" + Month.getCountsAsString(4);
    }

    /*
     * Method:
     *     getJulianCountOutputs
     *
     * Purpose:
     *     Return the Easter dates for March and April for JulianEaster
     *
     * @param none
     * @return String "March Dates:\n" + Month.getCountsAsString(3) + "\nApril Dates:\n" + Month.getCountsAsString(4): formated Easter Dates
     */
    private static String getJulianCountsOutput() {
        return "March Dates:\n" + Month.getCountsAsString(3) + "\nApril Dates:\n" + Month.getCountsAsString(4);
    }

    /*
     * Method:
     *     getYearSelected
     *
     * Purpose:
     *     Obtain user integer for year selected.
     *     Year must be an integer, once valid integer chosen, it is returned
     *     to be handled inside of main game loop.
     *
     * @param none
     * @return int yearSelected: wanted year of user
     */
    private static int getYearSelected() {
        int yearSelected = 0;
        boolean validAction = false;
        while (!validAction) {
            System.out.println("Enter a Year: ");
            try {
                yearSelected = scanner.nextInt();
                validAction = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: Year Must be an Integer.");
                scanner.nextLine();
            }
        }
        return yearSelected;
    }

    /*
     * Method:
     *     main
     *
     * Purpose:
     *     Start and handle game loop for user interaction with Computus program.
     *     Map the user actions to respective processes.
     *     Print out formatted output.
     *
     * @param String[] args: values selected by user during execution (command line)
     * @return none
     */
    public static void main(String[] args) {
        int action;
        String toPrint;
        int yearSelected;
        while (isRunning()) {
            action = getUserAction();
            if (action == 1) {
                GregorianEaster gregorianEaster = new GregorianEaster(getYearSelected());
                gregorianEaster.calculateEaster();
                toPrint = gregorianEaster.toString();
            } else if (action == 2) {
                JulianEaster julianEaster = new JulianEaster(getYearSelected());
                julianEaster.calculateEaster();
                toPrint = julianEaster.toString();
            } else if (action == 3) {
                setRunning(false);
                toPrint = "Ending Program...";
            } else {
                toPrint = "Invalid action chosen; Read Directions Carefully.";
            }
            System.out.println(toPrint);
        }
    }

    // Setters (not documented)
    private static void setRunning(boolean flag) {
        running = flag;
    }

    // Getters (not documented)
    private static boolean isRunning() {
        return running;
    }
}