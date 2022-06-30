package com.pax.us.pay.ui.constant.entry.enumeration;

/**
 * General Confirmation Type
 * 
 */
public final class ConfirmationType {
    private ConfirmationType(){

    }

    /**
     * Yes
     */
    public static final String YES = "YES";

    /**
     * No
     */
    public static final String NO = "NO";

    /**
     * All defined String
     * @return String[]
     */
    public static String[] values() {
        return new String[]{YES, NO};
    }

}
