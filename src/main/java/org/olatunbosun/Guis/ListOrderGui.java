package org.olatunbosun.Guis;

import org.olatunbosun.Utility;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ListOrderGui extends JFrame {


    private JTable userTable;
    private DefaultTableModel tableModel;


    public ListOrderGui(){
        super("List Orders Page");


        setLayout(null);

//        setLayout(new FlowLayout());

        // Create an instance of the Menu class
        MenuGui menu = new MenuGui();


        // Create a panel to hold the label and table
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create a label for the title
        JLabel titleLabel = new JLabel("Drivers Information");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add the label to the panel
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Create columns for the table
        String[] columns = {"ID", "Customer Information", "Address of Delivery", "Product Name", "Quanity"};
        tableModel = new DefaultTableModel(null, columns);
        userTable = new JTable(tableModel);

        // Set the custom renderer and editor for the button column
//        userTable.getColumnModel().getColumn(5).setCellRenderer(new ListDriversInfo.ButtonRenderer());
//        userTable.getColumnModel().getColumn(5).setCellEditor(new ListDriversInfo.ButtonColumn());

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
//        addStaticUserData();

        // Set frame properties
        setSize(700, 500);
        setVisible(true);
    }









}
