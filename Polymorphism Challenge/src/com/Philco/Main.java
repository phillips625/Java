package com.Philco;

// ALL CLASSES ARE SUBCLASSES OF THE OBJECT CLASS.

///// getClass().getSimpleName() returns the name of the class

// Base class
class Car {

    private boolean engine;
    private int cylinder;
    private String name;
    private int wheels;

    public Car(int cylinder, String name) {
        this.cylinder = cylinder;
        this.name = name;
        this.engine = true;
        this.wheels = 4;
    }

    public int getCylinder() {
        return cylinder;
    }

    public String getName() {
        return name;
    }

    public String startEngine() {
        return "Car -> startEngine()";
    }

    public String accelerate() {
        return "Car -> accelerate()";
    }

    public String brake() {
        return "Car -> brake()";
    }
}

class Mitsubishi extends Car{

    public Mitsubishi(int cylinder, String name) {
        super(cylinder, name);
    }

    @Override
    public String startEngine() {
        // return super.startEngine();
        return "Mitsubishi -> startEngine()";
    }

    @Override
    public String accelerate() {
        // return super.accelerate();
        return "Mitsubishi -> accelerate()";
    }

    @Override
    public String brake() {
        // return super.brake();
        return "Mitsubishi -> brake()";
    }
}

class Holden extends Car{

    public Holden(int cylinder, String name) {
        super(cylinder, name);
    }

    @Override
    public String startEngine() {
        // return super.startEngine();

        // return "Holden -> startEngine()";
                // OR
        return getClass().getSimpleName() + " -> startEngine()";
    }

    @Override
    public String accelerate() {
        // return super.accelerate();
        return getClass().getSimpleName() + " -> accelerate()";
    }

    @Override
    public String brake() {
        // return super.brake();
        return getClass().getSimpleName() + " -> brake()";
    }
}

// This class was created by:
// 1. Right click on 'Mitsubishi' class, select Refactor, click copy. Rest is obvious.
class Ford extends Car{

    public Ford(int cylinder, String name) {
        super(cylinder, name);
    }

    @Override
    public String startEngine() {
        // return super.startEngine();
        return "Ford -> startEngine()";
    }

    @Override
    public String accelerate() {
        // return super.accelerate();
        return "Ford -> accelerate()";
    }

    // If you comment this line out, 'Car -> brake()' is printed.
    @Override
    public String brake() {
        // return super.brake();
        return "Ford -> brake()";
    }
}


public class Main {

    public static void main(String[] args) {

        Car car = new Car(8, "Base car");
        System.out.println(car.startEngine());
        System.out.println(car.accelerate());
        System.out.println(car.brake());

        Mitsubishi mitsubishi = new Mitsubishi(6, "Michi");
        System.out.println(mitsubishi.startEngine());
        System.out.println(mitsubishi.accelerate());
        System.out.println(mitsubishi.brake());

        Holden holden = new Holden(7, "Falcon");
        System.out.println(holden.startEngine());
        System.out.println(holden.accelerate());
        System.out.println(holden.brake());

        Ford ford = new Ford(5, "Commodore");
        System.out.println(ford.startEngine());
        System.out.println(ford.accelerate());
        System.out.println(ford.brake());
    }

}
