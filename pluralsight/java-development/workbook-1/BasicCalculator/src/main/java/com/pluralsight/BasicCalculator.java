package com.pluralsight;

import java.util.Scanner;

public class BasicCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask user for numbers
        System.out.print("Enter the first number: ");
        double num1 = scanner.nextDouble();

        System.out.print("Enter the second number: ");
        double num2 = scanner.nextDouble();

        // Ask user for operation (but we will always multiply)
        System.out.println("\nPossible calculations:");
        System.out.println("(A)dd");
        System.out.println("(S)ubtract");
        System.out.println("(M)ultiply");
        System.out.println("(D)ivide");
        System.out.print("Please select an option: ");
        //String option = scanner.next();

        // Always multiply, regardless of user choice
        double result = num1 * num2;

        // Print result in the required format
        System.out.println("\n" + num1 + " * " + num2 + " = " + result);

        scanner.close();
    }
}
