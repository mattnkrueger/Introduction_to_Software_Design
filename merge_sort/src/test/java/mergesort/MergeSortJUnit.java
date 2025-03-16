/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

package test.java.mergesort;

import main.java.mergesort.MergeSortSWD;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MergeSortJUnit {
    /*
     * Method:
     *     testBaseCaseLength0
     *
     * Purpose:
     *     Test if an empty array is passed into sort, an empty array is returned using assertArrayEquals.
     *
     * @test JUnit
     * @param none
     * @return none
     */
    @Test
    public void testBaseCaseLength0() {
        int[] emptyArray = {};
        assertArrayEquals(emptyArray, MergeSortSWD.sort(emptyArray));
    }

    /*
     * Method:
     *     testBaseCaseLength1
     *
     * Purpose:
     *     Test if an array of length one is passed into sort, the same array is returned using assertArrayEquals.
     *
     * @test JUnit
     * @param none
     * @return none
     */
    @Test
    public void testBaseCaseLength1() {
        int[] singleElementArray = {42};
        assertArrayEquals(singleElementArray, MergeSortSWD.sort(singleElementArray));
    }

    /*
     * Method:
     *     testSortedArray
     *
     * Purpose:
     *     Test if an already sorted array is passed into sort, the same array is returned using assertArrayEquals.
     *
     * @test JUnit
     * @param none
     * @return none
     */
    @Test
    public void testSortedArray() {
        int[] sortedArray = {1, 2, 3, 4, 5};
        int[] expectedArray = {1, 2, 3, 4, 5};
        assertArrayEquals(expectedArray, MergeSortSWD.sort(sortedArray));
    }

    /*
     * Method:
     *     testNonSortedArray
     *
     * Purpose:
     *     Test if a non-sorted array is passed into sort, a correctly sorted array is returned using assertArrayEquals.
     *
     * @test JUnit
     * @param none
     * @return none
     */
    @Test
    public void testNonSortedArray() {
        int[] unsortedArray = {4, 2, 5, 6, 1, 10};
        int[] expectedSortedArray = {1, 2, 4, 5, 6, 10};
        assertArrayEquals(expectedSortedArray, MergeSortSWD.sort(unsortedArray));
    }

    /*
     * Method:
     *     testNonSortedWithDuplicatesArray
     *
     * Purpose:
     *     Test if a non-sorted array with duplicates is passed into sort, a correctly sorted array is returned using assertArrayEquals.
     *
     * @test JUnit
     * @param none
     * @return none
     */
    @Test
    public void testNonSortedWithDuplicatesArray() {
        int[] arrayWithDuplicates = {3, 1, 2, 3, 1, 2};
        int[] expectedArrayWithDuplicates = {1, 1, 2, 2, 3, 3};
        assertArrayEquals(expectedArrayWithDuplicates, MergeSortSWD.sort(arrayWithDuplicates));
    }



    /*
     * Method:
     *     testPseudocodeOutput
     *
     * Purpose:
     *     Test output for MergeSort.getPseudocode() string using assertEquals
     *
     * @test JUnit
     * @param none
     * @return none
     */
    @Test
    public void testPseudocodeOutput() {
        String expectedOutput =
                "- Merge Sort Pseudocode -\n" +
                        "1. m = n / 2 # split in half\n" +
                        "\n" +
                        "2. sort a[1..m] # recursive sorts\n" +
                        "3. sort a[m+1..n]\n" +
                        "\n" +
                        "4. b = copy of a[1..m] # merge sorted sub-arrays using temp array\n" +
                        "5. i = 1, j = m+1, k = 1\n" +
                        "6. while i <= m and j <= n,\n" +
                        "7.     a[k++] = (a[j] < b[i]) ? a[j++] : b[i++]\n" +
                        "8.     → invariant: a[1..k] in final position\n" +
                        "9. while i <= m,\n" +
                        "10.    a[k++] = b[i++]\n" +
                        "11.    → invariant: a[1..k] in final position";

        assertEquals(expectedOutput, MergeSortSWD.getPseudocode());
    }
}
