package com.pax.us.pay.ui.constant.entry;

import com.pax.us.pay.ui.constant.entry.enumeration.ConfirmationType;
import com.pax.us.pay.ui.constant.entry.enumeration.PartialApprovalOption;

/**
 * define Activity for CONFIRMATION
 *
 */
public final class ConfirmationEntry {
    private ConfirmationEntry(){
        
    }

    /**
     * Activity Category: CONFIRMATION<br>{@value #CATEGORY}<br>
     */
    public static final String CATEGORY = "com.pax.us.pay.ui.category.CONFIRMATION";

    /**
     * Activity Action: Start UI<br>{@value #ACTION_START_UI}<br>
     * <p>BroadPOS would show status like {@link com.pax.us.pay.ui.constant.status.CardStatus#CARD_REMOVAL_REQUIRED}
     * before start any Entry Action. It's hard to choose default ui or customized ui. So we add this action.<br>
     * In InsertAnyTime case, if customized app implements this action, BroadPOS will call customized app to foreground.<br>
     * Suggestion to prompt a dialog "Processing,Please wait..."<br>
     * After dialog show up, sendNext()</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} - {@value EntryExtraData#PARAM_PACKAGE}  is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Output: Nothing
     * </p>
     */
    public static final String ACTION_START_UI = "com.pax.us.pay.action.START_UI";

    /**
     * Activity Action: General Confirmation with Message<br>{@value #ACTION_CONFIRM_UNIFIED_MESSAGE}<br>
     * <p>If specific confirmation activity not found in customized app,
     *  BroadPOS will try start this general confirmation activity.</p>
     * <p>
     *     Example:<br>
     *     If BroadPOS try to start Activity {@value #ACTION_CHECK_CARD_PRESENT}, but your app does not implement it.<br>
     *     then BroadPOS will try to start this general confirmation Activity {@value #ACTION_CONFIRM_UNIFIED_MESSAGE}.
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} - {@value EntryExtraData#PARAM_PACKAGE}  is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} - {@value EntryExtraData#PARAM_MESSAGE} Message shown on UI<br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} - {@value EntryExtraData#PARAM_TIMEOUT}<br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000. <br>
     *     For reference only, BroadPOS handles timeout.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} - {@value EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     * </p>
     */
    public static final String ACTION_CONFIRM_UNIFIED_MESSAGE = "com.pax.us.pay.action.CONFIRM_UNIFIED_MESSAGE";


    /**
     * Activity Action: Reverse or Accept a Partial-approval transaction<br>{@value #ACTION_REVERSE_PARTIAL_APPROVAL}<br>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} - {@value EntryExtraData#PARAM_PACKAGE}  is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_CURRENCY} - {@value EntryExtraData#PARAM_CURRENCY} is currency type. <br>
     *     Type: String<br>
     *     Default value: {@link com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType#USD}
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_APPROVED_AMOUNT} - {@value EntryExtraData#PARAM_APPROVED_AMOUNT} <br>
     *     Type: Long
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_TOTAL_AMOUNT} - {@value EntryExtraData#PARAM_TOTAL_AMOUNT} <br>
     *     Type: Long
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} - {@value EntryExtraData#PARAM_MESSAGE}  Message shown on UI<br>
     *     Type: String<br>
     *     It is optional. Reversed if some BroadPOS app prefer customized message.<br>
     * </p>
     * <p>
     *     If {@link EntryExtraData#PARAM_MESSAGE} exist, recommend display it.<br>
     *     Of course, you can display your own message, Like: <br>
     *     "Approved Amount: %1$s\nDUE: %2$s;Reverse?"<br>
     *     %1$s is formatted Approved amount,  %2$s is formatted due amount (Total Amount- Approved Amount)
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS} <br>
     *     Type: String[]<br>
     *     Options are {@link PartialApprovalOption#REVERSE} and {@link PartialApprovalOption#ACCEPT}<br>
     *     If select {@value PartialApprovalOption#REVERSE}, the transaction will be voided.<br>
     *     If select {@value PartialApprovalOption#ACCEPT}, the transaction will be accepted with partial-approval.
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} - {@value EntryExtraData#PARAM_TIMEOUT}<br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000. <br>
     *     For reference only, BroadPOS handles timeout.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} - {@value EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@value PartialApprovalOption#REVERSE}, return TRUE<br>
     *     If select {@value PartialApprovalOption#ACCEPT}, return FALSE
     * </p>
     */
    public static final String ACTION_REVERSE_PARTIAL_APPROVAL = "com.pax.us.pay.action.REVERSE_PARTIAL_APPROVAL";


    /**
     * Activity Action: Confirm If Start a New Payment when get partial-approval<br>{@value #ACTION_SUPPLEMENT_PARTIAL_APPROVAL}<br>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} - {@value EntryExtraData#PARAM_PACKAGE}  is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_CURRENCY} - {@value EntryExtraData#PARAM_CURRENCY} is currency type. <br>
     *     Type: String<br>
     *     Default value: {@link com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType#USD}
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_APPROVED_AMOUNT} - {@value EntryExtraData#PARAM_APPROVED_AMOUNT} <br>
     *     Type: Long
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_TOTAL_AMOUNT} - {@value EntryExtraData#PARAM_TOTAL_AMOUNT} <br>
     *     Type: Long
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} - {@value EntryExtraData#PARAM_MESSAGE}  Message shown on UI<br>
     *     Type: String<br>
     *     It is optional. Reversed if some BroadPOS app prefer customized message.<br>
     * </p>
     * <p>
     *     If {@link EntryExtraData#PARAM_MESSAGE} exist, recommend display it.<br>
     *     Of course, you can display your own message, Like: <br>
     *     "Approved Amount: %1$s\nDUE: %2$s;Another Payment?"<br>
     *     %1$s is formatted Approved amount,  %2$s is formatted due amount (Total Amount- Approved Amount)
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS} <br>
     *     Type: String[]<br>
     *     Options are {@link PartialApprovalOption#ACCEPT} and {@link PartialApprovalOption#DECLINE}<br>
     *     If select {@value PartialApprovalOption#ACCEPT}, a new payment will be started.<br>
     *     If select {@value PartialApprovalOption#DECLINE}, this transaction will be accepted with partial-approval.
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} - {@value EntryExtraData#PARAM_TIMEOUT}<br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000. <br>
     *     For reference only, BroadPOS handles timeout.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} - {@value EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@value PartialApprovalOption#ACCEPT}, return TRUE<br>
     *     If select {@value PartialApprovalOption#DECLINE}, return FALSE
     * </p>
     */
    public static final String ACTION_SUPPLEMENT_PARTIAL_APPROVAL = "com.pax.us.pay.action.SUPPLEMENT_PARTIAL_APPROVAL";

    /**
     * Activity Action: select the transaction online retry or offline <br>{@value #ACTION_CONFIRM_ONLINE_RETRY_OFFLINE}<br>
     * <p>If not implemented, BroadPOS will try to start action {@link #ACTION_CONFIRM_UNIFIED_MESSAGE}</p>
     * input: {@link EntryExtraData#PARAM_PACKAGE} - {@value EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} - {@value EntryExtraData#PARAM_MESSAGE} <br>
     * request: {@link EntryRequest#PARAM_CONFIRMED} - {@value EntryRequest#PARAM_CONFIRMED} <br>
     * @deprecated Not used anymore. Retry-Or-Offline feature has been removed from BroadPOS.
     */
    public static final String ACTION_CONFIRM_ONLINE_RETRY_OFFLINE = "com.pax.us.pay.action.CONFIRM_ONLINE_RETRY_OFFLINE";

//    /**
//     * The intent action of select the credit type <br>
//     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
//     * input: {@link EntryExtraData#PARAM_MESSAGE} - {@value EntryExtraData#PARAM_MESSAGE} <br>
//     * request: {@link EntryRequest#PARAM_CONFIRMED} - {@value EntryRequest#PARAM_CONFIRMED} <br>
//     * @deprecated Never used
//     */
//    public static final String ACTION_CONFIRM_DEBIT_TRANS = "com.pax.us.pay.action.CONFIRM_DEBIT_TRANS";
    /**
     * Activity Action: Confirm Card Present <br>{@value #ACTION_CHECK_CARD_PRESENT}<br>
     * <p>If not implemented, BroadPOS will try to start action {@value #ACTION_CONFIRM_UNIFIED_MESSAGE}</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} - {@value EntryExtraData#PARAM_PACKAGE}  is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} - {@value EntryExtraData#PARAM_MESSAGE} Message shown on UI<br>
     *     Type: String<br>
     *     It is optional. Reversed if some BroadPOS app prefer customized message.<br>
     * </p>
     * <p>
     *     If {@link EntryExtraData#PARAM_MESSAGE} exist, recommend display it.<br>
     *     Of course, you can display your own message, Like: <br>
     *     "Card Present?"<br>
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     Options: {@link ConfirmationType#YES} and {@link ConfirmationType#NO}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} - {@value EntryExtraData#PARAM_TIMEOUT}<br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000. <br>
     *     For reference only, BroadPOS handles timeout.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} - {@value EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@value ConfirmationType#YES}, return TRUE.
     * </p>
     */
    public static final String ACTION_CHECK_CARD_PRESENT = "com.pax.us.pay.action.CONFIRM_CARD_PRESENT";

    /**
     * Activity Action: Confirm Deactivate Warning <br>{@value #ACTION_CHECK_DEACTIVATE_WARN}<br>
     * This action is host dependent.<br>
     * <p>If not implemented, BroadPOS will try to start action {@link #ACTION_CONFIRM_UNIFIED_MESSAGE}</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} - {@value EntryExtraData#PARAM_PACKAGE}  is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} - {@value EntryExtraData#PARAM_MESSAGE} Message shown on UI<br>
     *     Type: String<br>
     *     It is optional. Reversed if some BroadPOS app prefer customized message.<br>
     * </p>
     * <p>
     *     If {@link EntryExtraData#PARAM_MESSAGE} exist, recommend display it.<br>
     *     Of course, you can display your own message, Like: <br>
     *     "Card Will Be Deactivated, Are You Sure?"<br>
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     Options: {@link ConfirmationType#YES}  and {@link ConfirmationType#NO}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} - {@value EntryExtraData#PARAM_TIMEOUT}<br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000. <br>
     *     For reference only, BroadPOS handles timeout.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} - {@value EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@value ConfirmationType#YES}, return TRUE.
     * </p>
     */
    public static final String ACTION_CHECK_DEACTIVATE_WARN = "com.pax.us.pay.action.CONFIRM_DEACTIVATE_WARN";


    /**
     * Activity Action: Confirm Batch Close Warning <br>{@value #ACTION_CONFIRM_BATCH_CLOSE}<br>
     * <p>If not implemented, BroadPOS will try to start action {@link #ACTION_CONFIRM_UNIFIED_MESSAGE}</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} - {@value EntryExtraData#PARAM_PACKAGE}  is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} - {@value EntryExtraData#PARAM_MESSAGE}  Message shown on UI<br>
     *     Type: String<br>
     *     It is optional. Reversed if some BroadPOS app prefer customized message.<br>
     * </p>
     * <p>
     *     If {@link EntryExtraData#PARAM_MESSAGE} exist, recommend display it.<br>
     *     Of course, you can display your own message, Like: <br>
     *     "Close Batch?"<br>
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     Options: {@link ConfirmationType#YES}  and {@link ConfirmationType#NO}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} - {@value EntryExtraData#PARAM_TIMEOUT}<br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000. <br>
     *     For reference only, BroadPOS handles timeout.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} - {@value EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@value ConfirmationType#YES}, return TRUE.
     * </p>
     */
    public static final String ACTION_CONFIRM_BATCH_CLOSE = "com.pax.us.pay.action.CONFIRM_BATCH_CLOSE";


    /**
     * Activity Action: Confirm Close With Untipped <br>{@value #ACTION_CONFIRM_UNTIPPED}<br>
     * <p>If not implemented, BroadPOS will try to start action {@link #ACTION_CONFIRM_UNIFIED_MESSAGE}</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} - {@value EntryExtraData#PARAM_PACKAGE}  is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} - {@value EntryExtraData#PARAM_MESSAGE}  Message shown on UI<br>
     *     Type: String<br>
     *     It is optional. Reversed if some BroadPOS app prefer customized message.<br>
     * </p>
     * <p>
     *     If {@link EntryExtraData#PARAM_MESSAGE} exist, recommend display it.<br>
     *     Of course, you can display your own message, Like: <br>
     *     "Close Batch with Untipped Trans.?"<br>
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     Options: {@link ConfirmationType#YES}  and {@link ConfirmationType#NO}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} - {@value EntryExtraData#PARAM_TIMEOUT}<br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000. <br>
     *     For reference only, BroadPOS handles timeout.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} - {@value EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@value ConfirmationType#YES}, return TRUE.
     * </p>
     */
    public static final String ACTION_CONFIRM_UNTIPPED = "com.pax.us.pay.action.CONFIRM_UNTIPPED";

    /**
     * Activity Action: Duplicate transaction approval <br>{@value #ACTION_CONFIRM_DUPLICATE_TRANS}<br>
     * <p>If not implemented, BroadPOS will try to start action {@link #ACTION_CONFIRM_UNIFIED_MESSAGE}</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} - {@value EntryExtraData#PARAM_PACKAGE}  is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} - {@value EntryExtraData#PARAM_MESSAGE}  Message shown on UI<br>
     *     Type: String<br>
     *     It is optional. Reversed if some BroadPOS app prefer customized message.<br>
     * </p>
     * <p>
     *     If {@link EntryExtraData#PARAM_MESSAGE} exist, recommend display it.<br>
     *     Of course, you can display your own message, Like: <br>
     *     "Duplicated Trans.\nDo you wish to continue?"<br>
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     Options: {@link ConfirmationType#YES}  and {@link ConfirmationType#NO}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} - {@value EntryExtraData#PARAM_TIMEOUT}<br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000. <br>
     *     For reference only, BroadPOS handles timeout.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} - {@value EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@value ConfirmationType#YES}, return TRUE.
     * </p>
     */
    public static final String ACTION_CONFIRM_DUPLICATE_TRANS = "com.pax.us.pay.action.CONFIRM_DUPLICATE_TRANS";

    /**
     * Activity Action: Confirm Surcharge Fee <br>{@value #ACTION_CONFIRM_SURCHARGE_FEE}<br>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} - {@value EntryExtraData#PARAM_PACKAGE}  is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_SURCHARGE_FEE_NAME}<br>
     *     Type: String<br>
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_CURRENCY} - {@value EntryExtraData#PARAM_CURRENCY} is currency type. <br>
     *     Type: String<br>
     *     Default value: {@link com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType#USD}
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_TOTAL_AMOUNT} - {@value EntryExtraData#PARAM_TOTAL_AMOUNT} <br>
     *     Type: Long
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_SURCHARGE_FEE}  <br>
     *     Type: Long
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_ENABLE_BYPASS} Optional <br>
     *     Type: Boolean<br>
     *     TRUE means Bypass is allowable
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     Options: {@link ConfirmationType#YES}  and {@link ConfirmationType#NO}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} - {@value EntryExtraData#PARAM_TIMEOUT}<br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000. <br>
     *     For reference only, BroadPOS handles timeout.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} - {@value EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@value ConfirmationType#YES}, return TRUE.
     * </p>
     */
    public static final String ACTION_CONFIRM_SURCHARGE_FEE = "com.pax.us.pay.action.CONFIRM_SURCHARGE_FEE";

    /**
     * Activity Action: Confirm Service Fee <br>{@value #ACTION_CONFIRM_SERVICE_FEE}<br>
     * This action is host dependent.<br>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} - {@value EntryExtraData#PARAM_PACKAGE}  is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_SERVICE_FEE_NAME} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_CURRENCY} - {@value EntryExtraData#PARAM_CURRENCY} is currency type. <br>
     *     Type: String<br>
     *     Default value: {@link com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType#USD}
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_TOTAL_AMOUNT} - {@value EntryExtraData#PARAM_TOTAL_AMOUNT} <br>
     *     Type: Long
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_SERVICE_FEE}<br>
     *     Type: Long
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     Options: {@link ConfirmationType#YES}  and {@link ConfirmationType#NO}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} - {@value EntryExtraData#PARAM_TIMEOUT}<br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000. <br>
     *     For reference only, BroadPOS handles timeout.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} - {@value EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@value ConfirmationType#YES}, return TRUE.
     * </p>
     */
    public static final String ACTION_CONFIRM_SERVICE_FEE = "com.pax.us.pay.action.CONFIRM_SERVICE_FEE";

    /**
     * Activity Action: Confirm Printer Status <br>{@value #ACTION_CONFIRM_PRINTER_STATUS}<br>
     * <p>If not implemented, BroadPOS will try to start action {@link #ACTION_CONFIRM_UNIFIED_MESSAGE}</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} - {@value EntryExtraData#PARAM_PACKAGE}  is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_PRINT_STATUS} <br>
     *     Type: String<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.PrintStatusType}
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} - {@value EntryExtraData#PARAM_MESSAGE}  Message shown on UI<br>
     *     Type: String<br>
     *     It is optional. Reversed if some BroadPOS app prefer customized message.<br>
     * </p>
     * <p>
     *     If {@link EntryExtraData#PARAM_MESSAGE} exist, recommend display it.<br>
     *     Of course, you can display your own message, Like: <br>
     *     if printer status is {@link com.pax.us.pay.ui.constant.entry.enumeration.PrintStatusType#PRINTER_OUT_OF_PAPER},<br>
     *     display "Printer Out Of Paper, Retry?"<br>
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     Options: {@link ConfirmationType#YES}  and {@link ConfirmationType#NO}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} - {@value EntryExtraData#PARAM_TIMEOUT}<br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000. <br>
     *     For reference only, BroadPOS handles timeout.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} - {@value EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@value ConfirmationType#YES}, return TRUE.
     * </p>
     */
    public static final String ACTION_CONFIRM_PRINTER_STATUS = "com.pax.us.pay.action.CONFIRM_PRINTER_STATUS";

    /**
     * Activity Action: Confirm Upload Transaction <br>{@value #ACTION_CONFIRM_UPLOAD_TRANS}<br>
     * <p>If not implemented, BroadPOS will try to start action {@link #ACTION_CONFIRM_UNIFIED_MESSAGE}</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} - {@value EntryExtraData#PARAM_PACKAGE}  is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} - {@value EntryExtraData#PARAM_MESSAGE}  Message shown on UI<br>
     *     Type: String<br>
     *     It is optional. Reversed if some BroadPOS app prefer customized message.<br>
     * </p>
     * <p>
     *     If {@link EntryExtraData#PARAM_MESSAGE} exist, recommend display it.<br>
     *     Of course, you can display your own message, Like: <br>
     *     "Upload Transaction, Are You Sure?"<br>
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     Options: {@link ConfirmationType#YES}  and {@link ConfirmationType#NO}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} - {@value EntryExtraData#PARAM_TIMEOUT}<br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000. <br>
     *     For reference only, BroadPOS handles timeout.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} - {@value EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@value ConfirmationType#YES}, return TRUE.
     * </p>
     */
    public static final String ACTION_CONFIRM_UPLOAD_TRANS = "com.pax.us.pay.action.CONFIRM_UPLOAD_TRANS";

    /**
     * Activity Action: Confirm Retry Upload Transaction <br>{@value #ACTION_CONFIRM_UPLOAD_RETRY}<br>
     * <p>If not implemented, BroadPOS will try to start action {@link #ACTION_CONFIRM_UNIFIED_MESSAGE}</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} - {@value EntryExtraData#PARAM_PACKAGE}  is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} - {@value EntryExtraData#PARAM_MESSAGE}  Message shown on UI<br>
     *     Type: String<br>
     *     It is optional. Reversed if some BroadPOS app prefer customized message.<br>
     * </p>
     * <p>
     *     If {@link EntryExtraData#PARAM_MESSAGE} exist, recommend display it.<br>
     *     Of course, you can display your own message, Like: <br>
     *     "Upload Retry?"<br>
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     Options: {@link ConfirmationType#YES}  and {@link ConfirmationType#NO}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} - {@value EntryExtraData#PARAM_TIMEOUT}<br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000. <br>
     *     For reference only, BroadPOS handles timeout.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} - {@value EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@value ConfirmationType#YES}, return TRUE.
     * </p>
     */
    public static final String ACTION_CONFIRM_UPLOAD_RETRY = "com.pax.us.pay.action.CONFIRM_UPLOAD_RETRY";

    /**
     * Activity Action: Confirm Print Failed Transaction <br>{@value #ACTION_CONFIRM_PRINT_FAILED_TRANS}<br>
     * <p>If not implemented, BroadPOS will try to start action {@link #ACTION_CONFIRM_UNIFIED_MESSAGE}</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} - {@value EntryExtraData#PARAM_PACKAGE}  is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} - {@value EntryExtraData#PARAM_MESSAGE}  Message shown on UI<br>
     *     Type: String<br>
     *     It is optional. Reversed if some BroadPOS app prefer customized message.<br>
     * </p>
     * <p>
     *     If {@link EntryExtraData#PARAM_MESSAGE} exist, recommend display it.<br>
     *     Of course, you can display your own message, Like: <br>
     *     "Print Failed Trans.?"<br>
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     Options: {@link ConfirmationType#YES}  and {@link ConfirmationType#NO}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} - {@value EntryExtraData#PARAM_TIMEOUT}<br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000. <br>
     *     For reference only, BroadPOS handles timeout.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} - {@value EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@value ConfirmationType#YES}, return TRUE.
     * </p>
     */
    public static final String ACTION_CONFIRM_PRINT_FAILED_TRANS = "com.pax.us.pay.action.CONFIRM_PRINT_FAILED_TRANS";

    /**
     * Activity Action: Confirm Print Receipt <br>{@value #ACTION_CONFIRM_PRINT_FPS}<br>
     * <p>If not implemented, BroadPOS will try to start action {@link #ACTION_CONFIRM_UNIFIED_MESSAGE}</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} - {@value EntryExtraData#PARAM_PACKAGE}  is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} - {@value EntryExtraData#PARAM_MESSAGE}  Message shown on UI<br>
     *     Type: String<br>
     *     It is optional. Reversed if some BroadPOS app prefer customized message.<br>
     * </p>
     * <p>
     *     If {@link EntryExtraData#PARAM_MESSAGE} exist, recommend display it.<br>
     *     Of course, you can display your own message, Like: <br>
     *     "Do You Want To Print Receipt?"<br>
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     Options: {@link ConfirmationType#YES}  and {@link ConfirmationType#NO}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} - {@value EntryExtraData#PARAM_TIMEOUT}<br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000. <br>
     *     For reference only, BroadPOS handles timeout.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} - {@value EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@value ConfirmationType#YES}, return TRUE.
     * </p>
     */
    public static final String ACTION_CONFIRM_PRINT_FPS = "com.pax.us.pay.action.CONFIRM_PRINT_FPS";


    /**
     * Activity Action: Confirm Delete SF transaction  <br>{@value #ACTION_CONFIRM_DELETE_SF}<br>
     * <p>If not implemented, BroadPOS will try to start action {@link #ACTION_CONFIRM_UNIFIED_MESSAGE}</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} - {@value EntryExtraData#PARAM_PACKAGE}  is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} - {@value EntryExtraData#PARAM_MESSAGE}  Message shown on UI<br>
     *     Type: String<br>
     *     It is optional. Reversed if some BroadPOS app prefer customized message.<br>
     * </p>
     * <p>
     *     If {@link EntryExtraData#PARAM_MESSAGE} exist, recommend display it.<br>
     *     Of course, you can display your own message, Like: <br>
     *     "Delete SF Records, Are You Sure?"<br>
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     Options: {@link ConfirmationType#YES}  and {@link ConfirmationType#NO}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} - {@value EntryExtraData#PARAM_TIMEOUT}<br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000. <br>
     *     For reference only, BroadPOS handles timeout.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} - {@value EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@value ConfirmationType#YES}, return TRUE.
     * </p>
     */
    public static final String ACTION_CONFIRM_DELETE_SF = "com.pax.us.pay.action.CONFIRM_DELETE_SF";

    /**
     * Activity Action: Confirm Print Customer Receipt <br>{@value #ACTION_CONFIRM_PRINT_CUSTOMER_COPY}<br>
     * <p>If not implemented, BroadPOS will try to start action {@link #ACTION_CONFIRM_UNIFIED_MESSAGE}</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} - {@value EntryExtraData#PARAM_PACKAGE}  is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} - {@value EntryExtraData#PARAM_MESSAGE}  Message shown on UI<br>
     *     Type: String<br>
     *     It is optional. Reversed if some BroadPOS app prefer customized message.<br>
     * </p>
     * <p>
     *     If {@link EntryExtraData#PARAM_MESSAGE} exist, recommend display it.<br>
     *     Of course, you can display your own message, Like: <br>
     *     "Please Tear Receipt.\nPrint Customer Receipt?"<br>
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} - {@value EntryExtraData#PARAM_TIMEOUT}<br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000. <br>
     *     For reference only, BroadPOS handles timeout.
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     Options: {@link ConfirmationType#YES}  and {@link ConfirmationType#NO}
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} - {@value EntryRequest#PARAM_CONFIRMED} YES: OK  NO:CANCEL  <br>
     */
    public static final String ACTION_CONFIRM_PRINT_CUSTOMER_COPY = "com.pax.us.pay.action.CONFIRM_PRINT_CUSTOMER_COPY";

    /**
     * Activity Action: Batch close for Application update <br>{@value #ACTION_CONFIRM_BATCH_FOR_APPLICATION_UPDATE}<br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} - {@value EntryExtraData#PARAM_MESSAGE} <br>
     * request: {@link EntryRequest#PARAM_CONFIRMED} - {@value EntryRequest#PARAM_CONFIRMED} <br>
     * @deprecated Never used.
     */
    public static final String ACTION_CONFIRM_BATCH_FOR_APPLICATION_UPDATE = "com.pax.us.pay.action.CONFIRM_BATCH_FOR_APPLICATION_UPDATE";

    /**
     * Activity Action: Retry Online <br>{@value #ACTION_CONFIRM_ONLINE_RETRY}<br>
     * <p>If not implemented, BroadPOS will try to start action {@link #ACTION_CONFIRM_UNIFIED_MESSAGE}</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} - {@value EntryExtraData#PARAM_PACKAGE}  is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} - {@value EntryExtraData#PARAM_MESSAGE}  Message shown on UI<br>
     *     Type: String<br>
     *     It is optional. Reversed if some BroadPOS app prefer customized message.<br>
     * </p>
     * <p>
     *     If {@link EntryExtraData#PARAM_MESSAGE} exist, recommend display it.<br>
     *     Of course, you can display your own message, Like: <br>
     *     "Online Failed, Retry?"<br>
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     Options: {@link ConfirmationType#YES}  and {@link ConfirmationType#NO}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} - {@value EntryExtraData#PARAM_TIMEOUT}<br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000. <br>
     *     For reference only, BroadPOS handles timeout.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} - {@value EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@value ConfirmationType#YES}, return TRUE.
     * </p>
     */
    public static final String ACTION_CONFIRM_ONLINE_RETRY = "com.pax.us.pay.action.CONFIRM_ONLINE_RETRY";


    /**
     * Activity Action: Confirm Tip Edit <br>{@value #ACTION_CONFIRM_ADJUST_TIP}<br>
     * <p>If not implemented, BroadPOS will try to start action {@link #ACTION_CONFIRM_UNIFIED_MESSAGE}</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} - {@value EntryExtraData#PARAM_PACKAGE}  is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} - {@value EntryExtraData#PARAM_MESSAGE}  Message shown on UI<br>
     *     Type: String<br>
     *     It is optional. Reversed if some BroadPOS app prefer customized message.<br>
     * </p>
     * <p>
     *     If {@link EntryExtraData#PARAM_MESSAGE} exist, recommend display it.<br>
     *     Of course, you can display your own message, Like: <br>
     *     "Would you like to add this tip?"<br>
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     Options: {@link ConfirmationType#YES}  and {@link ConfirmationType#NO}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} - {@value EntryExtraData#PARAM_TIMEOUT}<br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000. <br>
     *     For reference only, BroadPOS handles timeout.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} - {@value EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@value ConfirmationType#YES}, return TRUE.
     * </p>
     */
    public static final String ACTION_CONFIRM_ADJUST_TIP = "com.pax.us.pay.action.CONFIRM_ADJUST_TIP";

    /**
     * Activity Action: Confirm Card Process Result<br>{@value #ACTION_CONFIRM_CARD_PROCESS_RESULT}<br>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} - {@value EntryExtraData#PARAM_PACKAGE}  is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} - {@value EntryExtraData#PARAM_MESSAGE}<br>
     *     Type: String<br>
     *     Generally it is some card error message. Like "1-Chip Malfunction"
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     Options: {@link ConfirmationType#YES} - {@value ConfirmationType#YES}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} - {@value EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 5000.<br>
     *     If timeout, treat it as CONFIRMED.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} - {@value EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@value ConfirmationType#YES}, return TRUE.
     * </p>
     */
    public static final String ACTION_CONFIRM_CARD_PROCESS_RESULT = "com.pax.us.pay.action.CONFIRM_CARD_PROCESS_RESULT";


    /**
     * Activity Action: Confirm the Receipt need to be Signed or not<br>{@value #ACTION_CONFIRM_RECEIPT_SIGNATURE}<br>
     * This action is host dependent.<br>
     * <p>If not implemented, BroadPOS will try to start action {@link #ACTION_CONFIRM_UNIFIED_MESSAGE}</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} - {@value EntryExtraData#PARAM_PACKAGE}  is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} - {@value EntryExtraData#PARAM_MESSAGE}<br>
     *     Type: String<br>
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} - {@value EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 5000.
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     Value is {@link ConfirmationType#YES} - {@value ConfirmationType#YES}
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} - {@value EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@value ConfirmationType#YES}, return TRUE.
     * </p>
     */
    public static final String ACTION_CONFIRM_RECEIPT_SIGNATURE = "com.pax.us.pay.action.CONFIRM_RECEIPT_SIGNATURE";
    /**
     * Activity Action: Receipt Print Preview <br>{@value #ACTION_CONFIRM_RECEIPT_VIEW}<br>
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
     *     Input:  {@link EntryExtraData#PARAM_RECEIPT_URI} <br>
     *     Type: String
     * </p>
     * Example Code for convert uri to Bitmap:
     * <pre>
     *         Uri imageUri = Uri.parse(receiptUri);
     *         try {
     *             if (Build.VERSION.SDK_INT &lt; Build.VERSION_CODES.Q) {
     *                 bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
     *             } else {
     *                 ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(), imageUri);
     *                 ImageDecoder.OnHeaderDecodedListener listener = new ImageDecoder.OnHeaderDecodedListener() {
     *                     &#64;Override
     *                     public void onHeaderDecoded(@NonNull ImageDecoder decoder, @NonNull ImageDecoder.ImageInfo info, @NonNull ImageDecoder.Source source) {
     *                         decoder.setAllocator(ImageDecoder.ALLOCATOR_SOFTWARE);
     *                         decoder.setMutableRequired(true);
     *                     }
     *                 };
     *                 bitmap = ImageDecoder.decodeBitmap(source);
     *             }
     * </pre>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} - {@value EntryExtraData#PARAM_TIMEOUT}<br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000. <br>
     *     For reference only, BroadPOS handles timeout.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} - {@value EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@value ConfirmationType#YES}, return TRUE.
     * </p>
     */
    public static final String ACTION_CONFIRM_RECEIPT_VIEW = "com.pax.us.pay.action.CONFIRM_RECEIPT_VIEW";

    /**
     * Activity Action: Confirm Balance<br>{@value #ACTION_CONFIRM_BALANCE}<br>
     * <p>If not implemented, BroadPOS will try to start action {@link #ACTION_CONFIRM_UNIFIED_MESSAGE}</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} - {@value EntryExtraData#PARAM_PACKAGE}  is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_CURRENCY} - {@value EntryExtraData#PARAM_CURRENCY} is currency type. <br>
     *     Type: String<br>
     *     Default value: {@link com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType#USD}
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_BALANCE} - {@value EntryExtraData#PARAM_BALANCE}  <br>
     *     Type: Long
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} - {@value EntryExtraData#PARAM_TIMEOUT}<br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000. <br>
     *     For reference only, BroadPOS handles timeout.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} - {@value EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@value ConfirmationType#YES}, return TRUE.
     * </p>
     */
    public static final String ACTION_CONFIRM_BALANCE = "com.pax.us.pay.action.CONFIRM_BALANCE";

    /**
     * Activity Action: Confirm Merchant<br>{@value #ACTION_CONFIRM_MERCHANT_SCOPE}<br>
     * <p>Used when multi-merchant feature is enabled.</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} - {@value EntryExtraData#PARAM_PACKAGE}  is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} - {@value EntryExtraData#PARAM_MESSAGE}  Message shown on UI<br>
     *     Type: String<br>
     *     It is optional. Reversed if some BroadPOS app prefer customized message.<br>
     * </p>
     * <p>
     *     If {@link EntryExtraData#PARAM_MESSAGE} exist, recommend display it.<br>
     *     Of course, you can display your own message, Like: <br>
     *     "Apply for all merchants or current?"<br>
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS} <br>
     *     Type: String[]<br>
     *     Options: {@link com.pax.us.pay.ui.constant.entry.enumeration.MerchantScope#CURRENT} and {@link com.pax.us.pay.ui.constant.entry.enumeration.MerchantScope#ALL}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} - {@value EntryExtraData#PARAM_TIMEOUT}<br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000. <br>
     *     For reference only, BroadPOS handles timeout.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} - {@value EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@link com.pax.us.pay.ui.constant.entry.enumeration.MerchantScope#CURRENT}, return TRUE.
     * </p>
     */
    public static final String ACTION_CONFIRM_MERCHANT_SCOPE = "com.pax.us.pay.action.CONFIRM_MERCHANT_SCOPE";


    /**
     * Activity Action: Confirm Card Entry Retry<br>{@value #ACTION_CONFIRM_CARD_ENTRY_RETRY}<br>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} - {@value EntryExtraData#PARAM_PACKAGE}  is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} - {@value EntryExtraData#PARAM_MESSAGE} Message shown on UI<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS} <br>
     *     Type: String[]<br>
     *     Options: {@link com.pax.us.pay.ui.constant.entry.enumeration.CardEntryRetryOption#CONTINUE} and {@link com.pax.us.pay.ui.constant.entry.enumeration.CardEntryRetryOption#DECLINE} 
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} - {@value EntryExtraData#PARAM_TIMEOUT}<br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000. <br>
     *     For reference only, BroadPOS handles timeout.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} - {@value EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@value com.pax.us.pay.ui.constant.entry.enumeration.CardEntryRetryOption#CONTINUE}, return TRUE.
     * </p>
     */
    public static final String ACTION_CONFIRM_CARD_ENTRY_RETRY = "com.pax.us.pay.action.CONFIRM_CARD_ENTRY_RETRY";

    /**
     * Activity Action: Confirm Signature Match<br>{@value #ACTION_CONFIRM_SIGNATURE_MATCH}<br>
     * This action is host dependent.<br>
     * <p>If not implemented, BroadPOS will try to start action {@link #ACTION_CONFIRM_UNIFIED_MESSAGE}</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} - {@value EntryExtraData#PARAM_PACKAGE}  is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} - {@value EntryExtraData#PARAM_MESSAGE}  Message shown on UI<br>
     *     Type: String<br>
     *     It is optional. Reversed if some BroadPOS app prefer customized message.<br>
     * </p>
     * <p>
     *     If {@link EntryExtraData#PARAM_MESSAGE} exist, recommend display it.<br>
     *     Of course, you can display your own message, Like: <br>
     *     "Does the cardholder signature match?"<br>
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS} - {@value EntryExtraData#PARAM_OPTIONS} <br>
     *     Type: String[]<br>
     *     Options: {@link ConfirmationType#YES} and {@link ConfirmationType#NO}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} - {@value EntryExtraData#PARAM_TIMEOUT}<br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000. <br>
     *     For reference only, BroadPOS handles timeout.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} - {@value EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@value ConfirmationType#YES}, return TRUE.
     * </p>
     */
    public static final String ACTION_CONFIRM_SIGNATURE_MATCH = "com.pax.us.pay.action.CONFIRM_SIGNATURE_MATCH";

    /**
     * Activity Action: Display QRCode Receipt<br>{@value #ACTION_DISPLAY_QR_CODE_RECEIPT}<br>
     * <p>
     *     This UI is used to display a QRCode of receipt so that maybe customer could scan it and save to their phone.<br>
     *     This action request nothing. You can request go to next step like this:
     * </p>
     * <pre>
     *     Bundle bundle = new Bundle();
     *     bundle.putStringExtra(EntryRequest.PARAM_ACTION, ConfirmationEntry.ACTION_DISPLAY_QR_CODE_RECEIPT);
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
     *     Input:  {@link EntryExtraData#PARAM_QR_CODE_CONTENT} - {@value EntryExtraData#PARAM_QR_CODE_CONTENT} <br>
     *     Type: String
     * </p>
     * Example Code for convert QRCodeContent to Bitmap:<br>
     * <pre>
     * private Bitmap createQRCode(public static final String content, int qrCodeSize, Bitmap logo){
     *         Matrix m = new Matrix();
     *         int imageWidth = 80;
     *         int imageHeight = 80;
     *         m.setScale((float) imageWidth/logo.getWidth(), (float)imageHeight/logo.getHeight());
     *         logo = Bitmap.createBitmap(logo, 0, 0,
     *                 logo.getWidth(), logo.getHeight(), m, false);
     *         MultiFormatWriter writer = new MultiFormatWriter();
     *         Hashtable&lt;EncodeHintType, Object&gt; hst = new Hashtable&lt;&gt;();
     *         hst.put(EncodeHintType.CHARACTER_SET, "UTF-8");
     *         BitMatrix matrix = null;
     *         try {
     *             matrix = writer.encode(content, BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize, hst);
     *         } catch (Exception e) {
     *             Logger.e(e);
     *             return null;
     *         }
     *         int width = matrix.getWidth();
     *         int height = matrix.getHeight();
     *         int halfW = width / 2;
     *         int halfH = height / 2;
     *         int[] pixels = new int[width * height];
     *
     *         for (int y = 0; y &lt; height; y++) {
     *             for (int x = 0; x &lt; width; x++) {
     *                 if (x &gt; halfW - imageWidth/2 &amp;&amp; x &lt; halfW + imageWidth/2
     *                         &amp;&amp; y &gt; halfH - imageHeight/2 &amp;&amp; y &lt; halfH + imageHeight/2) {
     *                     pixels[y * width + x] = logo.getPixel(x - halfW
     *                             + imageWidth/2, y - halfH + imageHeight/2);
     *                 } else {
     *                     if (matrix.get(x, y)) {
     *                         pixels[y * width + x] = 0xff000000;
     *                     }
     *                 }
     *
     *             }
     *         }
     *         logo.recycle();
     *         Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
     *         bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
     *         return bitmap;
     *     }
     * </pre>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} - {@value EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 20000.
     * </p>
     * <p>
     *     Output: Nothing
     * </p>
     */
    public static final String ACTION_DISPLAY_QR_CODE_RECEIPT = "com.pax.us.pay.action.DISPLAY_QR_CODE_RECEIPT";

    /**
     * Activity Action: Confirm DCC information <br>{@value #ACTION_CONFIRM_DCC}<br>
     * This action is host dependent.<br>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} - {@value EntryExtraData#PARAM_PACKAGE}  is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_AMOUNT_MESSAGE} - {@value EntryExtraData#PARAM_AMOUNT_MESSAGE}<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_EXCHANGE_RATE} - {@value EntryExtraData#PARAM_EXCHANGE_RATE}<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_CURRENCY_ALPHA_CODE} - {@value EntryExtraData#PARAM_CURRENCY_ALPHA_CODE}<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MARGIN} - {@value EntryExtraData#PARAM_MARGIN} <br>
     *     Type: String
     *     </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_FOREIGN_AMOUNT_MESSAGE} - {@value EntryExtraData#PARAM_FOREIGN_AMOUNT_MESSAGE} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_CONFIRM_WITH_CURRENCY} - {@value EntryExtraData#PARAM_CONFIRM_WITH_CURRENCY} <br>
     *     Type: Boolean
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} - {@value EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 20000.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} - {@value EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If {@link EntryExtraData#PARAM_CONFIRM_WITH_CURRENCY} is true and select "USD", return TRUE.
     * </p>
     */
    public static final String ACTION_CONFIRM_DCC = "com.pax.us.pay.action.CONFIRM_DCC";


    /**
     * Activity Action: Confirm total amount<br>{@value #ACTION_CONFIRM_TOTAL_AMOUNT}<br>
     * <p>If not implemented, BroadPOS will try to start action {@link #ACTION_CONFIRM_UNIFIED_MESSAGE}</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} - {@value EntryExtraData#PARAM_PACKAGE}  is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_CURRENCY} - {@value EntryExtraData#PARAM_CURRENCY} is currency type. <br>
     *     Type: String<br>
     *     Default value: {@link com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType#USD}
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_TOTAL_AMOUNT} - {@value EntryExtraData#PARAM_TOTAL_AMOUNT}  <br>
     *     Type: Long
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} - {@value EntryExtraData#PARAM_TIMEOUT}<br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000. <br>
     *     For reference only, BroadPOS handles timeout.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} - {@value EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@value ConfirmationType#YES}, return TRUE.
     * </p>
     */
    public static final String ACTION_CONFIRM_TOTAL_AMOUNT = "com.pax.us.pay.action.CONFIRM_TOTAL_AMOUNT";

}
