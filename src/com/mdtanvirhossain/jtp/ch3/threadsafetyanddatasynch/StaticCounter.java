package com.mdtanvirhossain.jtp.ch3.threadsafetyanddatasynch;

public class StaticCounter {
    private static int count;

    public static synchronized void incrementCounter() {
        count = count + 1;
    }

    public static void incrementCounterBlock() {
        System.out.println("Going to increment counter.");

        synchronized (StaticCounter.class) {
            count = count + 1;
        }
    }

}
