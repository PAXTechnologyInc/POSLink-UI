package com.pax.us.pay.ui.constant.entry;

public class SignatureEntry {
    public static final String CATEGORY = "com.pax.us.pay.ui.category.SIGNATURE";
    /**
     * The intent action of SignatureEntry <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_CURRENCY}  enum: {@link com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType} <br>
     * input: {@link EntryExtraData#PARAM_TOTAL_AMOUNT} <br>
     * input: {@link EntryExtraData#PARAM_TIMEOUT} <br>
     * request: {@link EntryRequest#PARAM_SIGNATURE} <br>
     */
    public static final String ACTION_SIGNATURE = "com.pax.us.pay.action.GET_SIGNATURE";


}
