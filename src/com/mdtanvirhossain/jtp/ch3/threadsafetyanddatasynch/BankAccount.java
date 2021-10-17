package com.mdtanvirhossain.jtp.ch3.threadsafetyanddatasynch;

public class BankAccount {

    private long balance;

    public BankAccount(long balance) {
        this.balance = balance;
    }

    public synchronized void withdraw(long amount) {
        System.out.println("Withdrawing: " + amount);
        long newBalance = balance - amount;
        System.out.println("New balance is: " + newBalance);
        balance = newBalance;
    }

    public synchronized void deposit(long amount) {
        System.out.println("Depositing: " + amount);
        long newBalance = balance + amount;
        System.out.println("New balance is: " + newBalance);
        balance = newBalance;
    }

    public long getBalance() {
        return balance;
    }
}
