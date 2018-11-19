package com.Philco;

/**
 * Created by PhillipsDaramola on 09/07/2017.
 */

////// COMPOSITION: Models the HAS-A relationship. E.g a computer has a monitor, motherboard and case.
    // But, a computer is not a monitor nor a motherboard not a case.

// Inheritance basically means a Car is a Vehicle.
public class Car extends Vehicle{

    private int doors;
    private int engineCapacity;

    public Car(String name, int doors, int engineCapacity) {
        super(name);
        this.doors = doors;
        this.engineCapacity = engineCapacity;
    }
}
