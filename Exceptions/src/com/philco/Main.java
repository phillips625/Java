package com.philco;

// Exception: Something went wrong in the execution of the application at run time.
// 'Exception' and 'RuntimeException' are the 2 main classes that handle exceptions - both classes are defined in Java.lang. The types of exceptions
// that can be caught are subclasses of these 2 exceptions. E.g InputMismatchException is a subclass of one of these 2 classes.
//  'InputMismatchException' is better because we're being specific about the exception as opposed to being generic (like in the case of
// just using 'Exception').

// Expected to catch any exceptions when you're writing to a file.

import java.util.InputMismatchException;
import java.util.Scanner;

// An advantage of LBYL. LBYL approach is useful e.g in knowing whether a key exists in a map.

public class Main {

    public static void main(String[] args) {

//        int x = 90;
//        int y = 0;
//
//        System.out.println(divideEAFP(x, y));
//        System.out.println(divideEAFP(x, y));

//        int x = getInt();
//        System.out.println("x is " + x);
                //OR
//        int x = getIntLBYO();
//        System.out.println("x is " + x);
                //OR
        int x = getIntLBYO();
        System.out.println("x is " + x);

    }

    private static int getInt(){

        Scanner s = new Scanner(System.in);
        return s.nextInt();
    }

            // The next 2 methods are similar.

    // Look before you leap version of the getInt function.
    private static int getIntLBYO(){

        Scanner s = new Scanner(System.in);
        boolean isValid = true;
        System.out.println("Please enter an integer");
        String input = s.next();
        // Going through each character that has been accepted from the keyboard.
        for (int i = 0; i < input.length(); i++){

            // Basically for each element in the String, we're testing that each character is a number.
            // And if the character isn't then we're setting 'isValid' to false.
            // 'isDigit' checks if the arguments passed to it are digits.
            if(!Character.isDigit(input.charAt(i))){
                isValid = false;
                break;
            }
        }

        // If all the digits are there in input aka if 'input' was all digits.
        if (isValid){

            // We know that this will not give any errors - as 'input' is all digits.
            return Integer.parseInt(input);
        }

        return 0;
    }

    // Method 2: Requires less code.
    private static int getIntEAFP(){
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter an Integer");
        try {
            return s.nextInt();
        }
        catch (InputMismatchException e){
            return 0;
        }
    }



            // The 2 functions below are similar.

    // :BYL - Look Before You Leap
    private static int divideLBYL(int x, int y){
        // This is an example of LBYL. We're checking that y is not 0 before dividing it by x
        if (y != 0){
            return x/y;
        }
        else {
            return 0;
        }

    }

    // Easy to ask for forgiveness than permission.
    private static int divideEAFP(int x, int y){
        try {
            return x/y;
        }
        catch (ArithmeticException e){
            return 0;
        }
    }
}
