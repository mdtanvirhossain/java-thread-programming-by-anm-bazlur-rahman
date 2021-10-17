package com.mdtanvirhossain.jtp.ch3.threadsafetyanddatasynch;

public class Counter3 {
    private final Object lock = new Object();
    private int count;

    public void increment() {
        synchronized (lock) {
            count++;
        }
    }

    public void decrement() {
        synchronized (lock) {
            count--;
        }
    }
}
