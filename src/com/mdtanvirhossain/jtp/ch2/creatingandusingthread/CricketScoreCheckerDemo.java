package com.mdtanvirhossain.jtp.ch2.creatingandusingthread;

import java.util.concurrent.TimeUnit;

public class CricketScoreCheckerDemo {
    public static void main(String[] args) {
        CricketScoreChecker cricketScoreChecker = new CricketScoreChecker();
        cricketScoreChecker.start();

        // put in sleep the main thread
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // shutdown the thread, cricket match is over
        cricketScoreChecker.shutdown();
    }
}
