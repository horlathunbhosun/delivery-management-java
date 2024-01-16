package org.olatunbosun.Guis;



import org.olatunbosun.session.SessionManager;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Properties;

public class CreateOrder extends JFrame {


    JTextField quantity;
    JTextArea  address;
    JLabel productsLabel, quantityLabel, addressLabel,deliveryDateLabel ;


    JComboBox<String> products;

    JButton addMore, order;

    public CreateOrder(){
        super("Create Order Page");

        System.out.println(SessionManager.getSession("userInfo"));
        // Add the components to the frame
        JPanel contentPane = new JPanel();

//        contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(2, 1)); // Two rows for two sections

//        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add some padding

        // Create section1Panel
        JPanel  section1Panel = new JPanel();
        TitledBorder section1Border = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2), // Set the border size here
                "Section 1 Title",
                TitledBorder.LEFT,
                TitledBorder.TOP
        );
        section1Panel.setBorder(section1Border);
        section1Panel.setLayout(null);
        section1Panel.setPreferredSize(new Dimension(400, 200)); // Set preferred size here
        section1Panel.setMinimumSize(new Dimension(400, 200)); // Set minimum size here
        section1Panel.setBounds(10, 10, 400, 200);
        MenuGui menu = new MenuGui();


        // Create the text fields
        productsLabel = new JLabel("Products:");
        productsLabel.setBounds(10, 50, 100, 50);


        quantityLabel = new JLabel("Quanity:");
        quantityLabel.setBounds(10, 100, 120, 50);




        addressLabel = new JLabel("Address: ");
        addressLabel.setBounds(10, 150, 140, 50);

        deliveryDateLabel = new JLabel("Delivery Date: ");
        deliveryDateLabel.setBounds(10, 250, 160, 50);


        JPanel section2Panel = new JPanel();
        TitledBorder section2Border = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK, 4), // Set the border size here
                "Section 2 Title",
                TitledBorder.LEFT,
                TitledBorder.TOP
        );
        section2Panel.setBorder(section2Border);
        section2Panel.setLayout(null);
//        section2Panel.setPreferredSize(new Dimension(400, 300)); // Set preferred size here
//        section2Panel.setMinimumSize(new Dimension(400, 300)); // Set minimum size here
//        section1Panel.setBounds(10, 10, 400, 200);


        section1Panel.add(productsLabel);
        section1Panel.add(quantityLabel);
        section2Panel.add(addressLabel);
        section2Panel.add(deliveryDateLabel);



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



        order = new JButton("Order");
        order.setBackground(new Color(240, 95, 5));
        order.setOpaque(true);
        order.setBorderPainted(false);
        order.setBounds(120, 400, 150, 40);




        section1Panel.add(products);
        section1Panel.add(quantity);
        section2Panel.add(address);
        section2Panel.add(order);


        contentPane.add(section1Panel);
        contentPane.add(section2Panel);

        setJMenuBar(menu);

        setContentPane(contentPane);

//        contentPane.setLayout(null);

        setVisible(true);
        pack();
        setSize(600, 700);
    }
}
