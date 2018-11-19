package com.philco;

public class Main {

    public static void main(String[] args) {

        Account jamesAccount = new Account("James");
        jamesAccount.deposit(1000);
        jamesAccount.withdraw(500);
        jamesAccount.withdraw(-200);
        jamesAccount.deposit(-20);
        // This is not too good. We can add money to our account because we have declared have declared the transactions
        // field in the 'Account' class public. Fixed by making the 'transactions' field private.
        // jamesAccount.transactions.add(4000);
        jamesAccount.calculateBalance();

        System.out.println("Balance on account is " + jamesAccount.getBalance());
    }
}
