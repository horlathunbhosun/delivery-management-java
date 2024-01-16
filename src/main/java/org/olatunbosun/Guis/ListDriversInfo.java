package org.olatunbosun.Guis;

import org.olatunbosun.controllers.UserController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.Vector;

public class ListDriversInfo extends JFrame {

    private JTable userTable;
    private DefaultTableModel tableModel;

    public ListDriversInfo(){
        super("List of Drivers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a panel to hold the label and table
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create a label for the title
        JLabel titleLabel = new JLabel("Driver Information");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add the label to the panel
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Create columns for the table
        String[] columns = {"ID", "Name", "Email", "Truck Number", "Truck Capacity"};
        tableModel = new DefaultTableModel(columns, 0);
        userTable = new JTable(tableModel);

        //add menu bar
        MenuGui menu = new MenuGui();

        // Customize the table appearance

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
        // Add the panel to the frame
        add(mainPanel);

        //set menu bar
        setJMenuBar(menu);

        // Add static data to the table
        addStaticUserData();

        // Set frame properties
        setSize(700, 500);
        setVisible(true);
    }


    private void addStaticUserData() {
        // Add data to the table from controller
        Vector<Vector<Object>> data = UserController.getUserInfoWithUserType("driver");
        for (Vector<Object> row : data) {
            tableModel.addRow(row);
        }

    }

}
