package com.pax.us.pay.ui.constant.entry;

import com.pax.us.pay.ui.constant.entry.enumeration.EDCGroup;

//Note:
//Appending a new action, please add mapping into "SELECT_OPTION_MAP" in "SelectOptionContent"

/**
 * define Activity for OPTION
 */
public final class OptionEntry {
    private OptionEntry(){

    }
    /**
     * Activity Category: OPTION
     */
    public static final String CATEGORY = "com.pax.us.pay.ui.category.OPTION";
    /**
     * Activity Action: Select Ebt type
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
     *     Input: {@link EntryExtraData#PARAM_OPTIONS} <br>
     *     Type: String[]<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.EBTType} for details</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     */
    public static final String ACTION_SELECT_EBT_TYPE = "com.pax.us.pay.action.SELECT_EBT_TYPE";

    /**
     * Activity Action: Select CVV Bypassed Reason
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
     *     Input: {@link EntryExtraData#PARAM_OPTIONS} <br>
     *     Type: String[]<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.BypassReason} for details</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     */
    public static final String ACTION_SELECT_BY_PASS = "com.pax.us.pay.action.SELECT_BY_PASS";

    /**
     * Activity Action: Select Sub Trans Type
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
     *     Input: {@link EntryExtraData#PARAM_OPTIONS} <br>
     *     Type: String[]<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.SubTransType} and {@link com.pax.us.pay.ui.constant.entry.enumeration.CashoutType} for details
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     */
    public static final String ACTION_SELECT_SUB_TRANS_TYPE = "com.pax.us.pay.action.SELECT_SUB_TRANS_TYPE";


    /**
     * Activity Action: Select Sub AID
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
     *     Input: {@link EntryExtraData#PARAM_OPTIONS} <br>
     *     Type: String[]<br>
     *     Value depend on the EMV card's AID list
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     */
    public static final String ACTION_SELECT_AID = "com.pax.us.pay.action.SELECT_AID";

    /**
     * Activity Action: Select Refund Reason
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
     *     Input: {@link EntryExtraData#PARAM_OPTIONS} <br>
     *     Type: String[]<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.RefundReason}
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     */
    public static final String ACTION_SELECT_REFUND_REASON = "com.pax.us.pay.action.SELECT_REFUND_REASON";

    /**
     * Activity Action: Select MOTO Type
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
     *     Input: {@link EntryExtraData#PARAM_OPTIONS} <br>
     *     Type: String[]<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.MOTOType}
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     */
    public static final String ACTION_SELECT_MOTO_TYPE = "com.pax.us.pay.action.SELECT_MOTO_TYPE";

    /**
     * Activity Action: Select Tax Reason
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
     *     Input: {@link EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.TaxReason}<br>
     *     Type: String
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     */
    public static final String ACTION_SELECT_TAX_REASON = "com.pax.us.pay.action.SELECT_TAX_REASON";

    /**
     * Activity Action: Select Duplicate Transaction Override or Use original transaction
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
     *     Input: {@link EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.DuplicateType}<br>
     *     Type: String
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     */
    public static final String ACTION_SELECT_DUPLICATE_OVERRIDE = "com.pax.us.pay.action.SELECT_DUPLICATE_OVERRIDE";

    /**
     * Activity Action: Select Card Type
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
     *     Input: {@link EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.CardType}<br>
     *     Type: String
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     */
    public static final String ACTION_SELECT_CARD_TYPE = "com.pax.us.pay.action.SELECT_CARD_TYPE";

    /**
     * Activity Action: Select Transaction Type
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
     *     Input: {@link EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.TransType}<br>
     *     Type: String
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     */
    public static final String ACTION_SELECT_TRANS_TYPE = "com.pax.us.pay.action.SELECT_TRANS_TYPE";

    /**
     * Activity Action: Select EDC Type
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
     *     Input: {@link EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.EDCType}<br>
     *     Type: String
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     */
    public static final String ACTION_SELECT_EDC_TYPE = "com.pax.us.pay.action.SELECT_EDC_TYPE";

    /**
     * Activity Action: Select Search Type
     * <p>Used for POSLink Tip Adjustment. BroadPOS Use only</p>
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
     *     Input: {@link EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.SearchCriteria}<br>
     *     Type: String
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     */
    public static final String ACTION_SELECT_SEARCH_CRITERIA = "com.pax.us.pay.action.SELECT_SEARCH_CRITERIA";

    /**
     * Activity Action: Select transaction for Tip Adjust
     * <p>BroadPOS Use only</p>
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
     *     Input: {@link EntryExtraData#PARAM_TRANS_URL}<br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_SELECTION}<br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_SELECTION_ARGUMENTS}<br>
     *     Type: String[]
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_TRANS_NUMBER}<br>
     *     Type: String
     * </p>
     */
    public static final String ACTION_SELECT_TRANS_FOR_ADJUST = "com.pax.us.pay.action.SELECT_TRANS_FOR_ADJUST";


    /**
     * Activity Action: Select Batch Type
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
     *     Input: {@link EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.BatchType}<br>
     *     Type: String
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     */
    public static final String ACTION_SELECT_BATCH_TYPE = "com.pax.us.pay.action.SELECT_BATCH_TYPE";

    /**
     * Activity Action: Select Edc Group for Batch Transaction
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
     *     Input: {@link EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     See {@link EDCGroup}
     *     </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     */
    public static final String ACTION_SELECT_EDC_GROUP = "com.pax.us.pay.action.SELECT_EDC_GROUP";


    /**
     * Activity Action: Select Report type for Batch Transaction
     * <p>Depend on Host</p>
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
     *     Input: {@link EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.ReportType}<br>
     *     Type: String
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     */
    public static final String ACTION_SELECT_REPORT_TYPE = "com.pax.us.pay.action.SELECT_REPORT_TYPE";

    /**
     * Activity Action: Select Debit Account Type
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
     *     Input: {@link EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.AccountType}<br>
     *     Type: String
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     */
    public static final String ACTION_SELECT_ACCOUNT_TYPE = "com.pax.us.pay.action.SELECT_ACCOUNT_TYPE";


    /**
     * Activity Action: select tip amount options<br>
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
     *     Input: {@link EntryExtraData#PARAM_OPTIONS}: According the tip amount<br>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     * @deprecated Not used any more
     */
    public static final String ACTION_SELECT_TIP_AMOUNT = "com.pax.us.pay.action.SELECT_TIP_AMOUNT";

    /**
     * Activity Action: select tip amount options<br>
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
     *     Input: {@link EntryExtraData#PARAM_OPTIONS}: According the cashback amount<br>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     * @deprecated Not used any more
     */
    public static final String ACTION_SELECT_CASHBACK_AMOUNT = "com.pax.us.pay.action.SELECT_CASHBACK_AMOUNT";


    //Yanina: Remove it cuz duplicated with ACTION_SELECT_TRANS_FOR_ADJUST
//    /**
//     * Activity Action: Select Trans to Adjust
//     * <p>
//     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
//     *     Type: String
//     * </p>
//     * <p>
//     *     Input: {@link EntryExtraData#PARAM_MESSAGE}<br>
//     *     Type: String
//     * </p>
//     * <p>
//     *     Input: {@link EntryExtraData#PARAM_TRANS_URL}: String</p>
//     * <p>
//     *     Input: {@link EntryExtraData#PARAM_TRANS_SELECTION}</p>
//     * <p>
//     *     Input: {@link EntryExtraData#PARAM_TRANS_SELECTION_ARGUMENTS} </p>
//     * <p>
//     *     Output: {@link EntryRequest#PARAM_TRANS_NUMBER}<br>
//     *     Type: String
//     * </p>
//     * @deprecated Never used. See {@link #ACTION_SELECT_TRANS_FOR_ADJUST}
//     */
//    public static final String ACTION_SELECT_TRANS_ADJUST = "com.pax.us.pay.action.SELECT_TRANS_ADJUST";

    /**
     * Activity Action: Select User Language
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
     *     Input: {@link EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     Value depends on language list of EMV Card
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     */
    public static final String ACTION_SELECT_LANGUAGE = "com.pax.us.pay.action.SELECT_LANGUAGE";


    /**
     * Activity Action: Select Merchant
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
     *     Input: {@link EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     */
    public static final String ACTION_SELECT_MERCHANT = "com.pax.us.pay.action.SELECT_MERCHANT";

    /**
     * Activity Action: Select Installment Plan
     * <p>
     *     Input: {@link EntryExtraData#PARAM_BASE_AMOUNT}<br>
     *     Type: Long
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
     *     Input: {@link EntryExtraData#PARAM_INSTALLMENT_PAYMENT_AMOUNTS} <br>
     *     Type: String[]
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_INSTALLMENT_PAYMENT_TERMS_AND_CONDITIONS} <br>
     *     Type: String[]
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_INSTALLMENT_PAYMENT_TOTAL_FEES} <br>
     *     Type: String[]
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_INSTALLMENT_PAYMENT_NUMBER_OF_INSTALLMENTS} <br>
     *     Type: String[]
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_INSTALLMENT_PAYMENT_FREQUENCY_OF_INSTALLMENTS} <br>
     *     Type: String[]
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_INSTALLMENT_PAYMENT_TOTAL_AMOUNT_INCLUSIVE_FEES} <br>
     *     Type: String[]
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_INSTALLMENT_PAYMENT_PLAN_CURRENCIES} <br>
     *     Type: String[]
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_INSTALLMENT_PAYMENT_PLAN_ID} <br>
     *     Type: String[]
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_CURRENCY} is currency type. <br>
     *     Type: String<br>
     *     Default value: {@link com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType#USD}
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INSTALLMENT_SELECT_OPTION}<br>
     *     Type: Integer
     * </p>
     */
    public static final String ACTION_SELECT_INSTALLMENT_PLAN = "com.pax.us.pay.action.SELECT_INSTALLMENT_PLAN";

    /**
     * Activity Action: Select Original Transaction Currency
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
     *     Input: {@link EntryExtraData#PARAM_OPTIONS} <br>
     *     Type: String[]<br>
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     */
    public static final String ACTION_SELECT_ORIG_CURRENCY = "com.pax.us.pay.action.SELECT_ORIG_CURRENCY";

//Note:
//Appending a new action, please add mapping into "SELECT_OPTION_MAP" in "SelectOptionContent"

}
