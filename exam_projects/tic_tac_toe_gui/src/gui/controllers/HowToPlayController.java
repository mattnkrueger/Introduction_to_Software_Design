/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

package gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/*
 * Class:
 *
 *
 * Purpose:
 *
 *
 * Multithreaded:
 *
 *
 */
public class HowToPlayController implements Controller {
    /* "this is an attribute" */
    @FXML
    private Button backToGame;

    /* "this is an attribute" */
    private MainProgramController mainProgramController;

    /*
     * Method:
     *
     *
     * Purpose:
     *
     * @override
     * @param
     * @return
     */
    @Override
    public void setMainProgramController(MainProgramController mainProgramController) {
        this.mainProgramController = mainProgramController;
    }

    /*
     * Method:
     *
     *
     * Purpose:
     *
     *
     * @param
     * @return
     */
    @FXML
    public void switchBackToGame(ActionEvent e) {
        mainProgramController.setBorderPaneCenter("../fxml/game.fxml");
    }
}
