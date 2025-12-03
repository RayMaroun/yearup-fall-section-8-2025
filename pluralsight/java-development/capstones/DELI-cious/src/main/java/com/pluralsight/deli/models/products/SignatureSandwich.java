package com.pluralsight.deli.models.products;

import com.pluralsight.deli.enums.*;
import com.pluralsight.deli.enums.ingredients.*;
import com.pluralsight.deli.enums.modifiers.SandwichSize;
import com.pluralsight.deli.models.toppings.*;

/**
 * Represents a pre-configured signature sandwich with preset ingredients.
 *
 * BONUS FEATURE - INHERITANCE DEMONSTRATION:
 * This class extends Sandwich to create specialized sandwiches with
 * predefined recipes. It demonstrates how inheritance can be used to create
 * specialized versions of a base class.
 *
 * INHERITANCE BENEFITS:
 * - Code Reuse: SignatureSandwich inherits all of Sandwich's functionality
 *   (getPrice, getDescription, addTopping, etc.)
 * - Specialization: We only add the logic needed to auto-configure toppings
 * - Polymorphism: SignatureSandwich IS-A Sandwich, so it can be used anywhere
 *   a Sandwich is expected
 *
 * HOW IT WORKS:
 * 1. Customer selects a signature type (BLT, PHILLY_CHEESESTEAK, etc.)
 * 2. Customer selects a size (SMALL, MEDIUM, or LARGE)
 * 3. Constructor calls super() to create base Sandwich with appropriate bread
 * 4. Constructor automatically adds all toppings for that signature
 * 5. Result: A fully-configured sandwich ready to order!
 *
 * CUSTOMIZATION STILL POSSIBLE:
 * Even after creating a signature sandwich, customers can:
 * - Add extra toppings
 * - Request toasting (or not)
 * - Make any other modifications
 *
 * This gives the best of both worlds: convenience + flexibility!
 *
 * DESIGN PATTERN:
 * This could be considered a Factory Method pattern - the constructor
 * "manufactures" different sandwich configurations based on the type.
 *
 * @author Pluralsight Deli Team
 * @version 2.0 - Bonus feature implementation
 */
public class SignatureSandwich extends Sandwich {

    /**
     * The type of signature sandwich this represents.
     * Stored so we can display it in descriptions/receipts.
     */
    private SignatureSandwichType signatureType;

    /**
     * Constructs a SignatureSandwich with preset ingredients based on the type.
     *
     * CONSTRUCTOR CHAINING:
     * Notice how we call super(size, breadType) first. This is constructor
     * chaining - we leverage the parent class's constructor before adding
     * our specialized logic.
     *
     * Steps:
     * 1. Determine bread type based on signature
     * 2. Call super constructor to create base sandwich
     * 3. Add all preset toppings for this signature
     * 4. Set toasted flag if appropriate
     *
     * @param signatureType the type of signature sandwich to create
     * @param size the size (SMALL, MEDIUM, or LARGE)
     */
    public SignatureSandwich(SignatureSandwichType signatureType, SandwichSize size) {
        // Call parent constructor with size and appropriate bread type
        super(size, getBreadTypeForSignature(signatureType));

        // Store the signature type for later reference
        this.signatureType = signatureType;

        // Add all the preset toppings based on signature type
        buildSignatureSandwich(signatureType);
    }

    /**
     * Determines the appropriate bread type for each signature sandwich.
     *
     * HELPER METHOD:
     * This is a static helper method that encapsulates the bread selection
     * logic. Making it static is appropriate since it doesn't depend on
     * instance state - it's a pure function based on input.
     *
     * @param signatureType the type of signature sandwich
     * @return the appropriate BreadType for that signature
     */
    private static BreadType getBreadTypeForSignature(SignatureSandwichType signatureType) {
        // Use a switch expression (modern Java syntax) to map signatures to bread types
        // Note: This could also use a map or be stored in the enum itself
        return switch (signatureType) {
            case BLT -> BreadType.WHITE;              // Classic white bread for BLT
            case PHILLY_CHEESESTEAK -> BreadType.WRAP; // Wrap simulates a hoagie
            case ITALIAN -> BreadType.WHEAT;           // Wheat bread for Italian sub
            case CLUB -> BreadType.WHITE;              // White bread for club
        };
    }

    /**
     * Builds the signature sandwich by adding all preset ingredients.
     *
     * PRIVATE HELPER METHOD:
     * This method encapsulates the complex logic of configuring each signature.
     * It's private because it's an implementation detail - external code doesn't
     * need to call this directly.
     *
     * SWITCH STATEMENT:
     * We use a switch to handle each signature type differently. Each case
     * adds the specific toppings for that signature.
     *
     * DESIGN NOTE:
     * In a more advanced implementation, you could:
     * - Store recipes in a configuration file
     * - Use the Builder pattern for more complex customizations
     * - Have the SignatureSandwichType enum return a list of toppings
     *
     * For this educational example, explicit switch cases make the logic
     * clear and easy to understand.
     *
     * @param signatureType the type of signature sandwich to build
     */
    private void buildSignatureSandwich(SignatureSandwichType signatureType) {
        switch (signatureType) {
            case BLT:
                // BLT: Bacon, Lettuce, Tomato with mayo on toasted white bread
                addTopping(new MeatTopping(MeatType.BACON, false));
                addTopping(new RegularToppingItem(RegularTopping.LETTUCE));
                addTopping(new RegularToppingItem(RegularTopping.TOMATOES));
                addTopping(new SauceTopping(Sauce.MAYO));
                setToasted(true);  // BLTs are typically toasted
                break;

            case PHILLY_CHEESESTEAK:
                // Philly: Steak with provolone, peppers, and onions
                addTopping(new MeatTopping(MeatType.STEAK, false));
                addTopping(new CheeseTopping(CheeseType.PROVOLONE, false));
                addTopping(new RegularToppingItem(RegularTopping.PEPPERS));
                addTopping(new RegularToppingItem(RegularTopping.ONIONS));
                setToasted(false);  // Philly cheesesteaks are usually not toasted
                break;

            case ITALIAN:
                // Italian Sub: Ham, salami, provolone, lettuce, tomato, onions, vinaigrette
                addTopping(new MeatTopping(MeatType.HAM, false));
                addTopping(new MeatTopping(MeatType.SALAMI, false));
                addTopping(new CheeseTopping(CheeseType.PROVOLONE, false));
                addTopping(new RegularToppingItem(RegularTopping.LETTUCE));
                addTopping(new RegularToppingItem(RegularTopping.TOMATOES));
                addTopping(new RegularToppingItem(RegularTopping.ONIONS));
                addTopping(new SauceTopping(Sauce.VINAIGRETTE));
                setToasted(false);  // Italian subs are typically served cold
                break;

            case CLUB:
                // Club: Ham, chicken, bacon with swiss, lettuce, tomato, mayo
                addTopping(new MeatTopping(MeatType.HAM, false));
                addTopping(new MeatTopping(MeatType.CHICKEN, false));
                addTopping(new MeatTopping(MeatType.BACON, false));
                addTopping(new CheeseTopping(CheeseType.SWISS, false));
                addTopping(new RegularToppingItem(RegularTopping.LETTUCE));
                addTopping(new RegularToppingItem(RegularTopping.TOMATOES));
                addTopping(new SauceTopping(Sauce.MAYO));
                setToasted(true);  // Club sandwiches are typically toasted
                break;
        }
    }

    /**
     * Gets a description of this sandwich, overriding the parent method.
     *
     * POLYMORPHISM - METHOD OVERRIDING:
     * This method overrides Sandwich's getDescription() to provide a
     * signature-specific description. When you call getDescription() on a
     * SignatureSandwich object, THIS method runs, not the parent's.
     *
     * We still call super.getDescription() to reuse the parent's logic,
     * but we enhance it with the signature name.
     *
     * @return a description that includes the signature name
     */
    @Override
    public String getDescription() {
        // Call parent's getDescription() and enhance it
        return signatureType.getDisplayName() + " Signature - " + super.getDescription();
    }

    /**
     * Gets the signature type of this sandwich.
     *
     * @return the SignatureSandwichType enum value
     */
    public SignatureSandwichType getSignatureType() {
        return signatureType;
    }
}
