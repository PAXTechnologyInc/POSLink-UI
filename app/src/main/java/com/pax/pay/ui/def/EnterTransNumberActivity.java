package com.pax.pay.ui.def;

import android.text.TextUtils;

import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.core.helper.EnterTransNumHelper;
import com.paxus.utils.StringUtils;

public class EnterTransNumberActivity extends EnterDataLine1Activity<Long> implements EnterTransNumHelper.IEnterTransNumberListener {
    private EnterTransNumHelper helper;

    @Override
    protected void loadOtherParam(String label) {
        EditTextDataLimit limit;
        String lengthRange = getIntent().getExtras().getString(EntryExtraData.PARAM_VALUE_PATTERN);
        if (!TextUtils.isEmpty(lengthRange)) {
            limit = new EditTextDataLimit(getResources().getString(R.string.pls_input_transaction_number),
                    "", lengthRange, EInputType.NUM, false);
        } else {
            // Arvind: ANBP-679
            String minLength = getIntent().getExtras().getString(EntryExtraData.PARAM_MIN_LENGTH);
            String maxLength = getIntent().getExtras().getString(EntryExtraData.PARAM_MAX_LENGTH);
            int min = StringUtils.parseInt(minLength, 0);
            int max = StringUtils.parseInt(maxLength, 4);
            limit = new EditTextDataLimit(getString(R.string.pls_input_transaction_number),
                    "", min, max, EInputType.NUM, false);
        }
        setLimit(limit);
        helper = new EnterTransNumHelper(this, new RespStatusImpl(this));

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


    @Override
    protected void sendAbort() {
        helper.sendAbort();
    }

    @Override
    protected void sendNext(Long data) {
        helper.sendNext(data);
    }

    @Override
    protected Long convert(String content) {
        return StringUtils.parseLong(content);
    }

}
