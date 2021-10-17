package com.mdtanvirhossain.jtp.ch4.synchandcncrcollection;

import java.util.Vector;

public class ListHelper {
    public static <E> E getLast(Vector<E> list) {
        synchronized (list) {
            int lastIndex = list.size() - 1;
            return list.get(lastIndex);
        }
    }

    public static <E> void removeLast(Vector<E> list) {
        synchronized (list) {
            int lastIndex = list.size() - 1;
            list.remove(lastIndex);
        }
    }

}
