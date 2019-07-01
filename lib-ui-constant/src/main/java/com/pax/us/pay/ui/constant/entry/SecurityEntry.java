package com.pax.us.pay.ui.constant.entry;

public class SecurityEntry {
    public static final String CATEGORY = "com.pax.us.pay.ui.category.SECURITY";
    /**
     * The intent action of Input Account page
     * input: {@link EntryInput#PARAM_PACKAGE} <br/>
     * input: {@link EntryInput#PARAM_MESSAGE} <br/>
     * input: {@link EntryInput#PARAM_ENABLE_SWIPE} boolean <br/>
     * input: {@link EntryInput#PARAM_ENABLE_INSERT} <br/>
     * input: {@link EntryInput#PARAM_ENABLE_TAP} <br/>
     * input: {@link EntryInput#PARAM_ENABLE_MANUAL} <br/>
     * request: none
     */
    public static final String ACTION_INPUT_ACCOUNT = "com.pax.us.pay.action.INPUT_ACCOUNT";

    /**
     * The intent action of Enter PIN page
     * input: {@link EntryInput#PARAM_PACKAGE} <br/>
     * input: {@link EntryInput#PARAM_MESSAGE} <br/>
     * request: none
     */
    public static final String ACTION_ENTER_PIN = "com.pax.us.pay.action.ENTER_PIN";

    /**
     * The intent action of enter VCode/CVV
     * input: {@link EntryInput#PARAM_PACKAGE} <br/>
     * input: {@link EntryInput#PARAM_MESSAGE} <br/>
     * request: none
     */
    public static final String ACTION_ENTER_VCODE = "com.pax.us.pay.action.ENTER_CVV";


    /**
     * The intent action of enter card last 4 digits
     * input: {@link EntryInput#PARAM_PACKAGE} <br/>
     * input: {@link EntryInput#PARAM_MESSAGE} <br/>
     * request: none
     */
    public static final String ACTION_ENTER_CARD_LAST_4_DIGITS = "com.pax.us.pay.action.ENTER_CARD_LAST_4_DIGITS";


    /**
     * The intent action of enter card all digits
     * input: {@link EntryInput#PARAM_PACKAGE} <br/>
     * input: {@link EntryInput#PARAM_MESSAGE} <br/>
     * request: none
     */
    public static final String ACTION_ENTER_CARD_ALL_DIGITS = "com.pax.us.pay.action.ENTER_CARD_ALL_DIGITS";
}
