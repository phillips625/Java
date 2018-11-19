package com.philski.mylibrary;

/**
 * Created by PhillipsDaramola on 12/10/2017.
 */
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
