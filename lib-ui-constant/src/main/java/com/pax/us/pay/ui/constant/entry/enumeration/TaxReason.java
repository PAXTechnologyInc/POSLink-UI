package com.pax.us.pay.ui.constant.entry.enumeration;

public final class TaxReason {
    public static final String TAX_NOT_USED = "TAX Not Used";
    public static final String TAX_EXEMT = "TAX Exempt";
    public static final String ENTERE_TAX_AGAING = "Enter the tax amount again";


    public static String[] values() {
        return new String[]{TAX_NOT_USED, TAX_EXEMT, ENTERE_TAX_AGAING};
    }
}
