package com.mdtanvirhossain.jtp.ch4.synchandcncrcollection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Chapter4 {
    public static void main(String[] args) {
        System.out.println("Chapter-4");

        // synchronized list
        List<Integer> numbers = Collections.synchronizedList(new ArrayList<>());

        // client side locking
        synchronized (numbers) {
            for (int i = 0; i < numbers.size(); i++) {
                System.out.println(numbers.get(i));

            }
        }
    }
}
