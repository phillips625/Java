package com.Philco;

// All classes inherits from the OBJECT class

public class Main extends Object{

    public static void main(String[] args) {

        Outlander outlander = new Outlander(36);

        // steer method goes all the way to the vehicle class
        outlander.steer(45);
        outlander.accelerate(30);
        // Adds 20 to the rate (sinces it relates to the 'outlander' object)
        outlander.accelerate(20);

        outlander.accelerate(-42);
    }
}
