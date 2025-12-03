package com.pluralsight.deli.enums.modifiers;

/**
 * Enum representing drink sizes and their associated prices.
 *
 * WHAT IS A DRINK?
 * Drinks are beverages that customers can add to their order alongside sandwiches and chips.
 * Common options include sodas, iced tea, lemonade, juice, etc.
 *
 * PRICING STRATEGY:
 * Unlike sandwiches (which have complex pricing based on size + toppings), drinks use
 * simple fixed pricing based solely on size. The flavor doesn't affect the price.
 *
 * FIXED PRICES:
 * - SMALL: $2.00
 * - MEDIUM: $2.50
 * - LARGE: $3.00
 *
 * Price increases are linear ($0.50 per size upgrade), making it easy for customers
 * to understand the value proposition of upgrading.
 *
 * WHY STORE PRICE IN THE ENUM?
 * This enum demonstrates a design pattern where each enum constant stores associated data:
 * - displayName: How to show the size to users ("Small", "Medium", "Large")
 * - price: How much this size costs ($2.00, $2.50, $3.00)
 *
 * Benefits of this approach:
 * 1. CENTRALIZED: All drink pricing in one place (easy to update)
 * 2. TYPE-SAFE: Can't create invalid sizes or prices
 * 3. IMMUTABLE: Prices can't be accidentally changed at runtime
 * 4. SELF-DOCUMENTING: Looking at this enum tells you everything about drink pricing
 *
 * COMPARISON WITH SANDWICH PRICING:
 * SandwichSize enum stores multiple prices (base, meat, cheese, etc.)
 * DrinkSize enum only stores one price (the total drink cost)
 * This reflects the different complexity levels of these products.
 *
 * @author Pluralsight Deli Team
 * @version 2.0 - Moved to modifiers subpackage with comprehensive documentation
 */
public enum DrinkSize {

    /**
     * Small drink - 16 oz, $2.00.
     * Economy option for customers who want a beverage without spending too much.
     */
    SMALL("Small", 2.00),

    /**
     * Medium drink - 20 oz, $2.50.
     * Most popular size - good value and satisfying portion.
     * Only $0.50 more than small for 4 extra ounces.
     */
    MEDIUM("Medium", 2.50),

    /**
     * Large drink - 32 oz, $3.00.
     * Maximum size for very thirsty customers or those wanting the best value per ounce.
     * Best deal: $0.50 more than medium for 12 extra ounces!
     */
    LARGE("Large", 3.00);

    /**
     * The user-friendly display name for this drink size.
     *
     * Used in menus, receipts, and anywhere we show the size to customers.
     * Stored separately from the enum constant name for better presentation.
     */
    private final String displayName;

    /**
     * The fixed price for a drink of this size (in dollars).
     *
     * WHY FINAL?
     * Making this field final ensures price immutability - once a DrinkSize is
     * created with a price, that price cannot change. This prevents bugs and
     * ensures consistent pricing throughout the application.
     *
     * WHY DOUBLE?
     * We use double for monetary values in this educational project for simplicity.
     * In production software, you'd typically use BigDecimal for precise decimal
     * arithmetic (doubles can have floating-point rounding errors).
     */
    private final double price;

    /**
     * Private constructor for creating each enum constant with its display name and price.
     *
     * ENUM CONSTANT INITIALIZATION:
     * When the DrinkSize class loads, Java automatically calls this constructor three times:
     * - new DrinkSize("Small", 2.00) creates SMALL
     * - new DrinkSize("Medium", 2.50) creates MEDIUM
     * - new DrinkSize("Large", 3.00) creates LARGE
     *
     * After these three instances are created, no more DrinkSize instances can be made.
     * This is what makes enums special - fixed, immutable set of values.
     *
     * @param displayName the readable name to show customers
     * @param price the cost of this drink size in dollars
     */
    DrinkSize(String displayName, double price) {
        this.displayName = displayName;
        this.price = price;
    }

    /**
     * Gets the user-friendly display name for this drink size.
     *
     * Used throughout the UI to show readable size names.
     *
     * Example usage:
     * <pre>
     * DrinkSize size = DrinkSize.MEDIUM;
     * System.out.println(size.getDisplayName()); // Prints: "Medium"
     * </pre>
     *
     * @return the display name ("Small", "Medium", or "Large")
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Gets the price for a drink of this size.
     *
     * SIMPLE PRICING:
     * Unlike SandwichSize (which has separate methods for base price, meat price,
     * cheese price, etc.), DrinkSize only needs one price method because drink
     * pricing is straightforward - just one fixed price per size.
     *
     * Example usage:
     * <pre>
     * DrinkSize size = DrinkSize.LARGE;
     * double cost = size.getPrice(); // Returns: 3.00
     * System.out.println("Cost: $" + cost); // Prints: "Cost: $3.0"
     * </pre>
     *
     * @return the price in dollars (2.00, 2.50, or 3.00)
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns the display name when the enum is converted to a string.
     *
     * OVERRIDE BENEFIT:
     * Without this override, DrinkSize.MEDIUM.toString() would return "MEDIUM".
     * With this override, it returns "Medium" - much more user-friendly!
     *
     * This makes the enum convenient to use in string formatting:
     * <pre>
     * DrinkSize size = DrinkSize.SMALL;
     * System.out.println("You ordered a " + size + " drink");
     * // Prints: "You ordered a Small drink"
     * </pre>
     *
     * @return the display name for this drink size
     */
    @Override
    public String toString() {
        return displayName;
    }
}
