/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

package easter;

/*
 * Class:
 *     GregorianEaster
 *
 * Purpose:
 *     Calculate the Gregorian Easter for passed year.
 *     Formula (https://en.wikipedia.org/wiki/Date_of_Easter) linked in SWD.
 *     toString() holds the wanted Easter Date.
 *
 * Extends:
 *     Easter
 *
 * Note:
 *     I assume we are to model the formula in the Wikipedia article linked in class
 *     I have done the research to add descriptions to the abstracted variables (a,b,c, etc).
 *     I did not come to the algorithm on my own, as that would be insanely difficult.
 */
public class GregorianEaster extends Easter {
    // Note:
    // The following attributes are mirroring the formula applied in the linked Wikipedia article
    // Again, I am assuming that we are to model the formula provided in the article.
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;

    /*
     * Constructor:
     *     GregorianEaster (non-parameter constructor)
     *
     * Purpose:
     *     call the monadic constructor with the defaulted year of '0'
     *
     * @param none
     * @return none
     */
    public GregorianEaster() {
        this(0);
    }

    /*
     * Constructor:
     *     GregorianEaster (monadic constructor)
     *
     * @param year: integer year value for wanted Easter
     * @return none
     */
    public GregorianEaster(int year) {
        super(year);
    }

    /*
     * Method:
     *     calculateEaster
     *
     * Purpose:
     *     Set all attributes in order of the Meeus/Jones/Butcher Algorithm
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
        setF();
        setG();
        setH();
        setI();
        setK();
        setL();
        setM();
        setN();
        setO();

        setMonthInt(n);
        setMonthStr(n);
        setDay(o+1);
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
        this.a = this.getYear() % 19;
    }

    /*
     * Method:
     *     setB
     *
     * @param none
     * @return none
     */
    private void setB() {
        this.b = this.getYear() / 100;
    }

    /*
     * Method:
     *     setC
     *
     * @param none
     * @return none
     */
    private void setC() {
        this.c = this.getYear() % 100;
    }

    /*
     * Method:
     *     setD
     *
     * @param none
     * @return none
     */
    private void setD() {
        this.d = b / 4;
    }

    /*
     * Method:
     *     setE
     *
     * @param none
     * @return none
     */
    private void setE() {
        this.e = b % 4;
    }

    /*
     * Method:
     *     setF
     *
     * @param none
     * @return none
     */
    private void setF() {
        this.f = (b + 8) / 25;
    }

    /*
     * Method:
     *     setG
     *
     * @param none
     * @return none
     */
    private void setG() {
        this.g = (b - f + 1) / 3;
    }

    /*
     * Method:
     *     setH
     *
     * @param none
     * @return none
     */
    private void setH() {
        this.h = ((19*a) + b - d - g + 15) % 30;
    }

    /*
     * Method:
     *     setI
     *
     * @param none
     * @return none
     */
    private void setI() {
        this.i = c / 4;
    }

    /*
     * Method:
     *     setK
     *
     * @param none
     * @return none
     */
    private void setK() {
        this.k = c % 4;
    }

    /*
     * Method:
     *     setL
     *
     * @param none
     * @return none
     */
    private void setL() {
        this.l = (32 + (2*e) + (2*i) - h - k) % 7;
    }

    /*
     * Method:
     *     setM
     *
     * @param none
     * @return none
     */
    private void setM() {
        this.m = (a + (11*h) + (22*l)) / 451;
    }

    /*
     * Method:
     *     setN
     *
     * @param none
     * @return none
     */
    private void setN() {
        this.n = (h + l - (7*m) + 114) / 31;
    }

    /*
     * Method:
     *     setO
     *
     * Purpose:
     *     calculate & set Easter Day
     *
     * @param none
     * @return none
     */
    private void setO() {
        this.o = (h + l - (7*m) + 114) % 31;
    }

    /*
     * Method:
     *     toString
     *
     * Purpose:
     *     Append the Gregorian Easter date to the base Easter toString and return the string.
     *     This string is a formated Easter date for the Gregorian Easter
     *
     * @override Easter
     * @param none
     * @return "Gregorian " + easterString: formatted Easter string
     */
    @Override
    public String toString() {
        String easterString = super.toString();
        return "Gregorian " + easterString;
    }

    // Getters (not documented)
    public int getA() {
        return this.a;
    }

    public int getB() {
        return this.b;
    }

    public int getC() {
        return this.c;
    }

    public int getD() {
        return this.d;
    }

    public int getE() {
        return this.e;
    }

    public int getF() {
        return this.f;
    }

    public int getG() {
        return this.g;
    }

    public int getH() {
        return this.h;
    }

    public int getI() {
        return this.i;
    }

    public int getK() {
        return this.k;
    }

    public int getL() {
        return this.l;
    }

    public int getM() {
        return this.m;
    }

    public int getN() {
        return this.n;
    }

    public int getO() {
        return this.o;
    }
}