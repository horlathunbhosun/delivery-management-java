package org.olatunbosun.Guis;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class CreateOrderGui extends JFrame implements ActionListener {

    private final JPanel firstSection;
    private final JPanel secondSection;


    JTextField quantity;
    JTextArea  address;
    JLabel productsLabel, quantityLabel, addressLabel,deliveryDateLabel ;


    JComboBox<String> products;

    JButton addMore, orderButton;



    public CreateOrderGui(){
        setTitle("Create Order");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        firstSection = createFormFirstSectionPanel();
        secondSection = createFormSecondSectionPanel();

        orderButton = new JButton("Order");
        orderButton.setBackground(new Color(255, 165, 0));
        orderButton.setOpaque(true);
        orderButton.setBorderPainted(false);
        orderButton.setBounds(120, 230, 150, 40);
        orderButton.addActionListener(this);

        MenuGui menu = new MenuGui();


        JPanel secondSectionContainer = new JPanel(new BorderLayout());
        secondSectionContainer.add(secondSection, BorderLayout.CENTER);

        mainPanel.add(firstSection, BorderLayout.PAGE_START);
        mainPanel.add(secondSectionContainer, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(orderButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 10, 200, 10)); // Add some padding

        add(mainPanel);
        setJMenuBar(menu);
        setVisible(true);
        setSize(600, 700);

    }


    private JPanel createFormFirstSectionPanel() {
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
        Border border = BorderFactory.createTitledBorder("Products");
        panel.setBorder(border);

        return panel;

    }


//    private JPanel createFormSecondSectionPanel() {
//        JPanel panel = new JPanel(new GridBagLayout());
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(5, 5, 5, 5); // Adjust these insets to control the spacing
//
//        // Add form components to the panel
//        addressLabel = new JLabel("Address: ");
//        address = new JTextArea();
//        address.setPreferredSize(new Dimension(200, 100)); // Set a preferred size for JTextArea
//
//        deliveryDateLabel = new JLabel("Delivery Date: ");
//        JDateChooser dateChooser = new JDateChooser();
//        dateChooser.setDateFormatString("yyyy-MM-dd");
//
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        panel.add(addressLabel, gbc);
//
//        gbc.gridx = 1;
//        gbc.gridy = 0;
//        panel.add(address, gbc);
//
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//        panel.add(deliveryDateLabel, gbc);
//
//        gbc.gridx = 1;
//        gbc.gridy = 1;
//        panel.add(dateChooser, gbc);
//
//        // Add an empty border to the panel to create spacing around the components
//        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//
//        return panel;
//    }



    private JPanel createFormSecondSectionPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        // Add form components to the panel
        addressLabel = new JLabel("Address: ");
        address = new JTextArea();
        address.setSize(100, 100);

        deliveryDateLabel = new JLabel("Delivery Date: ");

        JDateChooser dateChooser = new JDateChooser();

        // Set date format (optional)
        dateChooser.setDateFormatString("yyyy-MM-dd");

//        quantity = new JTextField();

        panel.add(addressLabel);
        panel.add(address);
        panel.add(deliveryDateLabel);
        panel.add(dateChooser);

        // Add a titled border to the panel
        Border border = BorderFactory.createTitledBorder("Delivery Information");
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
