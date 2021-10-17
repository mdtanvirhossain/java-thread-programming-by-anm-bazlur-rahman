package com.mdtanvirhossain.jtp.ch3.threadsafetyanddatasynch;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BufferExampleExplicit {

    private final static int SIZE = 10;
    private Queue<Integer> queue = new LinkedList<>();
    private final Object lock = new Object();

    public void addItem(int item) {
        synchronized (lock) {
            while (queue.size() == SIZE) {
                log("Size is full, let's wait");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new AssertionError(e);
                }
            }
            log("Thread resumed");
            log("Adding item " + item);

            queue.add(item);

            log("Item added, let's notify");
            lock.notifyAll();
        }
    }

    public Integer getItem() {
        synchronized (lock) {
            while (queue.isEmpty()) {
                log("Queue is empty, let's wait");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new AssertionError(e);
                }
            }

            log("Thread resumed");
            log("Let's consume item");
            Integer value = queue.poll();

            log("Consumed " + value);
            lock.notifyAll();

            return value;
        }
    }

    private void log(String logMsg) {
        String time = DateTimeFormatter.ISO_LOCAL_TIME.format(LocalDateTime.now());
        String threadName = Thread.currentThread().getName();

        System.out.printf("%12s %s : %s%n", time, threadName, logMsg);
    }

    public Integer getARandomItem() {
        Random random = new Random();
        Integer value = random.nextInt(100) + 50;
        return value;
    }

    public static void main(String[] args) {
        BufferExampleExplicit bee = new BufferExampleExplicit();

        Thread thread1 = new Thread(() -> {
            while (true) {
                bee.addItem(bee.getARandomItem());
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
                bee.getItem();
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
