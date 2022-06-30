package com.pax.pay.ui.def;


import androidx.annotation.Nullable;

import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.us.pay.ui.core.helper.EnterFuelAmountHelper;
import com.pax.us.pay.ui.message.CurrencyConverter;

public class EnterFuelAmountActivity extends EnterDataLine1Activity<Long> implements EnterFuelAmountHelper.IEnterFuelAmountListener {
    private EnterFuelAmountHelper helper;
    private boolean isPoint = false;

    @Override
    protected void loadOtherParam(String label) {
        EditTextDataLimit limit = new EditTextDataLimit(getResources().getString(R.string.prompt_input_fuel_amount),
                "", 0, 12, EInputType.AMOUNT, false);
        setLimit(limit);

        helper = new EnterFuelAmountHelper(this, new RespStatusImpl(this));
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
        setAmount(0L);
        this.isPoint = isPoint;
    }
}
