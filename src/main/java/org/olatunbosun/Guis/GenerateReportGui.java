package org.olatunbosun.Guis;

import com.toedter.calendar.JDateChooser;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.olatunbosun.Utility;
import org.olatunbosun.controllers.OrderController;
import org.olatunbosun.models.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class GenerateReportGui extends JFrame implements ActionListener {


    JLabel dateLabel;
    JDateChooser dateChooser;

    JButton generateReportButton;

    public GenerateReportGui() {
        super("Generate Report Page");

        // Add the components to the frame
        JPanel contentPane = new JPanel();

        //add menu bar
        MenuGui menuGui = new MenuGui();


        // Create the text fields
        dateLabel = new JLabel("Enter Date:");
        dateLabel.setBounds(10, 50, 100, 20);
        dateLabel.setFont(new Font("Arial", Font.BOLD, 15));


        contentPane.add(dateLabel);


        dateChooser = new JDateChooser();
        dateChooser.setBounds(120, 50, 200, 20);


        generateReportButton = new JButton("Generate");
        Font boldFont = new Font(generateReportButton.getFont().getFamily(), Font.BOLD, generateReportButton.getFont().getSize());
        generateReportButton.setFont(boldFont);
        generateReportButton.setBackground(new Color(0, 158, 15));
        generateReportButton.setOpaque(true);
        generateReportButton.setBorderPainted(false);
        generateReportButton.setBounds(120, 100, 200, 20);
        generateReportButton.addActionListener(this);


        contentPane.add(dateChooser);

        contentPane.add(generateReportButton);
        contentPane.setBackground(new Color(159, 167, 192));


        setContentPane(contentPane);

        setJMenuBar(menuGui);
        contentPane.setLayout(null);





        setJMenuBar(menuGui);
        setContentPane(contentPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setVisible(true);
    }


    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == generateReportButton) {
            boolean isValid = validation();
            if (!isValid) {
                return;
            }

            Date date = dateChooser.getDate();
            // Convert the selected Date to LocalDate
            Instant instant = date.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            LocalDate localDate = instant.atZone(zoneId).toLocalDate();
            String formattedDate = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            System.out.println(formattedDate);

            String response = OrderController.GenerateReportDocument(formattedDate);
            if (response.equals("Document generated successfully")) {
                JOptionPane.showMessageDialog(this, response, "Success", JOptionPane.INFORMATION_MESSAGE);
                new HomeGui();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, response, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }


    }
    public boolean validation() {
        boolean isValid = true;
        StringBuilder errorMessage = new StringBuilder();
        if (dateChooser.getDate() == null) {
            errorMessage.append("Date is required\n");
            isValid = false;
        }
        // Display the first error encountered, if any
        if (!isValid) {
            JOptionPane.showMessageDialog(this, errorMessage.toString().trim(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return isValid;

    }


}
