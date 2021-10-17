package com.mdtanvirhossain.jtp.ch2.creatingandusingthread;

import java.util.concurrent.TimeUnit;

public class CricketScoreChecker extends Thread {

    private volatile boolean keepChecking = true;

    @Override
    public void run() {
        while (keepChecking) {
            checkScoreAndPrintTheResult();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void checkScoreAndPrintTheResult() {
        System.out.println("Current score is: BAN 125/2 (10 ov. target 220)");
    }

    public void shutdown() {
        this.keepChecking = false;
    }
}
