package com.mdtanvirhossain.jtp.ch2.creatingandusingthread;

import java.util.concurrent.TimeUnit;

public class WaitingForThreadToBeFinishedDemo3 {
    public static void main(String[] args) {

        Thread fibonacciThread = new Thread(() -> {
            findTheTop20FibonacciNumber();
        });
        fibonacciThread.start();

        try {
            fibonacciThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nThread t1 has finished!");
    }

    private static void findTheTop20FibonacciNumber() {
        for (int value = 1; value <= 20; value++) {
            System.out.print(fib(value) + ", ");
        }
    }

    private static int fib(int value) {
        if (value == 1 || value == 2) {
            return 1;
        }
        return fib(value - 1) + fib(value - 2);
    }
}

