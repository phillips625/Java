package com.Philco;

public class Main {

    public static void main(String[] args) {


        Bank bank = new Bank("NAB");

        if(bank.addBranch("Adelaide")){
            System.out.println("Adelaide branch created!");
        }

        bank.addBranch("Adelaide");
        bank.addCustomer("Adelaide", "Jay", 500000.05);
        bank.addCustomer("Adelaide", "Mike", 1500000.05);
        bank.addCustomer("Adelaide", "Jess", 5500450.05);

        bank.addBranch("Sydney");
        bank.addCustomer("Sydney", "Tim", 3340060.05);

        bank.addCustomerTransaction("Adelaide", "Jay", 53366700.05);
        bank.addCustomerTransaction("Adelaide", "Jay", 2535500.05);
        bank.addCustomerTransaction("Adelaide", "Mike", 85757320.05);

        bank.listCustomers("Adelaide", true);
        bank.listCustomers("Sydney", true);

        // This addition of this line should prevent the error message from showing.
        bank.addBranch("Melbourne");
        if (!bank.addCustomer("Melbourne", "James", 5323.32)){
            System.out.println("Error, Melbourne branch does not exist.");
        }

        // Trying to add a branch that already exists.
        if(!bank.addBranch("Adelaide")){
            System.out.println("Adelaide branch already exists.");
        }

        if (!bank.addCustomerTransaction("Adelaide", "Fergus", 322453.45)){
            // This should be printed as "Fergus does not exist."
            System.out.println("Customer does not exist at branch!");
        }

        // Checking if the system knows that Jay already exists.
        if (!bank.addCustomer("Adelaide", "Jay", 5343553)){
            System.out.println("Customer Jay already exists.");
        }
    }
}



















