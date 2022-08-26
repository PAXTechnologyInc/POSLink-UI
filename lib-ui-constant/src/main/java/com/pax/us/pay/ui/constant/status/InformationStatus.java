package com.pax.us.pay.ui.constant.status;

/**
 * define Broadcast which has INFORMATION Category
 */
public final class InformationStatus {
    private InformationStatus(){
        
    }

    /**
     * Broadcast Category: INFORMATION<br>{@value #CATEGORY}<br>
     * 
     */
    public static final String CATEGORY = "com.pax.us.pay.status.category.INFORMATION";


    /**
     * Broadcast Action: Transaction Start<br>{@value #TRANS_START}<br>
     */
    public static final String TRANS_START = "com.pax.us.pay.TRANS_START";
    /**
     * Broadcast Action: Transaction Completed<br>{@value #TRANS_COMPLETED}<br>
     * <p>
     *     Input: {@link StatusData#PARAM_CODE}<br>
     *     Type: String<br>
     *     result code</p>
     * <p>
     *     Input: {@link StatusData#PARAM_MSG} <br>
     *     Type: String<br>
     *     result message</p>
     * <p>
     *     Input: {@link StatusData#PARAM_EXT_MSG}<br>
     *     Type: String<br>
     *     Message which need shown on external device (like SP30s). For BroadPOS use only.
     * </p>
     * <p>
     *     Input: {@link StatusData#PARAM_HOST_RESP_TIMEOUT}<br>
     *     Type: Long<br>
     *     Default: 2000<br>
     * </p>
     */
    public static final String TRANS_COMPLETED = "com.pax.us.pay.TRANS_COMPLETED";

    /**
     * Broadcast Action: Online process begin<br>{@value #TRANS_ONLINE_STARTED}<br>
     * <p>"Processing, Please Wait..."</p>
     */
    public static final String TRANS_ONLINE_STARTED = "com.pax.us.pay.TRANS_ONLINE_STARTED";

    /**
     * Broadcast Action: Online process end<br>{@value #TRANS_ONLINE_FINISHED}<br>
     */
    public static final String TRANS_ONLINE_FINISHED = "com.pax.us.pay.TRANS_ONLINE_FINISHED";

    /**
     * Broadcast Action: Reversal Process begin<br>{@value #TRANS_REVERSAL_STARTED}<br>
     * <p>"Reversal..."</p>
     * @deprecated Not used anymore(Ticket BPOSANDAPP-492}
     */
    public static final String TRANS_REVERSAL_STARTED = "com.pax.us.pay.TRANS_REVERSAL_STARTED";
    /**
     * Broadcast Action: Reversal Process end<br>{@value #TRANS_REVERSAL_FINISHED}<br>
     * @deprecated Not used anymore(Ticket BPOSANDAPP-492}
     */
    public static final String TRANS_REVERSAL_FINISHED = "com.pax.us.pay.TRANS_REVERSAL_FINISHED";

    /**
     * Broadcast Action: Connect PINPAD Process begin<br>{@value #PINPAD_CONNECTION_STARTED}<br>
     * <p>"Connecting to PINPAD..."</p>
     */
    public static final String PINPAD_CONNECTION_STARTED = "com.pax.us.pay.PINPAD_CONNECTION_STARTED";

    /**
     * Broadcast Action: Connect PINPAD Process end<br>{@value #PINPAD_CONNECTION_FINISHED}<br>
     */
    public static final String PINPAD_CONNECTION_FINISHED = "com.pax.us.pay.PINPAD_CONNECTION_FINISHED";

    /**
     * Broadcast Action: Emv Trans Online Process begin<br>{@value #EMV_TRANS_ONLINE_STARTED}<br>
     * <p>When QuickChip is enabled, use {@link #TRANS_ONLINE_STARTED}</p>
     * <p>"Do Not Remove Your Card Please!\nProcessing Online&#8230;"</p>
     */
    public static final String EMV_TRANS_ONLINE_STARTED = "com.pax.us.pay.EMV_TRANS_ONLINE_STARTED";

    /**
     * Broadcast Action: Emv Trans Online Process end<br>{@value #EMV_TRANS_ONLINE_FINISHED}<br>
     * <p>When QuickChip is enabled, use {@link #TRANS_ONLINE_FINISHED}</p>
     */
    public static final String EMV_TRANS_ONLINE_FINISHED = "com.pax.us.pay.EMV_TRANS_ONLINE_FINISHED";

    /**
     * Broadcast Action: DCC Process begin<br>{@value #DCC_ONLINE_STARTED}<br>
     * This action is host dependent.<br>
     * <p>DCC is dynamic currency conversion</p>
     * <p>"DCC Request Processing..."</p>
     */
    public static final String DCC_ONLINE_STARTED = "com.pax.us.pay.DCC_ONLINE_STARTED";
    /**
     * Broadcast Action: DCC Process end<br>{@value #DCC_ONLINE_FINISHED}<br>
     * This action is host dependent.<br>
     */
    public static final String DCC_ONLINE_FINISHED = "com.pax.us.pay.DCC_ONLINE_FINISHED";

    /**
     * Broadcast Action: RKI Process begin (BroadPOS use only)<br>{@value #RKI_STARTED}<br>
     * <p>"Remote Key Injection, Processing..."</p>
     */
    public static final String RKI_STARTED = "com.pax.us.pay.RKI_STARTED";

    /**
     * Broadcast Action: RKI Process end (BroadPOS use only)<br>{@value #RKI_FINISHED}<br>
     */
    public static final String RKI_FINISHED = "com.pax.us.pay.RKI_FINISHED";

    /**
     * Broadcast Action: General Error<br>{@value #ERROR}<br>
     * <p>
     *     Input: {@link StatusData#PARAM_CODE} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link StatusData#PARAM_MSG} <br>
     *     Type: String
     * </p>
     */
    public static final String ERROR = "com.pax.us.pay.ERROR";

    /**
     * Broadcast Action: Update amount during card processing<br>{@value #TRANS_AMOUNT_CHANGED_IN_CARD_PROCESSING}<br>
     * <p>
     *     Input: {@link StatusData#PARAM_TOTAL_AMOUNT}<br>
     *     Type: Long
     * </p>
     */
    public static final String TRANS_AMOUNT_CHANGED_IN_CARD_PROCESSING = "com.pax.us.pay.AMOUNT_CHANGED_IN_CARD_PROCESSING";

}
