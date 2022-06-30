package com.pax.us.pay.ui.constant.entry.enumeration;

/**
 * Merchant Scope
 * <p>
 *     Confirm Batch current merchant or all merchants<br>
 *     Used in {@link com.pax.us.pay.ui.constant.entry.ConfirmationEntry#ACTION_CONFIRM_MERCHANT_SCOPE}
 * </p>
 *
 */
public final class MerchantScope {
    private MerchantScope(){

    }
    /**
     * All merchants
     */
    public static final String ALL = "All";

    /**
     * Current merchant
     */
    public static final String CURRENT = "Current";

}
