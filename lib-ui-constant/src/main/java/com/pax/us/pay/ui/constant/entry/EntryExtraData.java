package com.pax.us.pay.ui.constant.entry;

import com.pax.us.annotation.Field;

public final class EntryExtraData {
    
    @Field(type = String.class)
    public static final String PARAM_MESSAGE = "message";
    @Field(type = String.class)
    public static final String PARAM_PACKAGE = "senderPackage";
    /**
     * @see com.pax.us.pay.ui.constant.entry.enumeration.TransType
     */
    @Field(type = String.class)
    public static final String PARAM_TRANS_TYPE = "transType";
    @Field(type = String.class)
    public static final String PARAM_TRANS_TIME = "transTime";
    @Field(type = String.class)
    public static final String PARAM_TRANS_DATE = "transDate";
    @Field(type = String.class)
    public static final String PARAM_MERCHANT_ID = "merchantID";
    @Field(type = String.class)
    public static final String PARAM_TERMINAL_ID = "terminalID";
    /**
     * @see com.pax.us.pay.ui.constant.entry.enumeration.TransMode
     */
    @Field(type = String.class)
    public static final String PARAM_TRANS_MODE = "transMode";
    /**
     * @see com.pax.us.pay.ui.constant.entry.enumeration.EDCType
     */
    @Field(type = String.class)
    public static final String PARAM_EDC_TYPE = "edcType";
    /**
     * @see com.pax.us.pay.ui.constant.entry.enumeration.SubTransType
     */
    @Field(type = String.class)
    public static final String PARAM_SUB_TRANS_TYPE = "subTransType";
    @Field(type = long.class)
    public static final String PARAM_TRANS_NUMBER = "transNumber";
    @Field(type = String[].class)
    public static final String PARAM_OPTIONS = "options";
    /**
     * @see com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType
     */
    @Field(type = String.class)
    public static final String PARAM_CURRENCY = "currency";
    @Field(type = long.class)
    public static final String PARAM_BASE_AMOUNT = "baseAmount";
    @Field(type = long.class)
    public static final String PARAM_TOTAL_AMOUNT = "totalAmount";
    @Field(type = String.class)
    public static final String PARAM_AMOUNT_MESSAGE = "amountMessage";
    @Field(type = String.class)
    public static final String PARAM_TIP_NAME = "tipName";
    @Field(type = String[].class)
    public static final String PARAM_TIP_OPTIONS = "tipOptions";
    @Field(type = String[].class)
    public static final String PARAM_TIP_RATE_OPTIONS = "tipRateOptions";
    @Field(type = String[].class)
    public static final String PARAM_CASHBACK_OPTIONS = "cashbackOptions";
    @Field(type = String[].class)
    public static final String PARAM_CASHBACK_RATE_OPTIONS = "cashbackRateOptions";
    @Field(type = boolean.class)
    public static final String PARAM_ENABLE_OTHER_PROMPT = "enableOtherPrompt";
    @Field(type = long.class)
    public static final String PARAM_TIMEOUT = "timeout";
    @Field(type = String.class)
    public static final String PARAM_PRINT_STATUS = "printStatus";
    /**
     * @see com.pax.us.pay.ui.constant.entry.enumeration.PinStyles
     */
    @Field(type = String.class)
    public static final String PARAM_PIN_STYLES = "pinStyles";
    @Field(type = boolean.class)
    public static final String PARAM_IS_ONLINE_PIN = "isOnlinePin";
    @Field(type = String.class)
    public static final String PARAM_PIN_RANGE = "pinRange";
    @Field(type = boolean.class)
    public static final String PARAM_ENABLE_CANCEL = "enableCancel";
    /**
     * @see com.pax.us.pay.ui.constant.entry.enumeration.CardType
     */
    @Field(type = String.class)
    public static final String PARAM_CARD_TYPE = "cardType";
    @Field(type = boolean.class)
    public static final String PARAM_ANIMATION_SUPPORT = "animationSupport";
    @Field(type = boolean.class)
    public static final String PARAM_SOUND_SUPPORT = "soundSupport";
    @Field(type = String.class)
    public static final String PARAM_SOUND_URI = "soundUri";
    @Field(type = String.class)
    public static final String PARAM_VCODE_NAME = "vcodeName";
    /**
     * @see com.pax.us.pay.ui.constant.entry.enumeration.PanStyles
     */
    @Field(type = String.class)
    public static final String PARAM_PAN_STYLES = "panStyles";
    @Field(type = boolean.class)
    public static final String PARAM_IS_EXTERNAL_PINPAD = "external";
    /**
     * @see com.pax.us.pay.ui.constant.entry.enumeration.InterfaceStyles
     */
    @Field(type = String.class)
    public static final String PARAM_INTERFACE_STYLES = "interfaceStyles";
    @Field(type = String.class)
    public static final String PARAM_MERCHANT_NAME = "merchantName";
    @Field(type = boolean.class)
    public static final String PARAM_SHOW_VIRTUAL_PINPAD = "showVirtualPinPad";
    /**
     * @see com.pax.us.pay.ui.constant.entry.enumeration.AdminPasswordType
     */
    @Field(type = String.class)
    public static final String PARAM_ADMIN_PASSWORD_TYPE = "adminPasswordType";
    @Field(type = String.class)
    public static final String PARAM_EXCHANGE_RATE = "exchangeRate";
    @Field(type = String.class)
    public static final String PARAM_CURRENCY_ALPHA_CODE = "currencyAlpCode";
    @Field(type = String.class)
    public static final String PARAM_MARGIN = "margin";
    @Field(type = String.class)
    public static final String PARAM_FOREIGN_AMOUNT_MESSAGE = "foreignAmountMessage";
    @Field(type = boolean.class)
    public static final String PARAM_CONFIRM_WITH_CURRENCY = "confirmWithCurrency";
    @Field(type = boolean.class)
    public static final String PARAM_ENABLE_NO_TIP_SELECTION = "enableNoTipSelection";
    
    @Field(type = String[].class)
    public static final String PARAM_INSTALLMENT_PAYMENT_AMOUNTS = "installmentPaymentAmounts";
    @Field(type = String[].class)
    public static final String PARAM_INSTALLMENT_PAYMENT_TERMS_AND_CONDITIONS = "termsAndConditions";
    @Field(type = String[].class)
    public static final String PARAM_INSTALLMENT_PAYMENT_TOTAL_FEES = "totalFees";
    @Field(type = String[].class)
    public static final String PARAM_INSTALLMENT_PAYMENT_NUMBER_OF_INSTALLMENTS = "numberOfInstallmens";
    @Field(type = String[].class)
    public static final String PARAM_INSTALLMENT_PAYMENT_FREQUENCY_OF_INSTALLMENTS = "frequencyOfInstallments";
    @Field(type = String[].class)
    public static final String PARAM_INSTALLMENT_PAYMENT_TOTAL_AMOUNT_INCLUSIVE_FEES = "totalAmountInclusiveFees";
    @Field(type = String[].class)
    public static final String PARAM_INSTALLMENT_PAYMENT_PLAN_CURRENCIES = "CURRENCIES";
    @Field(type = String[].class)
    public static final String PARAM_INSTALLMENT_PAYMENT_PLAN_ID = "planId";
    
    @Field(type = boolean.class)
    public static final String PARAM_HAS_PHYSICAL_KEYBOARD = "hasPhyKeyboard";
    
    @Field(type = boolean.class)
    public static final String PARAM_ENABLE_SWIPE = "enableSwipe";
    @Field(type = boolean.class)
    public static final String PARAM_ENABLE_INSERT = "enableInsert";
    @Field(type = boolean.class)
    public static final String PARAM_ENABLE_TAP = "enableTap";
    @Field(type = boolean.class)
    public static final String PARAM_ENABLE_SCAN = "enableScan";
    @Field(type = boolean.class)
    public static final String PARAM_ENABLE_MANUAL = "enableManualEntry";
    @Field(type = boolean.class)
    public static final String PARAM_ENABLE_APPLEPAY = "enableApplePay";
    @Field(type = boolean.class)
    public static final String PARAM_ENABLE_GOOGLEPAY = "enableGooglePay";
    @Field(type = boolean.class)
    public static final String PARAM_ENABLE_SUMSUNGPAY = "enableSumsungPay";
    @Field(type = boolean.class)
    public static final String PARAM_ENABLE_NFCPAY = "enableNFCPay";
    @Field(type = boolean.class)
    public static final String PARAM_ENABLE_LASER_SCAN = "enableLaserScan";
    @Field(type = boolean.class)
    public static final String PARAM_ENABLE_CONTACTLESS_LIGHT = "enableContactlessLight";
    
    @Field(type = String[].class)
    public static final String PARAM_INFORMATION_KEY = "informationKey";
    @Field(type = Boolean[].class)
    public static final String PARAM_INFORMATION_VALUE = "informationValue";
    
    @Field(type = String.class)
    public static final String PARAM_TITLE = "title";
//    public static final String PARAM_USER_MESSAGE = "userMessage";
//    public static final String PARAM_PRIMARY_AMOUNT = "primaryAmount";
//    public static final String PARAM_CONVENIENCE_FEE = "convenienceFee";
//    public static final String PARAM_SERVICE_FEE = "serviceFee";
//    public static final String PARAM_TOTAL_FEE = "totalFee";
//
    @Field(type = long.class)
    public static final String PARAM_BALANCE = "balance";


    /**
     * PARAM_TRANS_MODE : transaction mode <br>
     * enum: {@link com.pax.us.pay.ui.constant.entry.enumeration.TransMode}
     */
    public static final String PARAM_CARD_NUMBER = "cardNumber";
    public static final String PARAM_BATCH_NUMBER = "batchNumber";
    public static final String PARAM_ORDER_NUMBER = "orderNumber";
    public static final String PARAM_TIP1 = "tip1";
    public static final String PARAM_TIP2 = "tip2";
    public static final String PARAM_TIP3 = "tip3";
    @Field(type = String[].class)
    public static final String PARAM_TIP_NAMES = "tipNames";
    @Field(type = String[].class)
    public static final String PARAM_TIP_AMOUNTS = "tipAmounts";
    
    @Field(type = long.class)
    public static final String PARAM_APPROVED_AMOUNT = "approvedAmount";
    public static final String PARAM_BASE_POINTS = "basePoints";
    public static final String PARAM_TOTAL_POINTS = "totalPoints";
    public static final String PARAM_APPROVED_POINTS = "approvedPoints";
    public static final String PARAM_TAX = "tax";
    public static final String PARAM_CASH_BACK = "cashBack";
    public static final String PARAM_MERCHANT_FEE = "merchantFee";
    @Field(type = String.class)
    public static final String PARAM_SURCHARGE_FEE_NAME = "surchargeFeeName";
    @Field(type = long.class)
    public static final String PARAM_SURCHARGE_FEE = "surchargeFee";
    @Field(type = long.class)
    public static final String PARAM_SERVICE_FEE = "serviceFee";
    @Field(type = String.class)
    public static final String PARAM_SERVICE_FEE_NAME = "serviceFeeName";
//    public static final String PARAM_PRIMARY_AMOUNT = "primaryAmount";
    @Field(type = boolean.class)
    public static final String PARAM_ENABLE_BYPASS = "enableBypass";
    
    /**
     * @see com.pax.us.pay.ui.constant.entry.enumeration.FSAAmountType
     */
    @Field(type = String[].class)
    public static final String PARAM_FSA_AMOUNT_OPTIONS = "fsaAmountOptions";
    
    public static final String PARAM_STATUS = "status";
    public static final String PARAM_CLERK_ID = "clerkID";
    public static final String PARAM_CLERK_NAME = "clerkName";
    public static final String PARAM_INVOICE_NUMBER = "invoiceNumber";
    public static final String PARAM_PO_NUMBER = "poNumber";
    public static final String PARAM_TABLE_NUMBER = "tableNumber";
    public static final String PARAM_GUESTS_NUMBER = "guestsNumber";
    public static final String PARAM_CUST_CODE = "custCode";
    public static final String PARAM_MERCHANT_TAX_ID = "merchantTaxID";
    public static final String PARAM_PROD_DESC = "prodDesc";
    public static final String PARAM_TAX_EXEMPT_ID = "taxExemptID";
    public static final String PARAM_REF_NUMBER = "refNumber";
    public static final String PARAM_AUTH_CODE = "authCode";
    public static final String PARAM_RESPONSE_STATUS = "responseStatus";
    /**
     * @see com.pax.us.pay.ui.constant.entry.enumeration.InputType
     */
    @Field(type = String.class)
    public static final String PARAM_EINPUT_TYPE = "eInputType";
//    public static final String PARAM_MAX_VALUE = "maxValue";
//    public static final String PARAM_MIN_VALUE = "minValue";
    @Field(type = String.class)
    public static final String PARAM_MAX_LENGTH = "maxLength";
    @Field(type = String.class)
    public static final String PARAM_MIN_LENGTH = "minLength";
    /**
     * @see com.pax.us.pay.ui.constant.entry.enumeration.UnitType
     */
    @Field(type = String.class)
    public static final String PARAM_TIP_UNIT = "tipUnit";
    @Field(type = long.class)
    public static final String PARAM_AMOUNT_UNIT = "amountUnit";
    @Field(type = String.class)
    public static final String PARAM_VALUE_PATTERN = "valuePattern";
    
    @Field(type = String.class)
    public static final String PARAM_FLEET_CUSTOMER_DATA_PATTERN = "fleetCustomerDataPattern";
    @Field(type = String.class)
    public static final String PARAM_FLEET_DEPARTMENT_NUMBER_PATTERN = "fleetDepartmentNumberPattern";
    @Field(type = String.class)
    public static final String PARAM_FLEET_USER_ID_PATTERN = "fleetUserIdPattern";
    @Field(type = String.class)
    public static final String PARAM_FLEET_VEHICLE_ID_PATTERN = "fleetVehicleIdPattern";
    @Field(type = String.class)
    public static final String PARAM_FLEET_VEHICLE_NUMBER_PATTERN = "fleetVehicleNumberPattern";
    @Field(type = String.class)
    public static final String PARAM_FLEET_JOB_NUMBER_PATTERN = "fleetJobNumberPattern";
    @Field(type = String.class)
    public static final String PARAM_FLEET_ODOMETER_PATTERN = "fleetOdometerPattern";
    @Field(type = String.class)
    public static final String PARAM_FLEET_DRIVER_ID_PATTERN = "fleetDriverIdPattern";
    @Field(type = String.class)
    public static final String PARAM_FLEET_LICENSE_NUMBER_PATTERN = "fleetLicenseNumberPattern";
    
    @Field(type = String.class)
    public static final String PARAM_ZIP_CODE_PATTERN = "zipCodePattern";
    @Field(type = String.class)
    public static final String PARAM_ADDRESS_PATTERN = "addressPattern";
    @Field(type = String.class)
    public static final String PARAM_QR_CODE_CONTENT = "qrCodeContent";

    /**
     * PARAM_TRANS_STATUS : transaction status <br>
     * To Do : enum: upload, offline, voided, Adjusted ??????
     */
    public static final String PARAM_TRANS_STATUS = "transStatus";
    public static final String PARAM_AVS_RESPONSE = "avsResponse";
    public static final String PARAM_ENTRY_MODE = "entryMode";
    public static final String PARAM_FOOD_STAMPS_BALANCE = "foodStampsBalance";
    public static final String PARAM_CASH_BENEFIT_BALANCE = "cashBenefitBalance";
    
    @Field(type = String.class)
    public static final String PARAM_RECEIPT_URI = "receiptUri";
    @Field(type = String.class)
    public static final String PARAM_EULA_URI = "eulaUri";
    /**
     * @see com.pax.us.pay.ui.constant.entry.enumeration.StartType
     */
    @Field(type = String.class)
    public static final String PARAM_START_TYPE = "startType";
    @Field(type = String.class)
    public static final String PARAM_TRANS_URL = "transUri";
    @Field(type = String.class)
    public static final String PARAM_TRANS_SELECTION = "transSelection";
    @Field(type = String.class)
    public static final String PARAM_TRANS_SELECTION_ARGUMENTS = "transSelectionArguments";

    //POSLink parameter
    /**
     * @see com.pax.us.pay.ui.constant.entry.enumeration.ManageUIConst.ContinuousScreen
     */
    @Field(type = String.class)
    public static final String PARAM_CONTINUE_SCREEN = "continueScreen";
    @Field(type = String.class)
    public static final String PARAM_MESSAGE_1 = "message1";
    @Field(type = String.class)
    public static final String PARAM_MESSAGE_2 = "message2";
    @Field(type = String.class)
    public static final String PARAM_INPUT_TYPE = "inputType";
    @Field(type = String.class)
    public static final String PARAM_DEFAULT_VALUE = "defaultValue";
    @Field(type = String[].class)
    public static final String PARAM_LABELS = "labels";
    /**
     * @see com.pax.us.pay.ui.constant.entry.enumeration.ManageUIConst.LabelProperty
     */
    @Field(type = String[].class)
    public static final String PARAM_LABELS_PROPERTY = "labelsProperty";
    /**
     * @see com.pax.us.pay.ui.constant.entry.enumeration.ManageUIConst.ButtonType
     */
    @Field(type = String.class)
    public static final String PARAM_BUTTON_TYPE = "buttonType";
    @Field(type = String.class)
    public static final String PARAM_TAX_LINE = "taxLine";
    @Field(type = String.class)
    public static final String PARAM_TOTAL_LINE = "totalLine";
    @Field(type = String.class)
    public static final String PARAM_IMAGE_URL = "imageURL";
    @Field(type = String.class)
    public static final String PARAM_IMAGE_DESC = "imageDesc";
    /**
     * TODO
     * @see com.pax.us.pay.ui.constant.bean.info.MsgInfoWrapper
     */
    public static final String PARAM_MESSAGE_LIST = "messageList";
    @Field(type = String.class)
    public static final String PARAM_CURRENCY_SYMBOL = "currencySymbol";
    @Field(type = String.class)
    public static final String PARAM_BUTTON_1_NAME = "button1Name";
    @Field(type = String.class)
    public static final String PARAM_BUTTON_1_COLOR = "button1Color";
    @Field(type = String.class)
    public static final String PARAM_BUTTON_1_KEY = "button1Key";
    @Field(type = String.class)
    public static final String PARAM_BUTTON_2_NAME = "button2Name";
    @Field(type = String.class)
    public static final String PARAM_BUTTON_2_COLOR = "button2Color";
    @Field(type = String.class)
    public static final String PARAM_BUTTON_2_KEY = "button2Key";
    @Field(type = String.class)
    public static final String PARAM_BUTTON_3_NAME = "button3Name";
    @Field(type = String.class)
    public static final String PARAM_BUTTON_3_COLOR = "button3Color";
    @Field(type = String.class)
    public static final String PARAM_BUTTON_3_KEY = "button3key";
    @Field(type = String.class)
    public static final String PARAM_TEXT = "text";
    /**
     * FIXME String but need to be boolean
     */
    public static final String PARAM_ENABLE_HARD_KEY = "enableHardKey";
    /**
     * FIXME String but need to be boolean String array
     */
    public static final String PARAM_HARD_KEY_LIST = "hardKeyList";
    /**
     * HORIZONTAL_SIGNATURE_BOX = 1;
     * VERTICAL_SIGNATURE_BOX = 2;
     */
    @Field(type = long.class)
    public static final String PARAM_SIGN_BOX = "signBox";
    public static final String PARAM_BARCODE_TYPE = "barcodeType";
    public static final String PARAM_BARCODE_DATA = "barcodeData";
    @Field(type = String.class)
    public static final String PARAM_SIGNLINE1 = "signLine1";
    @Field(type = String.class)
    public static final String PARAM_SIGNLINE2 = "signLine2";
    public static final String PARAM_INPUT_TEXT_TITLE = "inputTextTitle";

    /**
     * Accessibility mode
     * Boolean String Y/N
     */
    @Field(type = String.class)
    public static final String PARAM_ACCESSIBILITY_PIN_PAD_MODE = "accessibilityPinPadMode";

    //internal parameter
    /*
    * Type：Boolean
    * Action: ACTION_CONFIRM_BATCH_CLOSE
    * */
    @Field(type = boolean.class)
    public static final String PARAM_INTERNAL_NICKNAME_FLAG = "internalNicknameFlag";

}
