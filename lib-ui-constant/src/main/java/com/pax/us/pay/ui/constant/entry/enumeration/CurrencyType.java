package com.pax.us.pay.ui.constant.entry.enumeration;

/**
 * Currency Type
 * <p>
 *     It is used to display a amount value.<br>
 *     Example:<br>
 *     The value of {@link com.pax.us.pay.ui.constant.entry.EntryExtraData#PARAM_TOTAL_AMOUNT} is 100,<br>
 *     if used with {@link CurrencyType#USD}, the amount on page is shown as "$1.00",<br>
 *     else, if used with {@link CurrencyType#POINT}, the amount on page is shown as "POINT 100".
 * </p>
 */
public final class CurrencyType {
    private CurrencyType(){

    }
    /**
     * CAD
     */
    public static final String CAD = "CAD";
    /**
     * EUR
     */
    public static final String EUR = "EUR";
    /**
     * GBP
     */
    public static final String GBP = "GBP";
    /**
     * USD
     */
    public static final String USD = "USD";

    /**
     * POINT
     * <p>
     *     Generally used for Loyalty Card
     * </p>
     */
    public static final String POINT = "POINT";

    /**
     * All defined String
     * @return String[]
     */
    public static String[] values() {
        return new String[]{CAD, EUR, GBP, USD, POINT};
    }

}
