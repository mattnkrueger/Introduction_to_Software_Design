/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

package main.java.mergesort;

import java.util.Arrays;

/*
 * Class:
 *     MergeSort (utility)
 *
 * Purpose:
 *     This class provides methods to sort an array of integers in O(n*log(n)) time.
 *
 * Algorithm in English:
 *     Important Note:
 *         I have defined the steps for recursion to execute the left splitting before right splitting.
 *
 *             sort(leftSubArray);
 *             sort(rightSubArray);
 *
 *         Thus, the stack trace will show recursive splitting and sorting for the first left split and then the first right split.
 *
 *     Recursive Division Steps:
 *         Keep in mind left before right as defined in my code.
 *         1. Take original array.
 *         2. Divide array in half, creating left and right sub arrays.
 *         4. Once the length of a sub array is 1, begin to build the array (recursion complete, and bottom-most sub array reached).
 *     Rebuild Sorted Array Steps:
 *         5. Compare each index in bottom most sub arrays.
 *         6. To do this, compare the current index in the left and right sub arrays. Place the lesser value into the temporary array.
 *             Loop through all values to build a sorted temporary array. Return the temporary array back up the recursion trace.
 *         7. Continue building the sorted array for the left sub arrays by doing steps 5 and 6.
 *         8. With both the first left and first right sub arrays sorted, do step 6 on the original (now sorted) sub arrays.
 *         9. Now, you have a fully sorted array.
 *
 * Example walkthrough:
 *     Key:
 *         a - array
 *         la - leftSubArray
 *         ra - rightSubArray
 *
 *     Unsorted Array:
 *         a = [4,2,5,6,1,10]
 *
 *     Apply sort(a):
 *         first sort
 *             la = [4,2,5]
 *             ra = [6,1,10]
 *         la first sort: (recursion)
 *             la = [4]
 *             ra = [2, 5]
 *         la sorted, ra first sort
 *             la = [2]
 *             ra = [5]
 *             la & ra sorted, now merge
 *         ra first merge:
 *             la = [2]
 *             ra = [5]
 *             -> a = [2,5]
 *         pass a up recursion, la second merge
 *             la = [4]
 *             ra = [2,5]
 *             -> a = [2,4,5]
 *             original la now sorted
 *        ra first sort:
 *             la = [6]
 *             ra = [1, 10]
 *         la sorted, ra first sort:
 *             la = [1]
 *             ra = [10]
 *             la & ra sorted, now merge
 *         ra first merge:
 *             la = [1]
 *             ra = [10]
 *             -> a = [1,10]
 *         pass a up recursion, ra second merge
 *             la = [6]
 *             ra = [1, 10]
 *             -> a = [1,6,10]
 *             original ra now sorted
 *         lastly sort original la and ra
 *             la = [2,4,5]
 *             ra = [1,6,10]
 *             -> a = [1,2,4,5,6,10]
 *             array sorted!
 */
public class MergeSortSWD {
    /*
     * Method:
     *    sort
     *
     * Purpose:
     *    Recursive division method for Mergesort algorithm.
     *    The original, unsorted array is passed into this method.
     *    This method is recursive and calls itself with its sub arrays.
     *
     * @param array: integer array to be sorted (until length is 1)
     * @return array: sorted array (or sub array)
     */
    public static int[] sort(int[] array) {
        if (array.length < 2) {
            return array;
        }

        int middle = array.length / 2;

        int[] leftSubArray = Arrays.copyOfRange(array, 0, middle);
        int[] rightSubArray = Arrays.copyOfRange(array, middle, array.length);

        sort(leftSubArray);
        sort(rightSubArray);

        merge(array, leftSubArray, rightSubArray);

        return array;
    }

    /*
     * Method:
     *     merge
     *
     * Purpose:
     *     Rebuilding method for Mergesort performed on bottom-most sub arrays.
     *     Loop through all indexes in left and right sub arrays and insert lesser value into a temporary array.
     *     The temporary array is built in-place i.e. not a fresh (all 0) array.
     *     This is not a recursive function.
     *
     * @param array: integer array for sorted array built from sorted left and right sub arrays
     * @param leftSubArray: integer array for sorted leftSubArray
     * @param rightSubArray: integer array for sorted rightSubArray
     * @return
     */
    private static void merge(int[] array, int[] leftSubArray, int[] rightSubArray) {
        int i = 0;
        int j = 0;
        int k = 0;

        while (i <  leftSubArray.length && j < rightSubArray.length) {
            if (leftSubArray[i] <= rightSubArray[j]) {
                array[k++] = leftSubArray[i++];
            } else {
                array[k++] = rightSubArray[j++];
            }
        }

        while (i < leftSubArray.length) {
            array[k++] = leftSubArray[i++];
        }

        while (j < rightSubArray.length) {
            array[k++] = rightSubArray[j++];
        }
    }

    /*
     * Method:
     *     printPseudocode
     *
     * Purpose:
     *     Log the pseudocode of the MergeSort Algorithm to the console.
     *
     * Attribution:
     *     https://www.toptal.com/developers/sorting-algorithms/merge-sort.
     *
     * Modifications:
     *     number lines added and comments shifted to in-line.
     *
     * @param none
     * @return none
     */
    public static String getPseudocode() {
        String pseudocode = "- Merge Sort Pseudocode -\n" +
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
        return pseudocode;
    }
}
