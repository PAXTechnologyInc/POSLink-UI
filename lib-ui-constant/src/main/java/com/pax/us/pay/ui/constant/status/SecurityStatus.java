package com.pax.us.pay.ui.constant.status;


/**
 * define Broadcast which has SECURITY Category
 */
public final class SecurityStatus {
    private SecurityStatus(){
        
    }
    /**
     * Broadcast Category: SECURITY<br>{@value #CATEGORY}<br>
     */
    public static final String CATEGORY = "com.pax.us.pay.status.category.SECURITY";

    /**
     * Broadcast Action: specifies security input is doing INPUT.<br>{@value #SECURITY_ENTERING}<br>
     * <p>The length of input should add 1.</p>
     */
    public static final String SECURITY_ENTERING = "com.pax.us.pay.SECURITY_ENTERING";

    /**
     * Broadcast Action: specifies security input is doing DELETE.<br>{@value #SECURITY_ENTER_DELETE}<br>
     * <p>The length of input should minus 1.</p>
     */
    public static final String SECURITY_ENTER_DELETE = "com.pax.us.pay.SECURITY_DELETE";

    /**
     * Broadcast Action: specifies security input is CLEARED.<br>{@value #SECURITY_ENTER_CLEARED}<br>
     * <p>The length of input is 0.</p>
     */
    public static final String SECURITY_ENTER_CLEARED = "com.pax.us.pay.SECURITY_CLEARED";

}
