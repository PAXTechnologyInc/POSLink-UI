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
     * <p>
     *     -201	Errors involving data format issues, e.g.: data length error, date format error, etc.<br>
     *     -202	Data Content Error: data format is correct, but contents are invalid and the user needs to re-enter the data.<br>
     *     -203	Timeout: the input action exceeds the timeout.<br>
     *     -204	Protocol Error: Customized UI requested incorrect data from BroadPOS<br>
     *     -205	Index Error: Customized UI requested an incorrect index from BroadPOS<br>
     *     -206	Security Area Error: Customized UI requested incorrect security area from BroadPOS<br>
     * </p>
     */
    public static final String PARAM_CODE = "resultCode";

    /**
     * Result Message
     * <p>Type: String</p>
     */
    public static final String PARAM_MSG = "resultMessage";
}
