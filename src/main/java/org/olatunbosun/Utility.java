package org.olatunbosun;

import org.olatunbosun.Guis.LoginScreenGui;
import org.olatunbosun.session.SessionData;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {



    public static JLabel createClickableLinkLabel(String text) {
        JLabel linkLabel = new JLabel();
        linkLabel.setText("<html><u>" + text + "</u></html>");
        linkLabel.setForeground(Color.BLUE);
        linkLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        return linkLabel;
    }


    public static Image getScaledImage(Image srcImg, int width, int height) {
        BufferedImage resizedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImg.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(srcImg, 0, 0, width, height, null);
        g2d.dispose();

        return resizedImg;
    }


    public static boolean validateEmail(String email) {
        // Define the regex pattern for a valid email address
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        // Compile the pattern
        Pattern pattern = Pattern.compile(emailRegex);

        // Create a matcher with the given email
        Matcher matcher = pattern.matcher(email);

        // Return true if the email matches the pattern, otherwise false
        return matcher.matches();
    }


    public static boolean validatePassword(String password) {
        // Define the regex pattern for a valid password
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$";

        // Compile the pattern
        Pattern pattern = Pattern.compile(passwordRegex);

        // Create a matcher with the given password
        Matcher matcher = pattern.matcher(password);

        // Return true if the password matches the pattern, otherwise false
        return matcher.matches();
    }


    // Helper method to get today's date
    public static java.sql.Date getTodayDate() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());
    }

    // Helper method to check if a string is numeric
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    //helper method to generate orderNumber randomly with UUid
    public static String generateOrderNumber() {
    	return java.util.UUID.randomUUID().toString();
    }

    public static String generateRandomString(int length) {
        StringBuilder randomStringBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            // Generate a random character or digit and append it to the string
            char randomChar;
            if (Math.random() < 0.5) {
                // Generate a random digit (0-9)
                randomChar = (char) ('0' + Math.random() * ('9' - '0' + 1));
            } else {
                // Generate a random lowercase letter (a-z)
                randomChar = (char) ('a' + Math.random() * ('z' - 'a' + 1));
            }
            randomStringBuilder.append(randomChar);
        }

        return randomStringBuilder.toString();
    }


    public static String  getDateFormatted(Date $date) {
        // Define the desired date format pattern
        String pattern = "yyyy-MM-dd HH:mm:ss"; // Example pattern, adjust as needed
        // Create a SimpleDateFormat object with the specified pattern
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

        // Format the date using the SimpleDateFormat object

        return dateFormat.format($date);
    }

    public static void checkSessionAndHandleExpiration(JFrame frame, SessionData sessionData) {
        if (sessionData == null) {
            JOptionPane.showMessageDialog(frame, "Session expired, please login again", "Error", JOptionPane.ERROR_MESSAGE);
            new LoginScreenGui();
            frame.dispose();
        }
    }



}
