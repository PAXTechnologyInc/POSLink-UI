package com.pax.us.pay.ui.constant.entry.enumeration;

public class RefundReason {

    public static final String RETURN = "RETURN";
    public static final String ADJUSTMENT = "ADJUSTMENT";


    public static String[] values() {
        return new String[]{RETURN, ADJUSTMENT};
    }
}
