package com.pax.us.pay.ui.constant.status;

public final class InformationStatus {
    public static final String CATEGORY = "com.pax.us.pay.status.category.INFORMATION";


    public static final String TRANS_START = "com.pax.us.pay.TRANS_START";
    /**
     * param: {@link StatusData#PARAM_CODE} <br>
     * param: {@link StatusData#PARAM_MSG} <br>
     * param: {@link StatusData#PARAM_EXT_MSG} <br>
     */
    public static final String TRANS_COMPLETED = "com.pax.us.pay.TRANS_COMPLETED";

    public static final String TRANS_ONLINE_STARTED = "com.pax.us.pay.TRANS_ONLINE_STARTED";
    public static final String TRANS_ONLINE_FINISHED = "com.pax.us.pay.TRANS_ONLINE_FINISHED";

    public static final String TRANS_REVERSAL_STARTED = "com.pax.us.pay.TRANS_REVERSAL_STARTED";
    public static final String TRANS_REVERSAL_FINISHED = "com.pax.us.pay.TRANS_REVERSAL_FINISHED";

    public static final String PINPAD_CONNECTION_STARTED = "com.pax.us.pay.PINPAD_CONNECTION_STARTED";
    public static final String PINPAD_CONNECTION_FINISHED = "com.pax.us.pay.PINPAD_CONNECTION_FINISHED";

    public static final String EMV_TRANS_ONLINE_STARTED = "com.pax.us.pay.EMV_TRANS_ONLINE_STARTED";
    public static final String EMV_TRANS_ONLINE_FINISHED = "com.pax.us.pay.EMV_TRANS_ONLINE_FINISHED";

    public static final String DCC_ONLINE_STARTED = "com.pax.us.pay.DCC_ONLINE_STARTED";
    public static final String DCC_ONLINE_FINISHED = "com.pax.us.pay.DCC_ONLINE_FINISHED";

    public static final String RKI_STARTED = "com.pax.us.pay.RKI_STARTED";
    public static final String RKI_FINISHED = "com.pax.us.pay.RKI_FINISHED";

    /*
     * let UI app display some information while enter PIN
     */
    public static final String ENTER_PIN_STARTED = "com.pax.us.pay.ENTER_PIN_STARTED";
    public static final String ENTER_PIN_FINISHED = "com.pax.us.pay.ENTER_PIN_FINISHED";

    /**
     * param: {@link StatusData#PARAM_CODE} <br>
     * param: {@link StatusData#PARAM_MSG} <br>
     */
    public static final String ERROR = "com.pax.us.pay.ERROR";

    /**
     * param: {@link StatusData#PARAM_TOTAL_AMOUNT} <br>
     */
    public static final String TRANS_AMOUNT_CHANGED_IN_CARD_PROCESSING = "com.pax.us.pay.AMOUNT_CHANGED_IN_CARD_PROCESSING";

}
