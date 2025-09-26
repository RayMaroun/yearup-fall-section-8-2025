package com.pluralsight;

import java.util.Scanner;

public class MyApplication {
    public static void main(String[] args) {
        Scanner potatoScanner = new Scanner(System.in);

        String name = getName(potatoScanner);
        int age = getAge(potatoScanner);

        System.out.println("=============================================");
        printNameAndAge(name, age);
        System.out.println("=============================================");
        printVotingEligibility(age);
        System.out.println("=============================================");
        printMessage(name, age);
        System.out.println("=============================================");
        printWelcomeMessage(name);
        System.out.println("=============================================");
        printDrinkingEligibility(name, age);
        System.out.println("=============================================");

        double numberFromUser = getNumber(potatoScanner);
        double squareRoot = calculateSquareRoot(numberFromUser);

        System.out.println("The square root of " + numberFromUser + " is " + squareRoot);
        System.out.println("=============================================");

    }

    public static double getNumber(Scanner scanner) {
        System.out.println("Enter a number:");
        return scanner.nextDouble();
    }

    public static double calculateSquareRoot(double number) {
        return Math.sqrt(number);
    }

    public static void printDrinkingEligibility(String name, int age) {
        if (age >= 21 && name.equalsIgnoreCase("Bob")) {
            System.out.println("You are old enough to drink.");
        } else {
            System.out.println("You are not old enough to drink.");
        }
    }

    public static void printWelcomeMessage(String name) {
        if (name.equalsIgnoreCase("Alice")) {
            System.out.println("Welcome, Alice!");
        } else {
            System.out.println("Hello, stranger!");
        }
    }

    public static String getName(Scanner scanner) {
        System.out.println("Enter your name:");
        return scanner.nextLine();
    }

    public static int getAge(Scanner scanner) {
        System.out.println("Enter your age:");
        return scanner.nextInt();
    }

    public static void printMessage(String name, int age) {
        System.out.println("Hello, " + name + "! You are " + age + " years old.");
    }

    public static void printNameAndAge(String name, int age) {
        System.out.println("Your name is: " + name);
        System.out.println("Your age is: " + age);
    }

    public static void printVotingEligibility(int age) {
        if (age >= 18) {
            System.out.println("You are old enough to vote!");
        } else {
            System.out.println("You are not old enough to vote!");
        }
    }
}
