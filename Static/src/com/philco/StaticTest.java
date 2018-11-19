package com.philco;

/**
 * Created by PhillipsDaramola on 13/10/2017.
 */
public class StaticTest {

    // private int numInstances = 0;
    // There is only copy of this static variable unlike its counterpart above. Everytime we create a new StaticTest object,
    // the StaticTest doesn't create another copy of numInstances unlike its counterpart above.
    private static int numInstances = 0;
    private String name;

    public StaticTest(String name) {
        this.name = name;
        // Everytime the constructor is used, we'll increment 'numInstances'.
        numInstances++;
    }

    // STATIC METHOD AND FIELDS belong to the class, not to instances of the class - as a result can be called by referencing
    // the class names rather than the class instance. This is also one of the reasons why the main mehod is marked as static.
    // Java needs a method that can be called instantly rather than having to call the main method from an object.

    //public int getNumInstances() {
    public static int getNumInstances() {
        return numInstances;
    }

    public String getName() {
        return name;
    }
}
