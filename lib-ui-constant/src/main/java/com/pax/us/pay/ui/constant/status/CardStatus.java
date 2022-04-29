package com.pax.us.pay.ui.constant.status;

/**
 * define Broadcast which has CARD Category
 */
public final class CardStatus {
    private CardStatus(){

    }
    /**
     * Broadcast Category: CARD
     */
    public static final String CATEGORY = "com.pax.us.pay.status.category.CARD";

    /**
     * Broadcast Action: EMV Process begin
     * <p>"EMV Process Start..."</p>
     */
    public static final String CARD_PROCESS_STARTED = "com.pax.us.pay.CARD_PROCESS_STARTED";
    /**
     * Broadcast Action: EMV Process end
     */
    public static final String CARD_PROCESS_COMPLETED = "com.pax.us.pay.CARD_PROCESS_COMPLETED";
    /**
     * Broadcast Action: EMV Process error
     * @deprecated Use {@link com.pax.us.pay.ui.constant.entry.ConfirmationEntry#ACTION_CONFIRM_CARD_PROCESS_RESULT} instead.
     */
    public static final String CARD_PROCESS_ERROR = "com.pax.us.pay.CARD_PROCESS_ERROR";

    /**
     * Broadcast Action: card inserted
     * @deprecated BroadPOS Use Only
     */
    public static final String CARD_INSERTED = "com.pax.us.pay.CARD_INSERTED";

    /**
     * Broadcast Action: card swiped
     * @deprecated BroadPOS Use Only
     */
    public static final String CARD_SWIPED = "com.pax.us.pay.CARD_SWIPED";
    /**
     * Broadcast Action: card tapped
     * @deprecated BroadPOS Use Only
     */
    public static final String CARD_TAPPED = "com.pax.us.pay.CARD_TAPPED";

    /**
     * Broadcast Action: require insert card
     * <p>"Please Insert Chip Card!"</p>
     */
    public static final String CARD_INSERT_REQUIRED = "com.pax.us.pay.CARD_INSERT_REQUIRED";

    /**
     * Broadcast Action: require tap card
     * <p>"Please Tap Card!"</p>
     */
    public static final String CARD_TAP_REQUIRED = "com.pax.us.pay.CARD_TAP_REQUIRED";

    /**
     * Broadcast Action: require swipe card
     * <p>"Please Swipe Card!"</p>
     */
    public static final String CARD_SWIPE_REQUIRED = "com.pax.us.pay.CARD_SWIPE_REQUIRED";

    /**
     * Broadcast Action: require swipe card
     * <p>"Please Remove Card!"</p>
     */
    public static final String CARD_REMOVAL_REQUIRED = "com.pax.us.pay.CARD_REMOVAL_REQUIRED";
    /**
     * Broadcast Action: require swipe card
     * <p>"Please Remove Card Quickly!"</p>
     */
    public static final String CARD_QUICK_REMOVAL_REQUIRED = "com.pax.us.pay.CARD_QUICK_REMOVAL_REQUIRED";

    /**
     * Broadcast Action: card removed
     */
    public static final String CARD_REMOVED = "com.pax.us.pay.CARD_REMOVED";

    /**
     * Broadcast Action: See Phone
     * param: {@link #PARAM_PROMPTS} <br>
     * @deprecated Use {@link com.pax.us.pay.ui.constant.entry.ConfirmationEntry#ACTION_CONFIRM_CARD_PROCESS_RESULT} instead.
     */
    public static final String SEE_PHONE = "com.pax.us.pay.SEE_PHONE";

    /**
     * @deprecated never used?
     */
    public static final String PARAM_PROMPTS = "prompts";
}
