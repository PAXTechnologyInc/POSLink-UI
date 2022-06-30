package com.pax.us.pay.ui.constant.entry;

/**
 * define Activity for TEXT
 */
public final class TextEntry {
    private TextEntry(){
        
    }

    /**
     * Activity Category: TEXT<br>{@value #CATEGORY}<br>
     */
    public static final String CATEGORY = "com.pax.us.pay.ui.category.TEXT";
    /**
     * Activity Action: Enter Amount<br>{@value #ACTION_ENTER_AMOUNT}<br>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} - {@value EntryExtraData#PARAM_PACKAGE}  is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_TYPE} - {@value EntryExtraData#PARAM_TRANS_TYPE} is transaction name. Example: "CREDIT SALE"</p>
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
     *     Default value: {@link com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType#USD}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} - {@value EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "1-12"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_AMOUNT} - {@value EntryRequest#PARAM_AMOUNT} <br>
     *     Type: Long
     * </p>
     */
    public static final String ACTION_ENTER_AMOUNT = "com.pax.us.pay.action.ENTER_AMOUNT";

    /**
     * Activity Action: Enter Tip<br>{@value #ACTION_ENTER_TIP}<br>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} - {@value EntryExtraData#PARAM_PACKAGE}  is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_TYPE} - {@value EntryExtraData#PARAM_TRANS_TYPE} is transaction name. Example: "CREDIT SALE"</p>
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
     *     Default value: {@link com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType#USD}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_BASE_AMOUNT} - {@value EntryExtraData#PARAM_BASE_AMOUNT}<br>
     *     Type: Long<br>
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIP_NAME} - {@value EntryExtraData#PARAM_TIP_NAME}<br>
     *     Type: String<br>
     *     Default value is "Tip"<br>
     *     In BroadPOS standalone mode, if multiple tips are enabled, value might be "Tip1", "Tip2", or any value configured by merchant<br>
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIP_OPTIONS} - {@value EntryExtraData#PARAM_TIP_OPTIONS} is tip options.
     *     Type: String[]<br>
     *     It is optional. Used when tip select is enabled.<br>
     * </p>
     *
     * <p>
     *     Example:<br>
     *     if value of {@link EntryExtraData#PARAM_TIP_OPTIONS} is {"300","500","700"}, <br>
     *     you could provide 3 options for user:<br><br>
     *     $3.00, $5.00, $7.00
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIP_RATE_OPTIONS} - {@value EntryExtraData#PARAM_TIP_RATE_OPTIONS} is tip percentage options.<br>
     *     Type: String[]<br>
     *     It is optional. Used when tip select is enabled.<br>
     *     Used with {@link EntryExtraData#PARAM_TIP_OPTIONS} together.<br>
     * </p>
     * <p>
     *     Example:<br>
     *     if value of {@link EntryExtraData#PARAM_TIP_OPTIONS} is {"1000","1500","1800"} and value of {@link EntryExtraData#PARAM_TIP_RATE_OPTIONS} is {"10","15","18"},<br>
     *     you could provide 3 options for user:<br><br>
     *     $10.00(10%), $15.00(15%), $18.00(18%)
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIP_UNIT} - {@value EntryExtraData#PARAM_TIP_UNIT}. <br>
     *     Type: String<br>
     *     Default is {@value com.pax.us.pay.ui.constant.entry.enumeration.UnitType#CENT}.
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_AMOUNT_UNIT} - {@value EntryExtraData#PARAM_AMOUNT_UNIT} <br>
     *     Type: Long<br>
     *     Deprecated. Not used anymore. Use {@link EntryExtraData#PARAM_TIP_UNIT} instead.
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} - {@value EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-12". It is used for manual input tip.
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIP_NAMES} - {@value EntryExtraData#PARAM_TIP_NAMES}<br>
     *     Type: String[]<br>
     *     Name of Enabled Tips <br>
     *     Optional. For Standalone use only. Used when multiple tips are enabled.
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIP_AMOUNTS} - {@value EntryExtraData#PARAM_TIP_AMOUNTS} <br>
     *     Type: String[]<br>
     *     Value of Enabled Tips <br>
     *     Optional. For Standalone use only. Used when multiple tips are enabled.<br>
     * </p>
     * <p>
     *     Example:<br>
     *     If TIP1, TIP2, TIP3 are all enabled.<br>
     *     If name of enabled tips is: {"TIP", "TIP2", "TIP3}<br>
     *     value of enabled tips is: {"100", "200"}<br>
     *     That means:<br>
     *     The customer has entered $1.00 TIP, $2.00 TIP2. TIP3 not valued yet.
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ENABLE_NO_TIP_SELECTION} - {@value EntryExtraData#PARAM_ENABLE_NO_TIP_SELECTION} <br>
     *     Type: Boolean<br>
     *     Optional. TRUE means NO TIP SELECTION is enabled.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_TIP} - {@value EntryRequest#PARAM_TIP}<br>
     *     Type: Long<br>
     *     Optional. <br>
     *     If return nothing, BroadPOS will treat it as tip bypassed.<br>
     *     If return 0, BroadPOS will treat it as "NO TIP"<br>
     * </p>
     */
    public static final String ACTION_ENTER_TIP = "com.pax.us.pay.action.ENTER_TIP";

    /**
     * Activity Action: Enter Trans. No.<br>{@value #ACTION_ENTER_TRANS_NUMBER}<br>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} - {@value EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "1-4"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_TRANS_NUMBER} - {@value EntryRequest#PARAM_TRANS_NUMBER} <br>Type: Long
     *     </p>
     */
    public static final String ACTION_ENTER_TRANS_NUMBER = "com.pax.us.pay.action.ENTER_TRANS_NUMBER";

    /**
     * Activity Action: Enter Expiry Date<br>{@value #ACTION_ENTER_EXPIRY_DATE}<br>
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
     *     Output: {@link EntryRequest#PARAM_EXPIRY_DATE} - {@value EntryRequest#PARAM_EXPIRY_DATE} <br>Type: String<br>
     *     Format: MMYY
     * </p>
     */
    public static final String ACTION_ENTER_EXPIRY_DATE = "com.pax.us.pay.action.ENTER_EXPIRY_DATE";

    /**
     * Activity Action: Enter Address<br>{@value #ACTION_ENTER_ADDRESS}<br>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} - {@value EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-30"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_ADDRESS} - {@value EntryRequest#PARAM_ADDRESS} <br>Type: String</p>
     */
    public static final String ACTION_ENTER_ADDRESS = "com.pax.us.pay.action.ENTER_ADDRESS";

    /**
     * Activity Action: Enter Zip Code<br>{@value #ACTION_ENTER_ZIPCODE}<br>
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
     *     Input: {@link EntryExtraData#PARAM_EINPUT_TYPE} - {@value EntryExtraData#PARAM_EINPUT_TYPE} <br>
     *     Type: String<br>
     *     If value is {@value com.pax.us.pay.ui.constant.entry.enumeration.InputType#ALLTEXT},
     *     the output {@value EntryRequest#PARAM_ZIP_CODE} could be alpha-numeric string.<br>
     *     Else it should be numeric string.
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} - {@value EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-9"
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_ZIP_CODE}  - {@value EntryRequest#PARAM_ZIP_CODE}<br>Type: String </p>
     */
    public static final String ACTION_ENTER_ZIPCODE = "com.pax.us.pay.action.ENTER_ZIP_CODE";

    /**
     * Activity Action: Enter Auth Code<br>{@value #ACTION_ENTER_AUTH}<br>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} - {@value EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "1-15"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_AUTH_CODE} - {@value EntryRequest#PARAM_AUTH_CODE}  <br>Type: String</p>
     */
    public static final String ACTION_ENTER_AUTH = "com.pax.us.pay.action.ENTER_AUTH_CODE";

    /**
     * Activity Action: Enter FSA Amount<br>{@value #ACTION_ENTER_FSA_DATA}<br>
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
     *     Default value: {@link com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType#USD}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TOTAL_AMOUNT} - {@value EntryExtraData#PARAM_TOTAL_AMOUNT} is total amount<br>
     *     Type: Long
     *     </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_FSA_AMOUNT_OPTIONS} - {@value EntryExtraData#PARAM_FSA_AMOUNT_OPTIONS} is fsa amount options.<br>
     *     Type: String[]
     *     it control output fields.<br>
     *     Example:<br>
     *     If options are {"clinicAmount","prescriptionAmount"}, required outputs are 
     *     {@link EntryRequest#PARAM_CLINIC_AMOUNT} and {@link EntryRequest#PARAM_PRESCRIPTION_AMOUNT}
     *     Type: String[]<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.FSAAmountType}<br>
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_HEALTH_CARE_AMOUNT} - {@value EntryRequest#PARAM_HEALTH_CARE_AMOUNT}<br>
     *     Type: Long
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CLINIC_AMOUNT} - {@value EntryRequest#PARAM_CLINIC_AMOUNT} <br>
     *     Type: Long
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_PRESCRIPTION_AMOUNT} - {@value EntryRequest#PARAM_PRESCRIPTION_AMOUNT} <br>
     *     Type: Long
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_VISION_AMOUNT} -  {@value EntryRequest#PARAM_VISION_AMOUNT}<br>
     *     Type: Long
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_DENTAL_AMOUNT} - {@value EntryRequest#PARAM_DENTAL_AMOUNT}  <br>
     *     Type: Long
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_COPAY_AMOUNT} - {@value EntryRequest#PARAM_COPAY_AMOUNT} <br>
     *     Type: Long
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_OTC_AMOUNT} -  {@value EntryRequest#PARAM_OTC_AMOUNT} <br>
     *     Type: Long
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_TRANSIT_AMOUNT} - {@value EntryRequest#PARAM_TRANSIT_AMOUNT}<br>
     *     Type: Long
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FSA_OPTION} - {@value EntryRequest#PARAM_FSA_OPTION}<br>
     *     Type: String<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.FSAType}
     * </p>
     */
    public static final String ACTION_ENTER_FSA_DATA = "com.pax.us.pay.action.ENTER_FSA_DATA";


    /**
     * Activity Action: Enter Voucher Number<br>{@value #ACTION_ENTER_VOUCHER_DATA}<br>
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
     *     Input: {@link EntryExtraData#PARAM_TOTAL_AMOUNT} - {@value EntryExtraData#PARAM_TOTAL_AMOUNT} <br>
     *     Type: Long<br>
     *     Deprecated. Never used.
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} - {@value EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "1-15"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_VOUCHER_NUMBER} - {@value EntryRequest#PARAM_VOUCHER_NUMBER} <br>Type: String </p>
     */
    public static final String ACTION_ENTER_VOUCHER_DATA = "com.pax.us.pay.action.ENTER_VOUCHER_DATA";

    /**
     * Activity Action: Enter Address and Zip code<br>{@value #ACTION_ENTER_AVS_DATA}<br>
     * <p>
     *     "Please Enter Address"<br>
     *     "Please Enter Zip Code"
     * </p>
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
     *     Input: {@link EntryExtraData#PARAM_ZIP_CODE_PATTERN} - {@value EntryExtraData#PARAM_ZIP_CODE_PATTERN} is length limit for Zip Code. <br>
     *     Type: String<br>Default: "0-9" </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ADDRESS_PATTERN} - {@value EntryExtraData#PARAM_ADDRESS_PATTERN} is length limit for Address.<br>
     *     Type: String<br> Default: "0-30" </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_EINPUT_TYPE} - {@value EntryExtraData#PARAM_EINPUT_TYPE} <br>
     *     Type: String<br>
     *     If value is {@value com.pax.us.pay.ui.constant.entry.enumeration.InputType#ALLTEXT},
     *     the output {@value EntryRequest#PARAM_ZIP_CODE} could be alpha-numeric string.<br>
     *     Else it should be numeric string.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_ADDRESS} - {@value EntryRequest#PARAM_ADDRESS} <br>Type: String</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_ZIP_CODE} - {@value EntryRequest#PARAM_ZIP_CODE} <br>Type: String</p>
     */
    public static final String ACTION_ENTER_AVS_DATA = "com.pax.us.pay.action.ENTER_AVS_DATA";

    /**
     * Activity Action:  Enter Reference Number<br>{@value #ACTION_ENTER_REFERENCE_NUMBER}<br>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} - {@value EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "1-16"</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_EINPUT_TYPE} - {@value EntryExtraData#PARAM_EINPUT_TYPE} <br>
     *     Type: String<br>
     *     If value is {@value com.pax.us.pay.ui.constant.entry.enumeration.InputType#ALLTEXT},
     *     the output {@value EntryRequest#PARAM_REFERENCE_NUMBER} could be alpha-numeric string.<br>
     *     Else it should be numeric string.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_REFERENCE_NUMBER} - {@value EntryRequest#PARAM_REFERENCE_NUMBER}  <br>Type: String</p>
     */
    public static final String ACTION_ENTER_REFERENCE_NUMBER = "com.pax.us.pay.action.ENTER_REFERENCE_NUMBER";


    /**
     * Activity Action: Enter Invoice Number<br>{@value #ACTION_ENTER_INVOICE_NUMBER}<br>
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
     *     Input: {@link EntryExtraData#PARAM_EINPUT_TYPE} - {@value EntryExtraData#PARAM_EINPUT_TYPE} <br>
     *     Type: String<br>
     *     If value is {@value com.pax.us.pay.ui.constant.entry.enumeration.InputType#ALLTEXT},
     *     the output {@value EntryRequest#PARAM_INVOICE_NUMBER} could be alpha-numeric string.<br>
     *     Else it should be numeric string.
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} - {@value EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "1-20"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INVOICE_NUMBER} - {@value EntryRequest#PARAM_INVOICE_NUMBER} <br>Type: String </p>
     */
    public static final String ACTION_ENTER_INVOICE_NUMBER = "com.pax.us.pay.action.ENTER_INVOICE_NUMBER";

    /**
     * Activity Action: Enter Clerk ID<br>{@value #ACTION_ENTER_CLERK_ID}<br>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} - {@value EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-4"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CLERK_ID} - {@value EntryRequest#PARAM_CLERK_ID}  <br>Type: String</p>
     */
    public static final String ACTION_ENTER_CLERK_ID = "com.pax.us.pay.action.ENTER_CLERK_ID";

    /**
     * Activity Action: Enter Server ID (For Restaurant only)<br>{@value #ACTION_ENTER_SERVER_ID}<br>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} - {@value EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-4"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_SERVER_ID} - {@value EntryRequest#PARAM_SERVER_ID} <br>Type: String</p>
     */
    public static final String ACTION_ENTER_SERVER_ID = "com.pax.us.pay.action.ENTER_SERVER_ID";

    /**
     * Activity Action: Enter Cashback<br>{@value #ACTION_ENTER_CASH_BACK}<br>
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
     *     Default value: {@link com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType#USD}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} - {@value EntryExtraData#PARAM_VALUE_PATTERN} <br>
     *     Type: String<br>
     *     Default: "0-12". It is length limit for cashback manual input.
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_CASHBACK_OPTIONS} - {@value EntryExtraData#PARAM_CASHBACK_OPTIONS} is cashback options.<br>
     *     Type:String[]
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ENABLE_OTHER_PROMPT} - {@value EntryExtraData#PARAM_ENABLE_OTHER_PROMPT} controls whether prompt Other Cashback <br>
     *     Type: Boolean<br>
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CASHBACK_AMOUNT} - {@value EntryRequest#PARAM_CASHBACK_AMOUNT}<br>
     *     Type: Long
     * </p>
     */
    public static final String ACTION_ENTER_CASH_BACK = "com.pax.us.pay.action.ENTER_CASH_BACK";

    /**
     * Activity Action: Enter Original Trans. Date<br>{@value #ACTION_ENTER_ORIG_DATE}<br>
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
     *     Output: {@link EntryRequest#PARAM_ORIG_DATE} - {@value EntryRequest#PARAM_ORIG_DATE} <br>Type: String</p>
     */
    public static final String ACTION_ENTER_ORIG_DATE = "com.pax.us.pay.action.ENTER_ORIG_DATE";

    /**
     * Activity Action: Enter Fuel Amount<br>{@value #ACTION_ENTER_FUEL_AMOUNT}<br>
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
     *     Default value: {@link com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType#USD}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} - {@value EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-12"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FUEL_AMOUNT} - {@value EntryRequest#PARAM_FUEL_AMOUNT} <br>Type: Long </p>
     */
    public static final String ACTION_ENTER_FUEL_AMOUNT = "com.pax.us.pay.action.ENTER_FUEL_AMOUNT";

    /**
     * Activity Action: Enter Tax Amount<br>{@value #ACTION_ENTER_TAX_AMOUNT}<br>
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
     *     Default value: {@link com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType#USD}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} - {@value EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-12"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_TAX_AMOUNT} - {@value EntryRequest#PARAM_TAX_AMOUNT} <br>Type: Long</p>
     */
    public static final String ACTION_ENTER_TAX_AMOUNT = "com.pax.us.pay.action.ENTER_TAX_AMOUNT";


    /**
     * Activity Action: Enter Table Number (For Restaurant Only)<br>{@value #ACTION_ENTER_TABLE_NUMBER}<br>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} - {@value EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-4"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_TABLE_NUMBER} - {@value EntryRequest#PARAM_TABLE_NUMBER} <br>
     *     Type: String<br>
     *     Optional. If return nothing, BroadPOS will treat as bypass.</p>
     */
    public static final String ACTION_ENTER_TABLE_NUMBER = "com.pax.us.pay.action.ENTER_TABLE_NUMBER";

    /**
     * Activity Action: Enter Phone Number<br>{@value #ACTION_ENTER_PHONE_NUMBER}<br>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} - {@value EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "1-32"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_PHONE_NUMBER} - {@value EntryRequest#PARAM_PHONE_NUMBER} <br>Type: String </p>
     */
    public static final String ACTION_ENTER_PHONE_NUMBER = "com.pax.us.pay.action.ENTER_PHONE_NUMBER";


    /**
     * Activity Action: Enter Guest Number (For Restaurant Only)<br>{@value #ACTION_ENTER_GUEST_NUMBER}<br>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} - {@value EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-4"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_GUEST_NUMBER} - {@value EntryRequest#PARAM_GUEST_NUMBER} <br>Type: String </p>
     */
    public static final String ACTION_ENTER_GUEST_NUMBER = "com.pax.us.pay.action.ENTER_GUEST_NUMBER";

    /**
     * Activity Action: Enter Order Number<br>{@value #ACTION_ENTER_ORDER_NUMBER}<br>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} - {@value EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-12"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_ORDER_NUMBER} - {@value EntryRequest#PARAM_ORDER_NUMBER} <br>Type: String </p>
     */
    public static final String ACTION_ENTER_ORDER_NUMBER = "com.pax.us.pay.action.ENTER_ORDER_NUMBER";

    /**
     * Activity Action: Enter PO Number<br>{@value #ACTION_ENTER_PO_NUMBER}<br>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} - {@value EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-17"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_PO_NUMBER} - {@value EntryRequest#PARAM_PO_NUMBER} <br>Type: String</p>
     */
    public static final String ACTION_ENTER_PO_NUMBER = "com.pax.us.pay.action.ENTER_PO_NUMBER";

    /**
     * Activity Action: Enter Product Description<br>{@value #ACTION_ENTER_PROD_DESC}<br>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} - {@value EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-40"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_PROD_DESC} -{@value EntryRequest#PARAM_PROD_DESC}  <br>Type: String </p>
     */
    public static final String ACTION_ENTER_PROD_DESC = "com.pax.us.pay.action.ENTER_PROD_DESC";

    /**
     * Activity Action: Enter Customer Code<br>{@value #ACTION_ENTER_CUSTOMER_CODE}<br>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} - {@value EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-25"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CUSTOMER_CODE} - {@value EntryRequest#PARAM_CUSTOMER_CODE}  <br>Type: String </p>
     */
    public static final String ACTION_ENTER_CUSTOMER_CODE = "com.pax.us.pay.action.ENTER_CUSTOMER_CODE";

    /**
     * Activity Action: Enter Prompt Restriction Code<br>{@value #ACTION_ENTER_PROMPT_RESTRICTION_CODE}<br>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} - {@value EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "2-2"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_PROMPT_RESTRICTION_CODE} - {@value EntryRequest#PARAM_PROMPT_RESTRICTION_CODE} <br>Type: String</p>
     */
    public static final String ACTION_ENTER_PROMPT_RESTRICTION_CODE = "com.pax.us.pay.action.ENTER_PROMPT_RESTRICTION_CODE";

    /**
     * Activity Action: Enter Fleet Data<br>{@value #ACTION_ENTER_FLEET_DATA}<br>
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
     *     Input: {@link EntryExtraData#PARAM_FLEET_DRIVER_ID_PATTERN} - {@value EntryExtraData#PARAM_FLEET_DRIVER_ID_PATTERN}  <br>Type: String</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_FLEET_ODOMETER_PATTERN} - {@value EntryExtraData#PARAM_FLEET_ODOMETER_PATTERN} <br>Type: String</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_FLEET_VEHICLE_NUMBER_PATTERN}-{@value EntryExtraData#PARAM_FLEET_VEHICLE_NUMBER_PATTERN} <br>Type: String </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_FLEET_LICENSE_NUMBER_PATTERN} -{@value EntryExtraData#PARAM_FLEET_LICENSE_NUMBER_PATTERN} <br>Type: String</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_FLEET_JOB_NUMBER_PATTERN} - {@value EntryExtraData#PARAM_FLEET_JOB_NUMBER_PATTERN}  <br>Type: String</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_FLEET_DEPARTMENT_NUMBER_PATTERN} - {@value EntryExtraData#PARAM_FLEET_DEPARTMENT_NUMBER_PATTERN}  <br>Type: String</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_FLEET_CUSTOMER_DATA_PATTERN} - {@value EntryExtraData#PARAM_FLEET_CUSTOMER_DATA_PATTERN}  <br>Type: String</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_FLEET_USER_ID_PATTERN} - {@value EntryExtraData#PARAM_FLEET_USER_ID_PATTERN} <br>Type: String</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_FLEET_VEHICLE_ID_PATTERN} - {@value EntryExtraData#PARAM_FLEET_VEHICLE_ID_PATTERN}  <br>Type: String</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FLEET_DRIVER_ID} - {@value EntryRequest#PARAM_FLEET_DRIVER_ID} <br>Type: String </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FLEET_ODOMETER} - {@value EntryRequest#PARAM_FLEET_ODOMETER}  <br>Type: String</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FLEET_VEHICLE_NUMBER} - {@value EntryRequest#PARAM_FLEET_VEHICLE_NUMBER}  <br>Type: String</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FLEET_LICENSE_NUMBER} - {@value EntryRequest#PARAM_FLEET_LICENSE_NUMBER}  <br>Type: String</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FLEET_JOB_NUMBER} - {@value EntryRequest#PARAM_FLEET_JOB_NUMBER}  <br>Type: String</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FLEET_DEPARTMENT_NUMBER} - {@value EntryRequest#PARAM_FLEET_DEPARTMENT_NUMBER}<br>Type: String </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FLEET_CUSTOMER_DATA} - {@value EntryRequest#PARAM_FLEET_CUSTOMER_DATA} <br>Type: String </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FLEET_USER_ID} -{@value EntryRequest#PARAM_FLEET_USER_ID} <br>Type: String </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FLEET_VEHICLE_ID} - {@value EntryRequest#PARAM_FLEET_VEHICLE_ID} <br>Type: String </p>
     */
    public static final String ACTION_ENTER_FLEET_DATA = "com.pax.us.pay.action.ENTER_FLEET_DATA";


    /**
     * Activity Action: Enter Total Amount<br>{@value #ACTION_ENTER_TOTAL_AMOUNT}<br>
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
     *     Default value: {@link com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType#USD}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_BASE_AMOUNT} - {@value EntryExtraData#PARAM_BASE_AMOUNT} is base amount<br>
     *     Type: Long</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIP_NAME} - {@value EntryExtraData#PARAM_TIP_NAME} is tip name<br>
     *     Type:String</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} - {@value EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-12"
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ENABLE_NO_TIP_SELECTION} - {@value EntryExtraData#PARAM_ENABLE_NO_TIP_SELECTION} <br>
     *     Type: Boolean<br>
     *     Optional. TRUE means NO TIP SELECTION is enabled.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_TOTAL_AMOUNT} - {@value EntryRequest#PARAM_TOTAL_AMOUNT} <br>
     *     Type: String
     * </p>
     */
    public static final String ACTION_ENTER_TOTAL_AMOUNT = "com.pax.us.pay.action.ENTER_TOTAL_AMOUNT";

    /**
     * Activity Action: Enter Destination Zip Code<br>{@value #ACTION_ENTER_DEST_ZIPCODE}<br>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} - {@value EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-9"</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_EINPUT_TYPE} - {@value EntryExtraData#PARAM_EINPUT_TYPE} <br>
     *     Type: String<br>
     *     If value is {@value com.pax.us.pay.ui.constant.entry.enumeration.InputType#ALLTEXT},
     *     the output {@value EntryRequest#PARAM_DEST_ZIP_CODE} could be alpha-numeric string.<br>
     *     Else it should be numeric string.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_DEST_ZIP_CODE} - {@value EntryRequest#PARAM_DEST_ZIP_CODE} <br>
     *     Type: String
     * </p>
     */
    public static final String ACTION_ENTER_DEST_ZIPCODE = "com.pax.us.pay.action.ENTER_DEST_ZIP_CODE";

    /**
     * Activity Action: Enter Customer Service Phone Number<br>{@value #ACTION_ENTER_CS_PHONE_NUMBER}<br>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} - {@value EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "1-32"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_PHONE_NUMBER} - {@value EntryRequest#PARAM_PHONE_NUMBER}  <br>
     *     Type: String
     * </p>
     */
    public static final String ACTION_ENTER_CS_PHONE_NUMBER = "com.pax.us.pay.action.ENTER_CS_PHONE_NUMBER";

    /**
     * Activity Action: Enter Merchant Tax ID<br>{@value #ACTION_ENTER_MERCHANT_TAX_ID}<br>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} - {@value EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-15"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_MERCHANT_TAX_ID} - {@value EntryRequest#PARAM_MERCHANT_TAX_ID} <br>
     *     Type: String
     * </p>
     */
    public static final String ACTION_ENTER_MERCHANT_TAX_ID = "com.pax.us.pay.action.ENTER_MERCHANT_TAX_ID";

    /**
     * Activity Action: Enter Merchant Reference Number<br>{@value #ACTION_ENTER_MERCHANT_REFERENCE_NUMBER}<br>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} - {@value EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-12"</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_EINPUT_TYPE} - {@value EntryExtraData#PARAM_EINPUT_TYPE} <br>
     *     Type: String<br>
     *     If value is {@value com.pax.us.pay.ui.constant.entry.enumeration.InputType#ALLTEXT},
     *     the output {@value EntryRequest#PARAM_MERCHANT_REFERENCE_NUMBER} could be alpha-numeric string.<br>
     *     Else it should be numeric string.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_MERCHANT_REFERENCE_NUMBER} - {@value EntryRequest#PARAM_MERCHANT_REFERENCE_NUMBER} <br>
     *     Type: String
     * </p>
     */
    public static final String ACTION_ENTER_MERCHANT_REFERENCE_NUMBER = "com.pax.us.pay.action.ENTER_MERCHANT_REFERENCE_NUMBER";

    /**
     * Activity Action: Enter OCT Reference Number<br>{@value #ACTION_ENTER_OCT_REFERENCE_NUMBER}<br>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} - {@value EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-12"</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_EINPUT_TYPE} - {@value EntryExtraData#PARAM_EINPUT_TYPE} <br>
     *     Type: String<br>
     *     If value is {@value com.pax.us.pay.ui.constant.entry.enumeration.InputType#ALLTEXT},
     *     the output {@value EntryRequest#PARAM_OCT_REFERENCE_NUMBER} could be alpha-numeric string.<br>
     *     Else it should be numeric string.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_OCT_REFERENCE_NUMBER} - {@value EntryRequest#PARAM_OCT_REFERENCE_NUMBER} <br>
     *     Type: String
     * </p>
     */
    public static final String ACTION_ENTER_OCT_REFERENCE_NUMBER = "com.pax.us.pay.action.ENTER_OCT_REFERENCE_NUMBER";

    //-----------------------------Visa Installment---------------------------------
    /**
     * Activity Action: Enter Visa Installment Transaction ID (For Visa Installment use)<br>{@value #ACTION_ENTER_VISA_INSTALLMENT_TRANSACTIONID}<br>
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
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} - {@value EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000.
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} - {@value EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-20"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_VISA_TRANSID} - {@value EntryRequest#PARAM_VISA_TRANSID}<br>
     *     Type: String
     * </p>
     */
    public static final String ACTION_ENTER_VISA_INSTALLMENT_TRANSACTIONID = "com.pax.us.pay.action.ENTER_VISA_INSTALLMENT_TRANSACTIONID";

    /**
     * Activity Action: Enter Visa Installment Plan Acceptance ID (For Visa Installment use)<br>{@value #ACTION_ENTER_VISA_INSTALLMENT_PLAN_ACCEPTANCE_ID}<br>
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
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} - {@value EntryExtraData#PARAM_TIMEOUT} is timeout. <br>
     *     Type: Long<br>
     *     Unit: ms<br>
     *     Default is 30000.
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} - {@value EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-36"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_VISA_PLAN_ACCEPTANCE_ID} - {@value EntryRequest#PARAM_VISA_PLAN_ACCEPTANCE_ID} <br>
     *     Type: String
     * </p>
     */
    public static final String ACTION_ENTER_VISA_INSTALLMENT_PLAN_ACCEPTANCE_ID = "com.pax.us.pay.action.ENTER_VISA_INSTALLMENT_PLAN_ACCEPTANCE_ID";

}
