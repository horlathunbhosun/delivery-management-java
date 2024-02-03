package org.olatunbosun.Guis;

import org.olatunbosun.session.SessionData;
import org.olatunbosun.session.SessionManagerMain;

import java.awt.*;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class HomeGui extends JFrame implements ActionListener {
    JLabel titlelabel, welcomelabel,emptyLabel, nameLabel, roleLabel;
    JButton loginbutton, registerbutton;
    SessionData sessionData = SessionManagerMain.loadUserFromFile();

    public HomeGui() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        welcomelabel = new JLabel("Welcome to Goods Delivery Application");
        welcomelabel.setFont(new Font("Serif", Font.PLAIN, 24));
        welcomelabel.setBorder(new EmptyBorder(16, 0, 37, 0));// top,left,bottom,right
        welcomelabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        emptyLabel = new JLabel("");
        emptyLabel.setBorder(new EmptyBorder(0, 0, 20, 0));

        String name = sessionData.getFullName();

        nameLabel = new JLabel("NAME: "+name.toUpperCase(Locale.ROOT));
        nameLabel.setFont(new Font("Serif", Font.PLAIN, 24));
        nameLabel.setBorder(new EmptyBorder(16, 0, 37, 0));// top,left,bottom,right
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        String role = sessionData.getRole();


        roleLabel = new JLabel("ROLE: "+role.toUpperCase(Locale.ROOT));
        roleLabel.setFont(new Font("Serif", Font.PLAIN, 24));
        roleLabel.setBorder(new EmptyBorder(16, 0, 37, 0));// top,left,bottom,right
        roleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);



        MenuGui menu = new MenuGui();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(159, 167, 192));
        this.add(panel);
        panel.add(welcomelabel);

        panel.add(emptyLabel);
        panel.add(nameLabel);
        panel.add(roleLabel);
        //set menu bar
        setJMenuBar(menu);
//        setLocationRelativeTo(null);

        this.setTitle("Delivery Application");
        this.setSize(800, 500);
        this.setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub


    }

}