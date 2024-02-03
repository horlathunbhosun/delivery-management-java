package org.olatunbosun.Guis;

import org.olatunbosun.Utility;
import org.olatunbosun.session.SessionData;
import org.olatunbosun.session.SessionManager;
import org.olatunbosun.session.SessionManagerMain;

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
        super("Profile Page");

        // Create an instance of the Menu class
        MenuGui menu = new MenuGui();
        // Add the components to the frame
        JPanel contentPane = new JPanel();
        //add session manager to the frame
//        SessionData sessionData = SessionManager.getSession("userInfo");
        SessionData sessionData = SessionManagerMain.loadUserFromFile();

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
        fullName.setEditable(false);

        email = new JTextField();
        email.setBounds(120, 100, 250, 50);
        email.setText(sessionData.getEmail());
        email.setEditable(false);


        phoneNumber = new JTextField();
        phoneNumber.setBounds(120, 150, 250, 50);
        phoneNumber.setText(sessionData.getPhoneNumber());
        phoneNumber.setEditable(false);
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


        if (sessionData.getRole().equals("driver")) {
            truckNumberLabel.setVisible(true);
            truckNumber = new JTextField();
            truckNumber.setBounds(120, 250, 250, 50);
            truckNumber.setText(sessionData.getTruckNumber());
            truckNumber.setVisible(true);
            truckNumber.setEditable(false);

            truckCapacityLabel.setVisible(true);
            truckCapacity = new JTextField();
            truckCapacity.setBounds(120, 300, 250, 50);
            truckCapacity.setText(String.valueOf(sessionData.getTruckCapacity()));
            truckCapacity.setVisible(true);
            truckCapacity.setEditable(false);
            contentPane.add(truckNumber);
            contentPane.add(truckCapacity);
        }



        truckNumber = new JTextField();
        truckNumber.setBounds(120, 250, 250, 50);
        truckNumber.setVisible(false);

        truckCapacity = new JTextField();
        truckCapacity.setBounds(120, 250, 250, 50);
        truckCapacity.setVisible(false);



        contentPane.add(fullName);
        contentPane.add(email);
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
