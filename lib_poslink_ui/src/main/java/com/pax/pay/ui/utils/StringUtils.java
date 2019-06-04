package com.pax.pay.ui.utils;

import android.text.TextUtils;
import android.util.Base64;

import com.pax.pay.ui.log.Logger;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

/**
 * 处理string的工具 Created by Frank.W on 2017/5/5.
 */

public class StringUtils {
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static boolean isEmpty(String str) {
        return str == null || str.length() <= 0;
    }

    public static long maxAmount(int maxSize) {
        long max = 1;
        for (int i = 0; i < maxSize; i++) {
            max *= 10;
        }

        return max - 1;
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
            Logger.e(e.getMessage());
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

    public static float parseFloat(String number) {
        if (isEmpty(number))
            return 0f;
        try {
            return Float.parseFloat(number);
        } catch (NumberFormatException e) {
            return 0f;
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

    public static boolean isZero(String number) {
        return parseDouble(number) == 0;
    }

    public static String format(String format, Object... args) {
        return String.format(Locale.US, format, args);
    }

    public static byte[] str2Bcd(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        //Padding Left
        if ((str.length() % 2) != 0) {
            str = "0" + str;
        }
        try {
            byte[] result = new byte[str.length() / 2];
            for (int i = 0; i < result.length; i++) {
                result[i] = Integer.valueOf(str.substring(i * 2, (i + 1) * 2), 16).byteValue();
            }
            return result;
        } catch (Exception e) {
            Logger.e(e.getMessage());
        }

        return null;
    }

    public static String bcd2Str(byte[] b) {
        if (b == null) {
            return null;
        }
        return bcd2Str(b, b.length);
    }

    public static String bcd2Str(byte[] b, int length) {
        if (b == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(length * 2);
        for (int i = 0; i < length; ++i) {
            sb.append(HEX_DIGITS[((b[i] & 0xF0) >>> 4)]);
            sb.append(HEX_DIGITS[(b[i] & 0xF)]);
        }

        return sb.toString();
    }


    public static boolean isNumber(String str) {
        if (isEmpty(str)) {
            return false;
        }
        return str.matches("-[0-9]+(.[0-9]+)?|[0-9]+(.[0-9]+)?");
    }

    public static String encodeBase64(String result) throws UnsupportedEncodingException {
        return new String(Base64.encode(result.getBytes(), Base64.DEFAULT), "UTF-8");
    }

    public static String decodeBase64(String result) throws UnsupportedEncodingException {
        return new String(Base64.decode(result, Base64.DEFAULT), "UTF-8");
    }
}
