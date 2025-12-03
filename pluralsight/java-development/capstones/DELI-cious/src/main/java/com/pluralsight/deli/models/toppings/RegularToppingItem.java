package com.pluralsight.deli.models.toppings;

import com.pluralsight.deli.enums.ingredients.RegularTopping;
import com.pluralsight.deli.enums.modifiers.SandwichSize;
import com.pluralsight.deli.models.core.Topping;

/**
 * Represents free regular toppings (primarily vegetables and non-premium items).
 *
 * FREE TOPPINGS:
 * Unlike meat and cheese (premium toppings), regular toppings are FREE.
 * Customers can add as many as they want at no additional cost.
 *
 * Available regular toppings include:
 * - Vegetables: LETTUCE, TOMATOES, ONIONS, PEPPERS, CUCUMBERS, PICKLES, JALAPENOS, MUSHROOMS
 * - Premium vegetable: GUACAMOLE (note: still free in this system!)
 *
 * NO "EXTRA" PORTIONS:
 * Notice that the constructor doesn't accept an 'extra' parameter. Regular
 * toppings don't have extra portions because:
 * 1. They're already free, so "extra" wouldn't cost more anyway
 * 2. Simplifies the ordering process for customers
 * 3. Makes business sense - we charge extra for premium items (meat/cheese), not vegetables
 *
 * DESIGN PATTERNS:
 * - INHERITANCE: Extends Topping to fit into the topping hierarchy
 * - POLYMORPHISM: Can be used anywhere a Topping is expected
 * - TEMPLATE METHOD: Implements abstract methods from Topping
 *
 * WHY "RegularToppingItem" instead of "RegularTopping"?
 * The name "RegularTopping" is already used by the ENUM that defines the types
 * (LETTUCE, TOMATOES, etc.). To avoid naming conflicts, the CLASS is called
 * "RegularToppingItem". In an ideal world, we might rename the enum to
 * "RegularToppingType" and this class to "RegularTopping".
 *
 * @author Pluralsight Deli Team
 * @version 2.0 - Refactored to remove sandwichSize field
 */
public class RegularToppingItem extends Topping {

    /**
     * The specific type of regular topping (LETTUCE, TOMATOES, etc.).
     *
     * References the RegularTopping enum for the specific vegetable/item.
     */
    private RegularTopping toppingType;

    /**
     * Constructs a RegularToppingItem with the specified topping type.
     *
     * KEY DESIGN DECISIONS:
     * 1. No 'extra' parameter - regular toppings don't have extra portions
     *    (we hardcode 'false' when calling super constructor)
     * 2. No 'sandwichSize' parameter (v2.0 improvement) - size is passed to
     *    getPrice() when needed
     *
     * Why hardcode extra=false?
     * Since regular toppings are free, having "extra" would be meaningless.
     * We could allow it for inventory tracking, but for simplicity we don't.
     *
     * @param toppingType the type of regular topping (e.g., LETTUCE, TOMATOES)
     */
    public RegularToppingItem(RegularTopping toppingType) {
        // Always pass false for 'extra' - regular toppings don't have extra portions
        super(false);
        this.toppingType = toppingType;
    }

    /**
     * Returns the price for this topping (always $0.00 since it's free).
     *
     * CONSTANT PRICING:
     * Unlike MeatTopping and CheeseTopping, which vary by size, regular
     * toppings are always free regardless of sandwich size.
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
     * @param size the size of the sandwich (not used for regular toppings)
     * @return 0.0 (regular toppings are free)
     */
    @Override
    public double getPrice(SandwichSize size) {
        // Regular toppings are FREE regardless of sandwich size
        // We could have complex pricing here, but business rules say free = free!
        return 0.0;
    }

    /**
     * Gets the display name of this regular topping.
     *
     * DELEGATION TO ENUM:
     * The RegularTopping enum stores display names for each topping type.
     * We delegate to it rather than storing names ourselves.
     *
     * @return the topping's display name (e.g., "Lettuce", "Tomatoes", "Pickles")
     */
    @Override
    public String getName() {
        return toppingType.getDisplayName();
    }

    /**
     * Gets the type of this regular topping.
     *
     * Useful for:
     * - UI display and filtering
     * - Grouping on receipts
     * - Business analytics (e.g., "Which free topping is most popular?")
     *
     * @return the RegularTopping enum value
     */
    public RegularTopping getToppingType() {
        return toppingType;
    }
}
