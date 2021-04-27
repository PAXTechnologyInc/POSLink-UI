package com.pax.pay.ui.def;

import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.us.pay.ui.core.helper.EnterOriginalTransDateHelper;

public class EnterOriginalTransactionDateActivity extends EnterDataLine1Activity<String> implements EnterOriginalTransDateHelper.IEnterOriginalTransDateListener {
    private EnterOriginalTransDateHelper helper;

    @Override
    protected void loadOtherParam(String label) {
        EditTextDataLimit limit = new EditTextDataLimit(getResources().getString(R.string.pls_input_orig_trans_date),
                "", 0, 5, EInputType.DATE, false);
        setLimit(limit);

        helper = new EnterOriginalTransDateHelper(this, new RespStatusImpl(this));
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
