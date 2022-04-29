package com.pax.us.pay.ui.constant.entry;

/**
 * define Activity for TEXT
 */
public final class TextEntry {
    private TextEntry(){
        
    }

    /**
     * Activity Category: TEXT
     */
    public static final String CATEGORY = "com.pax.us.pay.ui.category.TEXT";
    /**
     * Activity Action: Enter Amount
     * <p>"Please Enter Amount"</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_TYPE} is transaction name. Example: "CREDIT SALE"</p>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-12"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_AMOUNT} <br>
     *     Type: Long
     * </p>
     */
    public static final String ACTION_ENTER_AMOUNT = "com.pax.us.pay.action.ENTER_AMOUNT";

    /**
     * Activity Action: Enter Tip
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_TYPE} is transaction name. Example: "CREDIT SALE"</p>
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
     *     Input: {@link EntryExtraData#PARAM_BASE_AMOUNT}<br>
     *     Type: Long<br>
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIP_NAME}<br>
     *     Type: String<br>
     *     Default value is "Tip"<br>
     *     In BroadPOS standalone mode, if multiple tips are enabled, value might be "Tip1", "Tip2", or any value configured by merchant<br>
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIP_OPTIONS} is tip options.
     *     Type: String[]<br>
     *     It is optional. Used when tip select is enabled.<br>
     * </p>
     *
     * <p>
     *     Example:<br>
     *     if value of {@link EntryExtraData#PARAM_TIP_OPTIONS} is {"300","500","700"}, <br>
     *     you could provide 3 options for user:<br><br>
     *     $3.00, $5.00, %7.00
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIP_RATE_OPTIONS} is tip percentage options.<br>
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
     *     Input: {@link EntryExtraData#PARAM_TIP_UNIT}. <br>
     *     Type: String<br>
     *     Default is {@link com.pax.us.pay.ui.constant.entry.enumeration.UnitType#CENT}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-12". It is used for manual input tip.
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_NAME_OF_ENABLED_TIPS}<br>
     *     Type: String[]<br>
     *     Optional. For Standalone use only. Used when multiple tips are enabled.
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_VALUE_OF_ENABLED_TIPS} <br>
     *     Type: String[]<br>
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
     *     Input: {@link EntryExtraData#PARAM_ENABLE_NO_TIP_SELECTION} <br>
     *     Type: Boolean<br>
     *     Optional. TRUE means NO TIP SELECTION is enabled.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_TIP}<br>
     *     Type: Long<br>
     *     Optional. <br>
     *     If return nothing, BroadPOS will treat it as tip bypassed.<br>
     *     If return 0, BroadPOS will treat it as "NO TIP"<br>
     * </p>
     */
    public static final String ACTION_ENTER_TIP = "com.pax.us.pay.action.ENTER_TIP";

    /**
     * Activity Action: Enter Trans. No.
     * <p>"Please Enter Transaction Number"</p>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-4"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_TRANS_NUMBER} <br>Type: String
     *     </p>
     */
    public static final String ACTION_ENTER_TRANS_NUMBER = "com.pax.us.pay.action.ENTER_TRANS_NUMBER";

    /**
     * Activity Action: Enter Expiry Date
     * <p>"Please Enter Expiry Date"</p>
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
     *     Output: {@link EntryRequest#PARAM_EXPIRY_DATE} <br>Type: String</p>
     */
    public static final String ACTION_ENTER_EXPIRY_DATE = "com.pax.us.pay.action.ENTER_EXPIRY_DATE";

    /**
     * Activity Action: Enter Address
     * <p>"Please Enter Address"</p>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-30"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_ADDRESS}  <br>Type: String</p>
     */
    public static final String ACTION_ENTER_ADDRESS = "com.pax.us.pay.action.ENTER_ADDRESS";

    /**
     * Activity Action: Enter Zip Code
     * <p>"Please Enter Zip Code"</p>
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
     *     Input: {@link EntryExtraData#PARAM_INPUT_TYPE} <br>
     *     Could be {@link com.pax.us.pay.ui.constant.entry.enumeration.InputType#NUMERIC} and {@link com.pax.us.pay.ui.constant.entry.enumeration.InputType#ALPHA_NUMERIC}<br>
     *     Default: {@link com.pax.us.pay.ui.constant.entry.enumeration.InputType#NUMERIC}<br>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-9"
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_ZIP_CODE} <br>Type: String </p>
     */
    public static final String ACTION_ENTER_ZIPCODE = "com.pax.us.pay.action.ENTER_ZIP_CODE";

    /**
     * Activity Action: Enter Auth Code
     * <p>"Please Enter Auth Code"</p>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "1-15"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_AUTH_CODE}  <br>Type: String</p>
     */
    public static final String ACTION_ENTER_AUTH = "com.pax.us.pay.action.ENTER_AUTH_CODE";

    /**
     * Activity Action: Enter FSA Amount
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
     *     Input: {@link EntryExtraData#PARAM_TOTAL_AMOUNT} is total amount</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_FSA_AMOUNT_OPTIONS} is fsa amount options.<br>
     *     Type: String[]
     *     it control output fields.<br>
     *     Example:<br>
     *     If options are {"clinicAmount","prescriptionAmount"}, required outputs are 
     *     {@link EntryRequest#PARAM_CLINIC_AMOUNT} and {@link EntryRequest#PARAM_PRESCRIPTION_AMOUNT}
     *     Type: String[]<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.FSAAmountType}<br>
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_HEALTH_CARE_AMOUNT}<br>
     *     Type: Long
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CLINIC_AMOUNT} <br>
     *     Type: Long
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_PRESCRIPTION_AMOUNT} <br>
     *     Type: Long
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_VISION_AMOUNT} <br>
     *     Type: Long
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_DENTAL_AMOUNT} <br>
     *     Type: Long
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_COPAY_AMOUNT} <br>
     *     Type: Long
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_OTC_AMOUNT} <br>
     *     Type: Long
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_TRANSIT_AMOUNT}<br>
     *     Type: Long
     * </p>
     */
    public static final String ACTION_ENTER_FSA_DATA = "com.pax.us.pay.action.ENTER_FSA_DATA";


    /**
     * Activity Action: Enter Voucher Number
     * <p>"Please Enter Voucher Number" </p>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-15"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_VOUCHER_NUMBER} <br>Type: String </p>
     */
    public static final String ACTION_ENTER_VOUCHER_DATA = "com.pax.us.pay.action.ENTER_VOUCHER_DATA";

    /**
     * Activity Action: Enter Address and Zip code
     * <p>
     *     "Please Enter Address"<br>
     *     "Please Enter Zip Code"
     * </p>
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
     *     Input: {@link EntryExtraData#PARAM_ZIP_CODE_PATTERN} is length limit for Zip Code. Default: "0-9" </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ADDRESS_PATTERN} is length limit for Address. Default: "0-30" </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_INPUT_TYPE} is input type for Zip Code. <br>
     *     Could be {@link com.pax.us.pay.ui.constant.entry.enumeration.InputType#NUMERIC} and {@link com.pax.us.pay.ui.constant.entry.enumeration.InputType#ALPHA_NUMERIC}<br>
     *     Default is {@link com.pax.us.pay.ui.constant.entry.enumeration.InputType#NUMERIC}
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_ADDRESS} <br>Type: String</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_ZIP_CODE} <br>Type: String</p>
     */
    public static final String ACTION_ENTER_AVS_DATA = "com.pax.us.pay.action.ENTER_AVS_DATA";

    /**
     * Activity Action:  Enter Reference Number
     * <p>"Please Enter Reference Number"</p>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "1-16"</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_INPUT_TYPE} <br>
     *     Could be {@link com.pax.us.pay.ui.constant.entry.enumeration.InputType#NUMERIC} and {@link com.pax.us.pay.ui.constant.entry.enumeration.InputType#ALPHA_NUMERIC}</p>
     *     Default: {@link com.pax.us.pay.ui.constant.entry.enumeration.InputType#NUMERIC}
     * <p>
     *     Output: {@link EntryRequest#PARAM_REFERENCE_NUMBER}  <br>Type: String</p>
     */
    public static final String ACTION_ENTER_REFERENCE_NUMBER = "com.pax.us.pay.action.ENTER_REFERENCE_NUMBER";


    /**
     * Activity Action: Enter Invoice Number
     * <p>"Please Enter Invoice Number"</p>
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
     *     Input: {@link EntryExtraData#PARAM_INPUT_TYPE} <br>
     *     Could be {@link com.pax.us.pay.ui.constant.entry.enumeration.InputType#NUMERIC} and {@link com.pax.us.pay.ui.constant.entry.enumeration.InputType#ALPHA_NUMERIC}<br>
     *     Default: {@link com.pax.us.pay.ui.constant.entry.enumeration.InputType#NUMERIC}
     * <p>
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-20"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INVOICE_NUMBER} <br>Type: String </p>
     */
    public static final String ACTION_ENTER_INVOICE_NUMBER = "com.pax.us.pay.action.ENTER_INVOICE_NUMBER";

    /**
     * Activity Action: Enter Clerk ID
     * <p>"Please Enter Clerk ID"</p>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-4"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CLERK_ID}  <br>Type: String</p>
     */
    public static final String ACTION_ENTER_CLERK_ID = "com.pax.us.pay.action.ENTER_CLERK_ID";

    /**
     * Activity Action: Enter Server ID (For Restaurant only)
     * <p>"Please Enter Server ID"</p>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-4"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_SERVER_ID} <br>Type: String</p>
     */
    public static final String ACTION_ENTER_SERVER_ID = "com.pax.us.pay.action.ENTER_SERVER_ID";

    /**
     * Activity Action: Enter Cashback
     * <p>"Please Enter Cashback"</p>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} <br>
     *     Type: String<br>
     *     Default: "0-12". It is length limit for cashback manual input.
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_CASHBACK_OPTIONS} is cashback options.<br>
     *     Type:String[]
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ENABLE_OTHER_PROMPT} controls whether prompt Other Cashback <br>
     *     Type: Boolean<br>
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CASHBACK_AMOUNT}<br>
     *     Type: Long
     * </p>
     */
    public static final String ACTION_ENTER_CASH_BACK = "com.pax.us.pay.action.ENTER_CASH_BACK";

    /**
     * Activity Action: Enter Original Trans. Date
     * <p>"Please Enter Original Transaction Date"</p>
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
     *     Output: {@link EntryRequest#PARAM_ORIG_DATE} <br>Type: String</p>
     */
    public static final String ACTION_ENTER_ORIG_DATE = "com.pax.us.pay.action.ENTER_ORIG_DATE";

    /**
     * Activity Action: Enter Fuel Amount
     * <p>"Please Enter Fuel Amount"</p>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-12"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FUEL_AMOUNT} <br>Type: Long </p>
     */
    public static final String ACTION_ENTER_FUEL_AMOUNT = "com.pax.us.pay.action.ENTER_FUEL_AMOUNT";

    /**
     * Activity Action: Enter Tax Amount
     * <p>"Please Enter Tax Amount"</p>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-12"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_TAX_AMOUNT} <br>Type: Long</p>
     */
    public static final String ACTION_ENTER_TAX_AMOUNT = "com.pax.us.pay.action.ENTER_TAX_AMOUNT";


    /**
     * Activity Action: Enter Table Number (For Restaurant Only)
     * <p>"Please Enter Table Number"</p>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-4"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_TABLE_NUMBER} <br>
     *     Type: String<br>
     *     Optional. If return nothing, BroadPOS will treat as bypass.</p>
     */
    public static final String ACTION_ENTER_TABLE_NUMBER = "com.pax.us.pay.action.ENTER_TABLE_NUMBER";

    /**
     * Activity Action: Enter Phone Number
     * <p>"Please Enter Phone Number"</p>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "1-32"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_PHONE_NUMBER} <br>Type: String </p>
     */
    public static final String ACTION_ENTER_PHONE_NUMBER = "com.pax.us.pay.action.ENTER_PHONE_NUMBER";


    /**
     * Activity Action: Enter Guest Number (For Restaurant Only)
     * <p>"Please Enter Guest Number"</p>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-4"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_GUEST_NUMBER} <br>Type: String </p>
     */
    public static final String ACTION_ENTER_GUEST_NUMBER = "com.pax.us.pay.action.ENTER_GUEST_NUMBER";

    /**
     * Activity Action: Enter Order Number
     * <p>"Please Enter Order Number"</p>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-12"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_ORDER_NUMBER} <br>Type: String </p>
     */
    public static final String ACTION_ENTER_ORDER_NUMBER = "com.pax.us.pay.action.ENTER_ORDER_NUMBER";

    /**
     * Activity Action: Enter PO Number
     * <p>"Please Enter P.O. Number"</p>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-17"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_PO_NUMBER} <br>Type: String</p>
     */
    public static final String ACTION_ENTER_PO_NUMBER = "com.pax.us.pay.action.ENTER_PO_NUMBER";

    /**
     * Activity Action: Enter Product Description
     * <p>"Please Enter Product Description"</p>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-40"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_PROC_DESC} <br>Type: String </p>
     */
    public static final String ACTION_ENTER_PROD_DESC = "com.pax.us.pay.action.ENTER_PROD_DESC";

    /**
     * Activity Action: Enter Customer Code
     * <p>"Please Enter Customer Code"</p>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-25"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_CUSTOMER_CODE}  <br>Type: String </p>
     */
    public static final String ACTION_ENTER_CUSTOMER_CODE = "com.pax.us.pay.action.ENTER_CUSTOMER_CODE";

    /**
     * Activity Action: Enter Prompt Restriction Code
     * <p>"Please Enter Prompt Restriction Code"</p>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-2"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_PROMPT_RESTRICTION_CODE} <br>Type: String</p>
     */
    public static final String ACTION_ENTER_PROMPT_RESTRICTION_CODE = "com.pax.us.pay.action.ENTER_PROMPT_RESTRICTION_CODE";

    /**
     * Activity Action: Enter Fleet Data
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
     *     Input: {@link EntryExtraData#PARAM_FLEET_DRIVER_ID_PATTERN}  <br>Type: String</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_FLEET_ODOMETER_PATTERN}  <br>Type: String</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_FLEET_VEHICLE_NUMBER_PATTERN} <br>Type: String </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_FLEET_LICENSE_NUMBER_PATTERN}  <br>Type: String</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_FLEET_JOB_NUMBER_PATTERN}  <br>Type: String</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_FLEET_DEPARTMENT_NUMBER_PATTERN}  <br>Type: String</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_FLEET_CUSTOMER_DATA_PATTERN}  <br>Type: String</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_FLEET_USER_ID_PATTERN}  <br>Type: String</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_FLEET_VEHICLE_ID_PATTERN}  <br>Type: String</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FLEET_DRIVER_ID} <br>Type: String </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FLEET_ODOMETER}  <br>Type: String</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FLEET_VEHICLE_NUMBER}  <br>Type: String</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FLEET_LICENSE_NUMBER}  <br>Type: String</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FLEET_JOB_NUMBER}  <br>Type: String</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FLEET_DEPARTMENT_NUMBER} <br>Type: String </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FLEET_CUSTOMER_DATA} <br>Type: String </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FLEET_USER_ID} <br>Type: String </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_FLEET_VEHICLE_ID} <br>Type: String </p>
     */
    public static final String ACTION_ENTER_FLEET_DATA = "com.pax.us.pay.action.ENTER_FLEET_DATA";


    /**
     * Activity Action: Enter Total Amount
     * <p>"Please Enter Total Amount"</p>
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
     *     Input: {@link EntryExtraData#PARAM_BASE_AMOUNT} is base amount</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIP_NAME} is tip name</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-12"
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ENABLE_NO_TIP_SELECTION} <br>
     *     Type: Boolean<br>
     *     Optional. TRUE means NO TIP SELECTION is enabled.
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_TOTAL_AMOUNT} <br>
     *     Type: String
     * </p>
     */
    public static final String ACTION_ENTER_TOTAL_AMOUNT = "com.pax.us.pay.action.ENTER_TOTAL_AMOUNT";

    /**
     * Activity Action: Enter Destination Zip Code
     * <p>"Please Enter Destination Zip Code"</p>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-9"</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_INPUT_TYPE} <br>
     *     Could be {@link com.pax.us.pay.ui.constant.entry.enumeration.InputType#NUMERIC} and {@link com.pax.us.pay.ui.constant.entry.enumeration.InputType#ALPHA_NUMERIC}<br>
     *     Default: {@link com.pax.us.pay.ui.constant.entry.enumeration.InputType#NUMERIC}
     * <p>
     *     Output: {@link EntryRequest#PARAM_DEST_ZIP_CODE} <br>
     *     Type: String
     * </p>
     */
    public static final String ACTION_ENTER_DEST_ZIPCODE = "com.pax.us.pay.action.ENTER_DEST_ZIP_CODE";

    /**
     * Activity Action: Enter Customer Service Phone Number
     * <p>"Please Enter Customer Service Phone"</p>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "1-32"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_PHONE_NUMBER} <br>
     *     Type: String
     * </p>
     */
    public static final String ACTION_ENTER_CS_PHONE_NUMBER = "com.pax.us.pay.action.ENTER_CS_PHONE_NUMBER";

    /**
     * Activity Action: Enter merchant tax id
     * <p>"Please Enter Merchant Tax ID"</p>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-15"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_MERCHANT_TAX_ID} <br>
     *     Type: String
     * </p>
     */
    public static final String ACTION_ENTER_MERCHANT_TAX_ID = "com.pax.us.pay.action.ENTER_MERCHANT_TAX_ID";

    /**
     * Activity Action: Enter Merchant Reference Number
     * <p>"Please Enter Merchant Reference Number"</p>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-12"</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_INPUT_TYPE} <br>
     *     Could be {@link com.pax.us.pay.ui.constant.entry.enumeration.InputType#NUMERIC} and {@link com.pax.us.pay.ui.constant.entry.enumeration.InputType#ALPHA_NUMERIC}<br>
     *     Default: {@link com.pax.us.pay.ui.constant.entry.enumeration.InputType#NUMERIC}
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_MERCHANT_REFERENCE_NUMBER} <br>
     *     Type: String
     * </p>
     */
    public static final String ACTION_ENTER_MERCHANT_REFERENCE_NUMBER = "com.pax.us.pay.action.ENTER_MERCHANT_REFERENCE_NUMBER";

    /**
     * Activity Action: Enter OCT reference number
     * <p>"Please Enter OCT Reference Number"</p>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-12"</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_INPUT_TYPE} <br>
     *     Could be {@link com.pax.us.pay.ui.constant.entry.enumeration.InputType#NUMERIC} and {@link com.pax.us.pay.ui.constant.entry.enumeration.InputType#ALPHA_NUMERIC}<br>
     *     Default: {@link com.pax.us.pay.ui.constant.entry.enumeration.InputType#NUMERIC}
     * <p>
     *     Output: {@link EntryRequest#PARAM_OCT_REFERENCE_NUMBER} <br>
     *     Type: String
     * </p>
     */
    public static final String ACTION_ENTER_OCT_REFERENCE_NUMBER = "com.pax.us.pay.action.ENTER_OCT_REFERENCE_NUMBER";

    /**
     * Activity Action: Enter Visa Installment Transaction ID
     * <p>"Please Enter Visa Installment TransactionID"</p>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-20"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_VISA_TRANSID}<br>
     *     Type: String
     * </p>
     */
    public static final String ACTION_ENTER_VISA_INSTALLMENT_TRANSACTIONID = "com.pax.us.pay.action.ENTER_VISA_INSTALLMENT_TRANSACTIONID";


    /**
     * Activity Action: Enter Visa Installment Plan Acceptance ID
     * <p>"Please Enter Plan Acceptance ID"</p>
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
     *     Input: {@link EntryExtraData#PARAM_VALUE_PATTERN} is length limit. <br>
     *     Type: String<br>
     *     Default: "0-36"</p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_VISA_PLAN_ACCEPTANCE_ID} <br>
     *     Type: String
     * </p>
     */
    public static final String ACTION_ENTER_VISA_INSTALLMENT_PLAN_ACCEPTANCE_ID = "com.pax.us.pay.action.ENTER_VISA_INSTALLMENT_PLAN_ACCEPTANCE_ID";

}
