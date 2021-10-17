package com.mdtanvirhossain.jtp.ch6.executorframework;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Watch6 {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        /*Runnable task = new Runnable() {
            @Override
            public void run() {
                printCurrentTime();
            }
        };
        executorService.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);*/

        executorService.scheduleAtFixedRate(() -> printCurrentTime(), 0, 1, TimeUnit.SECONDS);
    }

    private static void printCurrentTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss a");
        String formattedCurrentTime = LocalDateTime.now().format(formatter);

        // print the current time
        // Toolkit.getDefaultToolkit().beep(); // for beep sound
        System.out.println(formattedCurrentTime);
    }
}
