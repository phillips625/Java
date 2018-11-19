package com.Philco;

/**
 * Created by PhillipsDaramola on 28/09/2017.
 */
    // 'extends' is used in inheritance
    // 'implements' is used to implement an interface.

    // This is used to implement the 'ITelephone' interface.
    // This is the class implementation of the 'ITelephone' interface.
public class DeskPhone implements ITelephone {

    private int myNumber;
    private boolean isRinging;

    public DeskPhone(int myNumber) {
        this.myNumber = myNumber;
    }

    // CMD + N, Click 'Implement Methods'.

    //If you delete one of these methods, you'll get an error.
    @Override
    public void powerOn() {
        System.out.println("No action taken.");
    }

    @Override
    public void dial(int phoneNumber) {
        System.out.println(phoneNumber + "is now being rung.");
    }

    @Override
    public void answer() {
        if (isRinging){
            System.out.println("Answering the desk phone");
            // set isRinging to false because we've now answered the phone.
            isRinging = false;
        }
    }

    @Override
    public boolean callPhone(int phoneNumber) {
        if (phoneNumber == myNumber){
            isRinging = true;
            System.out.println("Ring ring");
        }
        else {
            isRinging = false;
        }
        return isRinging;
    }

    @Override
    public boolean isRinging() {
        return isRinging;
    }
}
