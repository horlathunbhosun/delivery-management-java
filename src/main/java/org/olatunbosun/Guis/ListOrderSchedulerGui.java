package org.olatunbosun.Guis;

import org.olatunbosun.Utility;
import org.olatunbosun.controllers.OrderController;
import org.olatunbosun.models.AssignOrderDelivery;
import org.olatunbosun.models.Order;
import org.olatunbosun.session.SessionData;
import org.olatunbosun.session.SessionManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

import static org.olatunbosun.controllers.OrderController.getUserOrdersByOrderId;

public class ListOrderSchedulerGui extends JFrame implements ActionListener{


    private JTable userTable;
    private DefaultTableModel tableModel;
    private JButton submitButton;

    public ListOrderSchedulerGui(){
        super("List Orders Page");

        // Create an instance of the Menu class
        MenuGui menu = new MenuGui();

        // Create a panel to hold the label and table
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create a label for the title
        JLabel titleLabel = new JLabel("Order Information");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add the label to the panel
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Create columns for the table
        String[] columns = { "Select Orders","ID", "Customer Information", "Address of Delivery", "Product"};
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
         submitButton = new JButton("Assign Orders");
        submitButton.addActionListener(this);
        mainPanel.add(submitButton, BorderLayout.SOUTH);
        // Add the panel to the frame
        add(mainPanel);

        //set menu bar
        setJMenuBar(menu);

        // Add static data to the table
        userOrderData();

        // Set frame properties
        setSize(700, 600);
        setVisible(true);
    }

    private void submitSelectedRows() {
        List<Map<String, Object>> selectedItems = new ArrayList<>();
        int sequence = 1;
        // Keep track of selected indices
        List<Integer> selectedIndices = new ArrayList<>();

        for (int i = 0; i < userTable.getRowCount(); i++) {
            boolean isSelected = (boolean) userTable.getValueAt(i, 0);
            if (isSelected) {
                // Record the selected index
                selectedIndices.add(i);
            }
        }
        // Now assign delivery sequence based on selection order
        System.out.println("Selected indices: " + selectedIndices);
        for (Integer selectedIndex : selectedIndices) {
            Map<String, Object> item = new HashMap<>();
            item.put("ID", userTable.getValueAt(selectedIndex, 1));
            item.put("delivery_sequence", sequence++);
            selectedItems.add(item);
        }

        List<AssignOrderDelivery> assignOrderDeliveries = new ArrayList<>();

        // Now you can use the selectedItems list
        for (Map<String, Object> item : selectedItems) {
            // Get the order details
            Order order  = OrderController.getUserOrdersByOrderId((String) item.get("ID"));
            int deliverySequence = (int) item.get("delivery_sequence");
            SessionData sessionData = SessionManager.getSession("assignOrder");
            Utility.checkSessionAndHandleExpiration(this, sessionData);

            int driverId = sessionData.getUserId();

            // Create an instance of the AssignOrderDelivery class
            AssignOrderDelivery assignOrderDelivery = new AssignOrderDelivery(order.getOrderId(),driverId, order.getCustomerId(),deliverySequence, order.getDeliveryAddress() ,"assigned_to_driver" );
                assignOrderDelivery.getOrderStatus();
            assignOrderDeliveries.add(assignOrderDelivery);
//            System.out.println("order delivery sequence: " + assignOrderDelivery);
//            System.out.println("Selected Order: " + order);
//            System.out.println("Selected ID: " + item.get("ID"));
//            System.out.println("Delivery Sequence: " + item.get("delivery_sequence"));
        }


        String response = OrderController.insertAssignDriverOrder(assignOrderDeliveries);

        // Check the result and show appropriate message
        if (response.equals("Order Assigned to driver Successful")) {
            // Show success message
            JOptionPane.showMessageDialog(this, response, "Success", JOptionPane.INFORMATION_MESSAGE);
            // Go to the next screen
            new HomeGui();
            // Close the current screen
            dispose();
        } else {
            // Show error message
            JOptionPane.showMessageDialog(this, "Error: " + response, "Error", JOptionPane.ERROR_MESSAGE);
        }


    }


//    private void submitSelectedRows() {
//        for (int i = 0; i < userTable.getRowCount(); i++) {
//            System.out.println(userTable.getValueAt(i, 0));
//            boolean isSelected = (boolean) userTable.getValueAt(i, 0);
//            System.out.println("Selected: " + isSelected);
//            if (isSelected) {
//                // Rest of your code
//                // Get the ID from the second column (index 1)
//                int id = (int) userTable.getValueAt(i, 1);
//                System.out.println("Selected ID: " + id);
//                // Get the Delivery Sequence from the fourth column (index 3)
//                String deliverySequence = (String) userTable.getValueAt(i, 3);
//                System.out.println("Delivery Sequence: " + deliverySequence);
//                // Add your logic here to process the selected ID and Delivery Sequence
//            }
////            if (isSelected) {
////
////            }
//        }
//    }


    private void userOrderData() {

        Vector<Vector<Object>> data = OrderController.getAllUserOrders();
        for (Vector<Object> row : data) {
            tableModel.addRow(row);
        }
    }


    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
//
        if (e.getSource() == submitButton ) {
            submitSelectedRows();
        }
    }
}
