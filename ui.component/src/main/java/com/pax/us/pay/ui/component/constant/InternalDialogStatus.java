package com.pax.us.pay.ui.component.constant;

public class InternalDialogStatus {

    public static final String CATEGORY_INTERNAL = "com.pax.us.pay.category.INTERNAL";

    /**
     * param: {@link InternalDialogStatus#PARAM_MESSAGE}
     * param: {@link InternalDialogStatus#PARAM_POSITIVE}
     * param: {@link InternalDialogStatus#PARAM_NEGATIVE}
     */
    public static final String ACTION_PROMPT_DIALOG = "com.pax.us.pay.internal.action.PROMPT_DIALOG";

    /**
     * request:
     * param: {@link InternalDialogStatus#PARAM_TITLE}
     * param: {@link InternalDialogStatus#PARAM_ITEM_LIST}
     * <p>
     * response:
     * param: {@link InternalDialogStatus#ACTION_ITEM_SELECTED}
     */
    public static final String ACTION_PROMPT_SELECT_DIALOG = "com.pax.us.pay.internal.action.PROMPT_SELECT_DIALOG";
    public static final String ACTION_PROMPT_SELECT_DIALOG_RESPONSE = "com.pax.us.pay.internal.action.PROMPT_SELECT_DIALOG_RESPONSE";
    //Request keys
    public static final String PARAM_MESSAGE = "message";
    public static final String PARAM_POSITIVE = "positive";
    public static final String PARAM_NEGATIVE = "negative";

    public static final String PARAM_TITLE = "title";
    public static final String PARAM_ITEM_LIST = "itemList";
    //String
    public static final String PARAM_TIMEOUT = "timeout";

    //Select response, 0 = OK, -1 = aborted, -2 = timeout
    public static final String PARAM_RESPONSE_CODE = "response_code";

    public static final int RESPONSE_OK = 0;
    public static final int RESPONSE_ABORTED = -1;
    public static final int RESPONSE_TIMEOUT = -2;


    //response action
    public static final String ACTION_POSITIVE_SELECTED = "com.pax.us.pay.internal.action.POSITIVE_SELECTED";
    public static final String ACTION_NEGATIVE_SELECTED = "com.pax.us.pay.internal.action.NEGATIVE_SELECTED";

    public static final String PARAM_ITEM_SELECTED = "com.pax.us.pay.internal.action.ITEM_SELECTED";

}
