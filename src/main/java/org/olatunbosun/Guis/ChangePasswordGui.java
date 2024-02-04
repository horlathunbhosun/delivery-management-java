package org.olatunbosun.Guis;

import org.olatunbosun.Utility;
import org.olatunbosun.controllers.UserController;
import org.olatunbosun.session.SessionData;
import org.olatunbosun.session.SessionManagerMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.*;

public class ChangePasswordGui extends JFrame implements ActionListener {


    JPasswordField passwordField, oldPasswordField;

    JLabel  oldPasswordFieldLabel, passwordLabel;



    JButton changePasswordButton;

    SessionData sessionData = SessionManagerMain.loadUserFromFile();

    public ChangePasswordGui(){
        super("Change Password Page");

        // Add the components to the frame
        JPanel contentPane = new JPanel();

        oldPasswordFieldLabel = new JLabel("Old Password:");
        oldPasswordFieldLabel.setBounds(10, 100, 120, 50);

        passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(10, 150, 140, 50);


        contentPane.add(oldPasswordFieldLabel);
        contentPane.add(passwordLabel);



        oldPasswordField = new JPasswordField();
        oldPasswordField.setBounds(120, 100, 400, 50);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 150, 400, 50);


        changePasswordButton = new JButton("Change Password");
        changePasswordButton.setBackground(new Color(0, 158, 15));
        changePasswordButton.setOpaque(true);
        changePasswordButton.setBorderPainted(false);
        changePasswordButton.setBounds(120, 230, 250, 40);
        changePasswordButton.addActionListener(this);


        contentPane.add(oldPasswordField);
        contentPane.add(passwordField);

        contentPane.add(changePasswordButton);


        contentPane.setBackground(new Color(159, 167, 192));


        setContentPane(contentPane);

        contentPane.setLayout(null);

        setVisible(true);
        pack();
        setSize(800, 600);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == changePasswordButton) {
            boolean isValid  =  validation();
            if (!isValid) {
                return;
            }


            // Get the oldPassword and password
            String oldPasswordText = String.valueOf(oldPasswordField.getPassword());
            String passwordText = String.valueOf(passwordField.getPassword());

            // Call the login controller
            String response =  UserController.changePassword(sessionData.getUserId(),oldPasswordText, passwordText);

            // Check the result and show appropriate message
            if (response.equals("Password change successful")) {
                // Show success message
                JOptionPane.showMessageDialog(this, response, "Success", JOptionPane.INFORMATION_MESSAGE);

                String responseLogout =  SessionManagerMain.logoutUserWithOutDialog(this);
                if (responseLogout.equals("User successfully logged out.")) {
                    JOptionPane.showMessageDialog(this, responseLogout, "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    new LoginScreenGui();
                }else{
                    JOptionPane.showMessageDialog(this,  response, "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Show error message
                JOptionPane.showMessageDialog(this,  response, "Error", JOptionPane.ERROR_MESSAGE);
            }

        }

    }


    public boolean validation() {
        boolean isValid = true;
        StringBuilder errorMessage = new StringBuilder();

        if (String.valueOf(oldPasswordField.getPassword()).isEmpty() || String.valueOf(passwordField.getPassword()).isEmpty()) {
            errorMessage.append("All Fields Are Required\n");
            isValid = false;
        }

        //validate password is in the right format
        if (!Utility.validatePassword(String.valueOf(passwordField.getPassword()))) {
            errorMessage.append("Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one number and one special character\n");
            isValid = false;
        }
        // Display the first error encountered, if any
        if (!isValid) {
            JOptionPane.showMessageDialog(this, errorMessage.toString().trim(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return isValid;

    }
}
