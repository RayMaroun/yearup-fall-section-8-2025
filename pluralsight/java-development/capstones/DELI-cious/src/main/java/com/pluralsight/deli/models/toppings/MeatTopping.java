package com.pluralsight.deli.models.toppings;

import com.pluralsight.deli.enums.ingredients.MeatType;
import com.pluralsight.deli.enums.modifiers.SandwichSize;
import com.pluralsight.deli.models.core.Topping;

/**
 * Represents a premium meat topping with size-based pricing.
 *
 * PREMIUM TOPPING:
 * Meat is a premium topping, meaning it costs extra money (unlike free regular toppings).
 * The price varies based on both the sandwich size AND whether it's an extra portion.
 *
 * PRICING STRUCTURE:
 * Regular meat prices (per sandwich size):
 * - 4" (SMALL): $1.00
 * - 8" (MEDIUM): $2.00
 * - 12" (LARGE): $3.00
 *
 * Extra meat prices (additional charge for "extra" portion):
 * - 4" (SMALL): +$0.50
 * - 8" (MEDIUM): +$1.00
 * - 12" (LARGE): +$1.50
 *
 * Example: A large sandwich with extra bacon costs:
 * Base sandwich ($8.50) + Regular bacon ($3.00) + Extra bacon ($1.50) = $13.00
 *
 * DESIGN IMPROVEMENT (v2.0):
 * In version 1.0, this class stored sandwichSize as a field. This was poor design
 * because a topping shouldn't "own" a size - it should just calculate price based
 * on the size passed to it. Now, getPrice() accepts size as a parameter instead.
 *
 * DELEGATION PATTERN:
 * This class delegates pricing logic to the SandwichSize enum's methods:
 * - getMeatPrice() for regular portions
 * - getExtraMeatPrice() for extra portions
 *
 * This keeps pricing logic centralized and easy to update.
 *
 * @author Pluralsight Deli Team
 * @version 2.0 - Refactored to remove sandwichSize field
 */
public class MeatTopping extends Topping {

    /**
     * The specific type of meat (STEAK, HAM, SALAMI, ROAST_BEEF, CHICKEN, or BACON).
     *
     * The meat type determines the display name but not the price.
     * All meats of the same size cost the same amount.
     */
    private MeatType meatType;

    /**
     * Constructs a MeatTopping with the specified meat type and portion size.
     *
     * KEY DESIGN CHANGE: Notice we NO LONGER pass sandwichSize to this constructor.
     * The sandwich will pass its size to getPrice() when calculating the total.
     *
     * @param meatType the type of meat (e.g., BACON, CHICKEN)
     * @param extra true if this is an extra portion (costs more), false for regular portion
     */
    public MeatTopping(MeatType meatType, boolean extra) {
        // Call parent constructor with just 'extra'
        // In v1.0, we also passed sandwichSize here (which was bad design)
        super(extra);
        this.meatType = meatType;
    }

    /**
     * Calculates the price of this meat topping based on the sandwich size.
     *
     * IMPROVED DESIGN:
     * Instead of using a stored sandwichSize field (v1.0), we now accept size
     * as a parameter. This makes the topping more flexible and follows better
     * OOP principles (loose coupling, Single Responsibility Principle).
     *
     * PRICING LOGIC:
     * - If this is an EXTRA portion: use SandwichSize.getExtraMeatPrice()
     * - If this is a REGULAR portion: use SandwichSize.getMeatPrice()
     *
     * The SandwichSize enum contains the actual price values, demonstrating
     * separation of concerns - the topping knows WHEN to charge, the enum
     * knows HOW MUCH to charge.
     *
     * @param size the size of the sandwich this topping is on
     * @return the price for this meat topping based on size and portion amount
     */
    @Override
    public double getPrice(SandwichSize size) {
        // Ternary operator: condition ? valueIfTrue : valueIfFalse
        // This is equivalent to: if (extra) return size.getExtraMeatPrice(); else return size.getMeatPrice();
        if (extra) {
            return size.getExtraMeatPrice();
        }
        return size.getMeatPrice();
    }

    /**
     * Gets the display name of this meat topping.
     *
     * DELEGATION: We delegate to MeatType enum's getDisplayName() method
     * rather than storing the name ourselves. The enum is the single source
     * of truth for meat names.
     *
     * @return the meat's display name (e.g., "Bacon", "Chicken", "Steak")
     */
    @Override
    public String getName() {
        return meatType.getDisplayName();
    }

    /**
     * Gets the type of meat for this topping.
     *
     * Useful for categorizing or filtering toppings.
     *
     * @return the MeatType enum value
     */
    public MeatType getMeatType() {
        return meatType;
    }
}
