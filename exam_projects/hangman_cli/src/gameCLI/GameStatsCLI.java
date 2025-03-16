package gameCLI;

/**
 * Game metadata
 */
public class GameStatsCLI {
    /**
     * is game finished?
     */
    private static boolean gameFinished;

    /**
     * number of rounds won
     */
    private static int winCount;

    /**
     * number of rounds played
     */
    private static int numberOfRoundsPlayed;

    /**
     * call freshGame()
     */
    public GameStatsCLI() {
        freshGame();
    }

    /**
     * add 1 to win count
     */
    public void incrementWinCount() {
        winCount ++;
    }

    /**
     *
     */
    public void endGame() {
        gameFinished = true;
    }

    /**
     * initialize new game
     */
    private void freshGame() {
        gameFinished = false;
        winCount = 0;
        numberOfRoundsPlayed = 0;
    }

    /**
     * call freshGame()
     */
    public void restartGame() {
        freshGame();
    }

    /**
     * add 1 to number of rounds played
     */
    public void incrementNumberOfRoundsPlayed() {
        numberOfRoundsPlayed ++;
    }

    /**
     * @return number of wins
     */
    public int getWinCount() {
        return winCount;
    }

    /**
     * @return number of rounds played
     */
    public int getNumberOfRoundsPlayed() {
        return numberOfRoundsPlayed;
    }

    /**
     * @return if game is finished
     */
    public static boolean isGameFinished() {
        return gameFinished;
    }
}
