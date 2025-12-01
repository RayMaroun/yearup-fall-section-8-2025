package com.pluralsight;

import com.pluralsight.db.DataManager;
import com.pluralsight.model.Product;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("User and Password are needed to connect to the database!");
            System.exit(1);
        }

        String username = args[0];
        String password = args[1];

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/northwind");
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        DataManager dataManager = new DataManager(dataSource);
        List<Product> productList = dataManager.getAllProducts();

        productList.forEach(System.out::println);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a Product ID:");
        int productID = scanner.nextInt();

        Product product = dataManager.getProductById(productID);
        System.out.println(product);


    }
}
