package com.philco;

/**
 * Created by PhillipsDaramola on 15/10/2017.
 */

// This class is created to explain why we would be using the 'final' keyword in a crucial method we don't want to change.
    // We don't want other classes to be aware of methods we want discreet.
public class ExtendedPassword extends Password {

    private int decryptedPassword;

    public ExtendedPassword(int password) {
        super(password);
        this.decryptedPassword = password;
    }

    // storePassword is now a final method in the Password class - hence cannot be overridden.
//    @Override
//    public void storePassword() {
//        // super.storePassword();
//        System.out.println("Saving password as " + this.decryptedPassword);
//    }
}
