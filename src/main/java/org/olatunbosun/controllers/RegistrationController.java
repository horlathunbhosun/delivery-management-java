package org.olatunbosun.controllers;

import org.olatunbosun.BCrypt;
import org.olatunbosun.database.MysqlConnection;
import org.olatunbosun.models.Registration;
import org.olatunbosun.models.UserUpdateInformation;

import java.sql.*;

public class RegistrationController {

    //
    public static String insertData(Registration registration) {
        try {
            Connection connection = MysqlConnection.getConnection();
            // Prepare the SQL statement with placeholders
            String sql = "INSERT INTO users (fullname,email,password,phone_number,role,truck_number, truck_capacity, date_created) VALUES (?, ?, ?, ?, ?, ?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Set values for the placeholders

                String hashedPassword = BCrypt.hashpw(registration.getPassword(), BCrypt.gensalt());

                preparedStatement.setString(1, registration.getFullName());
                preparedStatement.setString(2, registration.getEmail());
                preparedStatement.setString(3, hashedPassword);
                preparedStatement.setString(4, registration.getPhoneNumber());
                preparedStatement.setString(5, registration.getRole());
                preparedStatement.setString(6, registration.getTruckNumber());
                preparedStatement.setString(7, registration.getTruckCapacity() + "kg");
                preparedStatement.setTimestamp(8, new Timestamp(System.currentTimeMillis()));

                // Execute the statement
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println(rowsAffected + " row(s) affected.");
                if (rowsAffected > 0) {
                    System.out.println(rowsAffected + " row(s) affected.");
                    return "Registration Successful";
                } else {
                    return "Registration Failed\n Please Try Again";
                }
            }
        } catch (SQLException e) {
            return "Error: " + e.getMessage();
        }

    }




}
