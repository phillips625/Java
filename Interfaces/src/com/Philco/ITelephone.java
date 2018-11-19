package com.Philco;

/**
 * Created by PhillipsDaramola on 27/09/2017.
 */
// 'i' in 'ITelephone' symbolises interface.
public interface ITelephone {

    // These are called the SIGNATURE.
    // Defining the methods that using this interface has to implement. We are not defining the code itself here - the actual code/
    // implementation goes to the class that inherits this interface.
    // For a class that are going to be implementing this interface, these are the methods that it has to implement.

    void powerOn();
    // private or public doesn't matter here. Intellij states that public is redundant which makes sense because we're implementing the interface
    // methods in another class. private or public will be used in the actual class - this class is only used to define the methods that will
    // be implemented.

    //public void dial(int phoneNumber);
    void dial(int phoneNumber);
    void answer();
    boolean callPhone(int phoneNumber);
    boolean isRinging();
}
