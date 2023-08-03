package com.pax.us.pay.ui.constant.entry;

/**
 * define Broadcast about BroadPOS's response
 */
public final class EntryResponse {
    private EntryResponse(){

    }

    /**
     * BroadPOS Action: ACCEPTED <br>{@value #ACTION_ACCEPTED}<br>
     * <p>BroadPOS accepted ACTIVITY ACTION OUTPUT from your app.</p>
     */
    public static final String ACTION_ACCEPTED = "com.pax.us.pay.ui.ACCEPTED";

    /**
     * BroadPOS Action:DECLINED<br>{@value #ACTION_DECLINED}<br>
     * <p>BroadPOS declined ACTIVITY ACTION OUTPUT</p>
     * <p>
     *     Input: {@link EntryResponse#PARAM_CODE} - {@value EntryResponse#PARAM_CODE}<br>
     *     Type: Long
     *     </p>
     * <p>
     *     Input: {@link EntryResponse#PARAM_MSG} - {@value EntryResponse#PARAM_MSG}<br>
     *     Type: String</p>
     */
    public static final String ACTION_DECLINED = "com.pax.us.pay.ui.DECLINED";

    /**
     * Result Code
     * <p>Type: Long</p>
     */
    public static final String PARAM_CODE = "resultCode";

    /**
     * Result Message
     * <p>Type: String</p>
     */
    public static final String PARAM_MSG = "resultMessage";
}
