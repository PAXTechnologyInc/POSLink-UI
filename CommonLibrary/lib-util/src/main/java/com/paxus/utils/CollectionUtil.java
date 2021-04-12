package com.paxus.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Leon.F on 2018/1/25.
 */

public class CollectionUtil {

    public static boolean isEmpty(List l) {
        return l == null || l.isEmpty();
    }

    public static <T> List<T> filterList(List<T> list, FilterConditionCallback<T> filterConditionCallback) {
        List<T> result = new ArrayList<>();
        if (list == null) {
            return result;
        }
        for (T item : list) {
            if (filterConditionCallback.onFilter(item)) {
                result.add(item);
            }
        }
        return result;
    }

    public static <K, V> V getOrDefault(Map<K, V> map, K key, V defaultValue) {
        V v;
        return (((v = map.get(key)) != null) || map.containsKey(key))
                ? v
                : defaultValue;
    }

    public static <K, V> void copy(Map<K, V> src, Map<K, V> dest, K key) {
        dest.put(key, src.get(key));
    }

    public static <K, V> boolean compare(Map<K, V> map, K key, V... values) {
        if (values == null) {
            return map.get(key) == null;
        } else {
            for(V i : values) {
                if(i == null && !map.containsKey(key)){
                    return true;
                } else if(i != null && i.equals(map.get(key))) {
                    return true;
                }
            }
            return false;
        }
    }

    public interface FilterConditionCallback<T> {
        boolean onFilter(T item);
    }
}
