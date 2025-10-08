package com.pluralsight;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
/*        HashMap<Integer, Product> idsAndProducts = new HashMap<>();

        Product newProduct = new Product(1, "Potato", 123);
        idsAndProducts.put(newProduct.getProductID(), newProduct);

        System.out.println(idsAndProducts);*/

        HashMap<String, String> statesAndCapitals = new HashMap<>();
        statesAndCapitals.put("CT", "Hartford");
        statesAndCapitals.put("CA", "Sacramento");
        statesAndCapitals.put("WA", "Olympia");
        statesAndCapitals.put("TX", "Austin");
        statesAndCapitals.put("FL", "Tallahassee");

        System.out.println(statesAndCapitals);

        System.out.print("The capital of Texas is: ");
        System.out.println(statesAndCapitals.get("TX"));

        statesAndCapitals.remove("WA");

        System.out.println(statesAndCapitals);

        System.out.println("========================");

        for (String value : statesAndCapitals.values()) {
            System.out.println(value);
        }

        System.out.println("========================");

        System.out.println(statesAndCapitals.values());

        System.out.println("========================");

        for (String key : statesAndCapitals.keySet()) {
            System.out.println(key + ": " + statesAndCapitals.get(key));
        }

    }
}
