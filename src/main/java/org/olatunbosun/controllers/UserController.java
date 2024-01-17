package org.olatunbosun.controllers;

import org.olatunbosun.BCrypt;
import org.olatunbosun.database.MysqlConnection;
import org.olatunbosun.models.LoginModel;
import org.olatunbosun.session.SessionData;
import org.olatunbosun.session.SessionManager;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class UserController {

    public static Vector<Vector<Object>> getUserInfoWithUserType(String userType) {
        Vector<Vector<Object>> userData = new Vector<>();

        try (Connection connection = MysqlConnection.getConnection()) {
            // Prepare the SQL statement with placeholders
            String sql = "SELECT * FROM users WHERE role = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Set values for the placeholders
                preparedStatement.setString(1, userType);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        // Extract data from the result set
                        int id = resultSet.getInt("id");
                        String fullName = resultSet.getString("fullname");
                        String email = resultSet.getString("email");
                        String truckNumber = resultSet.getString("truck_number");
                        String truckCapacity = resultSet.getString("truck_capacity");

                        // Add the data to the collection
                        Vector<Object> row = new Vector<>();
                        row.add(id);
                        row.add(fullName);
                        row.add(email);
                        row.add(truckNumber);
                        row.add(truckCapacity);
                        row.add(id);
                        userData.add(row);
                    }

                    if (userData.isEmpty()) {
                        System.out.println("No users found with the specified user type");
                    }
                    return userData;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null; // or return an empty Vector if you prefer
        }
    }






}
