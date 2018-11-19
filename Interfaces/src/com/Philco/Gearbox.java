package com.Philco;

/**
 * Created by PhillipsDaramola on 27/09/2017.
 */

// Interface is basically a commitment that once a method is created, we aren't going to change it.

public class Gearbox {

    private boolean clutchIsIn;

//    public void operateClutch(String inOrOut){
//        // clutchIsIn is set to true if 'inOrOut' is set to "in".
//        this.clutchIsIn = inOrOut.equalsIgnoreCase("in");
//    }

                            // OR

    // This will work, but the problem is that the signature of the code has been changed aka 'operateClutch' is overridden. If other classes/
    // code is reliant on 'operateClutch' taking in a string as a parameter, then you're screwed.
    // N.B you've changed the signature of 'operateClutch' from operateClutch(String inOrOut) to operateClutch(boolean inOrOut).

    public void operateClutch(boolean inOrOut){
        // clutchIsIn is set to true if 'inOrOut' is set to "in".
        this.clutchIsIn = inOrOut;
    }
}
