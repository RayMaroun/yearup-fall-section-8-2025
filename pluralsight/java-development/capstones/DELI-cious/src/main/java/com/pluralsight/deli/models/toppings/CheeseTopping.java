package com.pluralsight.deli.models.toppings;

import com.pluralsight.deli.enums.ingredients.CheeseType;
import com.pluralsight.deli.enums.modifiers.SandwichSize;
import com.pluralsight.deli.models.core.Topping;

/**
 * Represents a premium cheese topping with size-based pricing.
 *
 * PREMIUM TOPPING:
 * Like meat, cheese is a premium topping that costs extra money.
 * The price varies based on sandwich size and whether it's an extra portion.
 *
 * PRICING STRUCTURE:
 * Regular cheese prices (per sandwich size):
 * - 4" (SMALL): $0.75
 * - 8" (MEDIUM): $1.50
 * - 12" (LARGE): $2.25
 *
 * Extra cheese prices (additional charge for "extra" portion):
 * - 4" (SMALL): +$0.30
 * - 8" (MEDIUM): +$0.60
 * - 12" (LARGE): +$0.90
 *
 * Example: A medium sandwich with extra cheddar costs:
 * Base sandwich ($7.00) + Regular cheddar ($1.50) + Extra cheddar ($0.60) = $9.10
 *
 * COMPARISON WITH MEATTOPPING:
 * Notice how CheeseTopping and MeatTopping have nearly identical structure.
 * They both:
 * - Extend Topping
 * - Have a type field (CheeseType vs MeatType)
 * - Delegate pricing to SandwichSize enum
 * - Support extra portions
 *
 * The only difference is the specific pricing methods they call. This demonstrates
 * good use of inheritance - shared structure in the parent class (Topping),
 * specific details in the subclasses.
 *
 * DESIGN IMPROVEMENT (v2.0):
 * Like MeatTopping, we removed the sandwichSize field and now accept size as
 * a parameter to getPrice(). This improves flexibility and follows SOLID principles.
 *
 * @author Pluralsight Deli Team
 * @version 2.0 - Refactored to remove sandwichSize field
 */
public class CheeseTopping extends Topping {

    /**
     * The specific type of cheese (AMERICAN, PROVOLONE, CHEDDAR, or SWISS).
     *
     * Like meat types, all cheese types of the same size cost the same.
     * The type is for customer preference and order tracking.
     */
    private CheeseType cheeseType;

    /**
     * Constructs a CheeseTopping with the specified cheese type and portion size.
     *
     * IMPROVED CONSTRUCTOR (v2.0):
     * No longer accepts sandwichSize parameter - size is passed to getPrice() instead.
     *
     * @param cheeseType the type of cheese (e.g., CHEDDAR, SWISS)
     * @param extra true if this is an extra portion (costs more), false for regular portion
     */
    public CheeseTopping(CheeseType cheeseType, boolean extra) {
        // Call parent constructor with just the 'extra' flag
        super(extra);
        this.cheeseType = cheeseType;
    }

    /**
     * Calculates the price of this cheese topping based on the sandwich size.
     *
     * PRICING STRATEGY:
     * - Extra portion: use SandwichSize.getExtraCheesePrice()
     * - Regular portion: use SandwichSize.getCheesePrice()
     *
     * REFACTORED DESIGN:
     * Instead of storing sandwichSize as a field (v1.0), we now receive it as
     * a parameter. Benefits:
     * 1. Looser coupling between Topping and Sandwich
     * 2. More testable (can test price calculation with different sizes easily)
     * 3. Follows Single Responsibility Principle
     * 4. Toppings don't need to "know" their sandwich size - they just calculate
     *    price when asked, based on whatever size is provided
     *
     * @param size the size of the sandwich this topping is on
     * @return the price for this cheese topping based on size and portion amount
     */
    @Override
    public double getPrice(SandwichSize size) {
        if (extra) {
            // Extra cheese costs additional money
            return size.getExtraCheesePrice();
        }
        // Regular cheese has standard pricing
        return size.getCheesePrice();
    }

    /**
     * Gets the display name of this cheese topping.
     *
     * DELEGATION PATTERN:
     * Rather than hardcoding cheese names here, we delegate to the CheeseType
     * enum. This keeps all cheese-related data centralized in one place.
     *
     * @return the cheese's display name (e.g., "Cheddar", "Swiss", "American")
     */
    @Override
    public String getName() {
        return cheeseType.getDisplayName();
    }

    /**
     * Gets the type of cheese for this topping.
     *
     * Useful for:
     * - Categorizing toppings in the UI
     * - Filtering or grouping on receipts
     * - Business analytics (e.g., "Which cheese is most popular?")
     *
     * @return the CheeseType enum value
     */
    public CheeseType getCheeseType() {
        return cheeseType;
    }
}
