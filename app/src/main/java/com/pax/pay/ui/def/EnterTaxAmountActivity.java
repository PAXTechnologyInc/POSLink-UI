package com.pax.pay.ui.def;


import androidx.annotation.Nullable;

import android.text.TextUtils;

import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.core.helper.EnterTaxAmountHelper;
import com.pax.us.pay.ui.message.CurrencyConverter;
import com.paxus.utils.StringUtils;

public class EnterTaxAmountActivity extends EnterDataLine1Activity<Long> implements EnterTaxAmountHelper.IEnterTaxAmountListener {
    private EnterTaxAmountHelper helper;
    private boolean isPoint = false;

    @Override
    protected void loadOtherParam(String label) {
//        EditTextDataLimit limit = new EditTextDataLimit(getString(R.string.prompt_input_tax_amount),
//                "", 0, 12, EInputType.AMOUNT, false);

        EditTextDataLimit limit;
        String lengthRange = getIntent().getExtras().getString(EntryExtraData.PARAM_VALUE_PATTERN);
        if (!TextUtils.isEmpty(lengthRange)) {
            limit = new EditTextDataLimit(getString(R.string.prompt_input_tax_amount),
                    "", lengthRange, EInputType.AMOUNT, false);
        } else {
            String minLength = getIntent().getExtras().getString(EntryExtraData.PARAM_MIN_LENGTH);
            String maxLength = getIntent().getExtras().getString(EntryExtraData.PARAM_MAX_LENGTH);
            int min = StringUtils.parseInt(minLength, 0);
            int max = StringUtils.parseInt(maxLength, 12);
            limit = new EditTextDataLimit(getString(R.string.prompt_input_tax_amount),
                    "", min, max, EInputType.AMOUNT, false);
        }
        setLimit(limit);

        helper = new EnterTaxAmountHelper(this, new RespStatusImpl(this));
    }

    @Override
    protected void onStart() {
        helper.start(this, getIntent());
        super.onStart();
    }

    @Override
    protected void onStop() {
        helper.stop();
        super.onStop();
    }


    @Override
    protected void sendAbort() {
        helper.sendAbort();
    }

    @Override
    protected void sendNext(Long data) {
        helper.sendNext(data);
    }

    @Override
    protected Long convert(String content) {
        return isPoint ? Long.valueOf(content) : CurrencyConverter.parse(content);
    }


    @Override
    public void onShowCurrency(@Nullable String s, boolean isPoint) {
        setCurrencyName(s);
        this.isPoint = isPoint;
    }

    @Override
    public void onShowAmount(long amount) {
        setAmount(amount);
    }
}
