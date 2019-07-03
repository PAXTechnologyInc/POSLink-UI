package com.pax.us.pay.ui.constant.entry.enumeration;

public class RefundReason {

    public static final String RETURN = "Return";
    public static final String ADJUSTMENT = "Adjustment";


    public static String[] values() {
        return new String[]{RETURN, ADJUSTMENT};
    }
}
