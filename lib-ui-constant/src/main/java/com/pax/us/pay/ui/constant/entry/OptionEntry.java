package com.pax.us.pay.ui.constant.entry;

import com.pax.us.pay.ui.constant.entry.enumeration.ConfirmationType;

public class OptionEntry {
    public static final String CATEGORY = "com.pax.us.pay.ui.category.OPTION";
    /**
     * The intent action of Select Ebt type <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_OPTIONS}: enum: {@link com.pax.us.pay.ui.constant.entry.enumeration.EBTType} <br>
     * request: {@link EntryRequest#PARAM_INDEX} <br>
     */
    public static final String ACTION_SELECT_EBT_TYPE = "com.pax.us.pay.action.SELECT_EBT_TYPE";

    /**
     * The intent action of Select by pass <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_OPTIONS}: enum: {@link com.pax.us.pay.ui.constant.entry.enumeration.BypassReason} <br>
     * request: {@link EntryRequest#PARAM_INDEX} <br>
     */
    public static final String ACTION_SELECT_BY_PASS = "com.pax.us.pay.action.SELECT_BY_PASS";

    /**
     * The intent action of check card present <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_OPTIONS}: enum: {@link ConfirmationType} <br>
     * request: {@link EntryRequest#PARAM_INDEX} <br>
     */
    public static final String ACTION_CHECK_CARD_PRESENT = "com.pax.us.pay.action.CHECK_CARD_PRESENT";


    /**
     * The intent action of Select Sub trans type <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_OPTIONS}:enum :  {@link com.pax.us.pay.ui.constant.entry.enumeration.SubTransType} <br>
     * request: {@link EntryRequest#PARAM_INDEX} <br>
     */
    public static final String ACTION_SELECT_SUB_TRANS_TYPE = "com.pax.us.pay.action.SELECT_SUB_TRANS_TYPE";


    /**
     * The intent action of Select Sub AID <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_OPTIONS}: According the EMV card's AID <br>
     * request: {@link EntryRequest#PARAM_INDEX} <br>
     */
    public static final String ACTION_SELECT_AID = "com.pax.us.pay.action.SELECT_AID";

    /**
     * The intent action of reverse partially approval <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_OPTIONS}: enum: {@link ConfirmationType} <br>
     * request: {@link EntryRequest#PARAM_INDEX} <br>
     */
    public static final String ACTION_REVERSE_PARTIAL_APPROVAL = "com.pax.us.pay.action.REVERSE_PARTIAL_APPROVAL";


    /**
     * The intent action of supplement partially approval <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_OPTIONS}: enum: {@link ConfirmationType} <br>
     * request: {@link EntryRequest#PARAM_INDEX} <br>
     */
    public static final String ACTION_SUPPLEMENT_PARTIAL_APPROVAL = "com.pax.us.pay.action.SUPPLEMENT_PARTIAL_APPROVAL";


}
