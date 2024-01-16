package org.olatunbosun.Guis;

import org.olatunbosun.Utility;
import org.olatunbosun.controllers.LoginController;
import org.olatunbosun.controllers.RegistrationController;
import org.olatunbosun.models.LoginModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginScreenGui extends JFrame implements ActionListener {

    JTextField email;
    JPasswordField passwordField;

    JLabel  emailLabel, passwordLabel,registrationButtonLabel;



    JButton loginButton;

    public LoginScreenGui(){
        super("Login Page");

        // Add the components to the frame
        JPanel contentPane = new JPanel();

        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 100, 120, 50);

        passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(10, 150, 140, 50);


        contentPane.add(emailLabel);
        contentPane.add(passwordLabel);



        email = new JTextField();
        email.setBounds(120, 100, 400, 50);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 150, 400, 50);



        ImageIcon icon = new ImageIcon("src/main/resources/images/arrow-right-to-bracket_1.png");
        // Set the desired size for the icon
        int iconWidth = 20;
        int iconHeight = 20;
        // Create a new ImageIcon with the desired size
        ImageIcon sizedIcon = new ImageIcon(Utility.getScaledImage(icon.getImage(), iconWidth, iconHeight));

        System.out.println(icon);
        loginButton = new JButton("Login",sizedIcon);
        loginButton.setBackground(new Color(0, 158, 15));
        loginButton.setOpaque(true);
        loginButton.setBorderPainted(false);
        loginButton.setBounds(120, 230, 150, 40);
        loginButton.addActionListener(this);

        registrationButtonLabel = Utility.createClickableLinkLabel("If not registered? Sign up");
        registrationButtonLabel.setBounds(120, 300, 150, 40);

        registrationButtonLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                dispose();
                new RegistrationGui();
                dispose();
            }
        });

        contentPane.add(email);
        contentPane.add(passwordField);

        contentPane.add(loginButton);
        contentPane.add(registrationButtonLabel);


        setContentPane(contentPane);

        contentPane.setLayout(null);

        setVisible(true);
        pack();
        setSize(600, 600);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginButton) {
            boolean isValid = validation();

            // Check if the validation passed
            if (!isValid) {
                return;
            }

            // Get the email and password
            String emailText = email.getText();
            String passwordText = String.valueOf(passwordField.getPassword());

            // Create a login model object
            LoginModel loginModel = new LoginModel(emailText, passwordText);

            // Call the login controller
            String response =  LoginController.Login(loginModel);

            // Check the result and show appropriate message
            if (response.equals("Login Successful")) {
                // Show success message
                JOptionPane.showMessageDialog(null, response, "Success", JOptionPane.INFORMATION_MESSAGE);
                // Go to the next screen
                new CreateOrderGui();
                // Close the current screen
                dispose();
            } else {
                // Show error message
                JOptionPane.showMessageDialog(null, "Error: " + response, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }



    public boolean validation() {
        boolean isValid = true;
        StringBuilder errorMessage = new StringBuilder();

        if (email.getText().isEmpty() || String.valueOf(passwordField.getPassword()).isEmpty()) {
            errorMessage.append("All Fields Are Required\n");
            isValid = false;
        }

        //validate email is in the right format
        if (!Utility.validateEmail(email.getText())) {
            errorMessage.append("Email is not in the right format\n");
            isValid = false;
        }

        // Display the first error encountered, if any
        if (!isValid) {
            JOptionPane.showMessageDialog(this, errorMessage.toString().trim(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return isValid;

    }
}
