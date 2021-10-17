package com.mdtanvirhossain.jtp.ch3.threadsafetyanddatasynch;

import java.util.concurrent.TimeUnit;

public class BankAccountSynch {

    private long balance;

    public BankAccountSynch(long balance) {
        this.balance = balance;
    }

    public synchronized void withdraw(long amount) {
        System.out.println("Inside the withdraw method! Lock acquired!");

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new AssertionError(e);
        }

        System.out.println("Withdrawing: " + amount);
        long newBalance = balance - amount;
        System.out.println("New balance is: " + newBalance);
        balance = newBalance;

        System.out.println("End of the withdraw method! Releasing the lock!");
    }

    public synchronized void deposit(long amount) {
        System.out.println("Inside the deposit method! Lock acquired!");

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new AssertionError(e);
        }

        System.out.println("Depositing: " + amount);
        long newBalance = balance + amount;
        System.out.println("New balance is: " + newBalance);
        balance = newBalance;

        System.out.println("End of the deposit method! Releasing the lock!");

    }

    public long getBalance() {
        return balance;
    }
}
