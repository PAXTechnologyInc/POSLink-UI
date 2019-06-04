package com.pax.pay.ui.utils;


import com.pax.pay.ui.log.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 日期相关处理 Created by Frank.W on 2017/4/28.
 */

public class DateUtils {

    public static SimpleDateFormat create(String pattern) {
        return new SimpleDateFormat(pattern, Locale.US);
    }

    public static Date convert(String date, String pattern) {
        SimpleDateFormat sdf = create(pattern);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            Logger.e(e.getMessage());
        }
        return new Date();
    }

    public static String getSystemDate(String formatStr) {
        Date sysDate = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat(formatStr, Locale.US);
        return format.format(sysDate);
    }

    public static String getDateString(Date date) {
        if (date == null) {
            return "";
        }

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        return format.format(date);
    }

    // String yyyMMdd
    public static String getDateString(String date) {
        if (date == null || date.length() != 8) {
            return "";
        }

        date = date.substring(4, 6) + "/" + date.substring(6) + "/" + date.substring(0, 4);
        return date;
    }

    public static String getTimeString(String time) {
        if (time == null || time.length() != 6) {
            return "";
        }

        time = time.substring(0, 2) + ":" + time.substring(2, 4) + ":" + time.substring(4);
        return time;
    }

}
