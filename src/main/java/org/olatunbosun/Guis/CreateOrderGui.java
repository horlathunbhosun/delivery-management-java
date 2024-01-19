package org.olatunbosun.Guis;

import com.toedter.calendar.JDateChooser;
import org.olatunbosun.Utility;
import org.olatunbosun.controllers.OrderController;
import org.olatunbosun.controllers.ProductController;
import org.olatunbosun.models.CreateOrder;
import org.olatunbosun.models.CreateOrderItem;
import org.olatunbosun.session.SessionManager;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;

public class CreateOrderGui extends JFrame implements ActionListener {

    private final JPanel firstSection;
    private final JPanel secondSection;


    JTextField quantity;
    JTextArea  address;
    JLabel productsLabel, quantityLabel, addressLabel,deliveryDateLabel ;


    JComboBox<String> products;

    JDateChooser dateChooser;

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

//        String[] productList = { "Rice", "Beans", "Garri" };

        Map<Integer, String> productMap =  ProductController.fetchProductDataFromDatabase();

        // Convert the map entries into arrays for JComboBox
//        Integer[] productIds = productMap.keySet().toArray(new Integer[0]);
        String[] productNames = productMap.values().toArray(new String[0]);


        products = new JComboBox<>(productNames);

        // Set a custom renderer for the JComboBox


        System.out.println(products.getSelectedItem());
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


    private JPanel createFormSecondSectionPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        // Add form components to the panel
        addressLabel = new JLabel("Address: ");
        address = new JTextArea();
        address.setSize(100, 100);

        deliveryDateLabel = new JLabel("Delivery Date: ");

         dateChooser = new JDateChooser();

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
        if (e.getSource() == orderButton) {
            boolean isValid  =  validation();
            if (!isValid) {
                return;
            }
            int productId = ProductController.getProductByProductName(String.valueOf(products.getSelectedItem()));
            if (productId == 0) {
                JOptionPane.showMessageDialog(this, "Product not found", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            CreateOrderItem createOrderItem = new CreateOrderItem(productId, Integer.parseInt(quantity.getText()));
            // generate order id randomly
            String orderNumber = Utility.generateOrderNumber();

            String date = Utility.getDateFormatted(dateChooser.getDate());

            CreateOrder createOrder = new CreateOrder(orderNumber,address.getText(),  date,"order_placed", SessionManager.getSession("userInfo").getUserId(), createOrderItem);

            System.out.println(createOrder);

            String responseMessage = OrderController.insertCreateOrder(createOrder);
           // Check the result and show appropriate message
            if (responseMessage.equals("Order created successfully")) {
                JOptionPane.showMessageDialog(null, responseMessage, "Success", JOptionPane.INFORMATION_MESSAGE);
                new ListOrderGui();
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Error: " + responseMessage, "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }


    public boolean validation() {
        boolean isValid = true;
        StringBuilder errorMessage = new StringBuilder();

        if (quantity.getText().isEmpty() || address.getText().isEmpty() || dateChooser.getDate() == null || products.getSelectedItem() == null) {
            errorMessage.append("All Fields Are Required\n");
            isValid = false;
        }

        if (products.getSelectedItem() == null) {
            errorMessage.append("You need to select a product\n");
            isValid = false;
        }

        if (address.getText().isEmpty()) {
            errorMessage.append("Address is required\n");
            isValid = false;
        }

        if (quantity.getText().isEmpty()) {
            errorMessage.append("Quantity is required\n");
            isValid = false;
        }

        if (dateChooser.getDate() == null) {
            errorMessage.append("Date is required\n");
            isValid = false;
        }

        if (!Utility.isNumeric(quantity.getText())) {
            errorMessage.append("Quantity must be a number\n");
            isValid = false;
        }


        if (dateChooser.getDate().before(Utility.getTodayDate())) {
            errorMessage.append("You need to choose a date after today\n");
            isValid = false;
        }

        // Display the first error encountered, if any
        if (!isValid) {
            JOptionPane.showMessageDialog(this, errorMessage.toString().trim(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return isValid;

    }

}
