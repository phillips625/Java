      // To Create this class

// Right-click on the src folder
// Select 'class'
// Type 'com.philski.mylibrary.Series'
// Click ok

package com.philski.mylibrary;

/**
 * Created by PhillipsDaramola on 11/10/2017.
 */
public class Series {

    // These methods have to be static because we are going to be using them as if they have been written in the 'Main' class.
    public static long nSum(int n){
        return (n * (n + 1))/2;
    }

    public static long factorial(int n){

        if (n==0){
            return 0;
        }

        long fact = 1;
        for (int i = 1; i <= n; i++){
            fact *= i;
        }

        return fact;
    }

    public static long fibonacci(int n){

        if (n == 0){
            return 0;
        }
        else if (n == 1){
            return 1;
        }

        long nMinus1 = 1;
        long nMinus2 = 0;
        long fib = 0;

        for (int i = 1; i < n; i++){
            fib = nMinus1 + nMinus2;
            nMinus2 = nMinus1;
            nMinus1 = fib;
        }

        return fib;
    }
}

// Don't forget to delete the main class after testing this class.

      /*         IN THE MAIN CLASS

      package com.philski;
// This is where the SERIES class resides.
import com.philski.mylibrary.Series;

public class Main {

    public static void main(String[] args) {

        for (int i = 0; i <= 10; i++){

            // The reason we can do 'Series.nSum' is because nSum is a static method - hence we have to call the class name directly.
            System.out.println(Series.nSum(i));
        }

        for (int i = 0; i <= 10; i++){
            System.out.println(Series.factorial(i));
        }

        for (int i = 0; i <= 10; i++){
            System.out.println(Series.fibonacci(i));
        }
    }
}


       */