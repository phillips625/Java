package com.Philco;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int[] myIntegers = getIntegers(5);
        int[] sorted = sortIntegers(myIntegers);
        printArray(sorted);

    }

    public static int[] getIntegers(int capacity) {

        int[] array = new int[capacity];

        System.out.println("Enter " + capacity + " integer values: \r" );

        for (int i = 0; i < array.length; i++) {
            array[i] = scanner.nextInt();
        }

        return array;
    }

    public static void printArray(int[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.println("Element " + i + ": Content: " + array[i]);
        }
    }


    public static int[] sortIntegers(int[] array) {

        /* This copies over 'array' to 'sortedArray'.*/
        /*
        // This ensures that 'sortedArray' has the same length as 'array'
        int[] sortedArray = new int[array.length];
        for (int i = 0; i < sortedArray.length; i++){
            // This copies over the content of 'array' into 'sortedArray'
            sortedArray[i] = array[i];
        }
        */
                // OR
        int[] sortedArray = Arrays.copyOf(array, array.length);

        boolean flag = true;
        int temp;

        while (flag) {
            // if for loop succeeds, flag is set to false and breaks out of the for loop.
            flag = false;

            // The reason this finish 1 less before the length of the array is because of [i + 1] in sortedArray[i + 1].
            for (int i = 0; i < sortedArray.length - 1; i++){

                if (sortedArray[i] < sortedArray[i + 1]){
                    temp = sortedArray[i];
                    sortedArray[i] = sortedArray[i+1];
                    sortedArray[i+1] = temp;

                    // Flag will continue to be set to true until all the numbers are sorted
                    // This makes sure that the for loop is executed one more time.
                    flag = true;
                }

            }
        }

        return sortedArray;
    }

}









































