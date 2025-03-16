/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

import main.java.mergesort.MergeSortSWD;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

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
public class Main {
    /* "this is an attribute" */
    private static final Scanner scanner = new Scanner(System.in);

    /* "this is an attribute" */
    private static boolean running = true;

    /* "this is an attribute" */
    private static void setRunning(boolean flag) {
        running = flag;
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
    private static int[] getValues() {
        String toPrint;
        ArrayList<Integer> wantedValues = new ArrayList<>();
        boolean running = true;
        int integerChosen;
        String quitChosen;

        System.out.println("Enter Integers to be Sorted\nPress 'q' to finalize array");
        while (running) {
            try {
                if (scanner.hasNextInt()) {
                    toPrint = "Current Array: ";
                    integerChosen = scanner.nextInt();
                    wantedValues.add(integerChosen);

               } else if (scanner.hasNext()) {
                    if (scanner.next().equals("q")) {
                        toPrint = "Array to Sort: ";
                        running = false;
                    }
                    else {
                        toPrint = "Invalid Selection Press\nPress 'q' to finalize array\nCurrent Array: ";
                    }
               } else {
                    toPrint = "Error: ";
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println(toPrint + wantedValues);
        }

        int size = wantedValues.size();
        int[] values = new int[size];

        for (int i = 0; i < size; i++) {
            values[i] = wantedValues.get(i);
        }
        return values;
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
    private static boolean isRunning() {
        return running;
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
    private static int getUserAction() {
        int actionChosen = 0;
        boolean validAction = false;

        while (!validAction) {
            System.out.println("Choose an Action:\n   1. Enter Values Into an Array to Sort with Merge Sort\n   2. View Pseudocode Algorithm\n   3. EXIT PROGRAM\n");
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
     *
     *
     * Purpose:
     *
     *
     * @param
     * @return
     */
    public static void main(String[] args) {
        int action;
        String toPrint;
        while (isRunning()) {
            action = getUserAction();
            if (action == 1) {
                toPrint = "";
                int[] values = getValues();
                System.out.println("\n-- Merge Sort --");
                System.out.println("Unsorted Array: " + Arrays.toString(values));
                System.out.println("Sorted Array: " + Arrays.toString(MergeSortSWD.sort(values)));
            } else if (action == 2) {
                toPrint = "";
                System.out.println(MergeSortSWD.getPseudocode());
            } else if (action == 3) {
                toPrint = "Ending Program...";
                setRunning(false);
            } else {
                toPrint = "\n--- ACTION TAKEN: INVALID ---\nInvalid action chosen; Read Directions Carefully.";
            }
            System.out.println(toPrint);
        }
    }
}