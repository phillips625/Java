package com.philco;

                    // COMPARABLE AND COMPARATOR

/*
       3 types of list
    1 List interface
    2 Array list
    3 Linked list

    These are part of the Java 'Collections' framework. The 'Collections' framework also includes sets, maps, trees and queues.
    At the top level of the Collections framework is the Collections class.

 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Theatre theatre = new Theatre("Olympian", 8, 12);

                // THIS IS A SHALLOW COPY - BOTH THE ORIGINAL AND COPY REFERENCES THE SAME OBJECT.
        // seatCopy is the same copy of 'seats' in 'theatre.seats'. They share the same object - so if
        // change one, you effectively change the other.
//        List<Theatre.Seat> seatCopy = new ArrayList<>(theatre.seats);
//        printList(seatCopy);

        // Reserve the second seat - A02
//        seatCopy.get(1).reserve();

        // The fact that we have two separate array list means nothing - they effectively share the same object.
        // seatCopy.get(1).reserve(); reserves a seat in 'seatcopy' yet the else statement
        // is executed here for the 'theatre' object.
        if (theatre.reserveSeat("D12")){
            System.out.println("Please pay for D12");
        }
        else {
            System.out.println("Seat already reserved");
        }

        if (theatre.reserveSeat("B12")){
            System.out.println("Please pay for B12");
        }
        else {
            System.out.println("Seat already reserved");
        }

        // This seat should already be reserved.
        if (theatre.reserveSeat("D12")){
            System.out.println("Please pay for D12");
        }
        else {
            System.out.println("Seat already reserved");
        }

        // theatre.getSeats() is assigned to 'reverseSeats'.
        List<Theatre.Seat> reverseSeats = new ArrayList<>(theatre.getSeats());
        // This reverse function uses the 'compareTo' we've implemented in the 'Theatre' class.
        // Goof for testing if our compareTo function works.
        Collections.reverse(reverseSeats);
        printList(reverseSeats);


        List<Theatre.Seat> priceSeats = new ArrayList<>(theatre.getSeats());
                // theatre.new Seat (... is the way to instantiate an inner class.
        priceSeats.add(theatre.new Seat("B00", 13.00));
        priceSeats.add(theatre.new Seat("A00", 13.00));
        // 'sort' accepts a 'List' and a 'Comparator'
        // When the sort method sorts the seats based on the price, it leaves the seats in the same order if they do not need to be swapped
        // around.
        Collections.sort(priceSeats, theatre.PRICE_ORDER);
        printList(priceSeats);

        // Shuffles the 'seatCopy' in a random order.
//        Collections.shuffle(seatCopy);
//
//        // Reverses the order of any list.
//        // This proves that 'seatCopy' and 'theatre' are separate array lists but they refer to the same object in memory.
//        // Here, order of seatCopy is reversed.
//
//        // Collections.reverse(seatCopy);
//        System.out.println("Printing Seatcopy");
//        printList(seatCopy);
//        System.out.println("Printing theatre seat");
//        // Despite the fact that seatCopy is reversed, the theatre still remains the same.
//        printList(theatre.seats);
//
//
//        // 'min' and 'max' methods uses the compareTo method we've written to figure out the natural order of the array list and it then
//        // picks out the max and min elements.
//        // Shuffling the seats doesn't have any impact on the 'min' and 'max' methods.
//        Theatre.Seat minSeat = Collections.min(seatCopy);
//        Theatre.Seat maxSeat = Collections.max(seatCopy);
//
//        System.out.println("Min seat: " + minSeat);
//        System.out.println("Max seat: " + maxSeat);
//
//        // Sorts the shuffled list.
//        sortList(seatCopy);
//        System.out.println("Printing sorted seatCopy");
//        printList(seatCopy);

                // DEEP COPY - aka the copy of the array list will not refer to the same object as the original
        // This will not work because it is not enough to set the size of the 'newList'. You actually have
        // to fill it up with objects the same size as the original copy (96 objects in this case) - then you can use the
        // copy method. Hardly ever used though.
//        List<Theatre.Seat> newList = new ArrayList<>(theatre.seats.size());
//        Collections.copy(newList, theatre.seats);
    }

    public static void printList(List<Theatre.Seat> list){

        for (Theatre.Seat seat : list){
            System.out.println(" " + seat.getSeatNumber() + " $" + seat.getPrice());
        }

        System.out.println();
        System.out.println("=======================================");
    }


    /*

                // THIS IS BUBBLE SORT!
    // This is useful when speed is not so important, but memory is at a premium - we're writing our own sort algo
    // rather than using an inbuilt one (merge sort). Merge sort requires more memory than a bubble sort.
    // This method is used to swap elements.
    // 'List<? extends Theatre.Seat>' ensures we're only passing a 'list of Seat class (from the Theatre class)'
    public static void sortList(List<? extends Theatre.Seat> list){

        for (int i = 0; i < list.size() - 1; i++){
            for (int j = i + 1; j <list.size(); j++){

                if (list.get(i).compareTo(list.get(j)) > 0){

                    // ' i, j' - we'll be swapping elements in positions i and j in the 'list'.
                    Collections.swap(list, i, j);
                }
            }
        }
    }

    */
}

/*


Theatre theatre = new Theatre("Olympian", 8, 12);
        // theatre.getSeats();

        if (theatre.reserveSeat("H11")){
            System.out.println("Please Pay");
        }
        else {
            System.out.println("Sorry, seat is already taken.");
        }

        // By this time, H11 should be taken.
        if (theatre.reserveSeat("H11")){
            System.out.println("Please Pay");
        }
        else {
            System.out.println("Sorry, seat is already taken.");
        }
 */