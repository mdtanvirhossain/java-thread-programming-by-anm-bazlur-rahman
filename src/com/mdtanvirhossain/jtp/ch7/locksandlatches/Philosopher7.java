package com.mdtanvirhossain.jtp.ch7.locksandlatches;

import com.mdtanvirhossain.jtp.ch3.threadsafetyanddatasynch.Fork;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class Philosopher7 implements Runnable {
    private String name;
    private final Lock leftFork;
    private final Lock rightFork;

    public Philosopher7(String name, Lock leftFork, Lock rightFork) {
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
            TimeUnit.SECONDS.sleep(eatingTime);
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

        if (leftFork.tryLock()) {
            try {
                log("Grabbed left fork");
                if (rightFork.tryLock()) {
                    try {
                        log("Grabbed right fork");
                        eat();
                    } finally {
                        log("Put down right fork");
                        rightFork.unlock();
                    }
                }
            } finally {
                log("Put down left fork");
                leftFork.unlock();
            }
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
