package com.pax.us.pay.ui.constant.status;

/**
 * define Broadcast which has PIN Category
 */
public final class PINStatus {
    private PINStatus(){

    }

    /**
     * Broadcast Category: PIN
     */
    public static final String CATEGORY = "com.pax.us.pay.status.category.PIN";

    /**
     * Broadcast Action: PIN_ENTERING
     * <p>PIN Input Box should append a character &#42;</p>
     */
    public static final String PIN_ENTERING = "com.pax.us.pay.PIN_ENTERING";

    /**
     * Broadcast Action: PIN_CLEARED
     * <p>PIN Input Box should clear all text</p>
     */
    public static final String PIN_ENTER_CLEARED = "com.pax.us.pay.PIN_CLEARED";

    /**
     * Broadcast Action: PIN_ENTER_COMPLETED
     * @deprecated Never Used
     */
    public static final String PIN_ENTER_COMPLETED = "com.pax.us.pay.PIN_ENTER_COMPLETED";

    /**
     * Broadcast Action: PIN_ENTER_ABORTED
     * @deprecated Never Used
     */
    public static final String PIN_ENTER_ABORTED = "com.pax.us.pay.PIN_ENTER_ABORTED";

}
