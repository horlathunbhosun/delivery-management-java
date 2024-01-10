package org.olatunbosun.Guis;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.olatunbosun.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Properties;

public class CreateOrder extends JFrame {


    JTextField quantity;
    JTextArea  address;
    JPasswordField passwordField;
    JLabel productsLabel, quantityLabel, addressLabel,deliveryDateLabel ;


    JComboBox<String> products;

    JButton addMore, order;

    public CreateOrder(){
        super("Create Order Page");

        // Add the components to the frame
        JPanel contentPane = new JPanel();




        // Create the text fields
        productsLabel = new JLabel("Products:");
        productsLabel.setBounds(10, 50, 100, 50);


        quantityLabel = new JLabel("Quanity:");
        quantityLabel.setBounds(10, 100, 120, 50);

        addressLabel = new JLabel("Address: ");
        addressLabel.setBounds(10, 150, 140, 50);

        deliveryDateLabel = new JLabel("Delivery Date: ");
        deliveryDateLabel.setBounds(10, 250, 160, 50);


        contentPane.add(productsLabel);
        contentPane.add(quantityLabel);
        contentPane.add(addressLabel);
        contentPane.add(deliveryDateLabel);

//
//        fullName = new JTextField();
//        fullName.setBounds(120, 50, 250, 50);


        String[] productList = { "Rice", "Beans", "Garri" };

        products = new JComboBox<>(productList);
        products.setBounds(120, 50, 250, 50);


        quantity = new JTextField();
        quantity.setBounds(120, 100, 250, 50);

        address = new JTextArea();
        address.setBounds(120, 150, 250, 100);


//         Create a date model
//        UtilDateModel model = new UtilDateModel();
//        Properties properties = new Properties();
//        JDatePicker datePicker;
//        datePicker = new JDatePicker(model);
//
//        //create a date picker
//        JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);



//        UtilDateModel model = new UtilDateModel();
//        JDatePanelImpl datePanel = new JDatePanelImpl(model, '');
//        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel,"");
//
//        phoneNumber = new JTextField();
//        phoneNumber.setBounds(120, 200, 250, 50);
//

//
//        truckNumber = new JTextField();
//        truckNumber.setBounds(120, 300, 250, 50);
//        truckNumber.setVisible(false);
//
//        truckCapacity = new JTextField();
//        truckCapacity.setBounds(120, 350, 250, 50);
//        truckCapacity.setVisible(false);


//        roles.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//                if ("Driver".equals(roles.getSelectedItem())) {
//                    truckNumberLabel.setVisible(true);
//                    truckCapacityLabel.setVisible(true);
//                    truckNumber.setVisible(true);
//                    truckCapacity.setVisible(true);
//                } else {
//                    truckNumberLabel.setVisible(false);
//                    truckCapacityLabel.setVisible(false);
//                    truckCapacity.setVisible(false);
//                    truckNumber.setVisible(false);
//                }
//            }
//
//        });



//        ImageIcon icon = new ImageIcon("src/main/resources/images/arrow-right-to-bracket_1.png");
//        // Set the desired size for the icon
//        int iconWidth = 20;
//        int iconHeight = 20;
//        // Create a new ImageIcon with the desired size
//        ImageIcon sizedIcon = new ImageIcon(Utility.getScaledImage(icon.getImage(), iconWidth, iconHeight));

//        System.out.println(icon);
        order = new JButton("Order");
        order.setBackground(new Color(240, 95, 5));
        order.setOpaque(true);
        order.setBorderPainted(false);
        order.setBounds(120, 400, 150, 40);




        contentPane.add(products);
        contentPane.add(quantity);
        contentPane.add(address);
        contentPane.add(order);




        setContentPane(contentPane);

        contentPane.setLayout(null);

        setVisible(true);
        pack();
        setSize(400, 600);
    }
}
