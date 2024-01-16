package org.olatunbosun.Guis;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class CreateOrderGui extends JFrame implements ActionListener {

    private final JPanel firstSection;
    private final JPanel secondSection;
    private final JButton submitButton;


    JTextField quantity;
    JTextArea  address;
    JLabel productsLabel, quantityLabel, addressLabel,deliveryDateLabel ;


    JComboBox<String> products;

    JButton addMore, order;



    public CreateOrderGui(){
        setTitle("Two Section GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        firstSection = createFormPanel("First Section");
        secondSection = createFormPanel("Second Section");
        submitButton = new JButton("Submit");
        submitButton.setEnabled(false);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle submit button click
                JOptionPane.showMessageDialog(CreateOrderGui.this, "Form submitted!");
            }
        });

        MenuGui menu = new MenuGui();


        mainPanel.add(firstSection, BorderLayout.PAGE_START);
        mainPanel.add(secondSection, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);


        add(mainPanel);
        setJMenuBar(menu);
        setVisible(true);
        setSize(600, 700);

    }


    private JPanel createFormFirstSectionPanel(String sectionName) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        // Add form components to the panel
        productsLabel = new JLabel("Products:");

        String[] productList = { "Rice", "Beans", "Garri" };

        products = new JComboBox<>(productList);
        quantityLabel = new JLabel("Quanity:");
        quantity = new JTextField();

        panel.add(productsLabel);
        panel.add(products);
        panel.add(quantityLabel);
        panel.add(quantity);

        // Add a titled border to the panel
        Border border = BorderFactory.createTitledBorder(sectionName);
        panel.setBorder(border);

        return panel;

    }


    private JPanel createFormSecondSectionPanel(String sectionName) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        // Add form components to the panel
        addressLabel = new JLabel("Address: ");
        address = new JTextArea();

        deliveryDateLabel = new JLabel("Delivery Date: ");

//        quantity = new JTextField();

        panel.add(addressLabel);
        panel.add(address);
        panel.add(deliveryDateLabel);
        panel.add(quantity);

        // Add a titled border to the panel
        Border border = BorderFactory.createTitledBorder(sectionName);
        panel.setBorder(border);

        return panel;

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
