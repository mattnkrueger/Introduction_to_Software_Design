/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

package test;

import easter.JulianEaster;
import utils.Month;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/*
 * Class:
 *     JulianEasterJUnitTest
 *
 * Purpose:
 *     JUnit Testing for JulianEaster Class
 *
 * Tests:
 *     1. testInitialization(): tests non-null value of object upon instantiation.
 *     2. testEasterDate(): tests correct Easter date output for specified year.
 *     3. testInvalidYear(): tests ArithmeticError for invalid year.
 *     4. testCycleCounts(): tests correct date counts for particular Easter dates in March and April.
 */
public class JulianEasterJUnitTest {
    /*
     * Method:
     *     testInitialization
     *
     * Purpose:
     *     Tests non-null value of object upon instantiation.
     *     Creates new JulianEaster object with valid year and assertsNotNull.
     *
     * @test JUnit Test
     * @param none
     * @return none
     */
    @Test
    void testInitialization() {
        JulianEaster julianEaster = new JulianEaster(2024);
        assertNotNull(julianEaster);
    }

    /*
     * Method:
     *     testEasterDate
     *
     * Purpose:
     *     Tests correct Easter date output for specified year.
     *     Creates new JulianEaster object with valid year and assertsEqual for outputting JulianEaster.toString().
     *
     * @test JUnit Test
     * @param none
     * @return none
     */
    @Test
    public void testEasterDate() {
        JulianEaster julianEaster = new JulianEaster(2024);
        julianEaster.calculateEaster();
        assertEquals("Julian Easter for Year 2024: April 22\n", julianEaster.toString());
    }

    /*
     * Method:
     *     testInvalidYear
     *
     * Purpose:
     *     Tests ArithmeticError for invalid year.
     *     Creates new JulianEaster object with invalid year and assertThrows for ArithmeticException.
     *
     * @test JUnit Test
     * @param none
     * @return none
     */
    @Test
    public void testInvalidYear() {
        assertThrows(ArithmeticException.class, () -> {
            JulianEaster je = new JulianEaster(-1000);
        });
    }

    /*
     * Method:
     *     testCycleCounts
     *
     * Purpose:
     *     Tests correct date counts for particular Easter dates in March and April.
     *     Creates 5,700,000 (1-5,700,000) new JulianEaster objects, updates
     *     Month utility class arrays, and assertEquals for expected value and index
     *     values for particular Easter dates in March and April.
     *
     * @test JUnit Test
     * @param none
     * @return none
     */
    @Test
    public void testCycleCounts() {
        for (int i = 1; i < 532; i++) {
            JulianEaster newJulianEaster = new JulianEaster(i);
            newJulianEaster.calculateEaster();
            Month.updateCounts(newJulianEaster.getMonthInt(), newJulianEaster.getDay());
        }
        assertEquals(0, Month.getMarchCounts()[19]);
        assertEquals(20, Month.getMarchCounts()[30]);
        assertEquals(16, Month.getAprilCounts()[19]);
        assertEquals(0, Month.getAprilCounts()[29]);
    }
}
