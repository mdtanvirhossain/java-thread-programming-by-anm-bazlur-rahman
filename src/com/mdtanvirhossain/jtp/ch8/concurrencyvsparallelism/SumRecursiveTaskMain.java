package com.mdtanvirhossain.jtp.ch8.concurrencyvsparallelism;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class SumRecursiveTaskMain {
    public static void main(String[] args) {
        int[] array = new int[10_000];

        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100);
        }

        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        SumRecursiveTask recursiveTask = new SumRecursiveTask(array, 0, array.length);

        Long result = (Long) forkJoinPool.invoke(recursiveTask);
        System.out.println("Result: " + result);
    }
}
