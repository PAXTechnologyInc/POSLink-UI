package com.pax.us.pay.ui.constant.entry;

import com.pax.us.annotation.Field;

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
    @Field(type = String.class)
    public static final String PARAM_ACTION = "action";

    /**
     * PARAM_AMOUNT : request parameter amount <br>
     * value type: long <br>
     */
    @Field(type = Long.class)
    public static final String PARAM_AMOUNT = "amount";

    /**
     * PARAM_TOTAL_AMOUNT : request parameter total amount <br>
     * value type: long <br>
     */
    @Field(type = Long.class)
    public static final String PARAM_TOTAL_AMOUNT = "totalAmount";
    /**
     * PARAM_TIP : request parameter tip amount <br>
     * value type: long <br>
     */
    @Field(type = Long.class)
    public static final String PARAM_TIP = "tip";
    /**
     * PARAM_ZIP_CODE : request parameter zip code <br>
     * value type: String <br>
     */
    @Field(type = String.class)
    public static final String PARAM_ZIP_CODE = "zipCode";
    /**
     * PARAM_TRANS_NUMBER : request parameter transaction number <br>
     * value type: long <br>
     */
    @Field(type = Long.class)
    public static final String PARAM_TRANS_NUMBER = "transNumber";
    /**
     * PARAM_EXPIRY_DATE : request parameter card expiry date <br>
     * value type: String <br>
     * format: MMYY <br>
     */
    @Field(type = String.class)
    public static final String PARAM_EXPIRY_DATE = "expiryDate";
    /**
     * PARAM_ORIG_DATE : request parameter original transaction date <br>
     * value type: String <br>
     * format:   <br>   ?????????????????
     */
    @Field(type = String.class)
    public static final String PARAM_ORIG_DATE = "origTransDate";
    /**
     * PARAM_ADDRESS : request parameter address <br>
     * value type: String <br>
     */
    @Field(type = String.class)
    public static final String PARAM_ADDRESS = "address";
    /**
     * PARAM_SIGNATURE : request parameter trace data of signature <br>
     * value type: short[] <br>
     */
    public static final String PARAM_SIGNATURE = "signature";
    @Field(type = String.class)
    public static final String PARAM_VOUCHER_NUMBER = "voucherNumber";
    @Field(type = String.class)
    public static final String PARAM_AUTH_CODE = "authCode";
    @Field(type = Long.class)
    public static final String PARAM_HEALTH_CARE_AMOUNT = "healthCareAmount";
    @Field(type = Long.class)
    public static final String PARAM_CLINIC_AMOUNT = "clinicAmount";
    @Field(type = Long.class)
    public static final String PARAM_PRESCRIPTION_AMOUNT = "prescriptionAmount";
    @Field(type = Long.class)
    public static final String PARAM_VISION_AMOUNT = "visionAmount";
    @Field(type = Long.class)
    public static final String PARAM_DENTAL_AMOUNT = "dentalAmount";
    @Field(type = Long.class)
    public static final String PARAM_COPAY_AMOUNT = "copayAmount";
    @Field(type = Long.class)
    public static final String PARAM_OTC_AMOUNT = "otcAmount";
    @Field(type = Long.class)
    public static final String PARAM_TRANSIT_AMOUNT = "transitAmount";
    /**
     * @see com.pax.us.pay.ui.constant.entry.enumeration.FSAType
     */
    @Field(type = String.class)
    public static final String PARAM_FSA_OPTION = "fsaOption";
    @Field(type = Long.class)
    public static final String PARAM_CASHBACK_AMOUNT = "cashbackAmount";
    @Field(type = Long.class)
    public static final String PARAM_FUEL_AMOUNT = "fuelAmount";
    @Field(type = long.class)
    public static final String PARAM_TAX_AMOUNT = "taxAmount";
    @Field(type = String.class)
    public static final String PARAM_MERCHANT_REFERENCE_NUMBER = "merchantReferenceNumber";
    @Field(type = String.class)
    public static final String PARAM_REFERENCE_NUMBER = "referenceNumber";
    @Field(type = String.class)
    public static final String PARAM_INVOICE_NUMBER = "invoiceNumber";
    @Field(type = String.class)
    public static final String PARAM_CLERK_ID = "clerkId";
    @Field(type = String.class)
    public static final String PARAM_SERVER_ID = "serverId";
    @Field(type = String.class)
    public static final String PARAM_TABLE_NUMBER = "tableNumber";
    @Field(type = String.class)
    public static final String PARAM_PHONE_NUMBER = "phoneNumber";
    @Field(type = String.class)
    public static final String PARAM_GUEST_NUMBER = "guestNumber";
    @Field(type = String.class)
    public static final String PARAM_ORDER_NUMBER = "orderNumber";
    @Field(type = String.class)
    public static final String PARAM_PO_NUMBER = "PONumber";
    @Field(type = String.class)
    public static final String PARAM_PROC_DESC = "prodDesc";
    @Field(type = String.class)
    public static final String PARAM_MERCHANT_TAX_ID = "merchantTaxID";
    public static final String PARAM_CS_PHONE_NUMBER = "customerServicePhoneNumber";
    @Field(type = String.class)
    public static final String PARAM_OCT_REFERENCE_NUMBER = "octReferenceNumber";
    @Field(type = String.class)
    public static final String PARAM_CUSTOMER_CODE = "customerCode";
    @Field(type = String.class)
    public static final String PARAM_PROMPT_RESTRICTION_CODE = "promptRestrictionCode";
    
    @Field(type = String.class)
    public static final String PARAM_FLEET_CUSTOMER_DATA = "fleetCustomerData";
    @Field(type = String.class)
    public static final String PARAM_FLEET_DEPARTMENT_NUMBER = "fleetDepartmentNumber";
    @Field(type = String.class)
    public static final String PARAM_FLEET_USER_ID = "fleetUserId";
    @Field(type = String.class)
    public static final String PARAM_FLEET_VEHICLE_ID = "fleetVehicleId";
    @Field(type = String.class)
    public static final String PARAM_FLEET_VEHICLE_NUMBER = "fleetVehicleNumber";
    @Field(type = String.class)
    public static final String PARAM_FLEET_JOB_NUMBER = "fleetJobNumber";
    @Field(type = String.class)
    public static final String PARAM_FLEET_ODOMETER = "fleetOdometer";
    @Field(type = String.class)
    public static final String PARAM_FLEET_DRIVER_ID = "fleetDriverId";
    @Field(type = String.class)
    public static final String PARAM_FLEET_LICENSE_NUMBER = "fleetLicenseNumber";

    /**
     * PARAM_DEST_ZIP_CODE : request parameter destination zip code <br>
     * value type: String <br>
     */
    @Field(type = String.class)
    public static final String PARAM_DEST_ZIP_CODE = "destinationZipCode";

    /**
     * PARAM_DO_NOT_PROMPT_AGAIN : request parameter prompt again <br>
     * value type: Boolean <br>
     */
    @Field(type = boolean.class)
    public static final String PARAM_DO_NOT_PROMPT_AGAIN = "doNotPromptAgain";

    //POSLINK
    @Field(type = String.class)
    public static final String PARAM_INPUT_VALUE = "inputValue";
    /**
     * FIXME String but need to be int array
     */
    @Field(type = String.class)
    public static final String PARAM_LABEL_SELECTED = "labelSelected";
    /**
     * FIXME String but need to be long
     */
    @Field(type = String.class)
    public static final String PARAM_BUTTON_NUMBER = "buttonNumber";
    @Field(type = String.class)
    public static final String PARAM_SIGN_STATUS = "signStatus";

    public static final String PARAM_VALUE = "value";
    @Field(type = int.class)
    public static final String PARAM_INDEX = "index";
    @Field(type = boolean.class)
    public static final String PARAM_CONFIRMED = "confirmed";
    public static final String PARAM_X = "x";
    public static final String PARAM_Y = "y";
    public static final String PARAM_WIDTH = "width";
    public static final String PARAM_HEIGHT = "height";
    public static final String PARAM_FONT_SIZE = "fontSize";

    //Visa Installment
    @Deprecated
    public static final String PARAM_INSTALLMENT_SELECT_OPTION = "installmentSelectOption";
    @Field(type = String.class)
    public static final String PARAM_VISA_TRANSID = "visaTransID";
    @Field(type = String.class)
    public static final String PARAM_VISA_PLAN_ACCEPTANCE_ID = "visaPlanAcceptanceID";

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
}
