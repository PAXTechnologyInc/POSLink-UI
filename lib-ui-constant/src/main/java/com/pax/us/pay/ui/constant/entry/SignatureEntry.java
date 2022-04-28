package com.pax.us.pay.ui.constant.entry;

//TODO Signature Data format

/**
 * define Activity for SIGNATURE
 */
public final class SignatureEntry {
    /**
     * Activity Category: SIGNATURE
     */
    public static final String CATEGORY = "com.pax.us.pay.ui.category.SIGNATURE";
    /**
     * Activity Action: Signature
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_TYPE} is trans name. <br>
     *     Type: String<br>
     *     Example: "CREDIT SALE"
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_MODE} is transaction mode.<br>
     *     Type: String<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.TransMode} for details
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_CURRENCY} is currency type. <br>
     *     Type: String<br>
     *     Default value: {@link com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType#USD}
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_TOTAL_AMOUNT} <br>
     *     Type: Long
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000.
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_ENABLE_CANCEL} specifies visibility of "CANCEL" button<br>
     *     Type: Boolean<br>
     *     If TRUE, "CANCEL" button is visible.
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_SIGNLINE1} is Signature Prompt Line 1<br>
     *     Example: "Transaction Approved"
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_SIGNLINE2} is Signature Prompt Line 2<br>
     *     Example: "Please Sign Your Name"
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_SIGNATURE} Example: </p>
     */
    public static final String ACTION_SIGNATURE = "com.pax.us.pay.action.GET_SIGNATURE";


}
