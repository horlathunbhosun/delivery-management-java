package org.olatunbosun.Guis;

import org.olatunbosun.controllers.OrderController;
import org.olatunbosun.controllers.UserController;
import org.olatunbosun.session.SessionData;
import org.olatunbosun.session.SessionManager;
import org.olatunbosun.session.SessionManagerMain;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class AssignedDeliverableGui extends JFrame implements ActionListener {


    private JTable userTable;
    private DefaultTableModel tableModel;
    SessionData sessionData = SessionManagerMain.loadUserFromFile();

    public AssignedDeliverableGui(){
        super("List of Drivers Assigned to Deliverables");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Create a panel to hold the label and table
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create a label for the title
        JLabel titleLabel = new JLabel("Deliverable assigned to driver with name " + sessionData.getFullName());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add the label to the panel
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Create columns for the table
        String[] columns = {"OrderNumber", "Customer Name", "Delivery Address", "Delivery Date", "Sequence", "status", "Mark as Delivered"};
        tableModel = new DefaultTableModel(columns, 0);
        userTable = new JTable(tableModel);

        //add menu bar
        MenuGui menu = new MenuGui();

        // Set the custom renderer and editor for the button column
        userTable.getColumnModel().getColumn(6).setCellRenderer(new AssignedDeliverableGui.ButtonRenderer());
        userTable.getColumnModel().getColumn(6).setCellEditor(new AssignedDeliverableGui.ButtonColumn());

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

        mainPanel.setBackground(new Color(159, 167, 192));


        //set menu bar
        setJMenuBar(menu);

        // Add static data to the table
        getDriverDeliverableData();

        // Set frame properties
        setSize(1000, 800);

        setVisible(true);


    }


    private void getDriverDeliverableData() {
        // Add data to the table from controller

        Vector<Vector<Object>> data = OrderController.getDriverDeliverable(sessionData.getUserId());
        for (Vector<Object> row : data) {
            tableModel.addRow(row);
        }

    }




    // Custom renderer to display a button in the table cell
    private static class ButtonRenderer extends DefaultTableCellRenderer {
        JButton button = new JButton("Mark");

        ButtonRenderer() {

            Font boldFont = new Font(button.getFont().getFamily(), Font.BOLD, button.getFont().getSize());
            button.setFont(boldFont);
            button.setBackground(new Color(61, 97, 192));
            button.setOpaque(true);
            button.setBorderPainted(false);
            button.addActionListener(e -> {
                // Retrieve the orderId from the button's text
                int orderId = Integer.parseInt(button.getText());
                markOrderCompleted(orderId,button);
            });
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return button;
        }
    }


    // ...

    // Create a custom renderer for the column containing the button
    private static class ButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {
        private final JButton button;
        private int orderId;

        ButtonColumn() {
            this.button = new JButton("Mark delivered");
            this.button.addActionListener(e -> markOrderCompleted(orderId, this.button));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            this.orderId = (int) value;
            return button;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            this.orderId = (int) value;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return orderId;
        }
    }


    private static void markOrderCompleted(int orderId, JButton button) {
        // Implement the logic to assign the order to a driver
        // You can open a dialog or perform any other necessary actions here

        System.out.println("Order Id: " + orderId);
//        SessionData sessionData = new SessionData();
//        sessionData.setUserId(driverId);
//        SessionManager.createSession("assignOrder", sessionData);
//        System.out.println(SessionManager.getSession("assignOrder").getUserId());
        // Open the new GUI
//        SwingUtilities.invokeLater(() -> {
//            ListOrderSchedulerGui listOrderSchedulerGui = new ListOrderSchedulerGui();
//            // Assuming that CreateOrderGui extends JFrame
//            listOrderSchedulerGui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//            listOrderSchedulerGui.setVisible(true);
//
//            // Close the current GUI
//            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(button);
//            frame.dispose();
//        });


    }



    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
