package com.pax.us.pay.ui.constant.status;

public final class CardStatus {
    public static final String CATEGORY = "com.pax.us.pay.status.category.CARD";

    public static final String CARD_PROCESS_STARTED = "com.pax.us.pay.CARD_PROCESS_STARTED";
    public static final String CARD_PROCESS_COMPLETED = "com.pax.us.pay.CARD_PROCESS_COMPLETED";
    public static final String CARD_PROCESS_ERROR = "com.pax.us.pay.CARD_PROCESS_ERROR";

    public static final String CARD_REMOVED = "com.pax.us.pay.CARD_REMOVED";

    public static final String CARD_INSERT_REQUIRED = "com.pax.us.pay.CARD_INSERT_REQUIRED";
    /**
     * Reserved for BroadPos application inner communication
     */
    public static final String CARD_INSERTED = "com.pax.us.pay.CARD_INSERTED";
    public static final String CARD_SWIPE_REQUIRED = "com.pax.us.pay.CARD_SWIPE_REQUIRED";
    /**
     * Reserved for BroadPos application inner communication
     */
    public static final String CARD_SWIPED = "com.pax.us.pay.CARD_SWIPED";
    public static final String CARD_TAP_REQUIRED = "com.pax.us.pay.CARD_TAP_REQUIRED";
    /**
     * Reserved for BroadPos application inner communication
     */
    public static final String CARD_TAPPED = "com.pax.us.pay.CARD_TAPPED";
    public static final String CARD_REMOVAL_REQUIRED = "com.pax.us.pay.CARD_REMOVAL_REQUIRED";
    public static final String CARD_QUICK_REMOVAL_REQUIRED = "com.pax.us.pay.CARD_QUICK_REMOVAL_REQUIRED";
    /**
     * param: {@link #PARAM_PROMPTS} <br>
     */
    public static final String SEE_PHONE = "com.pax.us.pay.SEE_PHONE";

    public static final String PARAM_PROMPTS = "prompts";
}
