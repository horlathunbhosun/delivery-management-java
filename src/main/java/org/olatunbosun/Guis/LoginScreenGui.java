package org.olatunbosun.Guis;

import org.olatunbosun.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginScreenGui extends JFrame  {

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
        setSize(400, 600);
    }
}
