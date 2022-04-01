package com.pax.us.pay.ui.constant.entry;

import com.pax.us.annotation.FieldAttr;

public final class EntryExtraData {
    
    @FieldAttr(type = String.class)
    public static final String PARAM_MESSAGE = "message";
    @FieldAttr(type = String.class)
    public static final String PARAM_PACKAGE = "senderPackage";
    /**
     * @see com.pax.us.pay.ui.constant.entry.enumeration.TransType
     */
    @FieldAttr(type = String.class)
    public static final String PARAM_TRANS_TYPE = "transType";
    @FieldAttr(type = String.class)
    public static final String PARAM_TRANS_TIME = "transTime";
    @FieldAttr(type = String.class)
    public static final String PARAM_TRANS_DATE = "transDate";
    @FieldAttr(type = String.class)
    public static final String PARAM_MERCHANT_ID = "merchantID";
    @FieldAttr(type = String.class)
    public static final String PARAM_TERMINAL_ID = "terminalID";
    /**
     * @see com.pax.us.pay.ui.constant.entry.enumeration.TransMode
     */
    @FieldAttr(type = String.class)
    public static final String PARAM_TRANS_MODE = "transMode";
    /**
     * @see com.pax.us.pay.ui.constant.entry.enumeration.EDCType
     */
    @FieldAttr(type = String.class)
    public static final String PARAM_EDC_TYPE = "edcType";
    /**
     * @see com.pax.us.pay.ui.constant.entry.enumeration.SubTransType
     */
    @FieldAttr(type = String.class)
    public static final String PARAM_SUB_TRANS_TYPE = "subTransType";
    @FieldAttr(type = long.class)
    public static final String PARAM_TRANS_NUMBER = "transNumber";
    @FieldAttr(type = String[].class)
    public static final String PARAM_OPTIONS = "options";
    /**
     * @see com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType
     */
    @FieldAttr(type = String.class)
    public static final String PARAM_CURRENCY = "currency";
    @FieldAttr(type = long.class)
    public static final String PARAM_BASE_AMOUNT = "baseAmount";
    @FieldAttr(type = long.class)
    public static final String PARAM_TOTAL_AMOUNT = "totalAmount";
    @FieldAttr(type = String.class)
    public static final String PARAM_AMOUNT_MESSAGE = "amountMessage";
    @FieldAttr(type = String.class)
    public static final String PARAM_TIP_NAME = "tipName";
    @FieldAttr(type = String[].class)
    public static final String PARAM_TIP_OPTIONS = "tipOptions";
    @FieldAttr(type = String[].class)
    public static final String PARAM_TIP_RATE_OPTIONS = "tipRateOptions";
    @FieldAttr(type = String[].class)
    public static final String PARAM_CASHBACK_OPTIONS = "cashbackOptions";
    @FieldAttr(type = String[].class)
    public static final String PARAM_CASHBACK_RATE_OPTIONS = "cashbackRateOptions";
    @FieldAttr(type = boolean.class)
    public static final String PARAM_ENABLE_OTHER_PROMPT = "enableOtherPrompt";
    @FieldAttr(type = long.class)
    public static final String PARAM_TIMEOUT = "timeout";
    @FieldAttr(type = String.class)
    public static final String PARAM_PRINT_STATUS = "printStatus";
    /**
     * @see com.pax.us.pay.ui.constant.entry.enumeration.PinStyles
     */
    @FieldAttr(type = String.class)
    public static final String PARAM_PIN_STYLES = "pinStyles";
    @FieldAttr(type = boolean.class)
    public static final String PARAM_IS_ONLINE_PIN = "isOnlinePin";
    @FieldAttr(type = String.class)
    public static final String PARAM_PIN_RANGE = "pinRange";
    @FieldAttr(type = boolean.class)
    public static final String PARAM_ENABLE_CANCEL = "enableCancel";
    /**
     * @see com.pax.us.pay.ui.constant.entry.enumeration.CardType
     */
    @FieldAttr(type = String.class)
    public static final String PARAM_CARD_TYPE = "cardType";
    @FieldAttr(type = boolean.class)
    public static final String PARAM_ANIMATION_SUPPORT = "animationSupport";
    @FieldAttr(type = boolean.class)
    public static final String PARAM_SOUND_SUPPORT = "soundSupport";
    @FieldAttr(type = String.class)
    public static final String PARAM_SOUND_URI = "soundUri";
    @FieldAttr(type = String.class)
    public static final String PARAM_VCODE_NAME = "vcodeName";
    /**
     * @see com.pax.us.pay.ui.constant.entry.enumeration.PanStyles
     */
    @FieldAttr(type = String.class)
    public static final String PARAM_PAN_STYLES = "panStyles";
    @FieldAttr(type = boolean.class)
    public static final String PARAM_IS_EXTERNAL_PINPAD = "external";
    /**
     * @see com.pax.us.pay.ui.constant.entry.enumeration.InterfaceStyles
     */
    @FieldAttr(type = String.class)
    public static final String PARAM_INTERFACE_STYLES = "interfaceStyles";
    @FieldAttr(type = String.class)
    public static final String PARAM_MERCHANT_NAME = "merchantName";
    @FieldAttr(type = boolean.class)
    public static final String PARAM_SHOW_VIRTUAL_PINPAD = "showVirtualPinPad";
    /**
     * @see com.pax.us.pay.ui.constant.entry.enumeration.AdminPasswordType
     */
    @FieldAttr(type = String.class)
    public static final String PARAM_ADMIN_PASSWORD_TYPE = "adminPasswordType";
    @FieldAttr(type = String.class)
    public static final String PARAM_EXCHANGE_RATE = "exchangeRate";
    @FieldAttr(type = String.class)
    public static final String PARAM_CURRENCY_ALPHA_CODE = "currencyAlpCode";
    @FieldAttr(type = String.class)
    public static final String PARAM_MARGIN = "margin";
    @FieldAttr(type = String.class)
    public static final String PARAM_FOREIGN_AMOUNT_MESSAGE = "foreignAmountMessage";
    @FieldAttr(type = boolean.class)
    public static final String PARAM_CONFIRM_WITH_CURRENCY = "confirmWithCurrency";
    @FieldAttr(type = boolean.class)
    public static final String PARAM_ENABLE_NO_TIP_SELECTION = "enableNoTipSelection";
    
    @FieldAttr(type = String[].class)
    public static final String PARAM_INSTALLMENT_PAYMENT_AMOUNTS = "installmentPaymentAmounts";
    @FieldAttr(type = String[].class)
    public static final String PARAM_INSTALLMENT_PAYMENT_TERMS_AND_CONDITIONS = "termsAndConditions";
    @FieldAttr(type = String[].class)
    public static final String PARAM_INSTALLMENT_PAYMENT_TOTAL_FEES = "totalFees";
    @FieldAttr(type = String[].class)
    public static final String PARAM_INSTALLMENT_PAYMENT_NUMBER_OF_INSTALLMENTS = "numberOfInstallmens";
    @FieldAttr(type = String[].class)
    public static final String PARAM_INSTALLMENT_PAYMENT_FREQUENCY_OF_INSTALLMENTS = "frequencyOfInstallments";
    @FieldAttr(type = String[].class)
    public static final String PARAM_INSTALLMENT_PAYMENT_TOTAL_AMOUNT_INCLUSIVE_FEES = "totalAmountInclusiveFees";
    @FieldAttr(type = String[].class)
    public static final String PARAM_INSTALLMENT_PAYMENT_PLAN_CURRENCIES = "CURRENCIES";
    @FieldAttr(type = String[].class)
    public static final String PARAM_INSTALLMENT_PAYMENT_PLAN_ID = "planId";
    
    @FieldAttr(type = boolean.class)
    public static final String PARAM_HAS_PHYSICAL_KEYBOARD = "hasPhyKeyboard";
    
    @FieldAttr(type = boolean.class)
    public static final String PARAM_ENABLE_SWIPE = "enableSwipe";
    @FieldAttr(type = boolean.class)
    public static final String PARAM_ENABLE_INSERT = "enableInsert";
    @FieldAttr(type = boolean.class)
    public static final String PARAM_ENABLE_TAP = "enableTap";
    @FieldAttr(type = boolean.class)
    public static final String PARAM_ENABLE_SCAN = "enableScan";
    @FieldAttr(type = boolean.class)
    public static final String PARAM_ENABLE_MANUAL = "enableManualEntry";
    @FieldAttr(type = boolean.class)
    public static final String PARAM_ENABLE_APPLEPAY = "enableApplePay";
    @FieldAttr(type = boolean.class)
    public static final String PARAM_ENABLE_GOOGLEPAY = "enableGooglePay";
    @FieldAttr(type = boolean.class)
    public static final String PARAM_ENABLE_SUMSUNGPAY = "enableSumsungPay";
    @FieldAttr(type = boolean.class)
    public static final String PARAM_ENABLE_NFCPAY = "enableNFCPay";
    @FieldAttr(type = boolean.class)
    public static final String PARAM_ENABLE_LASER_SCAN = "enableLaserScan";
    @FieldAttr(type = boolean.class)
    public static final String PARAM_ENABLE_CONTACTLESS_LIGHT = "enableContactlessLight";
    
    @FieldAttr(type = String[].class)
    public static final String PARAM_INFORMATION_KEY = "informationKey";
    @FieldAttr(type = Boolean[].class)
    public static final String PARAM_INFORMATION_VALUE = "informationValue";
    
    @FieldAttr(type = String.class)
    public static final String PARAM_TITLE = "title";
//    public static final String PARAM_USER_MESSAGE = "userMessage";
//    public static final String PARAM_PRIMARY_AMOUNT = "primaryAmount";
//    public static final String PARAM_CONVENIENCE_FEE = "convenienceFee";
//    public static final String PARAM_SERVICE_FEE = "serviceFee";
//    public static final String PARAM_TOTAL_FEE = "totalFee";
//
    @FieldAttr(type = long.class)
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
    @FieldAttr(type = String[].class)
    public static final String PARAM_TIP_NAMES = "tipNames";
    @FieldAttr(type = String[].class)
    public static final String PARAM_TIP_AMOUNTS = "tipAmounts";
    
    @FieldAttr(type = long.class)
    public static final String PARAM_APPROVED_AMOUNT = "approvedAmount";
    public static final String PARAM_BASE_POINTS = "basePoints";
    public static final String PARAM_TOTAL_POINTS = "totalPoints";
    public static final String PARAM_APPROVED_POINTS = "approvedPoints";
    public static final String PARAM_TAX = "tax";
    public static final String PARAM_CASH_BACK = "cashBack";
    public static final String PARAM_MERCHANT_FEE = "merchantFee";
    @FieldAttr(type = String.class)
    public static final String PARAM_SURCHARGE_FEE_NAME = "surchargeFeeName";
    @FieldAttr(type = long.class)
    public static final String PARAM_SURCHARGE_FEE = "surchargeFee";
    @FieldAttr(type = long.class)
    public static final String PARAM_SERVICE_FEE = "serviceFee";
    @FieldAttr(type = String.class)
    public static final String PARAM_SERVICE_FEE_NAME = "serviceFeeName";
//    public static final String PARAM_PRIMARY_AMOUNT = "primaryAmount";
    @FieldAttr(type = boolean.class)
    public static final String PARAM_ENABLE_BYPASS = "enableBypass";
    
    /**
     * @see com.pax.us.pay.ui.constant.entry.enumeration.FSAAmountType
     */
    @FieldAttr(type = String[].class)
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
    @FieldAttr(type = String.class)
    public static final String PARAM_EINPUT_TYPE = "eInputType";
//    public static final String PARAM_MAX_VALUE = "maxValue";
//    public static final String PARAM_MIN_VALUE = "minValue";
    @FieldAttr(type = String.class)
    public static final String PARAM_MAX_LENGTH = "maxLength";
    @FieldAttr(type = String.class)
    public static final String PARAM_MIN_LENGTH = "minLength";
    /**
     * @see com.pax.us.pay.ui.constant.entry.enumeration.UnitType
     */
    @FieldAttr(type = String.class)
    public static final String PARAM_TIP_UNIT = "tipUnit";
    @FieldAttr(type = long.class)
    public static final String PARAM_AMOUNT_UNIT = "amountUnit";
    @FieldAttr(type = String.class)
    public static final String PARAM_VALUE_PATTERN = "valuePattern";
    
    @FieldAttr(type = String.class)
    public static final String PARAM_FLEET_CUSTOMER_DATA_PATTERN = "fleetCustomerDataPattern";
    @FieldAttr(type = String.class)
    public static final String PARAM_FLEET_DEPARTMENT_NUMBER_PATTERN = "fleetDepartmentNumberPattern";
    @FieldAttr(type = String.class)
    public static final String PARAM_FLEET_USER_ID_PATTERN = "fleetUserIdPattern";
    @FieldAttr(type = String.class)
    public static final String PARAM_FLEET_VEHICLE_ID_PATTERN = "fleetVehicleIdPattern";
    @FieldAttr(type = String.class)
    public static final String PARAM_FLEET_VEHICLE_NUMBER_PATTERN = "fleetVehicleNumberPattern";
    @FieldAttr(type = String.class)
    public static final String PARAM_FLEET_JOB_NUMBER_PATTERN = "fleetJobNumberPattern";
    @FieldAttr(type = String.class)
    public static final String PARAM_FLEET_ODOMETER_PATTERN = "fleetOdometerPattern";
    @FieldAttr(type = String.class)
    public static final String PARAM_FLEET_DRIVER_ID_PATTERN = "fleetDriverIdPattern";
    @FieldAttr(type = String.class)
    public static final String PARAM_FLEET_LICENSE_NUMBER_PATTERN = "fleetLicenseNumberPattern";
    
    @FieldAttr(type = String.class)
    public static final String PARAM_ZIP_CODE_PATTERN = "zipCodePattern";
    @FieldAttr(type = String.class)
    public static final String PARAM_ADDRESS_PATTERN = "addressPattern";
    @FieldAttr(type = String.class)
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
    
    @FieldAttr(type = String.class)
    public static final String PARAM_RECEIPT_URI = "receiptUri";
    @FieldAttr(type = String.class)
    public static final String PARAM_EULA_URI = "eulaUri";
    /**
     * @see com.pax.us.pay.ui.constant.entry.enumeration.StartType
     */
    @FieldAttr(type = String.class)
    public static final String PARAM_START_TYPE = "startType";
    @FieldAttr(type = String.class)
    public static final String PARAM_TRANS_URL = "transUri";
    @FieldAttr(type = String.class)
    public static final String PARAM_TRANS_SELECTION = "transSelection";
    @FieldAttr(type = String.class)
    public static final String PARAM_TRANS_SELECTION_ARGUMENTS = "transSelectionArguments";

    //POSLink parameter
    /**
     * @see com.pax.us.pay.ui.constant.entry.enumeration.ManageUIConst.ContinuousScreen
     */
    @FieldAttr(type = String.class)
    public static final String PARAM_CONTINUE_SCREEN = "continueScreen";
    @FieldAttr(type = String.class)
    public static final String PARAM_MESSAGE_1 = "message1";
    @FieldAttr(type = String.class)
    public static final String PARAM_MESSAGE_2 = "message2";
    @FieldAttr(type = String.class)
    public static final String PARAM_INPUT_TYPE = "inputType";
    @FieldAttr(type = String.class)
    public static final String PARAM_DEFAULT_VALUE = "defaultValue";
    @FieldAttr(type = String[].class)
    public static final String PARAM_LABELS = "labels";
    /**
     * @see com.pax.us.pay.ui.constant.entry.enumeration.ManageUIConst.LabelProperty
     */
    @FieldAttr(type = String[].class)
    public static final String PARAM_LABELS_PROPERTY = "labelsProperty";
    /**
     * @see com.pax.us.pay.ui.constant.entry.enumeration.ManageUIConst.ButtonType
     */
    @FieldAttr(type = String.class)
    public static final String PARAM_BUTTON_TYPE = "buttonType";
    @FieldAttr(type = String.class)
    public static final String PARAM_TAX_LINE = "taxLine";
    @FieldAttr(type = String.class)
    public static final String PARAM_TOTAL_LINE = "totalLine";
    @FieldAttr(type = String.class)
    public static final String PARAM_IMAGE_URL = "imageURL";
    @FieldAttr(type = String.class)
    public static final String PARAM_IMAGE_DESC = "imageDesc";
    /**
     * TODO
     * @see com.pax.us.pay.ui.constant.bean.info.MsgInfoWrapper
     */
    public static final String PARAM_MESSAGE_LIST = "messageList";
    @FieldAttr(type = String.class)
    public static final String PARAM_CURRENCY_SYMBOL = "currencySymbol";
    @FieldAttr(type = String.class)
    public static final String PARAM_BUTTON_1_NAME = "button1Name";
    @FieldAttr(type = String.class)
    public static final String PARAM_BUTTON_1_COLOR = "button1Color";
    @FieldAttr(type = String.class)
    public static final String PARAM_BUTTON_1_KEY = "button1Key";
    @FieldAttr(type = String.class)
    public static final String PARAM_BUTTON_2_NAME = "button2Name";
    @FieldAttr(type = String.class)
    public static final String PARAM_BUTTON_2_COLOR = "button2Color";
    @FieldAttr(type = String.class)
    public static final String PARAM_BUTTON_2_KEY = "button2Key";
    @FieldAttr(type = String.class)
    public static final String PARAM_BUTTON_3_NAME = "button3Name";
    @FieldAttr(type = String.class)
    public static final String PARAM_BUTTON_3_COLOR = "button3Color";
    @FieldAttr(type = String.class)
    public static final String PARAM_BUTTON_3_KEY = "button3key";
    @FieldAttr(type = String.class)
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
    @FieldAttr(type = long.class)
    public static final String PARAM_SIGN_BOX = "signBox";
    public static final String PARAM_BARCODE_TYPE = "barcodeType";
    public static final String PARAM_BARCODE_DATA = "barcodeData";
    @FieldAttr(type = String.class)
    public static final String PARAM_SIGNLINE1 = "signLine1";
    @FieldAttr(type = String.class)
    public static final String PARAM_SIGNLINE2 = "signLine2";
    public static final String PARAM_INPUT_TEXT_TITLE = "inputTextTitle";

    /**
     * Accessibility mode
     * Boolean String Y/N
     */
    @FieldAttr(type = String.class)
    public static final String PARAM_ACCESSIBILITY_PIN_PAD_MODE = "accessibilityPinPadMode";

    //internal parameter
    /*
    * Type：Boolean
    * Action: ACTION_CONFIRM_BATCH_CLOSE
    * */
    @FieldAttr(type = boolean.class)
    public static final String PARAM_INTERNAL_NICKNAME_FLAG = "internalNicknameFlag";

}
