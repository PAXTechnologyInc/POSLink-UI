package com.pax.us.pay.ui.constant.status;

/**
 * define Broadcast which has CARD Category
 */
public final class CardStatus {
    private CardStatus(){

    }
    /**
     * Broadcast Category: CARD<br>{@value #CATEGORY}<br>
     */
    public static final String CATEGORY = "com.pax.us.pay.status.category.CARD";

    /**
     * Broadcast Action: EMV Process begin<br>{@value #CARD_PROCESS_STARTED}<br>
     * <p>"EMV Process Start..."</p>
     */
    public static final String CARD_PROCESS_STARTED = "com.pax.us.pay.CARD_PROCESS_STARTED";
    /**
     * Broadcast Action: EMV Process end<br>{@value #CARD_PROCESS_COMPLETED}<br>
     */
    public static final String CARD_PROCESS_COMPLETED = "com.pax.us.pay.CARD_PROCESS_COMPLETED";

    /**
     * Broadcast Action: EMV Process error<br>{@value #CARD_PROCESS_ERROR}<br>
     * @deprecated Not used anymore. Use {@link com.pax.us.pay.ui.constant.entry.ConfirmationEntry#ACTION_CONFIRM_CARD_PROCESS_RESULT} instead.
     */
    public static final String CARD_PROCESS_ERROR = "com.pax.us.pay.CARD_PROCESS_ERROR";

    /**
     * Broadcast Action: Card Inserted<br>{@value #CARD_INSERTED}<br>
     */
    public static final String CARD_INSERTED = "com.pax.us.pay.CARD_INSERTED";

    /**
     * Broadcast Action: Card Swiped<br>{@value #CARD_SWIPED}<br>
     */
    public static final String CARD_SWIPED = "com.pax.us.pay.CARD_SWIPED";
    /**
     * Broadcast Action: Card Tapped<br>{@value #CARD_TAPPED}<br>
     */
    public static final String CARD_TAPPED = "com.pax.us.pay.CARD_TAPPED";

    /**
     * Broadcast Action: require insert card<br>{@value #CARD_INSERT_REQUIRED}<br>
     * <p>"Please Insert Chip Card!"</p>
     */
    public static final String CARD_INSERT_REQUIRED = "com.pax.us.pay.CARD_INSERT_REQUIRED";

    /**
     * Broadcast Action: require tap card<br>{@value #CARD_TAP_REQUIRED}<br>
     * <p>"Please Tap Card!"</p>
     */
    public static final String CARD_TAP_REQUIRED = "com.pax.us.pay.CARD_TAP_REQUIRED";

    /**
     * Broadcast Action: require swipe card<br>{@value #CARD_SWIPE_REQUIRED}<br>
     * <p>"Please Swipe Card!"</p>
     */
    public static final String CARD_SWIPE_REQUIRED = "com.pax.us.pay.CARD_SWIPE_REQUIRED";

    /**
     * Broadcast Action: require swipe card<br>{@value #CARD_REMOVAL_REQUIRED}<br>
     * <p>"Please Remove Card!"</p>
     */
    public static final String CARD_REMOVAL_REQUIRED = "com.pax.us.pay.CARD_REMOVAL_REQUIRED";
    /**
     * Broadcast Action: require swipe card<br>{@value #CARD_QUICK_REMOVAL_REQUIRED}<br>
     * <p>"Please Remove Card Quickly!"</p>
     */
    public static final String CARD_QUICK_REMOVAL_REQUIRED = "com.pax.us.pay.CARD_QUICK_REMOVAL_REQUIRED";

    /**
     * Broadcast Action: card removed<br>{@value #CARD_REMOVED}<br>
     */
    public static final String CARD_REMOVED = "com.pax.us.pay.CARD_REMOVED";

    /**
     * Broadcast Action: See Phone (Used by EasyLink only)<br>{@value #SEE_PHONE}<br>
     * <p>
     *     Input: {@link StatusData#PARAM_PROMPTS} <br>
     *     Type: String
     * </p>
     * Actually now BroadPOS use {@link com.pax.us.pay.ui.constant.entry.ConfirmationEntry#ACTION_CONFIRM_CARD_PROCESS_RESULT} cuz it could know the dialog has closed and ready for next step.<br>
     * But for BroadPOS+, it will use this action for prompting error.
     */
    public static final String SEE_PHONE = "com.pax.us.pay.SEE_PHONE";

    /**
     * Broadcast Action: Update Search Mode (Used by EasyLink only)<br>{@value #CARD_SEARCH_MODE}<br>
     * <p>
     * Input: {@link StatusData#PARAM_ENABLE_SWIPE} <br>
     * Type: Boolean
     *  </p>
     *  <p>
     * Input: {@link StatusData#PARAM_ENABLE_INSERT} <br>
     * Type: Boolean
     *  </p>
     *  <p>
     * Input: {@link StatusData#PARAM_ENABLE_TAP} <br>
     * Type: Boolean
     *  </p>
     *  <p>
     * Input: {@link StatusData#PARAM_ENABLE_MANUAL} <br>
     * Type: Boolean
     *  </p>
     */
    public static final String CARD_SEARCH_MODE = "com.pax.us.pay.CARD_SEARCH_MODE";


}
