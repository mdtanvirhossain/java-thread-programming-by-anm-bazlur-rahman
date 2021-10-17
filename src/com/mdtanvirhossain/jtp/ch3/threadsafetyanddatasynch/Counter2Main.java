package com.mdtanvirhossain.jtp.ch3.threadsafetyanddatasynch;

public class Counter2Main {
    public static void main(String[] args) {
        Counter2 counter2 = new Counter2();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                counter2.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                counter2.decrement();
            }
        });

        long start = System.nanoTime();

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new AssertionError(e);
        }

        long end = System.nanoTime();

        System.out.println("Duration: " + (end - start));
    }
}
