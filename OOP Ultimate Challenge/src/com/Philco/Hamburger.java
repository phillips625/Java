package com.Philco;

// This is how base class

public class Hamburger {

    private String name;
    private double price;
    private String meat;
    private String breadRollType;

    // For additional items
    private String addition1Name;
    private double addition1Price;

    private String addition2Name;
    private double addition2Price;

    private String addition3Name;
    private double addition3Price;

    private String addition4Name;
    private double addition4Price;

    public Hamburger(String name, String meat, double price, String breadRollType) {
        this.name = name;
        this.price = price;
        this.meat = meat;
        this.breadRollType = breadRollType;
    }

    public void addHamburgerAddition1(String name, double price) {
        this.addition1Name = name;
        this.addition1Price = price;
    }
    public void addHamburgerAddition2(String name, double price) {
        this.addition2Name = name;
        this.addition2Price = price;
    }
    public void addHamburgerAddition3(String name, double price) {
        this.addition3Name = name;
        this.addition3Price = price;
    }
    public void addHamburgerAddition4(String name, double price) {
        this.addition4Name = name;
        this.addition4Price = price;
    }

    // Calculates the total price of the hamburger including the additional items (if any).
    public double itemizeHamburger() {

        // this.price is the value that was passed to us in the constructor
        double hamburgerPrice = this.price;

        System.out.println(this.name + " hamburger on a " + this.breadRollType + " roll price is with " + this.meat + ", price is " + this.price);

        if (this.addition1Name != null) {
            // Adds the price of the additional item to the final price
            hamburgerPrice += addition1Price;
            System.out.println("Added " + this.addition1Name + " for an extra " + this.addition1Price);
        }
        if (this.addition2Name != null) {
            // Adds the price of the additional item to the final price
            hamburgerPrice += addition2Price;
            System.out.println("Added " + this.addition2Name + " for an extra " + this.addition2Price);
        }
        if (this.addition3Name != null) {
            // Adds the price of the additional item to the final price
            hamburgerPrice += addition3Price;
            System.out.println("Added " + this.addition3Name + " for an extra " + this.addition3Price);
        }
        if (this.addition4Name != null) {
            // Adds the price of the additional item to the final price
            hamburgerPrice += addition4Price;
            System.out.println("Added " + this.addition4Name + " for an extra " + this.addition4Price);
        }

        return hamburgerPrice;
    }
}
