package com.pax.us.pay.ui.constant.entry;

/**
 * define Activity for DETAILS
 */
public final class InformationEntry {
    private InformationEntry(){
        
    }

    /**
     * Activity Category: DETAILS<br>{@value #CATEGORY}<br>
     */
    public static final String CATEGORY = "com.pax.us.pay.ui.category.DETAILS";

    /**
     * Activity Action: Display Transaction Information<br>{@value #ACTION_DISPLAY_TRANS_INFORMATION}<br>
     * <p>
     *     For Void/PostAuth/Adjust transaction use.
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} - {@value EntryExtraData#PARAM_PACKAGE}  is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_MODE} - {@value EntryExtraData#PARAM_TRANS_MODE} is transaction mode.<br>
     *     Type: String<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.TransMode} for details
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_TYPE} - {@value EntryExtraData#PARAM_TRANS_TYPE} is trans name. <br>
     *     Type: String<br>
     *     Example: "CREDIT SALE"
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} - {@value EntryExtraData#PARAM_TIMEOUT}<br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000. <br>
     *     For reference only, BroadPOS handles timeout.
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_INFORMATION_KEY} - {@value EntryExtraData#PARAM_INFORMATION_KEY} <br>
     *     Type:String[]<br>
     *     Left Column
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_INFORMATION_VALUE} - {@value EntryExtraData#PARAM_INFORMATION_VALUE} <br>
     *     Type:String[]
     *     Right Column
     * </p>
     * Example:
     * <p>
     *     If {@value EntryExtraData#PARAM_INFORMATION_KEY}  is {"Trans. Mode:","Trans. Number:","Trans. Date:", ...},<br>
     *     and {@value EntryExtraData#PARAM_INFORMATION_VALUE} is {"Normal","1","2022/05/01", ...},<br>
     *     Then the UI shows:<br>
     *     "Trans. Mode:          Normal"<br>
     *     "Trans. Number:            1"<br>
     *     "Trans. Date:      2022/05/01"<br>
     *     ...
     * </p>
     */
    public static final String ACTION_DISPLAY_TRANS_INFORMATION = "com.pax.us.pay.action.DISPLAY_TRANS_INFORMATION";


    /**
     * Activity Action: Play Approval Sound<br>{@value #ACTION_DISPLAY_APPROVE_MESSAGE}<br>
     * <p>
     *     This action request nothing. You can request go to next step like this:
     * </p>
     * <pre>
     *     Bundle bundle = new Bundle();
     *     bundle.putStringExtra(EntryRequest.PARAM_ACTION, InformationEntry.ACTION_DISPLAY_APPROVE_MESSAGE);
     *     Intent intent = new Intent();
     *     intent.setPackage(broadPOSPackage); //broadPOSPackage is from {@link EntryExtraData#PARAM_PACKAGE}
     *     intent.setAction(EntryRequest.ACTION_NEXT);
     *     intent.putExtras(bundle);
     *     context.sendBroadcast(intent);
     * </pre>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} - {@value EntryExtraData#PARAM_PACKAGE}  is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_MODE} - {@value EntryExtraData#PARAM_TRANS_MODE} is transaction mode.<br>
     *     Type: String<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.TransMode} for details
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_TYPE} - {@value EntryExtraData#PARAM_TRANS_TYPE} is trans name. <br>
     *     Type: String<br>
     *     Example: "CREDIT SALE"
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_CARD_TYPE} - {@value EntryExtraData#PARAM_CARD_TYPE} <br>
     *     Type: String<br>
     *     If card type is {@link com.pax.us.pay.ui.constant.entry.enumeration.CardType#VISA}, need play visa animation and visa sound.<br>
     *     Else, play sound specified by {@link EntryExtraData#PARAM_SOUND_URI}
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_ANIMATION_SUPPORT} - {@value EntryExtraData#PARAM_ANIMATION_SUPPORT} <br>
     *     Type: Boolean<br>
     *     TRUE is play animation.<br>
     *     Only works for Visa card.
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_SOUND_SUPPORT} - {@value EntryExtraData#PARAM_SOUND_SUPPORT} <br>
     *     Type: Boolean<br>
     *     TRUE is play sound.
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_SOUND_URI} - {@value EntryExtraData#PARAM_SOUND_URI} <br>
     *     Type: String<br>
     *     For other card types use.
     * </p>
     * Resource files:<br>
     * 1.<a href="doc-files/visa_animation.gif">Animation for Visa Approval: visa_animation.gif</a><br>
     * 2.<a href="doc-files/visa_sound.wav">Sound for Visa Approval: visa_sound.wav</a><br>
     * 3.<a href="doc-files/boba.wav">Default Sound for other approval: boba.wav</a>
     */
    public static final String ACTION_DISPLAY_APPROVE_MESSAGE = "com.pax.us.pay.action.DISPLAY_APPROVE_MESSAGE";

//    /**
//     * Activity Action: Display Visa Installment Transaction Approved message <br>
//     * <p>
//     *     Input: {@link EntryExtraData#PARAM_PACKAGE} - {@value EntryExtraData#PARAM_PACKAGE}  is the package name of caller.<br>
//     *     Type: String
//     * </p>
//     * <p>
//     *     Input: {@link EntryExtraData#PARAM_TRANS_MODE} - {@value EntryExtraData#PARAM_TRANS_MODE} is transaction mode.<br>
//     *     Type: String<br>
//     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.TransMode} for details
//     * </p>
//     * <p>
//     *     Input: {@link EntryExtraData#PARAM_TRANS_TYPE} - {@value EntryExtraData#PARAM_TRANS_TYPE} is trans name. <br>
//     *     Type: String<br>
//     *     Example: "CREDIT SALE"
//     * </p>
//     * <p>
//     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} - {@value EntryExtraData#PARAM_MESSAGE}<br>
//     *     Type: String
//     * </p>
//     * @deprecated Never used. Now the end ui is shown on Trans completed. See {@link com.pax.us.pay.ui.constant.status.StatusData#PARAM_DISPLAY_VISA_INSTALLMENT_APPROVAL} instead.
//     */
//    public static final String ACTION_DISPLAY_VISA_INSTALLMENT_TRANSACTION_END = "com.pax.us.pay.action.DISPLAY_VISA_INSTALLMENT_TRANSACTION_END";

}
