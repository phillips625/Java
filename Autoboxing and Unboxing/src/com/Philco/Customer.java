package com.Philco;

import java.util.ArrayList;

/**
 * This is the first class that we're going to create - we're working from the
 * buttom up (because all the other classes will need to access this one.)
 */
public class Customer {

    private String name;
    // List of Double objects
    private ArrayList<Double> transactions;

    // initialAmount stores the initial amount you've saved.
    public Customer(String name, double initialAmount) {
        this.name = name;

        // Initializing the transactions
        this.transactions = new ArrayList<Double>();
        // Now you can add the amount (the customer is putting in their bank account) to the arraylist.
        addTransaction(initialAmount);
    }

    // Allows customers to add money to their account
    public void addTransaction(double amount){

        // 'amount' is converted from a primitive to the object wrapper (which we've got in our arraylist) - DEMONSTRATING AUTOBOXING
        this.transactions.add(amount);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Double> getTransactions() {
        return transactions;
    }
}
