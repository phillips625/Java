package com.philco;

/**
 * Created by PhillipsDaramola on 30/10/2017.
 */
public class DogMain {

    public static void main(String[] args) {
        Labrador rover = new Labrador("Rover");
        Dog rover2 = new Dog("Rover");

        // A Labrador is an instance of Dog, hence this returns true.
        System.out.println(rover2.equals(rover));
        // A Dog is not an instance of Labrador, hence this returns false BECAUSE THE EQUALS METHOD WAS OVERRIDDEN IN THE LABRADOR CLASS -
        // WHICH IT SHOULDN'T. IT SHOULD ONLY BE OVERRIDDEN IN THE DOG CLASS.
        System.out.println(rover.equals(rover2));
    }
}
