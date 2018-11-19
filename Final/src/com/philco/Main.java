package com.philco;


//              CONSTANTS
// They are declared at class level.
// They are declared as 'static final' because we want them to be the same when we create an object (the static bit)
// and we want them to be unchangeable - 'final' bit.

    // This will give an error as the Math class is set to 'FINAL' - aka cannot be changed.
// public class Main extends Math{

public class Main{

    public static void main(String[] args) {

        // Each of these objects each have one copy of the 'final instanceNumber' variable.
        SomeClass one = new SomeClass("One");
        SomeClass two = new SomeClass("two");
        SomeClass three = new SomeClass("three");

        // This will give an error as instanceNumber is set to 'final'
        // - hence cannot be change.
        // one.instanceNumber = 4;

        System.out.println(Math.PI);

        // Math class cannot be instantiated as the Math constructor is made private.
        // You could think you could get around this by extending/inheriting from the Math class, Java made the Math class final!
        // "public final class Math" means the 'Math' class cannot be subclassed

        // Math m = new Math();

        int pw = 1111;

        //Password password = new Password(pw);
        Password password = new ExtendedPassword(pw);
        password.storePassword();

        // Wrong passwords
        password.letMeIn(12331);
        password.letMeIn(35353);

        // Correct password
        password.letMeIn(1111);


                    // SIBs
        System.out.println("Main Method Called");
        StaticInitialisationBlockTest staticInitialisationBlockTest = new StaticInitialisationBlockTest();
        staticInitialisationBlockTest.someMethod();
        // staticInitialisationBlockTest.owner calls the 'owner' property of the first static method in the StaticInitialisationBlockTest class.
        System.out.println("Owner is " + staticInitialisationBlockTest.owner);
    }
}
