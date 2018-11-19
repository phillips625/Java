package com.Philco;

import java.util.ArrayList;

/*Holds in memory the current element and points to the memory address of the elements around it.*/

/*Discussing the advantages of linkedlists over arrays.*/
public class Main {

    public static void main(String[] args) {

        Customer customer = new Customer("Tim", 34.56);
        Customer anotherCustomer;

        /* 'anotherCustomer' points to the 'customer' class. Java didn't create a separate class but rather just saved the memory address.*/
        anotherCustomer = customer;
        /*This updates the customer class because anotherCustomer points to customer.*/
        anotherCustomer.setBalance(23.34);
        System.out.println("Balance for customer " + customer.getName() + " is " + customer.getBalance());

        ArrayList<Integer> intList = new ArrayList<Integer>();

        intList.add(1);
        intList.add(3);
        intList.add(4);

        for (int i = 0; i<intList.size(); i++){
            System.out.println(i + ": " + intList.get(i));
        }

        /*Inserting number 2 in index position 1 (now 4 elements are printed instead of 3.)*/
        /*Because this is not a linked list, adding an item in the middle of the list essentially moves
        * the other items further down. With linked list, it is much easier - all you have to do is point
        * the address of element 1 to the address of element 2 and then the address of element 2 points to
        * the address of element 3 (that way, we don't have to physically moves down any elements in memory).*/
        intList.add(1,2);
        for (int i = 0; i<intList.size(); i++){
            System.out.println(i + ": " + intList.get(i));
        }
    }
}
