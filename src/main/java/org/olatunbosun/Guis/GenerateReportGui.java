package org.olatunbosun.Guis;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateReportGui extends JFrame implements ActionListener {


    public GenerateReportGui() {
        super("Generate Report Page");

        // Add the components to the frame
        JPanel contentPane = new JPanel();

        //add menu bar
        MenuGui menuGui = new MenuGui();


        setJMenuBar(menuGui);
        setContentPane(contentPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
