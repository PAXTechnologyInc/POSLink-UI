package com.pax.us.pay.ui.constant.entry;

public class SecurityEntry {
    public static final String CATEGORY = "com.pax.us.pay.ui.category.SECURITY";
    /**
     * The intent action of Input Account page <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_CURRENCY}  enum: {@link com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType} <br>
     * input: {@link EntryExtraData#PARAM_TOTAL_AMOUNT} Long <br>
     * input: {@link EntryExtraData#PARAM_AMOUNT_MESSAGE} String <br>
     * input: {@link EntryExtraData#PARAM_ENABLE_SWIPE} boolean <br>
     * input: {@link EntryExtraData#PARAM_ENABLE_INSERT} boolean <br>
     * input: {@link EntryExtraData#PARAM_ENABLE_TAP} boolean <br>
     * input: {@link EntryExtraData#PARAM_ENABLE_MANUAL} boolean <br>
     * input: {@link EntryExtraData#PARAM_ENABLE_APPLEPAY} boolean <br>
     * input: {@link EntryExtraData#PARAM_ENABLE_GOOGLEPAY} boolean <br>
     * input: {@link EntryExtraData#PARAM_ENABLE_SUMSUNGPAY} boolean <br>
     * input: {@link EntryExtraData#PARAM_ENABLE_NFCPAY} boolean <br>
     * input: {@link EntryExtraData#PARAM_PAN_STYLES} enum: {@link com.pax.us.pay.ui.constant.entry.enumeration.PanStyles}  <br>
     * input: {@link EntryExtraData#PARAM_ENABLE_CONTACTLESS_LIGHT} boolean <br>
     * request: {@link EntryRequest#PARAM_X} <br>
     * request: {@link EntryRequest#PARAM_Y} <br>
     * request: {@link EntryRequest#PARAM_WIDTH} <br>
     * request: {@link EntryRequest#PARAM_HEIGHT} <br>
     * request: {@link EntryRequest#PARAM_FONT_SIZE} <br>     *
     */
    public static final String ACTION_INPUT_ACCOUNT = "com.pax.us.pay.action.INPUT_ACCOUNT";

    /**
     * The intent action of Enter PIN page <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_CURRENCY}  enum: {@link com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType} <br>
     * input: {@link EntryExtraData#PARAM_TOTAL_AMOUNT}  <br>
     * input: {@link EntryExtraData#PARAM_IS_ONLINE_PIN} boolean <br>
     * input: {@link EntryExtraData#PARAM_PIN_STYLES} enum: {@link com.pax.us.pay.ui.constant.entry.enumeration.PinStyles}  <br>
     * input: {@link EntryExtraData#PARAM_PIN_RANGE}  <br>
     * input: {@link EntryExtraData#PARAM_IS_EXTERNAL_PINPAD} boolean <br>
     * request: {@link EntryRequest#PARAM_X} <br>
     * request: {@link EntryRequest#PARAM_Y} <br>
     * request: {@link EntryRequest#PARAM_WIDTH} <br>
     * request: {@link EntryRequest#PARAM_HEIGHT} <br>
     */
    public static final String ACTION_ENTER_PIN = "com.pax.us.pay.action.ENTER_PIN";

    /**
     * The intent action of enter VCode/CVV <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_CURRENCY}  enum: {@link com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType} <br>
     * input: {@link EntryExtraData#PARAM_TOTAL_AMOUNT}  <br>
     * input: {@link EntryExtraData#PARAM_VCODE_NAME enum: {@link com.pax.us.pay.ui.constant.entry.enumeration.VCodeName}  <br>
     * request: {@link EntryRequest#PARAM_X} <br>
     * request: {@link EntryRequest#PARAM_Y} <br>
     * request: {@link EntryRequest#PARAM_WIDTH} <br>
     * request: {@link EntryRequest#PARAM_HEIGHT}<br>
     * request: {@link EntryRequest#PARAM_FONT_SIZE} <br>     *
     */
    public static final String ACTION_ENTER_VCODE = "com.pax.us.pay.action.ENTER_CVV";


    /**
     * The intent action of enter card last 4 digits <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_CURRENCY}  enum: {@link com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType} <br>
     * input: {@link EntryExtraData#PARAM_TOTAL_AMOUNT}  <br>
     * request: {@link EntryRequest#PARAM_X}  <br>
     * request: {@link EntryRequest#PARAM_Y}  <br>
     * request: {@link EntryRequest#PARAM_WIDTH}  <br>
     * request: {@link EntryRequest#PARAM_HEIGHT}  <br>
     * request: {@link EntryRequest#PARAM_FONT_SIZE} <br>     *
     */
    public static final String ACTION_ENTER_CARD_LAST_4_DIGITS = "com.pax.us.pay.action.ENTER_CARD_LAST_4_DIGITS";


    /**
     * The intent action of enter card all digits <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * input: {@link EntryExtraData#PARAM_CURRENCY}  enum: {@link com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType} <br>
     * input: {@link EntryExtraData#PARAM_TOTAL_AMOUNT}  <br>
     * request: {@link EntryRequest#PARAM_X}  <br>
     * request: {@link EntryRequest#PARAM_Y}  <br>
     * request: {@link EntryRequest#PARAM_WIDTH}  <br>
     * request: {@link EntryRequest#PARAM_HEIGHT}  <br>
     * request: {@link EntryRequest#PARAM_FONT_SIZE} <br>     *
     */
    public static final String ACTION_ENTER_CARD_ALL_DIGITS = "com.pax.us.pay.action.ENTER_CARD_ALL_DIGITS";

    /**
     * The intent action of enter administrator password <br>
     * input: {@link EntryExtraData#PARAM_PACKAGE} <br>
     * input: {@link EntryExtraData#PARAM_MESSAGE} <br>
     * request: {@link EntryRequest#PARAM_X}  <br>
     * request: {@link EntryRequest#PARAM_Y}  <br>
     * request: {@link EntryRequest#PARAM_WIDTH}  <br>
     * request: {@link EntryRequest#PARAM_HEIGHT}  <br>
     */
    public static final String ACTION_ENTER_ADMINISTRATION_PASSWORD = "com.pax.us.pay.action.ADMINISTRATOR_PASSWORD";
}
