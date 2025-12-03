package com.pluralsight.deli.enums;

/**
 * Enum representing predefined signature sandwich types.
 *
 * BONUS FEATURE:
 * This is part of the bonus challenge for the Deli application. Signature
 * sandwiches are pre-configured combinations of ingredients that are popular
 * or recommended by the deli.
 *
 * BENEFITS OF SIGNATURE SANDWICHES:
 * 1. Convenience: Customers don't have to customize every detail
 * 2. Quality: Chef-tested combinations that taste great
 * 3. Speed: Faster ordering process
 * 4. Upselling: Can showcase premium combinations
 *
 * DESIGN PATTERN:
 * This enum defines the "recipe" for each signature sandwich. Each constant
 * knows what bread and toppings should be included. The SignatureSandwich
 * class uses this information to build the actual sandwich object.
 *
 * HOW IT WORKS:
 * 1. Customer selects a signature sandwich type (e.g., BLT)
 * 2. Customer chooses size (SMALL, MEDIUM, or LARGE)
 * 3. SignatureSandwich class automatically adds the correct bread and toppings
 * 4. Customer can still customize (add/remove toppings, toast, etc.) if desired
 *
 * SIGNATURE SANDWICHES AVAILABLE:
 * - BLT: Classic bacon, lettuce, tomato
 * - PHILLY_CHEESESTEAK: Steak, provolone, peppers, onions
 * - ITALIAN: Ham, salami, provolone, lettuce, tomato, vinaigrette
 * - CLUB: Ham, chicken, bacon, swiss, lettuce, tomato, mayo
 *
 * @author Pluralsight Deli Team
 * @version 2.0 - Bonus feature implementation
 */
public enum SignatureSandwichType {

    /**
     * BLT - The classic Bacon, Lettuce, and Tomato sandwich.
     *
     * Ingredients:
     * - Bread: WHITE
     * - Meat: BACON
     * - Toppings: LETTUCE, TOMATOES
     * - Sauce: MAYO
     * - Typically toasted
     *
     * A timeless American sandwich favorite!
     */
    BLT("BLT", "Classic Bacon, Lettuce, and Tomato on toasted white bread with mayo"),

    /**
     * Philly Cheesesteak - Philadelphia's famous sandwich.
     *
     * Ingredients:
     * - Bread: WRAP (traditional hoagie substitute)
     * - Meat: STEAK
     * - Cheese: PROVOLONE
     * - Toppings: PEPPERS, ONIONS
     * - Typically NOT toasted (the steak is already hot)
     *
     * A hearty, savory sandwich inspired by Philadelphia street food.
     */
    PHILLY_CHEESESTEAK("Philly Cheesesteak", "Steak and provolone with grilled peppers and onions on a wrap"),

    /**
     * Italian Sub - A delicious Italian-American classic.
     *
     * Ingredients:
     * - Bread: WHEAT
     * - Meats: HAM, SALAMI (multiple meats!)
     * - Cheese: PROVOLONE
     * - Toppings: LETTUCE, TOMATOES, ONIONS
     * - Sauce: VINAIGRETTE
     * - Typically NOT toasted
     *
     * Packed with flavor and perfect for Italian food lovers!
     */
    ITALIAN("Italian Sub", "Ham and salami with provolone, lettuce, tomatoes, onions, and vinaigrette"),

    /**
     * Club Sandwich - A triple-decker classic loaded with premium ingredients.
     *
     * Ingredients:
     * - Bread: WHITE
     * - Meats: HAM, CHICKEN, BACON (triple meat!)
     * - Cheese: SWISS
     * - Toppings: LETTUCE, TOMATOES
     * - Sauce: MAYO
     * - Typically toasted
     *
     * One of the most popular sandwiches - perfect for big appetites!
     */
    CLUB("Club Sandwich", "Ham, chicken, and bacon with swiss, lettuce, tomatoes, and mayo on toasted white");

    /**
     * The display name for this signature sandwich.
     * Used in menus and on receipts.
     */
    private final String displayName;

    /**
     * A brief description of the sandwich and its ingredients.
     * Helps customers understand what they're ordering.
     */
    private final String description;

    /**
     * Private constructor for enum constants.
     *
     * @param displayName the name to show in menus and receipts
     * @param description a brief description of ingredients and preparation
     */
    SignatureSandwichType(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    /**
     * Gets the display name of this signature sandwich.
     *
     * @return the sandwich name (e.g., "BLT", "Philly Cheesesteak")
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Gets the description of this signature sandwich.
     *
     * Useful for displaying in menus so customers know what's included.
     *
     * @return a description of ingredients and preparation
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the display name when converted to string.
     *
     * Makes the enum easier to print and log.
     *
     * @return the display name
     */
    @Override
    public String toString() {
        return displayName;
    }
}
