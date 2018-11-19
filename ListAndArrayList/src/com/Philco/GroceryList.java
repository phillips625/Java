package com.Philco;

import java.util.ArrayList;

/**
 * Created by PhillipsDaramola on 20/08/2017.
 */


/*This class should handle all complexities.*/

public class GroceryList {

    // ArrayList is a class. It allows you to specify what you want to store in the list i.e. String in this case. We don't have to specify how many items
    // there are in the ArrayList as ArrayList handles that for us.
    private ArrayList<String> groceryList = new ArrayList<String>();


    public void addGroceryItem(String item) {
        // The add method automatically adds automatically handles the allocation of each item.
        groceryList.add(item);
    }

    // This is a getter for the array list.
    public ArrayList<String> getGroceryList() {
        return groceryList;
    }

    public void printGroceryList() {

        // With array, you have '.length'. With ArrayList, you have '.size()'
        System.out.println("You have " + groceryList.size() + " items in your list");

        for(int i = 0; i < groceryList.size(); i++){
            // '.get' is how you access the element in groceryList.
            System.out.println((i + 1) + ". " + groceryList.get(i));
        }
    }

    // TO ENHANCE modifyGroceryItem
    public void modifyGroceryItem(String currentItem, String newItem) {
        // Checks if newItem exists.
        int position = findItem(currentItem);
        if (position >= 0){
            // If currentItem exists, we're passing it's position and the newItem we want to take its place.
            modifyGroceryItem(position, newItem);
        }
    }

    // This will be used to replace an item
    // Now this method is only accessible by this class as we don't want people messing with the position of the items.
    private void modifyGroceryItem(int position, String newItem) {
        groceryList.set(position, newItem);
        System.out.println("Grocery Item " + (position + 1) + " has been modified.");
    }

    // TO ENHANCE removeGroceryItem
    public void removeGroceryItem(String item) {
        // Checks if newItem exists.
        int position = findItem(item);
        if (position >= 0){
            removeGroceryItem(position);
        }
    }

    // This is used to remove an item
    // Now this method is only accessible by this class as we don't want people messing with the position of the items.
    private void removeGroceryItem(int position) {
        // gets the item at that position and assign this to a string (because arrayList is a string).
        // String theItem = groceryList.get(position);

        //E.g
        // Milk - 0                     Milk - 0
        // Bread - 1        becomes     Meat - 1 (New position 1 item)
        // Meat - 2
        groceryList.remove(position);
    }

    // IMPROVEMENT
    // Used to find items.
    // We don't want to expose the position of the items.
    private int findItem(String searchItem) {

        // returns the index of searchItem
        return groceryList.indexOf(searchItem);
    }

    public boolean onFile(String searchItem){
        int position = findItem(searchItem);
        if (position >= 0){
            return true;
        }
        return false;
    }

    /*
    // Used to find items.
    public String findItem(String searchItem) {
        // Hold down CMD and click "contains"
        // boolean exists = groceryList.contains(searchItem);

        // indexOf returns the index of 'searchItem' if it exists in the ArrayList.
        // indexOf returns -1 if item does not exist.
        int position = groceryList.indexOf(searchItem);
        if(position >= 0) {
            // returns the item using the get method
            return groceryList.get(position);
        }

        // If the item doesn't exist, return null.
        return null;
    }
    */
}







/*

public class GroceryList {

    // [] allows you to specify that 'myNumbers is an array'
    private int[] myNumbers = new int[50];
    // This has to be in a method, hence it will not work here.
    // myNumbers[4] = 50;

    // ArrayList is a class. It allows you to specify what you want to store in the list i.e. String in this case. We don't have to specify how many items
    // there are in the ArrayList as ArrayList handles that for us.
    private ArrayList<String> groceryList = new ArrayList<String>();


    public void addGroceryItem(String item) {
        // This works because it is in a class.
        // myNumbers[4] = 50;
    }
}
 */
