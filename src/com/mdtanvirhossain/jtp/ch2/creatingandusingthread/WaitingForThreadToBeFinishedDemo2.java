package com.mdtanvirhossain.jtp.ch2.creatingandusingthread;

import java.util.concurrent.TimeUnit;

public class WaitingForThreadToBeFinishedDemo2 {
    private static boolean doneWorking = false;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            findTheTop20FibonacciNumber();
            // doneWorking = true;
        });
        t1.start();

        // let's put the main thread in sleep
        /*try {
            TimeUnit.SECONDS.sleep(2);

            if (doneWorking) {
                System.out.println("\nThread t1 has finished the work!");
            } else {
                System.out.println("\nThread t1 is running!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        while (t1.isAlive()) {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("\nThread t1 is running");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
