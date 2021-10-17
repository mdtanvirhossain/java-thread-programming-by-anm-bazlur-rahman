package com.mdtanvirhossain.jtp.ch3.threadsafetyanddatasynch;

public class Counter {
    private int count;

    public synchronized void incrementCounter() {
        this.count = count + 1;
    }

    public void incrementCounterBlock() {
        System.out.println("Going to increment counter.");

        // more statement
        synchronized (this) {
            count = count + 1;
        }
    }

}
