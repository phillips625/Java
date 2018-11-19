package com.Philco;

public class Main {

    public static void main(String[] args) {


        Hamburger hamburger = new Hamburger("Basic", "Sausage", 3.56, "White");
        double price = hamburger.itemizeHamburger();

        // Adding additional items
        hamburger.addHamburgerAddition1("Tomato", 0.60);
        hamburger.addHamburgerAddition2("Onion", 0.50);
        hamburger.addHamburgerAddition3("cheese", 0.55);

        price = hamburger.itemizeHamburger();

        System.out.println("Total burger price is " + price);


        System.out.println("----------------------------");

        //// Healthy Burger
        HealthyBurger healthyBurger = new HealthyBurger("Bacon", 5.65);
        healthyBurger.addHamburgerAddition1("Egg",5.43);
        healthyBurger.addHealthAddition1("Lentills", 2.34);

        System.out.println( "Total healthy burger prices is "+ healthyBurger.itemizeHamburger());


        System.out.println("----------------------------");

        /////// Deluxe Burger
        DeluxeBurger deluxeBurger = new DeluxeBurger();
        deluxeBurger.addHamburgerAddition3("Shouldn't do this ", 12.45);
        deluxeBurger.itemizeHamburger();

    }
}
