package com.alex.eko.paragon.utils.functional;


import com.alex.eko.paragon.utils.abstraction.ListMng;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class UpdateUtil {

    public static <T> void updateIfNotNull(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }



    public static <T, V> void updateCollection(
            T entity,
            Map<V, Boolean> items,
            Function<T, List<V>> getCurrentCollection,
            Consumer<T> saveFunction) {

        List<V> currentCollection = getCurrentCollection.apply(entity);
        items.forEach((item, shouldAdd) -> {
            if (shouldAdd) {
                ListMng.addItem(item, currentCollection);
            } else {
                ListMng.removeItem(item, currentCollection);
            }
        });
        saveFunction.accept(entity);
    }


}
