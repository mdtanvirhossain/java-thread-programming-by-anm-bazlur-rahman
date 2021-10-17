package com.mdtanvirhossain.jtp.ch2.creatingandusingthread;

public class RunnableThreadDemoUsingLambda {
    public static void main(String[] args) {

        // using anonymous inner class
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
            }
        });
        thread1.start();

        // using lambda
        Thread thread2 = new Thread(() -> doWork());
        thread2.start();
    }

    private static void doWork() {
        System.out.println("Doing some important work!");
    }
}
