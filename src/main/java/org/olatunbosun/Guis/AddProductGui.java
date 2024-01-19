package org.olatunbosun.Guis;

import org.olatunbosun.Utility;
import org.olatunbosun.controllers.ProductController;
import org.olatunbosun.models.Products;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddProductGui extends JFrame implements ActionListener {


    JTextField productName;
    JLabel productNameLabel;

    JButton addProductButton;

    public AddProductGui() {
        super("Add Product Page");

        // Add the components to the frame
        JPanel contentPane = new JPanel();

        //add menu bar
        MenuGui menuGui = new MenuGui();

        // Create the text fields
        productNameLabel = new JLabel("Product Name:");
        productNameLabel.setBounds(10, 50, 100, 50);



        contentPane.add(productNameLabel);




        productName = new JTextField();
        productName.setBounds(120, 50, 400, 50);




        addProductButton = new JButton("Add Product");
        Font boldFont = new Font(addProductButton.getFont().getFamily(), Font.BOLD, addProductButton.getFont().getSize());
        addProductButton.setFont(boldFont);
        addProductButton.setBackground(new Color(0, 158, 15));
        addProductButton.setOpaque(true);
        addProductButton.setBorderPainted(false);
        addProductButton.setBounds(120, 100, 150, 40);
        addProductButton.addActionListener(this);


        contentPane.add(productName);

        contentPane.add(addProductButton);


        setContentPane(contentPane);

        setJMenuBar(menuGui);
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

        if (e.getSource() == addProductButton) {
            boolean isValid  =  validation();
            if (!isValid) {
                return;
            }

            Products products = new Products(productName.getText());
           String response = ProductController.insertDataProduct(products);
            // Check the result and show appropriate message
            if (response.equals("Product Added Successful")) {
                JOptionPane.showMessageDialog(null, response, "Success", JOptionPane.INFORMATION_MESSAGE);
                new ListProductsGui();
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Error: " + response, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }



    public boolean validation() {
        boolean isValid = true;
        StringBuilder errorMessage = new StringBuilder();

        if (productName.getText().isEmpty()) {
            errorMessage.append("All Fields Are Required\n");
            isValid = false;
        }
        // Display the first error encountered, if any
        if (!isValid) {
            JOptionPane.showMessageDialog(this, errorMessage.toString().trim(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return isValid;

    }
}
