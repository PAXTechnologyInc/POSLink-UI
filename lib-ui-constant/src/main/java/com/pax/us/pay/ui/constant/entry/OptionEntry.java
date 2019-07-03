package com.pax.us.pay.ui.constant.entry;

import com.pax.us.pay.ui.constant.entry.enumeration.ConfirmationType;

public class OptionEntry {
    public static final String CATEGORY = "com.pax.us.pay.ui.category.OPTION";
    /**
     * The intent action of Select Ebt type
     * input: {@link EntryExtraData#PARAM_PACKAGE}
     * input: {@link EntryExtraData#PARAM_MESSAGE}
     * input: {@link EntryExtraData#PARAM_OPTIONS}: enum: {@link com.pax.us.pay.ui.constant.entry.enumeration.EBTType}
     * request: {@link EntryRequest#PARAM_INDEX}
     */
    public static final String ACTION_SELECT_EBT_TYPE = "com.pax.us.pay.action.SELECT_EBT_TYPE";

    /**
     * The intent action of Select by pass
     * input: {@link EntryExtraData#PARAM_PACKAGE}
     * input: {@link EntryExtraData#PARAM_MESSAGE}
     * input: {@link EntryExtraData#PARAM_OPTIONS}: enum: {@link com.pax.us.pay.ui.constant.entry.enumeration.BypassReason}
     * request: {@link EntryRequest#PARAM_INDEX}
     */
    public static final String ACTION_SELECT_BY_PASS = "com.pax.us.pay.action.SELECT_BY_PASS";

    /**
     * The intent action of check card present
     * input: {@link EntryExtraData#PARAM_PACKAGE}
     * input: {@link EntryExtraData#PARAM_MESSAGE}
     * input: {@link EntryExtraData#PARAM_OPTIONS}: enum: {@link ConfirmationType}
     * request: {@link EntryRequest#PARAM_INDEX}
     */
    public static final String ACTION_CHECK_CARD_PRESENT = "com.pax.us.pay.action.CHECK_CARD_PRESENT";


    /**
     * The intent action of Select Sub trans type
     * input: {@link EntryExtraData#PARAM_PACKAGE}
     * input: {@link EntryExtraData#PARAM_MESSAGE}
     * input: {@link EntryExtraData#PARAM_OPTIONS}:enum :  {@link com.pax.us.pay.ui.constant.entry.enumeration.SubTransType}
     * request: {@link EntryRequest#PARAM_INDEX}
     */
    public static final String ACTION_SELECT_SUB_TRANS_TYPE = "com.pax.us.pay.action.SELECT_SUB_TRANS_TYPE";


    /**
     * The intent action of Select Sub AID
     * input: {@link EntryExtraData#PARAM_PACKAGE}
     * input: {@link EntryExtraData#PARAM_MESSAGE}
     * input: {@link EntryExtraData#PARAM_OPTIONS}: According the EMV card's AID
     * request: {@link EntryRequest#PARAM_INDEX}
     */
    public static final String ACTION_SELECT_AID = "com.pax.us.pay.action.SELECT_AID";

    /**
     * The intent action of reverse partially approval
     * input: {@link EntryExtraData#PARAM_PACKAGE}
     * input: {@link EntryExtraData#PARAM_MESSAGE}
     * input: {@link EntryExtraData#PARAM_OPTIONS}: enum: {@link ConfirmationType}
     * request: {@link EntryRequest#PARAM_INDEX}
     */
    public static final String ACTION_REVERSE_PARTIAL_APPROVAL = "com.pax.us.pay.action.REVERSE_PARTIAL_APPROVAL";


    /**
     * The intent action of supplement partially approval
     * input: {@link EntryExtraData#PARAM_PACKAGE}
     * input: {@link EntryExtraData#PARAM_MESSAGE}
     * input: {@link EntryExtraData#PARAM_OPTIONS}: enum: {@link ConfirmationType}
     * request: {@link EntryRequest#PARAM_INDEX}
     */
    public static final String ACTION_SUPPLEMENT_PARTIAL_APPROVAL = "com.pax.us.pay.action.SUPPLEMENT_PARTIAL_APPROVAL";


}
