package com.Philco;

/**
 * Created by PhillipsDaramola on 31/08/2017.
 */
public class Contact {
    private String name;
    private String phoneNumber;

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    /*
    * Tim is explaining that method is called factory method and it is known as factory method pattern. That pattern is used
    * everywhere and it can be really helpful. It's easy to think of factories in the real world - a factory is just somewhere
    * that items gets produced such as cars, computers or TVs. In your case Contacts. Factory method is good approach to encapsulation.
    * */

    // We're going to expose a public static method so we can create a contact record. (This means we don't have to create a new Contact object
    // everytime we want to create a new contact).
    // 'static' is important here because we don't have to create a new object instance for this 'Contact' class.
    // This method will return a contact record.
    // e.g public static Contact createContact - returns Contact class
    // or public static int createContact - returns int
    public static Contact createContact (String name, String phoneNumber){
        return new Contact(name, phoneNumber);
    }

}
