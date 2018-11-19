package com.Philco;

import java.util.ArrayList;

/**
 * Created by PhillipsDaramola on 03/09/2017.
 */
public class MobilePhone {

    // You declare variables here
    private String myNumber;
    // Array list of 'Contact' objects (records).
    private ArrayList<Contact> myContacts;

    // You initialise variables in the constructor
    public MobilePhone(String myNumber) {
        this.myNumber = myNumber;

        // Creates an empty list
        myContacts = new ArrayList<Contact>();
    }

    // Returns true if the contact has been added successfully, false if the contact already exists.
    // We're passing in a contact object creates.
    public boolean addNewContact(Contact contact){
        if (findContact(contact.getName()) >= 0){
            System.out.println("Contact is already on file.");
            return false;
        }

        // add method adds contact to the above array list.
        myContacts.add(contact);
        // Returns true when the record is successfully added.
        return true;
    }

    // oldContact is used to check if the contact is on file.
    // newContact is the new contact to update it to.
    public boolean updateContact(Contact oldContact, Contact newContact){
        int findPosition = findContact(oldContact);
        if (findPosition < 0){
            System.out.println(oldContact.getName() + ", was not found.");
            return false;
        }

        // This will update the array list with the new contact. 'findPosition' is the position of the element we want to update.
        this.myContacts.set(findPosition, newContact);
        System.out.println(oldContact.getName() + ", was replaced with " + newContact.getName());
        return true;
    }

    public boolean removeContact(Contact contact){
        int findPosition = findContact(contact);
        if (findPosition < 0){
            System.out.println(contact.getName() + ", was not found.");
            return false;
        }

        this.myContacts.remove(findPosition);
        System.out.println(contact.getName() + ". was deleted.");
        return true;
    }

    // We're going to overload the findContact method.

    // returns the index of contact
    private int findContact(Contact contact){
        return this.myContacts.indexOf(contact);
    }
    private int findContact(String contactName){
        for (int i = 0; i<this.myContacts.size(); i++){

            // We're are getting the contact object in the array list and assigning it to 'contact'.
            Contact contact = this.myContacts.get(i);

            // if 'contact' is equal to the contactName that was passed to this method, return the position number in the array list
            if (contact.getName().equals(contactName)){
                return i;
            }
        }

        // Return -1 if record cannot be found
        return -1;
    }

    // queryContact methods are using function overload
    public String queryContact (Contact contact){

        // This confirms if the contact is on file.
        if (findContact(contact) >= 0){
            return contact.getName();
        }

        return null;
    }

    // We're passing in a name and retrieving the contact record based on that name.
    public Contact queryContact(String name){

        // This gets the position number of the name
        int position = findContact(name);
        // If the position exists
        if(position>=0){
            // Return the contact object in that position.
            return this.myContacts.get(position);
        }

        return null;
    }


    public void printContacts(){

        System.out.println("Contact List");
        for (int i = 0; i<this.myContacts.size(); i++){
            System.out.println((i+1) + "." +
                    // 'myContacts.get(i)' is a 'contact' object
                    this.myContacts.get(i).getName() + " -> " +
                    this.myContacts.get(i).getPhoneNumber());
        }
    }

}
























