package com.mdtanvirhossain.jtp.ch1.whatisthreading;

public class MainThreadExample {
    public static void main(String[] args) {
        Thread currentThread = Thread.currentThread();
        System.out.println("Current thread name: " + currentThread.getName());
    }
}
