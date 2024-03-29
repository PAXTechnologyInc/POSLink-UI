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
