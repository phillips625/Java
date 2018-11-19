package com.Philco;

/**
 * Created by PhillipsDaramola on 06/08/2017.
 */
public class DeluxeBurger extends Hamburger {

    public DeluxeBurger() {
        super("Deluxe", "Sausage", 14.59, "Brown");
        super.addHamburgerAddition1("Chips",3.56);
        super.addHamburgerAddition2("Drink", 1.67);
    }

    ////// We're preventing additional items being added to the deluxe burger

    @Override
    public void addHamburgerAddition1(String name, double price) {
        // super.addHamburgerAddition1(name, price);
        System.out.println("Cannot add additional items to the Deluxe edition.");
    }

    @Override
    public void addHamburgerAddition2(String name, double price) {
        System.out.println("Cannot add additional items to the Deluxe edition.");
    }

    @Override
    public void addHamburgerAddition3(String name, double price) {
        System.out.println("Cannot add additional items to the Deluxe edition.");
    }

    @Override
    public void addHamburgerAddition4(String name, double price) {
        System.out.println("Cannot add additional items to the Deluxe edition.");
    }
}
