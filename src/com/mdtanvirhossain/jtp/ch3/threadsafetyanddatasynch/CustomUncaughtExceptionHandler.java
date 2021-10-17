package com.mdtanvirhossain.jtp.ch3.threadsafetyanddatasynch;

public class CustomUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        System.out.println("Exception has been thrown from: " + thread.getName());
        System.out.println("Exception message: " + throwable.getMessage());

    }
}
