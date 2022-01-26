package com.paxus.utils;

/*
 *https://en.wikipedia.org/wiki/ISO_3166-1_numeric
 * https://www.iso.org/obp/ui#home
 */
public enum CountryCode {

    /**
     * <a href="http://en.wikipedia.org/wiki/United_States">United States</a>
     */
    US("840", "USD"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Canada">Canada</a>
     */
    CA("124", "CAD"),

    /**
     * https://en.wikipedia.org/wiki/Ireland
     */
    IE("372", "EUR"),

    /**
     * <a href="http://en.wikipedia.org/wiki/United_Kingdom">United Kingdom</a>
     */
    GB("826", "GBP"),
    ;

    private String countryCode;
    private String currencryName;

    CountryCode(String countryCode, String currencyName) {
        this.countryCode = countryCode;
        this.currencryName = currencyName;
    }

    public static String findCurrencyNameByCountryCode(String countryCode) {
        for (CountryCode code : CountryCode.values()) {
            if (code.getCountryCode().equals(countryCode)) {
                return code.getCurrencryName();
            }
        }

        return null;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCurrencryName() {
        return currencryName;
    }
}
