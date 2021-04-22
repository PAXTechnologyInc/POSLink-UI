package com.pax.pay.ui.def;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.core.helper.EnterAmountHelper;
import com.pax.us.pay.ui.message.CurrencyConverter;
import com.paxus.utils.StringUtils;

public class EnterAmountActivity extends EnterDataLine1Activity<Long> implements EnterAmountHelper.IEnterAmountListener {
    private EnterAmountHelper helper;
    private boolean isPoint = false;
    private int min;
    private int max;
    private String lengthRange;

    @Override
    protected void loadOtherParam(String label) {
        EditTextDataLimit limit;
        lengthRange = getIntent().getExtras().getString(EntryExtraData.PARAM_VALUE_PATTERN);
        if (!TextUtils.isEmpty(lengthRange)) {
            limit = new EditTextDataLimit(getString(R.string.prompt_input_amount),
                    "", lengthRange, EInputType.AMOUNT, false);
        } else {
            String minLength = getIntent().getExtras().getString(EntryExtraData.PARAM_MIN_LENGTH);
            String maxLength = getIntent().getExtras().getString(EntryExtraData.PARAM_MAX_LENGTH);
            min = StringUtils.parseInt(minLength, 0);
            max = StringUtils.parseInt(maxLength, 12);
            limit = new EditTextDataLimit(getString(R.string.prompt_input_amount),
                    "", min, max, EInputType.AMOUNT, false);
        }
        setLimit(limit);
        helper = new EnterAmountHelper(this, new RespStatusImpl(this));
        //helper.start(this, getIntent());
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
        if (isPoint) {
            if (content == null || content.isEmpty()) {
                return 0L;
            }
            try {
                return Long.valueOf(content);
            } catch (NumberFormatException e) {
                return 0L;
            }
        } else {
            return CurrencyConverter.parse(content);
        }
    }

    @Override
    public void onShowCurrency(@Nullable String s, boolean isPoint) {
        this.isPoint = isPoint;
        if (!isPoint) {
            setCurrencyName(s);
            setAmount(0L);
        } else {
            //APMN-138
            EditTextDataLimit limit = null;
            if (!StringUtils.isEmpty(lengthRange)) {
                limit = new EditTextDataLimit(getResources().getString(R.string.prompt_input_point),
                        "", lengthRange, EInputType.NUM, false);
            } else {
                limit = new EditTextDataLimit(getResources().getString(R.string.prompt_input_point),
                        "", min, max, EInputType.NUM, false);
            }
            setLimit(limit);
        }
    }
}
