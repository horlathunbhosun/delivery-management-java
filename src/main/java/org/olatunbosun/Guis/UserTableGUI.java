package org.olatunbosun.Guis;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

public class UserTableGUI extends JFrame {
    private JTable userTable;
    private DefaultTableModel tableModel;

    public UserTableGUI() {
        setTitle("User Information Table");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        // Create columns for the table
        String[] columns = {"ID", "Name", "Age", "Email"};
        tableModel = new DefaultTableModel(columns, 0);
        userTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(userTable);
        add(scrollPane, BorderLayout.CENTER);

        // Fetch user data from the database and populate the table
        fetchUserData();

        // Set up the GUI
        setVisible(true);
    }

    private void fetchUserData() {
        try {
            // Replace the following details with your actual database connection information
            String jdbcURL = "jdbc:mysql://localhost:3306/your_database";
            String username = "your_username";
            String password = "your_password";

            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");


            while (resultSet.next()) {
                // Extract data from the result set
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String email = resultSet.getString("email");

                // Add the data to the table model
                Vector<Object> row = new Vector<>();
                row.add(id);
                row.add(name);
                row.add(age);
                row.add(email);
                tableModel.addRow(row);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UserTableGUI());
    }
}
