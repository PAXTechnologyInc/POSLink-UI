package com.pax.us.pay.ui.constant.entry.enumeration;

/**
 * PIN Style
 */
public final class PinStyles {
    private PinStyles(){

    }
    /**
     * RETRY
     * <p>BroadPOS prompt as "Please Enter PIN"</p>
     */
    public static final String NORMAL = "RETRY";
    /**
     * RETRY
     * <p>BroadPOS prompt as "PIN Error, Please Retry\nPlease Enter PIN"</p>
     */
    public static final String RETRY = "RETRY";

    /**
     * LAST
     * <p>BroadPOS prompt as "Last PIN Attempt\nPlease Enter PIN"</p>
     */
    public static final String LAST = "LAST";
}
