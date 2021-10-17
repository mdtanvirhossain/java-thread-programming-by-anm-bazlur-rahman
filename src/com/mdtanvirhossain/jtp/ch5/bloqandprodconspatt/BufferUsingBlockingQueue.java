package com.mdtanvirhossain.jtp.ch5.bloqandprodconspatt;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class BufferUsingBlockingQueue {

    private BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>(10);

    public void addItem(Integer item) {
        try {
            blockingQueue.put(item);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new AssertionError(e);
        }
    }

    public Integer getItem() {
        try {
            return blockingQueue.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new AssertionError(e);
        }
    }

    private static void sleepOneSecond() {
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new AssertionError(e);
        }
    }

    public static void main(String[] args) {
        BufferUsingBlockingQueue bufferUsingBlockingQueue = new BufferUsingBlockingQueue();
        Random random = new Random();

        Thread producer = new Thread(() -> {
            while (true) {
                int anInt = random.nextInt();
                System.out.println("Producer: " + anInt);
                bufferUsingBlockingQueue.addItem(anInt);
                sleepOneSecond();
            }
        });

        Thread consumer = new Thread(() -> {
            while (true) {
                Integer item = bufferUsingBlockingQueue.getItem();
                System.out.println("Consumed: " + item);
                sleepOneSecond();
            }
        });

        producer.start();
        consumer.start();
    }

}
