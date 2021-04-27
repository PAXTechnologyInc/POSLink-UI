/*
 * ============================================================================
 * COPYRIGHT
 *              Pax CORPORATION PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with Pax Corporation and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *      Copyright (C) 2016 - ? Pax Corporation. All rights reserved.
 * Module Date: 2016-11-30
 * Module Author: Kim.L
 * Description:
 *
 * ============================================================================
 */
package com.pax.us.pay.ui.message;

import android.annotation.TargetApi;
import android.icu.util.ULocale;
import android.os.Build;
import android.util.Log;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Currency;
import java.util.Locale;

public class CurrencyConverter {

    private static Locale defLocale = Locale.US;

    private CurrencyConverter() {
        //do nothing
    }

    /**
     * @param currencyName : {@see Locale#getDisplayName(Locale)}
     */
    public static Locale setDefCurrency(String currencyName) {
        if (currencyName == null || currencyName.isEmpty()) {
            currencyName = "USD";
        }

        CurrencyCode code = CurrencyCode.findTypeByCurrencyName(currencyName);
        if (code == null) {
            return defLocale;
        }

        Locale locale = code.getLocale();
        if (locale != null) {
            defLocale = locale;
            Locale.setDefault(defLocale);
            return defLocale;
        }

        return defLocale;
    }

    public static Locale getDefCurrency() {
        return defLocale;
    }

    public static String convert(Long amount) {
        return convert(amount, "", defLocale);
    }

    public static String convert(Long amount, String prefix) {
        return convert(amount, prefix, defLocale);
    }

    public static String convert(Long amount, String prefix, Locale locale) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ULocale uLocale = ULocale.forLocale(locale);
            android.icu.util.Currency currency = findCurrencyByLocale(uLocale);
            double amt = Double.valueOf(amount) / (Math.pow(10, currency.getDefaultFractionDigits()));
            return convert(amt, prefix, uLocale, currency);
        } else {
            Currency currency = findCurrencyByLocale(locale);
            double amt = Double.valueOf(amount) / (Math.pow(10, currency.getDefaultFractionDigits()));
            return convert(amt, prefix, locale, currency);
        }
    }

    public static String convert(Double amount) {
        return convert(amount, "", defLocale);
    }

    public static String convert(Double amount, String prefix) {
        return convert(amount, prefix, defLocale);
    }

    public static String convert(Double amount, String prefix, Locale locale) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ULocale uLocale = ULocale.forLocale(locale);
            return convert(amount, prefix, uLocale, findCurrencyByLocale(uLocale));
        } else {
            return convert(amount, prefix, locale, findCurrencyByLocale(locale));
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    public static android.icu.util.Currency findCurrencyByLocale(ULocale locale) {
        String variant = locale.getVariant();
        android.icu.util.Currency currency;
        if(variant != null && !variant.isEmpty()) {
            currency = android.icu.util.Currency.getInstance(variant);
        } else {
            currency = android.icu.util.Currency.getInstance(locale);
        }
        return currency;
    }

    public static Currency findCurrencyByLocale(Locale locale) {
        String variant = locale.getVariant();
        Currency currency;
        if(variant != null && !variant.isEmpty()) {
            currency = Currency.getInstance(variant);
        } else {
            currency = Currency.getInstance(locale);
        }
        return currency;
    }

    @TargetApi(Build.VERSION_CODES.N)
    public static String convert(Double amount, String prefix, ULocale locale, android.icu.util.Currency currency) {
        double newAmount = Math.abs(amount); // AET-58
        prefix = newAmount > amount ? "-" : "" + prefix;

        android.icu.text.NumberFormat formatter = android.icu.text.NumberFormat.getCurrencyInstance(locale);
        formatter.setCurrency(currency);
        try {
            return prefix + formatter.format(newAmount);
        } catch (IllegalArgumentException e) {
            Log.e("", e.getMessage());
        }
        return "";
    }

    public static String convert(Double amount, String prefix, Locale locale, Currency currency) {
        double newAmount = Math.abs(amount); // AET-58
        prefix = newAmount > amount ? "-" : "" + prefix;

        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        formatter.setCurrency(currency);
        try {
            return prefix + formatter.format(newAmount);
        } catch (IllegalArgumentException e) {
            Log.e("", e.getMessage());
        }
        return "";
    }

    public static Long parse(String formatterAmount) {
        return parse(formatterAmount, defLocale);
    }

    public static Long parse(String formatterAmount, Locale locale) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ULocale uLocale = ULocale.forLocale(locale);
            android.icu.text.NumberFormat formatter = android.icu.text.NumberFormat.getCurrencyInstance(uLocale);
            android.icu.util.Currency currency = findCurrencyByLocale(uLocale);
            formatter.setCurrency(currency);
            try {
                Number num = formatter.parse(formatterAmount);

                return Math.round(num.doubleValue() * Math.pow(10, currency.getDefaultFractionDigits()));
            } catch (ParseException | NumberFormatException e) {
                Log.e("", e.getMessage());
            }
        } else {
            NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
            Currency currency = findCurrencyByLocale(locale);
            formatter.setCurrency(currency);
            try {
                Number num = formatter.parse(formatterAmount);

                return Math.round(num.doubleValue() * Math.pow(10, currency.getDefaultFractionDigits()));
            } catch (ParseException | NumberFormatException e) {
                Log.e("", e.getMessage());
            }
        }
        return 0L;
    }
}
