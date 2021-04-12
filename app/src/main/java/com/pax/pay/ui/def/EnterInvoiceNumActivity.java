package com.pax.pay.ui.def;

import android.text.TextUtils;

import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.core.helper.EnterInvoiceNumHelper;
import com.paxus.utils.StringUtils;

public class EnterInvoiceNumActivity extends EnterDataLine1Activity<String> implements EnterInvoiceNumHelper.IEnterInvoiceNumListener {
    private EnterInvoiceNumHelper helper;

    @Override
    protected void loadOtherParam(String label) {
        EditTextDataLimit limit;
        String lengthRange = getIntent().getExtras().getString(EntryExtraData.PARAM_VALUE_PATTERN);
        if (!TextUtils.isEmpty(lengthRange)) {
            limit = new EditTextDataLimit(getResources().getString(R.string.pls_input_invoice_number),
                    "", lengthRange, EInputType.ALLTEXT, false);
        } else {
            String minLength = getIntent().getExtras().getString(EntryExtraData.PARAM_MIN_LENGTH);
            String maxLength = getIntent().getExtras().getString(EntryExtraData.PARAM_MAX_LENGTH);

            int min = StringUtils.parseInt(minLength, 0);
            int max = StringUtils.parseInt(maxLength, 30);

            // Arvind: Changing EInputType from NUM to ALLTEXT.
            // https://pax-us.atlassian.net/browse/ANBP-354
            limit = new EditTextDataLimit(getString(R.string.pls_input_invoice_number),
                    "", min, max, EInputType.ALLTEXT, false);
        }
        setLimit(limit);

        helper = new EnterInvoiceNumHelper(this, new RespStatusImpl(this));
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
