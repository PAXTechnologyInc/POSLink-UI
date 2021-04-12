package com.pax.us.pay.ui.message;

import java.util.Locale;

/**
 * <a href="http://www.localeplanet.com/icu/iso3166.html>ISO 3166</a>
 * <a href="https://en.wikipedia.org/wiki/ISO_4217>ISO 4217</a>
 */
public enum CurrencyCode {
    /**
     * <a href="http://en.wikipedia.org/wiki/United_Arab_Emirates">United Arab Emirates</a>
     */
    //AE("AED", "   ", 784, 2, new Locale("ar", "AE")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Afghanistan">Afghanistan</a>
     */
    AF("AFN", "   ", 971, 2, new Locale("uz", "AF")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Albania">Albania</a>
     */
    AL("ALL", "Lek", 8, 2, new Locale("sq", "AL")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Argentina">Argentina</a>
     */
    AR("ARS", "A$ ", 32, 2, new Locale("es", "AR")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Australia">Australia</a>
     */
    AU("AUD", "$  ", 36, 2, new Locale("en", "AU")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Azerbaijan">Azerbaijan</a>
     */
    AZ("AZN", "   ", 944, 2, new Locale("az", "AZ")),

    /**
     * <a href="http://en.wikipedia.org/wiki/Barbados">Barbados</a>
     */
    BB("BBD", "$  ", 52, 2, new Locale("en", "BB")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Belgium">Belgium</a>
     */
    BD("BDT", "Tk ", 50, 2, new Locale("bn", "BD")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Bulgaria">Bulgaria</a>
     */
    BG("BGN", "лв", 975, 2, new Locale("bg", "BG")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Bahrain">Bahrain</a>
     */
    //BH("BHD", "BD ", 48, 3, new Locale("ar", "BH")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Bermuda">Bermuda</a>
     */
    BM("BMD", "$  ", 60, 2, new Locale("en", "BM")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Brunei">Brunei Darussalam</a>
     */
    BN("BND", "   ", 96, 2, new Locale("ms", "BN")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Bolivia">Plurinational State of Bolivia</a>
     */
    BO("BOB", "$  ", 68, 2, new Locale("es", "BO")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Brazil">Brazil</a>
     */
    BR("BRL", "R$ ", 986, 2, new Locale("pt", "BR")),
    /**
     * <a href="http://en.wikipedia.org/wiki/The_Bahamas">Bahamas</a>
     */
    BS("BSD", "B$ ", 44, 2, new Locale("en", "BS")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Botswana">Botswana</a>
     */
    BW("BWP", "Pu ", 72, 2, new Locale("en", "BW")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Belize">Belize</a>
     */
    BZ("BZD", "BZ$", 84, 2, new Locale("en", "BZ")),

    /**
     * <a href="http://en.wikipedia.org/wiki/Canada">Canada</a>
     */
    CA("CAD", "$  ", 124, 2, Locale.CANADA),
    /**
     * <a href="http://en.wikipedia.org/wiki/Switzerland">Switzerland</a>
     */
    CH("CHF", "S₣ ", 756, 2, new Locale("en", "CH")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Chile">Chile</a>
     */
    CL("CLP", "   ", 152, 2, new Locale("es", "CL")),
    /**
     * <a href="http://en.wikipedia.org/wiki/China">China</a>
     */
    CN("CNY", "¥  ", 156, 2, Locale.CHINA),
    /**
     * <a href="http://en.wikipedia.org/wiki/Colombia">Colombia</a>
     */
    CO("COP", "$  ", 170, 2, new Locale("es", "CO")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Costa_Rica">Costa Rica</a>
     */
    CR("CRC", "₡  ", 188, 2, new Locale("es", "CR")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Czech_Republic">Czech Republic</a>
     */
    CZ("CZK", "Kč ", 203, 2, new Locale("cs", "CZ")),

    /**
     * <a href="http://en.wikipedia.org/wiki/Denmark">Denmark</a>
     */
    DK("DKK", "kr ", 208, 2, new Locale("da", "DK")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Dominican_Republic">Dominican Republic</a>
     */
    DO("DOP", "$  ", 214, 2, new Locale("es", "DO")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Algeria">Algeria</a>
     */
    DZ("DZD", "", 12, 2, new Locale("kab", "DZ")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Egypt">Egypt</a>
     */
    //EG("EGP", "£  ", 818, 2, new Locale("ar", "EG")),
    /**
     * <a href="https://en.wikipedia.org/wiki/European_Union">Euro</a>
     */
    EU("EUR", "€ ", 978, 2, new Locale("en", "GB", "EUR")),

    /**
     * <a href="http://en.wikipedia.org/wiki/United_Kingdom">United Kingdom</a>
     */
    GB("GBP", "£  ", 826, 2, Locale.UK),
    /**
     * <a href="http://en.wikipedia.org/wiki/Guatemala">Guatemala</a>
     */
    GT("GTQ", "Q  ", 320, 2, new Locale("es", "GT")),

    /**
     * <a href="http://en.wikipedia.org/wiki/Hong_Kong">Hong Kong</a>
     */
    HK("HKD", "HK$", 344, 2, new Locale("zh", "HK")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Honduras">Honduras</a>
     */
    HN("HNL", "L  ", 340, 2, new Locale("es", "HN")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Croatia">Croatia</a>
     */
    HR("HRK", "Kn ", 191, 2, new Locale("hr", "HR")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Hungary">Hungary</a>
     */
    HU("HUF", "Ft ", 348, 2, new Locale("hu", "HU")),

    /**
     * <a href="http://en.wikipedia.org/wiki/Israel">Israel</a>
     */
    IL("ILS", "₪  ", 376, 2, new Locale("he", "IL")),
    /**
     * <a href="http://en.wikipedia.org/wiki/India">India</a>
     */
    IN("INR", "₨  ", 356, 2, new Locale("en", "IN")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Iceland">Iceland</a>
     */
    IS("ISK", "kr ", 352, 2, new Locale("is", "IS")),

    /**
     * <a href="http://en.wikipedia.org/wiki/Jamaica">Jamaica</a>
     */
    JM("JMD", "$  ", 388, 2, new Locale("en", "JM")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Jordan">Jordan</a>
     */
    //JO("JOD", "JD ", 400, 3, new Locale("ar", "JO")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Japan">Japan</a>
     */
    JP("JPY", "￥ ", 392, 0, Locale.JAPAN),

    /**
     * <a href="http://en.wikipedia.org/wiki/Kenya">Kenya</a>
     */
    KE("KES", "KSk", 404, 2, new Locale("en", "KE")),
    /**
     * <a href="http://en.wikipedia.org/wiki/South_Korea">Republic of Korea</a>
     */
    KR("KRW", "₩  ", 410, 0, Locale.KOREA),
    /**
     * <a href="http://en.wikipedia.org/wiki/Kuwait">Kuwait</a>
     */
    //KW("KWD", "KD ", 414, 3, new Locale("ar", "KW")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Kazakhstan">Kazakhstan</a>
     */
    KZ("KZT", "лв", 398, 2, new Locale("kk", "KZ")),

    /**
     * <a href="http://en.wikipedia.org/wiki/Lebanon">Lebanon</a>
     */
    //LB("LBP", "£  ", 422, 2, new Locale("ar", "LB")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Sri_Lanka">Sri Lanka</a>
     */
    LK("LKR", "   ", 144, 2, new Locale("si", "LK")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Liberia">Liberia</a>
     */
    LR("LRD", "$  ", 430, 2, new Locale("en", "LR")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Lithuania">Lithuania</a>
     */
    LT("LTL", "L  ", 440, 2, new Locale("lt", "LT")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Latvia">Latvia</a>
     */
    LV("LVL", "Ls ", 428, 2, new Locale("lv", "LV")),

    /**
     * <a href="http://en.wikipedia.org/wiki/Morocco">Morocco</a>
     */
    MA("MAD", "DH ", 504, 2, new Locale("zgh", "MA")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Macau">Macao</a>
     */
    MO("MOP", "$  ", 446, 2, new Locale("zh", "MO")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Mauritius">Mauritius</a>
     */
    MU("MUR", "   ", 480, 2, new Locale("en", "MU")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Mexico">Mexico</a>
     */
    MX("MXN", "$  ", 484, 2, new Locale("es", "MX")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Malaysia">Malaysia</a>
     */
    MY("MYR", "RM ", 458, 2, new Locale("en", "MY")),

    /**
     * <a href="http://en.wikipedia.org/wiki/Nigeria">Nigeria</a>
     */
    NG("NGN", "₦  ", 566, 2, new Locale("en", "NG")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Norway">Norway</a>
     */
    NO("NOK", "kr ", 578, 2, new Locale("nb", "NO")),
    /**
     * <a href="http://en.wikipedia.org/wiki/New_Zealand">New Zealand</a>
     */
    NZ("NZD", "$  ", 554, 2, new Locale("en", "NZ")),

    /**
     * <a href=http://en.wikipedia.org/wiki/Oman"">Oman</a>
     */
    //OM("OMR", "   ", 512, 3, new Locale("ar", "OM")),

    /**
     * <a href="http://en.wikipedia.org/wiki/Panama">Panama</a>
     */
    PA("PAB", "B/.", 590, 2, new Locale("es", "PA")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Peru">Peru</a>
     */
    PE("PEN", "   ", 604, 2, new Locale("es", "PE")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Philippines">Philippines</a>
     */
    PH("PHP", "₱  ", 608, 2, new Locale("en", "PH")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Pakistan">Pakistan</a>
     */
    PK("PKR", "Rs ", 586, 2, new Locale("en", "PK")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Poland">Poland</a>
     */
    PL("PLN", "zł ", 985, 2, new Locale("pl", "PL")),

    /**
     * <a href="http://en.wikipedia.org/wiki/Qatar">Qatar</a>
     */
    //QA("QAR", "   ", 634, 2, new Locale("ar", "QA")),

    /**
     * <a href="http://en.wikipedia.org/wiki/Romania">Romania</a>
     */
    RO("RON", "L  ", 946, 2, new Locale("ro", "RO")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Russia">Russian Federation</a>
     */
    RU("RUB", "   ", 643, 2, new Locale("ru", "RU")),

    /**
     * <a href="http://en.wikipedia.org/wiki/Saudi_Arabia">Saudi Arabia</a>
     */
    //SA("SAR", "   ", 682, 2, new Locale("ar", "SA")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Seychelles">Seychelles</a>
     */
    SC("SCR", "   ", 690, 2, new Locale("en", "SC")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Sweden">Sweden</a>
     */
    SE("SEK", "   ", 752, 2, new Locale("sv", "SE")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Singapore">Singapore</a>
     */
    SG("SGD", "   ", 702, 2, new Locale("en", "SG")),

    /**
     * <a href="http://en.wikipedia.org/wiki/Thailand">Thailand</a>
     */
    TH("THB", "฿  ", 764, 2, new Locale("th", "TH")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Turkey">Turkey</a>
     */
    TR("TRY", "₺  ", 949, 2, new Locale("tr", "TR")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Trinidad_and_Tobago">Trinidad and Tobago</a>
     */
    TT("TTD", "   ", 780, 2, new Locale("en", "TT")),
    /**
     * <a href="http://en.wikipedia.org/wiki/Taiwan">Taiwan, Province of China</a>
     */
    TW("TWD", "NT$", 901, 2, Locale.TAIWAN),

    /**
     * <a href="http://en.wikipedia.org/wiki/Vatican_City">Holy See (Vatican City State)</a>
     */
    UA("UAH", "   ", 980, 2, new Locale("uk", "UA")),
    /**
     * <a href="http://en.wikipedia.org/wiki/United_States">United States</a>
     */
    US("USD", "$  ", 840, 2, Locale.US),

    /**
     * <a href="http://en.wikipedia.org/wiki/Venezuela">Bolivarian Republic of Venezuela</a>
     */
    VE("VEF", "Bs.", 937, 2, new Locale("es", "VE")),

    /**
     * <a href="https://en.wikipedia.org/wiki/Eastern_Caribbean_dollar">Eastern Caribbean dollar</a>
     */
    XC("XCD", "$  ", 951, 2, new Locale("en", "AG")),

    /**
     * <a href="http://en.wikipedia.org/wiki/Yemen">Yemen</a>
     */
    //YE("YER", "   ", 886, 2, new Locale("ar", "YE")),

    /**
     * <a href="http://en.wikipedia.org/wiki/South_Africa">South Africa</a>
     */
    ZA("ZAR", "R  ", 710, 2, new Locale("en", "ZA")),
    ;

    private final String currencyName;
    private final String symbol;
    private final int currencyCode;
    private final int minorUnit;
    private final Locale locale;

    /**
     * ISO 3166 http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2
     * @param currencyName International currency symbol
     * @param symbol Currency symbol
     * @param currencyCode ISO 4217 Code https://en.wikipedia.org/wiki/ISO_4217
     * @param minorUnit ISO 4217 https://en.wikipedia.org/wiki/ISO_4217
     * @param locale
     */
    CurrencyCode(String currencyName, String symbol, int currencyCode, int minorUnit, Locale locale) {
        this.currencyName = currencyName;
        this.symbol = symbol;
        this.currencyCode = currencyCode;
        this.minorUnit = minorUnit;
        this.locale = locale;
    }

    public static CurrencyCode findTypeByCurrencyName(String currencyName) {
        for (CurrencyCode type : CurrencyCode.values()) {
            if (type.getCurrencyName().equals(currencyName))
                return type;
        }
        return null;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getCurrencyCode() {
        return currencyCode;
    }

    public int getMinorUnit() {
        return minorUnit;
    }

    public Locale getLocale() {
        return locale;
    }
}
