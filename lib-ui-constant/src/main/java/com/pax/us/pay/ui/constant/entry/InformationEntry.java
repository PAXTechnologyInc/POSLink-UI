package com.pax.us.pay.ui.constant.entry;

/**
 * define Activity for DETAILS
 */
public final class InformationEntry {
    private InformationEntry(){
        
    }

    /**
     * Activity Category: DETAILS
     */
    public static final String CATEGORY = "com.pax.us.pay.ui.category.DETAILS";

    /**
     * Activity Action: Display Transaction Information
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_MODE} is transaction mode.<br>
     *     Type: String<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.TransMode} for details
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_TYPE} is trans name. <br>
     *     Type: String<br>
     *     Example: "CREDIT SALE"
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000.
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_INFORMATION_KEY} <br>
     *     Type:String[]
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_INFORMATION_VALUE} <br>
     *     Type:String[]
     * </p>
     */
    public static final String ACTION_DISPLAY_TRANS_INFORMATION = "com.pax.us.pay.action.DISPLAY_TRANS_INFORMATION";


    /**
     * Activity Action: Play Approval Sound
     * <p>
     *     This action request nothing. You can request go to next step like this:
     * </p>
     * <pre>
     *     Intent intent = new Intent();
     *     intent.setPackage(broadPOSPackage); //broadPOSPackage is from {@link EntryExtraData#PARAM_PACKAGE}
     *     intent.setAction(EntryRequest.ACTION_NEXT);
     *     context.sendBroadcast(intent);
     * </pre>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_MODE} is transaction mode.<br>
     *     Type: String<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.TransMode} for details
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_TYPE} is trans name. <br>
     *     Type: String<br>
     *     Example: "CREDIT SALE"
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_CARD_TYPE} <br>
     *     Type: String<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.CardType} for details
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_ANIMATION_SUPPORT} <br>
     *     Type: Boolean<br>
     *     TRUE is play animation
     *     Only works for Visa card<br>
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_SOUND_SUPPORT} <br>
     *     Type: Boolean<br>
     *     TRUE is play sound.
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_SOUND_URI} <br>
     *     Type: String
     * </p>
     */
    public static final String ACTION_DISPLAY_APPROVE_MESSAGE = "com.pax.us.pay.action.DISPLAY_APPROVE_MESSAGE";

    /**
     * Activity Action: Display Visa Installment Transaction Approved message <br>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_MODE} is transaction mode.<br>
     *     Type: String<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.TransMode} for details
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_TYPE} is trans name. <br>
     *     Type: String<br>
     *     Example: "CREDIT SALE"
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE}<br>
     *     Type: String
     * </p>
     * @deprecated Never used. Use {@link com.pax.us.pay.ui.constant.status.StatusData#PARAM_DISPLAY_VISA_INSTALLMENT_APPROVAL} instead.
     */
    public static final String ACTION_DISPLAY_VISA_INSTALLMENT_TRANSACTION_END = "com.pax.us.pay.action.DISPLAY_VISA_INSTALLMENT_TRANSACTION_END";

}
