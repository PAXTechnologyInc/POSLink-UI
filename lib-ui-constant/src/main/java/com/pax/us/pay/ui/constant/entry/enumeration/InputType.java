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
    public static final String ALPHA_NUMERIC = "0";

    /**
     * Numeric String
     */
    public static final String NUMERIC = "1";

    /**
     * Date in MMDDYYYY format. For POSLinkEntry use only.
     */
    public static final String DATE = "2";

    /**
     * Time in HHMMSS format. For POSLinkEntry use only.
     */
    public static final String TIME = "3";

    /**
     * Currency in XXXXXX.XX format. For POSLinkEntry use only.
     */
    public static final String CURRENCY = "4";

    /**
     * Password format
     */
    public static final String PASSWORD = "5";

    /**
     * Phone number as "(xxx) xxx-xxxx". For POSLinkEntry use only.
     */
    public static final String PHONE = "6";

    /**
     * Social Security as "xxx-xx-xxxx". For POSLinkEntry use only.
     */
    public static final String SSN = "7";
}
