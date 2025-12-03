package com.pluralsight.deli.models.toppings;

import com.pluralsight.deli.enums.ingredients.Sauce;
import com.pluralsight.deli.enums.modifiers.SandwichSize;
import com.pluralsight.deli.models.core.Topping;

/**
 * Represents free sauce toppings that go ON the sandwich.
 *
 * FREE TOPPINGS:
 * Sauces are free, just like regular vegetable toppings.
 * Customers can add multiple sauces at no additional cost.
 *
 * Available sauces (that go ON the sandwich):
 * - MAYO (Mayonnaise)
 * - MUSTARD
 * - KETCHUP
 * - RANCH
 * - THOUSAND_ISLANDS
 * - VINAIGRETTE
 *
 * IMPORTANT DISTINCTION - SAUCE vs SIDE:
 * In v2.0, we separated sauces into two categories:
 *
 * 1. SauceTopping (THIS CLASS): Sauces that go ON/IN the sandwich
 *    - Examples: mayo, mustard, ketchup, ranch
 *    - These are FREE
 *    - Added directly to the sandwich
 *
 * 2. SideTopping: Items that come ON THE SIDE
 *    - Examples: au jus (for dipping), sauce on the side
 *    - These may COST MONEY (size-based pricing)
 *    - Served separately, not on the sandwich
 *
 * Why this matters:
 * In v1.0, AU_JUS was incorrectly in the Sauce enum. But au jus isn't spread
 * on the sandwich - it's served on the side for dipping (like for a French Dip).
 * In v2.0, AU_JUS moved to SideType enum and uses SideTopping class instead.
 *
 * NO "EXTRA" PORTIONS:
 * Like regular toppings, sauces don't have extra portions because:
 * - They're free, so "extra" wouldn't cost more
 * - Simpler customer experience
 * - Business logic: we charge for premium items, not condiments
 *
 * SIMILARITY TO RegularToppingItem:
 * Notice how SauceTopping and RegularToppingItem are almost identical:
 * - Both extend Topping
 * - Both are free (getPrice returns 0.0)
 * - Both don't support "extra"
 * - Both use an enum for their types
 *
 * You might ask: "Why not combine them into one 'FreeToppingItem' class?"
 * Good question! We keep them separate because:
 * 1. Business logic: sauces and vegetables are conceptually different
 * 2. Display: receipts should show "Sauces:" and "Toppings:" separately
 * 3. Extensibility: if business rules change (e.g., premium sauces cost money),
 *    we only need to modify one class
 *
 * @author Pluralsight Deli Team
 * @version 2.0 - Refactored to remove sandwichSize field, AU_JUS moved to SideTopping
 */
public class SauceTopping extends Topping {

    /**
     * The specific type of sauce (MAYO, MUSTARD, KETCHUP, etc.).
     *
     * NOTE: AU_JUS is NO LONGER in the Sauce enum as of v2.0.
     * It's now in SideType enum because it's served on the side, not on the sandwich.
     */
    private Sauce sauce;

    /**
     * Constructs a SauceTopping with the specified sauce type.
     *
     * KEY DESIGN DECISIONS:
     * 1. No 'extra' parameter - sauces don't have extra portions (hardcode false)
     * 2. No 'sandwichSize' parameter (v2.0) - size passed to getPrice() instead
     *
     * @param sauce the type of sauce (e.g., MAYO, MUSTARD, RANCH)
     */
    public SauceTopping(Sauce sauce) {
        // Always pass false for 'extra' - sauces don't have extra portions
        super(false);
        this.sauce = sauce;
    }

    /**
     * Returns the price for this sauce (always $0.00 since it's free).
     *
     * FREE PRICING:
     * All sauces that go ON the sandwich are free, regardless of sandwich size.
     *
     * DESIGN NOTE:
     * We accept a 'size' parameter to match the abstract method signature in
     * Topping, even though we don't use it. This allows polymorphic behavior -
     * all toppings can be priced the same way in a loop:
     *
     * for (Topping t : toppings) {
     *     price += t.getPrice(sandwichSize);  // Works for all topping types!
     * }
     *
     * @param size the size of the sandwich (not used for sauce pricing)
     * @return 0.0 (sauces are free)
     */
    @Override
    public double getPrice(SandwichSize size) {
        // Sauces on the sandwich are FREE
        // (But side items like au jus may cost money - see SideTopping class)
        return 0.0;
    }

    /**
     * Gets the display name of this sauce.
     *
     * DELEGATION:
     * We delegate to the Sauce enum's getDisplayName() method.
     * The enum is the single source of truth for sauce names.
     *
     * @return the sauce's display name (e.g., "Mayo", "Mustard", "Ranch")
     */
    @Override
    public String getName() {
        return sauce.getDisplayName();
    }

    /**
     * Gets the type of sauce for this topping.
     *
     * Useful for:
     * - Grouping sauces separately on receipts
     * - UI filtering and display
     * - Analytics (e.g., "Ranch is our most popular sauce!")
     *
     * @return the Sauce enum value
     */
    public Sauce getSauce() {
        return sauce;
    }
}
