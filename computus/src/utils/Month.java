/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */
package utils;

import java.util.HashMap;

/*
 * Class:
 *     Month (utility class)
 *
 * Purpose:
 *     This class provides a HashMap containing an int to String mapping of months.
 *     Additional arrays are provided for each Month (size is their respective month size: 30, 31, 29) to tally the number of times Easter falls on each date.
 *     Methods are provided to update these arrays.
 *
 * Utilization:
 *     This class is utilized by GregorianEaster and JulianEaster classes for determining the date counts for the 5,700,000-year cycle
 */
public final class Month {
    /* months: HashMap mapping integer of month to string of month. Declared and initialized simultaneously */
    private static final HashMap<Integer, String> months = new HashMap<>() {{
        put(1, "January");
        put(2, "February");
        put(3, "March");
        put(4, "April");
        put(5, "May");
        put(6, "June");
        put(7, "July");
        put(8, "August");
        put(9, "September");
        put(10, "October");
        put(11, "November");
        put(12, "December");
    }};

    /* januaryCounts: array holding 31 elements representing tally of each day */
    private static int[] januaryCounts = new int[31];

    /* februaryCounts: array holding 29 elements representing tally of each day */
    private static int[] februaryCounts = new int[29];

    /* marchCounts: array holding 31 elements representing tally of each day */
    private static int[] marchCounts = new int[31];

    /* aprilCounts: array holding 30 elements representing tally of each day */
    private static int[] aprilCounts = new int[30];

    /* mayCounts: array holding 31 elements representing tally of each day */
    private static int[] mayCounts = new int[31];

    /* juneCounts: array holding 30 elements representing tally of each day */
    private static int[] juneCounts = new int[30];

    /* julyCounts: array holding 31 elements representing tally of each day */
    private static int[] julyCounts = new int[31];

    /* augustCounts: array holding 31 elements representing tally of each day */
    private static int[] augustCounts = new int[31];

    /* septemberCounts: array holding 30 elements representing tally of each day */
    private static int[] septemberCounts = new int[30];

    /* octoberCounts: array holding 31 elements representing tally of each day */
    private static int[] octoberCounts = new int[31];

    /* novemberCounts: array holding 30 elements representing tally of each day */
    private static int[] novemberCounts = new int[30];

    /* decemberCounts: array holding 31 elements representing tally of each day */
    private static int[] decemberCounts = new int[31];

    /*
     * Method:
     *     clearMonthArrays
     *
     * Purpose:
     *     create new arrays with all indexes set to 0, essentially clearing the arrays
     *
     * @param none
     * @return none
     */
    public static void clearMonthArrays() {
        januaryCounts = new int[31];
        februaryCounts = new int[29];
        marchCounts = new int[31];
        aprilCounts = new int[30];
        mayCounts = new int[31];
        juneCounts = new int[30];
        julyCounts = new int[31];
        augustCounts = new int[31];
        septemberCounts = new int[30];
        octoberCounts = new int[31];
        novemberCounts = new int[30];
        decemberCounts = new int[31];
    }

    /*
     * Method:
     *     incrementJanuaryCounts
     *
     * Purpose:
     *     add 1 to index - 1 (date) to update tally of times date has been recorded
     *
     * @param day: integer of day in month
     * @return none
     */
    public static void incrementJanuaryCounts(int day) {
        januaryCounts[day-1] = januaryCounts[day-1] + 1;
    }

    /*
     * Method:
     *     incrementFebruaryCounts
     *
     * Purpose:
     *     add 1 to index - 1 (date) to update tally of times date has been recorded
     *
     * @param day: integer of day in month
     * @return none
     */
    public static void incrementFebruaryCounts(int day) {
        februaryCounts[day-1] = februaryCounts[day-1] + 1;
    }

    /*
     * Method:
     *     incrementMarchCounts
     *
     * Purpose:
     *     add 1 to index - 1 (date) to update tally of times date has been recorded
     *
     * @param day: integer of day in month
     * @return none
     */
    public static void incrementMarchCounts(int day) {
        marchCounts[day-1] = marchCounts[day-1] + 1;
    }

    /*
     * Method:
     *     incrementAprilCounts
     *
     * Purpose:
     *     add 1 to index - 1 (date) to update tally of times date has been recorded
     *
     * @param day: integer of day in month
     * @return none
     */
    public static void incrementAprilCounts(int day) {
        aprilCounts[day-1] = aprilCounts[day-1] + 1;
    }

    /*
     * Method:
     *     incrementMayCounts
     *
     * Purpose:
     *     add 1 to index - 1 (date) to update tally of times date has been recorded
     *
     * @param day: integer of day in month
     * @return none
     */
    public static void incrementMayCounts(int day) {
        mayCounts[day-1] = mayCounts[day-1] + 1;
    }

    /*
     * Method:
     *     incrementJuneCounts
     *
     * Purpose:
     *     add 1 to index - 1 (date) to update tally of times date has been recorded
     *
     * @param day: integer of day in month
     * @return none
     */
    public static void incrementJuneCounts(int day) {
        juneCounts[day-1] = juneCounts[day-1] + 1;
    }

    /*
     * Method:
     *     incrementJulyCounts
     *
     * Purpose:
     *     add 1 to index - 1 (date) to update tally of times date has been recorded
     *
     * @param day: integer of day in month
     * @return none
     */
    public static void incrementJulyCounts(int day) {
        julyCounts[day-1] = julyCounts[day-1] + 1;
    }

    /*
     * Method:
     *     incrementAugustCounts
     *
     * Purpose:
     *     add 1 to index - 1 (date) to update tally of times date has been recorded
     *
     * @param day: integer of day in month
     * @return none
     */
    public static void incrementAugustCounts(int day) {
        augustCounts[day-1] = augustCounts[day-1] + 1;
    }

    /*
     * Method:
     *     incrementSeptemberCounts
     *
     * Purpose:
     *     add 1 to index - 1 (date) to update tally of times date has been recorded
     *
     * @param day: integer of day in month
     * @return none
     */
    public static void incrementSeptemberCounts(int day) {
        septemberCounts[day-1] = septemberCounts[day-1] + 1;
    }

    /*
     * Method:
     *     incrementOctoberCounts
     *
     * Purpose:
     *     add 1 to index - 1 (date) to update tally of times date has been recorded
     *
     * @param day: integer of day in month
     * @return none
     */
    public static void incrementOctoberCounts(int day) {
        octoberCounts[day-1] = octoberCounts[day-1] + 1;
    }

    /*
     * Method:
     *     incrementNovemberCounts
     *
     * Purpose:
     *     add 1 to index - 1 (date) to update tally of times date has been recorded
     *
     * @param day: integer of day in month
     * @return none
     */
    public static void incrementNovemberCounts(int day) {
        novemberCounts[day-1] = novemberCounts[day-1] + 1;
    }

    /*
     * Method:
     *     incrementDecemberCounts
     *
     * Purpose:
     *     add 1 to index - 1 (date) to update tally of times date has been recorded
     *
     * @param day: integer of day in month
     * @return none
     */
    public static void incrementDecemberCounts(int day) {
        decemberCounts[day-1] = decemberCounts[day-1] + 1;
    }

    /*
     * Method:
     *     updateCounts
     *
     * Purpose:
     *     Map passed month and day to correct array, and update the tally
     *
     * @param month: integer of month
     * @param day: integer of day in month
     * @return none
     */
    public static void updateCounts(int month, int day) {
        if (month == 1) {
            incrementJanuaryCounts(day);
        } else if (month == 2) {
            incrementFebruaryCounts(day);
        } else if (month == 3) {
            incrementMarchCounts(day);
        } else if (month == 4) {
            incrementAprilCounts(day);
        } else if (month == 5) {
            incrementMayCounts(day);
        } else if (month == 6) {
            incrementJuneCounts(day);
        } else if (month == 7) {
            incrementJulyCounts(day);
        } else if (month == 8) {
            incrementAugustCounts(day);
        } else if (month == 9) {
            incrementSeptemberCounts(day);
        } else if (month == 10) {
            incrementOctoberCounts(day);
        } else if (month == 11) {
            incrementNovemberCounts(day);
        } else if (month == 12) {
            incrementDecemberCounts(day);
        } else {
            throw new IllegalArgumentException("Invalid month: " + month);
        }
    }

    /*
     * Method:
     *     getCountsAsString
     *
     * Purpose:
     *     Return the counts of each day in selected month
     *
     * @param month: integer wanted month counts
     * @return
     */
    public static String getCountsAsString(int month) {
        String monthStr = months.get(month);
        int[] countArray;
        if (month == 1) {
            countArray = getJanuaryCounts();
        } else if (month == 2) {
            countArray = getFebruaryCounts();
        } else if (month == 3) {
            countArray = getMarchCounts();
        } else if (month == 4) {
            countArray = getAprilCounts();
        } else if (month == 5) {
            countArray = getMayCounts();
        } else if (month == 6) {
            countArray = getJuneCounts();
        } else if (month == 7) {
            countArray = getJulyCounts();
        } else if (month == 8) {
            countArray = getAugustCounts();
        } else if (month == 9) {
            countArray = getSeptemberCounts();
        } else if (month == 10) {
            countArray = getOctoberCounts();
        } else if (month == 11) {
            countArray = getNovemberCounts();
        } else if (month == 12) {
            countArray = getDecemberCounts();
        } else {
            throw new IllegalArgumentException("Invalid month: " + month);
        }

        int day;
        StringBuilder countsString = new StringBuilder();
        for (int i = 0; i < countArray.length; i++) {
            day = i + 1;
            String dateCount = "|__ " + monthStr + " " + day + ": " + countArray[i] + "\n";
            countsString.append(dateCount);
        }
        return countsString.toString();
    }

    // Getters (not documented)
    public static HashMap<Integer, String> getMonths() {
        return months;
    }

    public static int[] getJanuaryCounts() {
        return januaryCounts;
    }

    public static int[] getFebruaryCounts() {
        return februaryCounts;
    }

    public static int[] getMarchCounts() {
        return marchCounts;
    }

    public static int[] getAprilCounts() {
        return aprilCounts;
    }

    public static int[] getMayCounts() {
        return mayCounts;
    }

    public static int[] getJuneCounts() {
        return juneCounts;
    }

    public static int[] getJulyCounts() {
        return julyCounts;
    }

    public static int[] getAugustCounts() {
        return augustCounts;
    }

    public static int[] getSeptemberCounts() {
        return septemberCounts;
    }

    public static int[] getOctoberCounts() {
        return octoberCounts;
    }

    public static int[] getNovemberCounts() {
        return novemberCounts;
    }

    public static int[] getDecemberCounts() {
        return decemberCounts;
    }
}