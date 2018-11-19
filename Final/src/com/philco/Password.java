package com.philco;

/**
 * Created by PhillipsDaramola on 15/10/2017.
 */
public class Password {

    private static final int key = 54232342;
    private final int encryptedPassword;

    public Password(int password) {
        this.encryptedPassword = encryptDecrypt(password);
    }

    private int encryptDecrypt(int password){
        // ^ means XOR. We're xoring the password we input with the 'key' above.
        return password ^ key;
    }

    // We don't want other classes to be aware of methods we want discreet.
    // This method is FINAL - meaning that it cannot be overridden by another class.
    public final void storePassword(){
        System.out.println("Saving password as " + this.encryptedPassword);
    }

    public boolean letMeIn(int password){

        if (encryptDecrypt(password) == this.encryptedPassword){
            System.out.println("Welcome");
            return true;
        }

        else {
            System.out.println(" Wrong password mate ");
            return false;
        }
    }
}
