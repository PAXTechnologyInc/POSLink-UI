package com.pax.us.pay.ui.constant.entry;

public class InformationEntry {
    public static final String CATEGORY = "com.pax.us.pay.ui.category.CONFIRMATION";

    /**
     * The intent action of display fees <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_INFORMATION_KEY} <br>
     * input: {@link EntryExtraData#PARAM_INFORMATION_VALUE} <br>
     * request: None <br>
     */
    public static final String ACTION_DISPLAY_FEES = "com.pax.us.pay.action.DISPLAY_FEES";

    /**
     * The intent action of print review <br>
     */
    public static final String ACTION_PRINT_PREVIEW = "com.pax.us.pay.action.PRINT_PREVIEW";

    /**
     * The intent action of display balance <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_INFORMATION_KEY} <br>
     * input: {@link EntryExtraData#PARAM_INFORMATION_VALUE} <br>
     * request: None <br>
     */
    public static final String ACTION_DISPLAY_BALANCE = "com.pax.us.pay.action.DISPLAY_BALANCE";

    /**
     * The intent action of display transaction information <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_INFORMATION_KEY} <br>
     * input: {@link EntryExtraData#PARAM_INFORMATION_VALUE} <br>
     * request: None <br>
     */
    public static final String ACTION_DISPLAY_TRANS_INFORMATION = "com.pax.us.pay.action.DISPLAY_TRANS_INFORMATION";


}
