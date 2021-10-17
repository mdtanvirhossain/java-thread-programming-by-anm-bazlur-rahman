package com.mdtanvirhossain.jtp.ch7.locksandlatches;

import java.util.concurrent.CountDownLatch;

public class Tester implements Runnable {
    private CountDownLatch latch;
    private String name;

    public Tester(String name, CountDownLatch latch) {
        this.latch = latch;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            System.out.printf("Tester %s is waiting for developers to finish their work!%n", name);
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new AssertionError(e);
        }

        System.out.printf("Testing is done by tester: %s !%n", name);
    }
}
