package com.pluralsight.deli.enums.ingredients;

/**
 * Enum representing the types of cheese available as sandwich toppings.
 *
 * PREMIUM TOPPING:
 * Like meat, cheese is a premium topping that costs extra beyond the base sandwich price.
 * However, cheese costs less than meat (it's a supporting ingredient, not the main protein).
 *
 * PRICING (see SandwichSize enum for actual values):
 * - 4" sandwich: $0.75 per cheese, +$0.30 for extra
 * - 8" sandwich: $1.50 per cheese, +$0.60 for extra
 * - 12" sandwich: $2.25 per cheese, +$0.90 for extra
 *
 * ALL CHEESES COST THE SAME:
 * Whether you choose American or Swiss, the price is the same.
 * This keeps the ordering process simple and fair.
 *
 * MULTIPLE CHEESES ALLOWED:
 * Customers can add multiple types of cheese to one sandwich (e.g., cheddar AND provolone).
 * Each cheese is charged separately. Some popular combinations:
 * - American + Provolone (classic Philly cheesesteak)
 * - Cheddar + Swiss (extra creamy)
 *
 * CHEESE OPTIONS:
 * We offer 4 classic cheese varieties that pair well with sandwiches:
 * - AMERICAN: Creamy, mild, melts perfectly
 * - PROVOLONE: Semi-hard Italian cheese, slightly sharp
 * - CHEDDAR: Sharp, tangy flavor, very popular
 * - SWISS: Mild, nutty flavor with characteristic holes
 *
 * WHY USE AN ENUM?
 * - TYPE SAFETY: Prevents typos like "Cheder" or "Swis"
 * - COMPILE-TIME CHECKING: Java verifies cheese types at compile time
 * - EASY MAINTENANCE: Adding new cheeses is straightforward
 * - CONSISTENT DATA: Same cheese types used everywhere in the app
 *
 * @author Pluralsight Deli Team
 * @version 2.0 - Moved to ingredients subpackage with comprehensive documentation
 */
public enum CheeseType {

    /**
     * American cheese - Processed cheese that melts smoothly and evenly.
     * Most popular choice for classic American sandwiches and burgers.
     * Mild, creamy flavor that doesn't overpower other ingredients.
     */
    AMERICAN("American"),

    /**
     * Provolone cheese - Semi-hard Italian cheese with smooth texture.
     * Traditional choice for Italian subs and Philly cheesesteaks.
     * Slightly sharp, smoky flavor that complements cured meats.
     */
    PROVOLONE("Provolone"),

    /**
     * Cheddar cheese - Firm cheese with sharp, tangy flavor.
     * Versatile cheese that pairs well with most meats and vegetables.
     * Especially good with bacon, ham, and roast beef.
     */
    CHEDDAR("Cheddar"),

    /**
     * Swiss cheese - Pale yellow cheese with distinctive holes (eyes).
     * Mild, slightly nutty and sweet flavor.
     * Classic pairing with ham, turkey, and mustard (think club sandwiches).
     */
    SWISS("Swiss");

    /**
     * The user-friendly display name for this cheese type.
     *
     * WHY STORE BOTH CONSTANT AND DISPLAY NAME?
     * - Constant (AMERICAN): Follows Java enum naming conventions (ALL_CAPS)
     * - Display ("American"): Proper capitalization for showing users
     *
     * This allows clean code structure while maintaining readable UI text.
     *
     * WHY FINAL?
     * The final keyword ensures that once a CheeseType is created with a display name,
     * that name cannot be changed. This prevents bugs and maintains data integrity.
     */
    private final String displayName;

    /**
     * Private constructor for creating each enum constant with its display name.
     *
     * HOW ENUM CONSTRUCTORS WORK:
     * When the CheeseType class is loaded into memory, Java automatically calls
     * this constructor once for each constant defined above:
     * - new CheeseType("American") creates AMERICAN
     * - new CheeseType("Provolone") creates PROVOLONE
     * - and so on...
     *
     * KEY POINT: Enum constructors are ALWAYS private (or package-private).
     * You cannot create new CheeseType instances at runtime - all instances
     * are defined at compile time.
     *
     * @param displayName the readable name to show in the user interface
     */
    CheeseType(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Gets the user-friendly display name for this cheese type.
     *
     * This method provides access to the readable cheese name for use in the UI,
     * receipts, and any user-facing text.
     *
     * Example usage:
     * <pre>
     * CheeseType cheese = CheeseType.CHEDDAR;
     * System.out.println(cheese.getDisplayName()); // Prints: "Cheddar"
     * </pre>
     *
     * @return the display name (e.g., "American", "Provolone", "Cheddar", "Swiss")
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Returns the display name when the enum is converted to a string.
     *
     * OVERRIDING toString():
     * Without this override, CheeseType.SWISS.toString() would return "SWISS".
     * With this override, it returns "Swiss" - much more readable!
     *
     * Benefits of overriding toString():
     * - More readable console output during debugging
     * - Cleaner text in logs and error messages
     * - Simplified string concatenation (no need to call getDisplayName())
     * - Consistent with how users see the cheese names
     *
     * Example:
     * <pre>
     * System.out.println("Selected: " + CheeseType.PROVOLONE); // Prints: "Selected: Provolone"
     * String receipt = cheese + " on " + bread;                 // "Cheddar on Wheat"
     * </pre>
     *
     * @return the display name for this cheese type
     */
    @Override
    public String toString() {
        return displayName;
    }
}
