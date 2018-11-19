package com.philco;

import java.util.*;

/**
 * Created by PhillipsDaramola on 15/10/2017.
 */

                            // COMPARABLE AND COMPARATOR


// final - each object has there own final variable and doesn't overlap with other object's.

public class Theatre {

    private final String theatreName;
    // private List<Seat> seats = new ArrayList<>();
                    // OR
    //private List<Seat> seats = new LinkedList<>();
                    // OR
    // We can be even more generic by using Collections.
    // This means we can use any of the Collection classes to hold our seats.
    // In the 'Collections Hierarchy', set, list, queue and dequeue implements from the Collections
    // interface.
    // The HashSet distorted how the seats were listed.
    // LinkedHashSet returned the seat list in order.
    //private Collection<Seat> seats = new HashSet<>();

    private List<Seat> seats = new ArrayList<>();

    /*
                    // USING 'COMPARATOR' HERE
    // This is used by the sort method (in the main class) to sort the seats based on the price.
    // This is an example of an ANONYMOUS INNER class providing an implementation of the compare method!
    static final Comparator<Seat> PRICE_ORDER = new Comparator<Seat>() {
        @Override
        public int compare(Seat seat1, Seat seat2) {
            // We want to sort by the price of the tickets.
            if (seat1.getPrice() < seat2.getPrice()){
                return -1;
            }
            else if (seat1.getPrice() > seat2.getPrice()){
                return 1;
            }
            else {
                return 0;
            }
        }
    };


*/
                        // OR

    // USING 'COMPARATOR' HERE
    // This is used by the sort method (in the main class) to sort the seats based on the price.
    // This is an example of an ANONYMOUS INNER class providing an implementation of the compare method!
    static final Comparator<Seat> PRICE_ORDER;

    // This Static initialization block was created by:
    // - Click the Comparator (before the above 'OR')
    // - Click the light bulb
    // - Click 'Split into declaration and initialization'.
    static {
        PRICE_ORDER = new Comparator<Seat>() {
            @Override
            // We can't leave the 'compare' method as is, as there are more than 1 seat that would return an equal price
            // (so we have to break it down a little further).
            public int compare(Seat seat1, Seat seat2) {
                // We want to sort by the price of the tickets.
                if (seat1.getPrice() < seat2.getPrice()) {
                    return -1;
                } else if (seat1.getPrice() > seat2.getPrice()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };
    }
    // This gave an error as we're trying to cast 'TreeSet' (which is 3 levels below the Collection class) unto the Collection class.
    // We can ONLY cast Data Structures to other Data Structures on the same level. Also Data Structures on a higher level (e.g Collection class)
    // can be cast to another data structure THAT IS ONE LEVEL BELOW IT! NOT 2, 3, 4 OR MORE LEVELS IN THIS CASE ('Collection'->SET->SORTEDSET->'TreeSet').
    // private Collection<Seat> seats = new TreeSet<>();

    public Theatre(String theatreName, int numRows, int seatsPerRow) {

        this.theatreName = theatreName;

        // e.g numRows is 8. lastRow = 'A' + (numRows - 1) means A + 7 = H. A starts at 0.
        int lastRow = 'A' + (numRows - 1);

        /*
        char is auto promoted to int. char is represented as integer between 0 and 65535 and
        can be used with integer arithmetic, char is single char it can be any character from
        special characters to numbers, and letters.
         */
        // looping through the row from 'A' to 'Z'
        // e.g lastRow could be the letter 'H'.
        for (char row = 'A'; row <= lastRow; row++){

            // For each row, we have to allocate the seats for that section.
            for (int seatNum = 1; seatNum <= seatsPerRow; seatNum++){

                // This is the base price.
                double price = 12.00;

                // Premium price paid by people in the middle of front 3 rows.
                if ((row < 'D') && (seatNum >= 4 && seatNum <= 9)){
                    price = 14.00;
                }

                // Less price for people in seats greater than F AND people who are sat at the edge.
                else if ((row > 'F') || (seatNum < 4 || seatNum > 9)){
                    price = 7.00;
                }

                // Creating a Seat object for every seat.
                // String.format("%02d", seatNum) reformats seatNum.
                // %02d means two digits e.g 01, 02, .. 77, 78,..
                Seat seat = new Seat(row + String.format("%02d", seatNum), price);
                seats.add(seat);
            }
        }
    }

    public String getTheatreName() {
        return theatreName;
    }

    // Trying to make this method more efficient.
    public boolean reserveSeat(String seatNumber){

                            // MORE EFFICIENT

        // Seat requestedSeat = null;

        // This will be giving us the new seat for comparison purposes.
        Seat requestedSeat = new Seat(seatNumber, 0);
        // 'seats' is the list of seats. 'requestSeats' is the seat we want to search 'seats' against.
        // 'null' because we're going to be the passing the inbuilt comparison operator (WE CREATED THIS
        // 'compareTo' (compareToIgnoreCase in the compareTo method) USED BY THE 'binarySearch' METHOD IN THE SEATS CLASS).
        // 'binarySearch' uses the 'compareTo' function in the 'Seat' class.
        // binarySearch (think about a tree with 2 branches) is the fastest way to find an item in a sorted list. binarySearch start
        // there search from the middle of the list and traverses the branches until it find the item it wants. E.g 2^10 = 1024 items
        // will only take 10 levels to find an item (hence more efficient).
        // "binarySearch" relies on the list we're searching being sorted.
        int foundSeat = Collections.binarySearch(seats, requestedSeat, null);

        if (foundSeat >= 0){
            return seats.get(foundSeat).reserve();
        }
        else {
            System.out.println("There is no seat " + seatNumber);
            return false;
        }

                            // OR
        // You can copy and adapt the Binary Search source code written by Java here.

                            // LESS EFFICIENT

//        for (Seat seat : seats){
//            // Print a dot to check how many seats it had to check in order to get the relevant seat.
//            // A lot of dots where printed in order to find H11.
//            // You'll find that using an ArrayList is not efficient for traversing the list.
//            System.out.println(".");
//            if (seat.getSeatNumber().equals(seatNumber)){
//                requestedSeat = seat;
//                // breaks out of the nearest LOOP (THE FOR LOOP IN THIS CASE).
//                break;
//            }
//        }
//
//        if (requestedSeat == null){
//            System.out.println("There is no seat " + seatNumber);
//            return false;
//        }
//
//        return requestedSeat.reserve();
    }

                // For testing
    /*
    public void getSeats(){

        for (Seat seat : seats){
            System.out.println(seat.getSeatNumber());
        }
    }
    */
    public Collection<Seat> getSeats(){

        return seats;
    }


    // Seat class shouldn't be public - only doing this for the purpose of this example.
    // Inner class - Not worth creating a separate class for it (i.e tied to this current package).
    // private - only the Theatre class can have access to it.

    // Comparable in Comparable<Seat> is an interface used to compare Seats. Need to override the compareTo
    // method for the Comparable interface to work.
    //private class Seat implements Comparable<Seat>{
    public class Seat implements Comparable<Seat>{

        private final String seatNumber;
        private double price;
        private boolean reserved = false;

        public Seat(String seatNumber, double price) {
            this.seatNumber = seatNumber;
            this.price = price;
        }

        // Needs to be implemented for Comparable class to be valid.
        // This was NEED TO IN ORDER TO USE OUR BINARY SEARCH data structure (which is obviously more efficient than an array list).
        @Override
        public int compareTo(Seat seat) {
            // Works like you standard compareTo method - -1 when less (getSeatNumber is less than seatNumber), 0 when equal, +1 when greater
            return this.seatNumber.compareToIgnoreCase(seat.getSeatNumber());
        }

        public boolean reserve (){
// The first time this method is called by an object, reserved is set to true. If the
// method is called again by that object, since reserved is already set to true, this if statment
            // will not be called again (as now you have if (false)). The else statement is executed instead.
            if (!this.reserved){
                // No need to try to reprocess it, because the seat is reserved.
                this.reserved = true;
                System.out.println("Seat " + seatNumber + " reserved.");
                return true;
            }
            else {
                return false;
            }
        }

        public boolean cancel(){
            // If the seat has been reserved (aka the 'reserve' method has been called), 'reserved'
            // will have been set to true - consequently, this if statement can be executed!
            // Notice that reserved is set to false, meaning that this seat can be booked again in the
            // 'reserve' method (and of course this if statement can't be executed because reserved is
            // now false).
            if (this.reserved){
                this.reserved = false;
                System.out.println("Reservation of seat " + seatNumber + " cancelled");
                return true;
            }
            else {
                return false;
            }
        }

        public String getSeatNumber() {
            return seatNumber;
        }

        public double getPrice() {
            return price;
        }
    }

}






















