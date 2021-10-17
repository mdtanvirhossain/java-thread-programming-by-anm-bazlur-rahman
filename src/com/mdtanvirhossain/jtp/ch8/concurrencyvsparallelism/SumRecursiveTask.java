package com.mdtanvirhossain.jtp.ch8.concurrencyvsparallelism;

import java.util.concurrent.RecursiveTask;

public class SumRecursiveTask extends RecursiveTask {

    private static final int SEQUENTIAL_THRESHOLD = 5000;

    private int[] array;
    private int low;
    private int high;

    public SumRecursiveTask(int[] array, int low, int high) {
        this.array = array;
        this.low = low;
        this.high = high;
    }

    @Override
    protected Long compute() {
        if (high - low > SEQUENTIAL_THRESHOLD) {
            int mid = low + (high - low) / 2;
            SumRecursiveTask left = new SumRecursiveTask(array, low, mid);
            SumRecursiveTask right = new SumRecursiveTask(array, mid + 1, high);
            left.fork();

            Long rightAnswer = right.compute();
            Long leftAnswer = left.compute();

            return leftAnswer + rightAnswer;
        } else {
            return computeDirectly();
        }
    }

    private Long computeDirectly() {
        long sum = 0;
        for (int i = low; i < high; i++) {
            sum += array[i];
        }

        return sum;
    }
}
