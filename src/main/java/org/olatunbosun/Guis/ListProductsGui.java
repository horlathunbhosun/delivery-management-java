package org.olatunbosun.Guis;

import org.olatunbosun.controllers.ProductController;
import org.olatunbosun.controllers.UserController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ListProductsGui extends JFrame  {

    private JTable productTable;
    private DefaultTableModel tableModel;


    public ListProductsGui(){
        super("List Products Page");


        setLayout(new FlowLayout());

        // Create an instance of the Menu class
        MenuGui menu = new MenuGui();


        // Create a panel to hold the label and table
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create a label for the title
        JLabel titleLabel = new JLabel("Product List");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add the label to the panel
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Create columns for the table
        String[] columns = { "ID", "Products Name", "Date Created"};
        tableModel = new DefaultTableModel(null, columns);
        productTable = new JTable(tableModel);

        // Set the table header
        JTableHeader tableHeader = productTable.getTableHeader();
        tableHeader.setFont(new Font("Arial", Font.BOLD, 14)); // Adjust font for header
        tableHeader.setBackground(Color.LIGHT_GRAY); // Adjust background color for header
        productTable.setGridColor(Color.BLACK);
        productTable.setShowGrid(true);
        productTable.setIntercellSpacing(new Dimension(1, 1)); // Adjust spacing between cells

        JScrollPane scrollPane = new JScrollPane(productTable);

        // Add the table to the panel
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add the panel to the frame
        add(mainPanel);

        //set menu bar
        setJMenuBar(menu);

        // Add static data to the table
        addProductsData();

        // Set frame properties
        setSize(700, 500);
        setVisible(true);
    }


    private void addProductsData() {
        // Add data to the table from controller
        Vector<Vector<Object>> data = ProductController.getProducts();
        for (Vector<Object> row : data) {
            tableModel.addRow(row);
            System.out.println(row);
        }

    }
}
