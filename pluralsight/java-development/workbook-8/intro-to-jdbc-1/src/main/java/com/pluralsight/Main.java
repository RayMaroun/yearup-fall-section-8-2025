package com.pluralsight;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/world",
                "root",
                "P@ssw0rd"
        );

        Statement statement = connection.createStatement();

        String query = """
                SELECT Name, Population
                FROM City
                WHERE CountryCode = "USA";
                """;

        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
            /*String city = results.getString("Name");
            int population = results.getInt("Population");*/

            String city = results.getString(1);
            int population = results.getInt(2);

            System.out.println(city + ": " + population);
        }

        connection.close();
    }
}
