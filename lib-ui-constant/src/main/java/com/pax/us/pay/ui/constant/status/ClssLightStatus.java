package com.pax.us.pay.ui.constant.status;

/**
 * define Broadcast which has VIRTUAL_CLSS_LIGHT Category
 * <p>
 * <img src="doc-files/ctlss_light_status.JPG" alt = "Contactless Lights">
 * </p>
 */
public final class ClssLightStatus {
    private ClssLightStatus(){

    }
    /**
     * Broadcast Category: VIRTUAL_CLSS_LIGHT<br>{@value #CATEGORY}<br>
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
     * Broadcast Action: Contactless Light - not ready<br>{@value #CLSS_LIGHT_NOT_READY}<br>
     * <p>
     *     All lights OFF
     * </p>
     */
    public static final String CLSS_LIGHT_NOT_READY = "com.pax.us.pay.CLSS_LIGHT_NOT_READY";

    /**
     * Broadcast Action: Contactless Light - idle<br>{@value #CLSS_LIGHT_IDLE}<br>
     * <p>
     *     BLUE light BLINK. Other lights OFF.
     * </p>
     */
    public static final String CLSS_LIGHT_IDLE = "com.pax.us.pay.CLSS_LIGHT_IDLE";

    /**
     * Broadcast Action: Contactless Light - ready for transaction<br>{@value #CLSS_LIGHT_READY_FOR_TXN}<br>
     * <p>
     *     BLUE light ON. Other lights OFF
     * </p>
     */
    public static final String CLSS_LIGHT_READY_FOR_TXN = "com.pax.us.pay.CLSS_LIGHT_READY_FOR_TXN";

    /**
     * Broadcast Action: Contactless Light - processing<br>{@value #CLSS_LIGHT_PROCESSING}<br>
     * <p>
     *     BLUE light ON. YELLOW light ON, Other lights OFF.
     * </p>
     */
    public static final String CLSS_LIGHT_PROCESSING = "com.pax.us.pay.CLSS_LIGHT_PROCESSING";

    /**
     * Broadcast Action: Contactless Light - remove card<br>{@value #CLSS_LIGHT_REMOVE_CARD}<br>
     * <p>
     *    RED light OFF. Other lights ON
     * </p>
     */
    public static final String CLSS_LIGHT_REMOVE_CARD = "com.pax.us.pay.CLSS_LIGHT_REMOVE_CARD";

    /**
     * Broadcast Action: Contactless Light - complete<br>{@value #CLSS_LIGHT_COMPLETED}<br>
     * <p>
     *    All lights OFF
     * </p>
     */
    public static final String CLSS_LIGHT_COMPLETED = "com.pax.us.pay.CLSS_LIGHT_COMPLETED";

    /**
     * Broadcast Action: Contactless Light - error<br>{@value #CLSS_LIGHT_ERROR}<br>
     * <p>
     *     RED light ON. Other lights OFF
     * </p>
     */
    public static final String CLSS_LIGHT_ERROR = "com.pax.us.pay.CLSS_LIGHT_ERROR";
}
