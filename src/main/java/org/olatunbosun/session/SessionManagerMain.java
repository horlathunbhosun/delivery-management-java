package org.olatunbosun.session;

import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SessionManagerMain {

    // Save user information to a JSON file
    static String filePath = "src/main/resources/loggedInUser.json";


    public static void saveUserToFile(SessionData loggedInUser) {
        try (FileWriter writer = new FileWriter(filePath)) {
            Gson gson = new Gson();
            gson.toJson(loggedInUser, writer);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
    }

    // Load user information from a JSON file
    public static SessionData loadUserFromFile() {

        File file = new File(filePath);

        if (file.exists()) {
            try (FileReader reader = new FileReader(filePath)) {
                Gson gson = new Gson();
                return gson.fromJson(reader, SessionData.class);
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception appropriately
            }
        } else {
            System.out.println("Session file does not exist.");
        }
        return null;
    }


    public static String logoutUser(Component parentComponent ){

//        SessionData loggedInUser = null;

        int option = JOptionPane.showConfirmDialog(
                parentComponent,
                "Are you sure you want to logout?",
                "Logout",
                JOptionPane.YES_NO_OPTION
        );

        if (option == JOptionPane.YES_OPTION) {
            File file = new File(filePath);
            if (file.exists()) {
                if (file.delete()) {
                    return "User successfully logged out.";
                } else {
//                    JOptionPane.showMessageDialog(parentComponent, "Logout failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    return "Logout failed. Please try again.";

                }
            } else {
//                JOptionPane.showMessageDialog(parentComponent, "User is already logged out.", "Error", JOptionPane.ERROR_MESSAGE);
                return "User is already logged out.";
            }
        }else {
            return "User is still logged in.";
        }


    }

    public static String logoutUserWithOutDialog(Component parentComponent ){

//        SessionData loggedInUser = null;

            File file = new File(filePath);
            if (file.exists()) {
                if (file.delete()) {
                    return "User successfully logged out.";
                } else {
//                    JOptionPane.showMessageDialog(parentComponent, "Logout failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    return "Logout failed. Please try again.";
                }
            } else {
//                JOptionPane.showMessageDialog(parentComponent, "User is already logged out.", "Error", JOptionPane.ERROR_MESSAGE);
                return "User is already logged out.";
            }



    }
}
