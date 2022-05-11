package com.pax.us.pay.ui.constant.entry.enumeration;

/**
 * CVV Bypass Reason
 * <p>The reason why the CVV (for credit) or Gift Card PIN was not entered.</p>
 */
public final class BypassReason {
    private BypassReason(){

    }
    /**
     * Want to bypass
     */
    public static final String WANT_TO_BYPASS = "Bypass";

    /**
     * Cannot read
     */
    public static final String CANNOT_READ = "Unable to Read";

    /**
     * Does not exit
     */
    public static final String DOES_NOT_EXIST = "Not Present";


    /**
     * All defined String
     * @return String[]
     */
    public static String[] values() {
        return new String[]{WANT_TO_BYPASS, CANNOT_READ, DOES_NOT_EXIST};
    }
}
