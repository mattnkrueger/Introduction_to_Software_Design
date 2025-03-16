/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

package test;

import easter.GregorianEaster;
import utils.Month;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/*
 * Class:
 *     GregorianEasterJUnitTest
 *
 * Purpose:
 *     JUnit Testing for GregorianEaster Class
 *
 * Tests:
 *     1. testInitialization(): tests non-null value of object upon instantiation.
 *     2. testEasterDate(): tests correct Easter date output for specified year.
 *     3. testInvalidYear(): tests ArithmeticError for invalid year.
 *     4. testCycleCounts(): tests correct date counts for particular Easter dates in March and April.
 */
public class GregorianEasterJUnitTest {
    /*
     * Method:
     *     testInitialization
     *
     * Purpose:
     *     Tests non-null value of object upon instantiation.
     *     Creates new GregorianEaster object with valid year and assertsNotNull.
     *
     * @test JUnit Test
     * @param none
     * @return none
     */
    @Test
    void testInitialization() {
        GregorianEaster gregorianEaster = new GregorianEaster(2024);
        assertNotNull(gregorianEaster);
    }

    /*
     * Method:
     *     testEasterDate
     *
     * Purpose:
     *     Tests correct Easter date output for specified year.
     *     Creates new GregorianEaster object with valid year and assertsEqual for outputting GregorianEaster.toString().
     *
     * @test JUnit Test
     * @param none
     * @return none
     */
    @Test
    public void testEasterDate() {
        GregorianEaster gregorianEaster = new GregorianEaster(2024);
        gregorianEaster.calculateEaster();
        System.out.println(gregorianEaster.toString());
        assertEquals("Gregorian Easter for Year 2024: March 31\n", gregorianEaster.toString());
    }

    /*
     * Method:
     *     testInvalidYear
     *
     * Purpose:
     *     Tests ArithmeticError for invalid year.
     *     Creates new GregorianEaster object with invalid year and assertThrows for ArithmeticException.
     *
     * @test JUnit Test
     * @param none
     * @return none
     */
    @Test
    public void testInvalidYear() {
        assertThrows(ArithmeticException.class, () -> {
            GregorianEaster ge = new GregorianEaster(-1000);
        });
    }

    /*
     * Method:
     *     testCycleCounts
     *
     * Purpose:
     *     Tests correct date counts for particular Easter dates in March and April.
     *     Creates 5,700,000 (1-5,700,000) new GregorianEaster objects, updates
     *     Month utility class arrays, and assertEquals for expected value and index
     *     values for particular Easter dates in March and April.
     *
     * @test JUnit Test
     * @param none
     * @return none
     */
    @Test
    public void testCycleCounts() {
        for (int i = 1; i < 5700001; i++) {
            GregorianEaster newGregorianEaster = new GregorianEaster(i);
            newGregorianEaster.calculateEaster();
            Month.updateCounts(newGregorianEaster.getMonthInt(), newGregorianEaster.getDay());
        }
        assertEquals(0, Month.getMarchCounts()[19]);
        assertEquals(189525, Month.getMarchCounts()[30]);
        assertEquals(189525, Month.getAprilCounts()[19]);
        assertEquals(0, Month.getAprilCounts()[29]);
    }
}
