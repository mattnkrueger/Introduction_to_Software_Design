/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

package wordleGUI.controllers;

import javafx.animation.PauseTransition;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import spellcheck.SpellChecker;
import wordleGUI.utils.BackgroundColors;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import game.Words;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import wordleGUI.utils.WordleGame;

/*
 * Class:
 *     GameController
 *
 * Purpose:
 *     This class is the JavaFX controller for the Wordle game.
 *
 *     Upon being loaded into the main content of 'mainProgram.fxml' by MainProgramController,
 *         'game.fxml', the fxml attached to this controller, is initialized creating the 6x5 grid and buttons
 *         representing the keyboard.
 *
 *     The buttons are linked to user actions and is the interface for the user interacting with the Wordle game.
 *
 *    Additionally, this class holds the methods linked to the buttons, which ultimately point to the WordleGame utility
 *         to handle logic.
 *
 * Implements:
 *     Controller - adds method to link this controller to MainProgramController allowing access to change the content in the program.
 *     Initializable - adds method to initialize the screen when loaded.
 */
public class GameController implements Controller {
    /* mainProgramController: reference to the parent MainProgramController object; see Controller Interface */
    private MainProgramController mainProgramController;

    /* currentGuess: integer representing the guess that the user is on */
    private int currentGuess;

    /* currentIndex: integer representing the current index of the guess to insert the letter */
    private int currentIndex;

    /* livesLeft: JavaFX fxml Text for user lives left visual (inside of statsGridPane) */
    @FXML
    private Text livesLeft;

    /* guessesUsed: JavaFX Text for guesses used by user visual (inside of statsGridPane) */
    @FXML
    private Text guessesUsed;

    /* buttonVBox: JavaFX for button parent node to design layout in vertical manner */
    @FXML
    private VBox buttonVBox;

    /* guessGridPane: JavaFX for 6x5 GridPane to hold user guesses */
    @FXML
    private GridPane guessGridPane;

    /* guessTexts: 6x5 array of JavaFX Texts to hold the current letter indexes of user guess. Parallel with guessPanes. */
    private final Text[][] guessTexts = new Text[6][5];

    /* guessPanes: 6x5 array of JavaFX BorderPanes to hold the guessTexts for respective indexes of user guess. Parallel with guessTexts. */
    private final BorderPane[][] guessPanes = new BorderPane[6][5];

    /* defaultBackground: see BackgroundColors */
    private final Background defaultBackground = BackgroundColors.getDefaultBackground();

    /* currentIndexBackground: see BackgroundColors */
    private final Background currentIndexBackground = BackgroundColors.getCurrentIndexBackground();

    /* yellowBackground: see BackgroundColors */
    private final Background yellowBackground = BackgroundColors.getYellowBackground();

    /* greenBackground: see BackgroundColors */
    private final Background greenBackground = BackgroundColors.getGreenBackground();

    /* grayBackground: see BackgroundColors */
    private final Background grayBackground = BackgroundColors.getGrayBackground();

    /* redBackground: see BackgroundColors */
    private final Background redBackground = BackgroundColors.getRedBackground();

    /*
     * Method:
     *     initialize
     *
     * Purpose:
     *     This method is needed to correctly produce the fxml components on the screen.
     *
     *     Since I have already injected the wanted visuals into my fxml file, the initalizable
     *        is called when this controller is loaded to ensure the necessary setup of the UI styles,
     *        and initial states for the screen.
     *
     *     As a side effect, this method resets the Wordle game where you cannot
     *        begin playing Wordle, switch to another play, and expect to resume the same game; as this is always called when loaded
     *        by MainProgramController, the previous game is cleared and a new game initialized.
     *
     * @param none
     * @return none
     */
    public void initialize() {
        // initializes 6x5 grid panes and texts
        initializeGuesses();

        // initialize keyboard
        initializeButtonVBox();

        // restart index (to 0,0) and update status labels
        currentGuess = 0;
        currentIndex = 0;
        updateRemainingLivesAndGuessesUsed();

        // restart Wordle Game stats & word
        WordleGame.setRemainingLives(6);
        WordleGame.setCorrectWord(Words.getRandomWord());

        // FOR TEST: print out correct word to verify working logic
        System.out.println("CORRECT WORD: " + WordleGame.getCorrectWord());
    }

    /*
     * Method:
     *     updateRemainingLivesAndGuessesUsed
     *
     * Purpose:
     *     Obtain the current lives left to determine number of guesses used.
     *     Update the JavaFX Text display with coloring cases:
     *         1. if user has 5 or 6 lives -> green text
     *         2. if user has 3 or 4 lives -> yellow text
     *         1. if user has 1 or 2 lives -> red text
     *
     * @param
     * @return
     */
    private void updateRemainingLivesAndGuessesUsed() {
        int remainingLives = WordleGame.getRemainingLives();
        livesLeft.setText("Lives Left: " + String.valueOf(remainingLives));
        if (remainingLives > 4) {
            livesLeft.setFill(Color.GREEN);
        } else if (remainingLives > 2) {
            livesLeft.setFill(Color.ORANGE);
        } else {
            livesLeft.setFill(Color.RED);
        }
        guessesUsed.setText("Guesses Used: " + String.valueOf((6-remainingLives)));
    }

    /*
     * Method:
     *     initializeGuesses
     *
     * Purpose:
     *     This method initializes the JavaFX BorderPanes and child texts by setting guessPanes and guessTexts to default values.
     *
     *     This is achieved by looping through each node in the guessGridPane JavaFX 6x5 gridPane.
     *     The GridPane provides method to obtain row and column of current node, so a nested for loop is not needed.
     *
     * @param none
     * @return none
     */
    private void initializeGuesses() {
        // initialize grid items by looping through each index in the grid
        //
        // there is one node in each grid index:
        // BorderPane - controls the background of the pane (gray, green, yellow, white)
        //
        // inside each BorderPane, there is one more node:
        // Text - controls the user guess for the index
        for (Node node : guessGridPane.getChildren()) {
            if (node instanceof BorderPane) {
                // initialize the current border pane
                BorderPane borderPane = (BorderPane) node;

                Integer rowIndex = GridPane.getRowIndex(borderPane);
                Integer colIndex = GridPane.getColumnIndex(borderPane);
                int row = Objects.requireNonNullElse(rowIndex, 0); // these oneliners are suggested by idea - i was using basic if statement
                int col = Objects.requireNonNullElse(colIndex, 0);

                // add to guess array
                guessPanes[row][col] = borderPane;

                Text text = (Text) borderPane.getCenter();
                guessTexts[row][col] = text;

                // set default
                borderPane.setBackground(defaultBackground);
            }
        }

        // set current index to 0, 0
        guessPanes[0][0].setBackground(currentIndexBackground);
        guessTexts[0][0].setText("");
    }

    /*
     * Method:
     *     initializeButtonVBox
     *
     * Purpose:
     *     This method initializes the on-screen keyboard of 29 buttons:
     *         - 26 letter buttons for alphabet
     *         - delete button (backspace icon)
     *         - enter button (enter icon)
     *         - clear button (trash icon)
     *
     *     The keyboard is initialized by created three JavaFX HBoxs for the three rows of the qwerty keyboard.
     *     The HBoxes are then added to the parent JavaFX VBox buttonVBox representing the keyboard.
     *
     * @param none
     * @return none
     */
    private void initializeButtonVBox() {
        // create first row and add first row letters (via Words utility class)
        HBox firstRow = new HBox();
        for (int i = 0; i < Words.getFirstRow().length; i++) {
            String letter = Words.getFirstRow()[i];
            Button letterButton = setInitializedButton(letter);
            firstRow.getChildren().add(letterButton);
        }

        // create second row and add second row letters (via Words utility class)
        HBox secondRow = new HBox();
        for (int i = 0; i < Words.getSecondRow().length; i++) {
            String letter = Words.getSecondRow()[i];
            Button letterButton = setInitializedButton(letter);
            secondRow.getChildren().add(letterButton);
        }

        // create third row and add third row letters (via Words utility class)
        HBox thirdRow = new HBox();
        for (int i = 0; i < Words.getThirdRow().length; i++) {
            String letter = Words.getThirdRow()[i];
            Button letterButton = setInitializedButton(letter);
            thirdRow.getChildren().add(letterButton);
        }

        // create backspace button & add to third row
        Image backspaceImg = new Image(getClass().getResourceAsStream("../resources/backspace.png"));
        ImageView backspaceImgView = new ImageView(backspaceImg);
        backspaceImgView.setImage(backspaceImg);
        backspaceImgView.setFitHeight(20);
        backspaceImgView.setFitWidth(25);
        Button deleteButton = setInitializedButton("Del");
        deleteButton.setGraphic(backspaceImgView);
        thirdRow.getChildren().add(deleteButton);

        // create clear button & add to third row
        Image trashImg = new Image(getClass().getResourceAsStream("../resources/trash.png"));
        ImageView trashImgView = new ImageView(trashImg);
        trashImgView.setImage(trashImg);
        trashImgView.setFitHeight(20);
        trashImgView.setFitWidth(20);
        Button clearButton = setInitializedButton("Clear");
        clearButton.setGraphic(trashImgView);
        secondRow.getChildren().add(clearButton);

        // create enter button & add to second row
        Image enterImg = new Image(getClass().getResourceAsStream("../resources/enter.png"));
        ImageView enterImgView = new ImageView(enterImg);
        enterImgView.setImage(enterImg);
        enterImgView.setFitHeight(20);
        enterImgView.setFitWidth(20);
        Button enterButton = setInitializedButton("Enter");
        enterButton.setGraphic(enterImgView);
        thirdRow.getChildren().add(enterButton);

        // row alignment props -> set to center of VBox (its parent node)
        firstRow.setAlignment(Pos.CENTER);
        secondRow.setAlignment(Pos.CENTER);
        thirdRow.setAlignment(Pos.CENTER);

        // add rows in order to stack inside the VBox (its parent node)
        buttonVBox.getChildren().add(firstRow);
        buttonVBox.getChildren().add(secondRow);
        buttonVBox.getChildren().add(thirdRow);
    }

    /*
     * Method:
     *     setInitializedButton
     *
     * Purpose:
     *     Called on each button initialized by initializeButtonVBox and links JavaFX button to a javafx.event ActionEvent
     *
     *     Actions:
     *        - handleLetterButton: if button Text "a"-"z"
     *        - handleEnterButton: if button Text "Enter"
     *        - handleClearButton: if button Text  "Clear"
     *        - handleDeleteButton: if button Text  "Delete"
     *
     *     Additionally, the sizing of the buttons are set
     * @param none
     * @return none
     */
    private Button setInitializedButton(String title) {
        // create button with passed text
        Button button = new Button(title);

        // map button to its action
        // remove the text of the button if it is not a letter
        if (title.equals("Enter")) {
            button.setOnAction(this::handleEnterButton);
            button.setText(null);
            button.setPrefWidth(70);
            button.setPrefHeight(50);
        } else if (title.equals("Clear")) {
            button.setOnAction(this::handleClearButton);
            button.setText(null);
            button.setPrefWidth(50);
            button.setPrefHeight(50);
        } else if (title.equals("Del")) {
            button.setOnAction(this::handleDelButton);
            button.setText(null);
            button.setPrefWidth(70);
            button.setPrefHeight(50);
        } else {
            button.setOnAction(this::handleLetterButton);
            button.setPrefWidth(50);
            button.setPrefHeight(50);
        }
        return button;
    }

    /*
     * Method:
     *     setCursor
     *
     * Purpose:
     *
     *     After each click by the user, this method is called to change the JavaFX Text the current 'cursor' is on.
     *
     *     It takes the row & column of the 6x5 grid to set the cursor on, and changes the background color of the cursor to currentIndexBackground (white).
     *
     * @param row: integer row value to place cursor.
     * @return col: integer column value to place cursor.
     */
    private void setCursor(int row, int col) {
        // Min/Max bounds; ensure that index doesn't increase beyond 4 (last guess letter) or decrease beyond 0 (first guess letter)
        if (col > 4) {
            col = 4;
        } else if (col < 0) {
            col = 0;
        }

        // set cursor background
        if ((row < 5 && col >= 0 && col <= 4) && (WordleGame.getRemainingLives() != 0)) {
            guessPanes[row][col].setBackground(currentIndexBackground);
        }
    }

    /*
     * Method:
     *     handleEnterButton
     *
     * Purpose:
     *     This method handles the logic for the 'Enter' button on the user on-screen keyboard, both checking if the user has guessed the correct word, and either continuing or ending the game.
     *     This method utilizes the StringBuilder class to concatenate the content of the JavaFX Text values to build a 5-letter word.
     *     The created string is then 'analyzed' by the SpellChecker to either pass or deny the user's guess.
     *
     *     Outcomes of SpellChecker analysis:
     *         1. word is contained in SpellChecker HashSet: (WORDLE LOGIC)
     *             1.a - the word is passed to the WordleGame utility class to handle guess logic ('WordleGame.addGuess(guessWord, currentGuess);')
     *             1.b - to compensate for a lack of full-coverage inside the WordleGame class, there is additional processing to ensure the edge case of duplicate-lettered guesses is covered.
     *             1.c - the result of the processing returns a WordleGame GameStatus, which is used to determine if the Wordle is still in play.
     *                  1.c.1 - If the game is over: a javafx.animation PauseTransition (timer) is set to change the screen to 'gameOver.fxml' switching the screen through the MainProgramController after 0.5 seconds.
     *                  1.c.2 - If the game is not over: the current guess index and current guess are incremented and passed into setCursor(guessIndex, index) to update cursor.
     *         2. if word is NOT contained in SpellChecker HashSet: (PAUSE, FLASH RED, & CLEAR GUESS)
     *             2.a - a timer is set to pause all actions in the javafx.animation PauseTransition (timer) is set to clear the current guess and reset the cursor to the beginning index of the current guess row.
     *                 During the duration of the timer, the current JavaFX BorderPane guessPanes[][] inside the current row index of the 6x5 JavaFX GridPane are turned red, signaling an erroneous guess.
     *
     * Refactor:
     *     Doing this method again, I would remove all Wordle logic from the code & move it to WordleGame (where all other logic is handled).
     *
     * @see SpellChecker: contains HashSet accessed by this method to check if valid 5-letter word
     * @see PauseTransition: javafx.animation used as a timer to pause program then execute linked code (https://docs.oracle.com/javase/8/javafx/api/javafx/animation/PauseTransition.html)
     * @see Duration: javafx.util to set time paused by PauseTransition instance (https://docs.oracle.com/javase/8/javafx/api/javafx/util/Duration.html)
     * @see WordleGame: bulk of logic (besides edge case, which is handled by this method)
     *
     * @param e: javafx.event.Event mapped to the 'Enter' button, which triggers this method when clicked
     * @return none
     */
    private void handleEnterButton(ActionEvent e) {
        // loop through guess indexes to build guess word
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            String indexLetter = guessTexts[currentGuess][i].getText();
            stringBuilder.append(indexLetter);
        }
        String guessWord = stringBuilder.toString();

        // ensure that guess word is 5 letters and a valid word
        if (SpellChecker.isAWord(guessWord)) {
            // add to guess list
            WordleGame.addGuess(guessWord, currentGuess);

            // pass number 1:
            // apply coloring to all indexes
            // DOES NOT HANDLE MULTIPLE LETTER INSTANCES
            for (int i = 0; i < 5; i++) {
                // pass letter into wordle game utility
                String letter = guessTexts[currentGuess][i].getText();

                // initial updates
                WordleGame.handleNewGuessIndex(guessWord, letter, i);

                // update the color of the letter at the index
                if (WordleGame.getLetterStatuses().get(letter) == WordleGame.GuessStatus.GREEN) {
                    // if correct, regardless of multiple - make green
                    guessPanes[currentGuess][i].setBackground(greenBackground);
                } else if (WordleGame.getLetterStatuses().get(letter) == WordleGame.GuessStatus.YELLOW) {
                    // check if multiple occurrence (e.g. vivid - two v's)
                    if (WordleGame.isGuessMultipleOccurrence(guessWord, letter)) {
                        if (WordleGame.isLetterFirstOfMultiple(guessWord, letter, i)) {
                            // if first of multiple - make pane yellow
                            guessPanes[currentGuess][i].setBackground(yellowBackground);
                        } else {
                            // if not first of multiple - make pane gray
                            guessPanes[currentGuess][i].setBackground(grayBackground);
                        }
                    } else {
                        // if not multiple - make pane yellow
                        guessPanes[currentGuess][i].setBackground(yellowBackground);
                    }
                } else {
                    // wrong - make gray
                    guessPanes[currentGuess][i].setBackground(grayBackground);
                }

                // clear letter statuses to reset for next guess index
                WordleGame.getLetterStatuses().clear();
            }

            // update remaining lives and fxid labels
            WordleGame.updateRemainingLives();
            updateRemainingLivesAndGuessesUsed();

            // check game status
            if (WordleGame.getGameStatus() == WordleGame.GameStatus.WIN || WordleGame.getGameStatus() == WordleGame.GameStatus.LOSS) {
                // create timer that is 0.5 seconds
                PauseTransition gameOver = new PauseTransition(Duration.seconds(0.5));

                // after 0.5 seconds switch screen
                gameOver.setOnFinished(this::switchToGameOverScreen);

                // start timer
                gameOver.play();
            } else if (WordleGame.getGameStatus() == WordleGame.GameStatus.PLAYING) {
                // increment current guess & reset index to 0
                currentGuess++;
                currentIndex = 0;

                // update cursor
                setCursor(currentGuess, currentIndex);
            } else {
                System.out.println("UNKNOWN GAME STATUS");
                System.exit(0);
            }
        } else {
            // set all index panes to red for 0.5 seconds to indicate not a word
            for (int i = 0; i < 5; i++) {
                guessPanes[currentGuess][i].setBackground(redBackground);
            }

            // create timer that is 0.5 seconds
            PauseTransition errorIndication = new PauseTransition(Duration.seconds(0.5));

            // after 0.5 seconds clear the indexes
            errorIndication.setOnFinished(this::handleClearButton);

            // start timer
            errorIndication.play();
        }
    }

    /*
     * Method:
     *     handleDelButton
     *
     * Purpose:
     *     This method handles the user interaction with the 'Delete' button to move the current 'cursor' back one index in the user guess row.
     *     The cursor is moved back one index in the JavaFX Text indexes inside the 6x5 JavaFX GridPane if the current index is not at 0.
     *
     * @param e: javafx.event.Event mapped to the 'Delete' button, which triggers this method when clicked
     * @return none
     */
    private void handleDelButton(ActionEvent e) {
        // current borderpane letter
        Text currentIndexText = (Text) guessPanes[currentGuess][currentIndex].getCenter();
        String currentSpot = currentIndexText.getText();

        // set current background to default
        guessPanes[currentGuess][currentIndex].setBackground(defaultBackground);

        // delete logic
        if (currentIndex == 0) {
            // clear the current spot, keeping index the same
            guessTexts[currentGuess][currentIndex].setText("");
        } else {
            if (currentSpot.isEmpty()) {
                // move back and clear previous letter; fixes jumping over index problem
                currentIndex--;
            }
            // clear the text at the current index without moving index
            guessTexts[currentGuess][currentIndex].setText("");
        }

        // update cursor
        setCursor(currentGuess, currentIndex);
    }

    /*
     * Method:
     *     handleClearButton
     *
     * Purpose:
     *     This method sets all JavaFX Text indexes inside the 6x5 JavaFX GridPane to "", removing the current indexes in the users guess.
     *     This method also sets the cursor back to the start (index 0) of the current guess row.
     *
     * @param e: javafx.event.Event mapped to the 'Clear' button, which triggers this method when clicked
     * @return none
     */
    private void handleClearButton(ActionEvent e) {
        // loop through indexes in currentGuess
        for (int i = 0; i < 5; i++) {
            // set all to default background & and clear text
            guessPanes[currentGuess][i].setBackground(defaultBackground);
            guessTexts[currentGuess][i].setText("");
        }

        // set currentIndex to 0 & update cursor
        currentIndex = 0;
        setCursor(currentGuess, currentIndex);
    }

    /*
     * Method:
     *     handleLetterButton
     *
     * Purpose:
     *     This method handles the user interaction with any of the alphabet buttons, adding the selected button's letter to the JavaFX Text index in the current 'cursor' location, then moves the cursor forward if not the fifth letter.
     *
     * @param e: javafx.event.Event mapped any of the "a"-"z" letter buttons, which triggers this method when clicked
     * @return none
     */
    private void handleLetterButton(ActionEvent e) {
        Button clicked = (Button) e.getSource();
        String letter = clicked.getText();

        // current borderpane letter
        Text currentIndexText = (Text) guessPanes[currentGuess][currentIndex].getCenter();
        String currentSpot = currentIndexText.getText();

        // if not last letter
        if (currentIndex < 4 && currentSpot.isEmpty()) {
            // set current pane background to default & current text to letter
            guessPanes[currentGuess][currentIndex].setBackground(defaultBackground);
            guessTexts[currentGuess][currentIndex].setText(letter);

            // increment index
            currentIndex++;
        } else if (currentIndex == 4 && currentSpot.isEmpty()) {
            // set current pane background to default & current text to letter
            guessPanes[currentGuess][currentIndex].setBackground(defaultBackground);
            guessTexts[currentGuess][currentIndex].setText(letter);
        }

        // update cursor location
        setCursor(currentGuess, currentIndex);
    }

    /*
     * Method:
     *     switchToGameOverScreen
     *
     * Purpose:
     *     This method switches the main content shown in the 'mainProgram.fxml's to the 'gameOver.fxml' code.
     *
     * @see handleEnterButton
     * @see PauseTransition
     * @param e: javafx.event.Event mapped to the end of the PauseTransition inside the 'handleEnterButton' method for if the game is over.
     * @return none
     */
    @FXML
    public void switchToGameOverScreen(ActionEvent e) {
        mainProgramController.setBorderPaneCenter("../fxml/gameOverScreen.fxml");
    }

    /*
     * Method:
     *     setMainProgramController
     *
     * Purpose:
     *     Link the controller with the parent MainProgramController.
     *
     * @param mainProgramController: MainProgramController object of the JavaFX application
     * @return none
     */
    @Override
    public void setMainProgramController(MainProgramController mainProgramController) {
        this.mainProgramController = mainProgramController;
    }
}