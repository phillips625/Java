package com.Philco;

import java.util.ArrayList;

// This is a wrapper class that works around the fact that int (a primitive type) cannot be added to an arraylist.
class IntClass{
    private int myValue;

    public IntClass(int myValue) {
        this.myValue = myValue;
    }

    public int getMyValue() {
        return myValue;
    }

    public void setMyValue(int myValue) {
        this.myValue = myValue;
    }
}

public class Main {

    public static void main(String[] args) {

        String[] strArray = new String[10];
        int[] intArray = new int[10];

        ArrayList<String> strArrayList = new ArrayList<String>();
        // Adds an object of the type String to the arraylist.
        strArrayList.add("Tim");

            // This outputs an error as int is a primitive type (a primitive type is not a class).
       // ArrayList<int> intArrayList = new ArrayList<int>();

        /*First Solution.*/
        ArrayList<IntClass> intClassArrayList = new ArrayList<IntClass>();
        // Add element to arraylist.
        intClassArrayList.add(new IntClass(54));

                /*
                 *  SOLUTION : AUTOBOXING - valueOf(i) (this allows you to create arraylists of primitive types).
                */

        // Integer is actually a class.
        Integer integer = new Integer(54);
        Double doubleValue = new Double(12.23);

                /*2nd Easier Way*/

        // Now this works
        ArrayList<Integer> intArrayList = new ArrayList<Integer>();
        for (int i = 0; i<10; i++){
            // Returns an Integer object
            // valueOf(i) IS AUTOBOXING. WE'RE CONVERTING THE INTEGER PRIMITIVE TYPE INTO AN INTEGER OBJECT.
            intArrayList.add(Integer.valueOf(i));
        }

        for (int i = 0; i<10; i++){

            // intValue converts from an Integer class to a primitive 'int'.
            // intArrayList.get(i) returns an Integer class.

            // 'intValue()' IS UNBOXING. WE'RE UNBOXING/CONVERTING THE INTEGER CLASS BACK TO AN INTEGER PRIMITIVE TYPE.
            System.out.println(i + " -> " + intArrayList.get(i).intValue());
        }

                /*
                * EASIER WAY TO AUTOBOX AND UNBOX!
                 */

                // AUTOBOXING
                /*Why does this work despite the fact that we're not using the new keyword?*/
                // At compile time, 'Integer.valueOf(56)' will be executed.
                Integer myIntValue = 56;

                //UNBOXING
                int myInt = myIntValue; //This is basically 'myIntValue.intValue()' at compile time.

                ArrayList<Double> myDoubleValues = new ArrayList<Double>();

                for (double dbl = 0.0; dbl <= 10.0; dbl += 0.5){
                    /*This is autoboxing - we're converting the primitive type 'dbl' to the object rapper 'Double'.*/
                    // myDoubleValues.add(Double.valueOf(dbl));
                            // OR
                    myDoubleValues.add(dbl);
                }

                for (int i = 0; i<myDoubleValues.size(); i++){
                    /*This is unboxing - we're converting the double object back to a primitive type (which is 'value').*/
                    // double value = myDoubleValues.get(i).doubleValue();
                            // OR
                    double value = myDoubleValues.get(i);
                    System.out.println(i + " --> " + value);
                }

    }
}














