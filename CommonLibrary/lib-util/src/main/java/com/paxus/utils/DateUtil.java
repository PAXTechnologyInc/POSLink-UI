package com.paxus.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Frank.W on 2017/4/28.
 */
public class DateUtil {

    /**
     * Format: YYYYMMDD
     */
    public static String getDate() {
        return getTime("yyyyMMdd");
    }

    /**
     * Format: HHMMSS
     */
    public static String getTime() {
        return getTime("HHmmss");
    }

    public static String getTime(String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.US);
        dateFormat.setTimeZone(TimeZone.getDefault());
        return dateFormat.format(new Date());
    }

    public static Date convert(String date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.US);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
        }
        return new Date();
    }
}
