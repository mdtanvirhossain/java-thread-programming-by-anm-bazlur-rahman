package com.mdtanvirhossain.jtp.ch7.locksandlatches;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Manager {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CountDownLatch latch = new CountDownLatch(2);

        Developer developer1 = new Developer("Developer# 1", latch);
        Developer developer2 = new Developer("developer# 2", latch);

        Tester tester = new Tester("Tester# 1", latch);

        executorService.execute(developer1);
        executorService.execute(developer2);
        executorService.execute(tester);

        executorService.shutdown();
    }
}
