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
package com.paxus.utils;

import android.annotation.TargetApi;
import android.icu.util.ULocale;
import android.os.Build;
import android.util.Log;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Currency;
import java.util.Locale;

public class CurrencyConverter {

    private CurrencyConverter() {
        //do nothing
    }

    /**
     * @param currencyCode : ISO 4127 code, or POINT
     */
    private static CurrencyCode getDefCurrency(String currencyCode) {
        if (currencyCode == null || currencyCode.isEmpty()) {
            return CurrencyCode.US;
        }

        CurrencyCode code = CurrencyCode.findTypeByCurrencyName(currencyCode);
        return code == null ? CurrencyCode.US : code;
    }

    public static String convert(long amount, String prefix, String currencyCode) {

        CurrencyCode code = getDefCurrency(currencyCode);

        if (CurrencyCode.POINT.equals(code)) {
            //For POINT, don't nee prefix "USD".
            return convert(amount, "", new DecimalFormat(",###"));
        }

        Locale locale = code.getLocale();
        if (locale != null) {
            return convert(amount, prefix, locale);
        } else {
            return Long.toString(amount);
        }
    }

    public static String convert(long amount, String prefix, NumberFormat formatter) {
        double newAmount = Math.abs(amount); // AET-58
        prefix = newAmount > amount ? "-" : "" + prefix;

        try {
            return prefix + formatter.format(newAmount);
        } catch (IllegalArgumentException e) {
            Log.e("", e.getMessage());
        }
        return Long.toString(amount);
    }

    public static String convert(long amount, String prefix, Locale locale) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ULocale uLocale = ULocale.forLocale(locale);
            android.icu.util.Currency currency = findCurrencyByLocale(uLocale);
            double amt = amount / (Math.pow(10, currency.getDefaultFractionDigits()));
            return convert(amt, prefix, uLocale, currency);
        } else {
            Currency currency = findCurrencyByLocale(locale);
            double amt = amount / (Math.pow(10, currency.getDefaultFractionDigits()));
            return convert(amt, prefix, locale, currency);
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static android.icu.util.Currency findCurrencyByLocale(ULocale locale) {
        String variant = locale.getVariant();
        android.icu.util.Currency currency;
        if (variant != null && !variant.isEmpty()) {
            currency = android.icu.util.Currency.getInstance(variant);
        } else {
            currency = android.icu.util.Currency.getInstance(locale);
        }
        return currency;
    }

    private static Currency findCurrencyByLocale(Locale locale) {
        String variant = locale.getVariant();
        Currency currency;
        if (variant != null && !variant.isEmpty()) {
            currency = Currency.getInstance(variant);
        } else {
            currency = Currency.getInstance(locale);
        }
        return currency;
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static String convert(double amount, String prefix, ULocale locale, android.icu.util.Currency currency) {
        double newAmount = Math.abs(amount); // AET-58
        prefix = newAmount > amount ? "-" : "" + prefix;

        android.icu.text.NumberFormat formatter = android.icu.text.NumberFormat.getCurrencyInstance(locale);
        formatter.setCurrency(currency);
        try {
            return prefix + formatter.format(newAmount);
        } catch (IllegalArgumentException e) {
            Log.e("", e.getMessage());
        }
        return Double.toHexString(amount);
    }

    private static String convert(double amount, String prefix, Locale locale, Currency currency) {
        double newAmount = Math.abs(amount); // AET-58
        prefix = newAmount > amount ? "-" : "" + prefix;

        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        formatter.setCurrency(currency);
        try {
            return prefix + formatter.format(newAmount);
        } catch (IllegalArgumentException e) {
            Log.e("", e.getMessage());
        }
        return Double.toHexString(amount);
    }

    public static long parse(String formatterAmount, String currencyCode) {
        CurrencyCode code = getDefCurrency(currencyCode);

        if (CurrencyCode.POINT.equals(code)) {
            //For POINT, don't nee prefix "USD".
            return parse(formatterAmount, new DecimalFormat(",###"));
        }

        Locale locale = code.getLocale();
        if (locale != null) {
            return parse(formatterAmount, locale);
        } else {
            return 0L;
        }
    }

    public static long parse(String formatterAmount, NumberFormat formatter) {
        try {
            return formatter.parse(formatterAmount).longValue();
        } catch (ParseException | NumberFormatException e) {
            Log.e("", e.getMessage());
        }
        return 0L;
    }

    public static long parse(String formatterAmount, Locale locale) {
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
