package com.pax.us.pay.ui.constant.entry;

public class EntryResponse {

    /**
     * ACCEPTED: BroadPOS accepted request action from your app. <br>
     */
    public static final String ACTION_ACCEPTED = "com.pax.us.pay.ui.ACCEPTED";

    /**
     * DECLINED with reason: BroadPOS declined request action with result code, result message. <br>
     * param: {@link EntryResponse#PARAM_CODE} <br>
     * param: {@link EntryResponse#PARAM_MSG} <br>
     */
    public static final String ACTION_DECLINED = "com.pax.us.pay.ui.DECLINED";

    /**
     * PARAM_CODE : BroadPOS response with result code <br>
     * value type: long <br>
     */
    public static final String PARAM_CODE = "resultCode";

    /**
     * PARAM_MSG : BroadPOS response with result message <br>
     * value type: String <br>
     */
    public static final String PARAM_MSG = "resultMessage";
}
