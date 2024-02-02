package org.olatunbosun.controllers;

import org.olatunbosun.database.MysqlConnection;
import org.olatunbosun.models.CreateOrder;

import java.sql.*;
import java.util.Vector;

public class OrderController {


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



    public static Vector<Vector<Object>> getUserOrders(int userId) {
        Vector<Vector<Object>> orderData = new Vector<>();

        try (Connection connection = MysqlConnection.getConnection()) {
            // Prepare the SQL statement with placeholders

            String selectOrdersSql =  "SELECT o.*, oi.product_id, oi.quantity, oi.date_created, p.product_name " +
                    "FROM orders o " +
                    "INNER JOIN order_items oi ON o.id = oi.order_id " +
                    "INNER JOIN products p ON oi.product_id = p.id " +
                    "WHERE o.user_id = ?";

            PreparedStatement selectStatement = connection.prepareStatement(selectOrdersSql);
            try (selectStatement) {
                // Set values for the placeholders
                selectStatement.setInt(1, userId);
                ResultSet resultSetUserOrders = selectStatement.executeQuery();
                try (resultSetUserOrders) {
                    while (resultSetUserOrders.next()) {
                        // Extract data from the result set
                        int orderId = resultSetUserOrders.getInt("id");
                        String orderNumber = resultSetUserOrders.getString("order_number");
                        String deliveryAddress = resultSetUserOrders.getString("delivery_address");
                        String deliveryDate = resultSetUserOrders.getString("delivery_date");
                        String orderStatus = resultSetUserOrders.getString("order_status");
                        String productName = resultSetUserOrders.getString("product_name");
                        int quantity = resultSetUserOrders.getInt("quantity");
                        Timestamp dateCreated = resultSetUserOrders.getTimestamp("date_created");

                        // Add the data to the collection
                        Vector<Object> row = new Vector<>();
                        row.add(orderId);
                        row.add(orderNumber);
                        row.add(productName);
                        row.add(quantity);
                        row.add(deliveryAddress);
                        row.add(deliveryDate);
                        row.add(orderStatus);
                        row.add(dateCreated);
                        orderData.add(row);
                    }

                    if (orderData.isEmpty()) {
                        System.out.println("No Order found");
                    }
                    return orderData;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null; // or return an empty Vector if you prefer
        }
    }


    public static Vector<Vector<Object>> getAllUserOrders() {
        Vector<Vector<Object>> orderData = new Vector<>();

        try (Connection connection = MysqlConnection.getConnection()) {
            // Prepare the SQL statement with placeholders

            String selectOrdersSql =  "SELECT o.*, us.fullname, oi.product_id, oi.quantity, oi.date_created, p.product_name " +
                    "FROM orders o " +
                    "INNER JOIN users us ON o.user_id = us.id " +
                    "INNER JOIN order_items oi ON o.id = oi.order_id " +
                    "INNER JOIN products p ON oi.product_id = p.id " +
                    "ORDER BY o.id DESC";

            PreparedStatement selectStatement = connection.prepareStatement(selectOrdersSql);
            try (selectStatement) {
                // Set values for the placeholders
                ResultSet resultSetUserOrders = selectStatement.executeQuery();
                try (resultSetUserOrders) {
                    while (resultSetUserOrders.next()) {

                        // Extract data from the result set
                        String fullname = resultSetUserOrders.getString("fullname");
                        String orderNumber = resultSetUserOrders.getString("order_number");
                        String deliveryAddress = resultSetUserOrders.getString("delivery_address");
                        String productName = resultSetUserOrders.getString("product_name");

                        // Add the data to the collection
                        Vector<Object> row = new Vector<>();
                        row.add(false);
                        row.add(orderNumber);
                        row.add(fullname);
                        row.add(deliveryAddress);
                        row.add(productName);
                        orderData.add(row);
                    }

                    if (orderData.isEmpty()) {
                        System.out.println("No Order found");
                    }
                    System.out.println("Order Data: " + orderData);
                    return orderData;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null; // or return an empty Vector if you prefer
        }
    }


}
