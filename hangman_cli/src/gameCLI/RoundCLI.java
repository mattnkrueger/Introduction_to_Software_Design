package gameCLI;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Current round stats
 */
public class RoundCLI {
    /**
     * all user guesses: string form
     */
    private final ArrayList<String> guesses;

    /**
     * all user correct guesses: map index int to string guess
     */
    private final HashMap<Integer, String> correctGuesses;

    /**
     * correct word (hidden to user)
     */
    private final String correctWord;

    /**
     * number of starting lives (set by user)
     */
    private final int startingLives;

    /**
     * remaining lives
     */
    private int livesLeft;

    /**
     * is round finished?
     */
    private boolean roundFinished;

    /**
     * non-argument constructor with default correctWord: "Error", default gameDifficulty "EASY"
     */
    public RoundCLI() {
        this("Error", "EASY");
    }

    /**
     * Dyadic constructor
     *
     * @param correctWord
     * @param gameDifficulty
     */
    public RoundCLI(String correctWord, String gameDifficulty) {
        int lives;
        if (gameDifficulty.equals("HARD")) {
            lives = 4;
        } else if (gameDifficulty.equals("MEDIUM")) {
            lives = 6;
        } else if (gameDifficulty.equals("EASY")) {
            lives = 8;
        } else {
            lives = 6; // defaults to 6 lives (MEDIUM)
        }

        // assign
        this.startingLives = lives;
        this.livesLeft = lives;
        this.correctWord = correctWord;
        this.roundFinished = false;

        // initialize
        this.guesses = new ArrayList<>();
        this.correctGuesses = new HashMap<>();
    }

    /**
     * @return guesses array
     */
    public ArrayList<String> getGuesses() {
        return this.guesses;
    }

    /**
     * @return correct guesses hashmap
     */
    public HashMap<Integer, String> getCorrectGuesses() {
        return this.correctGuesses;
    }

    /**
     * @return correct word
     */
    public String getCorrectWord() {
        return correctWord;
    }

    /**
     * remove 1 from lives left
     */
    private void decrementLivesLeft() {
        this.livesLeft --;
    }

    /**
     * set round to finished
     */
    public void endRound() {
        roundFinished = true;
    }

    /**
     * @param roundFinished
     */
    public void setRoundFinished(boolean roundFinished) {
        this.roundFinished = roundFinished;
    }

    /**
     * get round status
     * @return
     */
    public boolean isRoundFinished() {
        return roundFinished;
    }

    /**
     * @return number of starting lives
     */
    public int getStartingLives() {
        return this.startingLives;
    }

    /**
     * @return number of lives remaining
     */
    public int getLivesLeft() {
        return this.livesLeft;
    }

    /**
     * @param guess
     * @return true if new guess
     */
    private boolean isNewLetterGuess(GuessCLI guess) {
        return !this.guesses.contains(String.valueOf(guess.getGuess())); // ide suggested rather than basic if-else
    }

    /**
     * handle guess
     * @param guess
     */
    public void checkGuess(GuessCLI guess) {
        if (isNewLetterGuess(guess)) {
            // add to guesses regardless of correct or incorrect
            this.guesses.add(String.valueOf(guess.getGuess()));

            // loop through entire string and add to hash if char at index is present
            if (isCharInWord(guess)) {
                for (int i = 0; i < correctWord.length(); i++) {
                    handleGuessIndex(guess, i);
                }
            } else {
                decrementLivesLeft();
            }
        } else {
            // TODO: do this inside of GUI
            System.out.println("Letter already guessed");
            System.out.println(this.guesses);
        }
    }

    /**
     * @param guess
     * @return true if guess char is inside correct word
     */
    private boolean isCharInWord(GuessCLI guess) {
        return correctWord.contains(String.valueOf(guess.getGuess()));
    }

    /**
     * handle duplicate letter in word
     * @param guess
     * @param i index of char
     */
    private void handleGuessIndex(GuessCLI guess, int i) {
        if (correctWord.charAt(i) == guess.getGuess()) {
            correctGuesses.put(i, String.valueOf(guess.getGuess()));
        }
    }

    /**
     * @return true if word is fully guessed
     */
    public boolean isWordCompleted() {
        String guessString = String.join("", this.correctGuesses.values());
        return guessString.equals(correctWord);
    }

    /**
     * @return true if livesLeft = 0
     */
    public boolean isOutOfLives() {
        return (getLivesLeft() == 0);
    }
}