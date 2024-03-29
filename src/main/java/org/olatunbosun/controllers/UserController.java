package org.olatunbosun.controllers;

import org.olatunbosun.BCrypt;
import org.olatunbosun.database.MysqlConnection;
import org.olatunbosun.models.LoginModel;
import org.olatunbosun.models.UserUpdateInformation;
import org.olatunbosun.session.SessionData;
import org.olatunbosun.session.SessionManager;
import org.olatunbosun.session.SessionManagerMain;

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



    public static String updateData(UserUpdateInformation userUpdateInformation, int userId) {
        try {
            Connection connection = MysqlConnection.getConnection();
            // Prepare the SQL statement with placeholders
            String sql = "UPDATE users SET fullname = ?, email = ?, phone_number = ?, role = ?, truck_number = ?, truck_capacity = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Set values for the placeholders
                preparedStatement.setString(1, userUpdateInformation.getFullName());
                preparedStatement.setString(2, userUpdateInformation.getEmail());
                preparedStatement.setString(3, userUpdateInformation.getPhoneNumber());
                preparedStatement.setString(4, userUpdateInformation.getRole());
                preparedStatement.setString(5, userUpdateInformation.getTruckNumber());
                preparedStatement.setString(6, userUpdateInformation.getTruckCapacity() + "kg");
                preparedStatement.setInt(7, userId);

                // Execute the statement
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println(rowsAffected + " row(s) affected.");
                if (rowsAffected > 0) {
                    // Prepare the SQL statement to fetch the updated data
                    String fetchSql = "SELECT * FROM users WHERE id = ?";
                    try (PreparedStatement fetchStatement = connection.prepareStatement(fetchSql)) {
                        fetchStatement.setInt(1, userId);
                        try (ResultSet resultSet = fetchStatement.executeQuery()) {
                            if (resultSet.next()) {
                                SessionData sessionData = new SessionData();
                                sessionData.setUserId(resultSet.getInt("id"));
                                sessionData.setEmail(resultSet.getString("email"));
                                sessionData.setFullName(resultSet.getString("fullname"));
                                sessionData.setRole(resultSet.getString("role"));
                                sessionData.setPhoneNumber(resultSet.getString("phone_number"));
                                sessionData.setTruckNumber(resultSet.getString("truck_number"));
                                sessionData.setTruckCapacity(resultSet.getString("truck_capacity"));
                                SessionManager.createSession("userInfo", sessionData);
                                SessionManagerMain.saveUserToFile(sessionData);
                                return "Update Successful";
                            } else {
                                return "Update Failed\n Please Try Again";
                            }
                        }
                    }
                } else {
                    return "Update Failed\n Please Try Again";
                }
            }
        } catch (SQLException e) {
            return "Error: " + e.getMessage();
        }
    }


    public static String changePassword(int userId, String oldPassword, String newPassword){
            try {
                Connection connection = MysqlConnection.getConnection();
                // Prepare the SQL statement with placeholders
                String sql = "SELECT * FROM users WHERE id = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    // Set values for the placeholders
                    preparedStatement.setInt(1, userId);

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            // Get the stored hashed password
                            String storedHashedPassword = resultSet.getString("password");
                            // Check if the old password matches the stored password
                            boolean passwordCheck =  BCrypt.checkpw(oldPassword, storedHashedPassword);
                            if (passwordCheck) {
                                // If the old password is correct, hash the new password
                                String hashedNewPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());

                                // Prepare the SQL statement to update the password
                                String updateSql = "UPDATE users SET password = ? WHERE id = ?";
                                try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
                                    // Set values for the placeholders
                                    updateStatement.setString(1, hashedNewPassword);
                                    updateStatement.setInt(2, userId);
                                    // Execute the statement
                                    int rowsAffected = updateStatement.executeUpdate();
                                    if (rowsAffected > 0) {
                                        return "Password change successful";
                                    } else {
                                        return "Password change failed";
                                    }
                                }
                            } else {
                                return "Old password is incorrect";
                            }
                        } else {
                            return "User not found";
                        }
                    }
                }
            } catch (SQLException e) {
                return "Error: " + e.getMessage();
            }
        }







}
