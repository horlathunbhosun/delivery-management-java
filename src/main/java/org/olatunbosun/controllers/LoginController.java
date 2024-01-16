package org.olatunbosun.controllers;

import org.olatunbosun.BCrypt;
import org.olatunbosun.database.MysqlConnection;
import org.olatunbosun.models.LoginModel;
import org.olatunbosun.session.SessionData;
import org.olatunbosun.session.SessionManager;

import java.sql.*;

public class LoginController {

    public static String Login(LoginModel loginModel){


        try {
            Connection connection = MysqlConnection.getConnection();
            // Prepare the SQL statement with placeholders
            String sql = "SELECT * FROM users WHERE email = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Set values for the placeholders
                preparedStatement.setString(1, loginModel.getEmail());

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Step 2: Hash the entered password
                        String storedHashedPassword = resultSet.getString("password");
                        boolean passwordCheck =  BCrypt.checkpw(loginModel.getPassword(), storedHashedPassword);
                        // Step 3: Compare hashes for authentication
                        if (passwordCheck) {
                            System.out.println("Authentication successful");
                            SessionData sessionData = new SessionData();
                            sessionData.setUserId(resultSet.getInt("id"));
                            sessionData.setEmail(resultSet.getString("email"));
                            sessionData.setFullName(resultSet.getString("fullname"));
                            sessionData.setRole(resultSet.getString("role"));
                            sessionData.setPhoneNumber(resultSet.getString("phone_number"));
                            sessionData.setTruckNumber(resultSet.getString("truck_number"));
                            sessionData.setTruckCapacity(resultSet.getString("truck_capacity"));
                            SessionManager.createSession("userInfo", sessionData);
                            return "Login Successful";
                        } else {
                            System.out.println("Authentication failed");
                            return "Login Failed incorrect password";
                        }
                    } else {
                        System.out.println("User not found");
                        return "Login Failed User with email not found";
                    }
                }
            }
        } catch (SQLException e) {
            return "Error: " + e.getMessage();
        }
    }

}
