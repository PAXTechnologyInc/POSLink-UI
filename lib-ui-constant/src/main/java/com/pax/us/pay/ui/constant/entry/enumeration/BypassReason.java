package com.pax.us.pay.ui.constant.entry.enumeration;

public final class BypassReason {
    public static final String WANT_TO_BYPASS = "Bypass";
    public static final String CANNOT_READ = "Unable to Read";
    public static final String DOES_NOT_EXIST = "Not Present";


    public static String[] values() {
        return new String[]{WANT_TO_BYPASS, CANNOT_READ, DOES_NOT_EXIST};
    }
}
