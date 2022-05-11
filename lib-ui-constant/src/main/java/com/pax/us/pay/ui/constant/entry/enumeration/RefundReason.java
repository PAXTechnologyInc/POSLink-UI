package com.pax.us.pay.ui.constant.entry.enumeration;

/**
 * Refund reason
 */
public class RefundReason {

    /**
     * Return
     */
    public static final String RETURN = "RETURN";

    /**
     * Adjustment
     */
    public static final String ADJUSTMENT = "ADJUSTMENT";


    public static String[] values() {
        return new String[]{RETURN, ADJUSTMENT};
    }
}
