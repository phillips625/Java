package com.Philco;

// Interface refers to a method that a class must implement. The interface itself is abstract (which means that there is no code for any
// of the methods) - you only supply the method name and the parameters that the method will accept.
// The idea of an INTERFACE is to provide a common behaviour that could be used by several classes by having them all implement the same
// interface. It is used to standardize the way a class is used.

// Both the MobilePhone class and DeskPhone class implements the ITelephone class.

public class Main {

    public static void main(String[] args) {

        // We can't do this because 'Deskphone' implements the functionality of a given type of phone - deskphone -
        // hence the reason why you have to do "jPhone = new DeskPhone(1234);"..
        // ITelephone jPhone = new ITelephone().

        ITelephone jPhone;
                // OR
        // This works fine by itself. This will give an error on  'jPhone = new MobilePhone(234);' because MobilePhone and DeskPhone are
        // different classes.
        // DeskPhone jPhone;

        // You have to use the appropriate class that has implemented the interface.
        jPhone = new DeskPhone(1234);

        jPhone.powerOn();
        jPhone.callPhone(1234);
        jPhone.answer();

        // This object is possible because we're implementing the same interface.
        jPhone = new MobilePhone(234);
        jPhone.powerOn();
        jPhone.callPhone(234);
        jPhone.answer();
    }
}
