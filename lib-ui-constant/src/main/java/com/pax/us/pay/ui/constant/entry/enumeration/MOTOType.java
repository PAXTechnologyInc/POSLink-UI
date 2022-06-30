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

    public static String[] values() {
        return new String[]{MAIL, PHONE};
    }
}
