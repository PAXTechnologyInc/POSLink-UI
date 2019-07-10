package com.pax.us.pay.ui.constant.entry.enumeration;

public class SubTransType {
    public static final String NONE = "NONE";
    public static final String BY_CARDNUM = "BY CARDNUM";
    public static final String BY_REFNO = "BY REFNO";
    public static final String ALL = "ALL";

    public static String[] values() {
        return new String[]{NONE, BY_CARDNUM, BY_REFNO, ALL};
    }

}
