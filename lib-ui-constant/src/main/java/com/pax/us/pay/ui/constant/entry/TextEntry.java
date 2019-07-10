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
     * input: {@link EntryExtraData#PARAM_TOTAL_AMOUNT} <br>
     * input: {@link EntryExtraData#PARAM_TIP_OPTIONS} String Array, Nullable amount array <br>
     * request: {@link EntryRequest#PARAM_TIP} <br>
     */
    public static final String ACTION_ENTER_TIP = "com.pax.us.pay.action.ENTER_TIP";

    /**
     * The intent action of Enter Trans.No <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
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
     * request: {@link EntryRequest#PARAM_REFERENCE_NUMBER} <br>
     */
    public static final String ACTION_ENTER_REFERENCE_NUMBER = "com.pax.us.pay.action.ENTER_REFERENCE_NUMBER";


    /**
     * The intent action of Enter invoice number <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
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
     * request: {@link EntryRequest#PARAM_CASHBACK_AMOUNT} long  <br>
     */
    public static final String ACTION_ENTER_CASH_BACK = "com.pax.us.pay.action.ENTER_CASH_BACK";


}
