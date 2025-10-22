package com.pluralsight;

public class Main {
    public static void main(String[] args) {
        Student alice = new Student("Alice", 10);
        Student bob = new Student("Bob", 9);

        alice.printDetails();
        bob.printDetails();

        Student.changeSchool("Lakeside Academy");

        System.out.println("==========================================");

        alice.printDetails();
        bob.printDetails();


    }
}
