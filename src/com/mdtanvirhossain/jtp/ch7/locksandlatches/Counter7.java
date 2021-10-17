package com.mdtanvirhossain.jtp.ch7.locksandlatches;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter7 {
    private int count;
    private Lock lock = new ReentrantLock();

    private void increment() {
        lock.lock();
        try {
            count++;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
