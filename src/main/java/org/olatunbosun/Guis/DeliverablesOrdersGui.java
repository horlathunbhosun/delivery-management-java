package org.olatunbosun.Guis;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class DeliverablesOrdersGui extends JFrame {
    public DeliverablesOrdersGui() {
        setTitle("List of Deliverables / Orders");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        String[] columnNames = {"Sl#", "Date", "Customer Information", "Address of Delivery", "Package Name", "Package Weight", "List of Drivers", "Delivery Sequence Number", "Action"};
        Object[][] data = {
                {"1", "10/11/2022", "AjayKumar", "Peldi", "Rice", "2kg", "Driver1", "1", new JButton("Assign")},
                {"2", "10/11/2022", "Dinesh", "Maryse Bastie Rouen", "Beans", "2kg", "Driver1\nDriver2\nDriver3", "1", new JButton("Assign")},
                {"3", "10/11/2022", "Sai", "Maryse Bastie France", "Garri", "2kg", "Driver1", "2", new JButton("Assign")},
                {"4", "10/11/2022", "Tholza", "14 juliet Saint etienne Du ro", "Laptop", "2kg", "Driver1", "3", new JButton("Assign")}
        };

        JTable table = new JTable(new DefaultTableModel(data, columnNames));
        table.getColumnModel().getColumn(8).setCellRenderer((TableCellRenderer) new ButtonRenderer());
        table.getColumnModel().getColumn(8).setCellEditor(new ButtonEditor(new JCheckBox()));
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

//    public static void main(String[] args) {
//        DeliverablesOrders deliverablesOrders = new DeliverablesOrders();
//        deliverablesOrders.setVisible(true);
//    }
}

class ButtonRenderer extends JButton implements TableCellRenderer {
    public ButtonRenderer() {
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(UIManager.getColor("Button.background"));
        }
        setText((value == null) ? "" : value.toString());
        return this;
    }
}

class ButtonEditor extends DefaultCellEditor {
    protected JButton button;

    private String label;

    private boolean isPushed;

    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(e -> fireEditingStopped());
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    public Object getCellEditorValue() {
        if (isPushed) {
            JOptionPane.showMessageDialog(button, label + ": Ouch!");
        }
        isPushed = false;
        return new String(label);
    }

    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}
