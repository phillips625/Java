package com.Philco;

/**
 * Created by PhillipsDaramola on 03/10/2017.
 */
public abstract class Animal {

    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Abstract methods - any class that want to inherit from the Animal class has to implement these methods!
    // Leave these abstract methods here (as opposed to creating interfaces for them) because each animal breathes differently
    // , hence each animal class will implement these differently.
    public abstract void eat();
    public abstract void breathe();

}
