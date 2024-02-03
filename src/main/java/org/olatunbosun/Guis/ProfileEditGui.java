package org.olatunbosun.Guis;

import org.olatunbosun.Utility;
import org.olatunbosun.controllers.UserController;
import org.olatunbosun.models.UserUpdateInformation;
import org.olatunbosun.session.SessionData;
import org.olatunbosun.session.SessionManager;
import org.olatunbosun.session.SessionManagerMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.*;

public class ProfileEditGui extends JFrame  implements ActionListener {


    JTextField fullName,email, phoneNumber, truckNumber, truckCapacity;
    JPasswordField passwordField;
    JLabel fullNameLabel, emailLabel, passwordLabel,phoneNumberLabel, roleFieldLabel, truckNumberLabel, truckCapacityLabel, loginButtonLabel, titleLabel;


    JComboBox<String> roles;

    JButton updateProfileButton;

    public ProfileEditGui(){
        super("Edit Page");

        // Create an instance of the Menu class
        MenuGui menu = new MenuGui();

        //add session manager to the frame
        SessionData sessionData = SessionManagerMain.loadUserFromFile();

        Utility.checkSessionAndHandleExpiration(this, sessionData);

        // Add the components to the frame
        JPanel contentPane = new JPanel();


        // Create the text fields
        fullNameLabel = new JLabel("FullName:");
        fullNameLabel.setBounds(10, 50, 100, 50);


        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 100, 120, 50);


        phoneNumberLabel = new JLabel("Phone Number: ");
        phoneNumberLabel.setBounds(10, 150, 160, 50);



        roleFieldLabel = new JLabel("Role: ");
        roleFieldLabel.setBounds(10, 200, 170, 50);



        truckNumberLabel = new JLabel("Truck Number: ");
        truckNumberLabel.setBounds(10, 250, 170, 50);
        truckNumberLabel.setVisible(false);


        truckCapacityLabel = new JLabel("Truck Capacity: ");
        truckCapacityLabel.setBounds(10, 300, 170, 50);
        truckCapacityLabel.setVisible(false);


        contentPane.add(fullNameLabel);
        contentPane.add(emailLabel);
        contentPane.add(phoneNumberLabel);
        contentPane.add(roleFieldLabel);
        contentPane.add(truckNumberLabel);
        contentPane.add(truckCapacityLabel);


        fullName = new JTextField();
        fullName.setBounds(120, 50, 250, 50);
        fullName.setText(sessionData.getFullName());


        email = new JTextField();
        email.setBounds(120, 100, 250, 50);
        email.setText(sessionData.getEmail());

        phoneNumber = new JTextField();
        phoneNumber.setBounds(120, 150, 250, 50);
        phoneNumber.setText(sessionData.getPhoneNumber());
        String[] rolesList = { "customer", "scheduler", "driver" };

        roles = new JComboBox<>(rolesList);
        roles.setBounds(120, 200, 250, 50);
        System.out.println(sessionData.getRole());
        roles.setSelectedItem(sessionData.getRole());
        roles.setEnabled(false);

        // Set a custom renderer to display the selected item
        roles.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setText(value.toString());
                return this;
            }
        });

        truckNumber = new JTextField();
        truckNumber.setBounds(120, 250, 250, 50);
        truckNumber.setVisible(false);

        truckCapacity = new JTextField();
        truckCapacity.setBounds(120, 300, 250, 50);
        truckCapacity.setVisible(false);


        if (sessionData.getRole().equals("driver")) {
            truckNumberLabel.setVisible(true);
            truckNumber = new JTextField();
            truckNumber.setBounds(120, 250, 250, 50);
            truckNumber.setText(sessionData.getTruckNumber());
            truckNumber.setVisible(true);
//            truckNumber.setEditable(false);

            truckCapacityLabel.setVisible(true);
            truckCapacity = new JTextField();
            truckCapacity.setBounds(120, 300, 250, 50);
            truckCapacity.setText(String.valueOf(sessionData.getTruckCapacity()));
            truckCapacity.setVisible(true);
//            truckCapacity.setEditable(false);
            contentPane.add(truckNumber);
            contentPane.add(truckCapacity);
        }






        ImageIcon icon = new ImageIcon("src/main/resources/images/arrow-right-to-bracket_1.png");
        // Set the desired size for the icon
        int iconWidth = 20;
        int iconHeight = 20;
        // Create a new ImageIcon with the desired size
        ImageIcon sizedIcon = new ImageIcon(Utility.getScaledImage(icon.getImage(), iconWidth, iconHeight));
        System.out.println(icon);
        updateProfileButton = new JButton("Update Profile",sizedIcon);
        updateProfileButton.setBackground(new Color(0, 158, 15));
        updateProfileButton.setOpaque(true);
        updateProfileButton.setBorderPainted(false);
        updateProfileButton.setBounds(120, 350, 150, 40);
        updateProfileButton.addActionListener(this);

        contentPane.add(fullName);
        contentPane.add(email);
        contentPane.add(phoneNumber);
        contentPane.add(roles);
        contentPane.add(truckNumber);
        contentPane.add(truckCapacity);
        contentPane.add(updateProfileButton);

        contentPane.setBackground(new Color(159, 167, 192));

//        contentPane.set(menu)


        menu.setBackground(new Color(159, 167, 192));

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
        if (e.getSource() == updateProfileButton) {

            UserUpdateInformation userUpdateInformation = new UserUpdateInformation(
                    fullName.getText(),
                    email.getText(),
                    phoneNumber.getText(),
                    roles.getSelectedItem().toString(),
                    truckNumber.getText(),
                    truckCapacity.getText()
            );

            SessionData sessionData = SessionManagerMain.loadUserFromFile();
            Utility.checkSessionAndHandleExpiration(this, sessionData);

            String response = UserController.updateData(userUpdateInformation, sessionData.getUserId());

            // Check the result and show appropriate message
            if (response.equals("Update Successful")) {
                JOptionPane.showMessageDialog(this, response, "Success", JOptionPane.INFORMATION_MESSAGE);
                new ProfileGui();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,  response, "Error", JOptionPane.ERROR_MESSAGE);
            }
            System.out.println(userUpdateInformation);
        }
    }
}
