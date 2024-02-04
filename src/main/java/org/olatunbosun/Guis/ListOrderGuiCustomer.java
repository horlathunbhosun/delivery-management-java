package org.olatunbosun.Guis;

import org.olatunbosun.Utility;
import org.olatunbosun.controllers.OrderController;
import org.olatunbosun.session.SessionData;
import org.olatunbosun.session.SessionManagerMain;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
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

        // Set the table header
        JTableHeader tableHeader = userTable.getTableHeader();
        tableHeader.setFont(new Font("Arial", Font.BOLD, 14)); // Adjust font for header
        tableHeader.setBackground(Color.LIGHT_GRAY); // Adjust background color for header

        userTable.setGridColor(Color.BLACK);
        userTable.setShowGrid(true);
        userTable.setIntercellSpacing(new Dimension(1, 1)); // Adjust spacing between cells

        JScrollPane scrollPane = new JScrollPane(userTable);

        // Add the table to the panel
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add the panel to the frame's content pane
        getContentPane().add(mainPanel);

        mainPanel.setBackground(new Color(159, 167, 192));

        setJMenuBar(menu);
        // Set frame properties
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // Add static data to the table
        userOrderData();
    }

    private void userOrderData() {

        Utility.checkSessionAndHandleExpiration(this, sessionData);


        Vector<Vector<Object>> data = OrderController.getUserOrders(sessionData.getUserId());
        for (Vector<Object> row : data) {
            tableModel.addRow(row);
        }
    }

}

