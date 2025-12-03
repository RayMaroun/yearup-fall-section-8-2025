package com.pluralsight.deli.services;

import com.pluralsight.deli.models.Order;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Manages the creation and storage of order receipts as text files.
 *
 * FILE I/O DEMONSTRATION:
 * This class demonstrates Java File I/O concepts:
 * - Writing to files using FileWriter
 * - Try-with-resources for automatic resource management
 * - Exception handling for I/O operations
 * - File naming conventions
 *
 * SERVICE LAYER PATTERN:
 * This class is in the 'services' package because it provides a service
 * (file management) rather than representing a domain object (like Product or Order).
 *
 * Separation of concerns:
 * - Models (Product, Order): Represent data and business logic
 * - Services (ReceiptFileManager): Handle external operations (file I/O)
 * - UI (UserInterface): Handle user interaction
 *
 * STATIC METHODS:
 * All methods are static because this class doesn't maintain state.
 * Each receipt operation is independent, so we don't need instances.
 *
 * @author Pluralsight Deli Team
 * @version 2.0 - Enhanced with educational comments
 */
public class ReceiptFileManager {

    /**
     * The folder where all receipts are stored.
     *
     * CONSTANTS:
     * - static final means this value is shared across all uses and can't be changed
     * - ALL_CAPS naming convention indicates it's a constant
     * - Defined in one place so if we need to change the folder, we only update it here
     */
    private static final String RECEIPTS_FOLDER = "receipts";

    /**
     * Date/time formatter for creating unique filenames.
     *
     * FORMAT PATTERN:
     * "yyyyMMdd-HHmmss" produces filenames like: 20240315-143052.txt
     * - yyyy: 4-digit year (2024)
     * - MM: 2-digit month (03)
     * - dd: 2-digit day (15)
     * - HH: 2-digit hour in 24-hour format (14)
     * - mm: 2-digit minute (30)
     * - ss: 2-digit second (52)
     *
     * This ensures unique filenames and allows chronological sorting.
     */
    private static final DateTimeFormatter FILE_NAME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

    /**
     * Saves an order receipt to a timestamped text file.
     *
     * TRY-WITH-RESOURCES:
     * The try (FileWriter writer = ...) syntax is try-with-resources.
     * Benefits:
     * - FileWriter is automatically closed when try block ends
     * - No need for finally block to close the writer
     * - Prevents resource leaks even if exceptions occur
     *
     * How it works:
     * 1. Create FileWriter (opens the file)
     * 2. Execute try block (write content)
     * 3. Automatically close FileWriter (even if exception thrown)
     *
     * EXCEPTION HANDLING:
     * File I/O can fail for many reasons:
     * - Disk full
     * - Permission denied
     * - Folder doesn't exist
     *
     * We use try-catch to handle IOException and provide feedback.
     *
     * @param order the order to save to a receipt file
     * @return the filename if successful, null if an error occurred
     */
    public static String saveReceipt(Order order) {
        // Get current date/time for filename and receipt header
        LocalDateTime now = LocalDateTime.now();

        // Build filename: receipts/20240315-143052.txt
        String fileName = RECEIPTS_FOLDER + "/" + now.format(FILE_NAME_FORMATTER) + ".txt";

        // Try-with-resources: FileWriter will auto-close
        try (FileWriter writer = new FileWriter(fileName)) {
            // Generate and write the receipt content
            writer.write(generateReceiptContent(order, now));

            // Success feedback
            System.out.println("\nReceipt saved successfully: " + fileName);
            return fileName;

        } catch (IOException e) {
            // Handle any file I/O errors
            System.err.println("Error saving receipt: " + e.getMessage());
            return null;  // Indicate failure
        }
    }

    /**
     * Generates the formatted receipt content as a string.
     *
     * PRIVATE HELPER METHOD:
     * This method is private because it's an implementation detail.
     * External code only needs saveReceipt() - this internal method
     * is encapsulated.
     *
     * STRING FORMATTING:
     * We use Unicode box-drawing characters for a professional appearance:
     * - ╔ ╗ ╚ ╝ : Box corners
     * - ║ : Vertical lines
     * - ═ : Horizontal lines
     *
     * These create a nice visual border for the receipt.
     *
     * DELEGATION:
     * We call order.toString() to get the order details. This demonstrates
     * good separation of concerns - Order knows how to format itself, and
     * ReceiptFileManager just adds the header/footer.
     *
     * @param order the order to generate a receipt for
     * @param timestamp the date/time for the receipt header
     * @return the complete receipt content as a formatted string
     */
    private static String generateReceiptContent(Order order, LocalDateTime timestamp) {
        StringBuilder receipt = new StringBuilder();

        // Header with box-drawing characters
        receipt.append("╔════════════════════════════════════════════════╗\n");
        receipt.append("║          DELI-cious Sandwiches                 ║\n");
        receipt.append("║          Official Receipt                      ║\n");
        receipt.append("╚════════════════════════════════════════════════╝\n\n");

        // Format timestamp for display (different from filename format)
        // Example: 03/15/2024 02:30:52 PM
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
        receipt.append("Date: ").append(timestamp.format(displayFormatter)).append("\n\n");

        // Add the order details (delegates to Order.toString())
        receipt.append(order.toString());

        // Footer with thank you message
        receipt.append("\n");
        receipt.append("═".repeat(50)).append("\n");
        receipt.append("Thank you for your order!\n");
        receipt.append("We hope you enjoy your meal!\n");

        return receipt.toString();
    }
}
