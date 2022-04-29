package com.pax.us.pay.ui.constant.status;

/**
 * define Broadcast which has VIRTUAL_CLSS_LIGHT Category
 * <p>Control contactless lights</p>
 */
public final class ClssLightStatus {
    private ClssLightStatus(){

    }
    /**
     * Broadcast Category: VIRTUAL_CLSS_LIGHT
     *
     */
    public static final String CATEGORY = "com.pax.us.pay.status.category.VIRTUAL_CLSS_LIGHT";

//    /**
//     * Broadcast Action: Blue light ON
//     */
//    public static final String CLSS_LIGHT_BLUE_ON = "com.pax.us.pay.CLSS_LIGHT_BLUE_ON";
//    /**
//     * Broadcast Action: Blue light OFF
//     */
//    public static final String CLSS_LIGHT_BLUE_OFF = "com.pax.us.pay.CLSS_LIGHT_BLUE_OFF";
//    /**
//     * Broadcast Action: Blue light BLINK
//     */
//    public static final String CLSS_LIGHT_BLUE_BLINK = "com.pax.us.pay.CLSS_LIGHT_BLUE_BLINK";
//
//    /**
//     * Broadcast Action: Yellow light ON
//     */
//    public static final String CLSS_LIGHT_YELLOW_ON = "com.pax.us.pay.CLSS_LIGHT_YELLOW_ON";
//    /**
//     * Broadcast Action: Yellow light OFF
//     */
//    public static final String CLSS_LIGHT_YELLOW_OFF = "com.pax.us.pay.CLSS_LIGHT_YELLOW_OFF";
//    /**
//     * Broadcast Action: Yellow light BLINK
//     */
//    public static final String CLSS_LIGHT_YELLOW_BLINK = "com.pax.us.pay.CLSS_LIGHT_YELLOW_BLINK";
//
//    /**
//     * Broadcast Action: Green light ON
//     */
//    public static final String CLSS_LIGHT_GREEN_ON = "com.pax.us.pay.CLSS_LIGHT_GREEN_ON";
//    /**
//     * Broadcast Action: Green light OFF
//     */
//    public static final String CLSS_LIGHT_GREEN_OFF = "com.pax.us.pay.CLSS_LIGHT_GREEN_OFF";
//    /**
//     * Broadcast Action: Green light BLINK
//     */
//    public static final String CLSS_LIGHT_GREEN_BLINK = "com.pax.us.pay.CLSS_LIGHT_GREEN_BLINK";
//
//    /**
//     * Broadcast Action: Red light ON
//     */
//    public static final String CLSS_LIGHT_RED_ON = "com.pax.us.pay.CLSS_LIGHT_RED_ON";
//    /**
//     * Broadcast Action: Red light OFF
//     */
//    public static final String CLSS_LIGHT_RED_OFF = "com.pax.us.pay.CLSS_LIGHT_RED_OFF";
//    /**
//     * Broadcast Action: Red light BLINK
//     */
//    public static final String CLSS_LIGHT_RED_BLINK = "com.pax.us.pay.CLSS_LIGHT_RED_BLINK";

    /**
     * Broadcast Action: Contactless Light - not ready
     * <p>
     *     All lights OFF
     * </p>
     */
    public static final String CLSS_LIGHT_NOT_READY = "com.pax.us.pay.CLSS_LIGHT_NOT_READY";

    /**
     * Broadcast Action: Contactless Light - idle
     * <p>
     *     BLUE light BLINK. Other lights OFF.
     * </p>
     */
    public static final String CLSS_LIGHT_IDLE = "com.pax.us.pay.CLSS_LIGHT_IDLE";

    /**
     * Broadcast Action: Contactless Light - ready for transaction
     * <p>
     *     BLUE light ON. Other lights OFF
     * </p>
     */
    public static final String CLSS_LIGHT_READY_FOR_TXN = "com.pax.us.pay.CLSS_LIGHT_READY_FOR_TXN";

    /**
     * Broadcast Action: Contactless Light - processing
     * <p>
     *     BLUE light ON. YELLOW light ON, Other lights OFF.
     * </p>
     */
    public static final String CLSS_LIGHT_PROCESSING = "com.pax.us.pay.CLSS_LIGHT_PROCESSING";

    /**
     * Broadcast Action: Contactless Light - remove card
     * <p>
     *    RED light OFF. Other lights ON
     * </p>
     */
    public static final String CLSS_LIGHT_REMOVE_CARD = "com.pax.us.pay.CLSS_LIGHT_REMOVE_CARD";

    /**
     * Broadcast Action: Contactless Light - complete
     * <p>
     *    All lights OFF
     * </p>
     */
    public static final String CLSS_LIGHT_COMPLETED = "com.pax.us.pay.CLSS_LIGHT_COMPLETED";

    /**
     * Broadcast Action: Contactless Light - error
     * <p>
     *     RED light ON. Other lights OFF
     * </p>
     */
    public static final String CLSS_LIGHT_ERROR = "com.pax.us.pay.CLSS_LIGHT_ERROR";
}
