package com.philco;

import java.util.Date;

public final class Transaction {

    private double amount;
    private Date transactionDate;

    public Transaction(double amount) {
        if (amount > 0.0)
            this.amount = amount;

        this.transactionDate = DateProvider.getInstance().now();
    }

    public double getAmount() {
        return amount;
    }
}
