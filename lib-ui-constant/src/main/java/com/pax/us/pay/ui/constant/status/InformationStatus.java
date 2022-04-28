package com.pax.us.pay.ui.constant.status;

/**
 * define Broadcast which has INFORMATION Category
 */
public final class InformationStatus {
    private InformationStatus(){
        
    }

    /**
     * Broadcast Category: INFORMATION
     * 
     */
    public static final String CATEGORY = "com.pax.us.pay.status.category.INFORMATION";


    /**
     * Broadcast Action: Transaction Start
     */
    public static final String TRANS_START = "com.pax.us.pay.TRANS_START";
    /**
     * Broadcast Action: Transaction Completed
     * <p>
     *     Input: {@link StatusData#PARAM_CODE} result code</p>
     * <p>
     *     Input: {@link StatusData#PARAM_MSG} result message</p>
     * <p>
     *     Input: {@link StatusData#PARAM_EXT_MSG} BroadPOS use only</p>
     */
    public static final String TRANS_COMPLETED = "com.pax.us.pay.TRANS_COMPLETED";

    /**
     * Broadcast Action: Online process begin
     * <p>"Processing, Please Wait..."</p>
     */
    public static final String TRANS_ONLINE_STARTED = "com.pax.us.pay.TRANS_ONLINE_STARTED";

    /**
     * Broadcast Action: Online process end
     */
    public static final String TRANS_ONLINE_FINISHED = "com.pax.us.pay.TRANS_ONLINE_FINISHED";

    /**
     * Broadcast Action: Reversal Process begin
     * <p>"Reversal..."</p>
     * @deprecated Not used any more(Ticket BPOSANDAPP-492}
     */
    public static final String TRANS_REVERSAL_STARTED = "com.pax.us.pay.TRANS_REVERSAL_STARTED";
    /**
     * Broadcast Action: Reversal Process end
     * @deprecated Not used any more(Ticket BPOSANDAPP-492}
     */
    public static final String TRANS_REVERSAL_FINISHED = "com.pax.us.pay.TRANS_REVERSAL_FINISHED";

    /**
     * Broadcast Action: Connect PINPAD Process begin
     * <p>"Connecting to PINPAD..."</p>
     */
    public static final String PINPAD_CONNECTION_STARTED = "com.pax.us.pay.PINPAD_CONNECTION_STARTED";

    /**
     * Broadcast Action: Connect PINPAD Process end
     */
    public static final String PINPAD_CONNECTION_FINISHED = "com.pax.us.pay.PINPAD_CONNECTION_FINISHED";

    /**
     * Broadcast Action: Emv Trans Online Process begin
     * <p>When QuickChip is enabled, use {@link #TRANS_ONLINE_STARTED}</p>
     * <p>"Do Not Remove Your Card Please!\nProcessing Online&#8230;"</p>
     */
    public static final String EMV_TRANS_ONLINE_STARTED = "com.pax.us.pay.EMV_TRANS_ONLINE_STARTED";

    /**
     * Broadcast Action: Emv Trans Online Process end
     * <p>When QuickChip is enabled, use {@link #TRANS_ONLINE_FINISHED}</p>
     */
    public static final String EMV_TRANS_ONLINE_FINISHED = "com.pax.us.pay.EMV_TRANS_ONLINE_FINISHED";

    /**
     * Broadcast Action: DCC Process begin
     * <p>DCC is dynamic currency conversion</p>
     * <p>"DCC Request Processing..."</p>
     */
    public static final String DCC_ONLINE_STARTED = "com.pax.us.pay.DCC_ONLINE_STARTED";
    /**
     * Broadcast Action: DCC Process end
     */
    public static final String DCC_ONLINE_FINISHED = "com.pax.us.pay.DCC_ONLINE_FINISHED";

    /**
     * Broadcast Action: RKI Process begin
     * <p>"Remote Key Injection, Processing..."</p>
     */
    public static final String RKI_STARTED = "com.pax.us.pay.RKI_STARTED";

    /**
     * Broadcast Action: RKI Process end
     */
    public static final String RKI_FINISHED = "com.pax.us.pay.RKI_FINISHED";


    /**
     * Broadcast Action: General Error
     * <p>
     *     Input: {@link StatusData#PARAM_CODE} errorCode</p>
     * <p>
     *     Input: {@link StatusData#PARAM_MSG} errorMessage</p>
     */
    public static final String ERROR = "com.pax.us.pay.ERROR";

    /**
     * Broadcast Action: Update amount during card processing
     * <p>
     *     Input: {@link StatusData#PARAM_TOTAL_AMOUNT}</p>
     */
    public static final String TRANS_AMOUNT_CHANGED_IN_CARD_PROCESSING = "com.pax.us.pay.AMOUNT_CHANGED_IN_CARD_PROCESSING";

}
