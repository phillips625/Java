package com.Philco;

/**
 * Created by PhillipsDaramola on 28/09/2017.
 */
public class MobilePhone implements ITelephone {

    private int myNumber;
    private boolean isRinging;
    private boolean isOn = false;

    public MobilePhone(int myNumber) {
        this.myNumber = myNumber;
    }

    // CMD + N, Click 'Implement Methods'.

    //If you delete one of these methods, you'll get an error.
    @Override
    public void powerOn() {
        isOn = true;
        System.out.println("Mobile Phone powered up.");
    }

    @Override
    public void dial(int phoneNumber) {
        if (isOn){
            System.out.println(phoneNumber + "is now being rung - mobile phone.");
        }
        else {
            System.out.println("Phone is switched off");
        }
    }

    @Override
    public void answer() {
        if (isRinging){
            System.out.println("Answering the mobile phone");
            // set isRinging to false because we've now answered the phone.
            isRinging = false;
        }
    }

    @Override
    public boolean callPhone(int phoneNumber) {
        if (phoneNumber == myNumber && isOn){
            isRinging = true;
            System.out.println("Melody ring");
        }
        else {
            isRinging = false;
            System.out.println("Mobile phone not on or number is different.");
        }
        return isRinging;
    }

    @Override
    public boolean isRinging() {
        return isRinging;
    }
}
