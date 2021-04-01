package com.pax.us.pay.ui.constant.entry;

public class EntryRequest {

    /**
     * NEXT with value: request BroadPOS to go to next step with input value. <br>
     */
    public static final String ACTION_NEXT = "com.pax.us.pay.ui.NEXT";

    /**
     * ABORT: request BroadPOS to abort the transaction. <br>
     */
    public static final String ACTION_ABORT = "com.pax.us.pay.ui.ABORT";

    /**
     * ABORT: request BroadPOS to abort the transaction. <br>
     */
    public static final String ACTION_TIME_OUT = "com.pax.us.pay.ui.TIME_OUT";


    /**
     * PREV: request BroadPOS to go to previous step . <br>
     */
    public static final String ACTION_PREV = "com.pax.us.pay.ui.PREV";

    /**
     * SECURITY_AREA: request BroadPOS to show security widget on a specific position. <br>
     * param: {@link EntryRequest#PARAM_X} <br>
     * param: {@link EntryRequest#PARAM_Y} <br>
     * param: {@link EntryRequest#PARAM_WIDTH} <br>
     * param: {@link EntryRequest#PARAM_HEIGHT} <br>
     */
    public static final String ACTION_SECURITY_AREA = "com.pax.us.pay.ui.SECURITY_AREA";

    /**
     * PIN_LAYOUT: optional broadcast, for {@link com.pax.us.pay.ui.constant.entry.SecurityEntry#ACTION_ENTER_PIN} only <br>
     * need to be sent before {@link EntryRequest#ACTION_SECURITY_AREA} <br>
     * param: {@link EntryRequest#PARAM_KEY_0} <br>
     * param: {@link EntryRequest#PARAM_KEY_1} <br>
     * param: {@link EntryRequest#PARAM_KEY_2} <br>
     * param: {@link EntryRequest#PARAM_KEY_3} <br>
     * param: {@link EntryRequest#PARAM_KEY_4} <br>
     * param: {@link EntryRequest#PARAM_KEY_5} <br>
     * param: {@link EntryRequest#PARAM_KEY_6} <br>
     * param: {@link EntryRequest#PARAM_KEY_7} <br>
     * param: {@link EntryRequest#PARAM_KEY_8} <br>
     * param: {@link EntryRequest#PARAM_KEY_9} <br>
     * param: {@link EntryRequest#PARAM_KEY_CLEAR} <br>
     * param: {@link EntryRequest#PARAM_KEY_ENTER} <br>
     * param: {@link EntryRequest#PARAM_KEY_CANCEL} <br>
     */
    public static final String ACTION_SET_PIN_KEY_LAYOUT = "com.pax.us.pay.ui.SET_PIN_LAYOUT";

    /**
     * START_SCAN: optional, for {@link com.pax.us.pay.ui.constant.entry.SecurityEntry#ACTION_INPUT_ACCOUNT} <br>
     */
    public static final String ACTION_START_SCAN = "com.pax.us.pay.ui.START_SCAN";

    /**
     * PARAM_ACTION: request parameter current action for BroadPOS <br>
     * value type: String <br>
     */
    public static final String PARAM_ACTION = "action";

    /**
     * PARAM_AMOUNT : request parameter amount <br>
     * value type: long <br>
     */
    public static final String PARAM_AMOUNT = "amount";

    /**
     * PARAM_TOTAL_AMOUNT : request parameter total amount <br>
     * value type: long <br>
     */
    public static final String PARAM_TOTAL_AMOUNT = "totalAmount";
    /**
     * PARAM_TIP : request parameter tip amount <br>
     * value type: long <br>
     */
    public static final String PARAM_TIP = "tip";
    /**
     * PARAM_ZIP_CODE : request parameter zip code <br>
     * value type: String <br>
     */
    public static final String PARAM_ZIP_CODE = "zipCode";
    /**
     * PARAM_TRANS_NUMBER : request parameter transaction number <br>
     * value type: long <br>
     */
    public static final String PARAM_TRANS_NUMBER = "transNumber";
    /**
     * PARAM_EXPIRY_DATE : request parameter card expiry date <br>
     * value type: String <br>
     * format: MMYY <br>
     */
    public static final String PARAM_EXPIRY_DATE = "expiryDate";
    /**
     * PARAM_ORIG_DATE : request parameter original transaction date <br>
     * value type: String <br>
     * format:   <br>   ?????????????????
     */
    public static final String PARAM_ORIG_DATE = "origTransDate";
    /**
     * PARAM_ADDRESS : request parameter address <br>
     * value type: String <br>
     */
    public static final String PARAM_ADDRESS = "address";
    /**
     * PARAM_SIGNATURE : request parameter trace data of signature <br>
     * value type: short[] <br>
     */
    public static final String PARAM_SIGNATURE = "signature";
    public static final String PARAM_VOUCHER_NUMBER = "voucherNumber";
    public static final String PARAM_AUTH_CODE = "authCode";

    public static final String PARAM_HEALTH_CARE_AMOUNT = "healthCareAmount";
    public static final String PARAM_CLINIC_AMOUNT = "clinicAmount";
    public static final String PARAM_PRESCRIPTION_AMOUNT = "prescriptionAmount";
    public static final String PARAM_VISION_AMOUNT = "visionAmount";
    public static final String PARAM_DENTAL_AMOUNT = "dentalAmount";
    public static final String PARAM_COPAY_AMOUNT = "copayAmount";
    public static final String PARAM_OTC_AMOUNT = "otcAmount";
    public static final String PARAM_TRANSIT_AMOUNT = "transitAmount";
    public static final String PARAM_FSA_OPTION = "fsaOption";

    public static final String PARAM_CASHBACK_AMOUNT = "cashbackAmount";
    public static final String PARAM_FUEL_AMOUNT = "fuelAmount";
    public static final String PARAM_TAX_AMOUNT = "taxAmount";

    public static final String PARAM_MERCHANT_REFERENCE_NUMBER = "merchantReferenceNumber";
    public static final String PARAM_REFERENCE_NUMBER = "referenceNumber";
    public static final String PARAM_INVOICE_NUMBER = "invoiceNumber";
    public static final String PARAM_CLERK_ID = "clerkId";
    public static final String PARAM_SERVER_ID = "serverId";
    public static final String PARAM_TABLE_NUMBER = "tableNumber";
    public static final String PARAM_PHONE_NUMBER = "phoneNumber";
    public static final String PARAM_GUEST_NUMBER = "guestNumber";
    public static final String PARAM_ORDER_NUMBER = "orderNumber";
    public static final String PARAM_PO_NUMBER = "PONumber";
    public static final String PARAM_PROC_DESC = "prodDesc";
    public static final String PARAM_MERCHANT_TAX_ID = "merchantTaxID";
    public static final String PARAM_CS_PHONE_NUMBER = "customerServicePhoneNumber";
    public static final String PARAM_OCT_REFERENCE_NUMBER = "octReferenceNumber";

    public static final String PARAM_CUSTOMER_CODE = "customerCode";
    public static final String PARAM_PROMPT_RESTRICTION_CODE = "promptRestrictionCode";

    public static final String PARAM_FLEET_CUSTOMER_DATA = "fleetCustomerData";
    public static final String PARAM_FLEET_DEPARTMENT_NUMBER = "fleetDepartmentNumber";
    public static final String PARAM_FLEET_USER_ID = "fleetUserId";
    public static final String PARAM_FLEET_VEHICLE_ID = "fleetVehicleId";
    public static final String PARAM_FLEET_VEHICLE_NUMBER = "fleetVehicleNumber";
    public static final String PARAM_FLEET_JOB_NUMBER = "fleetJobNumber";
    public static final String PARAM_FLEET_ODOMETER = "fleetOdometer";
    public static final String PARAM_FLEET_DRIVER_ID = "fleetDriverId";
    public static final String PARAM_FLEET_LICENSE_NUMBER = "fleetLicenseNumber";

    /**
     * PARAM_DEST_ZIP_CODE : request parameter destination zip code <br>
     * value type: String <br>
     */
    public static final String PARAM_DEST_ZIP_CODE = "destinationZipCode";

    /**
     * PARAM_DO_NOT_PROMPT_AGAIN : request parameter prompt again <br>
     * value type: Boolean <br>
     */
    public static final String PARAM_DO_NOT_PROMPT_AGAIN = "doNotPromptAgain";

    //POSLINK 
    public static final String PARAM_INPUT_VALUE = "inputValue";
    public static final String PARAM_LABEL_SELECTED = "labelSelected";

    public static final String PARAM_VALUE = "value";

    public static final String PARAM_INDEX = "index";
    public static final String PARAM_CONFIRMED = "confirmed";

    public static final String PARAM_X = "x";
    public static final String PARAM_Y = "y";
    public static final String PARAM_WIDTH = "width";
    public static final String PARAM_HEIGHT = "height";
    public static final String PARAM_FONT_SIZE = "fontSize";

    /**
     * Type {@link android.graphics.Rect}
     */
    public static final String PARAM_KEY_0 = "key_0";
    /**
     * Type {@link android.graphics.Rect}
     */
    public static final String PARAM_KEY_1 = "key_1";
    /**
     * Type {@link android.graphics.Rect}
     */
    public static final String PARAM_KEY_2 = "key_2";
    /**
     * Type {@link android.graphics.Rect}
     */
    public static final String PARAM_KEY_3 = "key_3";
    /**
     * Type {@link android.graphics.Rect}
     */
    public static final String PARAM_KEY_4 = "key_4";
    /**
     * Type {@link android.graphics.Rect}
     */
    public static final String PARAM_KEY_5 = "key_5";
    /**
     * Type {@link android.graphics.Rect}
     */
    public static final String PARAM_KEY_6 = "key_6";
    /**
     * Type {@link android.graphics.Rect}
     */
    public static final String PARAM_KEY_7 = "key_7";
    /**
     * Type {@link android.graphics.Rect}
     */
    public static final String PARAM_KEY_8 = "key_8";
    /**
     * Type {@link android.graphics.Rect}
     */
    public static final String PARAM_KEY_9 = "key_9";
    /**
     * Type {@link android.graphics.Rect}
     */
    public static final String PARAM_KEY_CLEAR = "key_clear";
    /**
     * Type {@link android.graphics.Rect}
     */
    public static final String PARAM_KEY_ENTER = "key_enter";
    /**
     * Type {@link android.graphics.Rect}
     */
    public static final String PARAM_KEY_CANCEL = "key_cancel";

    //Aded for Visa Installment
    public static final String INSTALLMEN_SELECT_OPTION = "installment_select_option";
}
