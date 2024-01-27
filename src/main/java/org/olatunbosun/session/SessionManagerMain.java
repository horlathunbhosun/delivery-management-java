package org.olatunbosun.session;

import com.google.gson.Gson;

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


    public static void logoutUser() {
        SessionData loggedInUser = null;
    }
}
