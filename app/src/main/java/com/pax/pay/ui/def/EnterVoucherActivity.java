package com.pax.pay.ui.def;


import android.support.annotation.Nullable;

import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.us.pay.ui.core.helper.VoucherHelper;

public class EnterVoucherActivity extends EnterDataLine1Activity<String> implements VoucherHelper.IEnterVoucherListener {
    private VoucherHelper helper;

    @Override
    protected void loadOtherParam(String message) {
        EditTextDataLimit voucherLimit = new EditTextDataLimit(getString(R.string.please_enter_voucher_number),
                "", 0, 15, EInputType.ALLTEXT, false);
        EditTextDataLimit authCodeLimit = new EditTextDataLimit(getResources().getString(R.string.please_enter_auth_code), "", 0, 15, EInputType.ALLTEXT, false);
        setLimit(voucherLimit);
        helper = new VoucherHelper(this, new RespStatusImpl(this));
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
    protected String convert(String content) {
        return content;
    }

    @Override
    protected void sendAbort() {
        helper.sendAbort();
    }

    @Override
    protected void sendNext(String data) {
        helper.sendNext(data);
    }

    @Override
    public void onShowAmount(long amount) {
        setAmount(amount);
    }

    @Override
    public void onShowCurrency(@Nullable String currency, boolean isPoint) {
        if (isPoint) {
            setPoint(true);
        } else {
            setCurrencyName(currency);
            setPoint(false);
        }
    }

}
