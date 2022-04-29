package com.pax.us.pay.ui.constant.entry.enumeration;

/**
 * Input Type
 */
public final class InputType {
    private InputType(){

    }
//    /**
//     * TEXT
//     * @deprecated Use {@link #ALPHA_NUMERIC} instead.
//     */
//    public static final String ALLTEXT = "ALLTEXT";

    /**
     * Alpha-Numeric String.
     */
    public static final String ALPHA_NUMERIC = "ALPHA_NUMERIC"; //"0";

    /**
     * Numeric String
     */
    public static final String NUMERIC = "NUMERIC";//"1";

    /**
     * Date in MMDDYYYY format. For POSLinkEntry use only.
     */
    public static final String DATE = "DATE";//"2";

    /**
     * Time in HHMMSS format. For POSLinkEntry use only.
     */
    public static final String TIME = "TIME";//"3";

    /**
     * Currency in XXXXXX.XX format. For POSLinkEntry use only.
     */
    public static final String CURRENCY = "CURRENCY";//"4";

    /**
     * Password format. . For POSLinkEntry use only.
     */
    public static final String PASSWORD = "PASSWORD";//"5";

    /**
     * Phone number as "(xxx) xxx-xxxx". For POSLinkEntry use only.
     */
    public static final String PHONE = "PHONE";//"6";

    /**
     * Social Security as "xxx-xx-xxxx". For POSLinkEntry use only.
     */
    public static final String SSN = "SSN";//"7";
}
