package com.pluralsight.deli.models.core;

import com.pluralsight.deli.enums.modifiers.SandwichSize;

/**
 * Abstract base class for all sandwich toppings.
 *
 * DESIGN PATTERN: Template Method Pattern
 * This class defines the structure for all toppings while allowing subclasses to customize behavior.
 *
 * KEY DESIGN DECISION: Why doesn't Topping store sandwichSize?
 * ----------------------------------------------------------------
 * Originally, this class had a 'sandwichSize' field, but this created tight coupling and violated
 * the Single Responsibility Principle. Here's why the current design is better:
 *
 * OLD (Bad) Design:
 *    - Topping had: protected SandwichSize sandwichSize;
 *    - Constructor required: public Topping(SandwichSize size, boolean extra)
 *    - Problem: A topping like "bacon" doesn't inherently have a size. The price depends on
 *      the sandwich size, but that doesn't mean the topping should STORE that information.
 *    - This created tight coupling between Topping and Sandwich.
 *
 * NEW (Good) Design:
 *    - Topping does NOT store sandwichSize
 *    - Instead, getPrice() accepts size as a PARAMETER: getPrice(SandwichSize size)
 *    - Benefit: Toppings are now more flexible and follow better OOP principles
 *    - The Sandwich is responsible for knowing its own size and passing it when needed
 *
 * This demonstrates the DEPENDENCY INVERSION PRINCIPLE: high-level modules (Sandwich) should
 * not depend on low-level modules (Topping) storing their state.
 *
 * INHERITANCE HIERARCHY:
 * Topping (abstract)
 *    ├── MeatTopping (premium, size-based pricing)
 *    ├── CheeseTopping (premium, size-based pricing)
 *    ├── RegularToppingItem (free)
 *    ├── SauceTopping (free)
 *    └── SideTopping (premium, size-based pricing)
 *
 * @author Pluralsight Deli Team
 * @version 2.0 - Refactored to remove sandwichSize field
 */
public abstract class Topping {

    /**
     * Indicates whether this is an extra portion of the topping.
     *
     * Extra portions typically cost more for premium toppings (meat, cheese, sides).
     * Free toppings (regular vegetables, sauces) don't have extra portions.
     *
     * Example: Regular bacon vs. Extra bacon on a sandwich
     */
    protected boolean extra;

    /**
     * Constructor for creating a topping.
     *
     * Note: We removed sandwichSize from the constructor compared to version 1.0.
     * This makes toppings more independent and better follows OOP principles.
     *
     * @param extra whether this is an extra portion (only applies to premium toppings)
     */
    public Topping(boolean extra) {
        this.extra = extra;
    }

    /**
     * Calculate the price of this topping based on the sandwich size.
     *
     * TEMPLATE METHOD PATTERN: This abstract method forces each topping type to define
     * its own pricing logic, but they all follow the same interface.
     *
     * Pricing varies by topping type:
     * - MeatTopping: Size-based pricing (4"=$1.00, 8"=$2.00, 12"=$3.00) + extra charge
     * - CheeseTopping: Size-based pricing (4"=$0.75, 8"=$1.50, 12"=$2.25) + extra charge
     * - SideTopping: Size-based pricing + extra charge
     * - RegularToppingItem: FREE ($0.00)
     * - SauceTopping: FREE ($0.00)
     *
     * @param size the size of the sandwich this topping is on
     * @return the price for this topping in dollars
     */
    public abstract double getPrice(SandwichSize size);

    /**
     * Get the display name of this topping.
     *
     * @return the topping name (e.g., "Bacon", "Cheddar", "Lettuce", "Mayo")
     */
    public abstract String getName();

    /**
     * Check if this is an extra portion.
     *
     * @return true if this is an extra portion, false otherwise
     */
    public boolean isExtra() {
        return extra;
    }

    /**
     * Returns a formatted string representation of the topping.
     *
     * Automatically prefixes "Extra " for extra portions.
     *
     * Examples:
     * - Regular bacon: "Bacon"
     * - Extra bacon: "Extra Bacon"
     * - Lettuce: "Lettuce"
     *
     * @return formatted topping name
     */
    @Override
    public String toString() {
        return (extra ? "Extra " : "") + getName();
    }
}
