package com.pax.us.pay.ui.constant.entry.enumeration;

/**
 * Search Criteria
 */
public final class SearchCriteria {
    private SearchCriteria(){

    }
    /**
     * By Trans. No.
     */
    public static final String BY_TRANS_NUMBER = "BY TRANS NUMBER";

    /**
     * By Card No.
     */
    public static final String BY_LAST_4_DIGITS = "BY LAST 4 DIGITS";

    /**
     * By Invoice No.
     */
    public static final String BY_INVOICE_NUMBER = "BY INVOICE NUMBER";

    /**
     * By Card Type
     */
    public static final String BY_CARD_TYPE = "BY CARD TYPE";

    /**
     * By Operator ID
     */
    public static final String BY_CLERK_SERVER_ID = "BY CLERK SERVER ID";

    /**
     * View Untipped Trans.
     */
    public static final String BY_UNTIPPED = "SEARCH UNTIPPED";

    /**
     * By Ref. No.
     */
    public static final String BY_REFERENCE_NUMBER = "BY REFERENCE NUMBER";

    /**
     * @deprecated Never used
     */
    public static final String BY_STORE_FORWARD = "SEARCH STORE FORWARD";

    /**
     * @deprecated Never used
     */
    public static final String BY_UPLOAD_TRANS = "SEARCH UPLOAD TRANSACTION";

    /**
     * @deprecated Never used
     */
    public static final String BY_RESENT_FAILED = "SEARCH RE-SENT FAILED";

}
