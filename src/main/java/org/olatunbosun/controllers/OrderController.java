package org.olatunbosun.controllers;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.olatunbosun.database.MysqlConnection;
import org.olatunbosun.models.AssignOrderDelivery;
import org.olatunbosun.models.CreateOrder;
import org.olatunbosun.models.Order;

import java.io.FileOutputStream;
import java.sql.*;
import java.util.List;
import java.util.Vector;

public class OrderController {


    public static String insertCreateOrder(CreateOrder createOrder) {
        Connection connection = MysqlConnection.getConnection();
        try {
            // Begin the transaction
            connection.setAutoCommit(false);

            // Insert into the 'orders' table
            String insertOrderSql = "INSERT INTO orders (order_number,delivery_address,delivery_date,order_status,order_assigned,user_id, created_at) VALUES (?, ?, ?, ?, ?, ?,?)";
            PreparedStatement orderStatement = connection.prepareStatement(insertOrderSql, Statement.RETURN_GENERATED_KEYS);
            try (orderStatement) {
                orderStatement.setString(1, createOrder.getOrderNumber());
                orderStatement.setString(2, createOrder.getDeliveryAddress());
                orderStatement.setString(3, createOrder.getDeliveryDate());
                orderStatement.setString(4, createOrder.getOrderStatus());
                orderStatement.setInt(5, 0);
                orderStatement.setInt(6, createOrder.getCustomerId());
                orderStatement.setTimestamp(7, new Timestamp(System.currentTimeMillis()));

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
                    "WHERE o.user_id = ? " + "ORDER BY o.created_at DESC";

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



    public static Order getUserOrdersByOrderId(String orderId) {

        try (Connection connection = MysqlConnection.getConnection()) {
            // Prepare the SQL statement with placeholders

            String selectOrdersSql =  "SELECT o.*, oi.product_id, oi.quantity, oi.date_created, p.product_name " +
                    "FROM orders o " +
                    "INNER JOIN order_items oi ON o.id = oi.order_id " +
                    "INNER JOIN products p ON oi.product_id = p.id " +
                    "WHERE o.order_number = ?";

            PreparedStatement selectStatement = connection.prepareStatement(selectOrdersSql);
            try (selectStatement) {
                // Set values for the placeholders
                selectStatement.setString(1, orderId);
                ResultSet resultSetUserOrders = selectStatement.executeQuery();
                try (resultSetUserOrders) {
                    while (resultSetUserOrders.next()) {
                        // Extract data from the result set
                        int order_id = resultSetUserOrders.getInt("id");
                        int userId = resultSetUserOrders.getInt("user_id");
                        String orderNumber = resultSetUserOrders.getString("order_number");
                        String deliveryAddress = resultSetUserOrders.getString("delivery_address");
                        String deliveryDate = resultSetUserOrders.getString("delivery_date");
                        String orderStatus = resultSetUserOrders.getString("order_status");
                        String productName = resultSetUserOrders.getString("product_name");
                        int quantity = resultSetUserOrders.getInt("quantity");
                        Timestamp dateCreated = resultSetUserOrders.getTimestamp("date_created");

                        // Add the data to the collection
                        return new Order(order_id,userId, orderNumber, deliveryAddress, deliveryDate, orderStatus, productName, quantity, dateCreated.toString());
                    }
                    System.out.println("No Order found");
                    return null;
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
                    "WHERE o.order_assigned = false AND o.order_status = 'order_placed' "+
                    "ORDER BY o.created_at DESC";

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



//    public static String insertAssignDriverOrder(List<AssignOrderDelivery> assignOrderDelivery) {
//        try {
//            Connection connection = MysqlConnection.getConnection();
//            // Prepare the SQL statement with placeholders
//            String sql = "INSERT INTO order_delivery (order_id, driver_id, sequence, location, order_status, created_at) VALUES (?, ?, ?, ?, ?, ?)";
//            String updateSql = "UPDATE orders SET order_assigned = true, order_status = 'assigned_to_driver' WHERE id = ?";
//
//            for (AssignOrderDelivery orderDelivery : assignOrderDelivery) {
//                try (PreparedStatement insertStatement = connection.prepareStatement(sql);
//                     PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
//                    // Set values for the placeholders
//                    insertStatement.setInt(1, orderDelivery.getOrderId());
//                    insertStatement.setInt(2, orderDelivery.getDeliveryPersonId());
//                    insertStatement.setInt(3, orderDelivery.getSequenceNumber());
//                    insertStatement.setString(4, orderDelivery.getLocation());
//                    insertStatement.setString(5, orderDelivery.getOrderStatus());
//                    insertStatement.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
//
//                    // Execute the statement
//                    int rowsAffected = insertStatement.executeUpdate();
//                    System.out.println(rowsAffected + " row(s) affected.");
//                    if (rowsAffected <= 0) {
//                        return "Order Failed to assign \n Please Try Again";
//                    }
//
//                    // Set values for the update statement placeholders
//                    updateStatement.setInt(1, orderDelivery.getOrderId());
//
//                    // Execute the update statement
//                    int updateRowsAffected = updateStatement.executeUpdate();
//                    System.out.println(updateRowsAffected + " row(s) updated.");
//                    if (updateRowsAffected <= 0) {
//                        return "Failed to update order status \n Please Try Again";
//                    }
//
//                }
//            }
//            return "Order Assigned to driver Successful";
//
//        } catch (SQLException e) {
//            return e.getMessage();
//        }
//
//    }


    public static Vector<Vector<Object>> getAllUserOrdersAssignedToDriver() {
        Vector<Vector<Object>> orderDataDelivery = new Vector<>();

        try (Connection connection = MysqlConnection.getConnection()) {
            // Prepare the SQL statement with placeholders
            String selectOrdersSql =  "SELECT od.*, ors.*, us.fullname,dr.fullname AS driverName, oi.product_id, oi.quantity, oi.date_created " +
                    "FROM order_delivery od " +
                    "INNER JOIN orders ors ON od.order_id = ors.id " +
                    "INNER JOIN users us ON ors.user_id = us.id " +
                    "INNER JOIN users dr ON od.driver_id = dr.id " +
                    "INNER JOIN order_items oi ON od.order_id = oi.order_id " +
                    "ORDER BY od.created_at DESC";

            PreparedStatement selectStatement = connection.prepareStatement(selectOrdersSql);
            try (selectStatement) {
                // Set values for the placeholders
                ResultSet resultSetUserOrders = selectStatement.executeQuery();
                try (resultSetUserOrders) {
                    while (resultSetUserOrders.next()) {

                        // Extract data from the result set
                        String customerfullName = resultSetUserOrders.getString("fullname");
                        String driverfullName = resultSetUserOrders.getString("driverName");
                        String orderNumber = resultSetUserOrders.getString("order_number");
//                        String productName = resultSetUserOrders.getString("product_name");
                        String deliveryDate = resultSetUserOrders.getString("delivery_date");
                        String orderStatus = resultSetUserOrders.getString("order_status");

                        // Add the data to the collection
                        Vector<Object> row = new Vector<>();
                        row.add(orderNumber);
                        row.add(customerfullName);
                        row.add(driverfullName);
                        row.add(deliveryDate);
                        row.add(orderStatus);
                        orderDataDelivery.add(row);
                    }

                    if (orderDataDelivery.isEmpty()) {
                        System.out.println("No Order found");
                    }
                    System.out.println("Order Data: " + orderDataDelivery);
                    return orderDataDelivery;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null; // or return an empty Vector if you prefer
        }
    }


    public static String insertAssignDriverOrder(List<AssignOrderDelivery> assignOrderDelivery) {
        Connection connection = null;
        try {
            connection = MysqlConnection.getConnection();
            // Set auto-commit to false to start a transaction
            connection.setAutoCommit(false);

            // Prepare the SQL statement with placeholders
            String sql = "INSERT INTO order_delivery (order_id, driver_id, sequence, location, order_status, created_at) VALUES (?, ?, ?, ?, ?, ?)";
            String updateSql = "UPDATE orders SET order_assigned = true, order_status = 'assigned_to_driver' WHERE id = ?";

            for (AssignOrderDelivery orderDelivery : assignOrderDelivery) {
                try (PreparedStatement insertStatement = connection.prepareStatement(sql);
                     PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
                    // Set values for the placeholders
                    insertStatement.setInt(1, orderDelivery.getOrderId());
                    insertStatement.setInt(2, orderDelivery.getDeliveryPersonId());
                    insertStatement.setInt(3, orderDelivery.getSequenceNumber());
                    insertStatement.setString(4, orderDelivery.getLocation());
                    insertStatement.setString(5, orderDelivery.getOrderStatus());
                    insertStatement.setTimestamp(6, new Timestamp(System.currentTimeMillis()));

                    // Execute the statement
                    int rowsAffected = insertStatement.executeUpdate();
                    System.out.println(rowsAffected + " row(s) affected.");
                    if (rowsAffected <= 0) {
                        // Rollback the transaction and return error message
                        connection.rollback();
                        return "Order Failed to assign \n Please Try Again";
                    }

                    // Set values for the update statement placeholders
                    updateStatement.setInt(1, orderDelivery.getOrderId());

                    // Execute the update statement
                    int updateRowsAffected = updateStatement.executeUpdate();
                    System.out.println(updateRowsAffected + " row(s) updated.");
                    if (updateRowsAffected <= 0) {
                        // Rollback the transaction and return error message
                        connection.rollback();
                        return "Failed to update order status \n Please Try Again";
                    }
                }
            }

            // If all operations are successful, commit the transaction
            connection.commit();
            return "Order Assigned to driver Successful";

        } catch (SQLException e) {
            // Rollback the transaction in case of any exception
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();  // Handle rollback exception
            }
            return e.getMessage();
        } finally {
            // Set auto-commit back to true
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                }
            } catch (SQLException setAutoCommitException) {
                setAutoCommitException.printStackTrace();  // Handle setAutoCommit exception
            }
        }
    }



    public static String GenerateReportDocument(String dateSelected) {


        try (Connection connection = MysqlConnection.getConnection()) {

            // Prepare the SQL statement with placeholders
            String selectOrdersSql =
                    "SELECT od.*, ors.*,us.fullname, dr.fullname AS driverName, oi.product_id, oi.quantity, oi.date_created " +
                            "FROM order_delivery od " +
                            "INNER JOIN orders ors ON od.order_id = ors.id " +
                            "INNER JOIN users us ON ors.user_id = us.id " +
                            "INNER JOIN users dr ON od.driver_id = dr.id " +
                            "INNER JOIN order_items oi ON od.order_id = oi.order_id " + "WHERE od.created_at = ? " +
                            "ORDER BY od.created_at DESC";

//            String sql = "SELECT * FROM order WHERE date_column = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectOrdersSql)) {
                // Set the date parameter based on user input
                preparedStatement.setDate(1, Date.valueOf(dateSelected));

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // Check if there are any missions for the selected date
                    if (!resultSet.next()) {
                        return "No missions found for the selected date";
                    }

                    // Create Word document
                    XWPFDocument document = new XWPFDocument();

                    // Create title with the chosen day
                    XWPFParagraph titleParagraph = document.createParagraph();
                    titleParagraph.setAlignment(ParagraphAlignment.CENTER);

                    XWPFRun titleRun = titleParagraph.createRun();
                    titleRun.setBold(true);
                    titleRun.setFontSize(16);
                    titleRun.setText(" THE DAY SELECTED ");

                    // Add missions to the document
                    do {
                        String deliverAddress = resultSet.getString("delivery_address");
                        String driverFullName = resultSet.getString("driverName");
//                        String deliveryLocation = resultSet.getString("route");
                        String deliveryDate = resultSet.getString("delivery_date");
                        String customerFullName = resultSet.getString("fullname");

                        // Create mission paragraph
                        XWPFParagraph missionParagraph = document.createParagraph();
                        missionParagraph.setAlignment(ParagraphAlignment.LEFT);

                        // Add mission details
                        missionParagraph.createRun().setText("Driver: " + driverFullName);
                        missionParagraph.createRun().addCarriageReturn();
                        missionParagraph.createRun().setText("Route: " + "from warehouse to " + deliverAddress + " on " + deliveryDate);
                        missionParagraph.createRun().addCarriageReturn();
                        missionParagraph.createRun().setText("Warehouse Address: " + "Back to Warehouse" + " on " + deliveryDate);
                        missionParagraph.createRun().setText("Customer: " + customerFullName + " on " + deliveryDate);

                        // Add delivery addresses
                        // ...
                        missionParagraph.createRun().addCarriageReturn();



                        // Add warehouse again to end the mission
                        missionParagraph.createRun().addCarriageReturn();
                        missionParagraph.createRun().setText("Warehouse Address: " + "where the driver will return to after the last delivery" + " on " + deliveryDate);
                    } while (resultSet.next());

                    // Save Word document
                    String outputPath = "src/main/resources/reports/"; // Change this to your desired directory
                    String fileName =  dateSelected + "-GeneratedDocument.docx";

                    try (FileOutputStream fileOut = new FileOutputStream(outputPath + fileName)) {
                        document.write(fileOut);
                    }
                }
            }
        } catch (SQLException e) {
            return "Error connecting to the database: " + e.getMessage();
        } catch (Exception e) {
            return "Error during document generation: " + e.getMessage();
        }
        return "Document generated successfully";

    }



    public static Vector<Vector<Object>> getDriverDeliverable(int driverId) {
        Vector<Vector<Object>> userData = new Vector<>();

        try (Connection connection = MysqlConnection.getConnection()) {
            // Prepare the SQL statement with placeholders

            String selectOrdersSql =
                    "SELECT od.*, ors.*,us.fullname, dr.fullname AS driverName, oi.product_id, oi.quantity, oi.date_created " +
                            "FROM order_delivery od " +
                            "INNER JOIN orders ors ON od.order_id = ors.id " +
                            "INNER JOIN users us ON ors.user_id = us.id " +
                            "INNER JOIN users dr ON od.driver_id = dr.id " +
                            "INNER JOIN order_items oi ON od.order_id = oi.order_id " +
                            "WHERE od.driver_id = ? AND od.order_status = 'assigned_to_driver'" +
                            "ORDER BY od.created_at DESC";

//            String sql = "SELECT * FROM users WHERE role = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectOrdersSql)) {
                // Set values for the placeholders
                preparedStatement.setInt(1, driverId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        // Extract data from the result set
                        int id = resultSet.getInt("id");
                        String orderNumber = resultSet.getString("order_number");
                        String fullName = resultSet.getString("fullname");
                        String deliveryAddress = resultSet.getString("delivery_address");
                        String deliveryDate = resultSet.getString("delivery_date");
                        String status = resultSet.getString("order_status");
                        int sequence = resultSet.getInt("sequence");

                        // Add the data to the collection
                        Vector<Object> row = new Vector<>();
                        row.add(orderNumber);
                        row.add(fullName);
                        row.add(deliveryAddress);
                        row.add(deliveryDate);
                        row.add(sequence);
                        row.add(status);
                        row.add(id);
                        userData.add(row);
                    }

                    if (userData.isEmpty()) {
                        System.out.println("No Deliverable found for this driver");
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
