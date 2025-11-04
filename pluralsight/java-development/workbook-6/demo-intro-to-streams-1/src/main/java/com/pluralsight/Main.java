package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Order> allOrders = new ArrayList<>();

        Order order1 = new Order(123, "Rahinur");
        order1.addItem(new LineItem(11, "Hamburger", 5, 10));
        order1.addItem(new LineItem(12, "French Fries", 3, 10));
        allOrders.add(order1);

        Order order2 = new Order(456, "Wardah");
        order2.addItem(new LineItem(13, "Dates", 3, 2));
        order2.addItem(new LineItem(14, "Watermelon", 4, 2));
        allOrders.add(order2);

        Order order3 = new Order(365, "Arsenii");
        order3.addItem(new LineItem(15, "Iphone 14", 1000, 1));
        order3.addItem(new LineItem(16, "Iphone case", 10, 2));
        allOrders.add(order3);

        List<Order> impulseOrders = new ArrayList<>();

        for (Order order : allOrders) {
            if (order.getTotal() < 25) {
                impulseOrders.add(order);
            }
        }

        for (Order impulseOrder : impulseOrders) {
            System.out.println(impulseOrder);
        }

        double salesTotalAllOrders = 0;

        for (Order order : allOrders) {
            salesTotalAllOrders += order.getTotal();
        }

        System.out.println("Total: $" + salesTotalAllOrders);
    }
}
