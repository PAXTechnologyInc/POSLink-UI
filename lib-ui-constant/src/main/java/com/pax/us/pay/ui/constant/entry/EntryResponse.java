package com.pax.us.pay.ui.constant.entry;

public class EntryResponse {

    /**
     * ACCEPTED: BroadPOS accepted request action from your app.
     */
    public static final String ACTION_ACCEPTED = "com.pax.us.pay.ui.ACCEPTED";

    /**
     * DECLINED with reason: BroadPOS declined request action with result code, result message.
     */
    public static final String ACTION_DECLINED = "com.pax.us.pay.ui.DECLINED";

    public static final String PARAM_CODE = "resultCode";
    public static final String PARAM_MSG = "resultMessage";
}
