package com.pax.us.pay.ui.constant.entry.enumeration;

/**
 * Cashout Type
 * <p>
 *     Cashout: Used to remove the remaining balance from the Card. And the account balance will be zero. Typically used for gift cards.
 * </p>
 *
 */
public final class CashoutType {
    private CashoutType(){

    }

    /**
     * Cashout Active
     */
    public static final String CASHOUT_ACTIVE = "CASHOUT ACTIVE";

    /**
     * Cashout
     */
    public static final String CASHOUT = "CASHOUT";
}
