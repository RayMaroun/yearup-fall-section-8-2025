package com.pluralsight;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @Test
    public void accelerate_NormalSpeedChange_SpeedIncreased() {
        // Arrange
        Car stephanCar = new Car("Mazda", "Miata");
        int speedChange = 15;
        int expectedSpeed = 15;

        // Act
        stephanCar.accelerate(speedChange);

        // Assert
        int actualSpeed = stephanCar.getSpeed();
        assertEquals(expectedSpeed, actualSpeed);
    }

    @Test
    public void brake_NormalSpeedChange_SpeedDecreased() {
        // Arrange
        Car mohammadCar = new Car("Toyota", "FourRunner");
        int speedUpBy = 15;
        int slowDownBy = 10;
        int expectedSpeed = 5;
        mohammadCar.accelerate(speedUpBy);

        // Act
        mohammadCar.brake(slowDownBy);

        // Assert
        int actualSpeed = mohammadCar.getSpeed();
        assertEquals(expectedSpeed, actualSpeed);
    }

    @Test
    public void brake_SpeedChangeGreaterThanActualSpeed_SpeedIsZero() {
        // Arrange
        Car wardahCar = new Car("Ferrari", "SF90");
        int speedUpBy = 15;
        int slowDownBy = 20;
        int expectedSpeed = 0;
        wardahCar.accelerate(speedUpBy);

        // Act
        wardahCar.brake(slowDownBy);

        // Assert
        int actualSpeed = wardahCar.getSpeed();
        assertEquals(expectedSpeed, actualSpeed);
    }

}