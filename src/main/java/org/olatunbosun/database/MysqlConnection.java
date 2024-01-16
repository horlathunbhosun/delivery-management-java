package org.olatunbosun.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnection {

    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Provide the database URL, username, and password
            String url = "jdbc:mysql://localhost:3306/deliver_management";
            String username = "root";
            String password = "olatunbosun";

            // Create the connection
            connection = DriverManager.getConnection(url, username, password);

            System.out.println("Connected to the database!");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
