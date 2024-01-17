package org.olatunbosun.Guis;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGui extends JMenuBar implements ActionListener {

    JMenu homeMenu,profileMenu,ordersMenu,missionOverviewMenu, driversMenu, logoutMenu;
    JMenuItem viewProfile, editProfile,viewOrders, createOrders, viewDeliverables, completeDeliverable, generateReport;
    Box horizontalBox;

    public MenuGui(){

        homeMenu = new JMenu("Home");
        profileMenu = new JMenu("Profile");
        ordersMenu = new JMenu("Orders");
        missionOverviewMenu = new JMenu("Mission Overview");
        driversMenu = new JMenu("Drivers");

        createOrders = new JMenuItem("Create Orders");
        viewOrders = new JMenuItem("View Orders");

        viewDeliverables = new JMenuItem("View Deliverables");

        completeDeliverable = new JMenuItem("Completed Deliverables");

        viewProfile = new JMenuItem("View Profile");
        editProfile = new JMenuItem("Edit Profile");
        generateReport = new JMenuItem("Generate Report");

        viewProfile.addActionListener(this);
        editProfile.addActionListener(this);
        profileMenu.add(viewProfile);
        profileMenu.add(editProfile);

        ordersMenu.add(createOrders);
        ordersMenu.add(viewOrders);
        missionOverviewMenu.add(generateReport);
        driversMenu.add(viewDeliverables);
        driversMenu.add(completeDeliverable);



        logoutMenu = new JMenu("Logout");

//         horizontalBox = Box.createHorizontalBox();


        add(homeMenu);
        add(profileMenu);
        add(ordersMenu);
        add(missionOverviewMenu);
        add(driversMenu);

//        add(Box.createHorizontalGlue());
        add(logoutMenu);
//        add(horizontalBox);


    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
//
        if (e.getSource() == viewProfile) {
            System.out.println("viewProfile");
            disposeCurrentFrame();
            new ProfileGui();
        }

        if (e.getSource() == editProfile) {
            System.out.println("editProfile");
            disposeCurrentFrame();
            new ProfileEditGui();
        }

        if (e.getSource() == createOrders) {
            System.out.println("createOrders");
            disposeCurrentFrame();
            new CreateOrderGui();
        }

//        if (e.getSource() == viewOrders) {
//            System.out.println("viewOrders");
//            disposeCurrentFrame();
//            new ViewOrderGui();
//        }

//        if (e.getSource() == viewDeliverables) {
//            System.out.println("viewDeliverables");
//            disposeCurrentFrame();
//            new ViewDeliverablesGui();
//        }

//        if (e.getSource() == completeDeliverable) {
//            System.out.println("completeDeliverable");
//            disposeCurrentFrame();
//            new CompleteDeliverableGui();
//        }

//        if (e.getSource() == generateReport) {
//            System.out.println("generateReport");
//            disposeCurrentFrame();
//            new GenerateReportGui();
//        }

//        if (e.getSource() == logoutMenu) {
//            System.out.println("logoutMenu");
//            disposeCurrentFrame();
//            new LoginGui();
//        }
    }

    private void disposeCurrentFrame() {
        SwingUtilities.getWindowAncestor(this).dispose();
    }
    // Check the user role and add the corresponding menu
//        if ("admin".equals(userRole)) {
//        JMenu adminMenu = new JMenu("Admin");
//        JMenuItem adminItem = new JMenuItem("Admin Option");
//        adminMenu.add(adminItem);
//        add(adminMenu);
//    }


}
