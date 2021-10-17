package com.mdtanvirhossain.jtp.ch7.locksandlatches;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosopherDemo7 {
    public static void main(String[] args) {
        Lock[] forks = new Lock[5];

        for (int i = 0; i < forks.length; i++) {
            forks[i] = new ReentrantLock();
        }

        Philosopher7[] philosophers7 = new Philosopher7[5];
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < philosophers7.length; i++) {
            Lock leftFork = forks[i];
            Lock rightFork = forks[(i + 1) % forks.length];

            philosophers7[i] = new Philosopher7("Philosopher " + (i + 1), leftFork, rightFork);
            executorService.execute(philosophers7[i]);
        }
        executorService.shutdown();
    }
}
