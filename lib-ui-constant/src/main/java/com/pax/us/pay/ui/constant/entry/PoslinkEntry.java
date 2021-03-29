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
     * input: {@link EntryExtraData#PARAM_MESSAGE_1} } <br>
     * input: {@link EntryExtraData#PARAM_TITLE} } <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE_2} <br>
     * input: {@link EntryExtraData#PARAM_TOP_DOWN} <br>
     * input: {@link EntryExtraData#PARAM_TAX_LINE} <br>
     * input: {@link EntryExtraData#PARAM_TOTAL_LINE} <br>
     * input: {@link EntryExtraData#PARAM_IMAGE_URL} <br>
     * input: {@link EntryExtraData#PARAM_IMAGE_DESC} <br>
     * input: {@link EntryExtraData#PARAM_LINE_ITEM_ACTION} <br>
     * input: {@link EntryExtraData#PARAM_ITEM_INDEX} <br>
     */
    public static final String ACTION_SHOW_MESSAGE = "com.pax.us.pay.action.SHOW_MESSAGE";

} 