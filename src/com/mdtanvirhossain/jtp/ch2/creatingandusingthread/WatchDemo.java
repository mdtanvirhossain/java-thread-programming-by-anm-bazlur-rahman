package com.mdtanvirhossain.jtp.ch2.creatingandusingthread;

public class WatchDemo {
    public static void main(String[] args) throws InterruptedException {

        Watch watch = new Watch();

        Thread watchThread = new Thread(watch);
        watchThread.start();

        Thread.sleep(500);
        watch.shutdown();

    }
}
