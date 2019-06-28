package com.pax.pay.poslink.ui.demo.utils;

import android.util.Log;

public class StringUtils {

    public static boolean isEmpty(String str) {
        return str == null || str.length() <= 0;
    }

    public static long parseLong(String number) {
        return parseLong(number, 0L);
    }

    public static long parseLong(String number, long defValue) {
        if (isEmpty(number))
            return defValue;
        try {
            return Long.parseLong(number);
        } catch (NumberFormatException e) {
            Log.e("TAG", e.getMessage());
        }
        return defValue;
    }

    public static int parseInt(String number) {
        return parseInt(number, 0);
    }

    public static int parseInt(String number, int defValue) {
        if (isEmpty(number))
            return defValue;
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return defValue;
        }
    }

    public static double parseDouble(String number) {
        return parseDouble(number, 0f);
    }

    public static double parseDouble(String number, double defValue) {
        if (isEmpty(number))
            return defValue;
        try {
            return Double.parseDouble(number);
        } catch (NumberFormatException e) {
            return defValue;
        }
    }


}
