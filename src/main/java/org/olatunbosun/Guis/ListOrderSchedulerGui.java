package org.olatunbosun.Guis;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListOrderSchedulerGui extends JFrame {


    private JTable userTable;
    private DefaultTableModel tableModel;


    public ListOrderSchedulerGui(){
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
        String[] columns = { "Select","ID", "Customer Information", "Address of Delivery", "Product", "Delivery Sequence"};
        tableModel = new DefaultTableModel(null, columns){
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? Boolean.class : super.getColumnClass(columnIndex);
            }

        };
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

        // Create a button to retrieve selected IDs
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitSelectedRows();
            }
        });
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

    private void submitSelectedRows() {
        for (int i = 0; i < userTable.getRowCount(); i++) {
            boolean isSelected = (boolean) userTable.getValueAt(i, 0);
            if (isSelected) {
                // Get the ID from the second column (index 1)
                int id = (int) userTable.getValueAt(i, 1);
                System.out.println("Selected ID: " + id);
                // Get the Delivery Sequence from the fourth column (index 3)
                String deliverySequence = (String) userTable.getValueAt(i, 3);
                System.out.println("Delivery Sequence: " + deliverySequence);
                // Add your logic here to process the selected ID and Delivery Sequence
            }
        }
    }


    




}
