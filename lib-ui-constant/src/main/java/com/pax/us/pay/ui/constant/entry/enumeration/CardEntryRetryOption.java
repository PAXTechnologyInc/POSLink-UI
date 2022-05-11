package com.pax.us.pay.ui.constant.entry.enumeration;

/**
 * Options when handle a partial-approved transaction
 *
 */
public final class CardEntryRetryOption {
    private CardEntryRetryOption(){

    }
    /**
     * Continue
     *
     * <p>Retry card entry</p>
     */
    public static final String CONTINUE = "CONTINUE";
    /**
     * Decline
     *
     * <p>Do not retry card entry</p>
     */
    public static final String DECLINE = "DECLINE";
}