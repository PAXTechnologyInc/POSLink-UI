package com.pax.pay.ui.def;

import android.support.annotation.Nullable;

import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.us.pay.ui.core.helper.EnterPromptRestrictionCodeHelper;

public class EnterPromptRestrictionCodeActivity extends EnterDataLine1Activity<String> implements EnterPromptRestrictionCodeHelper.IEnterPromptRestrictionCodeListener {
    private EnterPromptRestrictionCodeHelper helper;

    @Override
    protected void loadOtherParam(String label) {
        EditTextDataLimit limit = new EditTextDataLimit(getResources().getString(R.string.pls_input_prompt_restriction_code),
                "", 0, 2, EInputType.NUM, false);
        setLimit(limit);

        helper = new EnterPromptRestrictionCodeHelper(this, new RespStatusImpl(this));
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

    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message, String transMode) {
        super.onShowMessage(transName, message, transMode);
        setPromptTv(message);
    }
}
