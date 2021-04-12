package com.pax.pay.ui.def;

import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.us.pay.ui.core.helper.EnterTableNumHelper;

public class EnterTableNumberActivity extends EnterDataLine1Activity<String> implements EnterTableNumHelper.IEnterTableNumListener {
    private EnterTableNumHelper helper;

    @Override
    protected void loadOtherParam(String label) {
        EditTextDataLimit limit = new EditTextDataLimit(getString(R.string.pls_input_table_number),
                "", 0, 4, EInputType.NUM, false);
        setLimit(limit);

        helper = new EnterTableNumHelper(this, new RespStatusImpl(this));
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
