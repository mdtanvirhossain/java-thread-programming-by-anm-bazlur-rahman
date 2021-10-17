package com.mdtanvirhossain.jtp.ch8.concurrencyvsparallelism;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class QuickSortActionMain {
    public static void main(String[] args) {
        List<Integer> list = initializeSomeRandomNumbers();

        QuickSortAction<Integer> quickSortAction = new QuickSortAction<>(list);

        ForkJoinPool pool = ForkJoinPool.commonPool();
        pool.invoke(quickSortAction);

        System.out.println(list);
    }

    private static List<Integer> initializeSomeRandomNumbers() {
        final int SIZE = 10_000;
        List<Integer> list = new ArrayList<>(SIZE);

        for (int i = 0; i < SIZE; i++) {
            int value = (int) (Math.random() * 1000);
            list.add(value);

        }

        return list;
    }
}
