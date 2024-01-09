package org.olatunbosun.Guis;

import org.olatunbosun.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ListOrderGui extends JFrame {


     JLabel headerLabel;
     JLabel statusLabel;
     JPanel controlPanel;

    public ListOrderGui(){
        super("List Orders Page");

//        setLayout(new GridLayout(3, 1));

        // Create an instance of the Menu class
        MenuGui menu = new MenuGui();

//        headerLabel = new JLabel("", JLabel.CENTER);
//        statusLabel = new JLabel("",JLabel.CENTER);
//        statusLabel.setSize(350,100);

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

//        headerLabel.setText("Control in action: JTable");

        String[] columnNames = {"Name", "Salary"};
        Object[][] data = {
                {"Ramesh Raman", 5000},
                {"Shabbir Hussein", 7000}
        };
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Adjust color as needed

        scrollPane.setSize(200, 50);
        table.setFillsViewportHeight(true);
        controlPanel.add(scrollPane);

        setContentPane(controlPanel);
//        controlPanel.setLayout(null);
        setJMenuBar(menu);



        setVisible(true);
        pack();
        setSize(600, 600);
    }
}
