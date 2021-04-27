package com.pax.pay.ui.def;

import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.us.pay.ui.core.helper.EnterClerkIDHelper;

public class EnterClerkIDActivity extends EnterDataLine1Activity<String> implements EnterClerkIDHelper.IEnterClerkIDListener {
    private EnterClerkIDHelper helper;

    @Override
    protected void loadOtherParam(String label) {
        EditTextDataLimit limit = new EditTextDataLimit(getResources().getString(R.string.pls_input_clerk_id),
                "", 0, 4, EInputType.NUM, false);
        setLimit(limit);

        helper = new EnterClerkIDHelper(this, new RespStatusImpl(this));
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
