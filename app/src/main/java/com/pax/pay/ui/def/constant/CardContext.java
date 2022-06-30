package com.pax.pay.ui.def.constant;

import android.content.Context;

import androidx.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public final class CardContext {

    public static final String TYPE_ALL = "ALL";
    public static final String TYPE_VISA = "VISA";
    public static final String TYPE_MASTER_CARD = "MasterCard";
    public static final String TYPE_AMEX = "AMEX";
    public static final String TYPE_DINERS = "Diners";
    public static final String TYPE_DISCOVER = "Discover";
    public static final String TYPE_JCB = "JCB";
    public static final String TYPE_EN_ROUTE = "enRoute";
    public static final String TYPE_INTERAC = "INTERAC";
    public static final String TYPE_CUP = "CUP"; //China Union Pay
    public static final String TYPE_EXTENDED = "Extended";
    public static final String TYPE_UNSUPPORTED = "Unsupported";
    public static final String TYPE_SUB_VISA_ELECTRON = "VisaElectron";
    public static final String TYPE_SUB_VISA_CD = "VisaCD";
    public static final String TYPE_SUB_VS_INTERLINK = "VSInterlink";
    public static final String TYPE_SUB_DISCOVER_CD = "DiscoverCD";
    public static final String TYPE_SUB_MAESTRO = "Maestro";
    public static final String TYPE_SUB_US_MAESTRO = "USMaestro";
    public static final String TYPE_SUB_DNA = "DNA";
    //Fleet Card
    public static final String TYPE_SUB_VISA_FLEET = "VisaFleet";
    public static final String TYPE_SUB_MASTER_CARD_FLEET = "MasterCardFleet";
    public static final String TYPE_SUB_FLEET_ONE = "FleetOne";
    public static final String TYPE_SUB_FLEET_WIDE = "Fleetwide";
    public static final String TYPE_SUB_FUEL_MAN = "Fuelman";
    public static final String TYPE_SUB_VOYAGER = "Voyager";
    public static final String TYPE_SUB_WRIGHT_EXPRESS = "WrightExpress";
    public static final String TYPE_SUB_GAS_CARD = "GasCard";
    //Debit
    public static final String TYPE_SUB_REVOLUTION_CARD = "RevolutionCard";
    //CardPresentType
    public static final String PRESENT_TYPE_ALWAYS_PRESENT = "Y";
    public static final String PRESENT_TYPE_ALWAYS_NOT_PRESENT = "N";
    public static final String PRESENT_TYPE_PROMPT = "A";

    private CardContext() {
    }

    public static boolean isFleetCard(@CardType String c) {
        return TYPE_SUB_MASTER_CARD_FLEET.equals(c)
                || TYPE_SUB_VISA_FLEET.equals(c)
                || TYPE_SUB_FLEET_ONE.equals(c)
                || TYPE_SUB_FLEET_WIDE.equals(c)
                || TYPE_SUB_FUEL_MAN.equals(c)
                || TYPE_SUB_WRIGHT_EXPRESS.equals(c)
                || TYPE_SUB_GAS_CARD.equals(c)
                || TYPE_SUB_VOYAGER.equals(c);
    }

    public static int getCVVLength(@CardType String c) {
        return TYPE_AMEX.equals(c) ? 4 : 3;
    }

    public static String getCardTypeDesc(Context context, @CardType String t) {
        return t;
    }

    @StringDef({
            TYPE_ALL,
            TYPE_VISA,
            TYPE_MASTER_CARD,
            TYPE_AMEX,
            TYPE_DINERS,
            TYPE_DISCOVER,
            TYPE_JCB,
            TYPE_EN_ROUTE,
            TYPE_INTERAC,
            TYPE_CUP,
            TYPE_EXTENDED,
            TYPE_UNSUPPORTED,

            TYPE_SUB_VISA_ELECTRON,
            TYPE_SUB_VISA_CD,
            TYPE_SUB_VS_INTERLINK,
            TYPE_SUB_DISCOVER_CD,
            TYPE_SUB_MAESTRO,
            TYPE_SUB_DNA,
            TYPE_SUB_US_MAESTRO,
            TYPE_SUB_VISA_FLEET,
            TYPE_SUB_MASTER_CARD_FLEET,
            TYPE_SUB_FLEET_ONE,
            TYPE_SUB_FLEET_WIDE,
            TYPE_SUB_FUEL_MAN,
            TYPE_SUB_VOYAGER,
            TYPE_SUB_WRIGHT_EXPRESS,
            TYPE_SUB_GAS_CARD,
            TYPE_SUB_REVOLUTION_CARD,
    })
    @Retention(RetentionPolicy.SOURCE)
    @Target(value = {ElementType.METHOD, ElementType.LOCAL_VARIABLE, ElementType.PARAMETER, ElementType.FIELD})
    public @interface CardType {
    }

    @StringDef({
            PRESENT_TYPE_ALWAYS_PRESENT,
            PRESENT_TYPE_ALWAYS_NOT_PRESENT,
            PRESENT_TYPE_PROMPT
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface CardPresentType {
    }
}
