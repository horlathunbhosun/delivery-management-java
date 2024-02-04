# Goods Delivery Application

## Project Description

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