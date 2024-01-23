package com.pax.us.pay.ui.constant.entry.enumeration;

/**
 * MOTO Type
 */
public final class MOTOType {
    private MOTOType(){

    }

    /**
     * Mail Order
     */
    public static final String MAIL = "MAIL ORDER";

    /**
     * Phone Order
     */
    public static final String PHONE = "PHONE ORDER";

    /**
     * None
     */
    public static final String NONE = "NONE";

    public static String[] values() {
        return new String[]{MAIL, PHONE, NONE};
    }
}
