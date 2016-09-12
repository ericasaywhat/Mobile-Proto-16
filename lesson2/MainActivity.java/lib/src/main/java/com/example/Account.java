package com.example;

/**
 * Created by erica on 9/8/16.
 */
//public class Account {
//
//    private long amount;
//
//    public static long getAmount(long amount) {
//        return amount;
//    }
//
//    public static long setAmount(long new_amount) {
//        long amount = new_amount;
//        return amount;
//    }

public abstract class Account {

    private long amount;

    public Account(long money) {
        amount = money;
    }

    public String toString() {
        return "Account Balance: $" + amount;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long new_amount) {
        amount = new_amount;

    }

    public void deposit(long depositAmount) {
        amount+= depositAmount;


    }

    public static Account largerAccount(Account acc1, Account acc2) {
        if (acc1.getAmount() > acc2.getAmount()) {
            return acc1;

        } else {
            return acc2;
        }

    }

    public static void main(String[] args) {
        Account a = new CheckingAccount(100);
        System.out.println(a);
        a.setAmount(20);
        a.deposit(10);
        System.out.println("New amount: " + a.getAmount());

        Account small = new CheckingAccount(20);
        Account big = new CheckingAccount(30);
        System.out.println(Account.largerAccount(small, big));
    }

}