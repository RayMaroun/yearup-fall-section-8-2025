package com.pluralsight.deli.enums.ingredients;

/**
 * Enum representing the types of meat (protein) available as sandwich toppings.
 *
 * PREMIUM TOPPING:
 * Meats are premium toppings, meaning they cost extra money beyond the base sandwich price.
 * The cost varies based on sandwich size and whether the customer wants extra meat.
 *
 * PRICING (see SandwichSize enum for actual values):
 * - 4" sandwich: $1.00 per meat, +$0.50 for extra
 * - 8" sandwich: $2.00 per meat, +$1.00 for extra
 * - 12" sandwich: $3.00 per meat, +$1.50 for extra
 *
 * ALL MEATS COST THE SAME:
 * Whether you choose expensive steak or economical ham, the price is the same.
 * This simplifies pricing and ordering for customers.
 *
 * MULTIPLE MEATS ALLOWED:
 * Customers can add multiple types of meat to one sandwich (e.g., ham AND bacon).
 * Each meat is charged separately.
 *
 * MEAT OPTIONS:
 * We offer 6 different protein options to suit various tastes:
 * - STEAK: Premium beef option, popular for cheesesteaks
 * - HAM: Classic deli meat, mild flavor
 * - SALAMI: Italian-style cured meat, bold flavor
 * - ROAST_BEEF: Sliced roasted beef, rich flavor
 * - CHICKEN: Lighter protein option
 * - BACON: Crispy, smoky favorite that pairs well with other meats
 *
 * WHY USE AN ENUM?
 * - TYPE SAFETY: Can't accidentally order "Turky" (typo)
 * - VALIDATION: Only valid meat types can be selected
 * - CONSISTENCY: Same meat types across entire application
 * - EASY TO EXTEND: Adding new meats is simple and safe
 *
 * @author Pluralsight Deli Team
 * @version 2.0 - Moved to ingredients subpackage with comprehensive documentation
 */
public enum MeatType {

    /**
     * Steak - Premium sliced beef, often used for Philly cheesesteaks.
     * Popular choice for customers wanting a hearty, filling sandwich.
     */
    STEAK("Steak"),

    /**
     * Ham - Classic deli meat with mild, slightly sweet flavor.
     * Versatile option that pairs well with most cheeses and toppings.
     */
    HAM("Ham"),

    /**
     * Salami - Italian-style cured meat with bold, savory flavor.
     * Traditional choice for Italian subs and Mediterranean-style sandwiches.
     */
    SALAMI("Salami"),

    /**
     * Roast Beef - Slow-roasted beef sliced thin, rich beefy flavor.
     * Popular for French Dip sandwiches (served with au jus for dipping).
     */
    ROAST_BEEF("Roast Beef"),

    /**
     * Chicken - Lean protein option with mild flavor.
     * Good choice for customers wanting a lighter or healthier sandwich.
     */
    CHICKEN("Chicken"),

    /**
     * Bacon - Crispy, smoky pork strips.
     * Often added to sandwiches alongside other meats (e.g., BLT, club sandwich).
     * Very popular despite being a premium topping!
     */
    BACON("Bacon");

    /**
     * The user-friendly display name for this meat type.
     *
     * DISPLAY NAME vs CONSTANT NAME:
     * - Constant: ROAST_BEEF (follows Java naming: ALL_CAPS with underscores)
     * - Display: "Roast Beef" (readable, proper spacing for UI)
     *
     * This separation allows clean code AND clean user interface.
     */
    private final String displayName;

    /**
     * Private constructor for creating each enum constant with its display name.
     *
     * ENUM CONSTRUCTOR BEHAVIOR:
     * This constructor is called automatically when the enum class loads.
     * It's called once for each constant defined above.
     *
     * Example: Java internally calls:
     * - new MeatType("Steak") for STEAK constant
     * - new MeatType("Ham") for HAM constant
     * - etc.
     *
     * You CANNOT call this constructor yourself - enum instances are fixed at compile time.
     *
     * @param displayName the readable name to show users in the UI
     */
    MeatType(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Gets the user-friendly display name for this meat type.
     *
     * Used throughout the UI to show readable meat names instead of enum constant names.
     *
     * Example usage:
     * <pre>
     * MeatType meat = MeatType.ROAST_BEEF;
     * System.out.println(meat.getDisplayName()); // Prints: "Roast Beef"
     * </pre>
     *
     * @return the display name (e.g., "Steak", "Ham", "Roast Beef")
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Returns the display name when the enum is converted to a string.
     *
     * OVERRIDING toString():
     * By default, MeatType.ROAST_BEEF.toString() would return "ROAST_BEEF".
     * We override it to return "Roast Beef" for better readability.
     *
     * Benefits:
     * - Cleaner output in receipts and logs
     * - Consistent with user-facing text
     * - Simpler string concatenation in code
     *
     * Example:
     * <pre>
     * System.out.println("Meat: " + MeatType.BACON); // Prints: "Meat: Bacon"
     * </pre>
     *
     * @return the display name for this meat type
     */
    @Override
    public String toString() {
        return displayName;
    }
}
