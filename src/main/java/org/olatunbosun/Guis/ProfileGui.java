package org.olatunbosun.Guis;

import org.olatunbosun.Utility;
import org.olatunbosun.session.SessionData;
import org.olatunbosun.session.SessionManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ProfileGui extends JFrame  implements ActionListener {
    JTextField fullName,email, phoneNumber, truckNumber, truckCapacity;
    JPasswordField passwordField;
    JLabel fullNameLabel, emailLabel, passwordLabel,phoneNumberLabel, roleFieldLabel, truckNumberLabel, truckCapacityLabel, loginButtonLabel, titleLabel;


    JComboBox<String> roles;

    JButton registerButton;

    public ProfileGui(){
        super("Edit Page");




        // Create an instance of the Menu class
        MenuGui menu = new MenuGui();


        // Add the components to the frame
        JPanel contentPane = new JPanel();
        //add session manager to the frame
        SessionData sessionData = SessionManager.getSession("userInfo");



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
        fullName.setText(sessionData.getFullName());
        fullName.setEditable(false);

        email = new JTextField();
        email.setBounds(120, 100, 250, 50);
        email.setText(sessionData.getEmail());
        email.setEditable(false);


        phoneNumber = new JTextField();
        phoneNumber.setBounds(120, 200, 250, 50);
        phoneNumber.setText(sessionData.getPhoneNumber());
        phoneNumber.setEditable(false);
        String[] rolesList = { "Customer", "Scheduler", "Driver" };


        truckNumber = new JTextField();
        truckNumber.setBounds(120, 300, 250, 50);
        truckNumber.setVisible(false);

        truckCapacity = new JTextField();
        truckCapacity.setBounds(120, 350, 250, 50);
        truckCapacity.setVisible(false);

        roles = new JComboBox<>(rolesList);
        roles.setBounds(120, 250, 250, 50);
        roles.setSelectedItem(sessionData.getRole());
        roles.setEditable(false);
        if (roles.getSelectedItem() == "Driver") {
            truckNumberLabel.setVisible(true);
            truckCapacityLabel.setVisible(true);
            truckCapacity.setVisible(true);
            truckNumber.setVisible(true);
            truckNumber.setText(sessionData.getTruckNumber());
            truckCapacity.setText(sessionData.getTruckCapacity());
        }

        contentPane.add(fullName);
        contentPane.add(email);
        contentPane.add(passwordField);
        contentPane.add(phoneNumber);
        contentPane.add(roles);
        contentPane.add(truckNumber);
        contentPane.add(truckCapacity);


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


    // Helper method to scale an Image
//    private static Image getScaledImage(Image srcImg, int width, int height) {
//        BufferedImage resizedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
//        Graphics2D g2d = resizedImg.createGraphics();
//
//        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//        g2d.drawImage(srcImg, 0, 0, width, height, null);
//        g2d.dispose();
//
//        return resizedImg;
//    }



}
