/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

package game;

import java.util.Random;

/*
 * Class:
 *     Words
 *
 * Purpose:
 *     This utility class serves two purposes:
 *         1. word bank of correct words:
 *             - there are 30 words (per SWD directions) contained within the string array 'words.'
 *             - for the Wordle game, a random word is selected as the correct word for the round.
 *         2. qwerty layout for on-screen keyboard. These are split into three rows to resemble a real-world qwerty keyboard.
 *             - first row: qwertyuiop
 *             - second row: asdfghjkl
 *             - third row: zxcvbnm
 */
public final class Words {
    /* words: string array with possible correct words */
    private static final String[] words = {
            "apple", "brace", "crown", "dream", "eagle",
            "flame", "grape", "heart", "ivory", "joker",
            "knock", "lemon", "magic", "noble", "ocean",
            "pearl", "quick", "robot", "snake", "tower",
            "ultra", "vivid", "whale", "xenon", "yacht",
            "zebra", "angel", "blaze", "charm", "daisy",
            "eager", "frost", "glide", "hover", "ideal",
            "jelly", "karma", "laser", "metal", "novel"
    };

    /* firstRow: letters in the first row of the qwerty layout */
    private static final String[] firstRow= {
            "q", "w", "e", "r", "t", "y", "u", "i", "o", "p"
    };

    /* secondRow: letters in the second row of the qwerty layout */
    private static final String[] secondRow = {
            "a", "s", "d", "f", "g", "h", "j", "k", "l"
    };

    /* thirdRow: letters in the third row of the qwerty layout */
    private static final String[] thirdRow = {
            "z", "x", "c", "v", "b", "n", "m"
    };


    /*
     * Method:
     *     getRandomWord
     *
     * Purpose:
     *    Returns a random word from the word bank.
     *    This method is used to set the new correct word for the Wordle game.
     *
     * @param none
     * @return string: random word from the word bank
     */
    public static String getRandomWord() {
        Random random = new Random();
        int randomIndex = random.nextInt(words.length);
        return words[randomIndex];
    }

    // Getters (not documented)
    public static String[] getWords() {
        return words;
    }

    public static String[] getFirstRow() {
        return firstRow;
    }

    public static String[] getSecondRow() {
        return secondRow;
    }

    public static String[] getThirdRow() {
        return thirdRow;
    }
}
