package com.pax.us.pay.ui.constant.entry;

import com.pax.us.pay.ui.constant.entry.enumeration.VCodeName;

/**
 * define Activity for SECURITY
 * <p>
 *     Secure UI need return location information of input box. <br>
 *     Include {@link EntryRequest#PARAM_X},{@link EntryRequest#PARAM_Y},{@link EntryRequest#PARAM_HEIGHT} and {@link EntryRequest#PARAM_WIDTH}
 * </p>
 */
public final class SecurityEntry {
    private SecurityEntry(){

    }
    /**
     * Activity Category: SECURITY
     */
    public static final String CATEGORY = "com.pax.us.pay.ui.category.SECURITY";
    /**
     * Activity Action: Input Account 
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_TYPE} is trans name. <br>
     *     Type: String<br>
     *     Example: "CREDIT SALE"
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_MODE} is transaction mode.<br>
     *     Type: String<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.TransMode} for details
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000.
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_MERCHANT_NAME} is merchant name. <br>
     *     Type: String<br>
     *     Used when multi-merchant feature is enabled
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_CURRENCY} is currency type. <br>
     *     Type: String<br>
     *     Default value: {@link com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType#USD}
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_TOTAL_AMOUNT} is total amount<br>
     *     Type: Long
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ENABLE_SWIPE} <br>
     *     Type: Boolean<br>
     *     TRUE is enabled. FALSE is disabled
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ENABLE_INSERT} <br>
     *     Type: Boolean<br>
     *     TRUE is enabled. FALSE is disabled
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ENABLE_TAP} <br>
     *     Type: Boolean<br>
     *     TRUE is enabled. FALSE is disabled
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ENABLE_MANUAL} <br>
     *     Type: Boolean<br>
     *     TRUE is enabled. FALSE is disabled
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ENABLE_APPLEPAY} <br>
     *     Type: Boolean<br>
     *     TRUE is enabled. FALSE is disabled
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ENABLE_GOOGLEPAY} <br>
     *     Type: Boolean<br>
     *     TRUE is enabled. FALSE is disabled
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ENABLE_SUMSUNGPAY} <br>
     *     Type: Boolean<br>
     *     TRUE is enabled. FALSE is disabled
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ENABLE_NFCPAY} <br>
     *     Type: Boolean<br>
     *     TRUE is enabled. FALSE is disabled
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ENABLE_SCAN}  <br>
     *     Type: Boolean<br>
     *     TRUE is enabled. FALSE is disabled
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PAN_STYLES} <br>
     *     Type: String<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.PanStyles}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ENABLE_CONTACTLESS_LIGHT} <br>
     *     Type: Boolean.<br>
     *     True is enabled. False is disabled
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_X} is X coordinate of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_Y} is Y coordinate of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_WIDTH} is width of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_HEIGHT} is height of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FONT_SIZE} is font size of input box <br>
     *     Type: Integer<br>
     *     Unit is sp
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FOCUSABLE} Optional <br>
     *     Type: Boolean<br>
     *     TRUE is focusable.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_HINT} Optional <br>
     *     Type: String
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_COLOR} Optional<br>
     *     Type : String<br>
     *     Format: ARGB<br>
     *     Example: <br>
     *     "ffffffff" which represent the opaque white color.<br>
     *     "cc00ff00" which means the green color with 20% transparent.
     * </p>
     * <p>
     *     About how to send output to BroadPOS, see {@link EntryRequest#ACTION_SECURITY_AREA} for details.<br>
     * </p>
     */
    public static final String ACTION_INPUT_ACCOUNT = "com.pax.us.pay.action.INPUT_ACCOUNT";

    /**
     * Activity Action: ENTER PIN
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_TYPE} is trans name. <br>
     *     Type: String<br>
     *     Example: "CREDIT SALE"
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_MODE} is transaction mode.<br>
     *     Type: String<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.TransMode} for details
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000.
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_CURRENCY} is currency type. <br>
     *     Type: String<br>
     *     Default value: {@link com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType#USD}
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_TOTAL_AMOUNT} is total amount<br>
     *     Type: Long
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_IS_ONLINE_PIN} <br>
     *     Type: Boolean<br>
     *     TRUE is enabled. FALSE is disabled
     * </p><p>
     *     Input: {@link EntryExtraData#PARAM_PIN_STYLES} <br>
     *     Type: String<br>
     *     see {@link com.pax.us.pay.ui.constant.entry.enumeration.PinStyles} for details
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PIN_RANGE} is length limit for PIN<br>
     *     Type: String<br>
     *     Format is same with {@link EntryExtraData#PARAM_VALUE_PATTERN}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_IS_EXTERNAL_PINPAD} tells the type of PIN PAD<br>
     *     Type: Boolean<br>
     *     TRUE means BroadPOS using external PIN PAD (like SP30S).
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_SHOW_VIRTUAL_PINPAD}
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_X} is X coordinate of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_Y} is Y coordinate of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_WIDTH} is width of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_HEIGHT} is height of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     About how to send output to BroadPOS, see {@link EntryRequest#ACTION_SECURITY_AREA} for details.<br>
     *     If you would like to use your PIN PAD layout, see {@link EntryRequest#ACTION_SET_PIN_KEY_LAYOUT} for details.
     * </p>
     */
    public static final String ACTION_ENTER_PIN = "com.pax.us.pay.action.ENTER_PIN";

    /**
     * Activity Action: Enter VCode/CVV
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_TYPE} is trans name. <br>
     *     Type: String<br>
     *     Example: "CREDIT SALE"
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_MODE} is transaction mode.<br>
     *     Type: String<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.TransMode} for details
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000.
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_VCODE_NAME} <br>
     *     see {@link VCodeName}
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_X} is X coordinate of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_Y} is Y coordinate of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_WIDTH} is width of input box<br>
     *     Type: Integer<br>
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_HEIGHT} is height of input box</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FONT_SIZE} is font size of input box <br>
     *     Type: Integer<br>
     *     Unit is sp
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FOCUSABLE} Optional <br>
     *     Type: Boolean<br>
     *     TRUE is focusable.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_HINT} Optional <br>
     *     Type: String
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_COLOR} Optional<br>
     *     Type : String<br>
     *     Format: ARGB<br>
     *     Example: <br>
     *     "ffffffff" which represent the opaque white color.<br>
     *     "cc00ff00" which means the green color with 20% transparent.
     * </p>
     * <p>
     *     About how to send output to BroadPOS, see {@link EntryRequest#ACTION_SECURITY_AREA} for details.<br>
     * </p>
     */
    public static final String ACTION_ENTER_VCODE = "com.pax.us.pay.action.ENTER_CVV";


    /**
     * Activity Action: Enter Last 4 Digits of Card
     * <p>Used when secure card feature is enabled</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_TYPE} is trans name. <br>
     *     Type: String<br>
     *     Example: "CREDIT SALE"
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_MODE} is transaction mode.<br>
     *     Type: String<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.TransMode} for details
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
     *     "Please Enter Last 4 Digits"
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_X} is X coordinate of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_Y} is Y coordinate of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_WIDTH} is width of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_HEIGHT} is height of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FONT_SIZE} is font size of input box <br>
     *     Type: Integer<br>
     *     Unit is sp
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FOCUSABLE} Optional <br>
     *     Type: Boolean<br>
     *     TRUE is focusable.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_HINT} Optional <br>
     *     Type: String
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_COLOR} Optional<br>
     *     Type : String<br>
     *     Format: ARGB<br>
     *     Example: <br>
     *     "ffffffff" which represent the opaque white color.<br>
     *     "cc00ff00" which means the green color with 20% transparent.
     * </p>
     * <p>
     *     About how to send output to BroadPOS, see {@link EntryRequest#ACTION_SECURITY_AREA} for details.<br>
     * </p>
     */
    public static final String ACTION_ENTER_CARD_LAST_4_DIGITS = "com.pax.us.pay.action.ENTER_CARD_LAST_4_DIGITS";


    /**
     * Activity Action: Enter All Digits of Card
     * <p>Used when secure card feature is enabled</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_TYPE} is trans name. <br>
     *     Type: String<br>
     *     Example: "CREDIT SALE"
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_MODE} is transaction mode.<br>
     *     Type: String<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.TransMode} for details
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
     *     "Please Enter All Digits"
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_X} is X coordinate of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_Y} is Y coordinate of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_WIDTH} is width of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_HEIGHT} is height of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FONT_SIZE} is font size of input box <br>
     *     Type: Integer<br>
     *     Unit is sp
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FOCUSABLE} Optional <br>
     *     Type: Boolean<br>
     *     TRUE is focusable.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_HINT} Optional <br>
     *     Type: String
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_COLOR} Optional<br>
     *     Type : String<br>
     *     Format: ARGB<br>
     *     Example: <br>
     *     "ffffffff" which represent the opaque white color.<br>
     *     "cc00ff00" which means the green color with 20% transparent.
     * </p>
     * <p>
     *     About how to send output to BroadPOS, see {@link EntryRequest#ACTION_SECURITY_AREA} for details.<br>
     * </p>
     */
    public static final String ACTION_ENTER_CARD_ALL_DIGITS = "com.pax.us.pay.action.ENTER_CARD_ALL_DIGITS";

    /**
     * Activity Action: Enter administrator password
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_TYPE} is trans name. <br>
     *     Type: String<br>
     *     Example: "CREDIT SALE"
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_MODE} is transaction mode.<br>
     *     Type: String<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.TransMode} for details
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000.
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_MERCHANT_NAME} is merchant name. </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ADMIN_PASSWORD_TYPE} is password type. <br>
     *     Type: String<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.AdminPasswordType} for details
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_X} is X coordinate of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_Y} is Y coordinate of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_WIDTH} is width of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_HEIGHT} is height of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FONT_SIZE} is font size of input box <br>
     *     Type: Integer<br>
     *     Unit is sp
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FOCUSABLE} Optional <br>
     *     Type: Boolean<br>
     *     TRUE is focusable.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_HINT} Optional <br>
     *     Type: String
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_COLOR} Optional<br>
     *     Type : String<br>
     *     Format: ARGB<br>
     *     Example: <br>
     *     "ffffffff" which represent the opaque white color.<br>
     *     "cc00ff00" which means the green color with 20% transparent.
     * </p>
     * <p>
     *     About how to send output to BroadPOS, see {@link EntryRequest#ACTION_SECURITY_AREA} for details.<br>
     * </p>
     */
    public static final String ACTION_ENTER_ADMINISTRATION_PASSWORD = "com.pax.us.pay.action.ADMINISTRATOR_PASSWORD";

    /**
     * Activity Action: Manage Input Account
     * <p>For POSLink Manager--Input Account</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_TYPE} is trans name. <br>
     *     Type: String<br>
     *     Example: "CREDIT SALE"
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_MODE} is transaction mode.<br>
     *     Type: String<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.TransMode} for details
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000.
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_AMOUNT_MESSAGE} Amount Message. Optional.<br>
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ENABLE_SWIPE} <br>
     *     Type: Boolean<br>
     *     TRUE is enabled. FALSE is disabled
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ENABLE_INSERT} <br>
     *     Type: Boolean<br>
     *     TRUE is enabled. FALSE is disabled
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ENABLE_TAP} <br>
     *     Type: Boolean<br>
     *     TRUE is enabled. FALSE is disabled
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ENABLE_MANUAL} <br>
     *     Type: Boolean<br>
     *     TRUE is enabled. FALSE is disabled
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ENABLE_NFCPAY} <br>
     *     Type: Boolean<br>
     *     TRUE is enabled. FALSE is disabled
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ENABLE_CONTACTLESS_LIGHT} <br>
     *     Type: Boolean.<br>
     *     True is enabled. False is disabled
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_X} is X coordinate of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_Y} is Y coordinate of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_WIDTH} is width of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_HEIGHT} is height of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FONT_SIZE} is font size of input box <br>
     *     Type: Integer<br>
     *     Unit is sp
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FOCUSABLE} Optional <br>
     *     Type: Boolean<br>
     *     TRUE is focusable.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_HINT} Optional <br>
     *     Type: String
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_COLOR} Optional<br>
     *     Type : String<br>
     *     Format: ARGB<br>
     *     Example: <br>
     *     "ffffffff" which represent the opaque white color.<br>
     *     "cc00ff00" which means the green color with 20% transparent.
     * </p>
     * <p>
     *     About how to send output to BroadPOS, see {@link EntryRequest#ACTION_SECURITY_AREA} for details.<br>
     * </p>
     */
    public static final String ACTION_MANAGE_INPUT_ACCOUNT = "com.pax.us.pay.action.MANAGE_INPUT_ACCOUNT";
}
