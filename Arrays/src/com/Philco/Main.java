package com.Philco;

import java.util.Scanner;

// Practical use of arrays: Finding averages of input numbers.
public class Main {

    // When typing, if you see java.util, it means it's a functionality that comes with java
    // We're defining a static variable called scanner. This is how you get input from the console in Java.
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // getIntegers(5) defines the length of an array.
        // getIntegers(5) returns an array of length 5 with the inputted values by the user.
        int[] myIntegers = getIntegers(5);

        for (int i = 0; i < myIntegers.length; i++) {
            System.out.println("Element " + i + " = " + myIntegers[i]);
        }

        System.out.println( "Average = " + getAverage(myIntegers));
    }

    // 'public static int[]' means we're returning an array.
    public static int[] getIntegers(int number) {

        System.out.println("Enter " + number + " integer values. \r");

        int[] values = new int[number];
        for (int i = 0; i < values.length; i++){

            // 'scanner.nextInt()' takes the input and returns an integer and stores it in the value array.
            values[i] = scanner.nextInt();
        }

        return values;
    }

    public static double getAverage(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++){
            sum += array[i];
        }

        return (double) sum / (double) array.length;
    }
}












/*              Array Learning
public class Main {

    public static void main(String[] args) {

        // One way to initialise an array
        int[] myIntArray = new int[10];
        myIntArray[5] = 50;
        System.out.println(myIntArray[5]);
        System.out.println("-----------------");

        // OR

        int[] myNewArray = {1,2,3,4,5,6,7,8,9,10};
        System.out.println(myNewArray[5]);
        System.out.println("-----------------");

        // OR
        int[] myThirdArray = new int[10];

        printArray(myThirdArray);
    }

    public static void printArray(int[] myThirdArray) {
        for (int i = 0; i < myThirdArray.length; i++){
            myThirdArray[i] = i * 10;
            System.out.println(myThirdArray[i]);
        }
        System.out.println("-----------------");
    }
}
*/