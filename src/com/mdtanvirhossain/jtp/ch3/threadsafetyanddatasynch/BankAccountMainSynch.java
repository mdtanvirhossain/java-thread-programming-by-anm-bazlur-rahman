package com.mdtanvirhossain.jtp.ch3.threadsafetyanddatasynch;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BankAccountMainSynch {
    public static void main(String[] args) {
        BankAccountSynch bankAccountSynch = new BankAccountSynch(100);

        DateTimeFormatter isoTimeFormatter = DateTimeFormatter.ISO_TIME;

        Thread withdrawThread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() +
                    " started at " + isoTimeFormatter.format(LocalDateTime.now()));
            bankAccountSynch.withdraw(100);
        });

        withdrawThread.setName("Withdraw thread");

        Thread depositThread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() +
                    " started at " + isoTimeFormatter.format(LocalDateTime.now()));
            bankAccountSynch.deposit(100);

        });

        depositThread.setName("Deposit thread");

        withdrawThread.start();
        depositThread.start();

        try {
            depositThread.join();
            withdrawThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new AssertionError(e);
        }

        System.out.println("Current balance available in account: " + bankAccountSynch.getBalance());
    }

    private static void sleep() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new AssertionError(e);
        }
    }
}
