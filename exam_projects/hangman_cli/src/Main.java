import gameCLI.*;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Entry point for cli game
 */
public class Main {

    /**
     * Command line popup to receive user guess
     *
     * @return the guessed letter
     */
    public static char chooseGuessUI() {
        Scanner scanner = new Scanner(System.in);
        char guessChosen = 0; // default

        System.out.println("Choose a Letter to Guess:\n");
        boolean validInput = false; // .charAt() can accept both numbers and letters, must be handled
        while (!validInput) {
            guessChosen = scanner.next().charAt(0);
            if (Character.isLetter(guessChosen)) {
                validInput = true;
            } else {
                System.out.println("Invalid Input: Enter a letter");
            }
        }
        return guessChosen;
    }

    /**
     *  Command line popup to receive user difficulty choice
     *
     * @return difficulty string
     * @throws InputMismatchException
     */
    public static String chooseDifficultyUI() throws InputMismatchException {
        Scanner scanner = new Scanner(System.in);
        int difficultyChosenInt;
        String difficultyChosenString = "MEDIUM"; // default

        System.out.println("Choose Round Difficulty:\n   1. Easy\n   2. Medium\n   3. Hard\n");
        try {
            difficultyChosenInt = scanner.nextInt();
            if (difficultyChosenInt == 1) {
                difficultyChosenString = "EASY";
            } else if (difficultyChosenInt == 2) {
                difficultyChosenString = "MEDIUM";
            } else if (difficultyChosenInt == 3) {
                difficultyChosenString = "HARD";
            }
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage() + " Error: invalid input\nGame Difficulty Defaults to 'MEDIUM'");
        }
        return difficultyChosenString;
    }

    /**
     * Command line popup to receive user action choice
     *
     * @return user action integer
     * @throws InputMismatchException
     */
    public static int chooseActionUI() throws InputMismatchException {
        Scanner scanner = new Scanner(System.in);
        int userActionChosen = 1; // default

        System.out.println("Choose an Action:\n   1. New Guess\n   2. New Round\n   3. QUIT GAME\n");
        try {
            userActionChosen = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage() + " Error: invalid input\nUser Action Defaults to '1' (New Guess)");
        }
        return userActionChosen;
    }

    /**
     * Command line popup to receive user choice to start new round
     *
     * @param callLocation game status method is called from (either 'endGame' or 'duringGame')
     * @return y or n to start new round
     * @throws InputMismatchException
     */
    public static char newRoundUI(String callLocation) throws InputMismatchException {
        Scanner scanner = new Scanner(System.in);
        char userActionChosen = 'n'; // default

        // handle location called from
        String newRoundMessage;
        if (callLocation.equals("endGame")) {
            newRoundMessage = "Play Another Round?\n[y/n]\n";
        } else if (callLocation.equals("duringGame")) {
            newRoundMessage = "Lose this Round and Start Fresh?\n[y/n]\n";
        } else {
            newRoundMessage = "'newRoundUI' called from unknown location. Ending game now...";
        }
        System.out.println(newRoundMessage);
        try {
            userActionChosen = scanner.next().charAt(0);
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage() + " Error: invalid input\n'newRoundUI' Input Defaults to 'n'");
        }
        return userActionChosen;
    }

    /**
     * Create new round and update game stats
     *
     * @param wordBank
     * @param currentGame
     * @param currentRound
     * @param callLocation
     * @return new round
     */
    public static RoundCLI newRound(WordBankCLI wordBank, GameStatsCLI currentGame, RoundCLI currentRound, String callLocation) {
        // get user input
        char newRoundChosen = newRoundUI(callLocation);
        if (newRoundChosen == 'y') {
            // update game
            currentGame.incrementNumberOfRoundsPlayed();

            // get user difficulty
            String userDifficultyChosen;
            userDifficultyChosen = chooseDifficultyUI();

            // get new correct word
            String correctWord;
            correctWord = wordBank.getRandomWord();

            // create new round
            currentRound = new RoundCLI(correctWord, userDifficultyChosen);
        } else if (callLocation.equals("endGame")) {
            // end game
            currentGame.endGame();
        }

        // keep round going to avoid this message spammed
        currentRound.setRoundFinished(false);

        return currentRound;
    }

    /**
     * MAIN: handle game logic through user input and method mapping
     *
     * @param args
     */
    public static void main(String[] args) {
        // initialize game packages
        GameStatsCLI currentGame = new GameStatsCLI();
        WordBankCLI wordBank = new WordBankCLI();
        String correctWord = wordBank.getRandomWord();

        // user interactions
        int userActionChosen;
        String userDifficultyChosen;
        char userGuessChosen;

        // chooseDifficultyUI
        userDifficultyChosen = chooseDifficultyUI();

        // initial round init
        RoundCLI currentRound = new RoundCLI(correctWord, userDifficultyChosen);

        while (!(GameStatsCLI.isGameFinished())) {

            //////////////////////////////////////////////
            // --- NEW ROUND
            if (currentRound.isRoundFinished()) {
                currentRound = newRound(wordBank, currentGame, currentRound, "duringGame");
            }
            //////////////////////////////////////////////

            //////////////////////////////////////////////
            // chooseActionUI
            userActionChosen = chooseActionUI();
            if (userActionChosen == 1) {
                // handle guess
                userGuessChosen = chooseGuessUI();
                GuessCLI userGuess = new GuessCLI(userGuessChosen);
                currentRound.checkGuess(userGuess);

                // output stats
                System.out.println("Lives Left: " + currentRound.getLivesLeft());
                System.out.println("Guesses: " + currentRound.getGuesses());
                System.out.println("Correct: " + currentRound.getCorrectGuesses());
            } else if (userActionChosen == 2) {
                currentRound.endRound();
            } else if (userActionChosen == 3) {
                currentGame.endGame();
                System.out.println("---- GAME ENDING STATS ----");
                System.out.println("played: " + currentGame.getNumberOfRoundsPlayed());
                System.out.println("won: " + currentGame.getWinCount());
            } else {
                // do nothing...
                continue;
            }
            //////////////////////////////////////////////

            //////////////////////////////////////////////
            // ---- GAME OVER
            // won round
            if (currentRound.isWordCompleted()) {
                // output winning message
                System.out.println("YOU WON!!!");
                System.out.println("You correctly Guessed: " + currentRound.getCorrectWord());

                // increment GameStats
                currentGame.incrementWinCount();

                System.out.println("Rounds Played: " + currentGame.getNumberOfRoundsPlayed());
                System.out.println("Wins: " + currentGame.getWinCount());

                // new round
                currentRound = newRound(wordBank, currentGame, currentRound, "endGame");
            }

            // lost round
            if (currentRound.isOutOfLives()) {
                // output losing message
                System.out.println("YOU LOST!!!");
                System.out.println("Correct word was: " + currentRound.getCorrectWord());

                System.out.println("Rounds Played: " + currentGame.getNumberOfRoundsPlayed());
                System.out.println("Wins: " + currentGame.getWinCount());

                // new round
                currentRound = newRound(wordBank, currentGame, currentRound, "endGame");
            }
            //////////////////////////////////////////////

            //////////////////////////////////////////////
            // --- UPDATE GUI
            // TODO... will do this in separate project
            //////////////////////////////////////////////
        }
    }
}