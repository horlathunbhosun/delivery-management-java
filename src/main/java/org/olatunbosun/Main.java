package org.olatunbosun;

import org.olatunbosun.Guis.*;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

import static org.olatunbosun.database.MysqlConnection.getConnection;

public class Main {


    public static void main(String[] arg){
//        new WelcomeScreenGui();
//        new CreateOrder();
//        MenuClass  swingMenuDemo = new MenuClass();
//        swingMenuDemo.showMenuDemo();
//        new RegistrationGui();
//        new WelcomeScreenGui();

//        new CreateOrderGui();
       new ListDriversInfo();
//        Connection connection = getConnection();
//        // Close the connection when done
//        try {
//            if (connection != null && !connection.isClosed()) {
//                connection.close();
//                System.out.println("Connection closed.");
//            }
//
//            System.out.println(connection);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


//           new ProfileGui();

    }
}