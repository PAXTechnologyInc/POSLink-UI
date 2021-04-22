package com.pax.pay.ui.def;

import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.us.pay.ui.core.helper.EnterOrderNumHelper;

public class EnterOrderNumberActivity extends EnterDataLine1Activity<String> implements EnterOrderNumHelper.IEnterOrderNumListener {

    private EnterOrderNumHelper helper;

    @Override
    protected void loadOtherParam(String label) {
        EditTextDataLimit limit = new EditTextDataLimit(getString(R.string.pls_input_order_number),
                "", 0, 12, EInputType.ALLTEXT, false);
        setLimit(limit);

        helper = new EnterOrderNumHelper(this, new RespStatusImpl(this));
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
