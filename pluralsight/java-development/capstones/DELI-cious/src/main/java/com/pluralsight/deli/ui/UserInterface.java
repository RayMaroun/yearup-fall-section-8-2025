package com.pluralsight.deli.ui;

import com.pluralsight.deli.enums.*;
import com.pluralsight.deli.enums.ingredients.*;
import com.pluralsight.deli.enums.modifiers.DrinkSize;
import com.pluralsight.deli.enums.modifiers.SandwichSize;
import com.pluralsight.deli.models.Order;
import com.pluralsight.deli.models.products.*;
import com.pluralsight.deli.models.toppings.*;
import com.pluralsight.deli.services.ReceiptFileManager;

import java.util.Scanner;

/**
 * Main user interface controller
 * Handles all screen interactions and user input
 */
public class UserInterface {
    private Scanner scanner;
    private Order currentOrder;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Main entry point - displays home screen
     */
    public void start() {
        displayWelcome();

        boolean running = true;
        while (running) {
            displayHomeScreen();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    startNewOrder();
                    break;
                case 0:
                    running = false;
                    System.out.println("\nThank you for visiting DELI-cious! Goodbye!");
                    break;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private void displayWelcome() {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("  ██████╗ ███████╗██╗     ██╗       ██████╗██╗ ██████╗ ██╗   ██╗███████╗");
        System.out.println("  ██╔══██╗██╔════╝██║     ██║      ██╔════╝██║██╔═══██╗██║   ██║██╔════╝");
        System.out.println("  ██║  ██║█████╗  ██║     ██║█████╗██║     ██║██║   ██║██║   ██║███████╗");
        System.out.println("  ██║  ██║██╔══╝  ██║     ██║╚════╝██║     ██║██║   ██║██║   ██║╚════██║");
        System.out.println("  ██████╔╝███████╗███████╗██║      ╚██████╗██║╚██████╔╝╚██████╔╝███████║");
        System.out.println("  ╚═════╝ ╚══════╝╚══════╝╚═╝       ╚═════╝╚═╝ ╚═════╝  ╚═════╝ ╚══════╝");
        System.out.println("\n             Welcome to DELI-cious Sandwiches!");
        System.out.println("           Your Custom Sandwich Destination");
        System.out.println("═".repeat(60) + "\n");
    }

    private void displayHomeScreen() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║            HOME SCREEN                 ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("  1) New Order");
        System.out.println("  0) Exit");
        System.out.println("─".repeat(42));
    }

    private void startNewOrder() {
        currentOrder = new Order();
        displayOrderScreen();
    }

    private void displayOrderScreen() {
        boolean orderActive = true;

        while (orderActive) {
            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║            ORDER SCREEN                ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.println("  1) Add Sandwich");
            System.out.println("  2) Add Drink");
            System.out.println("  3) Add Chips");
            System.out.println("  4) Checkout");
            System.out.println("  0) Cancel Order");
            System.out.println("─".repeat(42));

            if (currentOrder.getProductCount() > 0) {
                System.out.println("\nCurrent items: " + currentOrder.getProductCount());
            }

            int choice = getIntInput("\nEnter your choice: ");

            switch (choice) {
                case 1:
                    addSandwich();
                    break;
                case 2:
                    addDrink();
                    break;
                case 3:
                    addChips();
                    break;
                case 4:
                    orderActive = !checkout();
                    break;
                case 0:
                    if (confirmCancelOrder()) {
                        currentOrder = null;
                        orderActive = false;
                        System.out.println("\nOrder cancelled.");
                    }
                    break;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }

    private void addSandwich() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║          ADD SANDWICH                  ║");
        System.out.println("╚════════════════════════════════════════╝");

        // NEW in v2.0: Choose between custom and signature sandwiches
        System.out.println("\nWhat type of sandwich would you like?");
        System.out.println("  1) Custom Sandwich (build your own)");
        System.out.println("  2) Signature Sandwich (chef's special)");
        System.out.println("  0) Cancel");

        int choice = getIntInput("\nYour choice: ");

        if (choice == 1) {
            addCustomSandwich();
        } else if (choice == 2) {
            addSignatureSandwich();
        } else if (choice != 0) {
            System.out.println("Invalid choice.");
        }
    }

    /**
     * Adds a custom sandwich built by the customer.
     */
    private void addCustomSandwich() {
        // Select bread type
        BreadType breadType = selectBreadType();
        if (breadType == null) return;

        // Select size
        SandwichSize size = selectSandwichSize();
        if (size == null) return;

        // Create sandwich
        Sandwich sandwich = new Sandwich(size, breadType);

        // Add meats
        addMeats(sandwich);

        // Add cheese
        addCheese(sandwich);

        // Add regular toppings
        addRegularToppings(sandwich);

        // Add sauces
        addSauces(sandwich);

        // Add sides (NEW in v2.0: au jus, sauce on the side)
        addSides(sandwich);

        // Toasted?
        sandwich.setToasted(getYesNoInput("\nWould you like the sandwich toasted? (y/n): "));

        currentOrder.addProduct(sandwich);
        System.out.println("\n✓ Sandwich added to order!");
    }

    private BreadType selectBreadType() {
        System.out.println("\nSelect your bread:");
        BreadType[] types = BreadType.values();
        for (int i = 0; i < types.length; i++) {
            System.out.println("  " + (i + 1) + ") " + types[i]);
        }
        System.out.println("  0) Cancel");

        int choice = getIntInput("\nYour choice: ");
        if (choice == 0) return null;
        if (choice < 1 || choice > types.length) {
            System.out.println("Invalid choice.");
            return null;
        }

        return types[choice - 1];
    }

    private SandwichSize selectSandwichSize() {
        System.out.println("\nSelect sandwich size:");
        System.out.println("  1) 4\" - $5.50");
        System.out.println("  2) 8\" - $7.00");
        System.out.println("  3) 12\" - $8.50");
        System.out.println("  0) Cancel");

        int choice = getIntInput("\nYour choice: ");

        switch (choice) {
            case 1: return SandwichSize.SMALL;
            case 2: return SandwichSize.MEDIUM;
            case 3: return SandwichSize.LARGE;
            case 0: return null;
            default:
                System.out.println("Invalid choice.");
                return null;
        }
    }

    private void addMeats(Sandwich sandwich) {
        System.out.println("\n--- Select Meats (or 0 when done) ---");
        MeatType[] meats = MeatType.values();

        boolean addingMeats = true;
        while (addingMeats) {
            for (int i = 0; i < meats.length; i++) {
                System.out.println("  " + (i + 1) + ") " + meats[i]);
            }
            System.out.println("  0) Done adding meats");

            int choice = getIntInput("\nSelect meat: ");

            if (choice == 0) {
                addingMeats = false;
            } else if (choice >= 1 && choice <= meats.length) {
                MeatType meatType = meats[choice - 1];
                boolean extra = getYesNoInput("Extra " + meatType + "? (y/n): ");
                sandwich.addTopping(new MeatTopping(meatType, extra));
                System.out.println("✓ " + (extra ? "Extra " : "") + meatType + " added!");
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    private void addCheese(Sandwich sandwich) {
        System.out.println("\n--- Select Cheese (or 0 when done) ---");
        CheeseType[] cheeses = CheeseType.values();

        boolean addingCheese = true;
        while (addingCheese) {
            for (int i = 0; i < cheeses.length; i++) {
                System.out.println("  " + (i + 1) + ") " + cheeses[i]);
            }
            System.out.println("  0) Done adding cheese");

            int choice = getIntInput("\nSelect cheese: ");

            if (choice == 0) {
                addingCheese = false;
            } else if (choice >= 1 && choice <= cheeses.length) {
                CheeseType cheeseType = cheeses[choice - 1];
                boolean extra = getYesNoInput("Extra " + cheeseType + "? (y/n): ");
                sandwich.addTopping(new CheeseTopping(cheeseType, extra));
                System.out.println("✓ " + (extra ? "Extra " : "") + cheeseType + " added!");
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    private void addRegularToppings(Sandwich sandwich) {
        System.out.println("\n--- Select Regular Toppings (or 0 when done) ---");
        RegularTopping[] toppings = RegularTopping.values();

        boolean addingToppings = true;
        while (addingToppings) {
            for (int i = 0; i < toppings.length; i++) {
                System.out.println("  " + (i + 1) + ") " + toppings[i]);
            }
            System.out.println("  0) Done adding toppings");

            int choice = getIntInput("\nSelect topping: ");

            if (choice == 0) {
                addingToppings = false;
            } else if (choice >= 1 && choice <= toppings.length) {
                sandwich.addTopping(new RegularToppingItem(toppings[choice - 1]));
                System.out.println("✓ " + toppings[choice - 1] + " added!");
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    private void addSauces(Sandwich sandwich) {
        System.out.println("\n--- Select Sauces (or 0 when done) ---");
        Sauce[] sauces = Sauce.values();

        boolean addingSauces = true;
        while (addingSauces) {
            for (int i = 0; i < sauces.length; i++) {
                System.out.println("  " + (i + 1) + ") " + sauces[i]);
            }
            System.out.println("  0) Done adding sauces");

            int choice = getIntInput("\nSelect sauce: ");

            if (choice == 0) {
                addingSauces = false;
            } else if (choice >= 1 && choice <= sauces.length) {
                sandwich.addTopping(new SauceTopping(sauces[choice - 1]));
                System.out.println("✓ " + sauces[choice - 1] + " added!");
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    /**
     * Prompts user to add side items (au jus, sauce on the side).
     * NEW in v2.0 - Sides are separate from sauces that go ON the sandwich.
     */
    private void addSides(Sandwich sandwich) {
        System.out.println("\n--- Select Sides (served separately, not on sandwich) ---");
        SideType[] sides = SideType.values();

        boolean addingSides = true;
        while (addingSides) {
            for (int i = 0; i < sides.length; i++) {
                System.out.println("  " + (i + 1) + ") " + sides[i]);
            }
            System.out.println("  0) Done adding sides");

            int choice = getIntInput("\nSelect side: ");

            if (choice == 0) {
                addingSides = false;
            } else if (choice >= 1 && choice <= sides.length) {
                sandwich.addTopping(new SideTopping(sides[choice - 1]));
                System.out.println("✓ " + sides[choice - 1] + " added!");
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    /**
     * Adds a signature sandwich with pre-configured toppings.
     * BONUS FEATURE in v2.0 - Demonstrates inheritance and polymorphism.
     */
    private void addSignatureSandwich() {
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║       SIGNATURE SANDWICH MENU                  ║");
        System.out.println("╚════════════════════════════════════════════════╝");

        // Display signature options
        SignatureSandwichType[] signatures = SignatureSandwichType.values();
        for (int i = 0; i < signatures.length; i++) {
            System.out.println("\n" + (i + 1) + ") " + signatures[i].getDisplayName());
            System.out.println("   " + signatures[i].getDescription());
        }
        System.out.println("\n0) Cancel");

        int choice = getIntInput("\nSelect signature sandwich: ");

        if (choice == 0) return;
        if (choice < 1 || choice > signatures.length) {
            System.out.println("Invalid choice.");
            return;
        }

        SignatureSandwichType selectedType = signatures[choice - 1];

        // Select size
        SandwichSize size = selectSandwichSize();
        if (size == null) return;

        // Create the signature sandwich (it auto-adds toppings!)
        SignatureSandwich sandwich = new SignatureSandwich(selectedType, size);

        // Ask if they want to customize further
        System.out.println("\nYour " + selectedType.getDisplayName() + " has been prepared!");
        boolean customize = getYesNoInput("Would you like to add additional toppings? (y/n): ");

        if (customize) {
            System.out.println("\n--- Customize Your Signature Sandwich ---");

            // Allow adding more toppings
            if (getYesNoInput("Add more meats? (y/n): ")) {
                addMeats(sandwich);
            }
            if (getYesNoInput("Add more cheese? (y/n): ")) {
                addCheese(sandwich);
            }
            if (getYesNoInput("Add more regular toppings? (y/n): ")) {
                addRegularToppings(sandwich);
            }
            if (getYesNoInput("Add more sauces? (y/n): ")) {
                addSauces(sandwich);
            }
            if (getYesNoInput("Add sides? (y/n): ")) {
                addSides(sandwich);
            }
        }

        currentOrder.addProduct(sandwich);
        System.out.println("\n✓ " + selectedType.getDisplayName() + " added to order!");
    }

    private void addDrink() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║            ADD DRINK                   ║");
        System.out.println("╚════════════════════════════════════════╝");

        System.out.println("\nSelect drink size:");
        System.out.println("  1) Small - $2.00");
        System.out.println("  2) Medium - $2.50");
        System.out.println("  3) Large - $3.00");
        System.out.println("  0) Cancel");

        int choice = getIntInput("\nYour choice: ");

        DrinkSize size;
        switch (choice) {
            case 1: size = DrinkSize.SMALL; break;
            case 2: size = DrinkSize.MEDIUM; break;
            case 3: size = DrinkSize.LARGE; break;
            case 0: return;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        System.out.print("\nEnter drink flavor (e.g., Coke, Sprite, Lemonade): ");
        String flavor = scanner.nextLine().trim();

        if (flavor.isEmpty()) {
            System.out.println("Flavor cannot be empty.");
            return;
        }

        currentOrder.addProduct(new Drink(size, flavor));
        System.out.println("\n✓ Drink added to order!");
    }

    private void addChips() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║            ADD CHIPS                   ║");
        System.out.println("╚════════════════════════════════════════╝");

        System.out.print("\nEnter chip type (e.g., Lays, Doritos, Cheetos): ");
        String type = scanner.nextLine().trim();

        if (type.isEmpty()) {
            System.out.println("Chip type cannot be empty.");
            return;
        }

        currentOrder.addProduct(new Chips(type));
        System.out.println("\n✓ Chips added to order!");
    }

    private boolean checkout() {
        if (currentOrder.isEmpty()) {
            System.out.println("\nYour order is empty. Please add items first.");
            return false;
        }

        if (!currentOrder.isValid()) {
            System.out.println("\nInvalid order: If you don't order a sandwich, you must order chips or a drink.");
            return false;
        }

        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║            CHECKOUT                    ║");
        System.out.println("╚════════════════════════════════════════╝\n");

        System.out.println(currentOrder);

        System.out.println("\nOptions:");
        System.out.println("  1) Confirm Order");
        System.out.println("  0) Cancel and return to order");

        int choice = getIntInput("\nYour choice: ");

        if (choice == 1) {
            ReceiptFileManager.saveReceipt(currentOrder);
            System.out.println("\n" + "═".repeat(50));
            System.out.println("Order completed successfully!");
            System.out.println("Thank you for your order!");
            System.out.println("═".repeat(50));
            currentOrder = null;
            return true;
        }

        return false;
    }

    private boolean confirmCancelOrder() {
        if (currentOrder.isEmpty()) {
            return true;
        }

        return getYesNoInput("\nAre you sure you want to cancel this order? (y/n): ");
    }

    // Helper methods for input handling
    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private boolean getYesNoInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("y") || input.equals("yes")) {
                return true;
            } else if (input.equals("n") || input.equals("no")) {
                return false;
            }

            System.out.println("Please enter 'y' or 'n'.");
        }
    }
}
