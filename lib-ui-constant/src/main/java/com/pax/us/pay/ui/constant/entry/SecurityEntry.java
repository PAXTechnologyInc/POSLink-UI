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
     * Activity Category: SECURITY<br>{@value #CATEGORY}<br>
     */
    public static final String CATEGORY = "com.pax.us.pay.ui.category.SECURITY";
    /**
     * Activity Action: Input Account <br>{@value #ACTION_INPUT_ACCOUNT}<br>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} - {@value EntryExtraData#PARAM_PACKAGE}  is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_TYPE} - {@value EntryExtraData#PARAM_TRANS_TYPE} is trans name. <br>
     *     Type: String<br>
     *     Example: "CREDIT SALE"
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_MODE} - {@value EntryExtraData#PARAM_TRANS_MODE} is transaction mode.<br>
     *     Type: String<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.TransMode} for details
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} - {@value EntryExtraData#PARAM_TIMEOUT}<br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000. <br>
     *     For reference only, BroadPOS handles timeout.
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_MERCHANT_NAME} - {@value EntryExtraData#PARAM_MERCHANT_NAME} is merchant name. <br>
     *     Type: String<br>
     *     Used when multi-merchant feature is enabled
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_CURRENCY} - {@value EntryExtraData#PARAM_CURRENCY} is currency type. <br>
     *     Type: String<br>
     *     Default value: {@link com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType#USD}
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_TOTAL_AMOUNT} - {@value EntryExtraData#PARAM_TOTAL_AMOUNT} is total amount<br>
     *     Type: Long
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ENABLE_SWIPE}  - {@value EntryExtraData#PARAM_ENABLE_SWIPE}  <br>
     *     Type: Boolean<br>
     *     TRUE is enabled. FALSE is disabled
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ENABLE_INSERT}  - {@value EntryExtraData#PARAM_ENABLE_INSERT}  <br>
     *     Type: Boolean<br>
     *     TRUE is enabled. FALSE is disabled
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ENABLE_TAP}  - {@value EntryExtraData#PARAM_ENABLE_TAP}  <br>
     *     Type: Boolean<br>
     *     TRUE is enabled. FALSE is disabled
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ENABLE_MANUAL}  - {@value EntryExtraData#PARAM_ENABLE_MANUAL}  <br>
     *     Type: Boolean<br>
     *     TRUE is enabled. FALSE is disabled
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ENABLE_APPLEPAY}  - {@value EntryExtraData#PARAM_ENABLE_APPLEPAY}  <br>
     *     Type: Boolean<br>
     *     TRUE is enabled. FALSE is disabled
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ENABLE_GOOGLEPAY}  - {@value EntryExtraData#PARAM_ENABLE_GOOGLEPAY}  <br>
     *     Type: Boolean<br>
     *     TRUE is enabled. FALSE is disabled
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ENABLE_SAMSUNGPAY}  - {@value EntryExtraData#PARAM_ENABLE_SAMSUNGPAY}  <br>
     *     Type: Boolean<br>
     *     TRUE is enabled. FALSE is disabled
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ENABLE_NFCPAY}  - {@value EntryExtraData#PARAM_ENABLE_NFCPAY}  <br>
     *     Type: Boolean<br>
     *     TRUE is enabled. FALSE is disabled
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ENABLE_SCAN}  - {@value EntryExtraData#PARAM_ENABLE_SCAN}   <br>
     *     Type: Boolean<br>
     *     TRUE is enabled. FALSE is disabled
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ENABLE_LASER_SCAN}  - {@value EntryExtraData#PARAM_ENABLE_LASER_SCAN}   <br>
     *     Type: Boolean<br>
     *     TRUE is enabled. FALSE is disabled
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ENABLE_CONTACTLESS_LIGHT}  - {@value EntryExtraData#PARAM_ENABLE_CONTACTLESS_LIGHT}  <br>
     *     Type: Boolean.<br>
     *     True is enabled. False is disabled
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PAN_STYLES}  - {@value EntryExtraData#PARAM_PAN_STYLES}  <br>
     *     Type: String.<br>
     *     If value is {@link com.pax.us.pay.ui.constant.entry.enumeration.PanStyles#NEW_PAN},
     *     prompt "Please Enter New Card Number".<br>
     *     Else, prompt "Please Enter Card Number".<br>
     *     For GIFT/LOYALTY Replace/Merge transaction use.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_X} - {@value EntryRequest#PARAM_X} is X coordinate of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_Y} - {@value EntryRequest#PARAM_Y} is Y coordinate of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_WIDTH} - {@value EntryRequest#PARAM_WIDTH} is width of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_HEIGHT} - {@value EntryRequest#PARAM_HEIGHT} is height of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FONT_SIZE} - {@value EntryRequest#PARAM_FONT_SIZE} is font size of input box <br>
     *     Type: Integer<br>
     *     Unit: scaled pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FOCUSABLE} - {@value EntryRequest#PARAM_FOCUSABLE} Optional <br>
     *     Type: Boolean<br>
     *     TRUE is focusable.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_HINT} - {@value EntryRequest#PARAM_HINT} Optional <br>
     *     Type: String
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_COLOR} - {@value EntryRequest#PARAM_COLOR} Optional<br>
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
     * Activity Action: ENTER PIN<br>{@value #ACTION_ENTER_PIN}<br>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} - {@value EntryExtraData#PARAM_PACKAGE}  is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_TYPE} - {@value EntryExtraData#PARAM_TRANS_TYPE} is trans name. <br>
     *     Type: String<br>
     *     Example: "CREDIT SALE"
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_MODE} - {@value EntryExtraData#PARAM_TRANS_MODE} is transaction mode.<br>
     *     Type: String<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.TransMode} for details
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} - {@value EntryExtraData#PARAM_TIMEOUT}<br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000. <br>
     *     For reference only, BroadPOS handles timeout.
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_CURRENCY} - {@value EntryExtraData#PARAM_CURRENCY} is currency type. <br>
     *     Type: String<br>
     *     Default value: {@link com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType#USD}<br>
     *     Optional.
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_TOTAL_AMOUNT} - {@value EntryExtraData#PARAM_TOTAL_AMOUNT} is total amount<br>
     *     Type: Long<br>
     *     Optional.
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_IS_ONLINE_PIN} - {@value EntryExtraData#PARAM_IS_ONLINE_PIN} <br>
     *     Type: Boolean<br>
     *     TRUE is enabled. FALSE is disabled
     * </p><p>
     *     Input: {@link EntryExtraData#PARAM_PIN_STYLES} - {@value EntryExtraData#PARAM_PIN_STYLES}<br>
     *     Type: String<br>
     *     see {@link com.pax.us.pay.ui.constant.entry.enumeration.PinStyles} for details
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PIN_RANGE} - {@value EntryExtraData#PARAM_PIN_RANGE} is length limit for PIN<br>
     *     Type: String<br>
     *     It defines valid length of PIN and enumerates all possible lengths of PIN. "," will be used to separate each number of length.<br>
     *     Example:<br>
     *     If value is "0,4,6", it means NO PIN, or 4 or 6 digits of PIN are allowed.<br>
     *     So, if value start with "0,", you could show a message "Press OK to Bypass PIN".
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_IS_EXTERNAL_PINPAD} - {@value EntryExtraData#PARAM_IS_EXTERNAL_PINPAD} tells the type of PIN PAD<br>
     *     Type: Boolean<br>
     *     TRUE means BroadPOS using external PIN PAD (like SP30S).
     * </p>
     * <p>
     *     If you would like to use your PIN PAD layout, see {@link EntryRequest#ACTION_SET_PIN_KEY_LAYOUT} for details.<br>
     *     BroadPOS does not care about the location of PIN INPUT BOX, however you have to set security area to BroadPOS after UI is ready.<br>
     *     BroadPOS will start PIN entry process after receive broadcast {@link EntryRequest#ACTION_SECURITY_AREA}<br>
     * </p>
     * Example code:
     * <pre>
     *         Bundle bundle = new Bundle();
     *         bundle.putString(EntryRequest.PARAM_ACTION, SecurityEntry.ACTION_ENTER_PIN);
     *
     *         Intent intent = new Intent(EntryRequest.ACTION_SECURITY_AREA);
     *         intent.putExtras(bundle);
     *         intent.setPackage(packageName);
     *         requireContext().sendBroadcast(intent);
     * </pre>
     */
    public static final String ACTION_ENTER_PIN = "com.pax.us.pay.action.ENTER_PIN";

    /**
     * Activity Action: Enter VCode/CVV<br>{@value #ACTION_ENTER_VCODE}<br>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} - {@value EntryExtraData#PARAM_PACKAGE}  is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_TYPE} - {@value EntryExtraData#PARAM_TRANS_TYPE} is trans name. <br>
     *     Type: String<br>
     *     Example: "CREDIT SALE"
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_MODE} - {@value EntryExtraData#PARAM_TRANS_MODE} is transaction mode.<br>
     *     Type: String<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.TransMode} for details
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} - {@value EntryExtraData#PARAM_TIMEOUT}<br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000. <br>
     *     For reference only, BroadPOS handles timeout.
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_VCODE_NAME} - {@value EntryExtraData#PARAM_VCODE_NAME} <br>
     *     see {@link VCodeName}
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_X} - {@value EntryRequest#PARAM_X} is X coordinate of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_Y} - {@value EntryRequest#PARAM_Y} is Y coordinate of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_WIDTH} - {@value EntryRequest#PARAM_WIDTH} is width of input box<br>
     *     Type: Integer<br>
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_HEIGHT} - {@value EntryRequest#PARAM_HEIGHT} is height of input box</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FONT_SIZE} - {@value EntryRequest#PARAM_FONT_SIZE} is font size of input box <br>
     *     Type: Integer<br>
     *     Unit: scaled pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FOCUSABLE} - {@value EntryRequest#PARAM_FOCUSABLE} Optional <br>
     *     Type: Boolean<br>
     *     TRUE is focusable.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_HINT} - {@value EntryRequest#PARAM_HINT} Optional <br>
     *     Type: String
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_COLOR} - {@value EntryRequest#PARAM_COLOR} Optional<br>
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
     * Activity Action: Enter Last 4 Digits of Card<br>{@value #ACTION_ENTER_CARD_LAST_4_DIGITS}<br>
     * <p>Used when secure card feature is enabled</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} - {@value EntryExtraData#PARAM_PACKAGE}  is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_TYPE} - {@value EntryExtraData#PARAM_TRANS_TYPE} is trans name. <br>
     *     Type: String<br>
     *     Example: "CREDIT SALE"
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_MODE} - {@value EntryExtraData#PARAM_TRANS_MODE} is transaction mode.<br>
     *     Type: String<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.TransMode} for details
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} - {@value EntryExtraData#PARAM_MESSAGE}  Message shown on UI<br>
     *     Type: String<br>
     *     It is optional. Reversed if some BroadPOS app prefer customized message.<br>
     * </p>
     * <p>
     *     If {@link EntryExtraData#PARAM_MESSAGE} - {@value EntryExtraData#PARAM_MESSAGE} exist, recommend display it.<br>
     *     Of course, you can display your own message, Like: <br>
     *     "Please Enter Last 4 Digits"
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} - {@value EntryExtraData#PARAM_TIMEOUT}<br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000. <br>
     *     For reference only, BroadPOS handles timeout.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_X} - {@value EntryRequest#PARAM_X} is X coordinate of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_Y} - {@value EntryRequest#PARAM_Y} is Y coordinate of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_WIDTH} - {@value EntryRequest#PARAM_WIDTH} is width of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_HEIGHT} - {@value EntryRequest#PARAM_HEIGHT} is height of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FONT_SIZE} - {@value EntryRequest#PARAM_FONT_SIZE} is font size of input box <br>
     *     Type: Integer<br>
     *     Unit: scaled pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FOCUSABLE} - {@value EntryRequest#PARAM_FOCUSABLE} Optional <br>
     *     Type: Boolean<br>
     *     TRUE is focusable.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_HINT} - {@value EntryRequest#PARAM_HINT} Optional <br>
     *     Type: String
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_COLOR} - {@value EntryRequest#PARAM_COLOR} Optional<br>
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
     * Activity Action: Enter All Digits of Card<br>{@value #ACTION_ENTER_CARD_ALL_DIGITS}<br>
     * <p>Used when secure card feature is enabled</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} - {@value EntryExtraData#PARAM_PACKAGE}  is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_TYPE} - {@value EntryExtraData#PARAM_TRANS_TYPE} is trans name. <br>
     *     Type: String<br>
     *     Example: "CREDIT SALE"
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_MODE} - {@value EntryExtraData#PARAM_TRANS_MODE} is transaction mode.<br>
     *     Type: String<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.TransMode} for details
     * </p>
     * <p>
     *     Input:  {@link EntryExtraData#PARAM_MESSAGE} - {@value EntryExtraData#PARAM_MESSAGE}  Message shown on UI<br>
     *     Type: String<br>
     *     It is optional. Reversed if some BroadPOS app prefer customized message.<br>
     * </p>
     * <p>
     *     If {@link EntryExtraData#PARAM_MESSAGE} - {@value EntryExtraData#PARAM_MESSAGE} exist, recommend display it.<br>
     *     Of course, you can display your own message, Like: <br>
     *     "Please Enter All Digits"
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} - {@value EntryExtraData#PARAM_TIMEOUT}<br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000. <br>
     *     For reference only, BroadPOS handles timeout.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_X} - {@value EntryRequest#PARAM_X} is X coordinate of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_Y} - {@value EntryRequest#PARAM_Y} is Y coordinate of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_WIDTH} - {@value EntryRequest#PARAM_WIDTH} is width of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_HEIGHT} - {@value EntryRequest#PARAM_HEIGHT} is height of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FONT_SIZE} - {@value EntryRequest#PARAM_FONT_SIZE} is font size of input box <br>
     *     Type: Integer<br>
     *     Unit: scaled pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FOCUSABLE} - {@value EntryRequest#PARAM_FOCUSABLE} Optional <br>
     *     Type: Boolean<br>
     *     TRUE is focusable.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_HINT} - {@value EntryRequest#PARAM_HINT} Optional <br>
     *     Type: String
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_COLOR} - {@value EntryRequest#PARAM_COLOR} Optional<br>
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
     * Activity Action: Enter administrator password<br>{@value #ACTION_ENTER_ADMINISTRATION_PASSWORD}<br>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} - {@value EntryExtraData#PARAM_PACKAGE}  is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_TYPE} - {@value EntryExtraData#PARAM_TRANS_TYPE} is trans name. <br>
     *     Type: String<br>
     *     Example: "CREDIT SALE"
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_MODE} - {@value EntryExtraData#PARAM_TRANS_MODE} is transaction mode.<br>
     *     Type: String<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.TransMode} for details
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} - {@value EntryExtraData#PARAM_TIMEOUT}<br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000. <br>
     *     For reference only, BroadPOS handles timeout.
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_MERCHANT_NAME} is merchant name. </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ADMIN_PASSWORD_TYPE} is password type. <br>
     *     Type: String<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.AdminPasswordType} for details
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_X} - {@value EntryRequest#PARAM_X} is X coordinate of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_Y} - {@value EntryRequest#PARAM_Y} is Y coordinate of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_WIDTH} - {@value EntryRequest#PARAM_WIDTH} is width of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_HEIGHT} - {@value EntryRequest#PARAM_HEIGHT} is height of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FONT_SIZE} - {@value EntryRequest#PARAM_FONT_SIZE} is font size of input box <br>
     *     Type: Integer<br>
     *     Unit: scaled pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FOCUSABLE} - {@value EntryRequest#PARAM_FOCUSABLE} Optional <br>
     *     Type: Boolean<br>
     *     TRUE is focusable.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_HINT} - {@value EntryRequest#PARAM_HINT} Optional <br>
     *     Type: String
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_COLOR} - {@value EntryRequest#PARAM_COLOR} Optional<br>
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
    public static final String ACTION_ENTER_ADMINISTRATION_PASSWORD = "com.pax.us.pay.action.ENTER_ADMINISTRATOR_PASSWORD";

    /**
     * Activity Action: Manage Input Account<br>{@value #ACTION_MANAGE_INPUT_ACCOUNT}<br>
     * <p>For POSLink Manager--Input Account</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} - {@value EntryExtraData#PARAM_PACKAGE}  is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_TYPE} - {@value EntryExtraData#PARAM_TRANS_TYPE} is trans name. <br>
     *     Type: String<br>
     *     Example: "CREDIT SALE"
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_MODE} - {@value EntryExtraData#PARAM_TRANS_MODE} is transaction mode.<br>
     *     Type: String<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.TransMode} for details
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} - {@value EntryExtraData#PARAM_TIMEOUT}<br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000. <br>
     *     For reference only, BroadPOS handles timeout.
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_AMOUNT_MESSAGE} Amount Message. Optional.<br>
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ENABLE_SWIPE}  - {@value EntryExtraData#PARAM_ENABLE_SWIPE}  <br>
     *     Type: Boolean<br>
     *     TRUE is enabled. FALSE is disabled
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ENABLE_INSERT}  - {@value EntryExtraData#PARAM_ENABLE_INSERT}  <br>
     *     Type: Boolean<br>
     *     TRUE is enabled. FALSE is disabled
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ENABLE_TAP}  - {@value EntryExtraData#PARAM_ENABLE_TAP}  <br>
     *     Type: Boolean<br>
     *     TRUE is enabled. FALSE is disabled
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ENABLE_MANUAL}  - {@value EntryExtraData#PARAM_ENABLE_MANUAL}  <br>
     *     Type: Boolean<br>
     *     TRUE is enabled. FALSE is disabled
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ENABLE_NFCPAY}  - {@value EntryExtraData#PARAM_ENABLE_NFCPAY}  <br>
     *     Type: Boolean<br>
     *     TRUE is enabled. FALSE is disabled
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ENABLE_CONTACTLESS_LIGHT}  - {@value EntryExtraData#PARAM_ENABLE_CONTACTLESS_LIGHT}  <br>
     *     Type: Boolean.<br>
     *     True is enabled. False is disabled
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_X} - {@value EntryRequest#PARAM_X} is X coordinate of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_Y} - {@value EntryRequest#PARAM_Y} is Y coordinate of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_WIDTH} - {@value EntryRequest#PARAM_WIDTH} is width of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_HEIGHT} - {@value EntryRequest#PARAM_HEIGHT} is height of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FONT_SIZE} - {@value EntryRequest#PARAM_FONT_SIZE} is font size of input box <br>
     *     Type: Integer<br>
     *     Unit: scaled pixel
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FOCUSABLE} - {@value EntryRequest#PARAM_FOCUSABLE} Optional <br>
     *     Type: Boolean<br>
     *     TRUE is focusable.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_HINT} - {@value EntryRequest#PARAM_HINT} Optional <br>
     *     Type: String
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_COLOR} - {@value EntryRequest#PARAM_COLOR} Optional<br>
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
