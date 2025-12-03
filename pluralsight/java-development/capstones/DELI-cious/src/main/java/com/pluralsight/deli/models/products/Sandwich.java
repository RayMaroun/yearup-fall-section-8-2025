package com.pluralsight.deli.models.products;

import com.pluralsight.deli.enums.ingredients.*;
import com.pluralsight.deli.enums.modifiers.SandwichSize;
import com.pluralsight.deli.models.core.Product;
import com.pluralsight.deli.models.core.Topping;
import com.pluralsight.deli.models.toppings.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a customizable sandwich product in the deli ordering system.
 *
 * COMPOSITION PATTERN:
 * A Sandwich "HAS-A" list of toppings. This demonstrates composition - building
 * complex objects by combining simpler ones. Instead of a massive Sandwich class
 * with all topping logic, we compose it from separate Topping objects.
 *
 * Benefits of composition:
 * - Flexibility: Easy to add new topping types
 * - Maintainability: Topping logic is separated into their own classes
 * - Reusability: Topping classes could be used for other products (wraps, salads)
 *
 * INHERITANCE:
 * Sandwich extends Product, demonstrating inheritance (IS-A relationship).
 * Every Sandwich IS-A Product and can be used polymorphically in orders.
 *
 * POLYMORPHISM IN ACTION:
 * The toppings list contains different types of Topping objects (MeatTopping,
 * CheeseTopping, RegularToppingItem, SauceTopping, SideTopping). We can treat
 * them all uniformly through the Topping interface, but each calculates its
 * price differently. This is polymorphism in action!
 *
 * CUSTOMIZATION OPTIONS:
 * - Size: SMALL (4"), MEDIUM (8"), LARGE (12")
 * - Bread: WHITE, WHEAT, RYE, WRAP
 * - Toasted: Yes/No
 * - Toppings: Multiple types (meat, cheese, vegetables, sauces, sides)
 *
 * PRICING CALCULATION:
 * Total price = Base price (by size) + Sum of all topping prices
 * Each topping calculates its own price based on the sandwich size.
 *
 * @author Pluralsight Deli Team
 * @version 2.0 - Refactored for new package structure and topping design
 */
public class Sandwich extends Product {

    /**
     * The size of the sandwich (SMALL, MEDIUM, or LARGE).
     * Size affects both base price and topping prices.
     */
    private SandwichSize size;

    /**
     * The type of bread used for the sandwich.
     * Does not affect price (all bread types cost the same).
     */
    private BreadType breadType;

    /**
     * Whether the sandwich should be toasted.
     * Toasting is free - it's a preparation preference, not a charged option.
     */
    private boolean toasted;

    /**
     * List of all toppings on this sandwich.
     *
     * POLYMORPHISM:
     * This list can contain any subclass of Topping:
     * - MeatTopping
     * - CheeseTopping
     * - RegularToppingItem
     * - SauceTopping
     * - SideTopping
     *
     * We can iterate through them all uniformly despite their differences.
     */
    private List<Topping> toppings;

    /**
     * Constructs a new Sandwich with the specified size and bread type.
     *
     * INITIALIZATION:
     * - toasted defaults to false (customer must explicitly request toasting)
     * - toppings starts as empty ArrayList (customer adds toppings separately)
     *
     * @param size the size of the sandwich (determines base price)
     * @param breadType the type of bread
     */
    public Sandwich(SandwichSize size, BreadType breadType) {
        this.size = size;
        this.breadType = breadType;
        this.toasted = false;  // Not toasted by default
        this.toppings = new ArrayList<>();  // Start with no toppings
    }

    /**
     * Adds a topping to this sandwich.
     *
     * IMPORTANT DESIGN NOTE (v2.0):
     * Toppings are created WITHOUT a sandwich size passed to their constructor.
     * Instead, when we calculate the price, we'll pass the sandwich's size
     * to each topping's getPrice(size) method. This is better OOP design!
     *
     * POLYMORPHISM:
     * This method accepts ANY Topping subclass. Whether it's meat, cheese,
     * vegetables, sauce, or sides - they all get added to the same list.
     *
     * @param topping the topping to add (can be any Topping subclass)
     */
    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    /**
     * Sets whether this sandwich should be toasted.
     *
     * Toasting is free and affects preparation but not price.
     *
     * @param toasted true to toast the sandwich, false otherwise
     */
    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    /**
     * Calculates the total price of this sandwich.
     *
     * PRICING FORMULA:
     * Total = Base Price + Sum of all topping prices
     *
     * KEY DESIGN IMPROVEMENT (v2.0):
     * We now pass 'this.size' as a parameter to each topping's getPrice() method.
     * In v1.0, toppings stored the size themselves, which was poor design.
     *
     * POLYMORPHISM IN ACTION:
     * Even though our toppings list contains different types (MeatTopping,
     * CheeseTopping, etc.), we can iterate through them all the same way.
     * Each topping knows how to calculate its own price!
     *
     * Example calculation for Medium sandwich:
     * - Base price: $7.00
     * - Bacon (meat): $2.00
     * - Cheddar (cheese): $1.50
     * - Lettuce (regular): $0.00
     * - Mayo (sauce): $0.00
     * - Au jus (side): $0.75
     * Total: $11.25
     *
     * @return the total price of the sandwich in dollars
     */
    @Override
    public double getPrice() {
        // Start with the base price (determined by size)
        double price = size.getBasePrice();

        // Add the price of each topping
        // KEY CHANGE in v2.0: We PASS size to getPrice() instead of
        // having each topping store it
        for (Topping topping : toppings) {
            price += topping.getPrice(this.size);  // Pass size as parameter!
        }

        return price;
    }

    /**
     * Returns a concise description of the sandwich for display.
     *
     * Format:
     * [SIZE] [BREAD] Sandwich (Toasted)
     *   Toppings: [topping1], [topping2], ...
     *
     * Example:
     * 8" Wheat Sandwich (Toasted)
     *   Toppings: Bacon, Cheddar, Lettuce, Mayo
     *
     * @return a formatted string description
     */
    @Override
    public String getDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(size).append(" ").append(breadType).append(" Sandwich");

        if (toasted) {
            sb.append(" (Toasted)");
        }

        if (!toppings.isEmpty()) {
            sb.append("\n  Toppings: ");
            for (int i = 0; i < toppings.size(); i++) {
                if (i > 0) sb.append(", ");
                sb.append(toppings.get(i).toString());
            }
        }

        return sb.toString();
    }

    /**
     * Returns a detailed description with pricing breakdown for receipts.
     *
     * ORGANIZED BY CATEGORY:
     * This method groups toppings by type for better readability:
     * - Meats (with prices)
     * - Cheeses (with prices)
     * - Regular Toppings (free - no prices shown)
     * - Sauces (free - no prices shown)
     * - Sides (with prices) - NEW in v2.0
     *
     * INSTANCEOF OPERATOR:
     * We use 'instanceof' to determine each topping's specific type and
     * categorize them accordingly. This is one valid use of instanceof,
     * though it does create some coupling.
     *
     * Alternative approach (more advanced): Could use the Visitor pattern
     * or double dispatch, but that would be over-engineering for this use case.
     *
     * Example output:
     * 8" Wheat Sandwich (Toasted)
     *   Base Price: $7.00
     *   Meats: Bacon (+$2.00)
     *   Cheese: Cheddar (+$1.50)
     *   Toppings: Lettuce, Tomatoes
     *   Sauces: Mayo
     *   Sides: Au Jus (+$0.75)
     *   Total: $11.25
     *
     * @return a formatted string with detailed pricing breakdown
     */
    public String getDetailedDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(size).append(" ").append(breadType).append(" Sandwich");

        if (toasted) {
            sb.append(" (Toasted)");
        }

        sb.append("\n  Base Price: $").append(String.format("%.2f", size.getBasePrice()));

        // Categorize toppings by type for organized display
        // Using separate lists makes the receipt more readable
        List<MeatTopping> meats = new ArrayList<>();
        List<CheeseTopping> cheeses = new ArrayList<>();
        List<RegularToppingItem> regularToppings = new ArrayList<>();
        List<SauceTopping> sauces = new ArrayList<>();
        List<SideTopping> sides = new ArrayList<>();  // NEW in v2.0

        // Sort all toppings into their respective categories
        for (Topping topping : toppings) {
            if (topping instanceof MeatTopping) {
                meats.add((MeatTopping) topping);
            } else if (topping instanceof CheeseTopping) {
                cheeses.add((CheeseTopping) topping);
            } else if (topping instanceof RegularToppingItem) {
                regularToppings.add((RegularToppingItem) topping);
            } else if (topping instanceof SauceTopping) {
                sauces.add((SauceTopping) topping);
            } else if (topping instanceof SideTopping) {  // NEW in v2.0
                sides.add((SideTopping) topping);
            }
        }

        // Display meats with their prices
        if (!meats.isEmpty()) {
            sb.append("\n  Meats: ");
            for (int i = 0; i < meats.size(); i++) {
                if (i > 0) sb.append(", ");
                sb.append(meats.get(i).toString());
                // Show price if > 0 (should always be true for meat)
                double meatPrice = meats.get(i).getPrice(this.size);
                if (meatPrice > 0) {
                    sb.append(" (+$").append(String.format("%.2f", meatPrice)).append(")");
                }
            }
        }

        // Display cheeses with their prices
        if (!cheeses.isEmpty()) {
            sb.append("\n  Cheese: ");
            for (int i = 0; i < cheeses.size(); i++) {
                if (i > 0) sb.append(", ");
                sb.append(cheeses.get(i).toString());
                // Show price if > 0 (should always be true for cheese)
                double cheesePrice = cheeses.get(i).getPrice(this.size);
                if (cheesePrice > 0) {
                    sb.append(" (+$").append(String.format("%.2f", cheesePrice)).append(")");
                }
            }
        }

        // Display regular toppings (no prices - they're free)
        if (!regularToppings.isEmpty()) {
            sb.append("\n  Toppings: ");
            for (int i = 0; i < regularToppings.size(); i++) {
                if (i > 0) sb.append(", ");
                sb.append(regularToppings.get(i).getName());
            }
        }

        // Display sauces (no prices - they're free)
        if (!sauces.isEmpty()) {
            sb.append("\n  Sauces: ");
            for (int i = 0; i < sauces.size(); i++) {
                if (i > 0) sb.append(", ");
                sb.append(sauces.get(i).getName());
            }
        }

        // Display sides with their prices - NEW in v2.0
        if (!sides.isEmpty()) {
            sb.append("\n  Sides: ");
            for (int i = 0; i < sides.size(); i++) {
                if (i > 0) sb.append(", ");
                sb.append(sides.get(i).toString());
                // Show price (sides cost money!)
                double sidePrice = sides.get(i).getPrice(this.size);
                if (sidePrice > 0) {
                    sb.append(" (+$").append(String.format("%.2f", sidePrice)).append(")");
                }
            }
        }

        sb.append("\n  Total: $").append(String.format("%.2f", getPrice()));

        return sb.toString();
    }

    /**
     * Gets the size of this sandwich.
     *
     * @return the SandwichSize enum value
     */
    public SandwichSize getSize() {
        return size;
    }

    /**
     * Gets the bread type of this sandwich.
     *
     * @return the BreadType enum value
     */
    public BreadType getBreadType() {
        return breadType;
    }

    /**
     * Checks if this sandwich is toasted.
     *
     * @return true if toasted, false otherwise
     */
    public boolean isToasted() {
        return toasted;
    }

    /**
     * Gets a copy of the toppings list.
     *
     * DEFENSIVE COPYING:
     * We return a new ArrayList instead of the original to prevent external
     * code from modifying our internal toppings list directly. This protects
     * the internal state of the Sandwich.
     *
     * If we returned 'return toppings;' directly, someone could do:
     * sandwich.getToppings().clear(); // This would remove all toppings!
     *
     * By returning a copy, external modifications don't affect our internal list.
     *
     * @return a new list containing all toppings
     */
    public List<Topping> getToppings() {
        return new ArrayList<>(toppings);  // Return a copy for safety
    }
}
