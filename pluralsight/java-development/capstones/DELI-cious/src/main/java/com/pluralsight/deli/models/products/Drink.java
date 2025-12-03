package com.pluralsight.deli.models.products;

import com.pluralsight.deli.enums.modifiers.DrinkSize;
import com.pluralsight.deli.models.core.Product;

/**
 * Represents a drink product in the deli ordering system.
 *
 * INHERITANCE DEMONSTRATION:
 * This class extends Product, meaning it IS-A Product. This demonstrates the
 * "IS-A" relationship in Object-Oriented Programming.
 *
 * ENCAPSULATION:
 * The size and flavor fields are private, protecting the internal state.
 * External code can only access these through getter methods, giving us
 * control over how the data is accessed.
 *
 * POLYMORPHISM:
 * Because Drink extends Product, a Drink object can be used anywhere a Product
 * is expected. For example, in an Order's List<Product>, a Drink can be added
 * alongside Sandwiches and Chips.
 *
 * PRICING STRATEGY:
 * Drinks use size-based pricing defined in the DrinkSize enum:
 * - SMALL: $2.00
 * - MEDIUM: $2.50
 * - LARGE: $3.00
 *
 * This is an example of the STRATEGY PATTERN - the pricing logic is delegated
 * to the DrinkSize enum rather than being hardcoded in this class.
 *
 * @author Pluralsight Deli Team
 * @version 2.0
 */
public class Drink extends Product {

    /**
     * The size of the drink (SMALL, MEDIUM, or LARGE).
     * Size determines the price through the DrinkSize enum.
     */
    private DrinkSize size;

    /**
     * The flavor of the drink (e.g., "Coke", "Sprite", "Lemonade").
     * Flavor is for customer preference and doesn't affect price.
     */
    private String flavor;

    /**
     * Constructs a new Drink with the specified size and flavor.
     *
     * @param size the size of the drink (determines price)
     * @param flavor the flavor/type of drink (e.g., "Coke", "Orange Juice")
     */
    public Drink(DrinkSize size, String flavor) {
        this.size = size;
        this.flavor = flavor;
    }

    /**
     * Calculates the price of this drink based on its size.
     *
     * DELEGATION: This method delegates to the DrinkSize enum's getPrice() method.
     * This is cleaner than having a switch statement here and follows the
     * Single Responsibility Principle - DrinkSize is responsible for knowing
     * drink prices.
     *
     * @return the price of the drink in dollars
     */
    @Override
    public double getPrice() {
        return size.getPrice();
    }

    /**
     * Returns a formatted description of the drink for display purposes.
     *
     * Format: "[SIZE] [FLAVOR] - $[PRICE]"
     * Example: "MEDIUM Coke - $2.50"
     *
     * @return a string description including size, flavor, and price
     */
    @Override
    public String getDescription() {
        return size + " " + flavor + " - $" + String.format("%.2f", getPrice());
    }

    /**
     * Gets the size of this drink.
     *
     * @return the DrinkSize enum value
     */
    public DrinkSize getSize() {
        return size;
    }

    /**
     * Gets the flavor of this drink.
     *
     * @return the flavor as a string
     */
    public String getFlavor() {
        return flavor;
    }
}
