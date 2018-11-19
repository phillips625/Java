package com.philco;

/**
 * Created by PhillipsDaramola on 15/10/2017.
 */
public class SomeClass {

    public final int instanceNumber;
    private static int classCounter = 0;
    private final String name;

    public SomeClass(String name) {

        this.name = name;
        classCounter++;

        // This can be used to set a value we get from a method from another class PERMANENTLY.
        // e.g database keys we fetch from the method of another class - we don't want to change the key value
        // (hence we want it to be final).
        instanceNumber = classCounter;

        System.out.println(name + " created, instance is " + instanceNumber);
    }

    public int getInstanceNumber() {
        return instanceNumber;
    }
}
