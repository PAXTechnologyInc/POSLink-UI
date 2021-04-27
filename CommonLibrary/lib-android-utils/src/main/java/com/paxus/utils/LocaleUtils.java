package com.paxus.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.util.DisplayMetrics;

import java.util.Locale;

/**
 * Created by Yanina.Yang on 2/16/2021.
 */
public class LocaleUtils {
    public static final String PREF_LANGUAGE = "pref_language";
    public static final String PREF_COUNTRY = "pref_country";
    private static final String SHARED_PREFS = "prefs_locale";

    private LocaleUtils() {

    }

    public static ContextWrapper updateLanguage(Context baseContext, String language, String country) {
        return updateLanguage(baseContext, language, country, true);
    }

    private static ContextWrapper updateLanguage(Context baseContext, String language, String country, boolean savePrefs) {
        Locale locale = Locale.ENGLISH;
        if ("fr".equalsIgnoreCase(language)) {
            locale = Locale.FRENCH;
            if ("ca".equalsIgnoreCase(country)) {
                locale = Locale.CANADA_FRENCH;
            }
        }

        Resources resources = baseContext.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();

        if (locale != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                config.setLocale(locale);
                LocaleList localeList = new LocaleList(locale);
                if (localeList.size() > 0) {
                    LocaleList.setDefault(localeList);
                    config.setLocales(localeList);
                }
                baseContext = baseContext.createConfigurationContext(config);
            } else {
                config.locale = locale;
                resources.updateConfiguration(config, dm);
            }
        }

        if (savePrefs) {
            SharedPreferences preferences = baseContext.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(PREF_COUNTRY, country);
            editor.putString(PREF_LANGUAGE, language);
            editor.commit();
        }
        return new ContextWrapper(baseContext);
    }

    public static ContextWrapper wrapContext(Context baseContext) {
        SharedPreferences preferences = baseContext.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        String language = preferences.getString(PREF_LANGUAGE, null);
        String country = preferences.getString(PREF_COUNTRY, null);
        return updateLanguage(baseContext, language, country, false);
    }

    public static String getString(Context baseContext, int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return wrapContext(baseContext).getString(resId);
        } else {
            return baseContext.getString(resId);
        }
    }

    public static String getString(Context baseContext, int resId, Object... args) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return wrapContext(baseContext).getString(resId, args);
        } else {
            return baseContext.getString(resId, args);
        }
    }

}
