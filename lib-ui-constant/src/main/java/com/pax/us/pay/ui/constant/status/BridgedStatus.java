package com.pax.us.pay.ui.constant.status;

/**
 * define Broadcast which has BRIDGED Category
 */
public final class BridgedStatus {

    private BridgedStatus(){

    }

    /**
     * Broadcast Category: BRIDGED<br>{@value #CATEGORY}<br>
     * <p>
     *     Used for Devices integrate an independent payment device as its payment component
     *     Such as M serial and L serial etc, with BroadPOS Lite Application
     * </p>
     */
    public static final String CATEGORY = "com.pax.us.pay.status.category.BRIDGED";

    /**
     * Broadcast Action: See Phone (Used by Mxx)<br>{@value #SEE_PHONE}<br>
     * <p>
     *     Input: {@link StatusData#PARAM_PROMPTS} <br>
     *     Type: String
     * </p>
     * Actually now BroadPOS use {@link com.pax.us.pay.ui.constant.entry.ConfirmationEntry#ACTION_CONFIRM_CARD_PROCESS_RESULT} cuz it could know the dialog has closed and ready for next step.<br>
     * But for BroadPOS Lite, it will use this action for prompting error.
     */
    public static final String SEE_PHONE = "com.pax.us.pay.SEE_PHONE";

    /**
     * Broadcast Action: Update Search Mode (Used by Mxx/L1400 only)<br>{@value #CARD_SEARCH_MODE}<br>
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

    /**
     * Broadcast Action: Enter PIN Process start (Used by Mxx only)<br>{@value #ENTER_PIN_STARTED}<br>
     */
    public static final String ENTER_PIN_STARTED = "com.pax.us.pay.ENTER_PIN_STARTED";
    /**
     * Broadcast Action: Enter PIN Process end (Used by Mxx only)<br>{@value #ENTER_PIN_FINISHED}<br>
     */
    public static final String ENTER_PIN_FINISHED = "com.pax.us.pay.ENTER_PIN_FINISHED";

}
