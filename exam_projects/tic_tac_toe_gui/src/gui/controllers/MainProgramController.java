/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

package gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import gui.utils.WebPages;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;

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
public class MainProgramController implements Initializable {
    /* "this is an attribute" */
    @FXML
    private Pane borderPaneTop = new Pane(); // navbar

    /* "this is an attribute" */
    @FXML
    private Pane borderPaneBottom = new Pane(); // footer

    /* "this is an attribute" */
    @FXML
    private Pane borderPaneCenter = new Pane(); // current scene

    /* "this is an attribute" */
    private String currentFXML;

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
    private void initializeNavbar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/navbar.fxml"));
            Node navbar = loader.load();
            borderPaneTop.getChildren().add(navbar);

            Button home = (Button) navbar.lookup("#homeButton");
            home.setOnAction(event -> setBorderPaneCenter("../fxml/home.fxml"));

            Button help = (Button) navbar.lookup("#helpButton");
            help.setOnAction(event -> setBorderPaneCenter("../fxml/howToPlay.fxml"));

            Button exit = (Button) navbar.lookup("#exitButton");
            exit.setOnAction(event -> System.exit(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    private void initializeContent() {
        setBorderPaneCenter("../fxml/home.fxml");
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
    private void initializeFooter() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/footer.fxml"));
            Node footer = loader.load();
            borderPaneBottom.getChildren().add(footer);

            // Access hyperlinks using fx:id
            Button linkLinkedIn = (Button) footer.lookup("#linkedInButton");
            Button linkGitHub = (Button) footer.lookup("#gitHubButton");
            Button linkGitLab = (Button) footer.lookup("#gitLabButton");

            // Assign event handlers todo implement open page
            linkLinkedIn.setOnAction(e -> openWebPage("linkedIn"));
            linkGitHub.setOnAction(e -> openWebPage("gitHub"));
            linkGitLab.setOnAction(e -> openWebPage("gitLab"));

        } catch (IOException e) {
            e.printStackTrace();
        }
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
    private void openWebPage(String webPage) {
        String wantedPage = WebPages.getWebpages().get(webPage);
        try {
            // desktop instance
            // used here to launch specified Uniform Resource Identifier (i.e. general URL)
            Desktop userDesktop = Desktop.getDesktop();

            // create uri object parsing wanted url
            URI uri = new URI(wantedPage);
            System.out.println("webpage:" + uri);
            System.out.println("host: " + uri.getHost() + ", path: " + uri.getRawPath());

            // desktop.brose() opens the page on the user-default web-browser
            userDesktop.browse(uri);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in openWebPage(String webPage) (MainProgramController)");
        }
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
    public void setBorderPaneCenter(String fxmlFilePath) {
        this.currentFXML = fxmlFilePath;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFilePath));
            Pane currentScene = loader.load();

            Object object = loader.getController();

            // give other pages access to the main program controller
            // cast to different object page to set controller
            //
            if (object instanceof HomeController) {
                HomeController hc = (HomeController) object;
                hc.setMainProgramController(this);
            } else if (object instanceof HowToPlayController) {
                HowToPlayController htpc = (HowToPlayController) object;
                htpc.setMainProgramController(this);
            } else if (object instanceof GameController) {
                GameController gc = (GameController) object;
                gc.setMainProgramController(this);
            } else if (object instanceof GameOverController) {
                GameOverController goc = (GameOverController) object;
                goc.setMainProgramController(this);
            }

            borderPaneCenter.getChildren().clear();
            borderPaneCenter.getChildren().add(currentScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeNavbar();
        initializeContent();
        initializeFooter();
    }
}