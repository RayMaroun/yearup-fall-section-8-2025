package com.pluralsight.db;

import com.pluralsight.models.CustomerOrderHistory;
import com.pluralsight.models.Shipper;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private BasicDataSource dataSource;

    public DataManager(String url, String username, String password) {
        this.dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
    }

    public void insertShipper(String companyName, String phoneNumber) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO shippers (CompanyName, Phone) VALUES (?, ?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, companyName);
            preparedStatement.setString(2, phoneNumber);
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int shipperId = generatedKeys.getInt(1);
                    System.out.println("New Shipper ID: " + shipperId);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Shipper getShipperById(int shipperIdFromUser) {
        Shipper shipper = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM shippers WHERE ShipperID = ?")) {

            preparedStatement.setInt(1, shipperIdFromUser);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int shipperIdFromDB = resultSet.getInt("ShipperID");
                    String companyName = resultSet.getString("CompanyName");
                    String phoneNumber = resultSet.getString("Phone");

                    shipper = new Shipper(shipperIdFromDB, companyName, phoneNumber);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return shipper;
    }

    public List<Shipper> getAllShippers() {
        List<Shipper> shippers = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM shippers");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int shipperIdFromDB = resultSet.getInt("ShipperID");
                String companyName = resultSet.getString("CompanyName");
                String phoneNumber = resultSet.getString("Phone");

                Shipper shipper = new Shipper(shipperIdFromDB, companyName, phoneNumber);
                shippers.add(shipper);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return shippers;
    }

    public void updateShipperPhoneNumber(int shipperId, String newPhoneNumber) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE shippers SET Phone = ? WHERE ShipperID = ?")) {

            preparedStatement.setString(1, newPhoneNumber);
            preparedStatement.setInt(2, shipperId);
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void deleteShipper(int shipperId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM shippers WHERE ShipperID = ?")) {
            preparedStatement.setInt(1, shipperId);
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public List<CustomerOrderHistory> searchCustomerOrderHistory(String customerId) {
        List<CustomerOrderHistory> orderHistoryList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             CallableStatement callableStatement = connection.prepareCall("{CALL CustOrderHist(?)}")) {
            callableStatement.setString(1, customerId);

            try (ResultSet resultSet = callableStatement.executeQuery()) {
                while (resultSet.next()) {
                    String productName = resultSet.getString("ProductName");
                    int totalQuantity = resultSet.getInt("Total");

                    CustomerOrderHistory orderHistory = new CustomerOrderHistory(productName, totalQuantity);
                    orderHistoryList.add(orderHistory);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return orderHistoryList;
    }

}
