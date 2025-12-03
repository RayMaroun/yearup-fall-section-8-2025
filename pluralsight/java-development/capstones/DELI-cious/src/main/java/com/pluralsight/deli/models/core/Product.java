package com.pluralsight.deli.models.core;

/**
 * Abstract base class for all products that can be ordered in the deli system.
 *
 * This class demonstrates several Object-Oriented Programming (OOP) principles:
 *
 * 1. ABSTRACTION: This abstract class defines what a Product IS (it has a price and description)
 *    without specifying HOW different products calculate their prices. Each concrete product
 *    (Sandwich, Drink, Chips) will provide their own implementation.
 *
 * 2. POLYMORPHISM: Because all products inherit from this class, they can be treated uniformly.
 *    For example, an Order can contain a List<Product> and calculate the total without knowing
 *    whether each item is a Sandwich, Drink, or Chips.
 *
 * 3. ENCAPSULATION: By making this class abstract, we prevent direct instantiation. You can't
 *    create a generic "Product" - you must create a specific type like Sandwich or Drink.
 *
 * Why abstract methods?
 * - getPrice() and getDescription() are abstract because different products calculate prices
 *   differently (sandwiches based on size and toppings, drinks based on size, chips are fixed)
 * - Forcing subclasses to implement these methods ensures every product can be priced and described
 *
 * @author Pluralsight Deli Team
 * @version 2.0
 */
public abstract class Product {

    /**
     * Calculate and return the price of this product.
     *
     * Each product type implements its own pricing logic:
     * - Sandwiches: base price by size + topping prices
     * - Drinks: price based on size (small/medium/large)
     * - Chips: fixed price
     *
     * @return the total price of this product in dollars
     */
    public abstract double getPrice();

    /**
     * Get a human-readable description of this product.
     *
     * Used for displaying items in the UI and on receipts.
     * Should include key details like size, type, and major customizations.
     *
     * @return a string description of the product
     */
    public abstract String getDescription();

    /**
     * Provides a default string representation using the description.
     *
     * This toString() method delegates to getDescription(), which is an example
     * of the TEMPLATE METHOD PATTERN - the base class defines the structure
     * (toString calls getDescription) while subclasses provide the details
     * (each implements getDescription differently).
     *
     * @return string representation of this product
     */
    @Override
    public String toString() {
        return getDescription();
    }
}
