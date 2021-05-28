package com.pax.pay.ui.def;

import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.us.pay.ui.core.helper.EnterExpiryDateHelper;

public class EnterExpireDateActivity extends EnterDataLine1Activity<String> implements EnterExpiryDateHelper.IEnterExpiryDateListener {
    private EnterExpiryDateHelper helper;

    @Override
    protected void loadOtherParam(String message) {
        EditTextDataLimit limit = new EditTextDataLimit(getResources().getString(R.string.pls_input_expiry_date),
                "", 0, 5, EInputType.EXPIRY_DATE, false);
        setLimit(limit);

        helper = new EnterExpiryDateHelper(this, new RespStatusImpl(this));

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
        return content.replace("/", "");
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
