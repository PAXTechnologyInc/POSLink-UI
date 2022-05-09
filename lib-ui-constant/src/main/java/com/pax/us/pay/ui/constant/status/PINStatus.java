package com.pax.us.pay.ui.constant.status;

/**
 * define Broadcast which has PIN Category
 */
public final class PINStatus {
    private PINStatus(){

    }

    /**
     * Broadcast Category: PIN<br>{@value #CATEGORY}<br>
     */
    public static final String CATEGORY = "com.pax.us.pay.status.category.PIN";

    /**
     * Broadcast Action: PIN_ENTERING<br>{@value #PIN_ENTERING}<br>
     * <p>PIN Input Box should append 1 character &#42;</p>
     */
    public static final String PIN_ENTERING = "com.pax.us.pay.PIN_ENTERING";

    /**
     * Broadcast Action: PIN_CLEARED<br>{@value #PIN_ENTER_CLEARED}<br>
     * <p>KEY_CLEAR is clicked. PIN Input Box should delete 1 character</p>
     */
    public static final String PIN_ENTER_CLEARED = "com.pax.us.pay.PIN_CLEARED";

    /**
     * Broadcast Action: PIN_ENTER_COMPLETED<br>{@value #PIN_ENTER_COMPLETED}<br>
     * @deprecated Never used
     */
    public static final String PIN_ENTER_COMPLETED = "com.pax.us.pay.PIN_ENTER_COMPLETED";

    /**
     * Broadcast Action: PIN_ENTER_ABORTED<br>{@value #PIN_ENTER_ABORTED}<br>
     * @deprecated Never used
     */
    public static final String PIN_ENTER_ABORTED = "com.pax.us.pay.PIN_ENTER_ABORTED";

}
