package com.pax.us.pay.ui.constant.entry.enumeration;

/**
 * PIN Style
 */
@Deprecated public final class PinStyles {

    /**
     * <p>@Deprecated: Instead of sending NORMAL, RETRY, LAST,
     * EntryExtraData.PARAM_TRIALS_REMAINING is set to 2, 1, 0 respectively.</p>
     */
    @Deprecated private PinStyles(){}
    /**
     * NORMAL
     * <p>BroadPOS prompt as "Please Enter PIN"</p>
     */
    @Deprecated public static final String NORMAL = "NORMAL";
    /**
     * RETRY
     * <p>BroadPOS prompt as "PIN Error, Please Retry\nPlease Enter PIN"</p>
     */
    @Deprecated public static final String RETRY = "RETRY";

    /**
     * LAST
     * <p>BroadPOS prompt as "Last PIN Attempt\nPlease Enter PIN"</p>
     */
    @Deprecated public static final String LAST = "LAST";
}
