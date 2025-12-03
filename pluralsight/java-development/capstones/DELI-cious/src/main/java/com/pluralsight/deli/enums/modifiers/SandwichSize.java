package com.pluralsight.deli.enums.modifiers;

/**
 * Enum representing sandwich sizes and their associated pricing.
 *
 * STRATEGY PATTERN:
 * This enum encapsulates all size-based pricing logic in one place.
 * Instead of having pricing calculations scattered throughout the codebase,
 * we centralize them here. This is an implementation of the Strategy Pattern -
 * different sizes have different pricing strategies.
 *
 * SANDWICH SIZES:
 * - SMALL: 4" sandwich
 * - MEDIUM: 8" sandwich
 * - LARGE: 12" sandwich
 *
 * PRICING COMPONENTS:
 * Each size has different prices for:
 * 1. Base Price: The cost of bread and basic sandwich construction
 * 2. Meat Price: Cost for adding one type of meat
 * 3. Cheese Price: Cost for adding one type of cheese
 * 4. Extra Meat Price: Additional cost for extra meat
 * 5. Extra Cheese Price: Additional cost for extra cheese
 * 6. Side Price: Cost for side items (au jus, sauce on side) - NEW in v2.0
 *
 * WHY USE AN ENUM FOR PRICING?
 * Benefits of this approach:
 * 1. Type Safety: Can't create invalid sizes
 * 2. Centralized: All pricing in one place (easy to update)
 * 3. Immutable: Prices can't be accidentally changed
 * 4. Readable: Clear association between size and all its prices
 *
 * @author Pluralsight Deli Team
 * @version 2.0 - Added side pricing
 */
public enum SandwichSize {

    /** 4-inch sandwich - Small size */
    SMALL(4, 5.50, 1.00, 0.75, 0.50, 0.30, 0.50),

    /** 8-inch sandwich - Medium size */
    MEDIUM(8, 7.00, 2.00, 1.50, 1.00, 0.60, 0.75),

    /** 12-inch sandwich - Large size */
    LARGE(12, 8.50, 3.00, 2.25, 1.50, 0.90, 1.00);

    /** The length of the sandwich in inches */
    private final int inches;

    /** The base price for the sandwich (bread + assembly) */
    private final double basePrice;

    /** The price for adding a regular meat topping */
    private final double meatPrice;

    /** The price for adding a regular cheese topping */
    private final double cheesePrice;

    /** The additional price for extra meat */
    private final double extraMeatPrice;

    /** The additional price for extra cheese */
    private final double extraCheesePrice;

    /** The price for side items (au jus, sauce on side) - NEW in v2.0 */
    private final double sidePrice;

    /**
     * Constructor for SandwichSize enum.
     *
     * Note: Enum constructors are private by default.
     * You can't create new enum instances at runtime.
     *
     * @param inches the length of the sandwich in inches
     * @param basePrice the base price for the sandwich
     * @param meatPrice the price for regular meat
     * @param cheesePrice the price for regular cheese
     * @param extraMeatPrice the additional price for extra meat
     * @param extraCheesePrice the additional price for extra cheese
     * @param sidePrice the price for side items
     */
    SandwichSize(int inches, double basePrice, double meatPrice, double cheesePrice,
                 double extraMeatPrice, double extraCheesePrice, double sidePrice) {
        this.inches = inches;
        this.basePrice = basePrice;
        this.meatPrice = meatPrice;
        this.cheesePrice = cheesePrice;
        this.extraMeatPrice = extraMeatPrice;
        this.extraCheesePrice = extraCheesePrice;
        this.sidePrice = sidePrice;
    }

    /**
     * Gets the length of this sandwich size in inches.
     *
     * @return the number of inches (4, 8, or 12)
     */
    public int getInches() {
        return inches;
    }

    /**
     * Gets the base price for a sandwich of this size.
     *
     * The base price covers:
     * - Bread
     * - Assembly/preparation
     * - Basic sandwich construction
     *
     * This does NOT include toppings - those are added separately.
     *
     * @return the base price in dollars
     */
    public double getBasePrice() {
        return basePrice;
    }

    /**
     * Gets the price for a regular meat topping on this sandwich size.
     *
     * Examples:
     * - SMALL (4"): $1.00
     * - MEDIUM (8"): $2.00
     * - LARGE (12"): $3.00
     *
     * @return the regular meat price in dollars
     */
    public double getMeatPrice() {
        return meatPrice;
    }

    /**
     * Gets the price for a regular cheese topping on this sandwich size.
     *
     * Examples:
     * - SMALL (4"): $0.75
     * - MEDIUM (8"): $1.50
     * - LARGE (12"): $2.25
     *
     * @return the regular cheese price in dollars
     */
    public double getCheesePrice() {
        return cheesePrice;
    }

    /**
     * Gets the ADDITIONAL price for extra meat on this sandwich size.
     *
     * This is added ON TOP of the regular meat price.
     * Example for MEDIUM sandwich:
     * - Regular bacon: $2.00 (getMeatPrice)
     * - Extra bacon: +$1.00 (getExtraMeatPrice)
     * - Total: $3.00
     *
     * @return the extra meat surcharge in dollars
     */
    public double getExtraMeatPrice() {
        return extraMeatPrice;
    }

    /**
     * Gets the ADDITIONAL price for extra cheese on this sandwich size.
     *
     * This is added ON TOP of the regular cheese price.
     * Example for LARGE sandwich:
     * - Regular cheddar: $2.25 (getCheesePrice)
     * - Extra cheddar: +$0.90 (getExtraCheesePrice)
     * - Total: $3.15
     *
     * @return the extra cheese surcharge in dollars
     */
    public double getExtraCheesePrice() {
        return extraCheesePrice;
    }

    /**
     * Gets the price for a side item based on this sandwich size.
     *
     * NEW IN v2.0: Sides (au jus, sauce on the side) now have their own pricing.
     *
     * Examples:
     * - SMALL (4"): $0.50
     * - MEDIUM (8"): $0.75
     * - LARGE (12"): $1.00
     *
     * Why size-based pricing for sides?
     * - Larger sandwiches need more au jus for dipping
     * - More sauce is needed for larger portions
     * - Consistent with other topping pricing strategies
     *
     * @return the side item price in dollars
     */
    public double getSidePrice() {
        return sidePrice;
    }

    /**
     * Returns a string representation of this size showing inches.
     *
     * Format: [number]"
     * Examples: 4", 8", 12"
     *
     * This is used throughout the UI and receipts to display sandwich sizes.
     *
     * @return the size in inches with quote symbol
     */
    @Override
    public String toString() {
        return inches + "\"";
    }
}
