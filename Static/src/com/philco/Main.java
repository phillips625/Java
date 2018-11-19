package com.philco;

public class Main {


    public static int multiplier = 7;

    public static void main(String[] args) {

                            // WITH THE STATIC METHOD.

        StaticTest firstInstance = new StaticTest("First Instance");
        System.out.println(firstInstance.getName() + " is instance number " + StaticTest.getNumInstances());

        StaticTest secondInstance = new StaticTest("Second Instance");
        System.out.println(secondInstance.getName() + " is instance number " + StaticTest.getNumInstances());

        StaticTest thirdInstance = new StaticTest("Third Instance");

        // Notice 'secondInstance.getNumInstances' is UNCHANGED AND YET STILL GOT 3 (AS EXPECTED).
        // THIS IS BECAUSE firstInstance, secondInstance AND thirdInstance all share the same copy of the static 'numInstances'
        // variable in the StaticTest class.

        //System.out.println(thirdInstance.getName() + " is instance number " + secondInstance.getNumInstances());

                    // OR - For the purpose of good practise.
        System.out.println(thirdInstance.getName() + " is instance number " + StaticTest.getNumInstances());

/*
        // Trying to access a non-static field (multiplier) or method (multiply) in a static class will not work
        // because the main function is automatically called by the Main class (because it is static) and it is required that all
        // the methods and fields associated with the main method should also be static.
        int answer = multiply(6);
        System.out.println("The answer is " + answer);
        System.out.println("The answer is " + multiplier);

        */

// Trying to access a non-static field (multiplier) or method (multiply) in a static class will not work
        // because the main function is automatically called by the Main class (because it is static) and it is required that all
        // the methods and fields associated with the main method should also be static.
        int answer = multiply(6);
        System.out.println("The answer is " + answer);
        System.out.println("The answer is " + multiplier);
    }

    public static int multiply (int number){
        return number * multiplier;
    }
}

/*
                            Without the STATIC METHOD.

        StaticTest firstInstance = new StaticTest("First Instance");
        System.out.println(firstInstance.getName() + " is instance number " + firstInstance.getNumInstances());

        StaticTest secondInstance = new StaticTest("Second Instance");
        System.out.println(secondInstance.getName() + " is instance number " + secondInstance.getNumInstances());

        StaticTest thirdInstance = new StaticTest("Third Instance");

        // Notice 'secondInstance.getNumInstances' is UNCHANGED AND YET STILL GOT 3 (AS EXPECTED).
        // THIS IS BECAUSE firstInstance, secondInstance AND thirdInstance all share the same copy of the static 'numInstances'
        // variable in the StaticTest class.

        //System.out.println(thirdInstance.getName() + " is instance number " + secondInstance.getNumInstances());

                    // OR - For the purpose of good practise.
        System.out.println(thirdInstance.getName() + " is instance number " + thirdInstance.getNumInstances());

 */