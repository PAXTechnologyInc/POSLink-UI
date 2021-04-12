package com.pax.pay.ui.def.utils;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class RangeFilter {

    public static int getMaxLength(String lengthRange) {
        List<Integer> list = getLengthList(lengthRange);
        return list.get(list.size() - 1);
    }

    public static int getMinLength(String lengthRange) {
        List<Integer> list = getLengthList(lengthRange);
        return list.get(0);
    }

    public static List<Integer> getLengthList(String lengthRange) {
        String[] range = lengthRange.split("[,]");
        List<Integer> lenList = new ArrayList<>();
        for (String len : range) {
            if (len.contains("-")) {
                String[] tmp = len.split("-");
                int start = Integer.parseInt(tmp[0]);
                int end = Integer.parseInt(tmp[1]);
                for (int j = start; j <= end; j++) {
                    lenList.add(j);
                }
            } else {
                if (!TextUtils.isEmpty(len))
                    lenList.add(Integer.parseInt(len));
            }
        }
        return lenList;
    }
}
