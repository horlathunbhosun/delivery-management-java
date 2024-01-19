package org.olatunbosun.Guis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DynamicFormExample extends JFrame {
    private List<JPanel> formPanels;
    private JPanel mainPanel;
    private JButton addFormButton;
    private JButton submitButton;

    public DynamicFormExample() {
        super("Dynamic Form Example");
        formPanels = new ArrayList<>();
        mainPanel = new JPanel(new GridLayout(0, 1));
        addFormButton = new JButton("Add Form");
        submitButton = new JButton("Submit");

        addFormButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFormPanel();
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitFormData();
            }
        });

        mainPanel.add(addFormButton);
        mainPanel.add(submitButton);

        add(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addFormPanel() {
        JPanel formPanel = new JPanel(new GridLayout(0, 2));
        formPanel.setBorder(BorderFactory.createEtchedBorder());

        JTextField textField1 = new JTextField();
        JTextField textField2 = new JTextField();

        formPanel.add(new JLabel("Field 1:"));
        formPanel.add(textField1);
        formPanel.add(new JLabel("Field 2:"));
        formPanel.add(textField2);

        formPanels.add(formPanel);
        mainPanel.add(formPanel);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void submitFormData() {
        for (JPanel formPanel : formPanels) {
            Component[] components = formPanel.getComponents();
            for (Component component : components) {
                if (component instanceof JTextField) {
                    JTextField textField = (JTextField) component;
                    System.out.println(textField.getText());
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DynamicFormExample();
            }
        });
    }
}
