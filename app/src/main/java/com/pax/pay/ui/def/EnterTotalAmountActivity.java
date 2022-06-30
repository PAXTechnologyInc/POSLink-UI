package com.pax.pay.ui.def;


import androidx.annotation.Nullable;

import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.us.pay.ui.core.helper.EnterTotalAmountHelper;
import com.pax.us.pay.ui.message.CurrencyConverter;

public class EnterTotalAmountActivity extends EnterDataLine1Activity<Long> implements EnterTotalAmountHelper.IEnterTotalAmountListener {
    private EnterTotalAmountHelper helper;

    @Override
    protected void loadOtherParam(String message) {
        EditTextDataLimit limit = new EditTextDataLimit(getString(R.string.prompt_input_total_amount),
                "", 0, 12, EInputType.AMOUNT, false);
        setLimit(limit);

        helper = new EnterTotalAmountHelper(this, new RespStatusImpl(this));
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
        return CurrencyConverter.parse(content);
    }


    @Override
    public void onShowCurrency(@Nullable String s, boolean isPoint) {
        if (isPoint) {
            setPoint(true);
        } else {
            setPoint(false);
            setCurrencyName(s);
            setAmount(0L);
        }
    }


    @Override
    public void onShowAmount(long amount) {
        setAmount(amount);
    }

    @Override
    public void onShowEnableNoTipSelection(boolean enableCancel) {

    }

    @Override
    public void onShowTipName(@Nullable String tipName) {

    }
}
