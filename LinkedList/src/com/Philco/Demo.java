package com.Philco;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * Created by PhillipsDaramola on 13/09/2017.
 */

// LinkedList is great when you're adding data on an ongoing basis.

public class Demo {
    /*psmv + tab*/
    public static void main(String[] args) {

        LinkedList<String> placesToVisit = new LinkedList<String>();

        addInOrder(placesToVisit,"Sydney");
        addInOrder(placesToVisit,"Melbourne");
        addInOrder(placesToVisit,"Brisbane");
        addInOrder(placesToVisit,"Perth");
        addInOrder(placesToVisit,"Canberra");
        addInOrder(placesToVisit,"Adelaide");
        addInOrder(placesToVisit,"Darwin");

        /*
        placesToVisit.add("Sydney");
        placesToVisit.add("Melbourne");
        placesToVisit.add("Brisbane");
        placesToVisit.add("Perth");
        placesToVisit.add("Canberra");
        placesToVisit.add("Adelaide");
        placesToVisit.add("Darwin");
        */
        printList(placesToVisit);

        /*
        // Adding 'Alice Springs' to the first index e.g a[1].
        // Notice how 'Alice Springs' automatically links to 'Melbourne'. Sydney is now point to 'Alice
        // Springs'.
        placesToVisit.add(1, "Alice Springs");
        printList(placesToVisit);

        // Remove element in index 4 aka 'Perth'.
        placesToVisit.remove(4);
        printList(placesToVisit);
        */

        addInOrder(placesToVisit,"Alice Strings");
        // This should trigger the "comparison == 0"
        addInOrder(placesToVisit,"Darwin");

        printList(placesToVisit);

        visit(placesToVisit);
    }

    public static void printList(LinkedList<String> linkedList){

        // This is the equivalent of a for loop - looping through the entire list.
        Iterator<String> i = linkedList.iterator();

        // i.hasNext() - While a list member is 'has a next item' to point to.
        // hasNext checks if there's a next entry and returns the current value 'next()' in the meantime.
        while (i.hasNext()){
            // i.next() - this is the current element.
            // i.next() ALSO CHANGES THE VALUE OF THE ITERATOR - this is the equivalent of typing i++.
            System.out.println("Now visiting: " + i.next());
        }
        System.out.println("==================================");
    }

    // In general, you want your function to do ONE THING! Here the function adds strings to the linkedlist and also returns
    // a boolean - this is because of how linkedlist has been created by the authors of Java.
    private static boolean addInOrder(LinkedList<String> linkedList, String newCity){
        // THIS IS BASICALLY A SET-UP FOR THE ITERATOR AND IT DOESN'T ACCESS THE FIRST ENTRY YET.
        // ListIterator is better than just Iterator. listiterator() starts with the first element in the linked list.
        // stringListIterator stores the result of THE REARRANGED LIST.
        ListIterator<String> stringListIterator = linkedList.listIterator();

        // Goes through each item in stringListIterator.
        while (stringListIterator.hasNext()){

            // '.next()' IS ACTUALLY WHERE THE FIRST ENTRY IN THE LINKEDLIST IS ACCESSED.
            // compareTo returns a number. It returns 0 if the item in stringListIterator matches the item in 'newCity'.
            int comparison = stringListIterator.next().compareTo(newCity);

            // This ensures that a destination cannot be added twice.
            if (comparison == 0){
                // Equal, do not add item.
                System.out.println(newCity + " is already included as a destination");
                return false;
            }
            else if (comparison > 0){
                // newCity should appear before this one
                // E.g entry
                //  0            1     ....
                // Brisbane -> Adelaide
                // Because stringListIterator.next() (aka Adelaide) points to the next element in the list immediately, we have
                // to go back one step - so we can add Brisbane to the list. We use the previous method to achieve this.
                // THIS IS THE MAJOR ADVANTAGE OF A ListIterator AS IT ALLOWS US TO GO BACK TO THE PREVIOUS ELEMENT
                // This goes to the previous index, adds Adelaide (which is the new city) in 'stringListIterator.add(newCity);',
                // exits the else if statement and continues the loop.
                stringListIterator.previous();
                stringListIterator.add(newCity);
                return true;
            }
            else if (comparison < 0){
                //Move to the next city.
                // We don't have to do anything here as 'stringListIterator.next()' in 'int comparison = stringListIterator.next().compareTo(newCity);'
                // has already moved on to the next city.
            }
        }

        // If the newItem still hasn't found a home in one of the if statements, then add it to the end of the list.
        stringListIterator.add(newCity);

        // Because we've been through the other tests.
        return true;
    }

    // Allows you to specify whether you want to go forward or back when you're actually searching.
    private static void visit (LinkedList cities){

        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        /*
        ---- Result when you set to previous. In Java, you need to set the previous twice in order
        to actually get the desired effect. https://docs.oracle.com/javase/7/docs/api/java/util/ListIterator.html
        * Now visiting Darwin
            2
          Now visiting Darwin
            2
        * */
        boolean goingForward = true;

        ListIterator<String> listIterator = cities.listIterator();

        // getFirst() gets the first entry in our list.
        // If the first entry is nothing.
       // if (cities.getFirst() == ""){
            // OR a more efficient way.
        if (cities.isEmpty()) {
            System.out.println("No cities in the itinerary");
            return;
        }
        else {
            // next() physically moves the point to the next element.
            System.out.println("Now visiting " + listIterator.next());
            printMenu();
        }

        while (!quit){
            int action = scanner.nextInt();
            // Clears the next line
            scanner.nextLine();

            switch (action){
                case 0:
                    System.out.println("Holiday over");
                    break;
                    // Goes to next item
                case 1:

                    // Extra check when moving from one direction to another. Accounting for the inbetween nature of an iterator
                    // when moving from one direction to another.

                    // Prior to the menu being selected, if we aren't going forward, then we need to point in the direction that we're
                    // going forward.
                    // Checking that we can actually go to the next entry.
                    if (!goingForward){
                        if (listIterator.hasNext()){
                            listIterator.next();
                        }
                        goingForward = true;
                    }

                    // Checks if the next element is in the list.
                    if (listIterator.hasNext()){
                        System.out.println("Now visiting " + listIterator.next());
                    }
                    // If the next element doesn't exist.
                    else {
                        System.out.println("Reached the end of the list");
                        // We can't go forward because we are at the end of the list.
                        goingForward = false;
                    }
                    break;
                // Goes to previous item
                case 2:

                    // Extra check when moving from one direction to another. Accounting for the inbetween nature of an iterator
                    // when moving from one direction to another.

                    // If we were going forward but the user has selected previous, we check in ' if (listIterator.hasPrevious())' if
                    // we can go previous. If we can go previous, then we go to the previous entry to point it to the right direction,
                    // then we set goingForward to false to indicate that we are going in backwards direction.
                    if (goingForward){
                        if (listIterator.hasPrevious()){
                            listIterator.previous();
                        }
                        goingForward = false;
                    }
                    // If the previous element exists.
                    if (listIterator.hasPrevious()){
                        System.out.println("Now visiting " + listIterator.previous());
                    }
                    else {
                        System.out.println("We're at the start of the list");
                        // The only direction at the start of the list is forward.
                        goingForward = true;
                    }
                    break;
                case 3:
                    printMenu();
                    break;
            }
        }
    }

    private static void printMenu(){
        System.out.println("Available action: \npress");
        System.out.println(" 0 - to quit \n" +
                "1 - go to the next city \n" +
                "2 - go to the previous city \n" +
                "3 - print menu options \n" );
    }
}


















