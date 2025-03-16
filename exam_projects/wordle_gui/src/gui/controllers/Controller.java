/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

package gui.controllers;

/*
 * Interface:
 *     Controller
 *
 * Purpose:
 *     This interface defines a simple setter function that SHOULD BE IMPLEMENTED for all
 *        controllers for pages to be displayed in mainProgram.fxml's BorderPane center
 *
 * Overridden Method:
 *     setMainProgramController
 *
 * Refactor:
 *     A much, much better implementation would be to use this as a super class for all controllers.
 *     I had a different vision for this Interface when I began development, but only needed this...
 *     I am on a time crunch to turn in all programs, so I am leaving as is.
 */
public interface Controller {
    /*
     * Method:
     *     setMainProgramController
     *
     * Purpose:
     *     Override this method to link the controller with the parent MainProgramController.
     *
     * @param mainProgramController: MainProgramController object of the JavaFX application
     * @return none
     */
    public void setMainProgramController(MainProgramController mainProgramController);
}
