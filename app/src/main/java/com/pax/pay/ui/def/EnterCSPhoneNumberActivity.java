package com.pax.pay.ui.def;

import android.text.TextUtils;

import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.core.helper.EnterCSPhoneNumHelper;
import com.paxus.utils.StringUtils;

public class EnterCSPhoneNumberActivity extends EnterDataLine1Activity<String> implements EnterCSPhoneNumHelper.IEnterCSPhoneNumListener {
    private EnterCSPhoneNumHelper helper;
    private int min;
    private int max;

    @Override
    protected void loadOtherParam(String label) {
        EditTextDataLimit limit;
        String lengthRange = getIntent().getExtras().getString(EntryExtraData.PARAM_VALUE_PATTERN);
        if (!TextUtils.isEmpty(lengthRange)) {
            limit = new EditTextDataLimit(getResources().getString(R.string.pls_input_cs_phone_number),
                    "", lengthRange, EInputType.NUM, false);
        } else {
            String minLength = getIntent().getExtras().getString(EntryExtraData.PARAM_MIN_LENGTH);

            String maxLength = getIntent().getExtras().getString(EntryExtraData.PARAM_MAX_LENGTH);
            min = StringUtils.parseInt(minLength, 1);
            max = StringUtils.parseInt(maxLength, 32);
            limit = new EditTextDataLimit(getString(R.string.pls_input_cs_phone_number),
                    "", min, max, EInputType.NUM, false);
        }
        setLimit(limit);
        helper = new EnterCSPhoneNumHelper(this, new RespStatusImpl(this));
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
