package com.example;

/**
 * Created by erica on 9/11/16.
 */
public class CheckingAccount extends Account {

    public CheckingAccount(long amount) {
        super(amount);

    }

    public String toString() {
        return "Checking Account Balance: $" + getAmount();
    }

    public void withdraw(long withdrawAmount) {
        setAmount(getAmount()-withdrawAmount);
    }
}
