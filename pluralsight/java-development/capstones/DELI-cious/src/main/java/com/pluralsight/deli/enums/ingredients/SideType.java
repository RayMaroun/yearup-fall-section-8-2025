package com.pluralsight.deli.enums.ingredients;

/**
 * Enum representing types of side items that accompany sandwiches.
 *
 * WHAT ARE SIDES?
 * Sides are items served ALONGSIDE the sandwich, not ON it. They're served
 * in separate containers for dipping or adding as the customer prefers.
 *
 * DISTINCTION FROM OTHER TOPPINGS:
 * - SauceTopping: Goes ON/IN the sandwich (mayo, mustard) - FREE
 * - RegularToppingItem: Goes ON the sandwich (lettuce, tomato) - FREE
 * - SideTopping: Served SEPARATELY (au jus, extra sauce) - PAID (size-based)
 *
 * WHY DO SIDES COST MONEY?
 * Unlike sauces that go on the sandwich (which are free), sides require:
 * - Separate containers
 * - Extra portions of ingredients
 * - Additional preparation
 *
 * Therefore, they have size-based pricing similar to meat and cheese.
 *
 * SIDE TYPES:
 * 1. AU_JUS: A savory dipping broth, typically served with French Dip sandwiches
 *    - Customer dips the sandwich into the au jus
 *    - Very popular for roast beef sandwiches
 *
 * 2. SAUCE: A generic "sauce on the side" option
 *    - Could be extra mayo, ranch, or any sauce the customer wants separately
 *    - Gives customers flexibility to add as much or little as they want
 *
 * ENUM PATTERN:
 * This enum follows the same pattern as other type enums in the system:
 * - Each constant has a display name
 * - Constructor stores the display name
 * - getDisplayName() provides access
 * - toString() returns the display name for easy printing
 *
 * @author Pluralsight Deli Team
 * @version 2.0 - New in v2.0 to separate sides from sauces
 */
public enum SideType {

    /**
     * Au jus - a savory beef broth served on the side for dipping.
     *
     * Commonly ordered with:
     * - French Dip sandwiches
     * - Roast beef sandwiches
     * - Steak sandwiches
     *
     * Note: In v1.0, this was incorrectly categorized as a Sauce.
     * It's now properly categorized as a Side because it's served separately.
     */
    AU_JUS("Au Jus"),

    /**
     * Generic sauce on the side - any sauce served in a separate container.
     *
     * Examples:
     * - Extra mayo on the side
     * - Ranch for dipping
     * - Extra dressing
     *
     * This gives customers the option to control how much sauce they use.
     */
    SAUCE("Sauce");

    /**
     * The human-readable name for display in the UI and on receipts.
     */
    private final String displayName;

    /**
     * Private constructor for enum constants.
     *
     * Note: Enum constructors are always private (or package-private).
     * You can't create new enum instances at runtime - they're all
     * defined at compile time.
     *
     * @param displayName the human-readable name for this side type
     */
    SideType(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Gets the display name for this side type.
     *
     * @return the human-readable name (e.g., "Au Jus", "Sauce")
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Returns the display name when the enum is converted to a string.
     *
     * This makes printing and logging more readable:
     * - System.out.println(SideType.AU_JUS) prints "Au Jus"
     * - Instead of "AU_JUS" (the enum constant name)
     *
     * @return the display name
     */
    @Override
    public String toString() {
        return displayName;
    }
}
