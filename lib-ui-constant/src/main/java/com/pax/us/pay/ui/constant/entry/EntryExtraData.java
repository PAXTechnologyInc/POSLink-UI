package com.pax.us.pay.ui.constant.entry;

/**
 * define Extra Data for Activity Actions and Broadcast Actions
 */
public final class EntryExtraData {
    private EntryExtraData(){

    }

    /**
     * Message displayed on UI screen 
     * <p>Type : String </p>
     */
    public static final String PARAM_MESSAGE = "message";
    /**
     * The package name of caller.
     * <p>Type : String</p>
     */
    public static final String PARAM_PACKAGE = "senderPackage";

    /**
     * Transaction Type
     * <p>Type : String</p>
     * <p>Example: "CREDIT SALE"</p>
     */
    public static final String PARAM_TRANS_TYPE = "transType";


    /**
     * Transaction mode
     * <p>BroadPOS will show specific watermark. If you don't want to, you can ignore this parameter.</p>
     * <p>Type: String</p>
     * <p>see {@link com.pax.us.pay.ui.constant.entry.enumeration.TransMode} for details </p>
     */
    public static final String PARAM_TRANS_MODE = "transMode";

//    /**
//     * Sub Trans Type
//     * <p>Type : String</p>
//     * <p>See {@link com.pax.us.pay.ui.constant.entry.enumeration.SubTransType} and {@link com.pax.us.pay.ui.constant.entry.enumeration.CashoutType}</p>
//     * @deprecated Never used.
//     */
//    public static final String PARAM_SUB_TRANS_TYPE = "subTransType";


    /**
     * Options
     * <p>Type : String[]</p>
     */
    public static final String PARAM_OPTIONS = "options";

    /**
     * Currency Type
     * <p>Type: String</p>
     * <p>Default value: {@link com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType#USD}
     * <p>
     *      It is used to display a amount value.<br>
     *      Example:<br>
     *      The value of amount is 100, and currency type is {@link com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType#USD},<br>
     *      the amount on page is shown as "$1.00"<br>
     *      else, if currency type is {@link com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType#POINT}, the amount on page is shown as "POINT 100".
     * </p>
     * <p>See {@link com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType} for details<br>
     */
    public static final String PARAM_CURRENCY = "currency";
    /**
     * Base Amount
     * <p>Type : Long</p>
     */
    public static final String PARAM_BASE_AMOUNT = "baseAmount";

    /**
     * Total Amount
     * <p>
     *     Type : Long
     * </p>
     */
    public static final String PARAM_TOTAL_AMOUNT = "totalAmount";
    /**
     * Amount Message
     * <p>Type : String</p>
     */
    public static final String PARAM_AMOUNT_MESSAGE = "amountMessage";
    /**
     * Tip Name
     * <p>Type : String</p>
     * <p>Example: "TIP"</p>
     */
    public static final String PARAM_TIP_NAME = "tipName";

    /**
     * Tip options for selection
     * <p>Type : String[]</p>
     */
    public static final String PARAM_TIP_OPTIONS = "tipOptions";

    /**
     * Tip rate options for selection
     * <p>Type : String[]</p>
     */
    public static final String PARAM_TIP_RATE_OPTIONS = "tipRateOptions";

    /**
     * Cashback options for selection
     * <p>Type : String[]</p>
     * <p>
     *     Example: {"1000","2000","3000","5000"} means $10.00,$20.00,$30.00,$40.00
     * </p>
     */
    public static final String PARAM_CASHBACK_OPTIONS = "cashbackOptions";

//    /**
//     * Cashback rate options for selection
//     * <p>Type : String[]</p>
//     * <p>Used in {@link TextEntry#ACTION_ENTER_CASH_BACK}
//     * @deprecated Never used
//     */
//    public static final String PARAM_CASHBACK_RATE_OPTIONS = "cashbackRateOptions";

    /**
     * Prompt Other CashBack
     * <p>Type : boolean</p>
     */
    public static final String PARAM_ENABLE_OTHER_PROMPT = "enableOtherPrompt";

    /**
     * Timeout (unit: ms)
     * <p>General input parameter for every Activity Action</p>
     * <p>Type : Long</p>
     * <p>Default is 30000.</p>
     */
    public static final String PARAM_TIMEOUT = "timeout";


    /**
     * Printer Status
     * <p>Type: String</p>
     * <p>See {@link com.pax.us.pay.ui.constant.entry.enumeration.PrintStatusType} for details</p>
     */
    public static final String PARAM_PRINT_STATUS = "printStatus";

    /**
     * PIN Style
     * <p>Type: String</p>
     * <p>See {@link com.pax.us.pay.ui.constant.entry.enumeration.PinStyles} for details</p>
     */
    public static final String PARAM_PIN_STYLES = "pinStyles";

    /**
     * Is Online Pin
     * <p>BroadPOS use same UI for online pin and offline pin. <br>
     * But if you prefer different UI, you can use this parameter</p>
     * <p>Type : Boolean. <br>
     * True is online pin. False is offline pin.</p>
     * <p>Default is false</p>
     */
    public static final String PARAM_IS_ONLINE_PIN = "isOnlinePin";

    /**
     * Length limit for PIN
     * <p>Type: String</p>
     * <p>Format is same with {@link EntryExtraData#PARAM_VALUE_PATTERN}</p>
     */
    public static final String PARAM_PIN_RANGE = "pinRange";

    /**
     * Display "CANCEL" button or not
     * <p>Type : Boolean. true is enabled. false is disabled.</p>
     * <p>Default is false</p>
     */
    public static final String PARAM_ENABLE_CANCEL = "enableCancel";

    /**
     * Card Type
     * <p>Type: String</p>
     * <p>See {@link com.pax.us.pay.ui.constant.entry.enumeration.CardType} for details</p>
     */
    public static final String PARAM_CARD_TYPE = "cardType";

    /**
     * VCode Name
     * <p>Type: String</p>
     * <p>See {@link com.pax.us.pay.ui.constant.entry.enumeration.VCodeName} for details</p>
     */
    public static final String PARAM_VCODE_NAME = "vcodeName";

    /**
     * PAN Style
     * <p>Type: String</p>
     * <p>See {@link com.pax.us.pay.ui.constant.entry.enumeration.PanStyles} for details</p>
     */
    public static final String PARAM_PAN_STYLES = "panStyles";

    /**
     * Use external PIN PAD or internal PIN PAD
     * <p>Type: Boolean. True is External. False is Internal.</p>
     * <p>Default is false</p>
     */
    public static final String PARAM_IS_EXTERNAL_PINPAD = "external";
    /**
     * Interface Style
     * <p>Type: String</p>
     * @deprecated Not used anymore.
     */
    public static final String PARAM_INTERFACE_STYLES = "interfaceStyles";

    /**
     * Merchant Name
     * <p>Type: String</p>
     * <p>Used when multi-merchant feature is enabled</p>
     */
    public static final String PARAM_MERCHANT_NAME = "merchantName";

//    /**
//     * Show virtual pin pad
//     * <p>Type: Boolean</p>
//     * @deprecated Not used anymore BPCOMMON-24
//     */
//    public static final String PARAM_SHOW_VIRTUAL_PINPAD = "showVirtualPinPad";

    /**
     * Admin Password Type
     * <p>Type: String</p>
     * <p>See {@link com.pax.us.pay.ui.constant.entry.enumeration.AdminPasswordType} for details</p>
     */
    public static final String PARAM_ADMIN_PASSWORD_TYPE = "adminPasswordType";

    /**
     * Exchange Rate
     * <p>Type: String</p>
     */
    public static final String PARAM_EXCHANGE_RATE = "exchangeRate";
    /**
     * Currency Alpha Code
     * <p>Type: String</p>
     */
    public static final String PARAM_CURRENCY_ALPHA_CODE = "currencyAlpCode";
    /**
     * Margin
     * <p>Type: String</p>
     */
    public static final String PARAM_MARGIN = "margin";
    /**
     * Amount Message
     * <p>Type: String</p>
     */
    public static final String PARAM_FOREIGN_AMOUNT_MESSAGE = "foreignAmountMessage";
    /**
     * Confirm with Currency
     * <p>Type: Boolean</p>
     */
    public static final String PARAM_CONFIRM_WITH_CURRENCY = "confirmWithCurrency";

    /**
     * No Tip Selection
     * <p>Type: Boolean</p>
     * <p>Default is false</p>
     */
    public static final String PARAM_ENABLE_NO_TIP_SELECTION = "enableNoTipSelection";

    /**
     * Has Physical Keyboard
     * <p>Type: Boolean</p>
     */
    public static final String PARAM_HAS_PHYSICAL_KEYBOARD = "hasPhyKeyboard";


    /**
     * Enable Swipe
     * <p>Type: Boolean. True is enabled. False is disabled</p>
     * <p>Default is false.</p>
     */
    public static final String PARAM_ENABLE_SWIPE = "enableSwipe";

    /**
     * Enable Chip
     * <p>Type: Boolean. True is enabled. False is disabled</p>
     * <p>Default is false.</p>
     */
    public static final String PARAM_ENABLE_INSERT = "enableInsert";

    /**
     * Enable Contactless
     * <p>Type: Boolean. True is enabled. False is disabled</p>
     * <p>Default is false.</p>
     */
    public static final String PARAM_ENABLE_TAP = "enableTap";

    /**
     * Enable Scanner
     * <p>Type: Boolean. True is enabled. False is disabled</p>
     * <p>Default is false.</p>
     */
    public static final String PARAM_ENABLE_SCAN = "enableScan";

    /**
     * Enable Manual Enter
     * <p>Type: Boolean. True is enabled. False is disabled</p>
     * <p>Default is false.</p>
     */
    public static final String PARAM_ENABLE_MANUAL = "enableManualEntry";

    /**
     * Enable Apple Pay
     * <p>Type: Boolean. True is enabled. False is disabled</p>
     * <p>Default is false.</p>
     */
    public static final String PARAM_ENABLE_APPLEPAY = "enableApplePay";

    /**
     * Enable Google Pay
     * <p>Type: Boolean. True is enabled. False is disabled</p>
     * <p>Default is false.</p>
     */
    public static final String PARAM_ENABLE_GOOGLEPAY = "enableGooglePay";

    /**
     * Enable Samsung Pay
     * <p>Type: Boolean. True is enabled. False is disabled</p>
     * <p>Default is false.</p>
     */
    public static final String PARAM_ENABLE_SAMSUNGPAY = "enableSamsungPay";

    /**
     * Enable NFC Pay
     * <p>Type: Boolean. True is enabled. False is disabled</p>
     * <p>Default is false.</p>
     */
    public static final String PARAM_ENABLE_NFCPAY = "enableNFCPay";

    /**
     * Enable Laser Scanner
     * <p>Type: Boolean. True is enabled. False is disabled</p>
     * <p>Default is false.</p>
     */
    public static final String PARAM_ENABLE_LASER_SCAN = "enableLaserScan";

    /**
     * Enable Visual Contactless Light
     * <p>Type: Boolean. True is enabled. False is disabled</p>
     */
    public static final String PARAM_ENABLE_CONTACTLESS_LIGHT = "enableContactlessLight";


    /**
     * Information Keys
     * <p>Type: String[]</p>
     */
    public static final String PARAM_INFORMATION_KEY = "informationKey";
    /**
     * Information Values
     * <p>Type: String[]</p>
     */
    public static final String PARAM_INFORMATION_VALUE = "informationValue";


    /**
     * Title
     * <p>Type: String</p>
     */
    public static final String PARAM_TITLE = "title";
//    public static final String PARAM_USER_MESSAGE = "userMessage";
//    public static final String PARAM_PRIMARY_AMOUNT = "primaryAmount";
//    public static final String PARAM_CONVENIENCE_FEE = "convenienceFee";
//    public static final String PARAM_SERVICE_FEE = "serviceFee";
//    public static final String PARAM_TOTAL_FEE = "totalFee";
//
    /**
     * Balance
     * <p>Type: Long</p>
     *  @deprecated {@link ConfirmationEntry#ACTION_CONFIRM_BALANCE} deprecated.
     */
    public static final String PARAM_BALANCE = "balance";
//
//
//    /**
//     * Card Number
//     * <p>Type: String</p>
//     *  @deprecated Never used.
//     */
//    public static final String PARAM_CARD_NUMBER = "cardNumber";
//    /**
//     * Batch Number
//     * <p>Type: String</p>
//     *  @deprecated Never used.
//     */
//    public static final String PARAM_BATCH_NUMBER = "batchNumber";
//    /**
//     * Order Number
//     * <p>Type: String</p>
//     *  @deprecated Never used.
//     */
//    public static final String PARAM_ORDER_NUMBER = "orderNumber";


    /**
     * Name of Enabled Tips (For Standalone multiple-tips use only)
     * <p>Type: String[]</p>
     * <p>Used when multiple tips are enabled.</p>
     * <p>
     *     Example:<br>
     *     If TIP1, TIP2, TIP3 are all enabled.<br>
     *     If name of enabled tips is: {"TIP", "TIP2", "TIP3}<br>
     *     value of enabled tips is: {"100", "200"}<br>
     *     That means:<br>
     *     The customer has entered $1.00 TIP, $2.00 TIP2. TIP3 not valued yet.
     * </p>
     */
    public static final String PARAM_TIP_NAMES = "tipNames";
    /**
     * Value of Enabled Tips (For Standalone multiple-tips use only)
     * <p>Type: String[]</p>
     * <p>see {@link #PARAM_TIP_NAMES} for details</p>
     */
    public static final String PARAM_TIP_AMOUNTS = "tipAmounts";

    /**
     * Approve Amount
     * <p>Type: Long</p>
     */
    public static final String PARAM_APPROVED_AMOUNT = "approvedAmount";
//    /**
//     * @deprecated Never used
//     */
//    public static final String PARAM_BASE_POINTS = "basePoints";
//    /**
//     * @deprecated Never used
//     */
//    public static final String PARAM_TOTAL_POINTS = "totalPoints";
//    /**
//     * @deprecated Never used
//     */
//    public static final String PARAM_APPROVED_POINTS = "approvedPoints";
    /**
     * Surcharge Fee Name
     * <p>Type: String</p>
     */
    public static final String PARAM_SURCHARGE_FEE_NAME = "surchargeFeeName";

    /**
     * Surcharge Fee
     * <p>Type: Long</p>
     */
    public static final String PARAM_SURCHARGE_FEE = "surchargeFee";
    /**
     * Service Fee
     * <p>Type: Long</p>
     */
    public static final String PARAM_SERVICE_FEE = "serviceFee";
    /**
     * Service Fee Name
     * <p>Type: String</p>
     */
    public static final String PARAM_SERVICE_FEE_NAME = "serviceFeeName";
//    public static final String PARAM_PRIMARY_AMOUNT = "primaryAmount";
    /**
     * Enable Bypass
     * <p>Type: Boolean</p>
     */
    public static final String PARAM_ENABLE_BYPASS = "enableBypass";


    /**
     * FSA Amount Options
     * <p>Type: String[]</p>
     * <p>See {@link com.pax.us.pay.ui.constant.entry.enumeration.FSAAmountType}</p>
     */
    public static final String PARAM_FSA_AMOUNT_OPTIONS = "fsaAmountOptions";

//    /**
//     * Health Care Amount
//     * <p>Type: Long</p>
//     * @deprecated Never used.
//     */
//    public static final String PARAM_HEALTH_CARE_AMOUNT = "healthCareAmount";
//    /**
//     * Clinic Amount
//     * <p>Type: Long</p>
//     * @deprecated Never used.
//     */
//    public static final String PARAM_CLINIC_AMOUNT = "clinicAmount";
//
//    /**
//     * Dental Amount
//     * <p>Type: Long</p>
//     * @deprecated Never used.
//     */
//    public static final String PARAM_DENTAL_AMOUNT = "dentalAmount";

//    /**
//     * Copay Amount
//     * <p>Type: Long</p>
//     * @deprecated Never used.
//     */
//    public static final String PARAM_COPAY_AMOUNT = "copayAmount";
//
//    /**
//     * Prescription Amount
//     * <p>Type: Long</p>
//     * @deprecated Never used.
//     */
//    public static final String PARAM_PRESCRIPTION_AMOUNT = "prescriptionAmount";
//
//    /**
//     * Vision Amount
//     * <p>Type: Long</p>
//     * @deprecated Never used.
//     */
//    public static final String PARAM_VISION_AMOUNT = "visionAmount";
//
//    /**
//     * Transit Amount
//     * <p>Type: Long</p>
//     * @deprecated Never used.
//     */
//    public static final String PARAM_TRANSIT_AMOUNT = "transitAmount";
//
//    /**
//     * Status
//     * <p>Type: String</p>
//     * @deprecated Never used.
//     */
//    public static final String PARAM_STATUS = "status";
//    /**
//     * Clerk ID
//     * <p>Type: String</p>
//     * @deprecated Never used.
//     */
//    public static final String PARAM_CLERK_ID = "clerkID";
//    /**
//     * Clerk Name
//     * <p>Type: String</p>
//     * @deprecated Never used.
//     */
//    public static final String PARAM_CLERK_NAME = "clerkName";
//    /**
//     * Invoice Number
//     * <p>Type: String</p>
//     * @deprecated Never used.
//     */
//    public static final String PARAM_INVOICE_NUMBER = "invoiceNumber";
//    /**
//     * PO Number
//     * <p>Type: String</p>
//     * @deprecated Never used.
//     */
//    public static final String PARAM_PO_NUMBER = "poNumber";
//    /**
//     * Table Number
//     * <p>Type: String</p>
//     * @deprecated Never used.
//     */
//    public static final String PARAM_TABLE_NUMBER = "tableNumber";
//    /**
//     * Guest Number
//     * <p>Type: String</p>
//     * @deprecated Never used.
//     */
//    public static final String PARAM_GUESTS_NUMBER = "guestsNumber";
//    /**
//     * Customer Code
//     * <p>Type: String</p>
//     * @deprecated Never used.
//     */
//    public static final String PARAM_CUST_CODE = "custCode";
//    /**
//     * Merchant TAX ID
//     * <p>Type: String</p>
//     * @deprecated Never used.
//     */
//    public static final String PARAM_MERCHANT_TAX_ID = "merchantTaxID";
//    /**
//     * Product Description
//     * <p>Type: String</p>
//     * @deprecated Never used.
//     */
//    public static final String PARAM_PROD_DESC = "prodDesc";
//    /**
//     * TAX EXEMPT ID
//     * <p>Type: String</p>
//     * @deprecated Never used.
//     */
//    public static final String PARAM_TAX_EXEMPT_ID = "taxExemptID";
//    /**
//     * Ref Number
//     * <p>Type: String</p>
//     * @deprecated Never used.
//     */
//    public static final String PARAM_REF_NUMBER = "refNumber";
//    /**
//     * AUTH CODE
//     * <p>Type: String</p>
//     * @deprecated Never used.
//     */
//    public static final String PARAM_AUTH_CODE = "authCode";
//    /**
//     * Response Status
//     * <p>Type: String</p>
//     * @deprecated Never used.
//     */
//    public static final String PARAM_RESPONSE_STATUS = "responseStatus";
    /**
     * Input Type
     * <p>Type: String</p>
     * <p>See {@link com.pax.us.pay.ui.constant.entry.enumeration.InputType}</p>
     */
    public static final String PARAM_EINPUT_TYPE = "eInputType";
//    public static final String PARAM_MAX_VALUE = "maxValue";
//    public static final String PARAM_MIN_VALUE = "minValue";
    /**
     * Maximum length of input content
     * Type : String <br>
     */
    public static final String PARAM_MAX_LENGTH = "maxLength";

    /**
     * Minimum length of input content
     * Type : String <br>
     */
    public static final String PARAM_MIN_LENGTH = "minLength";

    /**
     * Tip Unit
     * <p>Type : String</p>
     * <p>Default is {@link com.pax.us.pay.ui.constant.entry.enumeration.UnitType#CENT}</p>
     * <p>See {@link com.pax.us.pay.ui.constant.entry.enumeration.UnitType} for details</p>
     */
    public static final String PARAM_TIP_UNIT = "tipUnit";

    /**
     * PARAM_AMOUNT_UNIT : the unit of tip amount <br>
     * Type : Long <br>
     * @deprecated Not used anymore. Use {@link #PARAM_TIP_UNIT} instead.
     */
    public static final String PARAM_AMOUNT_UNIT = "amountUnit";

    /**
     * The length limit for input value
     * <p>
     * Host enumerates all possible lengths of TEXT actions. <br>
     * ',' will be used to separate each number of length and '-' will be used to define a sequential length. <br>
     * Example: The valid length of the value range is "0,4,6- 12". <br>
     *          0 means that no input is required and pressing "Enter" will return. <br>
     *          The length value should be sorted in ascending order. <br>
     * </p>
     * <p>Type : String</p>
     */
    public static final String PARAM_VALUE_PATTERN = "valuePattern";

    /**
     * Length limit for Customer Data
     * <p>Type: String</p>
     * <p>See {@link #PARAM_VALUE_PATTERN} for details</p>
     */
    public static final String PARAM_FLEET_CUSTOMER_DATA_PATTERN = "fleetCustomerDataPattern";

    /**
     * Length limit for Department Number
     * <p>Type: String</p>
     * <p>See {@link #PARAM_VALUE_PATTERN} for details</p>
     */
    public static final String PARAM_FLEET_DEPARTMENT_NUMBER_PATTERN = "fleetDepartmentNumberPattern";

    /**
     * Length limit for User ID
     * <p>Type: String</p>
     * <p>See {@link #PARAM_VALUE_PATTERN} for details</p>
     */
    public static final String PARAM_FLEET_USER_ID_PATTERN = "fleetUserIdPattern";

    /**
     * Length limit for Vehicle ID
     * <p>Type: String</p>
     * <p>See {@link #PARAM_VALUE_PATTERN} for details</p>
     */
    public static final String PARAM_FLEET_VEHICLE_ID_PATTERN = "fleetVehicleIdPattern";

    /**
     * Length limit for Vehicle Number
     * <p>Type: String</p>
     * <p>See {@link #PARAM_VALUE_PATTERN} for details</p>
     */
    public static final String PARAM_FLEET_VEHICLE_NUMBER_PATTERN = "fleetVehicleNumberPattern";

    /**
     * Length limit for Job Number
     * <p>Type: String</p>
     * <p>See {@link #PARAM_VALUE_PATTERN} for details</p>
     */
    public static final String PARAM_FLEET_JOB_NUMBER_PATTERN = "fleetJobNumberPattern";

    /**
     * Length limit for Odometer
     * <p>Type: String</p>
     * <p>See {@link #PARAM_VALUE_PATTERN} for details</p>
     */
    public static final String PARAM_FLEET_ODOMETER_PATTERN = "fleetOdometerPattern";

    /**
     * Length limit for Driver ID
     * <p>Type: String</p>
     * <p>See {@link #PARAM_VALUE_PATTERN} for details</p>
     */
    public static final String PARAM_FLEET_DRIVER_ID_PATTERN = "fleetDriverIdPattern";

    /**
     * Length limit for License Number
     * <p>Type: String</p>
     * <p>See {@link #PARAM_VALUE_PATTERN} for details</p>
     */
    public static final String PARAM_FLEET_LICENSE_NUMBER_PATTERN = "fleetLicenseNumberPattern";

    /**
     * Length limit for Zip Code
     * <p>Type: String</p>
     * <p>See {@link #PARAM_VALUE_PATTERN} for details</p>
     */
    public static final String PARAM_ZIP_CODE_PATTERN = "zipCodePattern";
    /**
     * Length limit for Address
     * <p>Type: String</p>
     * <p>See {@link #PARAM_VALUE_PATTERN} for details</p>
     */
    public static final String PARAM_ADDRESS_PATTERN = "addressPattern";

    /**
     * QRCode Content
     * <p>Type: </p>
     */
    public static final String PARAM_QR_CODE_CONTENT = "qrCodeContent";

//    /**
//     * Tansaction status <br>
//     *     Type: String[]<br>
//     *     See upload, offline, voided, Adjusted ??????
//     * @deprecated Never used.
//     */
//    public static final String PARAM_TRANS_STATUS = "transStatus";
//    /**
//     * AVS Response
//     * <p>Type: String</p>
//     * @deprecated Never used.
//     */
//    public static final String PARAM_AVS_RESPONSE = "avsResponse";
//    /**
//     * Entry Mode
//     * <p>Type: String</p>
//     * @deprecated Never used.
//     */
//    public static final String PARAM_ENTRY_MODE = "entryMode";
//    /**
//     * FoodStamp Balance
//     * <p>Type: Long</p>
//     * @deprecated Never used.
//     */
//    public static final String PARAM_FOOD_STAMPS_BALANCE = "foodStampsBalance";
//    /**
//     * Cashbenefit Balance
//     * <p>Type: Long</p>
//     * @deprecated Never used.
//     */
//    public static final String PARAM_CASH_BENEFIT_BALANCE = "cashBenefitBalance";

    /**
     * RECEIPT URL
     * <p>Type: String</p>
     */
    public static final String PARAM_RECEIPT_URI = "receiptUri";
//    /**
//     * EULA URI
//     * <p>Type: String</p>
//     * @deprecated EULA handled by Host app. So we need remove it.
//     */
//    public static final String PARAM_EULA_URI = "eulaUri";
//    /**
//     * Start Type
//     * <p>Type: String</p>
//     * @deprecated Used in EULA. But EULA handled by Host app. So we need remove it.
//     */
//    public static final String PARAM_START_TYPE = "startType";

    /**
     * Continue Screen
     * <p>Type: String</p>
     * <p>See {@link com.pax.us.pay.ui.constant.entry.enumeration.ManageUIConst.ContinuousScreen} for details</p>
     */
    public static final String PARAM_CONTINUE_SCREEN = "continueScreen";

    /**
     * Message 1
     * <p>Type: String</p>
     */
    public static final String PARAM_MESSAGE_1 = "message1";
    /**
     * Message 2
     * <p>Type: String</p>
     */
    public static final String PARAM_MESSAGE_2 = "message2";
    /**
     * Input Type (For POSLink UI use)
     * <p>Type: String</p>
     * <br>See {@link com.pax.us.pay.ui.constant.entry.enumeration.ManageUIConst.InputType} for details
     */
    public static final String PARAM_INPUT_TYPE = "inputType";
    /**
     * Default Value
     * <p>Type: String</p>
     */
    public static final String PARAM_DEFAULT_VALUE = "defaultValue";

    /**
     * Labels
     * <p>Type: String[]</p>
     *
     */
    public static final String PARAM_LABELS = "labels";

    /**
     * Label Properties
     * <p>Type: String[]</p>
     * <p>See {@link com.pax.us.pay.ui.constant.entry.enumeration.ManageUIConst.LabelProperty} for details</p>
     */
    public static final String PARAM_LABELS_PROPERTY = "labelsProperty";

    /**
     * Button Type
     * <p>Type: String</p>
     * <p>See {@link com.pax.us.pay.ui.constant.entry.enumeration.ManageUIConst.ButtonType} for details</p>
     */
    public static final String PARAM_BUTTON_TYPE = "buttonType";

    /**
     * Tax Line
     * <p>Type: String</p>
     */
    public static final String PARAM_TAX_LINE = "taxLine";
    /**
     * Total Line
     * <p>Type: String</p>
     */
    public static final String PARAM_TOTAL_LINE = "totalLine";
    /**
     * Image URL
     * <p>Type: String</p>
     */
    public static final String PARAM_IMAGE_URL = "imageURL";
    /**
     * Image Description
     * <p>Type: String</p>
     */
    public static final String PARAM_IMAGE_DESC = "imageDesc";
    /**
     * Message List
     * <p>Type: String[]</p>
     */
    public static final String PARAM_MESSAGE_LIST = "messageList";
    /**
     * Currency Symbol
     * <p>Type: String</p>
     */
    public static final String PARAM_CURRENCY_SYMBOL = "currencySymbol";
    /**
     * Button 1 Name
     * <p>Type: String </p>
     */
    public static final String PARAM_BUTTON_1_NAME = "button1Name";
    /**
     * Button 1 Color
     * <p>Type: String </p>
     */
    public static final String PARAM_BUTTON_1_COLOR = "button1Color";
    /**
     * Button 1 Key
     * <p>Type: String </p>
     */
    public static final String PARAM_BUTTON_1_KEY = "button1Key";
    /**
     * Button 2 Name
     * <p>Type: String </p>
     */
    public static final String PARAM_BUTTON_2_NAME = "button2Name";
    /**
     * Button 2 Color
     * <p>Type: String </p>
     */
    public static final String PARAM_BUTTON_2_COLOR = "button2Color";
    /**
     * Button 2 Key
     * <p>Type: String </p>
     */
    public static final String PARAM_BUTTON_2_KEY = "button2Key";
    /**
     * Button 3 Name
     * <p>Type: String </p>
     */
    public static final String PARAM_BUTTON_3_NAME = "button3Name";
    /**
     * Button 3 Color
     * <p>Type: String </p>
     */
    public static final String PARAM_BUTTON_3_COLOR = "button3Color";
    /**
     * Button 3 Key
     * <p>Type: String </p>
     */
    public static final String PARAM_BUTTON_3_KEY = "button3Key";
    /**
     * TEXT
     * <p>Type: String</p>
     */
    public static final String PARAM_TEXT = "text";
    /**
     * Enable Hard Key
     * <p>Type: Boolean</p>
     */
    public static final String PARAM_ENABLE_HARD_KEY = "enableHardKey";
    /**
     * Hard Key List
     * <p>Type: String[]</p>
     */
    public static final String PARAM_HARD_KEY_LIST = "hardKeyList";
    /**
     * Sign Box
     * <p>Type: Long</p>
     */
    public static final String PARAM_SIGN_BOX = "signBox";
    /**
     * Bacode Type
     * <p>Type: String</p>
     */
    public static final String PARAM_BARCODE_TYPE = "barcodeType";
    /**
     * Barcode Data
     * <p>Type: String</p>
     */
    public static final String PARAM_BARCODE_DATA = "barcodeData";
    /**
     * Sign Line 1
     * <p>Type: String</p>
     */
    public static final String PARAM_SIGNLINE1 = "signLine1";
    /**
     * Sign Line 2
     * <p>Type: String</p>
     */
    public static final String PARAM_SIGNLINE2 = "signLine2";
    /**
     * Input Text Title
     * <p>Type: String</p>
     */
    public static final String PARAM_INPUT_TEXT_TITLE = "inputTextTitle";

    /**
     * Accessibility Pin Pad Mode
     * <p>Type: String</p>
     * <p>"D" is disabled, "Y" is ON. "N" is OFF.</p>
     */
    public static final String PARAM_ACCESSIBILITY_PIN_PAD_MODE = "accessibilityPinPadMode";

    //---------------------------------Visa Installment--------------------------------
    /**
     * Installment Payment Amounts
     * <p>Type: String[]</p>
     */
    public static final String PARAM_INSTALLMENT_PAYMENT_AMOUNTS = "installmentPaymentAmounts";
    /**
     * Installment Payment terms and conditions
     * <p>Type: String[]</p>
     */
    public static final String PARAM_INSTALLMENT_PAYMENT_TERMS_AND_CONDITIONS = "termsAndConditions";
    /**
     * Installment Payment total fees
     * <p>Type: String[]</p>
     */
    public static final String PARAM_INSTALLMENT_PAYMENT_TOTAL_FEES = "totalFees";
    /**
     * Installment number
     * <p>Type: String[]</p>
     */
    public static final String PARAM_INSTALLMENT_PAYMENT_NUMBER_OF_INSTALLMENTS = "numberOfInstallmens";
    /**
     * Installment Frequency
     * <p>Type: String[]</p>
     */
    public static final String PARAM_INSTALLMENT_PAYMENT_FREQUENCY_OF_INSTALLMENTS = "frequencyOfInstallments";
    /**
     * Total Amount Inclusive Fees
     * <p>Type: String[]</p>
     */
    public static final String PARAM_INSTALLMENT_PAYMENT_TOTAL_AMOUNT_INCLUSIVE_FEES = "totalAmountInclusiveFees";
    /**
     * Total Amount Currencies
     * <p>Type: String[]</p>
     */
    public static final String PARAM_INSTALLMENT_PAYMENT_PLAN_CURRENCIES = "CURRENCIES";
    /**
     * Total Amount Plan ID
     * <p>Type: String[]</p>
     */
    public static final String PARAM_INSTALLMENT_PAYMENT_PLAN_ID = "planId";

    //------------Tip Adjust------------------
    /**
     * Transaction URL
     * <p>Type:String</p>
     */
    public static final String PARAM_TRANS_URL = "transUri";
    /**
     * Transaction Selection
     * <p>Type:String</p>
     */
    public static final String PARAM_TRANS_SELECTION = "transSelection";
    /**
     * Transaction Selection Arguments
     * <p>Type:String[]</p>
     */
    public static final String PARAM_TRANS_SELECTION_ARGUMENTS = "transSelectionArguments";
// Yanina: Remove this parameters. Use PARAM_MESSAGE instead.
// ADJ-144
 //   //internal parameter
//    /**
//    * Type：Boolean
//    * Action: ACTION_CONFIRM_BATCH_CLOSE
//    * @deprecated Remove this parameters. Use PARAM_MESSAGE instead.
//    * */
//    public static final String PARAM_INTERNAL_NICKNAME_FLAG = "internalNicknameFlag";

    public static final String PARAM_STATUS_OBJECT = "status_object";
    public static final String PARAM_STATUS_STATE = "status_state";
    public static final String PARAM_STATUS_TYPE = "status_type";
}
