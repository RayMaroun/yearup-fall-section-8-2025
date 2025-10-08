package com.pluralsight;

public class Vehicle {
    private String makeModel;
    private int year;
    private String color;

    public Vehicle(String makeModel, int year, String color) {
        this.makeModel = makeModel;
        this.year = year;
        this.color = color;
    }

    public String getMakeModel() {
        return makeModel;
    }

    public void setMakeModel(String makeModel) {
        this.makeModel = makeModel;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "makeModel='" + makeModel + '\'' +
                ", year=" + year +
                ", color='" + color + '\'' +
                '}';
    }
}
