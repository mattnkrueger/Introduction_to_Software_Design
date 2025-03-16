/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

package wordleGUI.utils;

import java.util.HashMap;

/*
 * Class:
 *     Webpages
 *
 * Purpose:
 *     This utility class serves as a storage of the navigation links (URLS) inside the footer GUI fxml code.
 *
 *     The links are stored in a HashMap mapping string to string:
 *         "LinkedIn" -> "https://www.linkedin.com/in/mattnkrueger/"
 *
 *     To enable use, the class must first be initialized using 'WebPages.initialize()'
 *
 */
public class WebPages {
    /* webpages: HashMap mapping the wanted link to its URL */
    private static HashMap<String, String> webpages;

    /*
     * Method:
     *     initialize
     *
     * Purpose:
     *     This method creates a new HashMap, and places the following links into the HashMap:
     *         1. My personal LinkedIn
     *         2. My personal GitHub
     *         3. Class GitLab
     *
     *     To add additional pages, add another line:
     *         'webpages.put(<identifier>, <url>)'
     *
     * @param none
     * @return none
     */
    public static void initialize() {
        webpages = new HashMap<>();
        webpages.put("linkedIn", "https://www.linkedin.com/in/mattnkrueger/");
        webpages.put("gitHub", "https://github.com/mattnkrueger");
        webpages.put("gitLab", "https://class-git.engineering.uiowa.edu/swd2024fall/mnkrueger/-/wikis/Home/Oral-Exam-2/S52_WordleGUI_Hard?redirected_from=S52_WordleGUI_Hard");
    }

    /*
     * Method:
     *     getWebpages
     *
     * Purpose:
     *     This method returns the HashMap belonging to this class.
     *     With the returned map, you can query for a webpage you want to use.
     *
     *     Ex usage:
     *         'String wantedPage = WebPages.getWebpages().get(webPage);'
     *
     * @see MainProgramController
     * @param none
     * @return webpages: HashMap containing string identifier to string URL links.
     */
    public static HashMap<String, String> getWebpages() {
        return webpages;
    }
}
