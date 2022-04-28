package com.pax.us.pay.ui.constant.status;

/**
 * define Broadcast which has LANGUAGE Category
 * <p>
 *     In US market, BroadPOS app only support 1 language (English) and doesn't need change language.
 *     So generally it is only used for other markets like CANADA.
 * </p>
 */
public final class LanguageStatus {
    private LanguageStatus(){

    }

    /**
     * Broadcast Category: LANGUAGE
     */
    public static final String CATEGORY = "com.pax.us.pay.status.category.LANGUAGE";

    /**
     * Broadcast Action: Set Language
     * <p>
     *     Input: {@link #PARAM_COUNTRY}</p>
     * <p>
     *     Input: {@link #PARAM_LANGUAGE}</p>
     */
    public static final String SET_LANGUAGE = "com.pax.us.pay.SET_LANGUAGE";

    /**
     * Language Code of Locale.
     * <p>Type: String. See java.util.Locale for details.</p>
     * <p>Used in {@link #SET_LANGUAGE}</p>
     */
    public static final String PARAM_LANGUAGE = "language";

    /**
     * Country Code of Locale.
     * <p>Type: String. See java.util.Locale for details.</p>
     * <p>Used in {@link #SET_LANGUAGE}</p>
     */
    public static final String PARAM_COUNTRY = "country";
}
