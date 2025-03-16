/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

package wordleGUI.utils;

import java.util.Arrays;
import java.util.HashMap;

/*
 * Class:
 *     WordleGame
 *
 * Purpose:
 *     This utility class represents the logic for a WordleGame.
 *
 *     To create a 'new' game of Wordle, you must use:
 *         'WordleGame.initialize();' which resets all of the attributes to create a fresh game.
 *         I use 'new' as this is a utility class; see Refactor section.
 *
 *     Contained within this class are two enums:
 *         1. GameStatus - variable tracking the status of the wordle game
 *         2. GuessStatus - variable tracking the status of letters in the alphabet for the wordle game
 *
 *     This class is utilized inside the GameController, where the user interacts with the JavaFX code. Actions inside
 *         the GameController are pointed to this utility class to handle the Wordle logic. Inside of this class are methods
 *         to handle a new wordle guess and attributes tracking the current Wordle game.
 *
 * Refactor:
 *     I would refactor this to remove static from all methods, treating each new WordleGame as a game instance rather
 *         than a utility class. I would add a static attribute to count the number of games played.
 *
 */
public final class WordleGame {
    /*
     * Enum:
     *     GameStatus
     *
     * Purpose:
     *     Values for status of the Wordle game.
     *
     * Values:
     *     WIN: correct word is guessed by user.
     *     LOSS: correct word is not guessed by user after 6 rounds.
     *     PLAYING: correct word has not been guessed yet and 6 rounds have not been completed.
     */
    public enum GameStatus {
        WIN,
        LOSS,
        PLAYING,
    }

    /*
     * Enum:
     *     GuessStatus
     *
     * Purpose:
     *     Values representing the status for each of the 26 letters in the alphabet during the Wordle game.
     *
     * Values:
     *     GREEN: correctly guessed letter in correct spot
     *     YELLOW: correctly guessed letter in incorrect spot
     *     UNGUESSED: letter yet to be guessed or not in word
     */
    public enum GuessStatus {
        GREEN,
        YELLOW,
        UNGUESSED,
    }

    /* remainingLives: integer tracking the lives of the user */
    private static int remainingLives;

    /* correctWord: string containing the correct 5-letter word */
    private static String correctWord;

    /* gameOver: boolean flag for if the game is over */
    private static boolean gameOver;

    /* letterStatuses: HashMap mapping string letter -> GuessStatus.<status>; holds the status of each letter for coloring */
    private static HashMap<String, GuessStatus> letterStatuses;

    /* wordleIndexStatuses: array of GameStatuses for guess letters; length 5 */
    private static GuessStatus[] wordleIndexStatuses;

    /* guesses: array tracking the user's guesses; length 6 (six maximum guesses per Wordle rules) */
    private static String[] guesses;

    /*
     * Method:
     *     handleNewGuessIndex
     *
     * Purpose:
     *     This method contains logic for determining the guess status for a new letter.
     *
     *     Taking the string of a single letter and the index it falls in the user guess,
     *         the letter is checked for existence in the correct word.
     *
     *     If the word is contained in the correct word, it is then checked for each of the cases:
     *         1. in word, in correct index -> Green
     *         2. in word, in incorrect index -> unguessed (incorrect) or Yellow
     *             2a. in word, not previously guessLettered correct, in wrong index -> Yellow
     *             2b. in word, not previously guessLettered, and first instance of letter guessLetter -> Yellow
     *             2c. in word, multiple occurrence, wrong position -> leave unguessed (incorrect)
     *     The logic determines the GuessStatus for a single letter in the users guess
     *
     *     This method determines if there is multiple occurrences by querying the wordleIndexStatuses, as I treat  26 letters as single occurrance,
     *         if the index already contains an entry, then it is a duplicate letter.
     *
     *     I modeled my behavior after New York Time's Wordle Game, so only first instance of a duplicate letter is colored.
     *
     * @param guess: string guess word
     * @param guessLetter: string single guess letter
     * @param index: integer of the single guess letter
     * @return none
     */
    public static void handleNewGuessIndex(String guess, String guessLetter, int index) {
        guessLetter = guessLetter.toLowerCase(); // not triggered as letters always lowercase in JavaFX button Text

        if (correctWord.contains(guessLetter)) { // in word, in correct index
            if (guessLetter.equals(String.valueOf(correctWord.charAt(index)))) {
                wordleIndexStatuses[index] = GuessStatus.GREEN;
                letterStatuses.put(guessLetter, GuessStatus.GREEN);
            } else { // in word, incorrect position
                if (letterStatuses.get(guessLetter) != GuessStatus.GREEN) { // in word, not previously guessLettered correct, in wrong index
                    wordleIndexStatuses[index] = GuessStatus.YELLOW;
                    if (letterStatuses.get(guessLetter) != GuessStatus.YELLOW) {  // in word, not previously guessLettered, and first instance of letter guessLetter
                        letterStatuses.put(guessLetter, GuessStatus.YELLOW);
                    }
                } else {  // in word, multiple occurrence, wrong position
                    wordleIndexStatuses[index] = GuessStatus.UNGUESSED;
                }
            }
        } else {  // not in word
            wordleIndexStatuses[index] = GuessStatus.UNGUESSED;
            letterStatuses.put(guessLetter, GuessStatus.UNGUESSED);
        }
    }

    /*
     * Method:
     *      isGuessMultipleOccurrence
     *
     * Purpose:
     *      Return boolean for whether multiple occurrence word.
     *
     *      This method takes a guess and a letter.
     *      By filtering the letter out (setting to empty) and subtracting the result from the known length, the multiplicity of the letter can be determined.
     *
     * @param guess: string for user guess word
     * @param letter: string for single guess letter
     * @return boolean: true if multiple occurrence, false if not
     */
    public static boolean isGuessMultipleOccurrence(String guess, String letter) {
        int letterCount;
        String filteredLetter = guess.replace(letter, "");
        int filteredLength = filteredLetter.length();
        letterCount = guess.length() - filteredLength;
        return (letterCount > 1);
    }

    /*
     * Method:
     *     isLetterFirstOfMultiple
     *
     * Purpose:
     *     This method determines if the current index is the first of multiple.
     *
     *     The first index is found by 'guess.indexOf(letter),' which returns the first occurrence of the letter in the array of guess letters.
     *     True is returned if the first index is equal to the passed letter index, else false
     *
     * @param guess: string for user guess word
     * @param letter: string for single guess letter
     * @param index: integer for index of guess letter
     * @return boolean: true if first of multiple occurrence, false if not
     */
    public static boolean isLetterFirstOfMultiple(String guess, String letter, int index) {
        int firstIndex = guess.indexOf(letter);
        return (firstIndex == index);
    }

    /*
     * Method:
     *     isWordleWordGuessedCorrectly
     *
     * Purpose:
     *     Check the guess status of the guess indexes. If all are 'GuessStatus.GREEN,' then the word is guessed correct.
     *
     * @param none
     * @return boolean: true if all letters are correct, false if not
     */
    public static boolean isWordleWordGuessedCorrectly() {
        for (GuessStatus indexStatus : wordleIndexStatuses) {
            if (!(indexStatus == GuessStatus.GREEN)) {
                return false;
            }
        }
        return true;
    }

    /*
     * Method:
     *     addGuess
     *
     * Purpose:
     *     This method is used to add a guess to the guesses array.
     *
     * @param guess: string for user guess word
     * @param guessNum: integer for which guess it is (max 6)
     * @return none
     */
    public static void addGuess(String guess, int guessNum) {
        guesses[guessNum] = guess;
    }

    /*
     * Method:
     *     updateRemainingLives
     *
     * Purpose:
     *     Decrement the current lives of the user if the users word is incorrect
     *
     * @param none
     * @return none
     */
    public static void updateRemainingLives(){
        if (!(isWordleWordGuessedCorrectly())){
            remainingLives--;
        }
    }

    /*
     * Method:
     *     getGameStatus
     *
     * Purpose:
     *     This method returns the current status of the game by checking the following cases:
     *         1. remainingLives < 0 (game over) -> loss
     *         2. word guessed correctly (game over) -> win
     *     If it is neither, then the game is still in play.
     *
     * @param none
     * @return none
     */
    public static GameStatus getGameStatus() {
        if (remainingLives <= 0) {
            return GameStatus.LOSS;
        } else if (isWordleWordGuessedCorrectly()) {
            return GameStatus.WIN;
        } else {
            return GameStatus.PLAYING;
        }
    }

    /*
     * Method:
     *     initialize
     *
     * Purpose:
     *     This method is used to create a fresh Wordle game by resetting the following:
     *         - remainingLives (tracks lives of user6; set to )
     *         - letterStatuses (HashMap mapping letter to its GuessStatus; all 26 letters set to GuessStatus.UNGUESSED)
     *         - wordleIndexStatuses (array for current guess statuses; size of 5, each index set to GuessStatus.UNGUESSED)
     *         - guesses (creates a new array to track user guesses; size 6)
     *
     * @param none
     * @return none
     */
    public static void initialize() {
        remainingLives = 6;

        letterStatuses = new HashMap<>();
        for (char letterChar = 'a'; letterChar <= 'z'; letterChar ++) {
            String letterStr = String.valueOf(letterChar);
            letterStatuses.put(letterStr, GuessStatus.UNGUESSED);
        }

        wordleIndexStatuses = new GuessStatus[5];
        Arrays.fill(wordleIndexStatuses, GuessStatus.UNGUESSED);

        guesses = new String[6];
    }

    // Setters (not documented)
    public static void setRemainingLives(int remainingLives) {
        WordleGame.remainingLives = remainingLives;
    }

    public static void setCorrectWord(String correctWord) {
        WordleGame.correctWord = correctWord.toLowerCase();
    }

    // Getters (not documented)
    public static int getRemainingLives() {
        return remainingLives;
    }

    public static String[] getGuesses() {
        return guesses;
    }

    public static String getCorrectWord() {
        return correctWord;
    }

    public static HashMap<String, GuessStatus> getLetterStatuses() {
        return letterStatuses;
    }
}
