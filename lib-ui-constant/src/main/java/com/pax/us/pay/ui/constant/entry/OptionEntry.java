package com.pax.us.pay.ui.constant.entry;

import com.pax.us.pay.ui.constant.entry.enumeration.EDCGroup;
import com.pax.us.pay.ui.constant.entry.enumeration.InterfaceStyles;

//Note:
//Appending a new action, please add mapping into "SELECT_OPTION_MAP" in "SelectOptionContent"

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


    /**
     * The intent action of select batch transaction type  <br>
     * /**
     * The intent action of select the search type <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_OPTIONS}: enum: {@link com.pax.us.pay.ui.constant.entry.enumeration.BatchType} <br>
     * request: {@link EntryRequest#PARAM_INDEX} <br>
     */
    public static final String ACTION_SELECT_BATCH_TYPE = "com.pax.us.pay.action.SELECT_BATCH_TYPE";

    /**
     * The intent action of select edc group for batch transaction <br>
     * /**
     * The intent action of select the search type <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_OPTIONS}: enum: {@link EDCGroup} <br>
     * request: {@link EntryRequest#PARAM_INDEX} <br>
     */
    public static final String ACTION_SELECT_EDC_GROUP = "com.pax.us.pay.action.SELECT_EDC_GROUP";


    /**
     * The intent action of select report type for batch transaction <br>
     * /**
     * The intent action of select the search type <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_OPTIONS}: enum: {@link com.pax.us.pay.ui.constant.entry.enumeration.ReportType} <br>
     * request: {@link EntryRequest#PARAM_INDEX} <br>
     */
    public static final String ACTION_SELECT_REPORT_TYPE = "com.pax.us.pay.action.SELECT_REPORT_TYPE";

    /**
     * The intent action of select account type for debit transaction <br>
     * /**
     * The intent action of select the search type <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_OPTIONS}: enum: {@link com.pax.us.pay.ui.constant.entry.enumeration.AccountType} <br>
     * request: {@link EntryRequest#PARAM_INDEX} <br>
     */
    public static final String ACTION_SELECT_ACCOUNT_TYPE = "com.pax.us.pay.action.SELECT_ACCOUNT_TYPE";


    /**
     * The intent action of select tip amount options<br>
     * /**
     * The intent action of select the search type <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_OPTIONS}: According the tip amount<br>
     * request: {@link EntryRequest#PARAM_INDEX} <br>
     */
    public static final String ACTION_SELECT_TIP_AMOUNT = "com.pax.us.pay.action.SELECT_TIP_AMOUNT";

    /**
     * The intent action of select tip amount options<br>
     * /**
     * The intent action of select the search type <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_OPTIONS}: According the cashback amount<br>
     * request: {@link EntryRequest#PARAM_INDEX} <br>
     */
    public static final String ACTION_SELECT_CASHBACK_AMOUNT = "com.pax.us.pay.action.SELECT_CASHBACK_AMOUNT";


    /**
     * The intent action of select trans to adjust <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_TRANS_URL}: String <br>
     * input: {@link EntryExtraData#PARAM_TRANS_SELECTION}: String <br>
     * input: {@link EntryExtraData#PARAM_TRANS_SELECTION_ARGUMENTS}: StringArray <br>
     * request: {@link EntryRequest#PARAM_TRANS_NUMBER} <br>
     */
    public static final String ACTION_SELECT_TRANS_ADJUST = "com.pax.us.pay.action.SELECT_TRANS_ADJUST";

    /**
     * The intent action of select user language for debit transaction <br>
     * /**
     * The intent action of select the search type <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_OPTIONS} <br>
     * request: {@link EntryRequest#PARAM_INDEX} <br>
     */
    public static final String ACTION_SELECT_LANGUAGE = "com.pax.us.pay.action.SELECT_LANGUAGE";


    /**
     * The intent action of select user language for debit transaction <br>
     * /**
     * The intent action of select the search type <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_OPTIONS} <br>
     * input: {@link EntryExtraData#PARAM_INTERFACE_STYLES} enum: {@link InterfaceStyles}  <br>
     * request: {@link EntryRequest#PARAM_INDEX} <br>
     */
    public static final String ACTION_SELECT_MERCHANT = "com.pax.us.pay.action.SELECT_MERCHANT";

    /**
     * The intent action of select and display installment plan details <br>
     * /**
     * The intent action of select the search type <br>
     * input: {@link EntryExtraData#PARAM_INSTALLMENT_PAYMENT_AMOUNT_A} <br>
     * input: {@link EntryExtraData#PARAM_INSTALLMENT_PAYMENT_AMOUNT_B} <br>
     * input: {@link EntryExtraData#PARAM_NUMBER_OF_INSTALLMENTS_A} <br>
     * input: {@link EntryExtraData#PARAM_NUMBER_OF_INSTALLMENTS_B} <br>
     * input: {@link EntryExtraData#PARAM_FREQUENCY_OF_INSTALLMENT_A} <br>
     * input: {@link EntryExtraData#PARAM_FREQUENCY_OF_INSTALLMENT_B} <br>
     * input: {@link EntryExtraData#PARAM_TOTAL_FEES_PER_INSTALLMENT_PLAN_A} <br>
     * input: {@link EntryExtraData#PARAM_TOTAL_FEES_PER_INSTALLMENT_PLAN_B} <br>
     * input: {@link EntryExtraData#PARAM_TOTAL_AMOUNT_INCLUSIVE_OF_FEES_A} <br>
     * input: {@link EntryExtraData#PARAM_TOTAL_AMOUNT_INCLUSIVE_OF_FEES_B} <br>
     * input: {@link EntryExtraData#PARAM_TERMS_AND_CONDITIONS_A} <br>
     * input: {@link EntryExtraData#PARAM_TERMS_AND_CONDITIONS_B} <br>
     * input: {@link EntryExtraData#PARAM_PLANID_A} <br>
     * input: {@link EntryExtraData#PARAM_PLANID_B} <br>
     * input: {@link EntryExtraData#PARAM_PLAN_A_TERMS_AND_CONDITIONS_VERSION} <br>
     * input: {@link EntryExtraData#PARAM_PLAN_B_TERMS_AND_CONDITIONS_VERSION} <br>
     * request: {@link EntryRequest#PARAM_INSTALLMEN_SELECT_OPTION} <br>
     */
    public static final String ACTION_SELECT_INSTALLMENT_PLAN = "com.pax.us.pay.action.SELECT_INSTALLMENT_PLAN";


//Note:
//Appending a new action, please add mapping into "SELECT_OPTION_MAP" in "SelectOptionContent"

}
