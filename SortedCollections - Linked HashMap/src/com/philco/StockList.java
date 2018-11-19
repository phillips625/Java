package com.philco;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by PhillipsDaramola on 03/11/2017.
 */
public class StockList {

    // Because it is marked as final, we have to initialize it.
    private Map<String, StockItem> list;

    public StockList() {
        // list will be initialised when the stock map is created.
        // Unordered list when items are added.
        //this.list = new HashMap<>();

        // Ordered list when items are added.
        this.list = new LinkedHashMap<>();
    }

    // Used to add items to our list of stock items. Adding a stock item to the list or updating an item if we already have one with that name.
    public int addStock(StockItem item){

        if (item != null){
            // getOrDefault gets the item if it exists otherwise it passes the second item to the list. Either way,
            // inStock is going to have an item (either one passed to the list (item) or the one retrieved from the list). The first
            // argument tells us what item to look up.

            // Check if we have quantity in the list aka was this already in the list, adjust the quantity.
            StockItem inStock = list.getOrDefault(item.getName(), item);

            // If there are already stocks of this item.
            if (inStock != item){
                item.adjustStock(inStock.availableQuantity());
            }

            list.put(item.getName(), item);
            return item.availableQuantity();
        }
        return 0;
    }

    // For stop sale.
    // String item is used to look up our map.
    public int sellStock(String item, int quantity){
//        // We're doing a look up based on item.
//        StockItem inStock = list.getOrDefault(item, null);
//        if ((inStock != null) && (inStock.availableQuantity() >= quantity) && (quantity > 0)){
//            inStock.adjustStock(-quantity);
//            return quantity;
//        }
//        return 0;


        StockItem inStock = list.get(item);

        if ((inStock != null) && (quantity > 0)){
            // Instead of adjusting the item stock level directly, we're calling the finaliseStock method.
            return inStock.finaliseStock(quantity);
        }
        return 0;

    }

    public int reserveStock(String item, int quantity){
        // Returns a 'StockItem' value.
        StockItem inStock = list.get(item);

        if ((inStock != null) && (quantity > 0)){
            return inStock.reserveStock(quantity);
        }
        return 0;
    }

    public int unreserveStock(String item, int quantity){
        // Returns a 'StockItem' value.
        StockItem inStock = list.get(item);

        if ((inStock != null) && (quantity > 0)){
            return inStock.unreserveStock(quantity);
        }
        return 0;
    }

    public StockItem get(String name){
        // This returns a StockItem (from the map) using the 'name' as the key.
        return list.get(name);
    }

    // Returns a list of prices
    public Map<String, Double> priceList(){
        Map<String, Double> prices = new LinkedHashMap<>();

        // Retrieving entries in a map
        for (Map.Entry<String, StockItem> item: list.entrySet() ){
            prices.put(item.getKey(), item.getValue().getPrice());
        }

        return Collections.unmodifiableMap(prices);
    }

    public Map<String, StockItem> Items(){
        // This ensures that the list cannot be modified aka a map that cannot be modified.
        // Think about views in sql - can view but doesn't modify the list. Faster to access.
        return Collections.unmodifiableMap(list);
    }

        // This is not a good idea as toString is mainly used for debugging.

    // This will return the stock list.
    @Override
    public String toString() {

        String s = "\nStock List\n";
        double totalCost = 0.0;
        // Iterating though the entire map and prints the full list of stock.
        for (Map.Entry<String, StockItem> item : list.entrySet()){
            StockItem stockItem = item.getValue();
            double itemValue = stockItem.getPrice() * stockItem.availableQuantity();

            s = s + stockItem + ", there are " + stockItem.availableQuantity() + " in stock. Value of items: ";
            s = s + String.format("%.2f", itemValue) + "\n";
            totalCost += itemValue;
        }

        return s + " Total stock value " + totalCost;
    }
}
























