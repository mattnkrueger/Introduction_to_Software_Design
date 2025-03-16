package admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminLogin extends JFrame {
    private boolean loggedIn;
    private JTextArea userInputArea;
    private JTextArea passInputArea;
    private JLabel infoText;

    public AdminLogin (){
        loggedIn = false;
        setSize(400, 100);

        setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(2,2,5,5));
        JLabel userLabel = new JLabel("Username: ");
        JLabel passLabel = new JLabel("Password: ");
        userInputArea = new JTextArea();
        passInputArea = new JTextArea();

        inputPanel.add(userLabel);
        inputPanel.add(userInputArea);
        inputPanel.add(passLabel);
        inputPanel.add(passInputArea);

        add(inputPanel, BorderLayout.CENTER);

        JPanel submitPanel = new JPanel(new GridLayout(1,2,0,0));
        JButton submitButton = new JButton("Submit");
        SubmissionHandler submissionHandler = new SubmissionHandler();
        submitButton.addActionListener(submissionHandler);
        infoText = new JLabel();
        infoText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        infoText.setFont(new Font("Serif", Font.PLAIN, 12));
        submitPanel.add(submitButton);
        submitPanel.add(infoText);

        add(submitPanel,BorderLayout.SOUTH);


        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    private class SubmissionHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String submittedUser = userInputArea.getText();
            String submittedPass = passInputArea.getText();

            if (submittedUser.equals("a") && submittedPass.equals("a")) {
                loggedIn = true;
            } else {
                infoText.setText("Incorrect username or Password");
                userInputArea.setText("");
                passInputArea.setText("");
            }

            if (loggedIn){
                setVisible(false);
            }
        }
    }

    public Boolean isLoggedIn () {
        return loggedIn;
    }
}