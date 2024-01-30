package org.olatunbosun.Guis;

import org.olatunbosun.Utility;
import org.olatunbosun.controllers.OrderController;
import org.olatunbosun.controllers.ProductController;
import org.olatunbosun.session.SessionData;
import org.olatunbosun.session.SessionManager;
import org.olatunbosun.session.SessionManagerMain;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

public class ListOrderGui extends JFrame {


    private JTable userTable;
    private DefaultTableModel tableModel;
    SessionData sessionData = SessionManagerMain.loadUserFromFile();

    public ListOrderGui(){
        super("List Orders Page");


        JPanel contentPanel = new JPanel(new BorderLayout());


//        setLayout(new FlowLayout());

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
        String[] columns = {"ID", "Order Number",  "Product Name", "Quantity", "Address of Delivery", "Delivery Date", "Order Status"};
        tableModel = new DefaultTableModel(null, columns);
        userTable = new JTable(tableModel);

        // Set the custom renderer and editor for the button column
//        userTable.getColumnModel().getColumn(5).setCellRenderer(new ListDriversInfo.ButtonRenderer());
//        userTable.getColumnModel().getColumn(5).setCellEditor(new ListDriversInfo.ButtonColumn());
        userTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        userTable.getColumnModel().getColumn(2).setPreferredWidth(150);
        userTable.getColumnModel().getColumn(4).setPreferredWidth(150);
        userTable.getColumnModel().getColumn(5).setPreferredWidth(150);
        // Set the table header
        JTableHeader tableHeader = userTable.getTableHeader();
        tableHeader.setFont(new Font("Arial", Font.BOLD, 14)); // Adjust font for header
        tableHeader.setBackground(Color.LIGHT_GRAY); // Adjust background color for header

        userTable.setGridColor(Color.BLACK);
        userTable.setShowGrid(true);
//        userTable.setIntercellSpacing(new Dimension(10, 1)); // Adjust spacing between cells

        JScrollPane scrollPane = new JScrollPane(userTable);

        // Add the table to the panel
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));


        // Add the panel to the frame
//        add(mainPanel);
        contentPanel.add(mainPanel, BorderLayout.CENTER);
        setContentPane(contentPanel);


        getContentPane().setLayout(new BorderLayout());

        //set menu bar
        setJMenuBar(menu);

        // Add static data to the table
        userOrderData();

        // Set frame properties
        setSize(800, 500);
        setVisible(true);
    }



    private void userOrderData() {
        // Add data to the table from controller
        Vector<Vector<Object>> data = OrderController.getUserOrders(sessionData.getUserId());
        for (Vector<Object> row : data) {
            tableModel.addRow(row);
        }

    }








}
