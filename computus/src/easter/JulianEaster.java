/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

package easter;

/*
 * Class:
 *     JulianEaster
 *
 * Purpose:
 *     Calculate the Julian Easter for passed year.
 *     Formula (https://en.wikipedia.org/wiki/Date_of_Easter) linked in SWD.
 *     toString() holds the wanted Easter Date.
 *
 * Extends:
 *     Easter
 *
 * Note:
 *     I assume we are to model the formula in the Wikipedia article linked in class
 *     I did not come to the algorithm on my own, as that would be insanely difficult.
 */
public class JulianEaster extends Easter {
    // Note:
    // The following attributes are mirroring the formula applied in the linked Wikipedia article
    // Again, I am assuming that we are to model the formula provided in the article.
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int m;
    private int n;

    /*
     * Constructor:
     *     JulianEaster (non-parameter constructor)
     *
     * Purpose:
     *     call the monadic constructor with the defaulted year of '0'
     *
     * @param none
     * @return none
     */
    public JulianEaster() {
        this(0);
    }

    /*
     * Constructor:
     *     JulianEaster (monadic constructor)
     *
     * Purpose:
     *     Set the year attribute in the parent Easter class.
     *
     * @param year: integer year value for wanted Easter
     * @return none
     */
    public JulianEaster(int year) {
        super(year);
    }

    /*
     * Method:
     *     calculateEaster
     *
     * Purpose:
     *     Set all attributes in order of the Meeus' Julian Algorithm
     *     Additionally, the parent Easter class is updated to ensure toString returns wanted date.
     *
     * @param none
     * @return none
     */
    public void calculateEaster() {
        setA();
        setB();
        setC();
        setD();
        setE();
        setM();
        setN();

        setMonthInt(m);
        setMonthStr(m);
        setDay(n);
        setEasterDate();
    }

    /*
     * Method:
     *     setA
     *
     * @param none
     * @return none
     */
    private void setA() {
        this.a = super.getYear() % 4;
    }

    /*
     * Method:
     *     setB
     *
     * @param none
     * @return none
     */
    private void setB() {
        this.b = super.getYear() % 7;
    }

    /*
     * Method:
     *     setC
     *
     * @param none
     * @return none
     */
    private void setC() {
        this.c = super.getYear() % 19;
    }

    /*
     * Method:
     *     setD
     *
     * @param none
     * @return none
     */
    private void setD() {
        this.d = (((19*c) + 15) % 30);
    }

    /*
     * Method:
     *     setE
     *
     * @param none
     * @return none
     */
    private void setE() {
        this.e = ((2*a) + (4*b) - d + 34) % 7;
    }

    /*
     * Method:
     *     setM
     *
     * @param none
     * @return none
     */
    private void setM() {
        this.m = (d + e + 114) / 31;
    }

    /*
     * Method:
     *     setN
     *
     * @param none
     * @return none
     */
    private void setN() {
        this.n = ((d + e + 114) % 31) + 1;
    }

    /*
     * Method:
     *     toString
     *
     * Purpose:
     *     Append the Julian Easter date to the base Easter toString and return the string.
     *     This string is a formated Easter date for the Julian Easter
     *
     * @override Easter
     * @param none
     * @return "Julian " + easterString: formatted Easter date string
     */
    @Override
    public String toString() {
        String easterString = super.toString();
        return "Julian " + easterString;
    }

    // Getters (not documented)
    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getC() {
        return c;
    }

    public int getD() {
        return d;
    }

    public int getE() {
        return e;
    }

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }
}
