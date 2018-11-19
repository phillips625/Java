package com.Philco;

// How to resize an array without losing the incumbent data.

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // So we can get some input from the keyboard
    private static Scanner scanner = new Scanner(System.in);
    private static GroceryList groceryList = new GroceryList();

    public static void main(String[] args) {
        boolean quit = false;
        int choice = 0;
        printInstructions();

        while(!quit) {
            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();
            // This clears the input buffer
            scanner.nextLine();

            switch (choice) {
                case 0:
                    printInstructions();
                    break;
                case 1:
                    groceryList.printGroceryList();
                    break;
                case 2:
                    addItem();
                    break;
                case 3:
                    modifyItem();
                    break;
                case 4:
                    removeItem();
                    break;
                case 5:
                    searchForItem();
                    break;
                case 6:
                    processArrayList();
                    break;
                case 7:
                    quit = true;
                    break;
            }
        }
    }

    public static void printInstructions(){
        System.out.println("\nPress");
        System.out.println("\t0 - To print choice option");
        System.out.println("\t1 - To print the list of grocery items.");
        System.out.println("\t2 - To add an item to the list.");
        System.out.println("\t3 - To modify an item in the list.");
        System.out.println("\t4 - To remove an item in the list");
        System.out.println("\t5 - To search an item in the list");
        System.out.println("\t6 - To quit the application");
    }

    public static void addItem(){
        // System.out.print(); print all its content on the same line
        System.out.print("Please enter the grocery item: ");
        //scanner.nextLine() allows us to type anything until enter is pressed.
        groceryList.addGroceryItem(scanner.nextLine());
    }

    public static void modifyItem(){
        System.out.println("Enter current item name: ");
        // Accepts an integer
        // int itemNo = scanner.nextInt();
        String currentItem = scanner.nextLine();
        scanner.nextLine();

        System.out.println("Enter replacement item: ");
        String newItem = scanner.nextLine();
        // itemNo - 1 specifies the actual position of the item.
        groceryList.modifyGroceryItem(currentItem, newItem);
    }

    public static void removeItem() {
        System.out.println("Enter item name: ");
        // Accepts an integer
        String currentItem = scanner.nextLine();
        // If you don't add -1, you'll go out of bounds.
        groceryList.removeGroceryItem(currentItem);
    }

    public static void searchForItem(){
        System.out.println("Enter item to search for: ");
        String searchItem = scanner.nextLine();
        if(groceryList.onFile(searchItem)){
            System.out.println("Found " + searchItem + " in the list!");
        }
        else {
            System.out.println(searchItem + " is not on the list.");
        }
    }

    /*This will allow you to copy one array list to another (via the getter in the GroceryList class).*/
    public static void processArrayList(){

        ArrayList<String> newArrayList = new ArrayList<String>();
        /*This adds the content of groceryList.getGroceryList() into newArrayList*/
        newArrayList.addAll(groceryList.getGroceryList());

                // ANOTHER WAY
        /*This adds the content of groceryList.getGroceryList() into nextArray*/
        ArrayList<String> nextArray = new ArrayList<String>(groceryList.getGroceryList());

                // THIS CONVERTS THE ARRAYLIST INTO AN ARRAY
        //This sets the String array to the exact size of the array list.
        String[] myArray = new String[groceryList.getGroceryList().size()];
        /*toArray returns an array of Strings. toArray converts the array list returned in 'groceryList.getGroceryList()' to an array. myArray is passed in
        as an argument as this specifies the new name of the array.*/
        myArray = groceryList.getGroceryList().toArray(myArray);
    }
}



/*
package com.Philco;

// How to resize an array without losing the incumbent data.

import java.util.Scanner;

public class Main {

    private static Scanner s = new Scanner(System.in);
    private static int[] baseData = new int[10];

    public static void main(String[] args) {
        System.out.println("Enter 10 integers:");
        getInput();
        printArray(baseData);
        resizeArray();

        System.out.println("Enter 12 integers:");
        // getInput();
        baseData[10] = 67;
        baseData[11] = 34;
        printArray(baseData);

    }

    // This gets input from console
    private static void getInput()
    {
        for(int i = 0; i < baseData.length; i++)
            baseData[i] = s.nextInt();
    }

    // This prints an array
    private static void printArray(int[] arr)
    {
        for(int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    // This resizes the 'baseData' array by copying the original array into 'original', then extend the baseData array. Finally, we copy over the
    // data from 'original' array into the 'baseData' array using the for loop.
    private static void resizeArray()
    {
        // This is a copy of the above 'baseData' array.
        int[] original = baseData;

        baseData = new int[12];
        for (int i = 0; i < original.length; i++)
            baseData[i] = original[i];
    }

}

*/

