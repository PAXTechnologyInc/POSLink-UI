package com.pax.pay.ui.constant;


public class EUIMessageKey {
    //common
    public static final String INTENT_KEY_MESSAGE = "message";
    public static final String KEY_SENDER_PACKAGE = "senderPackage";
    public static final String INTENT_KEY_OPTIONS = "options";

    public static final String INTENT_KEY_AMOUNT = "amount";
    public static final String INTENT_KEY_TIP = "tip";
    public static final String INTENT_KEY_ZIP_CODE = "zipCode";
    public static final String INTENT_KEY_TRANS_NO = "transNo";
    public static final String INTENT_KEY_EXPIRE_DATE = "expireDate";
    public static final String INTENT_KEY_ADDRESS = "address";
    public static final String INTENT_KEY_SIGNATURE = "signature";
    public static final String INTENT_KEY_VOUCHER_NO = "voucherNo";
    public static final String INTENT_KEY_AUTH_CODE = "authCode";
    public static final String INTENT_KEY_HEALTH_CARE_AMOUNT = "healthCareAmount";
    public static final String INTENT_KEY_CLINIC_AMOUNT = "clinicAmount";
    public static final String INTENT_KEY_PRESCRIPTION_AMOUNT = "prescriptionAmount";
    public static final String INTENT_KEY_VISION_AMOUNT = "visionAmount";
    public static final String INTENT_KEY_DENTAL_AMOUNT = "dentalAmount";
    public static final String INTENT_KEY_TRANSIT_AMOUNT = "transitAmount";

    public static final String INTENT_KEY_VALUE = "value";
    public static final String INTENT_KEY_INDEX = "index";
    //input account
    public static final String INTENT_KEY_ENABLE_SWIPE = "enableSwipe";
    public static final String INTENT_KEY_ENABLE_INSERT = "enableInsert";
    public static final String INTENT_KEY_ENABLE_TAP = "enableTap";
    public static final String INTENT_KEY_ENABLE_MANUAL = "enableManualEntry";

    //secure area
    public static final String INTENT_KEY_X = "x";
    public static final String INTENT_KEY_Y = "y";
    public static final String INTENT_KEY_WIDTH = "width";
    public static final String INTENT_KEY_HEIGHT = "height";


    public interface IntentKey {
        String RESULT_CODE = "resultCode";
        String RESULT_MSG = "resultMessage";
        String CURRENCY = "currency";
        String MINLENGTH = "minLength";
        String MAXLENGTH = "maxLength";
    }
}
