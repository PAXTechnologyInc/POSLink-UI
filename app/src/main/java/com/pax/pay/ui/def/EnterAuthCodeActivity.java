package com.pax.pay.ui.def;

import android.text.TextUtils;

import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.core.helper.EnterAuthCodeHelper;
import com.paxus.utils.StringUtils;

public class EnterAuthCodeActivity extends EnterDataLine1Activity<String> implements EnterAuthCodeHelper.IEnterAuthCodeListener {

    private EnterAuthCodeHelper helper;

    @Override
    protected void loadOtherParam(String label) {
        EditTextDataLimit limit;
        String lengthRange = getIntent().getExtras().getString(EntryExtraData.PARAM_VALUE_PATTERN);
        if (!TextUtils.isEmpty(lengthRange)) {
            limit = new EditTextDataLimit(getResources().getString(R.string.please_enter_auth_code),
                    "", lengthRange, EInputType.ALLTEXT, false);
        } else {
            String minLength = getIntent().getExtras().getString(EntryExtraData.PARAM_MIN_LENGTH);
            String maxLength = getIntent().getExtras().getString(EntryExtraData.PARAM_MAX_LENGTH);
            int min = StringUtils.parseInt(minLength, 0);
            int max = StringUtils.parseInt(maxLength, 15);

            limit = new EditTextDataLimit(getResources().getString(R.string.please_enter_auth_code),
                    "", min, max, EInputType.ALLTEXT, false);
        }
        setLimit(limit);

        helper = new EnterAuthCodeHelper(this, new RespStatusImpl(this));
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
