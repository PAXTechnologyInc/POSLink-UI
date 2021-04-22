package com.pax.pay.ui.def;

import android.text.TextUtils;

import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.core.helper.EnterAddressHelper;

public class EnterAddressActivity extends EnterDataLine1Activity<String> implements EnterAddressHelper.IEnterAddressListener {
    private EnterAddressHelper helper;

    @Override
    protected void loadOtherParam(String label) {
        EditTextDataLimit limit;

        String lengthRange = getIntent().getExtras().getString(EntryExtraData.PARAM_VALUE_PATTERN);
        if (!TextUtils.isEmpty(lengthRange)) {
            limit = new EditTextDataLimit(getString(R.string.pls_input_address),
                    "", lengthRange, EInputType.ALLTEXT, false);
        } else {
            limit = new EditTextDataLimit(getString(R.string.pls_input_address),
                    "", 0, 255, EInputType.ALLTEXT, false);
        }
        setLimit(limit);

        helper = new EnterAddressHelper(this, new RespStatusImpl(this));
        //helper.start(this, getIntent());
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
