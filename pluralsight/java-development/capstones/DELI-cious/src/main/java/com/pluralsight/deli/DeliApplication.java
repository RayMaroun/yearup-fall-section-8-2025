package com.pluralsight.deli;

import com.pluralsight.deli.ui.UserInterface;

/**
 * Main application entry point for the DELI-cious Sandwich Shop Point of Sales System.
 *
 * APPLICATION ARCHITECTURE:
 * This class serves as the entry point and demonstrates a simple layered architecture:
 *
 * 1. Main/Entry Point (this class)
 *    - Bootstraps the application
 *    - Minimal logic - just creates and starts the UI
 *
 * 2. UI Layer (UserInterface)
 *    - Handles all user interaction
 *    - Displays menus and prompts
 *    - Collects input
 *
 * 3. Model Layer (Product, Order, Topping subclasses)
 *    - Represents business entities and data
 *    - Contains business logic (pricing, validation)
 *
 * 4. Service Layer (ReceiptFileManager)
 *    - Handles external operations (file I/O)
 *    - Separates infrastructure concerns from business logic
 *
 * SEPARATION OF CONCERNS:
 * Notice how simple this class is - it just creates a UserInterface and starts it.
 * All the complex logic is properly separated into other classes. This makes
 * the codebase easier to maintain, test, and understand.
 *
 * THE main() METHOD:
 * - public: Accessible from anywhere (required for Java to find it)
 * - static: Can be called without creating a DeliApplication instance
 * - void: Doesn't return a value
 * - String[] args: Command-line arguments (we don't use them in this app)
 *
 * This is the standard signature Java looks for when starting an application.
 *
 * @author Pluralsight Deli Team
 * @version 2.0 - Enhanced with educational comments and package restructure
 */
public class DeliApplication {

    /**
     * Main entry point for the application.
     *
     * EXECUTION FLOW:
     * 1. JVM calls this method when you run the program
     * 2. We create a new UserInterface object
     * 3. We call ui.start() which begins the interactive menu loop
     * 4. When the user exits, control returns here
     * 5. The program ends
     *
     * WHY SO SIMPLE?
     * This method should be simple! Its only job is to bootstrap the
     * application. All the real work happens in UserInterface and the
     * model classes. This is good design - single responsibility principle.
     *
     * @param args command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        // Create the user interface
        UserInterface ui = new UserInterface();

        // Start the interactive menu system
        // This will loop until the user chooses to exit
        ui.start();

        // When we reach here, the user has exited the application
        // The program will terminate normally
    }
}

