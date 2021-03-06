package com.pax.us.pay.ui.constant.entry;

public class InformationEntry {
    public static final String CATEGORY = "com.pax.us.pay.ui.category.DETAILS";

    /**
     * The intent action of display transaction information <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_INFORMATION_KEY} <br>
     * input: {@link EntryExtraData#PARAM_INFORMATION_VALUE} <br>
     * request: None <br>
     */
    public static final String ACTION_DISPLAY_TRANS_INFORMATION = "com.pax.us.pay.action.DISPLAY_TRANS_INFORMATION";


    /**
     * The intent action of display transaction information <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_CARD_TYPE} <br>
     * input: {@link EntryExtraData#PARAM_ANIMATION_SUPPORT} <br>
     * input: {@link EntryExtraData#PARAM_SOUND_SUPPORT} <br>
     * request: None <br>
     */
    public static final String ACTION_DISPLAY_APPROVE_MESSAGE = "com.pax.us.pay.action.DISPLAY_APPROVE_MESSAGE";

    /**
     * The intent action of display visa installment transaction approved message <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * request: None <br>
     */
    public static final String ACTION_DISPLAY_VISA_INSTALLMENT_TRANSACTION_END = "com.pax.us.pay.action.DISPLAY_VISA_INSTALLMENT_TRANSACTION_END";

}
