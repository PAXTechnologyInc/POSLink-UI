package com.pax.us.pay.ui.constant.entry;

public class InformationEntry {
    public static final String CATEGORY = "com.pax.us.pay.ui.category.CONFIRMATION";

    /**
     * The intent action of display fees
     * input: {@link EntryExtraData#PARAM_PACKAGE}
     * input: {@link EntryExtraData#PARAM_MESSAGE}
     * input: According the actually receiving message
     * request: None
     */
    public static final String ACTION_DISPLAY_FEES = "com.pax.us.pay.action.DISPLAY_FEES";

    /**
     * The intent action of print review
     */
    public static final String ACTION_PRINT_PREVIEW = "com.pax.us.pay.action.PRINT_PREVIEW";

    /**
     * The intent action of display balance
     * input: {@link EntryExtraData#PARAM_PACKAGE}
     * input: {@link EntryExtraData#PARAM_MESSAGE}
     * input: According the actually receiving message
     * request: None
     */
    public static final String ACTION_DISPLAY_BALANCE = "com.pax.us.pay.action.DISPLAY_BALANCE";

    /**
     * The intent action of display transaction information
     * input: {@link EntryExtraData#PARAM_PACKAGE}
     * input: {@link EntryExtraData#PARAM_MESSAGE}
     * input: According the actually receiving message
     * request: None
     */
    public static final String ACTION_DISPLAY_TRANS_INFORMATION = "com.pax.us.pay.action.DISPLAY_TRANS_INFORMATION";


}
