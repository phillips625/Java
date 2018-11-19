package com.Philco;

/**
 * Created by PhillipsDaramola on 04/10/2017.
 */
public class Penguin extends Bird {

    public Penguin(String name) {
        super(name);
    }

    @Override
    public void fly() {
        super.fly();
        System.out.println("I'm getting better at it..");
    }
}
