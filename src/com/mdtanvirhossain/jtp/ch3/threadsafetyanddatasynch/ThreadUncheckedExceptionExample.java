package com.mdtanvirhossain.jtp.ch3.threadsafetyanddatasynch;

import java.util.concurrent.TimeUnit;

public class ThreadUncheckedExceptionExample {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                System.err.println("Thread interrupted duo to " + e.getMessage());
            }

            throw new RuntimeException("Goodbye cruel world!");
        });

        thread.setUncaughtExceptionHandler(new CustomUncaughtExceptionHandler());
        thread.start();
    }
}
