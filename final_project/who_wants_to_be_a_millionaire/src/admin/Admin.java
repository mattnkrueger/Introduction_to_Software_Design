package admin;

import game.Game;
import utils.DBConnection;
import utils.HWTBAMMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Admin class for admin accessed GUI. Overlooks database and current players. NOT connected to server
 *
 * The refresh of the views are set by a simple Swing Timer, when executed (every 5 seconds), new sql queries are fired to obtain
 * The most recent data.
 */
public class Admin extends JFrame {
    private DBConnection conn;
    private JTextArea allTimeGameLog;
    private JTextArea currentlyPlayingTextArea;
    private JButton nukeAllButton;
    private JButton deletePlayerButton;
    private JTextField deletePlayerTextField;
    private AdminLogin adminLogin;
    private ExecutorService executor;
    private Timer timer;
    private JScrollPane scrollPaneAllTime;
    private JScrollPane scrollPaneCurrentlyPlaying;
    private JButton exitButton;

    public Admin() throws SQLException {
        adminLogin = new AdminLogin();
        conn = new DBConnection();

        // start login
        startLogin();

        // initialize timer & start
        timer = new Timer(1000, this::updateVisuals);
        timer.setRepeats(true);
        timer.start();

        // frame config
        setTitle("Admin");
        setSize(700, 500);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // layout
        // 2 x 2 grid
        // top - currenly playing & gamelog
        // scrollpane text areas
        allTimeGameLog = new JTextArea();
        scrollPaneAllTime = new JScrollPane(allTimeGameLog);
        scrollPaneAllTime.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneAllTime.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        currentlyPlayingTextArea = new JTextArea();
        scrollPaneCurrentlyPlaying = new JScrollPane(currentlyPlayingTextArea);
        scrollPaneCurrentlyPlaying.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneCurrentlyPlaying.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // bottom - delete buttons
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1,2,5,5));

        topPanel.add(scrollPaneAllTime, BorderLayout.WEST);
        topPanel.add(scrollPaneCurrentlyPlaying, BorderLayout.WEST);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1,2,5,5));

        JPanel bottomLeftPanel = new JPanel();
        bottomLeftPanel.setLayout(new GridLayout(1,2,5,5));

        // btns
        deletePlayerButton = new JButton();
        deletePlayerButton.addActionListener(new DeletePlayerButtonListener());
        deletePlayerButton.setText("Remove User (Disabled)");
        deletePlayerButton.setEnabled(false);
        deletePlayerTextField = new JTextField();
        deletePlayerTextField.setText("Disabled");
        deletePlayerTextField.setEnabled(false);
        deletePlayerTextField.setBackground(Color.LIGHT_GRAY);

        nukeAllButton = new JButton();
        nukeAllButton.setText("Clear Database (Disabled)");
        nukeAllButton.addActionListener(new NukeAllButtonListener());
        nukeAllButton.setEnabled(false);

        exitButton = new JButton("Exit");
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(Color.RED);
        ExitButtonHandler exitButtonHandler = new ExitButtonHandler();
        exitButton.addActionListener(exitButtonHandler);

        // add to panels
        bottomLeftPanel.add(deletePlayerButton, BorderLayout.WEST);
        bottomLeftPanel.add(deletePlayerTextField, BorderLayout.EAST);

        bottomPanel.add(nukeAllButton, BorderLayout.EAST);
        bottomPanel.add(bottomLeftPanel, BorderLayout.WEST);

        // add to layout
        add(exitButton, BorderLayout.NORTH);
        add(topPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        /**
         * Use swing worker to wait until login to finish constructor
         */
        SwingWorker<Void, Void> loginWorker = new SwingWorker<>() {
            /**
             * doInBackground sleeps thread (waits) until user logs in
             * @return nothing
             */
            @Override
            public Void doInBackground() {
                // Wait until the user has valid log in
                while (!adminLogin.isLoggedIn()) {
                    try {
                        Thread.sleep(500); //check login every half second
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }

            /**
             * done is called when user is logged in
             * continues with rest of game setup
             */
            @Override
            public void done() {
                //Make window visible
                setVisible(true);
            }
        };

        //Execute login worker
        executor = Executors.newCachedThreadPool();
        executor.execute(loginWorker);
    }

    private class NukeAllButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                conn.nukeDatabase();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private class DeletePlayerButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String username = deletePlayerTextField.getText();
            try {
                HWTBAMMessage message = conn.removeUser(username, "ADMIN");

                if (message.getResult().equals("failure")) {
                    System.out.println(message.getMessage());
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * timer action event to refresh admin sreen with recent information
     *
     * @param actionEvent
     */
    private void updateVisuals(ActionEvent actionEvent) {
        // if this frame is visible
        if (isVisible()) {
            String allTimeLeaderBoard;
            String currentlyPlaying;
            try {
                // get the current players and list ascending
                allTimeLeaderBoard = conn.getAllTimeLeaderBoard("desc").getMessage();
                currentlyPlaying = conn.getCurrentlyPlayingLeaderBoard("desc").getMessage();
            } catch (SQLException e) {
                System.out.println("SQL Error updating visuals");
                allTimeLeaderBoard = "";
                currentlyPlaying = "";
            }

            // parse & rebuid

            // clear
            allTimeGameLog.setText("");
            currentlyPlayingTextArea.setText("");

            // set new
            allTimeGameLog.append("------------ All Time Leaderboard ------------\n");
            allTimeGameLog.append("    User    |    Round    |   Date Completed \n");
            allTimeGameLog.append(allTimeLeaderBoard);

            currentlyPlayingTextArea.append("------------ Current Players ------------\n");
            currentlyPlayingTextArea.append("       User        |        Round        \n");
            currentlyPlayingTextArea.append(currentlyPlaying);
        }
    }

    private void startLogin() {
        adminLogin = new AdminLogin();
        adminLogin.setTitle("Admin Login");
        adminLogin.setSize(400, 100);
    }

    private class ExitButtonHandler implements ActionListener {
        /**
         * Overridden method that handles the action event triggered when exit button is clicked
         * closes connection
         *
         * @param actionEvent the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            // close all
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.exit(0);
        }
    }
}