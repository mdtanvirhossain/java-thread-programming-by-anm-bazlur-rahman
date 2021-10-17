package com.mdtanvirhossain.jtp.appendix.a3;

public class LiveLockDemo {
    public static void main(String[] args) {
        final Person person1 = new Person("Person # 1", true);
        final Person person2 = new Person("Person # 2", true);

        final Corridor s = new Corridor(person1);

        new Thread(() -> {
            person1.work(s, person2);
        }).start();

        new Thread(() -> {
            person2.work(s, person1);
        }).start();
    }
}
