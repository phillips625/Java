package com.Philco;

/**
 * Created by PhillipsDaramola on 06/08/2017.
 */
public class HealthyBurger extends Hamburger{

    private String healthyExtra1Name;
    private double healthyExtra1Price;

    private String healthyExtra2Name;
    private double healthyExtra2Price;

    public HealthyBurger(String meat, double price) {
        super("Healthy", meat, price, "Brown rye");
    }

    public void addHealthAddition1(String name, double price) {

        this.healthyExtra1Name = name;
        this.healthyExtra1Price = price;
    }

    public void addHealthAddition2(String name, double price) {

        this.healthyExtra2Name = name;
        this.healthyExtra2Price = price;
    }

    //// This method allows us to add the healthy additions from this class (from the addHealthAddition1 and addHealthAddition2 methods) to the overall total

    @Override
    public double itemizeHamburger() {

        // This stores the total price from hamburger class
        double hamburgerPrice = super.itemizeHamburger();

        if (healthyExtra1Name != null){
            hamburgerPrice += this.healthyExtra1Price;
            System.out.println("Added " + this.healthyExtra1Name + " for an extra " + this.healthyExtra1Price);
        }

        if (healthyExtra2Name != null){
            hamburgerPrice += this.healthyExtra2Price;
            System.out.println("Added " + this.healthyExtra2Name + " for an extra " + this.healthyExtra2Price);
        }

        // This NEW price includes the healthy extras
        return hamburgerPrice;
    }
}
