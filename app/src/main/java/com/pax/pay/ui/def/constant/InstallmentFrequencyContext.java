package com.pax.pay.ui.def.constant;

import android.content.Context;

import androidx.annotation.StringDef;

import com.pax.pay.ui.def.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class InstallmentFrequencyContext {
    //maps visa installment frequency from adj to noun(s)
    //e.g: monthly -> month, monthly -> Months

    public static final String INSTALLMENT_FREQENCY_WEEK = "weekly";
    public static final String INSTALLMENT_FREQENCY_BIWEEK = "biweekly";
    public static final String INSTALLMENT_FREQENCY_MONTH = "monthly";
    public static final String INSTALLMENT_FREQENCY_YEAR = "yearly";

    private InstallmentFrequencyContext(){
    }

    public static String convertFrequencyToSingularNoun(@InstallmentFrequency String rawFrequency, Context context){
        if (INSTALLMENT_FREQENCY_WEEK.equals(rawFrequency)) {
            return context.getString(R.string.visa_installment_frequency_week);
        } else if (INSTALLMENT_FREQENCY_BIWEEK.equals(rawFrequency)) {
            return context.getString(R.string.visa_installment_frequency_biweek);
        } else if (INSTALLMENT_FREQENCY_MONTH.equals(rawFrequency)) {
            return context.getString(R.string.visa_installment_frequency_month);
        } else if (INSTALLMENT_FREQENCY_YEAR.equals(rawFrequency)) {
            return context.getString(R.string.visa_installment_frequency_year);
        }
        return "";
    }

    public static String convertFrequencyToPluralNoun(@InstallmentFrequency String rawFrequency, Context context){
        if (INSTALLMENT_FREQENCY_WEEK.equals(rawFrequency)) {
            return context.getString(R.string.visa_installment_frequency_week_plural);
        } else if (INSTALLMENT_FREQENCY_BIWEEK.equals(rawFrequency)) {
            return context.getString(R.string.visa_installment_frequency_biweek_plural);
        } else if (INSTALLMENT_FREQENCY_MONTH.equals(rawFrequency)) {
            return context.getString(R.string.visa_installment_frequency_month_plural);
        } else if (INSTALLMENT_FREQENCY_YEAR.equals(rawFrequency)) {
            return context.getString(R.string.visa_installment_frequency_year_plural);
        }
        return "";
    }

    @StringDef({
            INSTALLMENT_FREQENCY_WEEK,
            INSTALLMENT_FREQENCY_BIWEEK,
            INSTALLMENT_FREQENCY_MONTH,
            INSTALLMENT_FREQENCY_YEAR
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface InstallmentFrequency {
    }
}