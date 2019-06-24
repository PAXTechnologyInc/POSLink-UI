package com.pax.pay.ui.def_ui.utils;

public enum CurrencyCode {
    AL("ALL", "Lek", 8, 2),
    DZ("DZD", "", 12, 2),
    AR("ARS", "A$ ", 32, 2),
    AU("AUD", "$  ", 36, 2),
    BS("BSD", "B$ ", 44, 2),
    BH("BHD", "BD ", 48, 3),
    BD("BDT", "Tk ", 50, 2),
    BB("BBD", "$  ", 52, 2),
    BM("BMD", "$  ", 60, 2),
    BO("BOB", "$  ", 68, 2),
    BW("BWP", "Pu ", 72, 2),
    BZ("BZD", "BZ$", 84, 2),
    BN("BND", "   ", 96, 2),
    CA("CAD", "$  ", 124, 2),
    LK("LKR", "   ", 144, 2),
    CL("CLP", "   ", 152, 2),
    CN("CNY", "¥  ", 156, 2),
    CO("COP", "$  ", 170, 2),
    CR("CRC", "₡  ", 188, 2),
    HR("HRK", "Kn ", 191, 2),
    CZ("CZK", "Kč ", 203, 2),
    DK("DKK", "kr ", 208, 2),
    DO("DOP", "$  ", 214, 2),
    GT("GTQ", "Q  ", 320, 2),
    HN("HNL", "L  ", 340, 2),
    HK("HKD", "HK$", 344, 2),
    HU("HUF", "Ft ", 348, 2),
    IS("ISK", "kr ", 352, 2),
    IN("INR", "₨  ", 356, 2),
    IL("ILS", "₪  ", 376, 2),
    JM("JMD", "$  ", 388, 2),
    JP("JPY", "￥ ", 392, 0),
    KZ("KZT", "лв", 398, 2),
    JO("JOD", "JD ", 400, 3),
    KE("KES", "KSk", 404, 2),
    KR("KRW", "₩  ", 410, 0),
    KW("KWD", "KD ", 414, 3),
    LB("LBP", "£  ", 422, 2),
    LV("LVL", "Ls ", 428, 2),
    LR("LRD", "$  ", 430, 2),
    LT("LTL", "L  ", 440, 2),
    MO("MOP", "$  ", 446, 2),
    MY("MYR", "RM ", 458, 2),
    MU("MUR", "   ", 480, 2),
    MX("MXN", "$  ", 484, 2),
    MA("MAD", "DH ", 504, 2),
    OM("OMR", "   ", 512, 3),
    NZ("NZD", "$  ", 554, 2),
    NG("NGN", "₦  ", 566, 2),
    NO("NOK", "kr ", 578, 2),
    PK("PKR", "Rs ", 586, 2),
    PA("PAB", "B/.", 590, 2),
    PE("PEN", "   ", 604, 2),
    PH("PHP", "₱  ", 608, 2),
    QA("QAR", "   ", 634, 2),
    RU("RUB", "   ", 643, 2),
    SA("SAR", "   ", 682, 2),
    SC("SCR", "   ", 690, 2),
    SG("SGD", "   ", 702, 2),
    ZA("ZAR", "R  ", 710, 2),
    SE("SEK", "   ", 752, 2),
    CH("CHF", "S₣ ", 756, 2),
    TH("THB", "฿  ", 764, 2),
    TT("TTD", "   ", 780, 2),
    AE("AED", "   ", 784, 2),
    EG("EGP", "£  ", 818, 2),
    GB("GBP", "£  ", 826, 2),
    US("USD", "$  ", 840, 2),
    YE("YER", "   ", 886, 2),
    TW("TWD", "NT$", 901, 2),
    VE("VEF", "Bs.", 937, 2),
    AZ("AZN", "   ", 944, 2),
    RO("RON", "L  ", 946, 2),
    TR("TRY", "   ", 949, 2),
    XC("XCD", "$  ", 951, 2),
    AF("AFN", "   ", 971, 2),
    BG("BGN", "лв", 975, 2),
    EU("EUR", "€ ", 978, 2),
    UA("UAH", "   ", 980, 2),
    PL("PLN", "zł ", 985, 2),
    BR("BRL", "R$ ", 986, 2),;


    private final String currencyName;
    private final String syboml;
    private final int currencyCode;
    private final int minorUnit;


    CurrencyCode(String currencyName, String syboml, int currencyCode, int minorUnit) {
        this.currencyName = currencyName;
        this.syboml = syboml;
        this.currencyCode = currencyCode;
        this.minorUnit = minorUnit;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public String getSyboml() {
        return syboml;
    }

    public int getCurrencyCode() {
        return currencyCode;
    }

    public int getMinorUnit() {
        return minorUnit;
    }


    public static CurrencyCode findTypeByCurrencyNmae(String currencyName) {
        for (CurrencyCode type : CurrencyCode.values()) {
            if (type.getCurrencyName().equals(currencyName))
                return type;
        }
        return null;
    }
}
