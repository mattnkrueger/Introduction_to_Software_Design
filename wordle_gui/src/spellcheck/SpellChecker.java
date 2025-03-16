/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

package spellcheck;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.io.IOException;

/*
 * Class:
 *     SpellChecker
 *
 * Purpose:
 *     This utility class is used inside the Wordle game logic to determine if a user's guess is a valid 5-letter word guess.
 *
 *     To use the spellchecking system, the utility class must be first initialize with 'SpellChecker.initialize();'
 *     This method reads the 'dictionary.txt' file to build the wordDictionary HashSet.
 *
 *     This class utilizes the dictionary on the MacOS OS (development for this project was done on personal machine) and
 *         processes only 5-letter words into a HashSet to be queried on as a spellchecker. If the 5 letter guess is contained
 *         inside the HashSet, then it is a valid guess.
 *
 *     How to access the of original 'words' file on MacOS bash/zsh command line:
 *         $ cd /usr/share/dict
 *         $ vim/code/nano words
 *
 *     '/usr/share/dict/words' was copied into spellcheck.resources package to be further processed by this class. This promotes
 *     safety as it is a local file being accessed rather than a system file. Additionally, it can be shared across different OS which may have
 *     varying dictionaries.
 */
public class SpellChecker {
    /* wordDictionary: HashSet containing strings of valid 5-letter words used for spellchecking */
    private static HashSet<String> wordDictionary;

    /*
     * Method:
     *     isAWord
     *
     * Purpose:
     *     This method queries the HashSet wordDictionary to see if the guessed word is a valid word.
     *     If the guess is in the HashSet, return true, else false.
     *
     * @param guess: string user guess containing five letters
     * @return boolean: boolean flag for if guess is a valid word
     */
    public static boolean isAWord(String guess) {
        return wordDictionary.contains(guess);
    }

    /*
     * Method:
     *     initialize
     *
     * Purpose:
     *     This method builds the HashSet, populating the HashSet with 5-letter words.
     *
     *     To do this the 'dictionary.txt' file is read line by line:
     *         1. create input stream (to read bytes)
     *         2. check if the input stream object exists
     *         3. wrap as a reader (to now read chars)
     *         4. wrap reader as buffered reader (for improved efficiency as buffers send data quicker than normal stream)
     *         5. read line by line to add 5-letter words into HashSet
     *
     * @throws IOException
     * @param
     * @return
     */
    public static void initialize() throws IOException {
        wordDictionary = new HashSet<>();
        try {
            InputStream inputStream = SpellChecker.class.getResourceAsStream("resources/dictionary.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String word;

            // can read until null as there are no empty lines (null acts as EOF)
            while ((word = bufferedReader.readLine()) != null) {
                if (isWordCorrectLength(word)) {
                    wordDictionary.add(word);
                }
            }
        } catch (IOException e) {
            System.out.println("Error creating spellchecker");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /*
     * Method:
     *     isWordCorrectLength
     *
     * Purpose:
     *     This method simply returns boolean for if passed word is 5 letters long
     *
     * @param word: string word
     * @return boolean: return (word.length() == 5);  true if 5-letters, false if not
     */
    private static boolean isWordCorrectLength(String word) {
        return (word.length() == 5);
    }
}