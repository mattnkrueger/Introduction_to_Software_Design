/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

package gui.utils;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/*
 * Class:
 *     BackgroundColors
 *
 * Purpose:
 *     This utility class serves as a storage for commonly used background colors:
 *         - default (lightgray)
 *         - current index (white)
 *         - gray
 *         - yellow
 *         - green
 *         - red
 *
 *     Each method contained in this class contains the same code with modified javafx.scene.paint color:
 *         First a BackgroundFill is created:
 *             Color.<color> - javafx.scene.paint; this is different for each background color;
 *             CornerRadii.EMPTY - javafx.scene.layout for radius of parent node; set to null so fxml code can determine radii.
 *             Insets.Empty - javafx.geometry.Insets for space around a node; set to null so fxml code can determine margin/padding.
 *
 *     This BackgroundFill is then passed into a javafx.scene.layout Background:
 *         'Background background = new Background(<BackgroundFill object>);'
 *
 * Refactor:
 *     This should be refactored to use a case/switch logic block to match wanted color to Color.<color>. This would significantly reduce the LOC.
 *     ex implementation:
 *     public static getBackgroundColor(String color) {
 *          if (color.equals("green")) {
 *             // set backgroundfill
 *          ...
 *          ...
 *          else {
 *             // set backgroundfill default
 *          }
 *          // wrap in background & return
 *      }
 */
public class BackgroundColors {
    /*
     * Method:
     *     getDefaultBackground
     *
     * Purpose:
     *     Return lightgray colored javafx.scene.layout background object
     *
     * @param none
     * @return Background
     */
    public static Background getDefaultBackground() {
        BackgroundFill defaultBackgroundFill = new BackgroundFill(
                Color.LIGHTGRAY,
                CornerRadii.EMPTY,
                Insets.EMPTY
        );
        Background defaultBackground = new Background(
                defaultBackgroundFill
        );
        return defaultBackground;
    }

    /*
     * Method:
     *     getGrayBackground
     *
     * Purpose:
     *     Return gray colored javafx.scene.layout background object
     *
     * @param none
     * @return Background
     */
    public static Background getGrayBackground() {
        BackgroundFill grayBackgroundFill = new BackgroundFill(
                Color.GRAY,
                CornerRadii.EMPTY,
                Insets.EMPTY
        );
        Background grayBackground = new Background(
                grayBackgroundFill
        );
        return grayBackground;
    }

    /*
     * Method:
     *     getYellowBackground
     *
     * Purpose:
     *     Return yellow colored javafx.scene.layout background object
     *
     * @param none
     * @return Background
     */
    public static Background getYellowBackground() {
        BackgroundFill yellowBackgroundFill = new BackgroundFill(
                Color.YELLOW,
                CornerRadii.EMPTY,
                Insets.EMPTY
        );
        Background yellowBackground = new Background(
                yellowBackgroundFill
        );
        return yellowBackground;
    }

    /*
     * Method:
     *     getGreenBackground
     *
     * Purpose:
     *     Return green colored javafx.scene.layout background object
     *
     * @param none
     * @return Background
     */
    public static Background getGreenBackground() {
        BackgroundFill greenBackgroundFill = new BackgroundFill(
                Color.GREEN,
                CornerRadii.EMPTY,
                Insets.EMPTY
        );
        Background greenBackground = new Background(
                greenBackgroundFill
        );
        return greenBackground;
    }

    /*
     * Method:
     *     getCurrentIndexBackground
     *
     * Purpose:
     *     Return white colored javafx.scene.layout background object
     *
     * @param none
     * @return Background
     */
    public static Background getCurrentIndexBackground() {
        BackgroundFill currentIndexBackgroundFill = new BackgroundFill(
                Color.WHITE,
                CornerRadii.EMPTY,
                Insets.EMPTY
        );
        Background currentIndexBackground = new Background(
                currentIndexBackgroundFill
        );
        return currentIndexBackground;
    }

    /*
     * Method:
     *     getRedBackground
     *
     * Purpose:
     *     Return red colored javafx.scene.layout background object
     *
     * @param none
     * @return Background
     */
    public static Background getRedBackground() {
        BackgroundFill redBackgroundFill = new BackgroundFill(
                Color.RED,
                CornerRadii.EMPTY,
                Insets.EMPTY
        );
        Background redBackground = new Background(
                redBackgroundFill
        );
        return redBackground;
    }
}
