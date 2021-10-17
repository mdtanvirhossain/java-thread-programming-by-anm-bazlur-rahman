package com.mdtanvirhossain.jtp.ch3.threadsafetyanddatasynch;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class DeadLockExample {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public void running() {
        synchronized (lock1) {
            log("Acquired lock1");
            synchronized (lock2) {
                log("Acquired lock2");
                System.out.println("Running!");
                log("About to release lock2");
            }
            log("About to release lock1");
        }
    }

    public void walking() {
        synchronized (lock2) {
            log("Acquired lock2");
            synchronized (lock1) {
                log("Acquired lock1");
                System.out.println("Running!");
                log("About to release lock1");
            }
            log("About to release lock2");
        }
    }

    private void log(String logMsg) {
        String time = DateTimeFormatter.ISO_LOCAL_TIME.format(LocalDateTime.now());
        String threadName = Thread.currentThread().getName();

        System.out.printf("%12s %s : %s%n", time, threadName, logMsg);
    }

    // main method
    public static void main(String[] args) {
        DeadLockExample deadLockExample = new DeadLockExample();

        Thread runningThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                deadLockExample.running();
            }

            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new AssertionError(e);
            }
        });

        runningThread.setName("Running Thread");

        Thread walkingThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                deadLockExample.walking();
            }

            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new AssertionError(e);
            }
        });

        walkingThread.setName("Walking Thread");

        runningThread.start();
        walkingThread.start();

    }
}
