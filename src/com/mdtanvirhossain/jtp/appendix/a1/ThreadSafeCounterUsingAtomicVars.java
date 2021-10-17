package com.mdtanvirhossain.jtp.appendix.a1;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSafeCounterUsingAtomicVars {
    private AtomicInteger value = new AtomicInteger(0);

    public void increment() {
        value.incrementAndGet();
    }

    public void decrement() {
        value.decrementAndGet();
    }

    public int value() {
        return value.get();
    }
}
