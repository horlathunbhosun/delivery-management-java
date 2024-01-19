package org.olatunbosun.controllers;

import org.olatunbosun.database.MysqlConnection;
import org.olatunbosun.models.CreateOrder;
import org.olatunbosun.models.Products;

import java.sql.*;

public class CreateOrderController {


    public static String insertCreateOrder(CreateOrder createOrder) {
        Connection connection = MysqlConnection.getConnection();
        try {
            // Begin the transaction
            connection.setAutoCommit(false);

            // Insert into the 'orders' table
            String insertOrderSql = "INSERT INTO orders (order_number,delivery_address,delivery_date,order_status,user_id, created_at) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement orderStatement = connection.prepareStatement(insertOrderSql, Statement.RETURN_GENERATED_KEYS);
            try (orderStatement) {
                orderStatement.setString(1, createOrder.getOrderNumber());
                orderStatement.setString(2, createOrder.getDeliveryAddress());
                orderStatement.setString(3, createOrder.getDeliveryDate());
                orderStatement.setString(4, createOrder.getOrderStatus());
                orderStatement.setInt(5, createOrder.getCustomerId());
                orderStatement.setTimestamp(6, new Timestamp(System.currentTimeMillis()));

                int orderRowsAffected = orderStatement.executeUpdate();

                // Check if the order insertion was successful
                if (orderRowsAffected <= 0) {
                    throw new SQLException("Failed to insert into the 'orders' table");
//                    return "Failed to insert into the 'orders' table";
                }


                // Retrieve the auto-generated order ID (assuming 'order_id' is an auto-increment field)
                int orderId;
                try (ResultSet generatedKeys = orderStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        orderId = generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("Failed to retrieve auto-generated order ID");
                    }
                }

                // Insert into the 'orderItems' table
                String insertOrderItemSql = "INSERT INTO order_items (order_id, product_id, quantity, date_created) VALUES (?, ?, ?,?)";
                try (PreparedStatement orderItemStatement = connection.prepareStatement(insertOrderItemSql)) {
                    orderItemStatement.setInt(1, orderId);  // Link the order item to the corresponding order
                    orderItemStatement.setInt(2, createOrder.getCreateOrderItem().getProductId());
                    orderItemStatement.setInt(3, createOrder.getCreateOrderItem().getQuantity());
                    orderItemStatement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));

                    int orderItemRowsAffected = orderItemStatement.executeUpdate();

                    // Check if the order item insertion was successful
                    if (orderItemRowsAffected <= 0) {
                        throw new SQLException("Failed to insert into the 'orderItems' table");
                    }
                }

                // Commit the transaction if everything is successful
                connection.commit();
                connection.setAutoCommit(true);

                // Provide a success message
                return "Order created successfully";
            } catch (SQLException e) {
                // Rollback the transaction in case of an exception
                try {
                    connection.rollback();
                } catch (SQLException rollbackException) {
                    rollbackException.printStackTrace();  // Handle rollback failure
                }

                // Handle the exception (log/print/throw as needed)
                e.printStackTrace();
                return "Order Creation failed. Please try again.";
            } finally {
                // Close the connection in a 'finally' block to ensure it happens regardless of success or failure
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException closeException) {
                    closeException.printStackTrace();  // Handle close failure
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }


    }







}
