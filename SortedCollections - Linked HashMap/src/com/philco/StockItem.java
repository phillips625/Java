package com.philco;

/**
 * Created by PhillipsDaramola on 01/11/2017.
 */

// We're going to store the names of items for sale, their price and quantity in stock.
    // We're going to implement 'Comparable' so that the stock items can be used in the sorted collections.
public class StockItem implements Comparable<StockItem>{

    private final String name;
    private double price;
    private int quantityInStock = 0;
    private int reserved = 0;

    public StockItem(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantityInStock = 0;
    }

    public StockItem(String name, double price, int quantityStock) {
        this.name = name;
        this.price = price;
        this.quantityInStock = quantityStock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int availableQuantity() {
        // The quantity of items we left have in stock
        return quantityInStock - reserved;
    }

    public void setPrice(double price){

        if (price > 0.0){
            this.price = price;
        }
    }

    public void adjustStock(int quantity) {
        int newQuantity = this.quantityInStock + quantity;
        if (newQuantity >= 0){
            this.quantityInStock = newQuantity;
        }
    }

    // Checks that there are enough stocks available before increasing the reserved amount.
    public int reserveStock(int quantity){

        if (quantity <= availableQuantity()){
            reserved += quantity;
            // returns quantity that has been reserved.
            return quantity;
        }
        return 0;
    }

    public int unreserveStock(int quantity){
        if (quantity <= reserved){
            reserved -= quantity;
            // returns quantity that is unreserved.
            return quantity;
        }

        return 0;
    }

    // Checks that there are enough stocks to reserve.
    public int finaliseStock(int quantity){

        if (quantity <= reserved){
            quantityInStock -= quantity;
            reserved -= quantity;
            return quantity;
        }

        return 0;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + 56;
    }

    @Override
    public boolean equals(Object obj) {

        System.out.println("Entering stockItem.equals");
        // If we're referring to the same object.
        if (obj == this){
            return true;
        }

        // if the object is null or if it different from the class we're currently in - we can't compare 2 different classes.
        if ((obj == null) || (obj.getClass() != this.getClass())){
            return false;
        }

        String objName = ((StockItem) obj).getName();
        // Returns a true or false.
        return this.name.equals(objName);
    }

    @Override
    public int compareTo(StockItem o) {
        // System.out.println("Entering StockItem compareTo");

        // Check if this 'class' is equal to the object o.
        if (this == o){
            return 0;
        }

        if (o != null){
            return this.name.compareTo(o.getName());
        }

        // This is basically making sure we're not comparing anything to null.
        throw new NullPointerException();
    }

    // Called when we print a StockItem object - e.g sout (new StockItem());
    @Override
    public String toString() {
        return this.name + " : price " + this.price + ". Reserved: " + this.reserved;
    }
}
