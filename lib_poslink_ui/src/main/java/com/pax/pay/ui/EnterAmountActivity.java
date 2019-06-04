package com.pax.pay.ui;


import com.pax.pay.ui.constant.EUIMessageKey;
import com.pax.pay.ui.utils.CurrencyCode;
import com.pax.pay.ui.utils.CurrencyConverter;

import java.util.Locale;

public class EnterAmountActivity extends EnterDataLine1Activity<Long> {
    private String currency;

    @Override
    protected void loadOtherParam(String label) {
        currency = getIntent().getStringExtra(EUIMessageKey.IntentKey.CURRENCY);
        if ((currency == null) || (currency == "")) {
            currency = "USD";
        }
        int minLen = getIntent().getIntExtra(EUIMessageKey.IntentKey.MINLENGTH, 0);
        int maxLen = getIntent().getIntExtra(EUIMessageKey.IntentKey.MAXLENGTH, 255);
        setCurrencyName(currency);
        setAmount(0L);
        navTitle = "";


        EditTextDataLimit limit = new EditTextDataLimit(label == null ? getResources().getString(R.string.prompt_input_amount) : label,
                "", minLen, maxLen, EInputType.AMOUNT, false);
        if (limit == null)
            limit = EditTextDataLimit.getDef();
        setLimit(limit);
    }

    @Override
    protected Long convert(String content) {
        String countryName = CurrencyCode.findTypeByCurrencyNmae(currency).getCurrencyName();
        Locale locale = CurrencyConverter.findLocalByCountryName(countryName);
        return CurrencyConverter.parse(content, locale);
    }


}
