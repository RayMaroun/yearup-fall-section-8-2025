package com.pluralsight;

import java.util.*;

public class Main {
    public static void main(String[] args) {
/*        String[] names = new String[5];
        names[0] = "Raymond";
        names[1] = "Potato";

        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i]);
        }*/

        List<String> names = new ArrayList<>();
        names.add("Raymond");
        names.add("Potato");

        //System.out.println(names);

        for (String potato : names) {
            System.out.println(potato);
        }

        System.out.println("===================================");
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);

        System.out.println(numbers);

        System.out.println("===================================");
        List<Vehicle> cars = new ArrayList<>();

        cars.add(new Vehicle("SUV", 2026, "Black"));
        cars.add(new Vehicle("Benz", 2026, "Red"));

        //System.out.println(cars);

        for (Vehicle car : cars) {
            System.out.println(car);
        }

        System.out.println("===================================");

        List<String> kids = new ArrayList<>();

        kids.add("Natalie");
        kids.add("Brittany");
        kids.add("Zachary");

        for (String kid : kids) {
            System.out.println(kid);
        }

        System.out.println("The first kid is: " + kids.get(0));

        for (int i = 0; i < kids.size(); i++) {
            System.out.println((i + 1) + " : " + kids.get(i));
        }

        System.out.println("===================================");
        kids.remove(0);

        for (int i = 0; i < kids.size(); i++) {
            System.out.println((i + 1) + " : " + kids.get(i));
        }

        System.out.println("===================================");
        //kids.add("Natalie");
        kids.add(0, "Natalie");

        for (int i = 0; i < kids.size(); i++) {
            System.out.println((i + 1) + " : " + kids.get(i));
        }

        System.out.println("===================================");
        kids.set(2, "Zach");

        for (int i = 0; i < kids.size(); i++) {
            System.out.println((i + 1) + " : " + kids.get(i));
        }

        System.out.println("===================================");

        Collections.sort(kids, Collections.reverseOrder());

        for (int i = 0; i < kids.size(); i++) {
            System.out.println((i + 1) + " : " + kids.get(i));
        }

        System.out.println("===================================");
        kids.clear();

        for (int i = 0; i < kids.size(); i++) {
            System.out.println((i + 1) + " : " + kids.get(i));
        }
    }
}
