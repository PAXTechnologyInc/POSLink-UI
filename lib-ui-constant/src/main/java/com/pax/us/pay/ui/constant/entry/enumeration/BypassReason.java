package com.pax.us.pay.ui.constant.entry.enumeration;

/**
 * CVV Bypass Reason
 * <p>The reason why the CVV (for credit) or Gift Card PIN was not entered.</p>
 */
public final class BypassReason {
    private BypassReason(){
    }

    /**
     * @deprecated  {@value NOT_AVAILABLE} and {@value ILLEGIBLE} replaced the previous options.
     *              Want to bypass
     */
    @Deprecated public static final String WANT_TO_BYPASS = "Bypass";
    /**
     * @deprecated  {@value NOT_AVAILABLE} and {@value ILLEGIBLE} replaced the previous options.
     *              Cannot read
     */
    @Deprecated public static final String CANNOT_READ = "Unable to Read";
    /**
     * @deprecated  {@value NOT_AVAILABLE} and {@value ILLEGIBLE} replaced the previous options.
     *              Does not exist
     */
    @Deprecated public static final String DOES_NOT_EXIST = "Not Present";

    /**
     * Not Available
     */
    public static final String NOT_AVAILABLE = "Not Available";

    /**
     * Illegible
     */
    public static final String ILLEGIBLE = "Illegible";


    /**
     * All defined String
     * @return String[]
     */
    public static String[] values() {
        return new String[]{NOT_AVAILABLE, ILLEGIBLE};
    }
}
