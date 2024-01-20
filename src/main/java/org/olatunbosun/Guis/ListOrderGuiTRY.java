package org.olatunbosun.Guis;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.Vector;

public class ListOrderGuiTRY extends JFrame {

    private JTable userTable;
    private DefaultTableModel tableModel;

    public ListOrderGuiTRY() {
        super("List Orders Page");

        // Create a panel to hold the label and table
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create a label for the title
        JLabel titleLabel = new JLabel("Customer Order Information");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add the label to the panel
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Create columns for the table
        String[] columns = {"ID", "Order Number", "Product Name", "Quantity", "Address of Delivery", "Delivery Date", "Order Status"};
        tableModel = new DefaultTableModel(null, columns);
        userTable = new JTable(tableModel);

        // Add the table to the panel
        mainPanel.add(new JScrollPane(userTable), BorderLayout.CENTER);

        // Add the panel to the frame's content pane
        getContentPane().add(mainPanel);

        // Set frame properties
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // Add static data to the table
        userOrderData();
    }

    private void userOrderData() {
        // Add data to the table from controller
        Vector<Vector<Object>> data = new Vector<>();
        // Add sample data for testing
        // Replace the lines that add sample data with the following:
//        data.add(new Vector<>(Arrays.asList("1", "ce39296d-1bcd-4d32-8864-d89d962c5ac3", "Product A", "5", "123 Street", "2024-01-19", "Shipped")));
//        data.add(new Vector<>(Arrays.asList("2", "ce39296d-1bcd-4d32-8864-d89d962c5ac3", "Product B", "3", "456 Avenue", "2024-01-20", "Pending")));
//        data.add(new Vector<>(Arrays.asList("4", "101ce39296d-1bcd-4d32-8864-d89d962c5ac3", "Product D", "1", "101 Drive", "2024-01-22", "Pending")));
//        data.add(new Vector<>(Arrays.asList("5", "ce39296d-1bcd-4d32-8864-d89d962c5ac3", "Product E", "4", "112 Boulevard", "2024-01-23", "Shipped")));
//        data.add(new Vector<>(Arrays.asList("6", "ce39296d-1bcd-4d32-8864-d89d962c5ac3", "Product F", "6", "131 Lane", "2024-01-24", "Pending")));
//        data.add(new Vector<>(Arrays.asList("7", "ce39296d-1bcd-4d32-8864-d89d962c5ac3", "Product G", "7", "415 Street", "2024-01-25", "Shipped")));
//        for (Vector<Object> row : data) {
//            tableModel.addRow(row);
//        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ListOrderGuiTRY::new);
    }
}

