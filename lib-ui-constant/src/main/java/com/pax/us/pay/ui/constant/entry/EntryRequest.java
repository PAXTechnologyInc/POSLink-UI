package com.pax.us.pay.ui.constant.entry;

/**
 * define Broadcast which sent to BroadPOS
 */
public final class EntryRequest {
    private EntryRequest(){

    }

    /**
     * Broadcast Action: NEXT<br>{@value #ACTION_NEXT}<br>
     * <p>
     *     For a entry (Activity Action), when you get the required OUTPUT ready,<br>
     *     you need send a broadcast to BroadPOS (the caller),<br>
     *     Example:<br>
     *     BroadPOS start the activity {@link TextEntry#ACTION_ENTER_AMOUNT} which implemented in your app,<br>
     *     after input done, you send a broadcast like this:
     * </p>
     * <pre>
     *     Bundle bundle = new Bundle();
     *     bundle.putLong(EntryRequest.PARAM_AMOUNT,amount);
     *     bundle.putStringExtra(EntryRequest.PARAM_ACTION,TextEntry.ACTION_ENTER_AMOUNT);
     *     Intent intent = new Intent();
     *     intent.setPackage(broadPOSPackage); //broadPOSPackage is from {@link EntryExtraData#PARAM_PACKAGE}
     *     intent.setAction(EntryRequest.ACTION_NEXT);
     *     intent.putExtras(bundle);
     *     context.sendBroadcast(intent);
     *</pre>
     * <p>
     *     For some actions which have no required outputs,
     *     like {@link ConfirmationEntry#ACTION_DISPLAY_QR_CODE_RECEIPT},
     *     you can send NEXT by this way:
     * </p>
     * <pre>
     *     Intent intent = new Intent();
     *     intent.setPackage(broadPOSPackage); //broadPOSPackage is from {@link EntryExtraData#PARAM_PACKAGE}
     *     intent.setAction(EntryRequest.ACTION_NEXT);
     *     context.sendBroadcast(intent);
     * </pre>
     */
    public static final String ACTION_NEXT = "com.pax.us.pay.ui.NEXT";

    /**
     * Broadcast Action: ABORT<br>{@value #ACTION_ABORT}<br>
     * <p>
     *     When you try to abort the transaction, you can send broadcast to BroadPOS. <br>
     *     Example: try to abort action {@link TextEntry#ACTION_ENTER_AMOUNT}:
     * </p>
     * <pre>
     *     Bundle bundle = new Bundle();
     *     bundle.putStringExtra(EntryRequest.PARAM_ACTION,TextEntry.ACTION_ENTER_AMOUNT);
     *     Intent intent = new Intent();
     *     intent.setPackage(broadPOSPackage); //broadPOSPackage is from {@link EntryExtraData#PARAM_PACKAGE}
     *     intent.setAction(EntryRequest.ACTION_ABORT);
     *     intent.putExtras(bundle);
     *     context.sendBroadcast(intent);
     *</pre>
     */
    public static final String ACTION_ABORT = "com.pax.us.pay.ui.ABORT";

    /**
     * Broadcast Action: TIMEOUT<br>{@value #ACTION_TIME_OUT}<br>
     * <p>
     *     When TIMEOUT, you can send broadcast to BroadPOS. <br>
     *     Example for action {@link SignatureEntry#ACTION_SIGNATURE}:
     * </p>
     * <pre>
     *     Bundle bundle = new Bundle();
     *     bundle.putStringExtra(EntryRequest.PARAM_ACTION,SignatureEntry.ACTION_SIGNATURE);
     *     Intent intent = new Intent();
     *     intent.putExtras(bundle);
     *     intent.setPackage(broadPOSPackage); //broadPOSPackage is from {@link EntryExtraData#PARAM_PACKAGE}
     *     intent.setAction(EntryRequest.ACTION_TIME_OUT);
     *     context.sendBroadcast(intent);
     *</pre>
     */
    public static final String ACTION_TIME_OUT = "com.pax.us.pay.ui.TIME_OUT";


    /**
     * Broadcast Action: PREV <br>{@value #ACTION_PREV}<br>
     * request BroadPOS to go to previous step . <br>
     * @deprecated Never used. Now BroadPOS can not go to previous step during transaction.
     */
    public static final String ACTION_PREV = "com.pax.us.pay.ui.PREV";

    /**
     * Broadcast Action: Set Secure Area<br>{@value #ACTION_SECURITY_AREA}<br>
     * <p>
     *     For {@link SecurityEntry}, you need tell BroadPOS about your input box after UI layout is ready.<br>
     *     Then BroadPOS could show security widget on specific position.<br>
     *     general you can do it on windows focused or on global layout done.
     * </p>
     * Example Code:
     * <pre>
     *     Bundle bundle = new Bundle();
     *     bundle.putInt(EntryRequest.PARAM_X, x);
     *     bundle.putInt(EntryRequest.PARAM_Y, y);
     *     bundle.putInt(EntryRequest.PARAM_WIDTH, width);
     *     bundle.putInt(EntryRequest.PARAM_HEIGHT, height);
     *     bundle.putInt(EntryRequest.PARAM_FONT_SIZE, fontSize);
     *     bundle.putString(EntryRequest.PARAM_FOCUSABLE, focusAble);
     *     bundle.putString(EntryRequest.PARAM_HINT, hint);
     *     bundle.putString(EntryRequest.PARAM_COLOR, fontColor);
     *     Intent intent = new Intent();
     *     intent.setPackage(broadPOSPackage); //broadPOSPackage is from {@link EntryExtraData#PARAM_PACKAGE}
     *     intent.putExtras(bundle);
     *     intent.setAction(EntryRequest.ACTION_SECURITY_AREA);
     *     context.sendBroadcast(intent);
     * </pre>
     * <p>
     *     ExtraData: {@link EntryRequest#PARAM_X} - {@value EntryRequest#PARAM_X} is X coordinate of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     ExtraData: {@link EntryRequest#PARAM_Y} - {@value EntryRequest#PARAM_Y} is Y coordinate of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     ExtraData: {@link EntryRequest#PARAM_WIDTH} - {@value EntryRequest#PARAM_WIDTH} is width of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     ExtraData: {@link EntryRequest#PARAM_HEIGHT} - {@value EntryRequest#PARAM_HEIGHT} is height of input box<br>
     *     Type: Integer<br>
     *     Unit: pixel
     * </p>
     * <p>
     *     ExtraData: {@link EntryRequest#PARAM_FONT_SIZE} - {@value EntryRequest#PARAM_FONT_SIZE} is font size of input box <br>
     *     Type: Integer<br>
     *     Unit: scaled pixel
     * </p>
     * <p>
     *     ExtraData: {@link EntryRequest#PARAM_FOCUSABLE} - {@value EntryRequest#PARAM_FOCUSABLE} Optional <br>
     *     Type: Boolean<br>
     *     TRUE is focusable.
     * </p>
     * <p>
     *     ExtraData: {@link EntryRequest#PARAM_HINT} - {@value EntryRequest#PARAM_HINT} Optional <br>
     *     Type: String
     * </p>
     * <p>
     *     ExtraData: {@link EntryRequest#PARAM_COLOR} - {@value EntryRequest#PARAM_COLOR} Optional<br>
     *     Type : String<br>
     *     Format: ARGB<br>
     *     Example: <br>
     *     "ffffffff" which represent the opaque white color.<br>
     *     "cc00ff00" which means the green color with 20% transparent.
     * </p>
     */
    public static final String ACTION_SECURITY_AREA = "com.pax.us.pay.ui.SECURITY_AREA";

    /**
     * Broadcast Action: Set Pin Pad Layout<br>{@value #ACTION_SET_PIN_KEY_LAYOUT}<br>
     * <p>
     *     For {@link com.pax.us.pay.ui.constant.entry.SecurityEntry#ACTION_ENTER_PIN} Only <br>
     *     Need to be sent before {@link EntryRequest#ACTION_SECURITY_AREA} <br>
     *     You can send this broadcast to BroadPOS if you prefer your PIN PAD layout.
     * </p>
     * Example Code:
     * <pre>
     *     Intent intent = new Intent();
     *     intent.setPackage(broadPOSPackage); //broadPOSPackage is from {@link EntryExtraData#PARAM_PACKAGE}
     *     intent.setAction(EntryRequest.ACTION_SET_PIN_KEY_LAYOUT);
     *     intent.putExtra(PARAM_KEY_0, new Rect(left0, top0, right0, bottom0); // location is from getLocationOnScreen
     *     ...
     *     ...
     *     intent.putExtra(PARAM_KEY_CANCEL, new Rect(leftCancel, topCancel, rightCancel, bottomCancel);
     *     context.sendBroadcast(intent);
     * </pre>
     * <p>
     *     ExtraData:  {@link EntryRequest#PARAM_KEY_0} - {@value EntryRequest#PARAM_KEY_0}<br>
     *     Type: android.graphics.Rect
     * </p>
     * <p>
     *     ExtraData:  {@link EntryRequest#PARAM_KEY_1} - {@value EntryRequest#PARAM_KEY_1}<br>
     *     Type: android.graphics.Rect
     * </p>
     * <p>
     *     ExtraData:  {@link EntryRequest#PARAM_KEY_2} - {@value EntryRequest#PARAM_KEY_2}<br>
     *     Type: android.graphics.Rect
     * </p>
     * <p>
     *     ExtraData:  {@link EntryRequest#PARAM_KEY_3} - {@value EntryRequest#PARAM_KEY_3}<br>
     *     Type: android.graphics.Rect
     * </p>
     * <p>
     *     ExtraData:  {@link EntryRequest#PARAM_KEY_4} - {@value EntryRequest#PARAM_KEY_4}<br>
     *     Type: android.graphics.Rect
     * </p>
     * <p>
     *     ExtraData:  {@link EntryRequest#PARAM_KEY_5} - {@value EntryRequest#PARAM_KEY_5}<br>
     *     Type: android.graphics.Rect
     * </p>
     * <p>
     *     ExtraData:  {@link EntryRequest#PARAM_KEY_6} - {@value EntryRequest#PARAM_KEY_6}<br>
     *     Type: android.graphics.Rect
     * </p>
     * <p>
     *     ExtraData:  {@link EntryRequest#PARAM_KEY_7} - {@value EntryRequest#PARAM_KEY_7}<br>
     *     Type: android.graphics.Rect
     * </p>
     * <p>
     *     ExtraData:  {@link EntryRequest#PARAM_KEY_8}<br>
     *     Type: android.graphics.Rect
     * </p>
     * <p>
     *     ExtraData:  {@link EntryRequest#PARAM_KEY_8} - {@value EntryRequest#PARAM_KEY_8}<br>
     *     Type: android.graphics.Rect
     * </p>
     * <p>
     *     ExtraData:  {@link EntryRequest#PARAM_KEY_CLEAR} - {@value EntryRequest#PARAM_KEY_CLEAR}<br>
     *     Type: android.graphics.Rect
     * </p>
     * <p>
     *     ExtraData:  {@link EntryRequest#PARAM_KEY_ENTER} - {@value EntryRequest#PARAM_KEY_ENTER}<br>
     *     Type: android.graphics.Rect
     * </p>
     * <p>
     *     ExtraData:  {@link EntryRequest#PARAM_KEY_CANCEL} - {@value EntryRequest#PARAM_KEY_CANCEL} <br>
     *     Type: android.graphics.Rect
     * </p>
     */
    public static final String ACTION_SET_PIN_KEY_LAYOUT = "com.pax.us.pay.ui.SET_PIN_LAYOUT";

    /**
     * Broadcast Action: Start QR Code Scan<br>{@value #ACTION_START_SCAN}<br>
     * <p>
     *     Used for {@link com.pax.us.pay.ui.constant.entry.SecurityEntry#ACTION_INPUT_ACCOUNT}<br>
     *     If user click the Start Scan button on your input account UI, you can tell BroadPOS to start scan.
     * </p>
     * Example Code:
     * <pre>
     *     Intent intent = new Intent(EntryRequest.ACTION_START_SCAN);
     *     intent.setPackage(broadPOSPackage); //broadPOSPackage is from {@link EntryExtraData#PARAM_PACKAGE}
     *     context.sendBroadcast(intent);
     * </pre>
     */
    public static final String ACTION_START_SCAN = "com.pax.us.pay.ui.START_SCAN";

    /**
     * Current Action
     * <p>Tell BroadPOS what is the current activity action</p>
     * <p>Type: String</p>
     */
    public static final String PARAM_ACTION = "action";

    /**
     * Amount
     * <p>Type: Long</p>
     */
    public static final String PARAM_AMOUNT = "amount";

    /**
     * Total Amount
     * <p>Type: Long</p>
     */
    public static final String PARAM_TOTAL_AMOUNT = "totalAmount";
    /**
     * Tip Amount
     * <p>Type: Long</p>
     */
    public static final String PARAM_TIP = "tip";

    /**
     * Zip Code
     * <p>Type: String</p>
     */
    public static final String PARAM_ZIP_CODE = "zipCode";
    /**
     * Transaction Number
     * <p>Type: Long</p>
     */
    public static final String PARAM_TRANS_NUMBER = "transNumber";
    /**
     * Card Expiry Date
     * <p>Type: String</p>
     * <p>format: MMYY</p>
     * <p>Example: "1249"</p>
     */
    public static final String PARAM_EXPIRY_DATE = "expiryDate";
    /**
     * Original transaction date
     * <p>Type: String</p>
     * <p>format: YYYYMMDD</p>
     */
    public static final String PARAM_ORIG_DATE = "origTransDate";

    /**
     * Address
     * <p>Type: String</p>
     */
    public static final String PARAM_ADDRESS = "address";
    /**
     * Signature data <br>
     * <p>Type: short[]</p>
     */
    public static final String PARAM_SIGNATURE = "signature";

    /**
     * Voucher Number
     * <p>Type: String</p>
     */
    public static final String PARAM_VOUCHER_NUMBER = "voucherNumber";

    /**
     * Auth Code
     * <p>Type: String</p>
     */
    public static final String PARAM_AUTH_CODE = "authCode";

    /**
     * Health Care Amount
     * <p>Type: Long</p>
     */
    public static final String PARAM_HEALTH_CARE_AMOUNT = "healthCareAmount";
    /**
     * Clinic Amount
     * <p>Type: Long</p>
     */
    public static final String PARAM_CLINIC_AMOUNT = "clinicAmount";

    /**
     * Prescription Amount
     * <p>Type: Long</p>
     */
    public static final String PARAM_PRESCRIPTION_AMOUNT = "prescriptionAmount";

    /**
     * Vision Amount
     * <p>Type: Long</p>
     */
    public static final String PARAM_VISION_AMOUNT = "visionAmount";

    /**
     * Dental Amount
     * <p>Type: Long</p>
     */
    public static final String PARAM_DENTAL_AMOUNT = "dentalAmount";

    /**
     * Copay Amount
     * <p>Type: Long</p>
     */
    public static final String PARAM_COPAY_AMOUNT = "copayAmount";

    /**
     * OTC Amount
     * <p>Type: Long</p>
     */
    public static final String PARAM_OTC_AMOUNT = "otcAmount";

    /**
     * Transit Amount
     * <p>Type: Long</p>
     */
    public static final String PARAM_TRANSIT_AMOUNT = "transitAmount";

    /**
     * FSA Type
     * <p>Type: String</p>
     * See {@link com.pax.us.pay.ui.constant.entry.enumeration.FSAType}
     */
    public static final String PARAM_FSA_OPTION = "fsaOption";

    /**
     * CashBack Amount
     * <p>Type: Long</p>
     */
    public static final String PARAM_CASHBACK_AMOUNT = "cashbackAmount";

    /**
     * Fuel Amount
     * <p>Type: Long</p>
     */
    public static final String PARAM_FUEL_AMOUNT = "fuelAmount";

    /**
     * Tax Amount
     * <p>Type: Long</p>
     */
    public static final String PARAM_TAX_AMOUNT = "taxAmount";

    /**
     * Merchant Reference Number
     * <p>Type: String</p>
     */
    public static final String PARAM_MERCHANT_REFERENCE_NUMBER = "merchantReferenceNumber";

    /**
     * Reference Number
     * <p>Type: String</p>
     */
    public static final String PARAM_REFERENCE_NUMBER = "referenceNumber";

    /**
     * Invoice Number
     * <p>Type: String</p>
     */
    public static final String PARAM_INVOICE_NUMBER = "invoiceNumber";

    /**
     * Clerk ID
     * <p>Type: String</p>
     */
    public static final String PARAM_CLERK_ID = "clerkId";

    /**
     * Server ID
     * <p>Type: String</p>
     */
    public static final String PARAM_SERVER_ID = "serverId";

    /**
     * Table Number
     * <p>Type: String</p>
     */
    public static final String PARAM_TABLE_NUMBER = "tableNumber";

    /**
     * Phone Number
     * <p>Type: String</p>
     */
    public static final String PARAM_PHONE_NUMBER = "phoneNumber";
    /**
     * Guest Number
     * <p>Type: String</p>
     */
    public static final String PARAM_GUEST_NUMBER = "guestNumber";
    /**
     * Order Number
     * <p>Type: String</p>
     */
    public static final String PARAM_ORDER_NUMBER = "orderNumber";
    /**
     * PO Number
     * <p>Type: String</p>
     */
    public static final String PARAM_PO_NUMBER = "PONumber";
    /**
     * Product Description
     * <p>Type: String</p>
     */
    public static final String PARAM_PROD_DESC = "prodDesc";
    /**
     * Merchant TAX ID
     * <p>Type: String</p>
     */
    public static final String PARAM_MERCHANT_TAX_ID = "merchantTaxID";

    /**
     * Customer Service Phone Number
     * <p>Type: String</p>
     */
    public static final String PARAM_CS_PHONE_NUMBER = "customerServicePhoneNumber";

    /**
     * OCT Reference Number
     * <p>Type: String</p>
     */
    public static final String PARAM_OCT_REFERENCE_NUMBER = "octReferenceNumber";

    /**
     * Customer Code
     * <p>Type: String</p>
     */
    public static final String PARAM_CUSTOMER_CODE = "customerCode";

    /**
     * Prompt Restriction Code
     * <p>Type: String</p>
     */
    public static final String PARAM_PROMPT_RESTRICTION_CODE = "promptRestrictionCode";

    /**
     * Customer Data
     * <p>Type: String</p>
     */
    public static final String PARAM_FLEET_CUSTOMER_DATA = "fleetCustomerData";

    /**
     * Department Number
     * <p>Type: String</p>
     */
    public static final String PARAM_FLEET_DEPARTMENT_NUMBER = "fleetDepartmentNumber";

    /**
     * Fleet User ID
     * <p>Type: String</p>
     */
    public static final String PARAM_FLEET_USER_ID = "fleetUserId";

    /**
     * Vehicle ID
     * <p>Type: String</p>
     */
    public static final String PARAM_FLEET_VEHICLE_ID = "fleetVehicleId";

    /**
     * Vehicle Number
     * <p>Type: String</p>
     */
    public static final String PARAM_FLEET_VEHICLE_NUMBER = "fleetVehicleNumber";

    /**
     * JOB Number
     * <p>Type: String</p>
     */
    public static final String PARAM_FLEET_JOB_NUMBER = "fleetJobNumber";

    /**
     * Odometer
     * <p>Type: String</p>
     */
    public static final String PARAM_FLEET_ODOMETER = "fleetOdometer";

    /**
     * Driver ID
     * <p>Type: String</p>
     */
    public static final String PARAM_FLEET_DRIVER_ID = "fleetDriverId";

    /**
     * License Number
     * <p>Type: String</p>
     */
    public static final String PARAM_FLEET_LICENSE_NUMBER = "fleetLicenseNumber";

    /**
     * Fleet P.O. Number
     * <p>Type: String</p>
     */
    public static final String PARAM_FLEET_PO_NUMBER = "fleetPONumber";

    /**
     * Trip Number
     * <p>Type: String</p>
     */
    public static final String PARAM_FLEET_TRIP_NUMBER = "fleetTripNumber";

    /**
     * Unit Number
     * <p>Type: String</p>
     */
    public static final String PARAM_FLEET_UNIT_NUMBER = "fleetUnitNumber";

    /**
     * Trailer Number
     * <p>Type: String</p>
     */
    public static final String PARAM_FLEET_TRAILER_NUMBER = "fleetTrailerNumber";

    /**
     * Reefer Hours
     * <p>Type: String</p>
     */
    public static final String PARAM_FLEET_REEFER_HOURS = "fleetReeferHours";

    /**
     * Maintenance ID
     * <p>Type: String</p>
     */
    public static final String PARAM_FLEET_MAINTENANCE_ID = "fleetMaintenanceID";

    /**
     * Hubometer
     * <p>Type: String</p>
     */
    public static final String PARAM_FLEET_HUBOMETER = "fleetHubometer";

    /**
     * Employee Number
     * <p>Type: String</p>
     */
    public static final String PARAM_FLEET_EMPLOYEE_NUMBER = "fleetEmployeeNumber";

    /**
     * Additional Prompted Data 1
     * <p>Type: String</p>
     */
    public static final String PARAM_FLEET_ADDITIONAL_PROMPTED_DATA_1 = "fleetAdditionalPromptedData1";

    /**
     * Additional Prompted Data 2
     * <p>Type: String</p>
     */
    public static final String PARAM_FLEET_ADDITIONAL_PROMPTED_DATA_2 = "fleetAdditionalPromptedData2";

    /**
     * Destination zip code
     * <p>Type: String</p>
     */
    public static final String PARAM_DEST_ZIP_CODE = "destinationZipCode";

    /**
     * Original Transaction Identifier
     * <p>Type: String</p>
     */
    public static final String PARAM_ORIGINAL_TRANSACTION_IDENTIFIER = "originalTransactionIdentifier";

    //POSLINK
    /**
     * Input Value
     * <p>Type: String</p>
     */
    public static final String PARAM_INPUT_VALUE = "inputValue";

    /**
     * Selected Label
     * <p>Type: String</p>
     */
    public static final String PARAM_LABEL_SELECTED = "labelSelected";

    /**
     * Button Number
     * <p>Type: String</p>
     */
    public static final String PARAM_BUTTON_NUMBER = "buttonNumber";

    /**
     * Sign Status
     * <p>Type: String</p>
     */
    public static final String PARAM_SIGN_STATUS = "signStatus";

//    /**
//     * Value
//     * @deprecated Never used
//     */
//    public static final String PARAM_VALUE = "value";

    /**
     * Selected Index
     * <p>Type: Integer</p>
     */
    public static final String PARAM_INDEX = "index";

    /**
     * PARAM_CONFIRMED
     * <br>Type: Boolean <br>
     */
    public static final String PARAM_CONFIRMED = "confirmed";

    /**
     * X coordinate
     * <p>Type: Integer</p>
     * <p>Unit: pixel</p>
     */
    public static final String PARAM_X = "x";
    /**
     * Y coordinate
     * <p>Type: Integer</p>
     * <p>Unit: pixel</p>
     */
    public static final String PARAM_Y = "y";

    /**
     * Width
     * <p>Type: Integer</p>
     * <p>Unit: pixel</p>
     */
    public static final String PARAM_WIDTH = "width";

    /**
     * Height
     * <p>Type: Integer</p>
     * <p>Unit: pixel</p>
     */
    public static final String PARAM_HEIGHT = "height";

    /**
     * Font Size
     * <p>Type: Integer</p>
     * <p>Unit: scaled pixel</p>
     * You can get scaled pixel like this:
     * <pre>
     *     TextPaint paint = editText.getPaint();
     *     int fontSize = (int)(paint.getTextSize()/paint.density);
     * </pre>
     */
    public static final String PARAM_FONT_SIZE = "fontSize";
    /**
     * Focusable
     * <p>Type:Boolean. True is focusable.</p>
     */
    public static final String PARAM_FOCUSABLE = "focusable";
    /**
     * Hint
     * <p>Type:String</p>
     */
    public static final String PARAM_HINT = "hint";
    /**
     * Color
     * <p>Type : String</p>
     * <p>
     *     format: ARGB<br>
     *     Example: <br>
     *     "ffffffff" which represent the opaque white color.<br>
     *     "cc00ff00" which means the green color with 20% transparent.
     * </p>
     */
    public static final String PARAM_COLOR = "color";

    /**
     * Location of KEY 0
     * <p>Type : android.graphics.Rect</p>
     */
    public static final String PARAM_KEY_0 = "key_0";
    /**
     * Location of KEY 1
     * <p>Type : android.graphics.Rect</p>
     */
    public static final String PARAM_KEY_1 = "key_1";
    /**
     * Location of KEY 2
     * <p>Type : android.graphics.Rect</p>
     */
    public static final String PARAM_KEY_2 = "key_2";
    /**
     * Location of KEY 3
     * <p>Type : android.graphics.Rect</p>
     */
    public static final String PARAM_KEY_3 = "key_3";
    /**
     * Location of KEY 4
     * <p>Type : android.graphics.Rect</p>
     */
    public static final String PARAM_KEY_4 = "key_4";
    /**
     * Location of KEY 5
     * <p>Type : android.graphics.Rect</p>
     */
    public static final String PARAM_KEY_5 = "key_5";
    /**
     * Location of KEY 6
     * <p>Type : android.graphics.Rect</p>
     */
    public static final String PARAM_KEY_6 = "key_6";
    /**
     * Location of KEY 7
     * <p>Type : android.graphics.Rect</p>
     */
    public static final String PARAM_KEY_7 = "key_7";
    /**
     * Location of KEY 8
     * <p>Type : android.graphics.Rect</p>
     */
    public static final String PARAM_KEY_8 = "key_8";
    /**
     * Location of KEY 9
     * <p>Type : android.graphics.Rect</p>
     */
    public static final String PARAM_KEY_9 = "key_9";
    /**
     * Location of KEY CLEAR
     * <p>Type : android.graphics.Rect</p>
     */
    public static final String PARAM_KEY_CLEAR = "key_clear";
    /**
     * Location of KEY ENTER
     * <p>Type : android.graphics.Rect</p>
     */
    public static final String PARAM_KEY_ENTER = "key_enter";
    /**
     * Location of KEY CANCEL
     * <p>Type : android.graphics.Rect</p>
     */
    public static final String PARAM_KEY_CANCEL = "key_cancel";

    //---------------------Visa Installment---------------
    /**
     * Visa Installment Transaction ID
     * <p>Type:String</p>
     */
    public static final String PARAM_VISA_TRANSID = "visaTransID";

    /**
     * Visa Installment Acceptance ID
     * <p>Type: String</p>
     */
    public static final String PARAM_VISA_PLAN_ACCEPTANCE_ID = "visaPlanAcceptanceID";

    /**
     * Ticket Number
     * <p>Type: String</p>
     */
    public static final String PARAM_TICKET_NUMBER = "ticketNumber";
}
