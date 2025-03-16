/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

package gui.utils;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

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
public class BackgroundColors {
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
     *
     *
     * Purpose:
     *
     *
     * @param
     * @return
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
     *
     *
     * Purpose:
     *
     *
     * @param
     * @return
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
     *
     *
     * Purpose:
     *
     *
     * @param
     * @return
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
     *
     *
     * Purpose:
     *
     *
     * @param
     * @return
     */
    public static Background getRedBackground() {
        BackgroundFill redBackgroundFill = new BackgroundFill(
                Color.INDIANRED,
                CornerRadii.EMPTY,
                Insets.EMPTY
        );
        Background redBackground = new Background(
                redBackgroundFill
        );
        return redBackground;
    }
}
