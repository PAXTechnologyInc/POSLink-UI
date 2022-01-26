package com.paxus.utils.log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Leon.F on 2017/12/26.
 */

public class CountRunTime {
    private static final Map<String, Long> timeMap = new HashMap<>();

    public static void start(String name) {
        timeMap.put(name, System.currentTimeMillis());
    }

    public static void countPoint(String name) {
        if (timeMap.containsKey(name)) {
            Logger.i("[" + name + "]: " + (System.currentTimeMillis() - timeMap.get(name)));
            timeMap.remove(name);
        }
    }
}

