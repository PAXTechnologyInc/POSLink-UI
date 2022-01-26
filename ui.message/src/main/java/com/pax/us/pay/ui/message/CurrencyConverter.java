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

import com.paxus.utils.CurrencyCode;

import java.util.Locale;

/**
 * @see com.paxus.utils.CurrencyConverter
 * @deprecated
 */
@Deprecated
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
        return convert(amount, "");
    }

    public static String convert(Long amount, String prefix) {
        return com.paxus.utils.CurrencyConverter.convert(amount, prefix, defLocale);
    }

    public static Long parse(String formatterAmount) {
        return parse(formatterAmount, defLocale);
    }

    public static Long parse(String formatterAmount, Locale locale) {
        return com.paxus.utils.CurrencyConverter.parse(formatterAmount, locale);
    }
}
