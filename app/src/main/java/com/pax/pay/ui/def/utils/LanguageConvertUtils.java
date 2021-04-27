package com.pax.pay.ui.def.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

public class LanguageConvertUtils {


    private final static String LAN_FR = "fr";
    private final static String LAN_EN = "en";


    public static String convertString(Context context, int resID, Integer replaceResId, Object... formatArgs) {
        String str = context.getResources().getString(resID);
        int idx = str.lastIndexOf("$");
        String len = str.substring(idx - 1, idx);
        int parNum = Integer.parseInt(len);
        Object[] newObj = new Object[parNum];
        if ((formatArgs.length + 1) == parNum) {
            newObj[0] = context.getResources().getString(replaceResId);
            for (int i = 0; i < formatArgs.length; i++) {
                newObj[i + 1] = formatArgs[i];
            }
            return context.getResources().getString(resID, newObj);
        } else {
            if (replaceResId != null) {
                Configuration conf = context.getResources().getConfiguration();
                Locale loc;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    loc = conf.getLocales().get(0);
                } else
                    loc = conf.locale;
                if (Locale.CANADA_FRENCH.equals(loc)) {
                    String title_fr = getResourcesByLocale(context.getResources(), LAN_FR).getString(replaceResId);
                    String title_en = getResourcesByLocale(context.getResources(), LAN_EN).getString(replaceResId);
                    resetLocale(context.getResources(), loc);
                    newObj[0] = title_en;
                    for (int i = 0; i < formatArgs.length; i++) {
                        newObj[i + 1] = formatArgs[i];
                    }
                    newObj[formatArgs.length + 1] = title_fr;
                    for (int i = 0; i < formatArgs.length; i++) {
                        newObj[formatArgs.length + 1 + i + 1] = formatArgs[i];
                    }
                    return context.getResources().getString(resID, newObj);
                }
            } else {
                for (int i = 0; i < formatArgs.length; i++) {
                    newObj[i] = formatArgs[i];
                }
                for (int i = 0; i < formatArgs.length; i++) {
                    newObj[formatArgs.length + i] = formatArgs[i];
                }
                return context.getResources().getString(resID, newObj);
            }
        }
        return null;
    }

    private static Resources getResourcesByLocale(Resources res, String localeName) {
        Configuration conf = new Configuration(res.getConfiguration());
        conf.locale = new Locale(localeName);
        return new Resources(res.getAssets(), res.getDisplayMetrics(), conf);
    }

    private static void resetLocale(Resources res, Locale locale) {
        Configuration conf = new Configuration(res.getConfiguration());
        conf.locale = locale;
        new Resources(res.getAssets(), res.getDisplayMetrics(), conf);
    }
}
