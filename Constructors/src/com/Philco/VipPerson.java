package com.Philco;

/**
 * Created by PhillipsDaramola on 25/06/2017.
 */
public class VipPerson {

    private String name;
    private double creditLimit;
    private String emailAddress;

    // This is actually the constructor that saves the values into the above fields.
    public VipPerson(String name, double creditLimit, String emailAddress) {
        this.name = name;
        this.creditLimit = creditLimit;
        this.emailAddress = emailAddress;
    }

    public VipPerson(){
        this("Default", 345.00, "default@yahoo.com");
    }

    public VipPerson(String name, double creditLimit) {
        this(name, creditLimit, "unknown@yahoo.com");
    }

    public String getName() {
        return name;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
