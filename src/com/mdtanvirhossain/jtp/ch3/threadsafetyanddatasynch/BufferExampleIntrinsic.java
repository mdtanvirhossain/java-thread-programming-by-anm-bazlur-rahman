package com.mdtanvirhossain.jtp.ch3.threadsafetyanddatasynch;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BufferExampleIntrinsic {

    private final static int SIZE = 10;
    private Queue<Integer> queue = new LinkedList<>();

    public synchronized void addItem(int item) {
        while (queue.size() == SIZE) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new AssertionError(e);
            }
        }
        queue.add(item);
        System.out.println("Item " + item + " added");
        notifyAll();
    }

    public synchronized Integer getItem() {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new AssertionError(e);
            }
        }
        Integer value = queue.poll();
        System.out.println("Item " + value + " used");
        notifyAll();

        return value;
    }

    public Integer getARandomItem() {
        Random random = new Random();
        Integer value = random.nextInt(100) + 50;
        return value;
    }

    public static void main(String[] args) {
        BufferExampleIntrinsic bei = new BufferExampleIntrinsic();

        Thread thread1 = new Thread(() -> {
            while (true) {
                bei.addItem(bei.getARandomItem());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new AssertionError(e);
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            while (true) {
                bei.getItem();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new AssertionError(e);
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
