package com.philco;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
                     * REAL WORLD APPLICATION
 *
                    -- CATCHING MULTIPLE EXCEPTIONS
 */

// Call stack and Stack trace
    // Call stack - all the methods that are called at a particular point of executing an application.
    // Each thread of execution has its own call stack.

    // Stack trace - The stack of execeptions thrown in the console (start from bottom to top).
// Exceptions itself extends throwable. The Throwable constructor fills in the stack trace for the exception.
    // The trace goes up the class stack (all the way up to the main function) which method throws an exceptions.

public class Example {

    public static void main(String[] args) {
        try {
            int result = divide();
            System.out.println(result);
        }

        // Multi catch exception: Catching multiple exceptions.
        catch (ArithmeticException | NoSuchElementException e) {
        // toString outputs the error.
            System.out.println(e.toString());
            System.out.println("Unable to perform division, autopilot shutting down");
        }
    }

    public static int divide(){

          int x, y;
//        try {
            x = getInt();
            y = getInt();
            System.out.println("x is " + x + "; y is " + y);
            return x/y;
//        }
//
//        // Always keep your catch statement simple - to avoid it throwing another round of exceptions.
//        catch (NoSuchElementException e){
//            // Avoid doing this - as this will throw an exception you're trying to catch.
//            // x = getInt();
//
//            // The throw will keep the 'stack trace' output in the console simple - as we're are now throwing our own exceptions as opposed
//            // to relying on exceptions thrown by methods in the Java classes.
//            // "No suitable input" will be on the output.
//            // Adviceable to throw a specific subclass of an exception rather than throwing 'Exceptions' itself.
//            throw new NoSuchElementException("No suitable input");
//        }
//
//        catch (ArithmeticException e){
//
//            // "Attempt to divide by zero" give us more information of what actually happened. Useful for log files when you're (or any human) trying to
//            // deduce what happened
//            throw new ArithmeticException("Attempt to divide by zero");
//        }

    }

    // This method is not a good method - don't use a method to get input from users for any reason.
    public static int getInt(){
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter an integer");

        // Propagating the exception through the 'Call Stack' (aka through all the associated methods in the execution of the application).
        while (true){
            try {

                return s.nextInt();
            }

            // This block of code will only catch the 'InputMismatchException' exceptions and no ther type of exceptions.
            catch (InputMismatchException e){

                // Goes in loop until a valid integer is inputted.
                // Go round again. Read past the end of the line in the first input.

                // Goes to the next line.
                s.nextLine();

                System.out.println("Please enter a number using only the digits 0 to 9");
            }
        }
    }
}



/*




                                // NON REAL-WORLD APPLICATION.

package com.philco;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

// Call stack and Stack trace
// Call stack - all the methods that are called at a particular point of executing an application.
// Each thread of execution has its own call stack.

// Stack trace - The stack of execeptions thrown in the console (start from bottom to top).
// Exceptions itself extends throwable. The Throwable constructor fills in the stack trace for the exception.
// The trace goes up the class stack (all the way up to the main function) which method throws an exceptions.

public class Example {

    public static void main(String[] args) {
        try {
            int result = divide();
            System.out.println(result);
        }

        catch (ArithmeticException e) {
            // toString outputs the error.
            System.out.println(e.toString());
            System.out.println("Unable to perform division, autopilot shutting down");
        }
    }

    public static int divide(){

        int x, y;
        try {
            x = getInt();
            y = getInt();
            System.out.println("x is " + x + "; y is " + y);
            return x/y;
        }

        // Always keep your catch statement simple - to avoid it throwing another round of exceptions.
        catch (NoSuchElementException e){
            // Avoid doing this - as this will throw an exception you're trying to catch.
            // x = getInt();

            // The throw will keep the 'stack trace' output in the console simple - as we're are now throwing our own exceptions as opposed
            // to relying on exceptions thrown by methods in the Java classes.
            // "No suitable input" will be on the output.
            // Adviceable to throw a specific subclass of an exception rather than throwing 'Exceptions' itself.
            throw new NoSuchElementException("No suitable input");
        }

        catch (ArithmeticException e){

            // "Attempt to divide by zero" give us more information of what actually happened. Useful for log files when you're (or any human) trying to
            // deduce what happened
            throw new ArithmeticException("Attempt to divide by zero");
        }

    }

    // This method is not a good method - don't use a method to get input from users for any reason.
    public static int getInt(){
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter an integer");

        // Propagating the exception through the 'Call Stack' (aka through all the associated methods in the execution of the application).
        while (true){
            try {

                return s.nextInt();
            }

            // This block of code will only catch the 'InputMismatchException' exceptions and no ther type of exceptions.
            catch (InputMismatchException e){

                // Goes in loop until a valid integer is inputted.
                // Go round again. Read past the end of the line in the first input.

                // Goes to the next line.
                s.nextLine();

                System.out.println("Please enter a number using only the digits 0 to 9");
            }
        }
    }
}









 */
