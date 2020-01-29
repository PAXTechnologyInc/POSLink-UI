package com.pax.us.pay.ui.constant.entry;

public class ConfirmationEntry {
    public static final String CATEGORY = "com.pax.us.pay.ui.category.CONFIRMATION";

    /**
     * The intent action of reverse partially approval <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * request: {@link EntryRequest#PARAM_CONFIRMED} <br>
     */
    public static final String ACTION_CONFIRM_UNIFIED_MESSAGE = "com.pax.us.pay.action.CONFIRM_UNIFIED_MESSAGE";


    /**
     * The intent action of reverse partially approval <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_CURRENCY} <br>
     * input: {@link EntryExtraData#PARAM_TOTAL_AMOUNT} <br>
     * request: {@link EntryRequest#PARAM_CONFIRMED} <br>
     */
    public static final String ACTION_REVERSE_PARTIAL_APPROVAL = "com.pax.us.pay.action.REVERSE_PARTIAL_APPROVAL";


    /**
     * The intent action of supplement partially approval <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_CURRENCY} <br>
     * input: {@link EntryExtraData#PARAM_TOTAL_AMOUNT} <br>
     * request: {@link EntryRequest#PARAM_CONFIRMED} <br>
     */
    public static final String ACTION_SUPPLEMENT_PARTIAL_APPROVAL = "com.pax.us.pay.action.SUPPLEMENT_PARTIAL_APPROVAL";

    /**
     * The intent action of select the transaction online retry or offline <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * request: {@link EntryRequest#PARAM_CONFIRMED} <br>
     */
    public static final String ACTION_CONFIRM_ONLINE_RETRY_OFFLINE = "com.pax.us.pay.action.CONFIRM_ONLINE_RETRY_OFFLINE";

    /**
     * The intent action of select the credit type <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * request: {@link EntryRequest#PARAM_CONFIRMED} <br>
     */
    public static final String ACTION_CONFIRM_DEBIT_TRANS = "com.pax.us.pay.action.CONFIRM_DEBIT_TRANS";

    /**
     * The intent action of check card present <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * request: {@link EntryRequest#PARAM_CONFIRMED} <br>
     */
    public static final String ACTION_CHECK_CARD_PRESENT = "com.pax.us.pay.action.CONFIRM_CARD_PRESENT";

    /**
     * The intent action of check deactivate warning <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * request: {@link EntryRequest#PARAM_CONFIRMED} <br>
     */
    public static final String ACTION_CHECK_DEACTIVATE_WARN = "com.pax.us.pay.action.CONFIRM_DEACTIVATE_WARN";


    /**
     * The intent action of batch close approval <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * request: {@link EntryRequest#PARAM_CONFIRMED} <br>
     */
    public static final String ACTION_CONFIRM_BATCH_CLOSE = "com.pax.us.pay.action.CONFIRM_BATCH_CLOSE";


    /**
     * The intent action of Close W/Untipped approval <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * request: {@link EntryRequest#PARAM_CONFIRMED} <br>
     */
    public static final String ACTION_CONFIRM_UNTIPPED = "com.pax.us.pay.action.CONFIRM_UNTIPPED";

    /**
     * The intent action of Duplicate transaction approval <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * request: {@link EntryRequest#PARAM_CONFIRMED} <br>
     */
    public static final String ACTION_CONFIRM_DUPLICATE_TRANS = "com.pax.us.pay.action.CONFIRM_DUPLICATE_TRANS";

    /**
     * The intent action of surcharge fee approval <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_SURCHARGE_FEE_NAME} String <br>
     * input: {@link EntryExtraData#PARAM_CURRENCY} <br>
     * input: {@link EntryExtraData#PARAM_TOTAL_AMOUNT} <br>
     * input: {@link EntryExtraData#PARAM_SURCHARGE_FEE} long <br>
     * request: {@link EntryRequest#PARAM_CONFIRMED} <br>
     */
    public static final String ACTION_CONFIRM_SURCHARGE_FEE = "com.pax.us.pay.action.CONFIRM_SURCHARGE_FEE";

    /**
     * The intent action of surcharge fee approval <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_PRINT_STATUS}:  enum: {@link com.pax.us.pay.ui.constant.entry.enumeration.PrintStatusType}<br>
     * request: {@link EntryRequest#PARAM_CONFIRMED} <br>
     */
    public static final String ACTION_CONFIRM_PRINTER_STATUS = "com.pax.us.pay.action.CONFIRM_PRINTER_STATUS";

    /**
     * The intent action of upload transaction approval <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * request: {@link EntryRequest#PARAM_CONFIRMED} <br>
     */
    public static final String ACTION_CONFIRM_UPLOAD_TRANS = "com.pax.us.pay.action.CONFIRM_UPLOAD_TRANS";

    /**
     * The intent action of retry upload transaction approval <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * request: {@link EntryRequest#PARAM_CONFIRMED} <br>
     */
    public static final String ACTION_CONFIRM_UPLOAD_RETRY = "com.pax.us.pay.action.CONFIRM_UPLOAD_RETRY";

    /**
     * The intent action of print failed transaction <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * request: {@link EntryRequest#PARAM_CONFIRMED} <br>
     */
    public static final String ACTION_CONFIRM_PRINT_FAILED_TRANS = "com.pax.us.pay.action.CONFIRM_PRINT_FAILED_TRANS";

    /**
     * The intent action of print receipt approval <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * request: {@link EntryRequest#PARAM_CONFIRMED} <br>
     */
    public static final String ACTION_CONFIRM_PRINT_FPS = "com.pax.us.pay.action.CONFIRM_PRINT_FPS";


    /**
     * The intent action of delete SF transaction records approval <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * request: {@link EntryRequest#PARAM_CONFIRMED} <br>
     */
    public static final String ACTION_CONFIRM_DELETE_SF = "com.pax.us.pay.action.CONFIRM_DELETE_SF";

    /**
     * The intent action of print customer receipt approval <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * request: {@link EntryRequest#PARAM_CONFIRMED} YES: OK  NO:CANCEL  <br>
     */
    public static final String ACTION_CONFIRM_PRINT_CUSTOMER_COPY = "com.pax.us.pay.action.CONFIRM_PRINT_CUSTOMER_COPY";

    /**
     * The intent action of Batch close for Application update <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * request: {@link EntryRequest#PARAM_CONFIRMED} <br>
     */
    public static final String ACTION_CONFIRM_BATCH_FOR_APPLICATION_UPDATE = "com.pax.us.pay.action.CONFIRM_BATCH_FOR_APPLICATION_UPDATE";

    /**
     * The intent action of select the transaction online retry <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * request: {@link EntryRequest#PARAM_CONFIRMED} <br>
     */
    public static final String ACTION_CONFIRM_ONLINE_RETRY = "com.pax.us.pay.action.CONFIRM_ONLINE_RETRY";


    /**
     * The intent action of confirm adjust tip <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * request: {@link EntryRequest#PARAM_CONFIRMED} <br>
     */
    public static final String ACTION_CONFIRM_ADJUST_TIP = "com.pax.us.pay.action.CONFIRM_ADJUST_TIP";

    /**
     * The intent action of confirm card process result<br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * request: {@link EntryRequest#PARAM_CONFIRMED} <br>
     */
    public static final String ACTION_CONFIRM_CARD_PROCESS_RESULT = "com.pax.us.pay.action.CONFIRM_CARD_PROCESS_RESULT";


    /**
     * The intent action of confirm the receipt need to be signed or not<br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * request: {@link EntryRequest#PARAM_CONFIRMED} <br>
     */
    public static final String ACTION_CONFIRM_RECEIPT_SIGNATURE = "com.pax.us.pay.action.CONFIRM_RECEIPT_SIGNATURE";

}
