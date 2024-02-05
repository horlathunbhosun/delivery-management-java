# Delivery Application

## Project Description

## How to run the project
1. Clone the repository
2. Open the project in your favorite IDE preferably IntelliJ IDEA
3. create a database in MySQL with the name `deliver_management`
4. Run the `deliver_management.sql` file in the `src/main/resources` folder to create the tables and insert some data
5. Run the `Main` class in the `src/main/java` folder to start the application
6. You can now register and login as a customer, scheduler or driver
7. You can create a new delivery as a customer, assign a delivery to a driver as a scheduler and view your deliveries as a driver
8. You can also generate a Word file with all assignments for a specific day as a scheduler
9. You can also view the list of missions you have already completed as a driver
10. You can also modify your email address, password, cell phone number, and, if applicable, truck-related information as a user
11. You can also view the list of all deliveries sorted by date as a scheduler
12. You can also select products from a predefined list in the database as a customer
13. You can also specify the quantity in Kg, choose a delivery date, and provide the delivery address as a customer
14. You can also assign routes to drivers, where a route consists of destination points as a scheduler
15. You can also view the list of missions assigned to you as a driver
16. You can also view the list of missions you have already completed as a driver
17. You can also generate a Word file with all assignments for a specific day, including a justified title and detailed mission information as a scheduler
18. You can also modify your email address, password, cell phone number, and, if applicable, truck-related information as a user
19. You can also view the list of all deliveries sorted by date as a scheduler
20. You can also select products from a predefined list in the database as a customer
21. You can also specify the quantity in Kg, choose a delivery date, and provide the delivery address as a customer
22. You can also assign routes to drivers, where a route consists of destination points as a scheduler
23. You can also view the list of missions assigned to you as a driver
24. You can also view the list of missions you have already completed as a driver
25. You can also generate a Word file with all assignments for a specific day, including a justified title and detailed mission information as a scheduler
26. You can also modify your email address, password, cell phone number, and, if applicable, truck-related information as a user

### Overview
This mini project aims to contribute to the development of a goods delivery application for customers, schedulers, and drivers.

### User Authentication and Registration
- Upon launching the application, a window prompts the user to either register or authenticate.
- Registration allows users to input their email address, password, cell phone number, and role (customer, scheduler, or driver).
    - If the user is a driver, additional information such as truck registration number and truck capacity in Kg is required.
- Authenticated users can modify their email address, password, cell phone number, and, if applicable, truck-related information.

### Customer Functionality
- Customers can select products from a predefined list in the database.
- They specify the quantity in Kg, choose a delivery date, and provide the delivery address.

### Scheduler Functionality
- Schedulers can access a list of all deliveries sorted by date.
- They have the ability to assign routes to drivers, where a route consists of destination points.
- Schedulers can generate a Word file with all assignments for a specific day, including a justified title and detailed mission information.

### Optional Google OR-Tools Integration
- There is an optional question regarding the use of the Google OR-Tools library for automatic daily journey planning.

### Driver Functionality
- Drivers can view the list of missions assigned to them.
- They can also view the list of missions they have already completed.

## Constraints and Instructions
- The database management system used should be MySQL.
- The programming language used must be Java.
- Code should be pushed to the designated repository at the end of each session.

Feel free to ask if you have any specific questions or need further clarification on any aspect of the project!