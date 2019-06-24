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
package com.pax.pay.ui.def_ui.utils;

import com.pax.pay.ui.def_ui.log.Logger;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;

public class CurrencyConverter {
    private static final List<Locale> locales = new ArrayList<>();

    private static Locale defLocale = Locale.US;

    static {
        Locale[] tempLocales = Locale.getAvailableLocales();
        for (Locale i : tempLocales) {
            try {
                CountryCode country = CountryCode.getByCode(i.getISO3Country());
                Currency.getInstance(i); // just for filtering
                if (country != null) {
                    locales.add(i);
                }
            } catch (IllegalArgumentException | MissingResourceException e) {
                //ignore exception
            }
        }
    }

    private CurrencyConverter() {
        //do nothing
    }

    public static List<Locale> getSupportedLocale() {
        return locales;
    }

    /**
     * @param countryName : {@see Locale#getDisplayName(Locale)}
     */
    public static Locale setDefCurrency(String countryName) {
        for (Locale i : locales) {
            //if (i.getDisplayName(Locale.US).equals(countryName)) {
            if (i.getCountry().equals(countryName)) {
                if (!i.equals(defLocale)) {
                    defLocale = i;
                    Locale.setDefault(defLocale);
                }
                return defLocale;
            }
        }
        return defLocale;
    }

    public static Locale getDefCurrency() {
        return defLocale;
    }

    public static Locale findLocalByCountryName(String countryName) {
        for (Locale i : locales) {
            if (i.getCountry() != null) {
                if (i.getCountry().equals(countryName)) {
                    return i;
                }
            }
        }
        return defLocale;
    }

    public static String convert(Long amount) {
        return convert(amount, "", defLocale);
    }

    public static String convert(Long amount, String prefix) {
        return convert(amount, prefix, defLocale);
    }

    public static String convert(Long amount, String prefix, Locale locale) {
        Currency currency = Currency.getInstance(locale);
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        formatter.setMinimumFractionDigits(currency.getDefaultFractionDigits());
        formatter.setMaximumFractionDigits(currency.getDefaultFractionDigits());
        Long newAmount = Math.abs(amount); // AET-58
        prefix = newAmount > amount ? "-" : "" + prefix;
        try {
            double amt = Double.valueOf(newAmount) / (Math.pow(10, currency.getDefaultFractionDigits()));
            return prefix + formatter.format(amt);
        } catch (IllegalArgumentException e) {
            Logger.e(e.getMessage());
        }
        return "";
    }

    public static String convert(Double amount) {
        return convert(amount, "", defLocale);
    }

    public static String convert(Double amount, String prefix) {
        return convert(amount, prefix, defLocale);
    }

    public static String convert(Double amount, String prefix, Locale locale) {
        Currency currency = Currency.getInstance(locale);
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        formatter.setMinimumFractionDigits(currency.getDefaultFractionDigits());
        formatter.setMaximumFractionDigits(currency.getDefaultFractionDigits());
        double newAmount = Math.abs(amount); // AET-58
        prefix = newAmount > amount ? "-" : "" + prefix;
        try {
            return prefix + formatter.format(newAmount);
        } catch (IllegalArgumentException e) {
            Logger.e(e.getMessage());
        }
        return "";
    }

    public static Long parse(String formatterAmount) {
        return parse(formatterAmount, defLocale);
    }

    public static Long parse(String formatterAmount, Locale locale) {
        Currency currency = Currency.getInstance(locale);
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        formatter.setMinimumFractionDigits(currency.getDefaultFractionDigits());
        formatter.setMaximumFractionDigits(currency.getDefaultFractionDigits());
        try {
            Number num = formatter.parse(formatterAmount);

            return Math.round(num.doubleValue() * Math.pow(10, currency.getDefaultFractionDigits()));
        } catch (ParseException | NumberFormatException e) {
            Logger.e(e.getMessage());
        }
        return 0L;
    }
}
