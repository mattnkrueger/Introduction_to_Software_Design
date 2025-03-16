/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

package gui.utils;

import javafx.scene.control.Label;
import java.util.HashMap;

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
public class WebPages {
    /* "this is an attribute" */
    private static HashMap<String, String> webpages;

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
    public static void initialize() {
        webpages = new HashMap<>();
        webpages.put("linkedIn", "https://www.linkedin.com/in/mattnkrueger/");
        webpages.put("gitHub", "https://github.com/mattnkrueger");
        webpages.put("gitLab", "https://class-git.engineering.uiowa.edu/swd2024fall/mnkrueger/-/wikis/Home/Oral-Exam-2/%7BS20_TicTacToe_Medium_GUI%7D");
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
    public static HashMap<String, String> getWebpages() {
        return webpages;
    }
}
