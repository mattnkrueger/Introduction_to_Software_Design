/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

package easter;

import utils.Month;

/*
 * Class:
 *     Easter (Parent)
 *
 * Purpose:
 *     Parent class for Gregorian and Julian Easter Classes.
 *     Provides base attributes for each Easter Class:
 *         - int year
 *         - int monthInt
 *         - String monthStr
 *         - int day
 *         - String easterDate
 *     Provides base methods for each Easter Class:
 *         - setters & getters
 *         - toString
 */
public class Easter {
    /* year: year of wanted Easter date */
    private int year;

    /* monthInt: integer (1-12) for month of wanted Easter date */
    private int monthInt;

    /* monthStr: string (January, February, ...) for month of wanted Easter date */
    private String monthStr;

    /* day: integer for day of wanted Easter date */
    private int day;

    /* easterDate: string for wanted Easter Date */
    private String easterDate;

    /*
     * Constructor:
     *     Easter
     *
     * Purpose:
     *     Ensure that the year is a valid year, and set attributes if valid. Else, end the program by ArithmeticException
     *
     * @param year: integer year passed into Easter instance
     * @return none
     */
    public Easter(int year) {
        if (year < 0) {
            throw new ArithmeticException("Year cannot be negative");
        }
        this.year = year;
    }

    /*
     * Method:
     *     setMonthStr
     *
     * Purpose:
     *     Set the month String by mapping the month integer inside the Month object's HashMap
     *
     * @param monthInt: integer representing the month of wanted easter
     * @return none
     */
    public void setMonthStr(int monthInt) {
        this.monthStr = Month.getMonths().get(monthInt);
    }


    /*
     * Method:
     *     setEasterDate
     *
     * Purpose:
     *     Concatenate string with monthStr and day.
     *     Ex: "March 3"
     *
     * @param none
     * @return none
     */
    public void setEasterDate() {
        this.easterDate = monthStr + " " + day;
    }

    /*
     * Method:
     *     toString
     *
     * Purpose:
     *     Return a string containing the Easter Date of the Easter object for the passed year.
     *
     * @overrides Object
     * @param none
     * @return String representation of the Easter Object.
     */
    @Override
    public String toString() {
        String header;
        String format;
        String body;

        format = "Easter for Year %d: ";
        header = String.format(format, year);
        body = easterDate;

        return header + body + "\n";
    }

    // Basic Setters (not documented)
    public void setDay(int day) {
        this.day = day;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonthInt(int monthInt) {
        this.monthInt = monthInt;
    }

    // Getters (not documented)
    public int getYear() {
        return year;

    }

    public String getEasterDate() {
        return easterDate;
    }

    public int getDay() {
        return day;
    }

    public int getMonthInt() {
        return monthInt;
    }

    public String getMonthStr() {
        return monthStr;
    }
}
