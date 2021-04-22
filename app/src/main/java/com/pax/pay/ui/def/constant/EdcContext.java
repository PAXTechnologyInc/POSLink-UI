package com.pax.pay.ui.def.constant;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class EdcContext {

    public static final String EDC_TYPE_ALL = "All";
    public static final String EDC_TYPE_CREDIT = "Credit";
    public static final String EDC_TYPE_DEBIT = "Debit";
    public static final String EDC_TYPE_EBT = "EBT";
    public static final String EDC_TYPE_CASH = "Cash";
    public static final String EDC_TYPE_GIFT = "Gift";
    public static final String EDC_TYPE_LOYALTY = "Loyalty";
    public static final String EDC_TYPE_SUB_GIFT = "SubGift"; // Sub Services
    public static final String EDC_TYPE_SUB_LOYALTY = "SubLoyalty";
    public static final String EDC_TYPE_CHECK = "Check";
    public static final int EBT_TYPE_FOOD_STAMP = 1;
    public static final int EBT_TYPE_CASH_BENEFIT = 2;
    public static final int EBT_TYPE_VOUCHER = 3;
    public static final int EBT_TYPE_E_WIC = 4;
    public static final int EBT_TYPE_BOTH = 5;
    public static final int EBT_TYPE_E_WIC_VOUCHER = 6;

    private EdcContext() {
    }

    public static String[] getEdcTypeStrings() {
        return new String[]{
                EDC_TYPE_CREDIT,
                EDC_TYPE_DEBIT,
                EDC_TYPE_EBT,
                EDC_TYPE_CASH,
                EDC_TYPE_GIFT,
                EDC_TYPE_SUB_GIFT,
                EDC_TYPE_LOYALTY,
                EDC_TYPE_SUB_LOYALTY,
        };
    }

    public static String getEdcTypeDesc(Context context, @EdcType String t) {
        if (EDC_TYPE_ALL.equals(t)) {
            return "All";
        } else if (EDC_TYPE_CREDIT.equals(t)) {
            return "Credit";
        } else if (EDC_TYPE_DEBIT.equals(t)) {
            return "Debit";
        } else if (EDC_TYPE_EBT.equals(t)) {
            return "EBT";
        } else if (EDC_TYPE_CASH.equals(t)) {
            return "Cash";
        } else if (EDC_TYPE_GIFT.equals(t)) {
            return "Gift";
        } else if (EDC_TYPE_LOYALTY.equals(t)) {
            return "Loyalty";
        } else if (EDC_TYPE_SUB_GIFT.equals(t)) {
            return "SubGift";
        } // Sub Services
        else if (EDC_TYPE_SUB_LOYALTY.equals(t)) {
            return "SubLoyalty";
        } else if (EDC_TYPE_CHECK.equals(t)) {
            return "Check";
        } else {
            return "";
        }
    }


    @StringDef({
            EDC_TYPE_ALL,
            EDC_TYPE_CREDIT,
            EDC_TYPE_DEBIT,
            EDC_TYPE_EBT,
            EDC_TYPE_CASH,
            EDC_TYPE_GIFT,
            EDC_TYPE_LOYALTY,
            EDC_TYPE_SUB_GIFT,
            EDC_TYPE_SUB_LOYALTY,
            EDC_TYPE_CHECK,
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface EdcType {
    }

    @IntDef({
            EBT_TYPE_FOOD_STAMP,
            EBT_TYPE_CASH_BENEFIT,
            EBT_TYPE_VOUCHER,
            EBT_TYPE_E_WIC,
            EBT_TYPE_BOTH,
            EBT_TYPE_E_WIC_VOUCHER,
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface EbtType {
    }

//    @NonNull
//    public static String getEbtTypeDesc(Context context, @EbtType int type) {
//        switch (type) {
//            case EBT_TYPE_FOOD_STAMP:
//                return context.getString(R.string.type_food_stamp);
//            case EBT_TYPE_CASH_BENEFIT:
//                return context.getString(R.string.type_cash_benefit);
//            case EBT_TYPE_VOUCHER:
//                return context.getString(R.string.type_voucher);
//            case EBT_TYPE_E_WIC:
//                return context.getString(R.string.type_e_wic);
//            case EBT_TYPE_BOTH:
//                return context.getString(R.string.type_food_or_cash);
//            case EBT_TYPE_E_WIC_VOUCHER:
//                return context.getString(R.string.type_e_wic_voucher);
//            default:
//                return "";
//        }
//    }
}
