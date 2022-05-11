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
     * Broadcast Category: LANGUAGE<br>{@value #CATEGORY}<br>
     */
    public static final String CATEGORY = "com.pax.us.pay.status.category.LANGUAGE";

    /**
     * Broadcast Action: Set Language<br>{@value #SET_LANGUAGE}<br>
     * <p>
     *     Input: {@link StatusData#PARAM_COUNTRY}<br>
     *     Type: String.<br>
     *     Country Code of Locale.<br>
     *     See java.util.Locale for details.
     * </p>
     * <p>
     *     Input: {@link StatusData#PARAM_LANGUAGE}<br>
     *     Type: String.<br>
     *     Language Code of Locale.<br>
     *     See java.util.Locale for details.
     * </p>
     */
    public static final String SET_LANGUAGE = "com.pax.us.pay.SET_LANGUAGE";


}
