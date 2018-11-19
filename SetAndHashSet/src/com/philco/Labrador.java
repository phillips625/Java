package com.philco;

/**
 * Created by PhillipsDaramola on 30/10/2017.
 */
public class Labrador extends Dog{

    public Labrador(String name) {
        super(name);
    }

            // DON'T OVERRIDE EQUALS HERE TOO - SO THAT THE EQUALS METHOD WOULD WORK CORRECTLY.
    // ONLY OVERRIDE EQUALS METHOD IN THE DOG CLASS.
    // MARK THE EQUALS

//    @Override
//    public boolean equals(Object obj) {
//
//        // If we're 'obj' to itself
//        if (this == obj) {
//            return true;
//        }
//
//        // Is obj the Labrador class
//        if (obj instanceof Labrador) {
//            String objName = ((Labrador) obj).getName();
//            // Compare the name field to the name in the 'obj' parameter.
//            // this.getName() gets the name from 'super(name);' above.
//            return this.getName().equals(objName);
//        }
//
//        // If we get this far down, we know that they are not the same.
//        return false;
//    }
}
