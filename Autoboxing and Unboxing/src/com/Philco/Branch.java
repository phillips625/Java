package com.Philco;

import java.util.ArrayList;

/**
 * Created by PhillipsDaramola on 10/09/2017.
 */
public class Branch {

    private String name;
    // List of Customers objects for each branch
    private ArrayList<Customer> customers;

    public Branch(String name) {
        this.name = name;
        //Getting the arraylist ready for use.
        this.customers = new ArrayList<Customer>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    // We're using a boolean to say when we pass a customer name that already exists, it should return a false.
    public boolean newCustomer(String customerName, double initialAmount){

        // This means the customers is already on file.
        if (findCustomer(customerName) == null){
            // We're calling the constructor code in the customer class
            this.customers.add(new Customer(customerName, initialAmount));
            return true;
        }

        // If the customer already exists, return false.
        return false;
    }

    public boolean addCustomerTransaction(String customerName, double amount){

        Customer existingCustomer = findCustomer(customerName);
        // This mean something has been returned and is on file. We want to grab the existing record and update that amount.
        if(existingCustomer != null){
            // addTransaction is a method in the Customer class
            existingCustomer.addTransaction(amount);
            return true;
        }

        //Customer could not be found
        return false;
    }

    private Customer findCustomer(String customerName){

        for (int i = 0; i < this.customers.size(); i++){
            // Get each customer object in the arraylist
            Customer checkedCustomer = this.customers.get(i);
            // If customer exists (we're checking each object to see if the inputted name (customerName) exists.)
            if (checkedCustomer.getName().equals(customerName)){
                return checkedCustomer;
            }
        }
        // Customer does not exist.
        return null;
    }
}




















