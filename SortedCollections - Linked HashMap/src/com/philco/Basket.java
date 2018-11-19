package com.philco;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by PhillipsDaramola on 03/11/2017.
 */
public class Basket {

    private final String name;
    // The key is the Item. The value is the quantity of an item that has been ordered.
    private final Map<StockItem, Integer> list;

    public Basket(String name) {
        this.name = name;
        // this.list = new HashMap<>();

        // Arranges the items in the basket in alphabetic order.
        // The 'TreeMap' uses the compareTo method in the 'StockItem' class to arrange the items in the basket in alphabetic order.
        // Each time we add a 'StockItem', we are going to be calling the compareTo method.
        // TreeMaps affects and reduces performance - computationally more costly. Hashmap doesn't have to execute that bit of extra code.
        this.list = new TreeMap<>();
    }

    // 'quantity' is the quantity of the item that the customer is ordering.
    public int addToBasket(StockItem item, int quantity){

        if ((item != null) && (quantity > 0)){
            // getOrDefault - we're checking if the specific 'item' is in the list. The key is the 'item' we're searching for.
            // We're defaulting to 0 if there wasn't any previous item (so that we can tally up the quantity of that item correctly).
            // getOrDefault - means get 'item' present or default to 0.
            // inBasket retrieves quantity of 'item' (if the item already exists) or is set to 0 (when it is a new item).
            int inBasket = list.getOrDefault(item, 0);
            // Adds the 'item' from above if there was one or defaults to 0 otherwise.
            // 'inBasket + quantity' is the new quantity.
            list.put(item, inBasket + quantity);
            return inBasket;
        }

        return 0;
    }

    public int removeFromBasket(StockItem item, int quantity){

        if ((item != null) && (quantity > 0)){
           // returns the current quantity in the basket otherwise, returns 0.
            // Bottom line, we get the quantity added to the basket - 0 or quantity of item returned (from the value in the key-value pair).
            int inBasket = list.getOrDefault(item, 0);
            int newQuantity = inBasket - quantity;

            if (newQuantity > 0){
                list.put(item, newQuantity);
                return quantity;
            }

            // Removes the item if the quantity is less than 0.
            else if (newQuantity == 0){
                list.remove(item);
                return quantity;
            }
        }

        return 0;
    }

    // Method to clear the basket.
    public void clearBasket(){
        this.list.clear();
    }

    public Map<StockItem, Integer> Items() {
        return Collections.unmodifiableMap(list);
    }

    @Override
    public String toString() {

        String s = "\nShopping basket " + name + " contains " + list.size() + ((list.size() == 1) ? " item" : " items") + "\n";
        double totalCost = 0.0;
        for (Map.Entry<StockItem, Integer> item : list.entrySet()){
            s = s + item.getKey() + ", " + item.getValue() + " purchased. \n";
            totalCost += item.getKey().getPrice() * item.getValue();
        }
        return s + " Total cost " + totalCost;
    }
}



















