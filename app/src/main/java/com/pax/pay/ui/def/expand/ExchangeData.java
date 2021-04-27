package com.pax.pay.ui.def.expand;

import java.util.Map;

public class ExchangeData {

    private static Map<String, String> transDataMap;

    public static Map<String, String> getTransDataMap() {
        return transDataMap;
    }

    public static void setTransDataMap(Map<String, String> map) {
        transDataMap = map;
    }
}
