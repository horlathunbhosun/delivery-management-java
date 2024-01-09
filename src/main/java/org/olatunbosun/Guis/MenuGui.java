package org.olatunbosun.Guis;

import javax.swing.*;

public class MenuGui extends JMenuBar {

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




    // Check the user role and add the corresponding menu
//        if ("admin".equals(userRole)) {
//        JMenu adminMenu = new JMenu("Admin");
//        JMenuItem adminItem = new JMenuItem("Admin Option");
//        adminMenu.add(adminItem);
//        add(adminMenu);
//    }


}
