package com.pluralsight.db;

import com.pluralsight.model.Product;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private DataSource dataSource;

    public DataManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String productsQuery = "SELECT ProductID, ProductName, UnitPrice, UnitsInStock FROM Products";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(productsQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                do {
                    int productId = resultSet.getInt(1);
                    String productName = resultSet.getString(2);
                    double unitPrice = resultSet.getDouble(3);
                    int unitsInStock = resultSet.getInt(4);

                    products.add(new Product(productId, productName, unitPrice, unitsInStock));
                } while (resultSet.next());
            } else {
                System.out.println("No Matches");
            }

        } catch (Exception ex) {
            System.err.println("An error has occurred!");
            ex.printStackTrace();
        }
        return products;
    }

    public Product getProductById(int productId) {
        Product product = null;
        String productByIdQuery = "SELECT ProductID, ProductName, UnitPrice, UnitsInStock FROM Products WHERE ProductID = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(productByIdQuery)) {
            preparedStatement.setInt(1, productId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int productIdFromDb = resultSet.getInt(1);
                    String productName = resultSet.getString(2);
                    double unitPrice = resultSet.getDouble(3);
                    int unitsInStock = resultSet.getInt(4);

                    product = new Product(productIdFromDb, productName, unitPrice, unitsInStock);

                } else {
                    System.out.println("Product not found!");
                }
            }

        } catch (Exception ex) {
            System.err.println("An error has occurred!");
            ex.printStackTrace();
        }
        return product;
    }


}
