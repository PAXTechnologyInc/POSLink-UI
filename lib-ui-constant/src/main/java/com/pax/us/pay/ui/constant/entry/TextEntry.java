package com.pax.us.pay.ui.constant.entry;

public class TextEntry {
    public static final String CATEGORY = "com.pax.us.pay.ui.category.TEXT";
    /**
     * The intent action of Enter Amount <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_CURRENCY}  enum: {@link com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType} <br>
     * request: {@link EntryRequest#PARAM_AMOUNT} <br>
     */
    public static final String ACTION_ENTER_AMOUNT = "com.pax.us.pay.action.ENTER_AMOUNT";
    /**
     * The intent action of Enter Tip <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_CURRENCY}  enum: {@link com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType} <br>
     * input: {@link EntryExtraData#PARAM_BASE_AMOUNT} <br>
     * input: {@link EntryExtraData#PARAM_TIP_NAME} <br>
     * input: {@link EntryExtraData#PARAM_TIP_OPTIONS} String Array, Nullable amount array <br>
     * input: {@link EntryExtraData#PARAM_TIP_RATE_OPTIONS} String Array, Nullable tip rate array,
     *         if it exist, the number of tip rate options should equal the number of  tip options <br>
     * input: {@link EntryExtraData#PARAM_TIP_UNIT enum: {@link com.pax.us.pay.ui.constant.entry.enumeration.UnitType}} <br>
     * input: {@link EntryExtraData#PARAM_MIN_LENGTH} <br>
     * input: {@link EntryExtraData#PARAM_MAX_LENGTH} <br>
     * request: {@link EntryRequest#PARAM_TIP} <br>
     */
    public static final String ACTION_ENTER_TIP = "com.pax.us.pay.action.ENTER_TIP";

    /**
     * The intent action of Enter Trans.No <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_MIN_LENGTH} <br>
     * input: {@link EntryExtraData#PARAM_MAX_LENGTH} <br>
     * request: {@link EntryRequest#PARAM_TRANS_NUMBER} <br>
     */
    public static final String ACTION_ENTER_TRANS_NUMBER = "com.pax.us.pay.action.ENTER_TRANS_NUMBER";

    /**
     * The intent action of Enter Exp date <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * request: {@link EntryRequest#PARAM_EXPIRY_DATE} <br>
     */
    public static final String ACTION_ENTER_EXPIRY_DATE = "com.pax.us.pay.action.ENTER_EXPIRY_DATE";

    /**
     * The intent action of Enter Address <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * request: {@link EntryRequest#PARAM_ADDRESS} <br>
     */
    public static final String ACTION_ENTER_ADDRESS = "com.pax.us.pay.action.ENTER_ADDRESS";

    /**
     * The intent action of Enter Zip Code <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * request: {@link EntryRequest#PARAM_ZIP_CODE} <br>
     */
    public static final String ACTION_ENTER_ZIPCODE = "com.pax.us.pay.action.ENTER_ZIP_CODE";

    /**
     * The intent action of Enter AuthCode <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_MIN_LENGTH} <br>
     * input: {@link EntryExtraData#PARAM_MAX_LENGTH} <br>
     * request: {@link EntryRequest#PARAM_AUTH_CODE} <br>
     */
    public static final String ACTION_ENTER_AUTH = "com.pax.us.pay.action.ENTER_AUTH_CODE";

    /**
     * The intent action of Enter FSA Amount <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_CURRENCY}  enum: {@link com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType} <br>
     * input: {@link EntryExtraData#PARAM_TOTAL_AMOUNT} <br>
     * input: {@link EntryExtraData#PARAM_FSA_AMOUNT_OPTIONS} enum: {@link com.pax.us.pay.ui.constant.entry.enumeration.FSAAmountType} <br>
     * request: {@link EntryRequest#PARAM_HEALTH_CARE_AMOUNT} <br>
     * request: {@link EntryRequest#PARAM_CLINIC_AMOUNT} <br>
     * request: {@link EntryRequest#PARAM_PRESCRIPTION_AMOUNT} <br>
     * request: {@link EntryRequest#PARAM_VISION_AMOUNT} <br>
     * request: {@link EntryRequest#PARAM_DENTAL_AMOUNT} <br>
     * request: {@link EntryRequest#PARAM_COPAY_AMOUNT} <br>
     * request: {@link EntryRequest#PARAM_TRANSIT_AMOUNT} <br>
     * request: {@link EntryRequest#PARAM_FSA_OPTION} enum: {@link com.pax.us.pay.ui.constant.entry.enumeration.FSAType}  <br>
     * the{@link EntryExtraData#PARAM_FSA_AMOUNT_OPTIONS} control which request field will be sent <br>
     */
    public static final String ACTION_ENTER_FSA_DATA = "com.pax.us.pay.action.ENTER_FSA_DATA";


    /**
     * The intent action of Enter voucher data <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * request: {@link EntryRequest#PARAM_VOUCHER_NUMBER} <br>
     * request: {@link EntryRequest#PARAM_AUTH_CODE} <br>
     */
    public static final String ACTION_ENTER_VOUCHER_DATA = "com.pax.us.pay.action.ENTER_VOUCHER_DATA";

    /**
     * The intent action of Enter AVS data <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * request: {@link EntryRequest#PARAM_ADDRESS} <br>
     * request: {@link EntryRequest#PARAM_ZIP_CODE}
     */
    public static final String ACTION_ENTER_AVS_DATA = "com.pax.us.pay.action.ENTER_AVS_DATA";

    /**
     * The intent action of Enter reference number <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_MIN_LENGTH} <br>
     * input: {@link EntryExtraData#PARAM_MAX_LENGTH} <br>
     * request: {@link EntryRequest#PARAM_REFERENCE_NUMBER} <br>
     */
    public static final String ACTION_ENTER_REFERENCE_NUMBER = "com.pax.us.pay.action.ENTER_REFERENCE_NUMBER";


    /**
     * The intent action of Enter invoice number <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_MIN_LENGTH} <br>
     * input: {@link EntryExtraData#PARAM_MAX_LENGTH} <br>
     * request: {@link EntryRequest#PARAM_INVOICE_NUMBER} <br>
     */
    public static final String ACTION_ENTER_INVOICE_NUMBER = "com.pax.us.pay.action.ENTER_INVOICE_NUMBER";

    /**
     * The intent action of Enter clerk id <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * request: {@link EntryRequest#PARAM_CLERK_ID} <br>
     */
    public static final String ACTION_ENTER_CLERK_ID = "com.pax.us.pay.action.ENTER_CLERK_ID";


    /**
     * The intent action of Enter cash back <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE}  <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE}  <br>
     * input: {@link EntryExtraData#PARAM_CURRENCY}  enum: {@link com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType}  <br>
     * input: {@link EntryExtraData#PARAM_TOTAL_AMOUNT} long  <br>
     * input: {@link EntryExtraData#PARAM_CASHBACK_OPTIONS} String Array, Nullable amount array  <br>
     * input: {@link EntryExtraData#PARAM_CASHBACK_RATE_OPTIONS} String Array, Nullable cashback rate array,
     *         if it exist, the number of cashback rate options should equal the number of  cashback options <br>
     * request: {@link EntryRequest#PARAM_CASHBACK_AMOUNT} long  <br>
     */
    public static final String ACTION_ENTER_CASH_BACK = "com.pax.us.pay.action.ENTER_CASH_BACK";

    /**
     * The intent action of Enter original transaction date <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE}  <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE}  <br>
     * request: {@link EntryRequest#PARAM_ORIG_DATE} <br>
     */
    public static final String ACTION_ENTER_ORIG_DATE = "com.pax.us.pay.action.ENTER_ORIG_DATE";

    /**
     * The intent action of Enter fuel amount <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE}  <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE}  <br>
     * input: {@link EntryExtraData#PARAM_CURRENCY}  enum: {@link com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType}  <br>
     * request: {@link EntryRequest#PARAM_FUEL_AMOUNT} long  <br>
     */
    public static final String ACTION_ENTER_FUEL_AMOUNT = "com.pax.us.pay.action.ENTER_FUEL_AMOUNT";

    /**
     * The intent action of Enter tax amount <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE}  <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE}  <br>
     * input: {@link EntryExtraData#PARAM_CURRENCY}  enum: {@link com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType}  <br>
     * input: {@link EntryExtraData#PARAM_TOTAL_AMOUNT} long  <br>
     * request: {@link EntryRequest#PARAM_TAX_AMOUNT} long  <br>
     */
    public static final String ACTION_ENTER_TAX_AMOUNT = "com.pax.us.pay.action.ENTER_TAX_AMOUNT";


    /**
     * The intent action of Enter server id <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE}  <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE}  <br>
     * request: {@link EntryRequest#PARAM_SERVER_ID} <br>
     */
    public static final String ACTION_ENTER_SERVER_ID = "com.pax.us.pay.action.ENTER_SERVER_ID";

    /**
     * The intent action of Enter table number <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE}  <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE}  <br>
     * request: {@link EntryRequest#PARAM_TABLE_NUMBER} <br>
     */
    public static final String ACTION_ENTER_TABLE_NUMBER = "com.pax.us.pay.action.ENTER_TABLE_NUMBER";

    /**
     * The intent action of Enter phone number <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE}  <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE}  <br>
     * request: {@link EntryRequest#PARAM_PHONE_NUMBER} <br>
     */
    public static final String ACTION_ENTER_PHONE_NUMBER = "com.pax.us.pay.action.ENTER_PHONE_NUMBER";


    /**
     * The intent action of Enter guest number <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE}  <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE}  <br>
     * request: {@link EntryRequest#PARAM_GUEST_NUMBER} <br>
     */
    public static final String ACTION_ENTER_GUEST_NUMBER = "com.pax.us.pay.action.ENTER_GUEST_NUMBER";

    /**
     * The intent action of Enter order number <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE}  <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE}  <br>
     * request: {@link EntryRequest#PARAM_ORDER_NUMBER} <br>
     */
    public static final String ACTION_ENTER_ORDER_NUMBER = "com.pax.us.pay.action.ENTER_ORDER_NUMBER";

    /**
     * The intent action of Enter P.O. number <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE}  <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE}  <br>
     * request: {@link EntryRequest#PARAM_PO_NUMBER} String <br>
     */
    public static final String ACTION_ENTER_PO_NUMBER = "com.pax.us.pay.action.ENTER_PO_NUMBER";

    /**
     * The intent action of Enter production description <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE}  <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE}  <br>
     * request: {@link EntryRequest#PARAM_PROC_DESC} String <br>
     */
    public static final String ACTION_ENTER_PROC_DESC = "com.pax.us.pay.action.ENTER_PROC_DESC";

    /**
     * The intent action of Enter customer code <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE}  <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE}  <br>
     * request: {@link EntryRequest#PARAM_CUSTOMER_CODE} String <br>
     */
    public static final String ACTION_ENTER_CUSTOMER_CODE = "com.pax.us.pay.action.ENTER_CUSTOMER_CODE";

    /**
     * The intent action of Enter prompt restriction code <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE}  <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE}  <br>
     * request: {@link EntryRequest#PARAM_PROMPT_RESTRICTION_CODE} String <br>
     */
    public static final String ACTION_ENTER_PROMPT_RESTRICTION_CODE = "com.pax.us.pay.action.ENTER_PROMPT_RESTRICTION_CODE";

    //FLEET CARD DATA
    /**
     * The intent action of Enter Fleet card data <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * request: {@link EntryRequest#PARAM_FLEET_DRIVER_ID} <br>
     * request: {@link EntryRequest#PARAM_FLEET_ODOMETER} <br>
     * request: {@link EntryRequest#PARAM_FLEET_VEHICLE_NUMBER} <br>
     * request: {@link EntryRequest#PARAM_FLEET_LICENSE_NUMBER} <br>
     * request: {@link EntryRequest#PARAM_FLEET_JOB_NUMBER} <br>
     * request: {@link EntryRequest#PARAM_FLEET_DEPARTMENT_NUMBER} <br>
     * request: {@link EntryRequest#PARAM_FLEET_CUSTOMER_DATA} <br>
     * request: {@link EntryRequest#PARAM_FLEET_USER_ID} <br>
     * request: {@link EntryRequest#PARAM_FLEET_VEHICLE_ID} <br>
     */
    public static final String ACTION_ENTER_FLEET_DATA = "com.pax.us.pay.action.ENTER_FLEET_DATA";


    /**
     * The intent action of Enter total amount <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_CURRENCY}  enum: {@link com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType} <br>
     * input: {@link EntryExtraData#PARAM_BASE_AMOUNT} <br>
     * request: {@link EntryRequest#PARAM_TOTAL_AMOUNT} <br>
     */
    public static final String ACTION_ENTER_TOTAL_AMOUNT = "com.pax.us.pay.action.ENTER_TOTAL_AMOUNT";

    /**
     * The intent action of Enter destination Zip Code <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_EINPUT_TYPE} enum: {@link com.pax.us.pay.ui.constant.entry.enumeration.InputType} <br>
     * input: {@link EntryExtraData#PARAM_MIN_LENGTH} <br>
     * input: {@link EntryExtraData#PARAM_MAX_LENGTH} <br>
     * request: {@link EntryRequest#PARAM_DEST_ZIP_CODE} <br>
     */
    public static final String ACTION_ENTER_DEST_ZIPCODE = "com.pax.us.pay.action.ENTER_DEST_ZIP_CODE";

}
