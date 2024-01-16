package org.olatunbosun.Guis;

import org.olatunbosun.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.*;

public class ProfileEditGui extends JFrame  implements ActionListener {


    JTextField fullName,email, phoneNumber, truckNumber, truckCapacity;
    JPasswordField passwordField;
    JLabel fullNameLabel, emailLabel, passwordLabel,phoneNumberLabel, roleFieldLabel, truckNumberLabel, truckCapacityLabel, loginButtonLabel, titleLabel;


    JComboBox<String> roles;

    JButton registerButton;

    public ProfileEditGui(){
        super("Edit Page");

        // Create an instance of the Menu class
        MenuGui menu = new MenuGui();


        // Add the components to the frame
        JPanel contentPane = new JPanel();


        // Create the text fields
        fullNameLabel = new JLabel("FullName:");
        fullNameLabel.setBounds(10, 50, 100, 50);


        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 100, 120, 50);

        passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(10, 150, 140, 50);

        phoneNumberLabel = new JLabel("Phone Number: ");
        phoneNumberLabel.setBounds(10, 200, 160, 50);



        roleFieldLabel = new JLabel("Role: ");
        roleFieldLabel.setBounds(10, 250, 170, 50);



        truckNumberLabel = new JLabel("Truck Number: ");
        truckNumberLabel.setBounds(10, 300, 170, 50);
        truckNumberLabel.setVisible(false);


        truckCapacityLabel = new JLabel("Truck Capacity(kg): ");
        truckCapacityLabel.setBounds(10, 350, 170, 50);
        truckCapacityLabel.setVisible(false);


        contentPane.add(fullNameLabel);
        contentPane.add(emailLabel);
        contentPane.add(passwordLabel);
        contentPane.add(phoneNumberLabel);
        contentPane.add(roleFieldLabel);
        contentPane.add(truckNumberLabel);
        contentPane.add(truckCapacityLabel);


        fullName = new JTextField();
        fullName.setBounds(120, 50, 250, 50);


        email = new JTextField();
        email.setBounds(120, 100, 250, 50);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 150, 250, 50);


        phoneNumber = new JTextField();
        phoneNumber.setBounds(120, 200, 250, 50);

        String[] rolesList = { "Customer", "Scheduler", "Driver" };


        truckNumber = new JTextField();
        truckNumber.setBounds(120, 300, 250, 50);
        truckNumber.setVisible(false);

        truckCapacity = new JTextField();
        truckCapacity.setBounds(120, 350, 250, 50);
        truckCapacity.setVisible(false);

        roles = new JComboBox<>(rolesList);
        roles.setBounds(120, 250, 250, 50);
        roles.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if ("Driver".equals(roles.getSelectedItem())) {
                    truckNumberLabel.setVisible(true);
                    truckCapacityLabel.setVisible(true);
                    truckNumber.setVisible(true);
                    truckCapacity.setVisible(true);
                } else {
                    truckNumberLabel.setVisible(false);
                    truckCapacityLabel.setVisible(false);
                    truckCapacity.setVisible(false);
                    truckNumber.setVisible(false);
                }
            }

        });



        ImageIcon icon = new ImageIcon("src/main/resources/images/arrow-right-to-bracket_1.png");
        // Set the desired size for the icon
        int iconWidth = 20;
        int iconHeight = 20;
        // Create a new ImageIcon with the desired size
        ImageIcon sizedIcon = new ImageIcon(Utility.getScaledImage(icon.getImage(), iconWidth, iconHeight));

        System.out.println(icon);
        registerButton = new JButton("Update Profile",sizedIcon);
        registerButton.setBackground(new Color(0, 158, 15));
        registerButton.setOpaque(true);
        registerButton.setBorderPainted(false);
        registerButton.setBounds(120, 400, 150, 40);



        contentPane.add(fullName);
        contentPane.add(email);
        contentPane.add(passwordField);
        contentPane.add(phoneNumber);
        contentPane.add(roles);
        contentPane.add(truckNumber);
        contentPane.add(truckCapacity);
        contentPane.add(registerButton);


//        contentPane.set(menu)



        setContentPane(contentPane);
        contentPane.setLayout(null);
        setJMenuBar(menu);

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

    }
}
