package com.alex.eko.paragon.utils.abstraction;

import java.util.List;

public class ListMng {

    public static <T> void addItem(T item, List<T> list) {
        if (item != null && !list.contains(item)) {
            list.add(item);
        }
    }
    public static <T> void removeItem(T item, List<T> list) {
        if (item != null) {
            list.remove(item);
        }
    }

}
