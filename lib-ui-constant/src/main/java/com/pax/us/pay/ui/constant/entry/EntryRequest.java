package com.pax.us.pay.ui.constant.entry;

public class EntryRequest {
    /**
     * NEXT with value: request BroadPOS to go to next step with input value.
     */
    public static final String ACTION_NEXT = "com.pax.us.pay.ui.NEXT";

    /**
     * ABORT: request BroadPOS to abort the transaction.
     */
    public static final String ACTION_ABORT = "com.pax.us.pay.ui.ABORT";

    /**
     * PREV: request BroadPOS to go to previous step .
     */
    public static final String ACTION_PREV = "com.pax.us.pay.ui.PREV";

    /**
     * SECURITY_AREA: request BroadPOS to show security widget on a specific position.
     * param: {@link EntryRequest#PARAM_X}
     * param: {@link EntryRequest#PARAM_Y}
     * param: {@link EntryRequest#PARAM_WIDTH}
     * param: {@link EntryRequest#PARAM_HEIGHT}
     */
    public static final String ACTION_SECURITY_AREA = "com.pax.us.pay.ui.SECURITY_AREA";

    public static final String PARAM_AMOUNT = "amount";
    public static final String PARAM_TIP = "tip";
    public static final String PARAM_ZIP_CODE = "zipCode";
    public static final String PARAM_TRANS_NUMBER = "transNumber";
    public static final String PARAM_EXPIRY_DATE = "expiryDate";
    public static final String PARAM_ADDRESS = "address";
    public static final String PARAM_SIGNATURE = "signature";
    public static final String PARAM_VOUCHER_NUMBER = "voucherNumber";
    public static final String PARAM_AUTH_CODE = "authCode";

    public static final String PARAM_HEALTH_CARE_AMOUNT = "healthCareAmount";
    public static final String PARAM_CLINIC_AMOUNT = "clinicAmount";
    public static final String PARAM_PRESCRIPTION_AMOUNT = "prescriptionAmount";
    public static final String PARAM_VISION_AMOUNT = "visionAmount";
    public static final String PARAM_DENTAL_AMOUNT = "dentalAmount";
    public static final String PARAM_COPAY_AMOUNT = "copayAmount";
    public static final String PARAM_TRANSIT_AMOUNT = "transitAmount";

    public static final String PARAM_CASHBACK_AMOUNT = "cashbackAmount";
    public static final String PARAM_REFERENCE_NUMBER = "referenceNumber";
    public static final String PARAM_INVOICE_NUMBER = "invoiceNumber";
    public static final String PARAM_CLERK_ID = "clerkId";

    public static final String PARAM_VALUE = "value";

    public static final String PARAM_INDEX = "index";

    public static final String PARAM_X = "x";
    public static final String PARAM_Y = "y";
    public static final String PARAM_WIDTH = "width";
    public static final String PARAM_HEIGHT = "height";
}
