package com.philco;


// SORTED COLLECTIONS - e.g Linked hashmaps and Linked hashset. Basically like hashmaps and hashsets, but this time, they are in order.

import java.util.Map;

public class Main {

    private static StockList stockList = new StockList();

    public static void main(String[] args) {


        StockItem temp = new StockItem("Bread", 0.86, 100);
        stockList.addStock(temp);

        temp = new StockItem("Cake", 1.10, 17);
        stockList.addStock(temp);

        temp = new StockItem("Car", 31.10, 2);
        stockList.addStock(temp);

        temp = new StockItem("Juice", 12.10, 47);
        stockList.addStock(temp);

        temp = new StockItem("Cup", 34.10, 447);
        stockList.addStock(temp);
        temp = new StockItem("Cup", 72.10, 47);
        stockList.addStock(temp);

        temp = new StockItem("Chair", 11.10, 57);
        stockList.addStock(temp);

        temp = new StockItem("Chips", 61.10, 67);
        stockList.addStock(temp);

        temp = new StockItem("Apple", 41.10, 77);
        stockList.addStock(temp);

        // Prints the toString method.
        System.out.println(stockList);

        Basket jamesBasket = new Basket("James");
        sellItem(jamesBasket, "Car", 1);
        System.out.println(jamesBasket);

        sellItem(jamesBasket, "Car", 1);
        System.out.println(jamesBasket);

        // This should print out an error as we only have 2 cars initially in stock - (which have now been sold).
        sellItem(jamesBasket, "Car", 1);

        // This product does not exist - so should fail.
        sellItem(jamesBasket, "Spanner", 5);

//        System.out.println(jamesBasket);

        sellItem(jamesBasket, "Chair", 10);
        sellItem(jamesBasket, "Chips", 12);
        sellItem(jamesBasket, "Apple", 20);

//        System.out.println(jamesBasket);

        Basket basket = new Basket("customer");
        sellItem(basket, "Chair", 4);
        sellItem(basket, "Chips", 4);
        removeItem(basket, "Apple", 4);

        System.out.println(basket);

        removeItem(jamesBasket, "Car", 1);
        removeItem(jamesBasket, "Chips", 4);
        removeItem(jamesBasket, "Car", 1);
        System.out.println(jamesBasket);

        System.out.println("\nDisplay stock list before and after check out\n");
        // Before check-out
        System.out.println(basket);
        System.out.println(stockList);
        checkOut(basket);
        // After checkout
        System.out.println(basket);
        System.out.println(stockList);

//        System.out.println(stockList);

//        // Trying to save an item that does not exit.
//        temp = new StockItem("Pen", 1.12);
//
//        // 'Items' returns the unmodifiable map. This will return an error as we are trying to modify an unmodifiable map.
//        // Adding the above item to the stockList.
//        stockList.Items().put(temp.getName(), temp);


        StockItem car = stockList.Items().get("Car");
        if (car != null){

            // 'stockList.Items().get("Car")' returns a StockItem value.
            // Despite the fact that Items() returns an unmodifiable map, we are still able to adjust an individual item (using the
            // 'adjustStock' method).
            // Remember: It is the collection itself THAT IS UNMODIFIABLE, AND NOT THE OBJECTS WITHIN IT.
            car.adjustStock(20000);

            System.out.println(stockList);
        }

                            // OR

        if (car != null){

            stockList.get("Car").adjustStock(20000);
        }

        // entrySet is the Set of the returned price list.
        // The individual entries in the 'stockList.priceList()' map cannot be modified.
//        for (Map.Entry<String, Double> price: stockList.priceList().entrySet()){
//            System.out.println(price.getKey() + " costs " + price.getValue());
//        }

        checkOut(jamesBasket);
        System.out.println(jamesBasket);
    }

    public static int sellItem(Basket basket, String item, int quantity){

        // Retrieve the item from stock item.
        StockItem stockItem = stockList.get(item);
        // Check if we can sell the stock.
        if (stockItem == null){
            System.out.println("We don't sell " + item);
            return 0;
        }

        if (stockList.reserveStock(item, quantity) != 0){
            return basket.addToBasket(stockItem, quantity);
        }

        return 0;
    }

    public static int removeItem(Basket basket, String item, int quantity){

        // Retrieve the item from stock item.
        StockItem stockItem = stockList.get(item);
        // Check if we can sell the stock.
        if (stockItem == null){
            System.out.println("We don't sell " + item);
            return 0;
        }

        if (basket.removeFromBasket(stockItem, quantity) == quantity){
            return stockList.unreserveStock(item,quantity);
        }

        return 0;
    }

    public static void checkOut(Basket basket){

        for (Map.Entry<StockItem, Integer> item : basket.Items().entrySet()){

            // Sells all the stocks in the basket.
            // item.getValue() gets the value of each 'Map<StockItem, Integer>' item in the basket
            stockList.sellStock(item.getKey().getName(), item.getValue());
        }
        basket.clearBasket();
    }
}






















