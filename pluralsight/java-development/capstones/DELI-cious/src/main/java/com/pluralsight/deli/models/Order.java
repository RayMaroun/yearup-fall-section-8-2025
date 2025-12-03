package com.pluralsight.deli.models;

import com.pluralsight.deli.models.core.Product;
import com.pluralsight.deli.models.products.Chips;
import com.pluralsight.deli.models.products.Drink;
import com.pluralsight.deli.models.products.Sandwich;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a customer's order containing multiple products.
 *
 * COLLECTION USAGE:
 * This class uses an ArrayList to manage products. ArrayList is perfect here because:
 * - We need ordered items (customers want to see items in the order they added them)
 * - We need fast random access (for iteration and display)
 * - We don't know how many items will be in an order ahead of time
 *
 * POLYMORPHISM IN ACTION:
 * The products list stores Product objects, which can be any subclass:
 * Sandwich, Drink, or Chips. This demonstrates polymorphism - we can treat
 * different product types uniformly while they maintain their specific behaviors.
 *
 * BUSINESS RULES:
 * An order must:
 * 1. Contain at least one item
 * 2. If no sandwiches, must have chips or a drink
 *
 * This prevents empty orders and ensures value for customers.
 *
 * @author Pluralsight Deli Team
 * @version 2.0 - Updated for new package structure
 */
public class Order {

    /**
     * List of all products in this order.
     *
     * INTERFACE vs IMPLEMENTATION:
     * Notice we declare this as List<Product> (interface) rather than
     * ArrayList<Product> (implementation). This is good practice because:
     * - We can change the implementation later without affecting other code
     * - We're coding to the interface, not the implementation
     * - More flexible for future modifications
     *
     * POLYMORPHISM:
     * This list can contain Sandwich, Drink, and Chips objects all mixed
     * together. Each type implements Product's methods differently, but
     * we can work with them all uniformly through the Product interface.
     */
    private List<Product> products;

    /**
     * Constructs a new empty Order.
     *
     * Initializes the products list as an empty ArrayList.
     * Products are added later using addProduct().
     */
    public Order() {
        this.products = new ArrayList<>();
    }

    /**
     * Adds a product to this order.
     *
     * POLYMORPHISM:
     * This method accepts ANY Product subclass. Whether you pass a Sandwich,
     * Drink, or Chips object, it works the same way. This is polymorphism!
     *
     * @param product the product to add (Sandwich, Drink, or Chips)
     */
    public void addProduct(Product product) {
        products.add(product);
    }

    /**
     * Returns a copy of the products list.
     *
     * DEFENSIVE COPYING:
     * We return a new ArrayList containing the same products rather than
     * returning our internal list directly. This prevents external code
     * from modifying our internal state.
     *
     * Example of why this matters:
     * If we returned the actual list, someone could do:
     *   order.getProducts().clear(); // Would delete all products!
     *
     * By returning a copy, external modifications don't affect our data.
     *
     * @return a new list containing all products in this order
     */
    public List<Product> getProducts() {
        return new ArrayList<>(products);  // Return copy, not original
    }

    /**
     * Calculates the total price of all products in this order.
     *
     * POLYMORPHISM IN ACTION:
     * Each product type (Sandwich, Drink, Chips) calculates its price
     * differently, but we can sum them all the same way. The correct
     * getPrice() method is called for each object automatically!
     *
     * Example:
     * - Sandwich: Calculates base price + topping prices
     * - Drink: Returns fixed price based on size
     * - Chips: Returns fixed price
     *
     * We don't need to know or care which type each product is!
     *
     * @return the total price of all products in dollars
     */
    public double getTotalPrice() {
        double total = 0.0;
        // Enhanced for loop - iterates through each product
        for (Product product : products) {
            // Polymorphism: The correct getPrice() is called based on actual object type
            total += product.getPrice();
        }
        return total;
    }

    /**
     * Gets the number of products in this order.
     *
     * @return the count of products
     */
    public int getProductCount() {
        return products.size();
    }

    /**
     * Checks if this order is empty (has no products).
     *
     * @return true if order has no products, false otherwise
     */
    public boolean isEmpty() {
        return products.isEmpty();
    }

    /**
     * Checks if this order contains only sandwiches (no drinks or chips).
     *
     * INSTANCEOF OPERATOR:
     * The 'instanceof' operator checks if an object is an instance of a
     * specific class. This is runtime type checking - we're asking "is this
     * product actually a Sandwich object?"
     *
     * Logic: Loop through all products. If we find ANY non-Sandwich, return false.
     * If we get through the whole loop without finding a non-Sandwich, return true.
     *
     * @return true if all products are sandwiches, false otherwise
     */
    public boolean hasOnlySandwiches() {
        for (Product product : products) {
            // If this product is NOT a sandwich, return false
            if (!(product instanceof Sandwich)) {
                return false;
            }
        }
        // All products checked - they're all sandwiches!
        return true;
    }

    /**
     * Checks if this order contains at least one sandwich.
     *
     * EARLY RETURN:
     * Notice how we return immediately when we find a sandwich. This is
     * more efficient than checking all products - as soon as we find one
     * sandwich, we have our answer!
     *
     * @return true if order contains at least one sandwich, false otherwise
     */
    public boolean hasSandwiches() {
        for (Product product : products) {
            if (product instanceof Sandwich) {
                return true;  // Found a sandwich - we're done!
            }
        }
        // No sandwiches found
        return false;
    }

    /**
     * Validates this order according to business rules.
     *
     * BUSINESS LOGIC:
     * An order is valid if:
     * 1. It's not empty (must have at least one item)
     * 2. If it has no sandwiches, it must have chips or a drink
     *
     * Why these rules?
     * - Can't checkout empty orders
     * - If customer doesn't want a sandwich, they should at least get chips/drink
     * - Prevents accidental empty orders
     *
     * VALIDATION PATTERN:
     * This method encapsulates business logic in one place. The UI can call
     * this before checkout to ensure the order meets requirements.
     *
     * @return true if order is valid, false otherwise
     */
    public boolean isValid() {
        // Rule 1: Order must not be empty
        if (isEmpty()) {
            return false;
        }

        // Rule 2: If no sandwiches, must have chips or drink
        if (!hasSandwiches()) {
            // Check if we have at least one chip or drink
            for (Product product : products) {
                if (product instanceof Chips || product instanceof Drink) {
                    return true;  // Found chips or drink - order is valid!
                }
            }
            // No sandwiches AND no chips/drinks - invalid!
            return false;
        }

        // Order has sandwiches - it's valid!
        return true;
    }

    /**
     * Returns a formatted string representation of this order for display.
     *
     * STRINGBUILDER:
     * We use StringBuilder instead of String concatenation for performance.
     * StringBuilder is mutable and efficient for building strings in loops.
     *
     * String concatenation (slower):
     *   String s = "";
     *   for (...) { s = s + "text"; }  // Creates new String object each time!
     *
     * StringBuilder (faster):
     *   StringBuilder sb = new StringBuilder();
     *   for (...) { sb.append("text"); }  // Modifies same object
     *
     * POLYMORPHISM:
     * Notice how we treat Sandwich objects differently (detailed description)
     * but use getDescription() for other products. We use instanceof to
     * determine the type at runtime.
     *
     * TYPE CASTING:
     * ((Sandwich) product) is a type cast - we're telling Java "trust me,
     * this Product is actually a Sandwich, so let me call Sandwich-specific
     * methods on it". We can safely do this because we checked with instanceof first.
     *
     * Format:
     * Order Summary:
     * ==================================================
     * 1. [Product 1 details]
     *
     * 2. [Product 2 details]
     *
     * ==================================================
     * Total: $XX.XX
     *
     * @return formatted order summary string
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order Summary:\n");
        sb.append("=".repeat(50)).append("\n");  // Java 11+ String.repeat()

        int itemNumber = 1;
        for (Product product : products) {
            sb.append(itemNumber++).append(". ");

            // Special handling for sandwiches - show detailed breakdown
            if (product instanceof Sandwich) {
                // Cast to Sandwich to access getDetailedDescription()
                sb.append(((Sandwich) product).getDetailedDescription());
            } else {
                // For drinks and chips, simple description is fine
                sb.append(product.getDescription());
            }
            sb.append("\n\n");
        }

        sb.append("=".repeat(50)).append("\n");
        sb.append("Total: $").append(String.format("%.2f", getTotalPrice())).append("\n");

        return sb.toString();
    }
}
