package com.Philco;

/**
 * Created by PhillipsDaramola on 25/06/2017.
 */
public class Account {

    private String number;
    private double balance;
    private String customerName;
    private String customerEmailAddress;
    private String customerPhoneNumber;


    // A CONSTRUCTOR
    public Account(){

        // This is used to call the overloaded constructor. This technique of calling a constructor from another constructor is unique to java.
        // This technique is used to make sure that you always have a default setter.
        this("Default number", 124.32, "Default name", "Default email", "Default number");
        System.out.println("Nice Ice Baby");
    }

    // An OVERLOADED CONSTRUCTOR. THIS IS THE MAIN/DADDY CONSTRUCTORS. All the other overloaded constructors are used to update this daddy constructor.
    public Account(String number, double balance, String customerName, String customerEmailAddress, String customerPhoneNumber){

        System.out.println("Nice Ice Baby with Parameters");
        // Setting the field values.
        // Use this method to set values in your field rather than the usual 'set..' e.g 'setNumber'. There are some cases where 'set..' might
        // not necessarily set your fields.
        this.number = number;
        this.balance = balance;
        this.customerName = customerName;
        this.customerEmailAddress = customerEmailAddress;
        this.customerPhoneNumber = customerPhoneNumber;

    }

    public Account(String customerName, String customerEmailAddress, String customerPhoneNumber) {
        // Set accountNumber and accountbalance manually
        this("99999", 100.5, customerName, customerEmailAddress, customerPhoneNumber);

        // this.customerName = customerName;
        // this.customerEmailAddress = customerEmailAddress;
        // this.customerPhoneNumber = customerPhoneNumber;
    }

    public void deposit(double depositAmount){
        this.balance += depositAmount;
        System.out.println("Deposit of " + depositAmount + " made. New Balance = " + this.balance);
    }

    public void withdrawal (double withdrawalAmount){

        if (this.balance - withdrawalAmount < 0){
            System.out.println("Only " + this.balance + " available. Withdrawal not approved. ");
        }
        else {
            this.balance -= withdrawalAmount;
            System.out.println("Withdrawal of " + withdrawalAmount + " processed. Remaining Balance = " + this.balance);
        }

    }

    public String getNumber() {
        return number;
    }

    // SETTERS ARE NOT NEEDED SINCE THE CONSTRUCTOR HAS TAKEN IT'S PLACE.
    public void setNumber(String number) {
        this.number = number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmailAddress() {
        return customerEmailAddress;
    }

    public void setCustomerEmailAddress(String customerEmailAddress) {
        this.customerEmailAddress = customerEmailAddress;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }
}
