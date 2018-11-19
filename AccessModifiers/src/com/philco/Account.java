package com.philco;

import java.util.ArrayList;

/**
 * Created by PhillipsDaramola on 13/10/2017.
 */
public class Account {

    private String accountName;
    private int balance = 0;

    // In this array list, we'll store deposits as a positive number and withdrawals as a negative number.
    private ArrayList<Integer> transactions;

    public Account(String accountName) {
        this.accountName = accountName;
        this.transactions = new ArrayList<>();
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount){
        if (amount > 0){
            transactions.add(amount);
            this.balance += amount;
            System.out.println(amount + " is deposited. Your balance is now " + this.balance);
        }
        else {
            System.out.println("Cannot deposit a negative sum");
        }
    }

    public void withdraw(int amount){

        int withdrawal = -amount;

        if (withdrawal < 0){
            this.transactions.add(withdrawal);
            this.balance += withdrawal;
            System.out.println(amount + " withdrawn. Your balance is now " + this.balance);
        }
        else {
            System.out.println("Cannot withdraw negative sums");
        }
    }

    public void calculateBalance(){
        this.balance = 0;
        for (int i : this.transactions){
            this.balance += i;
        }
        System.out.println("Calculated balance is " + this.balance);
    }
}





















