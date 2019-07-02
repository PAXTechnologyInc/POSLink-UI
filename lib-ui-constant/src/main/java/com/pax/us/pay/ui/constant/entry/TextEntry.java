package com.pax.us.pay.ui.constant.entry;

public class TextEntry {
    public static final String CATEGORY = "com.pax.us.pay.ui.category.TEXT";
    /**
     * The intent action of Enter Amount
     * input: {@link EntryExtraData#PARAM_PACKAGE}
     * input: {@link EntryExtraData#PARAM_MESSAGE}
     * input: {@link EntryExtraData#PARAM_CURRENCY}
     * request: {@link EntryRequest#PARAM_AMOUNT}
     */
    public static final String ACTION_ENTER_AMOUNT = "com.pax.us.pay.action.ENTER_AMOUNT";
    /**
     * The intent action of Enter Tip
     * input: {@link EntryExtraData#PARAM_PACKAGE}
     * input: {@link EntryExtraData#PARAM_MESSAGE}
     * input: {@link EntryExtraData#PARAM_CURRENCY}
     * request: {@link EntryRequest#PARAM_TIP}
     */
    public static final String ACTION_ENTER_TIP = "com.pax.us.pay.action.ENTER_TIP";

    /**
     * The intent action of Enter Trans.No
     * input: {@link EntryExtraData#PARAM_PACKAGE}
     * input: {@link EntryExtraData#PARAM_MESSAGE}
     * request: {@link EntryRequest#PARAM_TRANS_NUMBER}
     */
    public static final String ACTION_ENTER_TRANS_NUMBER = "com.pax.us.pay.action.ENTER_TRANS_NUMBER";

    /**
     * The intent action of Enter Exp date
     * input: {@link EntryExtraData#PARAM_PACKAGE}
     * input: {@link EntryExtraData#PARAM_MESSAGE}
     * request: {@link EntryRequest#PARAM_EXPIRY_DATE}
     */
    public static final String ACTION_ENTER_EXPIRY_DATE = "com.pax.us.pay.action.ENTER_EXPIRY_DATE";

    /**
     * The intent action of Enter Address
     * input: {@link EntryExtraData#PARAM_PACKAGE}
     * input: {@link EntryExtraData#PARAM_MESSAGE}
     * request: {@link EntryRequest#PARAM_ADDRESS}
     */
    public static final String ACTION_ENTER_ADDRESS = "com.pax.us.pay.action.ENTER_ADDRESS";

    /**
     * The intent action of Enter Zip Code
     * input: {@link EntryExtraData#PARAM_PACKAGE}
     * input: {@link EntryExtraData#PARAM_MESSAGE}
     * request: {@link EntryRequest#PARAM_ZIP_CODE}
     */
    public static final String ACTION_ENTER_ZIPCODE = "com.pax.us.pay.action.ENTER_ZIP_CODE";

    /**
     * The intent action of Enter AuthCode
     * input: {@link EntryExtraData#PARAM_PACKAGE}
     * input: {@link EntryExtraData#PARAM_MESSAGE}
     * request: {@link EntryRequest#PARAM_AUTH_CODE}
     */
    public static final String ACTION_ENTER_AUTH = "com.pax.us.pay.action.ENTER_AUTH_CODE";

    /**
     * The intent action of Enter FSA Amount
     * input: {@link EntryExtraData#PARAM_PACKAGE}
     * input: {@link EntryExtraData#PARAM_MESSAGE}
     * request: {@link EntryRequest#PARAM_HEALTH_CARE_AMOUNT}
     * request: {@link EntryRequest#PARAM_CLINIC_AMOUNT}
     * request: {@link EntryRequest#PARAM_PRESCRIPTION_AMOUNT}
     * request: {@link EntryRequest#PARAM_VISION_AMOUNT}
     * request: {@link EntryRequest#PARAM_DENTAL_AMOUNT}
     * request: {@link EntryRequest#PARAM_TRANSIT_AMOUNT}
     */
    public static final String ACTION_ENTER_FSA_AMOUNT = "com.pax.us.pay.action.ENTER_FSA_AMOUNT";


    /**
     * The intent action of Enter voucher data
     * input: {@link EntryExtraData#PARAM_PACKAGE}
     * input: {@link EntryExtraData#PARAM_MESSAGE}
     * request: {@link EntryRequest#PARAM_VOUCHER_NUMBER}
     * request: {@link EntryRequest#PARAM_AUTH_CODE}
     */
    public static final String ACTION_ENTER_VOUCHER_DATA = "com.pax.us.pay.action.ENTER_VOUCHER_DATA";

    /**
     * The intent action of Enter AVS data
     * input: {@link EntryExtraData#PARAM_PACKAGE}
     * input: {@link EntryExtraData#PARAM_MESSAGE}
     * request: {@link EntryRequest#PARAM_ADDRESS}
     * request: {@link EntryRequest#PARAM_ZIP_CODE}
     */
    public static final String ACTION_ENTER_AVS_DATA = "com.pax.us.pay.action.ENTER_AVS_DATA";

    /**
     * The intent action of Enter reference number
     * input: {@link EntryExtraData#PARAM_PACKAGE}
     * input: {@link EntryExtraData#PARAM_MESSAGE}
     * request: {@link EntryRequest#PARAM_REFERENCE_NUMBER}
     */
    public static final String ACTION_ENTER_REFERENCE_NUMBER = "com.pax.us.pay.action.ENTER_REFERENCE_NUMBER";


    /**
     * The intent action of Enter invoice number
     * input: {@link EntryExtraData#PARAM_PACKAGE}
     * input: {@link EntryExtraData#PARAM_MESSAGE}
     * request: {@link EntryRequest#PARAM_INVOICE_NUMBER}
     */
    public static final String ACTION_ENTER_INVOICE_NUMBER = "com.pax.us.pay.action.ENTER_INVOICE_NUMBER";

    /**
     * The intent action of Enter clerk id
     * input: {@link EntryExtraData#PARAM_PACKAGE}
     * input: {@link EntryExtraData#PARAM_MESSAGE}
     * request: {@link EntryRequest#PARAM_CLERK_ID}
     */
    public static final String ACTION_ENTER_CLERK_ID = "com.pax.us.pay.action.ENTER_CLERK_ID";


    /**
     * The intent action of Enter cash back
     * input: {@link EntryExtraData#PARAM_PACKAGE}
     * input: {@link EntryExtraData#PARAM_MESSAGE}
     * request: {@link EntryRequest#PARAM_CASHBACK_AMOUNT}
     */
    public static final String ACTION_ENTER_CASH_BACK = "com.pax.us.pay.action.ENTER_CASH_BACK";


}
