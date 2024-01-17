package org.olatunbosun.Guis;

import org.olatunbosun.Utility;
import org.olatunbosun.controllers.RegistrationController;
import org.olatunbosun.models.Registration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class RegistrationGui extends JFrame  implements ActionListener {
    JTextField fullName,email, phoneNumber, truckNumber, truckCapacity;
    JPasswordField passwordField;
    JLabel fullNameLabel, emailLabel, passwordLabel,phoneNumberLabel, roleFieldLabel, truckNumberLabel, truckCapacityLabel, loginButtonLabel, titleLabel;


    JComboBox<String> roles;

    JButton registerButton;

    public RegistrationGui(){
        super("Registration Page");

        // Add the components to the frame
        JPanel contentPane = new JPanel();




        // Create the text fields
         fullNameLabel = new JLabel("Fullname:");
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
        fullName.setBounds(120, 50, 400, 50);


        email = new JTextField();
        email.setBounds(120, 100, 400, 50);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 150, 400, 50);


        phoneNumber = new JTextField();
        phoneNumber.setBounds(120, 200, 400, 50);

        String[] rolesList = { "customer", "scheduler", "driver" };


        truckNumber = new JTextField();
        truckNumber.setBounds(120, 300, 400, 50);
        truckNumber.setVisible(false);

        truckCapacity = new JTextField();
        truckCapacity.setBounds(120, 350, 400, 50);
        truckCapacity.setVisible(false);

        roles = new JComboBox<>(rolesList);
        roles.setBounds(120, 250, 400, 50);
        roles.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if ("driver".equals(roles.getSelectedItem())) {
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

        registerButton = new JButton("Register",sizedIcon);
        Font boldFont = new Font(registerButton.getFont().getFamily(), Font.BOLD, registerButton.getFont().getSize());
        registerButton.setFont(boldFont);
        registerButton.setBackground(new Color(0, 158, 15));
        registerButton.setOpaque(true);
        registerButton.setBorderPainted(false);
        registerButton.setBounds(120, 400, 150, 40);
        registerButton.addActionListener(this);
        loginButtonLabel = Utility.createClickableLinkLabel("If registered? Sign in");
        loginButtonLabel.setBounds(120, 450, 150, 40);

        loginButtonLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new LoginScreenGui();
                dispose();
            }
        });

        contentPane.add(fullName);
        contentPane.add(email);
        contentPane.add(passwordField);
        contentPane.add(phoneNumber);
        contentPane.add(roles);
        contentPane.add(truckNumber);
        contentPane.add(truckCapacity);
        contentPane.add(registerButton);
        contentPane.add(loginButtonLabel);




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
        if (e.getSource() == registerButton) {
          boolean isValid  =  validation();
            if (!isValid) {
                return;
            }

            String role = roles.getSelectedItem().toString();
            if (role.equals("driver")) {
                role = "driver";
            } else if (role.equals("scheduler")) {
                role = "scheduler";
            } else {
                role = "customer";
            }

            String truckNum = truckNumber.getText();
            String truckCap = truckCapacity.getText();
            if (truckNum.isEmpty()) {
                truckNum = "null";
            }
            if (truckCap.isEmpty()) {
                truckCap = "null";
            }
            //pass the data to the registration model constructor
            Registration registration = new Registration(fullName.getText(), email.getText(), new String(passwordField.getPassword()), phoneNumber.getText(), role, truckNum, truckCap);
            System.out.println(registration);
            //save the data to the database
          String response =  RegistrationController.insertData(registration);

            // Check the result and show appropriate message
            if (response.equals("Registration Successful")) {
                JOptionPane.showMessageDialog(null, "Data inserted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                new LoginScreenGui();
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Error: " + response, "Error", JOptionPane.ERROR_MESSAGE);
            }

        }

    }


    public boolean validation() {
        boolean isValid = true;
        StringBuilder errorMessage = new StringBuilder();

        if (fullName.getText().isEmpty() || String.valueOf(passwordField.getPassword()).isEmpty() || email.getText().isEmpty() || phoneNumber.getText().isEmpty() || roles.getSelectedItem() == null) {
            errorMessage.append("All Fields Are Required\n");
            isValid = false;
        }

        if (roles.getSelectedItem().equals("Driver") && (truckNumber.getText().isEmpty() || truckCapacity.getText().isEmpty())) {
            errorMessage.append("You need to fill in the truck number and capacity\n");
            isValid = false;
        }

        //validate email is in the right format
        if (!Utility.validateEmail(email.getText())) {
            errorMessage.append("Email is not in the right format\n");
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
