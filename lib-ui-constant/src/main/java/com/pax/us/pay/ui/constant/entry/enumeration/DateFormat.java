package com.pax.us.pay.ui.constant.entry.enumeration;

public final class DateFormat {
    private DateFormat(){
    }

    public static final String MMYY = "MMYY";

    public static final String MMDD = "MMDD";

    public static final String YYYYMMDD = "YYYYMMDD";

    /**
     * All defined String
     * @return String[]
     */
    public static String[] values() {
        return new String[]{MMYY, MMDD, YYYYMMDD};
    }

}
