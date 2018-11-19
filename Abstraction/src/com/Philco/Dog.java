package com.Philco;

/**
 * Created by PhillipsDaramola on 03/10/2017.
 */
            // Interface Vs Abstraction
// Interface - The entire interface was completely abstract aka there are no implementations. There was no opportunity to enter fields or methods.
    // Abstraction - On the other hand, abstract classes allows you to declare fields and methods (Check the 'Animal' class for example).

// Must implement 'eat' and 'breathe' methods.
public class Dog extends Animal {

    public Dog(String name) {
        // Calls the constructor in the Animal class.
        super(name);
    }

    @Override
    public void eat() {
        // 'getName' is a function in the 'Animal' class.
        System.out.println(getName() + " is eating..");
    }

    @Override
    public void breathe() {
        System.out.println("Breath in, breath out..");
    }
}
