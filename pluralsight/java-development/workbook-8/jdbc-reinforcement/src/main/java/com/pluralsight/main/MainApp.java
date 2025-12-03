package com.pluralsight.main;

import com.pluralsight.db.DataManager;
import com.pluralsight.models.CustomerOrderHistory;
import com.pluralsight.models.Shipper;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("User and Password are needed!");
            System.exit(1);
        }

        String username = args[0];
        String password = args[1];
        String url = "jdbc:mysql://localhost:3306/northwind";

        DataManager dataManager = new DataManager(url, username, password);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Insert Shipper"); // C
            System.out.println("2. Get Shipper by ID"); // R
            System.out.println("3. Get All Shippers"); // R
            System.out.println("4. Update Shipper Phone Number"); // U
            System.out.println("5. Delete Shipper"); // D
            System.out.println("6. Search Customer Order History");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();


            switch (choice) {
                case 1 -> {
                    System.out.print("Enter company name: ");
                    String companyName = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();
                    dataManager.insertShipper(companyName, phoneNumber);
                }
                case 2 -> {
                    System.out.print("Enter Shipper ID: ");
                    int shipperId = scanner.nextInt();
                    scanner.nextLine();

                    Shipper shipper = dataManager.getShipperById(shipperId);

                    if (shipper != null) {
                        System.out.println("Shipper ID: " + shipper.getShipperId());
                        System.out.println("Company Name: " + shipper.getCompanyName());
                        System.out.println("Phone Number: " + shipper.getPhoneNumber());
                    } else {
                        System.out.println("Shipper not found!");
                    }
                }
                case 3 -> {
                    List<Shipper> shippers = dataManager.getAllShippers();
                    for (Shipper s : shippers) {
                        System.out.println("Shipper ID: " + s.getShipperId());
                        System.out.println("Company Name: " + s.getCompanyName());
                        System.out.println("Phone Number: " + s.getPhoneNumber());
                        System.out.println();
                    }
                }
                case 4 -> {
                    System.out.print("Enter shipper ID: ");
                    int updateShipperId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new phone number: ");
                    String newPhoneNumber = scanner.nextLine();
                    dataManager.updateShipperPhoneNumber(updateShipperId, newPhoneNumber);
                }
                case 5 -> {
                    System.out.print("Enter shipper ID: ");
                    int deleteShipperId = scanner.nextInt();
                    scanner.nextLine();
                    dataManager.deleteShipper(deleteShipperId);
                }
                case 6 -> {
                    System.out.print("Enter Customer ID: ");
                    String customerId = scanner.nextLine();

                    List<CustomerOrderHistory> orderHistoryList = dataManager.searchCustomerOrderHistory(customerId);
                    for (CustomerOrderHistory orderHistory : orderHistoryList) {
                        System.out.println("Product Name: " + orderHistory.getProductName());
                        System.out.println("Total Quantity: " + orderHistory.getTotalQuantity());
                        System.out.println();
                    }
                }
                case 7 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }

        }
    }
}
