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
     * Activity Category: OPTION <br>{@value #CATEGORY}<br>
     */
    public static final String CATEGORY = "com.pax.us.pay.ui.category.OPTION";
    /**
     * Activity Action: Select Ebt type <br>{@value #ACTION_SELECT_EBT_TYPE}<br>
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
     *     Input: {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS} <br>
     *     Type: String[]<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.EBTType} for details</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} - {@value EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     */
    public static final String ACTION_SELECT_EBT_TYPE = "com.pax.us.pay.action.SELECT_EBT_TYPE";

    /**
     * Activity Action: Select CVV Bypassed Reason<br>{@value #ACTION_SELECT_BY_PASS}<br>
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
     *     Input: {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS} <br>
     *     Type: String[]<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.BypassReason} for details</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} - {@value EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     */
    public static final String ACTION_SELECT_BY_PASS = "com.pax.us.pay.action.SELECT_BY_PASS";

    /**
     * Activity Action: Select Sub Trans Type<br>{@value #ACTION_SELECT_SUB_TRANS_TYPE}<br>
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
     *     Input: {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS} <br>
     *     Type: String[]<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.SubTransType} and {@link com.pax.us.pay.ui.constant.entry.enumeration.CashoutType} for details
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} - {@value EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     */
    public static final String ACTION_SELECT_SUB_TRANS_TYPE = "com.pax.us.pay.action.SELECT_SUB_TRANS_TYPE";


    /**
     * Activity Action: Select EMV AID<br>{@value #ACTION_SELECT_AID}<br>
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
     *     Input: {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS} <br>
     *     Type: String[]<br>
     *     Value depend on the EMV card's AID list
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} - {@value EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     */
    public static final String ACTION_SELECT_AID = "com.pax.us.pay.action.SELECT_AID";

    /**
     * Activity Action: Select Refund Reason<br>{@value #ACTION_SELECT_REFUND_REASON}<br>
     * This action is host dependent.<br>
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
     *     Input: {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS} <br>
     *     Type: String[]<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.RefundReason}
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} - {@value EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     */
    public static final String ACTION_SELECT_REFUND_REASON = "com.pax.us.pay.action.SELECT_REFUND_REASON";

    /**
     * Activity Action: Select MOTO Type<br>{@value #ACTION_SELECT_MOTO_TYPE}<br>
     * This action is host dependent.<br>
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
     *     Input: {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS} <br>
     *     Type: String[]<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.MOTOType}
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} - {@value EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     */
    public static final String ACTION_SELECT_MOTO_TYPE = "com.pax.us.pay.action.SELECT_MOTO_TYPE";

    /**
     * Activity Action: Select Tax Reason<br>{@value #ACTION_SELECT_TAX_REASON}<br>
     * This action is host dependent.<br>
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
     *     Input: {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.TaxReason}<br>
     *     Type: String
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} - {@value EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     */
    public static final String ACTION_SELECT_TAX_REASON = "com.pax.us.pay.action.SELECT_TAX_REASON";

    /**
     * Activity Action: Select Re-auth Option after getting DUP response<br>{@value #ACTION_SELECT_DUPLICATE_OVERRIDE}<br>
     * This action is host dependent.<br>
     * <p>
     *     Not apply for all BroadPOS apps. It depends on Host support.
     *     Example:
     *     Do a CREDIT SALE transaction using BroadPOS CardKnox, response from host tells it is duplicated.
     *     The BroadPOS CardKnox will start this action with option {@link com.pax.us.pay.ui.constant.entry.enumeration.DuplicateType#OVERRIDE},<br>
     *     If choose OVERRIDE, BroadPOS will do re-auth for this transaction.
     *     If decline the option by cancel, BroadPOS will treat this transaction as declined.
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
     *     Input: {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.DuplicateType}
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} - {@value EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     */
    public static final String ACTION_SELECT_DUPLICATE_OVERRIDE = "com.pax.us.pay.action.SELECT_DUPLICATE_OVERRIDE";

    /**
     * Activity Action: Select Card Type<br>{@value #ACTION_SELECT_CARD_TYPE}<br>
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
     *     Input: {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.CardType}<br>
     *     Type: String
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} - {@value EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     */
    public static final String ACTION_SELECT_CARD_TYPE = "com.pax.us.pay.action.SELECT_CARD_TYPE";

    /**
     * Activity Action: Select Transaction Type<br>{@value #ACTION_SELECT_TRANS_TYPE}<br>
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
     *     Input: {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.TransType}<br>
     *     Type: String
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} - {@value EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     */
    public static final String ACTION_SELECT_TRANS_TYPE = "com.pax.us.pay.action.SELECT_TRANS_TYPE";

    /**
     * Activity Action: Select EDC Type<br>{@value #ACTION_SELECT_EDC_TYPE}<br>
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
     *     Input: {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.EDCType}<br>
     *     Type: String
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} - {@value EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     */
    public static final String ACTION_SELECT_EDC_TYPE = "com.pax.us.pay.action.SELECT_EDC_TYPE";

    /**
     * Activity Action: Select Search Type<br>{@value #ACTION_SELECT_SEARCH_CRITERIA}<br>
     * <p>For POSLink Tip Adjustment use</p>
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
     *     Input: {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.SearchCriteria}<br>
     *     Type: String
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} - {@value EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     */
    public static final String ACTION_SELECT_SEARCH_CRITERIA = "com.pax.us.pay.action.SELECT_SEARCH_CRITERIA";

    /**
     * Activity Action: Select Batch Type<br>{@value #ACTION_SELECT_BATCH_TYPE}<br>
     * This action is host dependent.<br>
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
     *     Input: {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.BatchType}<br>
     *     Type: String
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} - {@value EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     */
    public static final String ACTION_SELECT_BATCH_TYPE = "com.pax.us.pay.action.SELECT_BATCH_TYPE";

    /**
     * Activity Action: Select Edc Group for Batch Transaction<br>{@value #ACTION_SELECT_EDC_GROUP}<br>
     * This action is host dependent.<br>
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
     *     Input: {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     See {@link EDCGroup}
     *     </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} - {@value EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     */
    public static final String ACTION_SELECT_EDC_GROUP = "com.pax.us.pay.action.SELECT_EDC_GROUP";


    /**
     * Activity Action: Select Report type for Batch Transaction<br>{@value #ACTION_SELECT_REPORT_TYPE}<br>
     * This action is host dependent.<br>
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
     *     Input: {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.ReportType}<br>
     *     Type: String
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} - {@value EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     */
    public static final String ACTION_SELECT_REPORT_TYPE = "com.pax.us.pay.action.SELECT_REPORT_TYPE";

    /**
     * Activity Action: Select Debit Account Type<br>{@value #ACTION_SELECT_ACCOUNT_TYPE}<br>
     * This action is host dependent.<br>
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
     *     Input: {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.AccountType}<br>
     *     Type: String
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} - {@value EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     */
    public static final String ACTION_SELECT_ACCOUNT_TYPE = "com.pax.us.pay.action.SELECT_ACCOUNT_TYPE";


    /**
     * Activity Action: select tip amount options<br>{@value #ACTION_SELECT_TIP_AMOUNT}<br>
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
     *     Input: {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS}: According the tip amount<br>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} - {@value EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     * @deprecated Never used. Use {@link TextEntry#ACTION_ENTER_TIP} instead.
     */
    public static final String ACTION_SELECT_TIP_AMOUNT = "com.pax.us.pay.action.SELECT_TIP_AMOUNT";

    /**
     * Activity Action: select cashback amount options<br>{@value #ACTION_SELECT_CASHBACK_AMOUNT}<br>
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
     *     Input: {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS}: According the cashback amount<br>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} - {@value EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     * @deprecated Never used. Use {@link TextEntry#ACTION_ENTER_CASH_BACK} instead.
     */
    public static final String ACTION_SELECT_CASHBACK_AMOUNT = "com.pax.us.pay.action.SELECT_CASHBACK_AMOUNT";


    /**
     * Activity Action: Select User Language<br>{@value #ACTION_SELECT_LANGUAGE}<br>
     * This action is host dependent.<br>
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
     *     Input: {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     Value depends on language list of EMV Card
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} - {@value EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     */
    public static final String ACTION_SELECT_LANGUAGE = "com.pax.us.pay.action.SELECT_LANGUAGE";


    /**
     * Activity Action: Select Merchant<br>{@value #ACTION_SELECT_MERCHANT}<br>
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
     *     Input: {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_INTERFACE_STYLES}<br>
     *     Type: String<br>
     *     Deprecated. Not used anymore.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} - {@value EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     */
    public static final String ACTION_SELECT_MERCHANT = "com.pax.us.pay.action.SELECT_MERCHANT";

    /**
     * Activity Action: Select Original Transaction Currency<br>{@value #ACTION_SELECT_ORIG_CURRENCY}<br>
     * This action is host dependent.<br>
     * <p>
     *     For Visa Installment use.
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
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} - {@value EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000.
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS} <br>
     *     Type: String[]<br>
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} - {@value EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     */
    public static final String ACTION_SELECT_ORIG_CURRENCY = "com.pax.us.pay.action.SELECT_ORIG_CURRENCY";

    //-----------------------------Visa Installment---------------------------------
    /**
     * Activity Action: Select Installment Plan (For Visa Installment use)<br>{@value #ACTION_SELECT_INSTALLMENT_PLAN}<br>
     * This action is host dependent.<br>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_BASE_AMOUNT} - {@value EntryExtraData#PARAM_BASE_AMOUNT}<br>
     *     Type: Long
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
     *     Input: {@link EntryExtraData#PARAM_INSTALLMENT_PAYMENT_AMOUNTS} - {@value EntryExtraData#PARAM_INSTALLMENT_PAYMENT_AMOUNTS} <br>
     *     Type: String[]
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_INSTALLMENT_PAYMENT_TERMS_AND_CONDITIONS} - {@value EntryExtraData#PARAM_INSTALLMENT_PAYMENT_TERMS_AND_CONDITIONS} <br>
     *     Type: String[]
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_INSTALLMENT_PAYMENT_TOTAL_FEES} - {@value EntryExtraData#PARAM_INSTALLMENT_PAYMENT_TOTAL_FEES}<br>
     *     Type: String[]
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_INSTALLMENT_PAYMENT_NUMBER_OF_INSTALLMENTS} - {@value EntryExtraData#PARAM_INSTALLMENT_PAYMENT_NUMBER_OF_INSTALLMENTS} <br>
     *     Type: String[]
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_INSTALLMENT_PAYMENT_FREQUENCY_OF_INSTALLMENTS} - {@value EntryExtraData#PARAM_INSTALLMENT_PAYMENT_FREQUENCY_OF_INSTALLMENTS} <br>
     *     Type: String[]
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_INSTALLMENT_PAYMENT_TOTAL_AMOUNT_INCLUSIVE_FEES} - {@value EntryExtraData#PARAM_INSTALLMENT_PAYMENT_TOTAL_AMOUNT_INCLUSIVE_FEES} <br>
     *     Type: String[]
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_INSTALLMENT_PAYMENT_PLAN_CURRENCIES} - {@value EntryExtraData#PARAM_INSTALLMENT_PAYMENT_PLAN_CURRENCIES} <br>
     *     Type: String[]
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_INSTALLMENT_PAYMENT_PLAN_ID} - {@value EntryExtraData#PARAM_INSTALLMENT_PAYMENT_PLAN_ID} <br>
     *     Type: String[]
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_CURRENCY} - {@value EntryExtraData#PARAM_CURRENCY} is currency type. <br>
     *     Type: String<br>
     *     Default value: {@link com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType#USD}
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} - {@value EntryRequest#PARAM_INDEX} Selected Index<br>
     *     Type: Integer<br>
     *     Value start from 0.
     * </p>
     */
    public static final String ACTION_SELECT_INSTALLMENT_PLAN = "com.pax.us.pay.action.SELECT_INSTALLMENT_PLAN";

    //---------------------Tip Adjust--------------------
    /**
     * Activity Action: Select Transaction for Tip Adjust
     * <p>Now not ready for public.</p>
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
     *     Type: Long
     * </p>
     */
    public static final String ACTION_SELECT_TRANS_FOR_ADJUST = "com.pax.us.pay.action.SELECT_TRANS_FOR_ADJUST";

//Note:
//Appending a new action, please add mapping into "SELECT_OPTION_MAP" in "SelectOptionContent"

}
