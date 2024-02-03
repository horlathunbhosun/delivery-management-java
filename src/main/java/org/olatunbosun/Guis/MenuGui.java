package org.olatunbosun.Guis;

import org.olatunbosun.session.SessionData;
import org.olatunbosun.session.SessionManager;
import org.olatunbosun.session.SessionManagerMain;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGui extends JMenuBar implements ActionListener {

    JMenu homeMenu,profileMenu,ordersMenu,missionOverviewMenu, driversMenu, logoutMenu, productsMenu;
    JMenuItem viewProfile, editProfile,viewOrders, createOrders, viewDeliverables, completeDeliverable, generateReport, assignOrdersToDrivers , viewAssignedOrders , addProduct, viewProducts, homeMenuItems, logoutMenuItems;
    Box horizontalBox;
    SessionData sessionData = SessionManagerMain.loadUserFromFile();

    public MenuGui(){


        homeMenu = new JMenu("Home");
        profileMenu = new JMenu("Profile");


        ordersMenu = new JMenu("Orders");
        productsMenu = new JMenu("Products");

        missionOverviewMenu = new JMenu("Mission Overview");
        driversMenu = new JMenu("Drivers");

        createOrders = new JMenuItem("Create Orders");
        viewOrders = new JMenuItem("View Orders");

        addProduct = new JMenuItem("Add Product");
        viewProducts = new JMenuItem("View Products");

        viewDeliverables = new JMenuItem("View Deliverables");

        completeDeliverable = new JMenuItem("Completed Deliverables");

        viewProfile = new JMenuItem("View Profile");
        editProfile = new JMenuItem("Edit Profile");
        generateReport = new JMenuItem("Generate Report");
        assignOrdersToDrivers = new JMenuItem("Assign Orders to Drivers");
        viewAssignedOrders = new JMenuItem("View Assigned Orders");

        homeMenuItems = new JMenuItem("Home");
        logoutMenuItems = new JMenuItem("Logout");


        viewProfile.addActionListener(this);
        editProfile.addActionListener(this);

        createOrders.addActionListener(this);
        viewOrders.addActionListener(this);

        profileMenu.add(viewProfile);
        profileMenu.add(editProfile);

        productsMenu.add(addProduct);
        productsMenu.add(viewProducts);
        addProduct.addActionListener(this);
        viewProducts.addActionListener(this);

        assignOrdersToDrivers.addActionListener(this);
        viewAssignedOrders.addActionListener(this);
        generateReport.addActionListener(this);

        ordersMenu.add(createOrders);
        ordersMenu.add(viewOrders);
        missionOverviewMenu.add(generateReport);
        missionOverviewMenu.add(assignOrdersToDrivers);
        missionOverviewMenu.add(viewAssignedOrders);
        driversMenu.add(viewDeliverables);
        driversMenu.add(completeDeliverable);
        homeMenu.add(homeMenuItems);
        homeMenuItems.addActionListener(this);

        logoutMenu = new JMenu("Logout");

        logoutMenu.add(logoutMenuItems);

        logoutMenuItems.addActionListener(this);

//         horizontalBox = Box.createHorizontalBox();

        //add session manager to the frame
        String userRole = sessionData.getRole();
        if (userRole.equals("customer")){
            profileMenu.setVisible(true);
            ordersMenu.setVisible(true);
            missionOverviewMenu.setVisible(false);
            driversMenu.setVisible(false);
            productsMenu.setVisible(false);

        }
        if (userRole.equals("scheduler")){
            profileMenu.setVisible(true);
            ordersMenu.setVisible(false);
            createOrders.setVisible(false);
            missionOverviewMenu.setVisible(true);
            driversMenu.setVisible(false);
            productsMenu.setVisible(true);

        }

        if (userRole.equals("driver")){
            profileMenu.setVisible(true);
            createOrders.setVisible(false);
            ordersMenu.setVisible(false);
            missionOverviewMenu.setVisible(false);
            driversMenu.setVisible(true);
            productsMenu.setVisible(false);

        }
        add(homeMenu);
        add(profileMenu);
        add(ordersMenu);
        add(productsMenu);
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

        if (e.getSource() == homeMenuItems){
            System.out.println("homeMenuItems");
            disposeCurrentFrame();
            new HomeGui();
        }
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

        if (e.getSource() == addProduct) {
            System.out.println("addProduct");
            disposeCurrentFrame();
            new AddProductGui();
        }

        if (e.getSource() == viewProducts) {
            System.out.println("viewProducts");
            disposeCurrentFrame();
            new ListProductsGui();
        }

        if (e.getSource() == viewOrders) {
            System.out.println("viewOrders");
            disposeCurrentFrame();
            if (sessionData.getRole().equals("customer")) {
                new ListOrderGuiCustomer();
            }else{
                new ListOrderSchedulerGui();
            }
        }

        if (e.getSource() == assignOrdersToDrivers) {
            System.out.println("assignOrdersToDrivers");
            disposeCurrentFrame();
            new ListDriversInfo();
        }
        if (e.getSource() == viewAssignedOrders) {
            System.out.println("viewAssignedOrders");
            disposeCurrentFrame();
            new ListAssignedOrders();
        }

        if (e.getSource() == generateReport) {
            System.out.println("viewDeliverables");
            disposeCurrentFrame();
            new GenerateReportGui();
        }

        if (e.getSource() == logoutMenuItems){
          String response =  SessionManagerMain.logoutUser(this);
            if (response.equals("Logout Successful")) {
                JOptionPane.showMessageDialog(this, "User successfully logged out", "Success", JOptionPane.INFORMATION_MESSAGE);
                disposeCurrentFrame();
                new LoginScreenGui();
            }else{
                JOptionPane.showMessageDialog(this,  response, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

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
