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
     * Activity Category: CONFIRMATION
     */
    public static final String CATEGORY = "com.pax.us.pay.ui.category.CONFIRMATION";

    /**
     * Activity Action: General Confirmation with Message<br>
     * <p>
     *     If specific confirmation activity not found in customized app, <br>
     *     BroadPOS will try start this general confirmation activity.<br>
     *     Example:<br>
     *     If BroadPOS try to start Activity {@link #ACTION_CHECK_CARD_PRESENT}, but your app does not implement it.<br>
     *     then BroadPOS will try to start this general confirmation Activity ("com.pax.us.pay.action.CONFIRM_UNIFIED_MESSAGE").
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} Message shown on UI<br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@link ConfirmationType#YES}, return TRUE.
     * </p>
     */
    public static final String ACTION_CONFIRM_UNIFIED_MESSAGE = "com.pax.us.pay.action.CONFIRM_UNIFIED_MESSAGE";


    /**
     * Activity Action: Reverse or Accept a Partial-approval transaction<br>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_CURRENCY} is currency type. <br>
     *     Type: String<br>
     *     Default value: {@link com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType#USD}
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_APPROVED_AMOUNT} <br>
     *     Type: Long
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_TOTAL_AMOUNT} <br>
     *     Type: Long
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} <br>
     *     Message shown on UI<br>
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
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS} <br>
     *     Type: String[]<br>
     *     Options are {@link PartialApprovalOption#REVERSE} and {@link PartialApprovalOption#ACCEPT}<br>
     *     If select {@link PartialApprovalOption#REVERSE}, the transaction will be voided.<br>
     *     If select {@link PartialApprovalOption#ACCEPT}, the transaction will be accepted with partial-approval.
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@link PartialApprovalOption#REVERSE}, return TRUE<br>
     *     If select {@link PartialApprovalOption#ACCEPT}, return FALSE
     * </p>
     */
    public static final String ACTION_REVERSE_PARTIAL_APPROVAL = "com.pax.us.pay.action.REVERSE_PARTIAL_APPROVAL";


    /**
     * Activity Action: Confirm If Start a New Payment when get partial-approval<br>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_CURRENCY} is currency type. <br>
     *     Type: String<br>
     *     Default value: {@link com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType#USD}
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_APPROVED_AMOUNT} <br>
     *     Type: Long
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_TOTAL_AMOUNT} <br>
     *     Type: Long
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} <br>
     *     Message shown on UI<br>
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
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS} <br>
     *     Type: String[]<br>
     *     If select {@link PartialApprovalOption#ACCEPT}, a new payment will be started.<br>
     *     If select {@link PartialApprovalOption#DECLINE}, this transaction will be accepted with partial-approval.
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@link PartialApprovalOption#ACCEPT}, return TRUE<br>
     *     If select {@link PartialApprovalOption#DECLINE}, return FALSE
     * </p>
     */
    public static final String ACTION_SUPPLEMENT_PARTIAL_APPROVAL = "com.pax.us.pay.action.SUPPLEMENT_PARTIAL_APPROVAL";

    /**
     * The intent action of select the transaction online retry or offline <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * request: {@link EntryRequest#PARAM_CONFIRMED} <br>
     * @deprecated Not used any more. This feature has been removed.
     */
    public static final String ACTION_CONFIRM_ONLINE_RETRY_OFFLINE = "com.pax.us.pay.action.CONFIRM_ONLINE_RETRY_OFFLINE";

    /**
     * The intent action of select the credit type <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * request: {@link EntryRequest#PARAM_CONFIRMED} <br>
     * @deprecated Not used any more
     */
    public static final String ACTION_CONFIRM_DEBIT_TRANS = "com.pax.us.pay.action.CONFIRM_DEBIT_TRANS";
    /**
     * Activity Action: Confirm Card Present <br>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} <br>
     *     Message shown on UI<br>
     *     Type: String<br>
     *     It is optional. Reversed if some BroadPOS app prefer customized message.<br>
     * </p>
     * <p>
     *     If {@link EntryExtraData#PARAM_MESSAGE} exist, recommend display it.<br>
     *     Of course, you can display your own message, Like: <br>
     *     "Card Present?"<br>
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     Values: {@link ConfirmationType#YES} and {@link ConfirmationType#NO}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@link ConfirmationType#YES}, return TRUE.
     * </p>
     */
    public static final String ACTION_CHECK_CARD_PRESENT = "com.pax.us.pay.action.CONFIRM_CARD_PRESENT";

    /**
     * Activity Action: Confirm Deactivate Warning <br>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} <br>
     *     Message shown on UI<br>
     *     Type: String<br>
     *     It is optional. Reversed if some BroadPOS app prefer customized message.<br>
     * </p>
     * <p>
     *     If {@link EntryExtraData#PARAM_MESSAGE} exist, recommend display it.<br>
     *     Of course, you can display your own message, Like: <br>
     *     "Card Will Be Deactivated, Are You Sure?"<br>
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     Values: {@link ConfirmationType#YES} and {@link ConfirmationType#NO}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@link ConfirmationType#YES}, return TRUE.
     * </p>
     */
    public static final String ACTION_CHECK_DEACTIVATE_WARN = "com.pax.us.pay.action.CONFIRM_DEACTIVATE_WARN";


    /**
     * Activity Action: Confirm Batch Close Warning <br>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} <br>
     *     Message shown on UI<br>
     *     Type: String<br>
     *     It is optional. Reversed if some BroadPOS app prefer customized message.<br>
     * </p>
     * <p>
     *     If {@link EntryExtraData#PARAM_MESSAGE} exist, recommend display it.<br>
     *     Of course, you can display your own message, Like: <br>
     *     "Close Batch?"<br>
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     Values: {@link ConfirmationType#YES} and {@link ConfirmationType#NO}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@link ConfirmationType#YES}, return TRUE.
     * </p>
     */
    public static final String ACTION_CONFIRM_BATCH_CLOSE = "com.pax.us.pay.action.CONFIRM_BATCH_CLOSE";


    /**
     * Activity Action: Confirm Close With Untipped <br>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} <br>
     *     Message shown on UI<br>
     *     Type: String<br>
     *     It is optional. Reversed if some BroadPOS app prefer customized message.<br>
     * </p>
     * <p>
     *     If {@link EntryExtraData#PARAM_MESSAGE} exist, recommend display it.<br>
     *     Of course, you can display your own message, Like: <br>
     *     "Close Batch with Untipped Trans.?"<br>
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     Values: {@link ConfirmationType#YES} and {@link ConfirmationType#NO}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@link ConfirmationType#YES}, return TRUE.
     * </p>
     */
    public static final String ACTION_CONFIRM_UNTIPPED = "com.pax.us.pay.action.CONFIRM_UNTIPPED";

    /**
     * Activity Action: Duplicate transaction approval <br>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} <br>
     *     Message shown on UI<br>
     *     Type: String<br>
     *     It is optional. Reversed if some BroadPOS app prefer customized message.<br>
     * </p>
     * <p>
     *     If {@link EntryExtraData#PARAM_MESSAGE} exist, recommend display it.<br>
     *     Of course, you can display your own message, Like: <br>
     *     "Duplicated Trans.\nDo you wish to continue?"<br>
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     Values: {@link ConfirmationType#YES} and {@link ConfirmationType#NO}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@link ConfirmationType#YES}, return TRUE.
     * </p>
     */
    public static final String ACTION_CONFIRM_DUPLICATE_TRANS = "com.pax.us.pay.action.CONFIRM_DUPLICATE_TRANS";

    /**
     * Activity Action: Confirm Surcharge Fee <br>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_SURCHARGE_FEE_NAME}<br>
     *     Type: String<br>
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_CURRENCY} is currency type. <br>
     *     Type: String<br>
     *     Default value: {@link com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType#USD}
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_TOTAL_AMOUNT} <br>
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
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     Values: {@link ConfirmationType#YES} and {@link ConfirmationType#NO}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@link ConfirmationType#YES}, return TRUE.
     * </p>
     */
    public static final String ACTION_CONFIRM_SURCHARGE_FEE = "com.pax.us.pay.action.CONFIRM_SURCHARGE_FEE";

    /**
     * Activity Action: Confirm Service Fee <br>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_SERVICE_FEE_NAME} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_CURRENCY} is currency type. <br>
     *     Type: String<br>
     *     Default value: {@link com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType#USD}
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_TOTAL_AMOUNT} <br>
     *     Type: Long
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_SERVICE_FEE}<br>
     *     Type: Long
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     Values: {@link ConfirmationType#YES} and {@link ConfirmationType#NO}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@link ConfirmationType#YES}, return TRUE.
     * </p>
     */
    public static final String ACTION_CONFIRM_SERVICE_FEE = "com.pax.us.pay.action.CONFIRM_SERVICE_FEE";

    /**
     * Activity Action: Confirm Printer Status <br>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_PRINT_STATUS} <br>
     *     Type: String<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.PrintStatusType}
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} <br>
     *     Message shown on UI<br>
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
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     Values: {@link ConfirmationType#YES} and {@link ConfirmationType#NO}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@link ConfirmationType#YES}, return TRUE.
     * </p>
     */
    public static final String ACTION_CONFIRM_PRINTER_STATUS = "com.pax.us.pay.action.CONFIRM_PRINTER_STATUS";

    /**
     * Activity Action: Confirm Upload Transaction <br>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} <br>
     *     Message shown on UI<br>
     *     Type: String<br>
     *     It is optional. Reversed if some BroadPOS app prefer customized message.<br>
     * </p>
     * <p>
     *     If {@link EntryExtraData#PARAM_MESSAGE} exist, recommend display it.<br>
     *     Of course, you can display your own message, Like: <br>
     *     "Upload Transaction, Are You Sure?"<br>
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     Values: {@link ConfirmationType#YES} and {@link ConfirmationType#NO}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@link ConfirmationType#YES}, return TRUE.
     * </p>
     */
    public static final String ACTION_CONFIRM_UPLOAD_TRANS = "com.pax.us.pay.action.CONFIRM_UPLOAD_TRANS";

    /**
     * Activity Action: Confirm Retry Upload Transaction <br>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} <br>
     *     Message shown on UI<br>
     *     Type: String<br>
     *     It is optional. Reversed if some BroadPOS app prefer customized message.<br>
     * </p>
     * <p>
     *     If {@link EntryExtraData#PARAM_MESSAGE} exist, recommend display it.<br>
     *     Of course, you can display your own message, Like: <br>
     *     "Upload Retry?"<br>
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     Values: {@link ConfirmationType#YES} and {@link ConfirmationType#NO}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@link ConfirmationType#YES}, return TRUE.
     * </p>
     */
    public static final String ACTION_CONFIRM_UPLOAD_RETRY = "com.pax.us.pay.action.CONFIRM_UPLOAD_RETRY";

    /**
     * Activity Action: Confirm Print Failed Transaction <br>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} <br>
     *     Message shown on UI<br>
     *     Type: String<br>
     *     It is optional. Reversed if some BroadPOS app prefer customized message.<br>
     * </p>
     * <p>
     *     If {@link EntryExtraData#PARAM_MESSAGE} exist, recommend display it.<br>
     *     Of course, you can display your own message, Like: <br>
     *     "Print Failed Trans.?"<br>
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     Values: {@link ConfirmationType#YES} and {@link ConfirmationType#NO}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@link ConfirmationType#YES}, return TRUE.
     * </p>
     */
    public static final String ACTION_CONFIRM_PRINT_FAILED_TRANS = "com.pax.us.pay.action.CONFIRM_PRINT_FAILED_TRANS";

    /**
     * Activity Action: Confirm Print Receipt <br>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} <br>
     *     Message shown on UI<br>
     *     Type: String<br>
     *     It is optional. Reversed if some BroadPOS app prefer customized message.<br>
     * </p>
     * <p>
     *     If {@link EntryExtraData#PARAM_MESSAGE} exist, recommend display it.<br>
     *     Of course, you can display your own message, Like: <br>
     *     "Do You Want To Print Receipt?"<br>
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     Values: {@link ConfirmationType#YES} and {@link ConfirmationType#NO}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@link ConfirmationType#YES}, return TRUE.
     * </p>
     */
    public static final String ACTION_CONFIRM_PRINT_FPS = "com.pax.us.pay.action.CONFIRM_PRINT_FPS";


    /**
     * Activity Action: Confirm Delete SF transaction  <br>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} <br>
     *     Message shown on UI<br>
     *     Type: String<br>
     *     It is optional. Reversed if some BroadPOS app prefer customized message.<br>
     * </p>
     * <p>
     *     If {@link EntryExtraData#PARAM_MESSAGE} exist, recommend display it.<br>
     *     Of course, you can display your own message, Like: <br>
     *     "Delete SF Records, Are You Sure?"<br>
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     Values: {@link ConfirmationType#YES} and {@link ConfirmationType#NO}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@link ConfirmationType#YES}, return TRUE.
     * </p>
     */
    public static final String ACTION_CONFIRM_DELETE_SF = "com.pax.us.pay.action.CONFIRM_DELETE_SF";

    /**
     * Activity Action: Confirm Print Customer Receipt <br>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} <br>
     *     Message shown on UI<br>
     *     Type: String<br>
     *     It is optional. Reversed if some BroadPOS app prefer customized message.<br>
     * </p>
     * <p>
     *     If {@link EntryExtraData#PARAM_MESSAGE} exist, recommend display it.<br>
     *     Of course, you can display your own message, Like: <br>
     *     "Please Tear Receipt.\nPrint Customer Receipt?"<br>
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000.
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     Values: {@link ConfirmationType#YES} and {@link ConfirmationType#NO}
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} YES: OK  NO:CANCEL  <br>
     */
    public static final String ACTION_CONFIRM_PRINT_CUSTOMER_COPY = "com.pax.us.pay.action.CONFIRM_PRINT_CUSTOMER_COPY";

    /**
     * The intent action of Batch close for Application update <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * request: {@link EntryRequest#PARAM_CONFIRMED} <br>
     * @deprecated Never used. This dialog shown by Host app.
     */
    public static final String ACTION_CONFIRM_BATCH_FOR_APPLICATION_UPDATE = "com.pax.us.pay.action.CONFIRM_BATCH_FOR_APPLICATION_UPDATE";
    /**
     * Activity Action: Retry Online <br>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} <br>
     *     Message shown on UI<br>
     *     Type: String<br>
     *     It is optional. Reversed if some BroadPOS app prefer customized message.<br>
     * </p>
     * <p>
     *     If {@link EntryExtraData#PARAM_MESSAGE} exist, recommend display it.<br>
     *     Of course, you can display your own message, Like: <br>
     *     "Online Failed, Retry?"<br>
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     Values: {@link ConfirmationType#YES} and {@link ConfirmationType#NO}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@link ConfirmationType#YES}, return TRUE.
     * </p>
     */
    public static final String ACTION_CONFIRM_ONLINE_RETRY = "com.pax.us.pay.action.CONFIRM_ONLINE_RETRY";


    /**
     * Activity Action: Confirm Tip Edit <br>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} <br>
     *     Message shown on UI<br>
     *     Type: String<br>
     *     It is optional. Reversed if some BroadPOS app prefer customized message.<br>
     * </p>
     * <p>
     *     If {@link EntryExtraData#PARAM_MESSAGE} exist, recommend display it.<br>
     *     Of course, you can display your own message, Like: <br>
     *     "Would you like to add this tip?"<br>
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     Values: {@link ConfirmationType#YES} and {@link ConfirmationType#NO}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@link ConfirmationType#YES}, return TRUE.
     * </p>
     */
    public static final String ACTION_CONFIRM_ADJUST_TIP = "com.pax.us.pay.action.CONFIRM_ADJUST_TIP";

    /**
     * Activity Action: Confirm Card Process Result<br>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE}<br>
     *     Type: String<br>
     *     Generally it is some card error message. Like "1-Chip Malfunction"
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS}<br>
     *     Type: String[]<br>
     *     Value is {@link ConfirmationType#YES}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 5000.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@link ConfirmationType#YES}, return TRUE.
     * </p>
     */
    public static final String ACTION_CONFIRM_CARD_PROCESS_RESULT = "com.pax.us.pay.action.CONFIRM_CARD_PROCESS_RESULT";


    /**
     * The intent action of confirm the receipt need to be signed or not<br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * request: {@link EntryRequest#PARAM_CONFIRMED} <br>
     * @deprecated Never used?
     */
    public static final String ACTION_CONFIRM_RECEIPT_SIGNATURE = "com.pax.us.pay.action.CONFIRM_RECEIPT_SIGNATURE";
    /**
     * Activity Action: Receipt Print Preview <br>
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
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@link ConfirmationType#YES}, return TRUE.
     * </p>
     */
    public static final String ACTION_CONFIRM_RECEIPT_VIEW = "com.pax.us.pay.action.CONFIRM_RECEIPT_VIEW";

    /**
     * Activity Action: Confirm Balance<br>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_CURRENCY} is currency type. <br>
     *     Type: String<br>
     *     Default value: {@link com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType#USD}
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_BALANCE}  <br>
     *     Type: Long
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@link ConfirmationType#YES}, return TRUE.
     * </p>
     * @deprecated Not used any more
     */
    public static final String ACTION_CONFIRM_BALANCE = "com.pax.us.pay.action.CONFIRM_BALANCE";

    /**
     * Activity Action: Confirm Merchant
     * <p>Used when multi-merchant feature is enabled.</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} <br>
     *     Message shown on UI<br>
     *     Type: String<br>
     *     It is optional. Reversed if some BroadPOS app prefer customized message.<br>
     * </p>
     * <p>
     *     If {@link EntryExtraData#PARAM_MESSAGE} exist, recommend display it.<br>
     *     Of course, you can display your own message, Like: <br>
     *     "Apply for all merchants or current?"<br>
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS} <br>
     *     Type: String[]<br>
     *     Options are {@link com.pax.us.pay.ui.constant.entry.enumeration.MerchantScope#CURRENT} and {@link com.pax.us.pay.ui.constant.entry.enumeration.MerchantScope#ALL}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@link com.pax.us.pay.ui.constant.entry.enumeration.MerchantScope#CURRENT}, return TRUE.
     * </p>
     */
    public static final String ACTION_CONFIRM_MERCHANT_SCOPE = "com.pax.us.pay.action.CONFIRM_MERCHANT_SCOPE";


    /**
     * Activity Action: Confirm Card Entry Retry<br>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE}<br>
     *     Message shown on UI<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS} <br>
     *     Type: String[]<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.CardEntryRetryOption}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@link ConfirmationType#YES}, return TRUE.
     * </p>
     */
    public static final String ACTION_CONFIRM_CARD_ENTRY_RETRY = "com.pax.us.pay.action.CONFIRM_CARD_ENTRY_RETRY";


    /**
     * The intent action of confirm end user license agreement<br>
     * when start action from host by call startActivity() directly, doesn't set EntryExtraData#PARAM_START_TYPE <br>
     * when start action from manager, set EntryExtraData#PARAM_START_TYPE value "ACTION" <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_EULA_URI} <br>
     * input: {@link EntryExtraData#PARAM_START_TYPE} <br>
     * request: {@link EntryRequest#PARAM_CONFIRMED} <br>
     * request: {@link EntryRequest#PARAM_DO_NOT_PROMPT_AGAIN} <br>
     * @deprecated Never used?
     */
    public static final String ACTION_CONFIRM_END_USER_LICENSE_AGREEMENT = "com.pax.us.pay.action.CONFIRM_END_USER_LICENSE_AGREEMENT";

    /**
     * The intent action of confirm eula continue<br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_OPTIONS} <br>
     * request: {@link EntryRequest#PARAM_CONFIRMED} <br>
     * @deprecated Never used?
     */
    public static final String ACTION_CONFIRM_EULA_CONTINUE = "com.pax.us.pay.action.CONFIRM_EULA_CONTINUE";
    /**
     * Activity Action: Confirm Signature Match
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} <br>
     *     Message shown on UI<br>
     *     Type: String<br>
     *     It is optional. Reversed if some BroadPOS app prefer customized message.<br>
     * </p>
     * <p>
     *     If {@link EntryExtraData#PARAM_MESSAGE} exist, recommend display it.<br>
     *     Of course, you can display your own message, Like: <br>
     *     "Does the cardholder signature match?"<br>
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_OPTIONS} <br>
     *     Type: String[]<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.ConfirmationType}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If select {@link ConfirmationType#YES}, return TRUE.
     * </p>
     */
    public static final String ACTION_CONFIRM_SIGNATURE_MATCH = "com.pax.us.pay.action.CONFIRM_SIGNATURE_MATCH";

    /**
     * Activity Action: Display QRCode Receipt
     * <p>
     *     This UI is used to display a QRCode of receipt so that maybe customer could scan it and save to their phone.<br>
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
     *     Input:  {@link EntryExtraData#PARAM_QR_CODE_CONTENT} <br>
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
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
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
     * Activity Action: Confirm DCC information <br>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_AMOUNT_MESSAGE} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_EXCHANGE_RATE} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_CURRENCY_ALPHA_CODE} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MARGIN} <br>
     *     Type: String
     *     </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_FOREIGN_AMOUNT_MESSAGE} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_CONFIRM_WITH_CURRENCY} <br>
     *     Type: Boolean
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 20000.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CONFIRMED} <br>
     *     Type: Boolean<br>
     *     If PARAM_CONFIRM_WITH_CURRENCY is true and select "USD", return TRUE.
     * </p>
     */
    public static final String ACTION_CONFIRM_DCC = "com.pax.us.pay.action.CONFIRM_DCC";

}
