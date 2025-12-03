package com.pluralsight.deli.enums.ingredients;

/**
 * Enum representing the types of bread available for sandwiches.
 *
 * WHAT IS AN ENUM?
 * An enum (enumeration) is a special Java type that represents a fixed set of constants.
 * Enums are perfect when you have a known, limited set of values that won't change
 * (like days of the week, card suits, or in this case, bread types).
 *
 * WHY USE AN ENUM INSTEAD OF STRINGS?
 * Instead of using String values like "white", "wheat", "rye", we use an enum because:
 * 1. TYPE SAFETY: The compiler prevents typos - you can't accidentally use "Wite" instead of "WHITE"
 * 2. AUTOCOMPLETE: IDEs can suggest valid values
 * 3. REFACTORING: If you rename a constant, IDE can update all uses automatically
 * 4. NULL SAFETY: Enums can't be null (unless explicitly set to null)
 * 5. METHODS: Enums can have fields and methods (like getDisplayName())
 *
 * BREAD OPTIONS:
 * All bread types cost the same (no price difference). The customer chooses based on
 * personal preference and dietary needs:
 * - WHITE: Classic soft white bread, mild flavor
 * - WHEAT: Whole wheat bread, nuttier flavor, more fiber
 * - RYE: Dense bread with distinctive flavor, popular for deli sandwiches
 * - WRAP: Tortilla-style wrap, softer and more flexible than bread
 *
 * DESIGN PATTERN:
 * This enum follows the CONSTANT SPECIFIC METHOD pattern by storing a display name
 * for each bread type. This allows us to:
 * - Store the enum constant in ALL_CAPS (Java convention)
 * - Display a user-friendly name in the UI (Title Case)
 *
 * Example:
 * - Code uses: BreadType.WHITE
 * - User sees: "White"
 *
 * @author Pluralsight Deli Team
 * @version 2.0 - Moved to ingredients subpackage with comprehensive documentation
 */
public enum BreadType {

    /**
     * Classic white bread - soft texture, mild flavor.
     * Most popular choice for traditional sandwiches.
     */
    WHITE("White"),

    /**
     * Whole wheat bread - hearty texture, nutty flavor.
     * Healthier option with more fiber and nutrients.
     */
    WHEAT("Wheat"),

    /**
     * Rye bread - dense texture, distinctive tangy flavor.
     * Traditional choice for deli sandwiches like pastrami or corned beef.
     */
    RYE("Rye"),

    /**
     * Tortilla wrap - soft, flexible alternative to bread.
     * Good for customers who want a lighter option or different texture.
     */
    WRAP("Wrap");

    /**
     * The user-friendly display name for this bread type.
     *
     * WHY STORE A DISPLAY NAME?
     * - The enum constant (WHITE, WHEAT) follows Java naming conventions (ALL_CAPS)
     * - The display name ("White", "Wheat") is what we show to users
     * - This separation allows us to change display text without changing code
     *
     * WHY FINAL?
     * - final means the value can't be changed after the enum is created
     * - This ensures data integrity - a WHITE bread can't become "Wheat" at runtime
     */
    private final String displayName;

    /**
     * Private constructor for creating each enum constant with its display name.
     *
     * ENUM CONSTRUCTORS ARE ALWAYS PRIVATE:
     * You can't create new enum instances at runtime. All enum constants are
     * created when the class loads. The constructor is called once for each
     * constant defined above.
     *
     * Example: When BreadType class loads, Java calls:
     * - new BreadType("White") for WHITE constant
     * - new BreadType("Wheat") for WHEAT constant
     * - And so on...
     *
     * @param displayName the user-friendly name to display in the UI
     */
    BreadType(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Gets the user-friendly display name for this bread type.
     *
     * This method allows code to access the readable name instead of the enum constant name.
     *
     * Example usage:
     * <pre>
     * BreadType bread = BreadType.WHITE;
     * System.out.println(bread.getDisplayName()); // Prints: "White"
     * </pre>
     *
     * @return the display name (e.g., "White", "Wheat", "Rye", "Wrap")
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Returns the display name when the enum is converted to a string.
     *
     * OVERRIDING toString():
     * By default, enum.toString() returns the constant name (e.g., "WHITE").
     * We override it to return the display name ("White") instead.
     *
     * Benefits:
     * - More readable in logs and debug output
     * - Consistent with user-facing displays
     * - Simplifies string formatting in UI code
     *
     * Example:
     * <pre>
     * System.out.println(BreadType.WHITE);        // Prints: "White" (not "WHITE")
     * String text = "Bread: " + BreadType.WHEAT;  // Results in: "Bread: Wheat"
     * </pre>
     *
     * @return the display name for this bread type
     */
    @Override
    public String toString() {
        return displayName;
    }
}
