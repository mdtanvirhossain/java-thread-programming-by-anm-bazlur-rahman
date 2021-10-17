package com.mdtanvirhossain.jtp.ch2.creatingandusingthread;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class Watch implements Runnable {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss a");
    private volatile boolean running = true;

    @Override
    public void run() {
        while (running) {
            printCurrentTime();
            sleepOneSecond();
        }
    }

    public void printCurrentTime() {
        String formattedCurrentTime = LocalDateTime.now().format(formatter);

        // print the current time
        System.out.println("Formatted current time = " + formattedCurrentTime);
    }

    private void sleepOneSecond() {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        this.running = false;
    }
}
