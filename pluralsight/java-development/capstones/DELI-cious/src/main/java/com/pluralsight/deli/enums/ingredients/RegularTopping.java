package com.pluralsight.deli.enums.ingredients;

/**
 * Enum representing regular (free) vegetable and fresh toppings for sandwiches.
 *
 * FREE TOPPINGS:
 * Unlike meat and cheese (premium toppings), regular toppings are INCLUDED with
 * every sandwich at no additional cost. Customers can add as many as they want!
 *
 * WHY ARE THESE FREE?
 * - Industry standard: Most delis include vegetables at no charge
 * - Encourages customization: Customers build their perfect sandwich
 * - Lower cost ingredients: Vegetables cost less than meat/cheese
 * - Promotes healthier eating: Free veggies encourage balanced sandwiches
 *
 * NO "EXTRA" PORTIONS:
 * Because regular toppings are free, we don't charge for "extra" amounts.
 * Customers get standard portions, but since there's no cost, there's no
 * upselling opportunity for extra servings.
 *
 * TOPPING CATEGORIES:
 * Our 9 regular topping options include:
 * - Fresh vegetables (lettuce, tomatoes, onions, cucumbers)
 * - Pickled items (pickles, jalapeños)
 * - Flavor enhancers (peppers, mushrooms)
 * - Premium vegetable (guacamole - typically costs extra, but we include it!)
 *
 * NAMING NOTE:
 * This enum is called "RegularTopping" (singular) because it represents
 * ONE TYPE of topping from the regular category. Each enum constant is
 * a single topping type (LETTUCE is one regular topping type).
 *
 * @author Pluralsight Deli Team
 * @version 2.0 - Moved to ingredients subpackage with comprehensive documentation
 */
public enum RegularTopping {

    /**
     * Lettuce - Crisp, fresh leafy green vegetable.
     * Adds crunch and freshness to sandwiches without overpowering other flavors.
     * Most popular free topping - requested on almost every sandwich.
     */
    LETTUCE("Lettuce"),

    /**
     * Bell Peppers - Sweet, crunchy peppers (typically green, red, or mixed).
     * Add color, crunch, and mild sweetness to sandwiches.
     * Popular on Italian subs and Philly cheesesteaks.
     */
    PEPPERS("Peppers"),

    /**
     * Onions - Sliced white, red, or yellow onions (typically raw).
     * Add sharp, pungent flavor and crunchy texture.
     * Can be overwhelming for some, but beloved by onion fans.
     */
    ONIONS("Onions"),

    /**
     * Tomatoes - Fresh sliced tomatoes, juicy and slightly acidic.
     * Add moisture, freshness, and bright flavor to sandwiches.
     * Essential for BLTs and most classic deli sandwiches.
     */
    TOMATOES("Tomatoes"),

    /**
     * Jalapeños - Spicy pickled green peppers with kick.
     * Add heat and tangy flavor for customers who like it spicy.
     * WARNING: Not for everyone! Significantly spicier than regular peppers.
     */
    JALAPENOS("Jalapeños"),

    /**
     * Cucumbers - Cool, crisp, refreshing sliced cucumbers.
     * Add crunch and mild flavor without overpowering the sandwich.
     * Popular for lighter, fresher-tasting sandwiches.
     */
    CUCUMBERS("Cucumbers"),

    /**
     * Pickles - Tangy, crunchy pickled cucumbers (dill pickles).
     * Add salty, sour, briny flavor that complements rich meats.
     * Classic addition to deli sandwiches - love it or hate it!
     */
    PICKLES("Pickles"),

    /**
     * Guacamole - Creamy avocado spread with lime and seasonings.
     * PREMIUM VEGETABLE: Usually costs extra at most delis, but we include it FREE!
     * Adds rich, creamy texture and healthy fats to sandwiches.
     * Very popular despite being more expensive than other free toppings.
     */
    GUACAMOLE("Guacamole"),

    /**
     * Mushrooms - Sliced button or portobello mushrooms (typically sautéed).
     * Add earthy, umami flavor and meaty texture.
     * Popular with vegetarians and steak sandwich lovers.
     */
    MUSHROOMS("Mushrooms");

    /**
     * The user-friendly display name for this topping.
     *
     * SPECIAL CHARACTER NOTE:
     * Notice JALAPENOS constant uses "Jalapeños" with the tilde (ñ) in the display name.
     * This shows the correct Spanish spelling while keeping the enum constant simple
     * (Java identifiers work best with standard ASCII characters).
     *
     * This is good internationalization (i18n) practice:
     * - Code uses ASCII (JALAPENOS)
     * - Users see proper spelling (Jalapeños)
     */
    private final String displayName;

    /**
     * Private constructor for creating each enum constant with its display name.
     *
     * ENUM INSTANTIATION:
     * When Java loads this enum class, it calls this constructor 9 times:
     * - new RegularTopping("Lettuce") for LETTUCE
     * - new RegularTopping("Peppers") for PEPPERS
     * - ... and so on for all 9 constants
     *
     * After this initial creation, no new RegularTopping instances can be made.
     *
     * @param displayName the readable name to show in menus and receipts
     */
    RegularTopping(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Gets the user-friendly display name for this topping.
     *
     * Used throughout the application to show readable topping names.
     *
     * Example usage:
     * <pre>
     * RegularTopping topping = RegularTopping.JALAPENOS;
     * System.out.println(topping.getDisplayName()); // Prints: "Jalapeños"
     * </pre>
     *
     * @return the display name (e.g., "Lettuce", "Jalapeños", "Guacamole")
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Returns the display name when the enum is converted to a string.
     *
     * OVERRIDE BENEFIT:
     * Makes enum instances print-friendly. Without this override:
     * - System.out.println(RegularTopping.JALAPENOS) would print "JALAPENOS"
     * With this override:
     * - System.out.println(RegularTopping.JALAPENOS) prints "Jalapeños"
     *
     * Much better for logs, receipts, and user-facing text!
     *
     * @return the display name for this topping
     */
    @Override
    public String toString() {
        return displayName;
    }
}
