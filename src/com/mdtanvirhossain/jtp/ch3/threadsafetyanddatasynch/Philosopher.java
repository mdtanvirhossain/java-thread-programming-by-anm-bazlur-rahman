package com.mdtanvirhossain.jtp.ch3.threadsafetyanddatasynch;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Philosopher extends Thread {
    private String name;
    private final Fork leftFork;
    private final Fork rightFork;

    public Philosopher(String name, Fork leftFork, Fork rightFork) {
        this.name = name;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    private void think() {
        log("Thinking");
    }

    private void eat() {
        // assume, eating takes sometime
        //let's put a random number

        try {
            log("Eating");
            int eatingTime = getRandomEatingTime();
            TimeUnit.NANOSECONDS.sleep(eatingTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new AssertionError(e);
        }
    }

    @Override
    public void run() {
        while (true) {
            keepThinkingAndEating();
        }
    }

    private void keepThinkingAndEating() {
        think();

        synchronized (leftFork) {
            log("Grabbed left fork");
            synchronized (rightFork) {
                log("Grabbed right fork");
                eat();
                log("Put down right fork");
            }
            log("Put down left fork");
        }
    }

    private int getRandomEatingTime() {
        Random random = new Random();
        return random.nextInt(100) * 50;
    }

    private void log(String logMsg) {
        String time = DateTimeFormatter.ISO_LOCAL_TIME.format(LocalDateTime.now());
        String threadName = Thread.currentThread().getName();

        System.out.printf("%12s %s : %s%n", time, threadName, logMsg);

        System.out.flush();
    }
}
