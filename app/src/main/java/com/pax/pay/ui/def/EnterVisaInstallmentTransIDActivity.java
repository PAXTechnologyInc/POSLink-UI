package com.pax.pay.ui.def;

import android.text.TextUtils;

import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.core.helper.EnterVisaInstallmentTransIDHelper;

public class EnterVisaInstallmentTransIDActivity extends EnterDataLine1Activity<String> implements EnterVisaInstallmentTransIDHelper.IEnterVisaInstallmentTransIDListener{
    private EnterVisaInstallmentTransIDHelper helper;

    @Override
    protected void loadOtherParam(String message) {
        EditTextDataLimit limit;
        EInputType inputType = EInputType.NUM;
        String lengthRange = getIntent().getExtras().getString(EntryExtraData.PARAM_VALUE_PATTERN);
        limit = new EditTextDataLimit(getResources().getString(R.string.pls_input_visa_installmenttransID),
                "", lengthRange, inputType, false);
        setLimit(limit);

        helper = new EnterVisaInstallmentTransIDHelper(this, new RespStatusImpl(this));
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
    protected String convert(String content) {
        return content;
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
}
