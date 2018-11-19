package com.philco;

import java.util.ArrayList;
import java.util.List;

public final class Bank {
    private List<Customer> customers;

    public Bank() {
        this.customers = new ArrayList<>();
    }

    public final void addCustomer(Customer customer) {
        this.customers.add(customer);
    }

    private final String customerSummary() {
        String summary = "Customer Summary";
        for (Customer c : customers)
            summary += "\n - " + c.getName() + " (" + format(c.getNumberOfAccounts(), "account") + ")";
        return summary;
    }

    //Make sure correct plural of word is created based on the number passed in:
    //If number passed in is 1 just return the word otherwise add an 's' at the end
    private final String format(int number, String word) {
        return number + " " + (number == 1 ? word : word + "s");
    }

    private final double totalInterestPaid() {
        double total = 0;
        for(Customer c: this.customers)
            total += c.totalInterestEarned();
        return total;
    }

    private final String getFirstCustomer() {
        try {
            if (this.customers != null)
                return this.customers.get(0).getName();
        } catch (Exception e){
            e.printStackTrace();
            return "Error";
        }

        return null;
    }
}
