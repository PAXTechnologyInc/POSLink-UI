package com.pax.us.pay.ui.constant.status;


/**
 * Broadcast actions to be used while {@link com.pax.us.pay.ui.constant.entry.PoslinkEntry#CATEGORY} interfaces are running.
 */
public final class POSLinkStatus {
    private POSLinkStatus(){
        
    }
    /**
     * Broadcast Category: POSLINK<br>{@value #CATEGORY}<br>
     */
    public static final String CATEGORY = "com.pax.us.pay.status.category.POSLINK";

    /**
     * Clear message pushed from POSLink.
     */
    public static final String CLEAR_MESSAGE = "com.pax.us.pay.POSLINK_CLEAR_MESSAGE";

}
