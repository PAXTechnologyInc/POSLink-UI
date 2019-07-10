package com.pax.us.pay.ui.constant.entry.enumeration;

public final class EBTType {
    public static final String FOOD_STAMP = "FOOD STAMP";
    public static final String CASH_BENEFIT = "CASH BENEFIT";
    public static final String VOUCHER = "VOUCHER";
    public static final String E_WIC = "E WIC";
    public static final String E_WIC_VOUCHER = "E WIC VOUCHER";

    public static String[] values() {
        return new String[]{FOOD_STAMP, CASH_BENEFIT, VOUCHER, E_WIC, E_WIC_VOUCHER};
    }
}
