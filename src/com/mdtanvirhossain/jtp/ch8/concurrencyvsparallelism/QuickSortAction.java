package com.mdtanvirhossain.jtp.ch8.concurrencyvsparallelism;

import java.util.List;
import java.util.concurrent.RecursiveAction;

public class QuickSortAction<T extends Comparable<T>> extends RecursiveAction {

    private List<T> data;
    private int left;
    private int right;

    public QuickSortAction(List<T> data) {
        this.data = data;
        this.left = 0;
        this.right = this.data.size() - 1;
    }

    public QuickSortAction(List<T> data, int left, int right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    @Override
    protected void compute() {
        // quick sort algorithm
        if (left >= right) {
            return;
        }
        int pivotIndex = left + ((right - left) / 2);
        pivotIndex = partition(pivotIndex);

        QuickSortAction<T> leftTask = new QuickSortAction<>(data, left, pivotIndex - 1);
        QuickSortAction<T> rightTask = new QuickSortAction<>(data, pivotIndex + 1, right);

        leftTask.fork();
        rightTask.compute();
        leftTask.join();

    }

    private int partition(int pivotIndex) {
        T pivotValue = data.get(pivotIndex);
        swap(pivotIndex, right);
        int storeIndex = left;

        for (int i = left; i < right; i++) {
            if (data.get(i).compareTo(pivotValue) < 0) {
                swap(i, storeIndex);
                storeIndex++;
            }
        }

        swap(storeIndex, right);

        return storeIndex;
    }

    private void swap(int pivotIndex, int right) {
        if (pivotIndex != right) {
            T pivotValue = data.get(pivotIndex);
            data.set(pivotIndex, data.get(right));
            data.set(right, pivotValue);
        }
    }
}
