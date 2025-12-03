package com.pluralsight.deli.enums.ingredients;

/**
 * Enum representing types of sauces that go ON/IN the sandwich.
 *
 * SAUCE vs SIDE:
 * This enum contains sauces that are applied DIRECTLY to the sandwich.
 * For items served ON THE SIDE (like au jus), see SideType enum instead.
 *
 * ALL SAUCES ARE FREE:
 * Sauces in this enum are complimentary - customers can add as many as
 * they want at no additional cost. This is standard in the deli industry.
 *
 * AVAILABLE SAUCES:
 * - MAYO: Mayonnaise
 * - MUSTARD: Yellow or brown mustard
 * - KETCHUP: Tomato ketchup
 * - RANCH: Ranch dressing
 * - THOUSAND_ISLANDS: Thousand Island dressing
 * - VINAIGRETTE: Oil and vinegar based dressing
 *
 * VERSION HISTORY:
 * v1.0: Included AU_JUS in this enum
 * v2.0: Moved AU_JUS to SideType enum (it's served on the side, not on the sandwich)
 *
 * DESIGN PATTERN:
 * This enum uses the standard enum pattern for the deli system:
 * - Each constant has a display name for UI/receipts
 * - Constructor initializes the display name
 * - Getter provides access to display name
 * - toString() returns display name for easy printing
 *
 * @author Pluralsight Deli Team
 * @version 2.0 - Removed AU_JUS (moved to SideType)
 */
public enum Sauce {

    /** Mayonnaise - classic creamy sandwich sauce */
    MAYO("Mayo"),

    /** Mustard - tangy yellow or brown condiment */
    MUSTARD("Mustard"),

    /** Ketchup - tomato-based sauce */
    KETCHUP("Ketchup"),

    /** Ranch dressing - creamy buttermilk-based sauce */
    RANCH("Ranch"),

    /** Thousand Island dressing - sweet and tangy pink sauce */
    THOUSAND_ISLANDS("Thousand Islands"),

    /** Vinaigrette - oil and vinegar based dressing */
    VINAIGRETTE("Vinaigrette");

    // NOTE: AU_JUS removed in v2.0 - it's now in SideType enum
    // Au jus is served on the side for dipping, not spread on the sandwich

    /**
     * The human-readable name for display in the UI and on receipts.
     *
     * Using final ensures this value can't be changed after construction,
     * which is important for enums (they should be immutable).
     */
    private final String displayName;

    /**
     * Private constructor for enum constants.
     *
     * Enum constructors are always private because enum instances are
     * created at compile time, not runtime. You can't do:
     * Sauce newSauce = new Sauce("BBQ"); // This would be a compile error
     *
     * @param displayName the human-readable name for this sauce
     */
    Sauce(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Gets the display name for this sauce.
     *
     * Used in the UI and on receipts to show customer-friendly names
     * instead of enum constant names (e.g., "Mayo" instead of "MAYO").
     *
     * @return the human-readable name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Returns the display name when the enum is converted to a string.
     *
     * This makes the enum easier to work with:
     * - System.out.println(Sauce.MAYO) prints "Mayo"
     * - Instead of "MAYO" (which would be the default)
     *
     * @return the display name
     */
    @Override
    public String toString() {
        return displayName;
    }
}

