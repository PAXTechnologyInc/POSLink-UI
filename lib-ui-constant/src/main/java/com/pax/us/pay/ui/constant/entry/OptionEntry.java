package com.pax.us.pay.ui.constant.entry;

public class OptionEntry {
    public static final String CATEGORY = "com.pax.us.pay.ui.category.OPTION";
    /**
     * The intent action of Select Ebt type <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_OPTIONS}: enum: {@link com.pax.us.pay.ui.constant.entry.enumeration.EBTType} <br>
     * request: {@link EntryRequest#PARAM_INDEX} <br>
     */
    public static final String ACTION_SELECT_EBT_TYPE = "com.pax.us.pay.action.SELECT_EBT_TYPE";

    /**
     * The intent action of Select by pass <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_OPTIONS}: enum: {@link com.pax.us.pay.ui.constant.entry.enumeration.BypassReason} <br>
     * request: {@link EntryRequest#PARAM_INDEX} <br>
     */
    public static final String ACTION_SELECT_BY_PASS = "com.pax.us.pay.action.SELECT_BY_PASS";

    /**
     * The intent action of Select Sub trans type <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_OPTIONS}:enum :  <br>
     *        {@link com.pax.us.pay.ui.constant.entry.enumeration.SubTransType}  <br>
     *        or {@link com.pax.us.pay.ui.constant.entry.enumeration.CashoutType} <br>
     * request: {@link EntryRequest#PARAM_INDEX} <br>
     */
    public static final String ACTION_SELECT_SUB_TRANS_TYPE = "com.pax.us.pay.action.SELECT_SUB_TRANS_TYPE";


    /**
     * The intent action of Select Sub AID <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_OPTIONS}: According the EMV card's AID <br>
     * request: {@link EntryRequest#PARAM_INDEX} <br>
     */
    public static final String ACTION_SELECT_AID = "com.pax.us.pay.action.SELECT_AID";

    /**
     * The intent action of select refund reason <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_OPTIONS}: enum: {@link com.pax.us.pay.ui.constant.entry.enumeration.RefundReason} <br>
     * request: {@link EntryRequest#PARAM_INDEX} <br>
     */
    public static final String ACTION_SELECT_REFUND_REASON = "com.pax.us.pay.action.SELECT_REFUND_REASON";

    /**
     * The intent action of select MOTO order type <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_OPTIONS}: enum: {@link com.pax.us.pay.ui.constant.entry.enumeration.MOTOType} <br>
     * request: {@link EntryRequest#PARAM_INDEX} <br>
     */
    public static final String ACTION_SELECT_MOTO_TYPE = "com.pax.us.pay.action.SELECT_MOTO_TYPE";

    /**
     * The intent action of select Tax reason <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_OPTIONS}: enum: {@link com.pax.us.pay.ui.constant.entry.enumeration.TaxReason} <br>
     * request: {@link EntryRequest#PARAM_INDEX} <br>
     */
    public static final String ACTION_SELECT_TAX_REASON = "com.pax.us.pay.action.SELECT_TAX_REASON";

    /**
     * The intent action of select duplicate transaction override or use original transaction <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_OPTIONS}: enum: {@link com.pax.us.pay.ui.constant.entry.enumeration.DuplicateType} <br>
     * request: {@link EntryRequest#PARAM_INDEX} <br>
     */
    public static final String ACTION_SELECT_DUPLICATE_OVERRIDE = "com.pax.us.pay.action.SELECT_DUPLICATE_OVERRIDE";

    /**
     * The intent action of select the card type <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_OPTIONS}: enum: {@link com.pax.us.pay.ui.constant.entry.enumeration.CardType} <br>
     * request: {@link EntryRequest#PARAM_INDEX} <br>
     */
    public static final String ACTION_SELECT_CARD_TYPE = "com.pax.us.pay.action.SELECT_CARD_TYPE";

    /**
     * The intent action of select the transaction type <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_OPTIONS}: enum: {@link com.pax.us.pay.ui.constant.entry.enumeration.TransType} <br>
     * request: {@link EntryRequest#PARAM_INDEX} <br>
     */
    public static final String ACTION_SELECT_TRANS_TYPE = "com.pax.us.pay.action.SELECT_TRANS_TYPE";

    /**
     * The intent action of select the EDC type <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_OPTIONS}: enum: {@link com.pax.us.pay.ui.constant.entry.enumeration.EDCType} <br>
     * request: {@link EntryRequest#PARAM_INDEX} <br>
     */
    public static final String ACTION_SELECT_EDC_TYPE = "com.pax.us.pay.action.SELECT_EDC_TYPE";

    /**
     * The intent action of select the search type <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_OPTIONS}: enum: {@link com.pax.us.pay.ui.constant.entry.enumeration.SearchCriteria} <br>
     * request: {@link EntryRequest#PARAM_INDEX} <br>
     */
    public static final String ACTION_SELECT_SEARCH_CRITERIA = "com.pax.us.pay.action.SELECT_SEARCH_CRITERIA";

    /**
     * The intent action of select transaction for Tip Adjust <br>
     * request: {@link EntryRequest#PARAM_TRANS_NUMBER} <br>
     */
    public static final String ACTION_SELECT_TRANS_FOR_ADJUST = "com.pax.us.pay.action.SELECT_TRANS_FOR_ADJUST";
}
