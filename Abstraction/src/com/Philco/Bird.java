package com.Philco;

/**
 * Created by PhillipsDaramola on 03/10/2017.
 */

// Making this an abstract class to account for the fact that not all birds can fly - we are going to be create a fly abstract class for
// classes that want to inherit from the Bird class to implement .
public abstract class Bird extends Animal implements CanFly{

    public Bird(String name) {
        super(name);
    }

    @Override
    public void eat() {
        System.out.println(getName() + " is pecking..");
    }

    @Override
    public void breathe() {
        System.out.println("Breathe in and out");
    }

    @Override
    public void fly() {
        System.out.println(getName() + " is flapping its wings..");
    }

    // This assumes that only birds can fly. A bat (which is a mammal) can also fly. A better design will be to have an interface which the
    // fly method - which both the bat and bird can inherit from.

    // public abstract void fly();
}
