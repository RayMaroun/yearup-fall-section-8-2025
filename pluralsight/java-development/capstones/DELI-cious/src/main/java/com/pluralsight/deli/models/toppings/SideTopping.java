package com.pluralsight.deli.models.toppings;


import com.pluralsight.deli.enums.ingredients.SideType;
import com.pluralsight.deli.enums.modifiers.SandwichSize;
import com.pluralsight.deli.models.core.Topping;

/**
 * Represents a side item served alongside the sandwich (not on it).
 *
 * NEW IN v2.0:
 * This class was created to properly handle items like au jus that are served
 * ON THE SIDE rather than ON the sandwich. In v1.0, au jus was incorrectly
 * categorized as a sauce topping.
 *
 * WHAT ARE SIDE TOPPINGS?
 * Side toppings are items served in separate containers alongside the sandwich:
 * - AU_JUS: Savory beef broth for dipping (popular with French Dip sandwiches)
 * - SAUCE: Extra sauce served on the side (customer controls how much to use)
 *
 * WHY SEPARATE FROM SAUCE TOPPING?
 * --------------------------------
 * SauceTopping vs SideTopping:
 *
 * SauceTopping (mayo, mustard, etc.):
 * - Goes ON/IN the sandwich
 * - Applied during preparation
 * - FREE
 * - Examples: Mayo spread on bread, mustard on the meat
 *
 * SideTopping (au jus, sauce on side):
 * - Served in SEPARATE container
 * - Customer applies themselves
 * - FREE (included with sandwich)
 * - Examples: Au jus for dipping, extra sauce on the side
 *
 * FREE TOPPING:
 * Unlike meat and cheese (which are premium/paid), sides are FREE and included
 * with the sandwich at no additional cost, just like regular toppings and sauces.
 *
 * PRICING:
 * Sides are always $0.00 (included), regardless of sandwich size.
 * This matches the pricing table from the requirements:
 * "Sides - au jus - sauce: Included | Included | Included"
 *
 * Example: A large French Dip sandwich with au jus costs:
 * Base sandwich ($8.50) + Roast beef ($3.00) + Au jus side ($0.00) = $11.50
 *
 * NO "EXTRA" PORTIONS:
 * Unlike meat and cheese, sides don't have "extra" portions because:
 * 1. The side container size already matches the sandwich size
 * 2. If customers want more, they'd order another full side item
 * 3. Simpler pricing model
 *
 * DESIGN PATTERN:
 * Like other topping classes, this implements the Template Method pattern by
 * extending Topping and implementing getPrice() and getName().
 *
 * @author Pluralsight Deli Team
 * @version 2.0 - New class to separate sides from sauces
 */
public class SideTopping extends Topping {

    /**
     * The specific type of side item (AU_JUS or SAUCE).
     *
     * References the SideType enum for the specific side.
     */
    private SideType sideType;

    /**
     * Constructs a SideTopping with the specified side type.
     *
     * KEY DESIGN DECISIONS:
     * 1. No 'extra' parameter - sides don't have extra portions (hardcode false)
     * 2. No 'sandwichSize' parameter (v2.0 design) - size is passed to getPrice()
     *
     * Why no extra?
     * - The side container already matches the sandwich size
     * - Customers wanting more would order a second side item
     * - Keeps pricing simple and consistent
     *
     * @param sideType the type of side item (e.g., AU_JUS, SAUCE)
     */
    public SideTopping(SideType sideType) {
        // Always pass false for 'extra' - side items don't have extra portions
        super(false);
        this.sideType = sideType;
    }

    /**
     * Calculates the price of this side item (always $0.00 since sides are free).
     *
     * FREE PRICING:
     * According to the pricing table, sides (au jus, sauce on the side) are
     * INCLUDED with the sandwich at no additional cost, just like regular
     * toppings and sauces.
     *
     * WHY ACCEPT SIZE PARAMETER IF WE DON'T USE IT?
     * This method must match the signature defined in the abstract Topping class.
     * Even though we don't need the size for pricing, we must accept it to
     * implement the abstract method. This is a tradeoff in object-oriented design:
     * - Pro: All toppings have a consistent interface (polymorphism)
     * - Con: Some subclasses have unused parameters
     *
     * In this case, the consistency is worth it because it allows us to treat
     * all toppings uniformly when calculating sandwich prices.
     *
     * @param size the size of the sandwich (not used for side pricing)
     * @return 0.0 (sides are free/included)
     */
    @Override
    public double getPrice(SandwichSize size) {
        // Sides are FREE - included with the sandwich at no additional cost
        // This matches the pricing table: "Sides - Included | Included | Included"
        return 0.0;
    }

    /**
     * Gets the display name of this side item.
     *
     * DELEGATION PATTERN:
     * Rather than storing the name ourselves, we delegate to the SideType enum.
     * The enum is the single source of truth for side item names.
     *
     * @return the side item's display name (e.g., "Au Jus", "Sauce")
     */
    @Override
    public String getName() {
        return sideType.getDisplayName();
    }

    /**
     * Gets the type of this side item.
     *
     * Useful for:
     * - UI display and filtering
     * - Grouping on receipts (show all sides together)
     * - Business analytics (e.g., "How many au jus orders per day?")
     *
     * @return the SideType enum value
     */
    public SideType getSideType() {
        return sideType;
    }
}
