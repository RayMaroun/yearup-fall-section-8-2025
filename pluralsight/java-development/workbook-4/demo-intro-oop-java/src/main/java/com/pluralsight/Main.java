package com.pluralsight;

public class Main {
    public static void main(String[] args) {
        //Person person1 = new Person();
        Person person2 = new Person("Raymond", "Maroun", 55);

        System.out.println(person2.getLastName());

        System.out.println("===================================");

        System.out.println(person2.getEnergy());

        person2.work();

        System.out.println(person2.getEnergy());

        person2.eat();

        System.out.println(person2.getEnergy());


        System.out.println("===================================");

        Person person3 = new Person("John", "Doe", 30);

        person2.greet(person3);
    }
}
