package org.olatunbosun.Guis;

import org.olatunbosun.controllers.OrderController;
import org.olatunbosun.session.SessionData;
import org.olatunbosun.session.SessionManagerMain;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class ListOrderGuiCustomer extends JFrame {

    private JTable userTable;
    private DefaultTableModel tableModel;
    SessionData sessionData = SessionManagerMain.loadUserFromFile();

    public ListOrderGuiCustomer() {
        super("List Orders Page");

        // Create an instance of the Menu class
        MenuGui menu = new MenuGui();

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

        setJMenuBar(menu);
        // Set frame properties
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // Add static data to the table
        userOrderData();
    }

    private void userOrderData() {

        Vector<Vector<Object>> data = OrderController.getUserOrders(sessionData.getUserId());
        for (Vector<Object> row : data) {
            tableModel.addRow(row);
        }
    }

}

