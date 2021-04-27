package com.pax.pay.ui.def;

import android.text.TextUtils;

import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.core.helper.EnterMerchantReferenceNumHelper;
import com.paxus.utils.StringUtils;

public class EnterMerchantReferenceNumberActivity extends EnterDataLine1Activity<String> implements EnterMerchantReferenceNumHelper.IEnterMerchantReferenceNumListener {

    private EnterMerchantReferenceNumHelper helper;

    @Override
    protected void loadOtherParam(String label) {

        EditTextDataLimit limit;
        EInputType inputType = EInputType.NUM;

        String eInputType = getIntent().getExtras().getString(EntryExtraData.PARAM_EINPUT_TYPE);
        if (eInputType != null && eInputType.equals("ALLTEXT"))
            inputType = EInputType.ALLTEXT;

        String lengthRange = getIntent().getExtras().getString(EntryExtraData.PARAM_VALUE_PATTERN);
        if (!TextUtils.isEmpty(lengthRange)) {
            limit = new EditTextDataLimit(getResources().getString(R.string.pls_input_merchant_reference_number),
                    "", lengthRange, inputType, false);
        } else {
            String minLength = getIntent().getExtras().getString(EntryExtraData.PARAM_MIN_LENGTH);
            String maxLength = getIntent().getExtras().getString(EntryExtraData.PARAM_MAX_LENGTH);
            int min = StringUtils.parseInt(minLength, 0);
            int max = StringUtils.parseInt(maxLength, 12);

            limit = new EditTextDataLimit(getResources().getString(R.string.pls_input_merchant_reference_number),
                    "", min, max, inputType, false);
        }
        setLimit(limit);

        helper = new EnterMerchantReferenceNumHelper(this, new RespStatusImpl(this));
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
