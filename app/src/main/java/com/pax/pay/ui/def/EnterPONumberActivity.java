package com.pax.pay.ui.def;

import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.us.pay.ui.core.helper.EnterPONumHelper;

public class EnterPONumberActivity extends EnterDataLine1Activity<String> implements EnterPONumHelper.IEnterPONumListener {
    private EnterPONumHelper helper;

    @Override
    protected void loadOtherParam(String label) {
        EditTextDataLimit limit = new EditTextDataLimit(getString(R.string.pls_input_po_number),
                "", 0, 17, EInputType.ALLTEXT, false);
        setLimit(limit);

        helper = new EnterPONumHelper(this, new RespStatusImpl(this));
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
