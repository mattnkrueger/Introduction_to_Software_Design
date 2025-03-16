package gameCLI;

import java.util.Random;

/**
 * Word choices
 */
public class WordBankCLI {
    /**
     * word bank for four-letter words
     */
    private static final String[] fourLetterWords = {"able", "back", "ball", "bark", "bear", "blue", "boat", "bowl", "cage", "cold", "corn", "dark", "dawn", "dirt", "door", "dust", "easy", "fall", "fast", "gate", "gold", "hill", "hold", "iron", "join", "jump", "lamp", "leaf", "line", "moon"};

    /**
     * word bank for five-letter words
     */
    private static final String[] fiveLetterWords = {"apple", "bread", "crane", "daisy", "eagle", "flame", "grape", "honey", "ivory", "jolly", "kites", "lemon", "mango", "noble", "olive", "pearl", "quilt", "raise", "storm", "table", "upset", "vivid", "wheat", "xenon", "yacht", "zesty", "bliss", "charm", "dream", "earth", "frost"};

    /**
     * @return word to use as correct word
     */
    public String getRandomWord() {
        int randomArrayChoice;
        int correctWordChoice;
        Random random = new Random();
        randomArrayChoice = random.nextInt(2) + 4;
        correctWordChoice = random.nextInt(fourLetterWords.length);

        String correctWord;
        if (randomArrayChoice == 4) {
            correctWord = this.getFourLetterWords()[correctWordChoice];
        } else if (randomArrayChoice == 5) {
            correctWord = this.getFiveLetterWords()[correctWordChoice];
        } else {
            correctWord = this.getFiveLetterWords()[correctWordChoice];
        }
        return correctWord;
    }

    /**
     * @return array of four letter words
     */
    private String[] getFourLetterWords() {
        return fourLetterWords;
    }

    /**
     * @return array of five letter words
     */
    private String[] getFiveLetterWords() {
        return fiveLetterWords;
    }
}