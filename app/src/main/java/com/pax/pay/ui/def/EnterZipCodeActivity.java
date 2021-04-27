package com.pax.pay.ui.def;

import android.text.TextUtils;

import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.core.helper.EnterZipCodeHelper;
import com.paxus.utils.StringUtils;

public class EnterZipCodeActivity extends EnterDataLine1Activity<String> implements EnterZipCodeHelper.IEnterZipCodeListener {
    private EnterZipCodeHelper helper;

    @Override
    protected void loadOtherParam(String label) {
        EditTextDataLimit limit;
        EInputType inputType = EInputType.NUM;
        // Arvind: ANBP-105
        String eInputType = getIntent().getExtras().getString(EntryExtraData.PARAM_EINPUT_TYPE);
        if (eInputType != null && eInputType.equals("ALLTEXT"))
            inputType = EInputType.ALLTEXT;
        // Arvind: ANBP-110
        String lengthRange = getIntent().getExtras().getString(EntryExtraData.PARAM_VALUE_PATTERN);
        if (!TextUtils.isEmpty(lengthRange)) {
            limit = new EditTextDataLimit(getResources().getString(R.string.pls_input_zip_code),
                    "", lengthRange, inputType, false);
        } else {
            String minLength = getIntent().getExtras().getString(EntryExtraData.PARAM_MIN_LENGTH);
            String maxLength = getIntent().getExtras().getString(EntryExtraData.PARAM_MAX_LENGTH);

            int min = StringUtils.parseInt(minLength, 0);
            int max = StringUtils.parseInt(maxLength, 9);

            limit = new EditTextDataLimit(getResources().getString(R.string.pls_input_zip_code),
                    "", min, max, inputType, false);
        }
        setLimit(limit);
        helper = new EnterZipCodeHelper(this, new RespStatusImpl(this));
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
