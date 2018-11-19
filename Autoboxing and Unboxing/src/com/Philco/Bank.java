package com.Philco;

import java.util.ArrayList;

/**
 * Created by PhillipsDaramola on 10/09/2017.
 */
public class Bank {
    private String name;
    private ArrayList<Branch> branches;

    public Bank(String name) {
        this.name = name;
        // branches is now initialised.
        this.branches = new ArrayList<Branch>();
    }

    public Boolean addBranch(String branchName){
        if(findBranch(branchName) == null){
            // Adds new branch object (by calling the constructor) to the arraylist if it doesn't exist.
            this.branches.add(new Branch(branchName));
            return true;
        }
        return false;
    }

    public boolean addCustomer(String branchName, String customerName, double initialAmount){

        Branch branch = findBranch(branchName);
        // We're only going to be adding the branch name if the customer exists.
        if (branch != null){
            // Because newCustomer method returns true, we've saved ourselves a line of code.
            return branch.newCustomer(customerName,initialAmount);
        }
        return false;
    }

    // Operates similarly to the addCustomer method. The above method adds customers, this one adds transactions.
    public boolean addCustomerTransaction(String branchName, String customerName, double amount) {

        Branch branch = findBranch(branchName);
        if (branch != null){
            // Because newCustomer method returns true, we've saved ourselves a line of code.
            return branch.addCustomerTransaction(customerName, amount);
        }
        return false;
    }

    private Branch findBranch(String branchName){

        for (int i = 0; i < this.branches.size(); i++){
            // Get each Branch object in the arraylist
            Branch checkedBranch = this.branches.get(i);
            // If Branch exists (we're checking each object to see if the inputted name (branchName) exists.)
            if (checkedBranch.getName().equals(branchName)){
                return checkedBranch;
            }
        }
        // Customer does not exist.
        return null;
    }

    // This lists out all the customers for each branch and all the transactions for each customer.
    // showTransactions will be set to true if you want to see the list of transactions and false if you only want to see
    // the customer names.
    public boolean listCustomers(String branchName, boolean showTransactions){

        Branch branch = findBranch(branchName);
        // Check if branch exists
        if (branch != null){
            System.out.println("Customer details for branch " + branch.getName());

            // Retrieve customers for each branch.
            ArrayList<Customer> branchCustomers = branch.getCustomers();

            // Getting all the customers for a particular branch
            for (int i = 0; i < branchCustomers.size(); i++){

                // branchCustomer stores each Customer.
                Customer branchCustomer = branchCustomers.get(i);
                System.out.println("Customer: " + branchCustomer.getName() + "[" + (i+1) + "]");

                if (showTransactions){
                    System.out.println("Transactions");
                    ArrayList<Double> transactions = branchCustomer.getTransactions();

                    for (int j = 0; j < transactions.size(); j++){
                        System.out.println("[" + (i+1) + "] Amount " + transactions.get(j));
                    }
                }
            }

            return true;
        }

        else {
            return false;
        }
    }

}















