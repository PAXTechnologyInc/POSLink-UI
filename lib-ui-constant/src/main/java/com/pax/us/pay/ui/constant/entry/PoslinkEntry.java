package com.pax.us.pay.ui.constant.entry;

public class PoslinkEntry {
    public static final String CATEGORY = "com.pax.us.pay.ui.category.POSLINK";

    /**
     * The intent action of show dialog <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_TITLE} } <br>
     * input: {@link EntryExtraData#PARAM_OPTIONS} <br>
     * input: {@link EntryExtraData#PARAM_TIMEOUT} <br>
     * input: {@link EntryExtraData#PARAM_CONTINUE_SCREEN} <br>
     * request: {@link EntryRequest#PARAM_INDEX} <br>
     */
    public static final String ACTION_SHOW_DIALOG = "com.pax.us.pay.action.SHOW_DIALOG";


    /**
     * The intent action of show dialog <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_TITLE} } <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE_1} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE_2} <br>
     * input: {@link EntryExtraData#PARAM_TIMEOUT} <br>
     * request: {@link EntryRequest#PARAM_INDEX} <br>
     */
    public static final String ACTION_SHOW_THANK_YOU = "com.pax.us.pay.action.SHOW_THANK_YOU";

    /**
     * The intent action of input text<br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_TITLE} } <br>
     * input: {@link EntryExtraData#PARAM_INPUT_TYPE} <br>
     * input: {@link EntryExtraData#PARAM_MIN_LENGTH} <br>
     * input: {@link EntryExtraData#PARAM_MAX_LENGTH} <br>
     * input: {@link EntryExtraData#PARAM_DEFAULT_VALUE} <br>
     * input: {@link EntryExtraData#PARAM_TIMEOUT} <br>
     * input: {@link EntryExtraData#PARAM_CONTINUE_SCREEN} <br>
     * request: {@link EntryRequest#PARAM_INPUT_VALUE} <br>
     */
    public static final String ACTION_INPUT_TEXT = "com.pax.us.pay.action.INPUT_TEXT";

    /**
     * The intent action of show dialog form <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_TITLE} } <br>
     * input: {@link EntryExtraData#PARAM_LABELS} <br>
     * input: {@link EntryExtraData#PARAM_LABELS_PROPERTY} <br>
     * input: {@link EntryExtraData#PARAM_BUTTON_TYPE} <br>
     * input: {@link EntryExtraData#PARAM_TIMEOUT} <br>
     * input: {@link EntryExtraData#PARAM_CONTINUE_SCREEN} <br>
     * request: {@link EntryRequest#PARAM_LABEL_SELECTED} <br>
     */
    public static final String ACTION_SHOW_DIALOG_FORM = "com.pax.us.pay.action.SHOW_DIALOG_FORM";


    /**
     * The intent action of show dialog form <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_TITLE} } <br>
     * input: {@link EntryExtraData#PARAM_TAX_LINE} <br>
     * input: {@link EntryExtraData#PARAM_TOTAL_LINE} <br>
     * input: {@link EntryExtraData#PARAM_IMAGE_URL} <br>
     * input: {@link EntryExtraData#PARAM_IMAGE_DESC} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE_LIST} <br>
     */
    public static final String ACTION_SHOW_MESSAGE = "com.pax.us.pay.action.SHOW_MESSAGE";


    /**
     * The intent action of show dialog form <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_TITLE} } <br>
     * input: {@link EntryExtraData#PARAM_TAX_LINE} <br>
     * input: {@link EntryExtraData#PARAM_TOTAL_LINE} <br>
     * input: {@link EntryExtraData#PARAM_CURRENCY_SYMBOL} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE_LIST} <br>
     */
    public static final String ACTION_SHOW_ITEM = "com.pax.us.pay.action.SHOW_ITEM";


    /**
     * The intent action of show dialog form <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_TITLE} <br>
     * input: {@link EntryExtraData#PARAM_TEXT} <br>
     * input: {@link EntryExtraData#PARAM_BUTTON_1_NAME} <br>
     * input: {@link EntryExtraData#PARAM_BUTTON_1_COLOR} <br>
     * input: {@link EntryExtraData#PARAM_BUTTON_1_KEY} <br>
     * input: {@link EntryExtraData#PARAM_BUTTON_2_NAME} <br>
     * input: {@link EntryExtraData#PARAM_BUTTON_2_COLOR} <br>
     * input: {@link EntryExtraData#PARAM_BUTTON_2_KEY} <br>
     * input: {@link EntryExtraData#PARAM_BUTTON_3_NAME} <br>
     * input: {@link EntryExtraData#PARAM_BUTTON_3_COLOR} <br>
     * input: {@link EntryExtraData#PARAM_BUTTON_3_KEY} <br>
     * input: {@link EntryExtraData#PARAM_TIMEOUT} <br>
     * input: {@link EntryExtraData#PARAM_ENABLE_HARD_KEY} <br>
     * input: {@link EntryExtraData#PARAM_HARD_KEY_LIST} <br>
     * input: {@link EntryExtraData#PARAM_CONTINUE_SCREEN} <br>
     * input: {@link EntryExtraData#PARAM_BARCODE_TYPE} <br>
     * input: {@link EntryExtraData#PARAM_BARCODE_DATA} <br>
     * request: {@link EntryRequest#PARAM_BUTTON_NUMBER} <br>
     */
    public static final String ACTION_SHOW_TEXT_BOX = "com.pax.us.pay.action.SHOW_TEXT_BOX";

    /**
     * The intent action of show signature box <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_TITLE} <br>
     * input: {@link EntryExtraData#PARAM_TEXT} <br>
     * input: {@link EntryExtraData#PARAM_TIMEOUT} <br>
     * request: {@link EntryRequest#PARAM_SIGN_STATUS} <br>
     * request: {@link EntryRequest#PARAM_SIGNATURE} <br>
     */
    public static final String ACTION_SHOW_SIGNATURE_BOX = "com.pax.us.pay.action.SHOW_SIGNATURE_BOX";
} 