package com.paxus.utils;


/**
 * Created by Frank.W on 2017/5/5.
 */
public class StringUtils {

    private StringUtils() {}

    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

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
            return defValue;
        }
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
        return parseFloat(number, 0f)
;    }

    public static float parseFloat(String number, float defValue) {
        if (isEmpty(number))
            return defValue;
        try {
            return Float.parseFloat(number);
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

    public static byte[] str2Hex(String str) {
        if (isEmpty(str)) {
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
            return null;
        }
    }

    public static String hex2Str(byte[] b) {
        if (b == null) {
            return null;
        }
        return hex2Str(b, b.length);
    }

    public static String hex2Str(byte[] b, int length) {
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

    public static byte[] str2Bcd(String str) {
        if (isEmpty(str)) {
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
            //Logger.e(e.getMessage());
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

    /**
     * mask card no using specific pattern
     *
     * @param str the original string
     * @return masked card no
     */
    public static String mask(String str) {
        return mask(str, "(\\w)(?=\\w{4})", "*");
    }

    /**
     * mask card no using specific pattern
     *
     * @param str     the original string
     * @param pattern it's a regular expression
     * @return masked card no
     */
    public static String mask(String str, String pattern, String replacement) {
        if (str == null || str.isEmpty() || pattern == null || pattern.isEmpty()) {
            return str;
        }

        return str.replaceAll(pattern, replacement);
    }
}
