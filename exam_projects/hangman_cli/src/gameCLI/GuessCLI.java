package gameCLI;

/**
 * User guess char
 */
public class GuessCLI {
    /**
     * user guess
     */
    private final char guess;

    /**
     * non-argument constructor sets default guess to 'a'
     */
    public GuessCLI() {
        this('a');
    }

    /**
     * monadic constructor
     * @param guess
     */
    public GuessCLI(char guess) {
        char lowercaseGuess = Character.toLowerCase(guess);
        this.guess = lowercaseGuess;
    }

    /**
     * @return user guess
     */
    public char getGuess() {
        return this.guess;
    }
}
