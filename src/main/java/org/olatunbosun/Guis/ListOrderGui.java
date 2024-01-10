package org.olatunbosun.Guis;

import org.olatunbosun.Utility;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ListOrderGui extends JFrame {


     JLabel headerLabel;
     JLabel statusLabel;
     JPanel controlPanel;

    JLabel titlelabel, welcomelabel,emptyLabel;
    JButton loginbutton, registerbutton;
    JTable orderTable;

    public ListOrderGui(){
        super("List Orders Page");


        setLayout(null);

//        setLayout(new FlowLayout());

        // Create an instance of the Menu class
        MenuGui menu = new MenuGui();


        // Create a JLabel for the title
        JLabel titleLabel = new JLabel("List of All Orders");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBounds(20,20,300,20);
        // Add the label to the top (North) of the main panel

        String[] columnNames = {"Product Name", "kg", "Address of Delivery", "Delivery status"};
        Object[][] data = {
                {"Ramesh Raman", 5000, "Testing now", "delivered"},
                {"Shabbir Hussein", 7000, "Testing now", "delivered"},
                {"Ramesh Raman", 5000, "Testing now", "delivered"},
                {"Ramesh Raman", 5000, "Testing now", "delivered"},
                {"Ramesh Raman", 5000, "Testing now", "delivered"},
                {"Ramesh Raman", 5000, "Testing now", "delivered"},
                {"Ramesh Raman", 5000, "Testing now", "delivered"},

        };
//        DefaultTableModel model = new DefaultTableModel(data, columnNames);


        // Create JTable with the model
        orderTable = new JTable(data, columnNames);
//        orderTable.getTableHeader().setBounds(50,50,500,20);
//        orderTable.setBounds(50,50,500,100);
        // Create JScrollPane to hold the JTable
        JScrollPane scrollPane = new JScrollPane(orderTable);
        scrollPane.setBounds(50,50,500,500);

        orderTable.setFillsViewportHeight(true);
        orderTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // Add the main panel to the JFrame
        add(titleLabel);
//        add(orderTable.getTableHeader());
        add(scrollPane);

        // Set JFrame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(); // Center the JFrame
        setJMenuBar(menu);

        setVisible(true);
//        pack();

        setSize(600, 600);
    }





}
