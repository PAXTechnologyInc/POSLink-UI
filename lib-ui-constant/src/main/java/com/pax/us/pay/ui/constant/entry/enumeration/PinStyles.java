package com.pax.us.pay.ui.constant.entry.enumeration;

/**
 * PIN Style
 */
@Deprecated public final class PinStyles {
    @Deprecated private PinStyles(){

    }
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
