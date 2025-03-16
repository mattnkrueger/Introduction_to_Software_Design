package admin;

import java.sql.SQLException;

/**
 * The Admin class is a class that creates a new admin GUI for the Who Wants To Be a Millionaire game.
 * This can be run regardless of connection. It is simply a visual for tracking/overseeing the database.
 */
public class AdminTest {
    /**
     * Method:
     *     main (entry-point)
     *
     * Purpose:
     *     This creates a new Admin GUI
     *
     * @param args: cli arguments when program started
     * @return none
     */
    public static void main(String[] args) throws SQLException {
        Admin admin = new Admin();
    }
}
