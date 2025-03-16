/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

package wordleGUI.controllers;

import game.WebPages;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import java.awt.*;
import java.io.IOException;
import java.net.URI;

/*
 * Class:
 *     MainProgramController
 *
 * Purpose:
 *     The MainProgramController handles page navigation buttons in the navbar and footer to reduce redundant code,
 *         and manages the 'mainProgram.fxml' page which is the main JavaFX program.
 *
 *     This class defines a JavaFX BorderPane layout (top, bottom, center, left, right):
 *         Constant Views:
 *             - Top Pane: defines navbar ('navbar.fxml')
 *             - Bottom Pane: defines footer ('footer.fxml')
 *
 *         Variable View:
 *             - Center Pane: defines the fxml page being currently shown to the user.
 *               To do this, the Center Pane is accessed, and the wanted fxml pages is added as
 *               a child.
 *
 *     Lastly, this class passes itself to each new page initialization to enable access to 'setBorderPaneCenter',
 *         which handles the switching the center to a new fxml.
 *
 *     Button Actions are initialized using lambdas:
 *         example usage:
 *             - 'Lambda Expressions in GUI Applications'
 *             - https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
 */
public class MainProgramController {
    /* borderPaneTop: JavaFX Pane to be set to 'navbar.fxml' */
    @FXML
    private Pane borderPaneTop = new Pane();

    /* borderPaneTop: JavaFX Pane to be set to 'footer.fxml' */
    @FXML
    private Pane borderPaneBottom = new Pane();

    /* borderPaneTop: variable JavaFX Pane to be set to user selected fxml through actions */
    @FXML
    private Pane borderPaneCenter = new Pane();

    /* currentFXML: tracks the current fxml filepath as a string. ex 'home.fxml' */
    private String currentFXML;

    /*
     * Method:
     *     initializeNavbar
     *
     * Purpose:
     *     This method is called when the program is first initialized to produce the navbar on at the top of the screen.
     *     Here, the navbar is treated as its own node component.
     *
     *     Initialization Process:
     *         1. load the 'navbar.fxml' file and add it to the borderPaneTop set inside the 'mainProgram.fxml' page.
     *         2. the skeleton fxml is queried with <node>.lookup("#<component fxid>") to map to java.event.ActionEvent to enable user interaction.
     *
     * @param none
     * @return none
     */
    private void initializeNavbar() {
        try {
            // get footer
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/navbar.fxml"));
            Node navbar = loader.load();
            borderPaneTop.getChildren().add(navbar);

            // I know these are buttons, casting silences the IDE error
            Button home = (Button) navbar.lookup("#homeButton");
            Button help = (Button) navbar.lookup("#helpButton");
            Button exit = (Button) navbar.lookup("#exitButton");

            // map actions using a lamba to initialize (rather than set in scenebuilder)
            home.setOnAction(event -> setBorderPaneCenter("../fxml/home.fxml"));
            help.setOnAction(event -> setBorderPaneCenter("../fxml/howToPlay.fxml"));
            exit.setOnAction(event -> System.exit(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Method:
     *
     * Purpose:
     *
     * @param none
     * @return none
     */
    private void initializeContent() {
        setBorderPaneCenter("../fxml/home.fxml");
    }

    /*
     * Method:
     *     initializeFooter
     *
     * Purpose:
     *     This method is called when the program is first initialized to produce the footer on at the bottom of the screen.
     *     Here, the footer is treated as its own node component.
     *
     *     Initialization Process:
     *         1. load the 'footer.fxml' file and add it to the borderPaneBottom set inside the 'mainProgram.fxml' page.
     *         2. the skeleton fxml is queried with <node>.lookup("#<component fxid>") to map to java.event.ActionEvent to enable user interaction.
     *
     * @param none
     * @return none
     */
    private void initializeFooter() {
        try {
            // get footer
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/footer.fxml"));
            Node footer = loader.load();
            borderPaneBottom.getChildren().add(footer);

            // I know these are buttons, casting silences the IDE error
            Button linkLinkedIn = (Button) footer.lookup("#linkedInButton");
            Button linkGitHub = (Button) footer.lookup("#gitHubButton");
            Button linkGitLab = (Button) footer.lookup("#gitLabButton");

            // map actions using a lamba to initialize (rather than set in scenebuilder)
            linkLinkedIn.setOnAction(event -> openWebPage("linkedIn"));
            linkGitHub.setOnAction(event -> openWebPage("gitHub"));
            linkGitLab.setOnAction(event -> openWebPage("gitLab"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Method:
     *     openWebPages
     *
     * Purpose:
     *     This method handles user interaction with buttons that pull up webpages:
     *         - "gitHub" -> Webpages.getWebpages().get("gitHub")
     *         - "gitLab" -> Webpages.getWebpages().get("gitLab")
     *         - "linkedIn" -> Webpages.getWebpages().get("linkedIn")
     *
     *     Here, the wanted webpage is mapped inside WebPages to receive the string representing the page URL.
     *     Then, the string URL is passed in Uniform Resource Identifier to be used by Desktop.browse().
     *     This interacts with the users machine Desktop to pull up the URL on their user-defaulted web-browser.
     *
     * Refactor:
     *     Accessing the HashMap inside WebPages should not be allowed... Using '.get' should be handled privately,
     *         rather than publicly outside its scope (such as this method.)
     *
     * @param webPage: string representing url of wanted page
     * @return none
     */
    private void openWebPage(String webPage) {
        String wantedPage = WebPages.getWebpages().get(webPage);
        try {
            // check if suppported (recommended by tooltip over Desktop)
            if (Desktop.isDesktopSupported()) {
                // desktop instance
                Desktop userDesktop = Desktop.getDesktop();

                // create uri object parsing wanted url
                URI uri = new URI(wantedPage);

                // debugging
                //            System.out.println("webpage:" + uri);
                //            System.out.println("host: " + uri.getHost() + ", path: " + uri.getRawPath());

                // open
                userDesktop.browse(uri);
            } else {
                throw new HeadlessException("User Desktop is not supported on this machine.");
            }
        } catch (HeadlessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in openWebPage(String webPage) (MainProgramController)");
        }
    }

    /*
     * Method:
     *     setBorderPaneCenter
     *
     * Purpose:
     *     This method changes the current JavaFX Pane inside the BorderPane Center of 'mainProgram.fxml'.
     *
     *     To do this, the wanted fxml is loaded, and the controller is grabbed.
     *     The controller is linked to the controller type of the wanted page.
     *     Lastly, the 'this' controller is passed into the wanted page controller to obtain access to this method.
     *
     * Refactor:
     *     This process is a convoluted and there is surely a better way to achieve page navigation.
     *         More research needs to be done on my part.
     *
     * @param fxmlFilePath: string representing wanted fxml page's filepath
     * @return none
     */
    public void setBorderPaneCenter(String fxmlFilePath) {
        this.currentFXML = fxmlFilePath;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFilePath));
            Pane currentCenter = loader.load();

            Object object = loader.getController();

            // link 'this' mainProgramController to currentCenter.
            // this allows for use of setBorderPaneCenter (this method) to be called to update 'mainProgram.fxml'.
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
            borderPaneCenter.getChildren().add(currentCenter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Method:
     *     initialize
     *
     * Purpose:
     *     
     *
     * @param
     * @return
     */
    public void initialize() {
        initializeNavbar();
        initializeContent();
        initializeFooter();
    }
}