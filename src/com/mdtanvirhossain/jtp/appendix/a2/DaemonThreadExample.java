package com.mdtanvirhossain.jtp.appendix.a2;

import java.util.concurrent.TimeUnit;

public class DaemonThreadExample {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                System.out.println("I'm daemon thread and I'm running!");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new AssertionError(e);
                }
            }
        });

        t1.setDaemon(true);
        t1.start();

        System.out.println("Main thread! is running!");

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new AssertionError(e);
        }

        System.out.println("Main thread! still running!");
        System.out.println("Main thread is going to stop now!");
    }
}
