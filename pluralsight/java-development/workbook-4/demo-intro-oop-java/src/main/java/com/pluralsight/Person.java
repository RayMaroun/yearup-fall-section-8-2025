package com.pluralsight;

public class Person {
    // Attributes
    private String firstName;
    private String lastName;
    private int age;
    private int energy;
    private boolean isStudent;

    // Constructor

    // person
    public Person() {
    }

    // person_string_string_int
    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.energy = 100;
    }

    // person_string_string
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.energy = 100;
    }

    // person_string_int
    public Person(String firstName, int age) {
        this.firstName = firstName;
        this.age = age;
        this.energy = 100;
    }

    // person_int_string
    public Person(int age, String lastName) {
        this.age = age;
        this.lastName = lastName;
        this.energy = 100;
    }

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }

    // Derived Getters
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getPersonalDetails() {
        return "FirstName: " + firstName + " Age: " + age;
    }

    // Actions

    // eat
    public void eat() {
        this.energy += 20;
        System.out.println("I am eating potatoes!");
    }

    // eat_int
    public void eat(int energyAdded) {
        this.energy += energyAdded;
        System.out.println("I am eating potatoes! And I added " + energyAdded + " energy!");
    }

    public void work() {
        this.energy -= 20;
        System.out.println("I am working!");
    }

    public void greet(Person person) {
        System.out.println("Hello " + person.getFirstName() + "! I am " + this.firstName);
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", energy=" + energy +
                ", isStudent=" + isStudent +
                '}';
    }
}
