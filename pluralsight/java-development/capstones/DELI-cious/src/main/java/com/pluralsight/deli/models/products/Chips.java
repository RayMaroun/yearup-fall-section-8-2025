package com.pluralsight.deli.models.products;

import com.pluralsight.deli.models.core.Product;

/**
 * Represents a bag of chips in the deli ordering system.
 *
 * INHERITANCE:
 * Chips extends Product, demonstrating the IS-A relationship.
 * Every Chips object IS-A Product and can be used polymorphically.
 *
 * SIMPLICITY:
 * This is the simplest product in the system:
 * - Fixed price (no size variations)
 * - Only one customization option (type/flavor)
 * - No complex pricing calculations
 *
 * This demonstrates that inheritance hierarchies can include both simple
 * and complex subclasses. Not every Product needs to be as complex as Sandwich!
 *
 * CONSTANTS:
 * We use a static final constant for the price. This is a best practice because:
 * 1. static: Shared across all Chips instances (saves memory)
 * 2. final: Cannot be changed (prevents accidental modification)
 * 3. ALL_CAPS naming convention indicates it's a constant
 *
 * If chip prices needed to change, you'd only update it in one place.
 *
 * @author Pluralsight Deli Team
 * @version 2.0
 */
public class Chips extends Product {

    /**
     * The fixed price for all chips, regardless of type.
     *
     * Why static final?
     * - static: One copy shared by all Chips objects (memory efficient)
     * - final: Immutable - price can't be accidentally changed at runtime
     * - This is a class-level constant, not an instance variable
     */
    private static final double CHIPS_PRICE = 1.50;

    /**
     * The type/flavor of chips (e.g., "BBQ", "Sour Cream & Onion", "Original").
     *
     * Unlike drinks, which have size-based pricing, all chip types cost the same.
     * The type is purely for customer preference and order tracking.
     */
    private String type;

    /**
     * Constructs a new bag of Chips with the specified type.
     *
     * @param type the flavor/type of chips (e.g., "Doritos", "Lays Original")
     */
    public Chips(String type) {
        this.type = type;
    }

    /**
     * Returns the price of chips.
     *
     * SIMPLE PRICING:
     * Unlike Sandwich (which calculates price based on size and toppings)
     * or Drink (which varies by size), Chips have a flat rate.
     *
     * This demonstrates that different Product subclasses can have
     * vastly different implementation complexity while sharing the same interface.
     *
     * @return the fixed price of $1.50
     */
    @Override
    public double getPrice() {
        return CHIPS_PRICE;
    }

    /**
     * Returns a formatted description of the chips for display purposes.
     *
     * Format: "[TYPE] Chips - $[PRICE]"
     * Example: "BBQ Chips - $1.50"
     *
     * @return a string description including type and price
     */
    @Override
    public String getDescription() {
        return type + " Chips - $" + String.format("%.2f", getPrice());
    }

    /**
     * Gets the type/flavor of these chips.
     *
     * @return the chip type as a string
     */
    public String getType() {
        return type;
    }
}
