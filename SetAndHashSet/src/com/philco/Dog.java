package com.philco;

/**
 * Created by PhillipsDaramola on 30/10/2017.
 */
public class Dog {

    private final String name;

    public Dog(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // final - this means that this method cannot be overridden.
    @Override
    public final boolean equals(Object obj) {

        // If we're 'obj' to itself
        if (this == obj){
            return true;
        }

        // Is obj the Dog class
        if (obj instanceof Dog){
            String objName = ((Dog) obj).getName();
            // Compare the name field to the name in the 'obj' parameter.
            return this.name.equals(objName);
        }

        // If we get this far down, we know that they are not the same.
        return false;
    }
}
