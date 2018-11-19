package com.Philco;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // We have not specified what type of objects we're going to be storing in our array list -
        // so the array list we've used here is a RAW TYPE.
        //                                      <Integer> is a parametized type.
        // Setting the Java to the right level - File > Project Structure > Ensure the level is set to 1.8 (this is the java source code version).
        // Now we don't need <Integer> anymore.
        ArrayList<Integer> items = new ArrayList<>();

        // The primitive ints are autoboxed/converted to Integer objects.
        items.add(1);
        items.add(2);
        // This will output an error - trying to cast a String to an Integer will output an error.
        // The problem here is that you don't see the error until runtime.
        // To see messages - View > Tool Windows > Messages
        // items.add("Tim");
        items.add(3);
        items.add(4);
        items.add(5);

        printDoubled(items);
    }

    private static void printDoubled(ArrayList<Integer> n){
        // for (Object i : n){
        for (int i : n){
            // Casting, '(Integer)', tells java what is in that array list.
            //System.out.println((Integer) i * 2);

            System.out.println( i * 2);
        }
    }
}
