package com.pax.us.pay.ui.constant.entry.enumeration;

/**
 * Tax Reason
 */
public final class TaxReason {
    private TaxReason(){

    }

    /**
     * TAX NOT USED
     */
    public static final String TAX_NOT_USED = "TAX NOT USED";

    /**
     * TAX EXEMPT
     */
    public static final String TAX_EXEMPT = "TAX EXEMPT";

    /**
     * ENTER THE TAX AMOUNT AGAIN
     */
    public static final String ENTER_TAX_AGAIN = "ENTER THE TAX AMOUNT AGAIN";


    public static String[] values() {
        return new String[]{TAX_NOT_USED, TAX_EXEMPT, ENTER_TAX_AGAIN};
    }
}
