package com.pluralsight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();

/*        HashMap<Integer, String> idsAndNames = new HashMap<>();

        idsAndNames.put(123, "Raymond");
        idsAndNames.put(456, "Mohammad");*/

        Pair<Integer> twoNums = new Pair<>(63, 65);
        System.out.println(twoNums.getLeftThing());
        twoNums.swap();
        System.out.println(twoNums.getLeftThing());

        System.out.println("========================================");

        Pair<String> twoWords = new Pair<>("Me", "You");
        System.out.println(twoWords.getLeftThing());
        twoWords.swap();
        System.out.println(twoWords.getLeftThing());

        System.out.println("========================================");
        displayWithLabel("Name", "Raymond");
        displayWithLabel("Age", 55);

        System.out.println("========================================");

        Pair<Musician> duets = new Pair<>(new Cellist("Ray", "Cello")
                , new Violinist("Nati", "Violin"));

        duets.perform();

    }

/*    private static void displayWithLabel(String label, String value) {
        System.out.println(label + ": " + value);
    }

    private static void displayWithLabel(String label, int value) {
        System.out.println(label + ": " + value);
    }*/

    private static <T> void displayWithLabel(String label, T value) {
        System.out.println(label + ": " + value);
    }


}
