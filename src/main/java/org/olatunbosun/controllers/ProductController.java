package org.olatunbosun.controllers;

import org.olatunbosun.BCrypt;
import org.olatunbosun.database.MysqlConnection;
import org.olatunbosun.models.Products;
import org.olatunbosun.models.Registration;

import java.sql.*;
import java.util.Vector;

public class ProductController {

    public static String insertDataProduct(Products products) {
        try {
            Connection connection = MysqlConnection.getConnection();
            // Prepare the SQL statement with placeholders
            String sql = "INSERT INTO products (product_name, date_created) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Set values for the placeholders
                preparedStatement.setString(1, products.getProductName());

                preparedStatement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));

                // Execute the statement
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println(rowsAffected + " row(s) affected.");
                if (rowsAffected > 0) {
                    System.out.println(rowsAffected + " row(s) affected.");
                    return "Product Added Successful";
                } else {
                    return "Product Failed to add \n Please Try Again";
                }
            }
        } catch (SQLException e) {
            return "Error: " + e.getMessage();
        }

    }

    public static Vector<Vector<Object>> getProducts() {
        Vector<Vector<Object>> productDate = new Vector<>();

        try (Connection connection = MysqlConnection.getConnection()) {
            // Prepare the SQL statement with placeholders
            String sql = "SELECT * FROM products";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Set values for the placeholders

                try (ResultSet resultSetProducts = preparedStatement.executeQuery()) {
                    while (resultSetProducts.next()) {
                        // Extract data from the result set
                        int id = resultSetProducts.getInt("id");
                        String productName = resultSetProducts.getString("product_name");
                        String dateCreated = resultSetProducts.getString("date_created");

                        // Add the data to the collection
                        Vector<Object> row = new Vector<>();
                        row.add(id);
                        row.add(productName);
                        row.add(dateCreated);
                        productDate.add(row);
                    }

                    if (productDate.isEmpty()) {
                        System.out.println("No Products found");
                    }
                    return productDate;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null; // or return an empty Vector if you prefer
        }
    }
}
