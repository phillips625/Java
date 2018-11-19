package com.philco;

/**
 * Created by PhillipsDaramola on 15/10/2017.
 */

//        STATIC INITIALIZERS
    // These are not used very often.

public class StaticInitialisationBlockTest {

    // Type 'psfs' and IntelliJ automatically writes (public static final String).
    public static final String owner;

    // THESE ARE STATIC INITIALISATION BLOCKS. THEY ARE CALLED IN THE ORDER THAT THEY ARE DECLARED IN A CLASS.
    // (E.G THE SIB BELOW WILL BE CALLED FIRST, followed by the second SIB, then the constructors will FINALLY BE CALLED).
    // SIBs ARE A FORM OF CONSTRUCTORS!
    static {
        owner = "tim";
        System.out.println(" SIBTest static initialization block called");
    }

    public StaticInitialisationBlockTest() {
        System.out.println("SIB Constructor called");
    }

    static {
        System.out.println(" Second initialization block called");
    }

    public void someMethod() {
        System.out.println("someMethod called");
    }


}
