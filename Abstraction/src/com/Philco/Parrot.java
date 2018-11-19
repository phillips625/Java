package com.Philco;

/**
 * Created by PhillipsDaramola on 04/10/2017.
 */
// Parrot extends/inherits from Bird because a Parrot IS A bird.
public class Parrot extends Bird {

    public Parrot(String name) {
        super(name);
    }

    // You can also override the 'eat' and 'breathe' methods from the 'Bird' class. As we haven't specified the implementation of these methods,
    // we would automatically inherit the 'eat' and 'breathe' methods.

        // This method will now be inherited in the Bird class.
    /* @Override
    public void fly() {
        System.out.println("Flitting from branch to branch..");
    }
    */
}
