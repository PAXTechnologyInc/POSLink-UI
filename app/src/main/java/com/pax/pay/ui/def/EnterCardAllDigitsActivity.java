package com.pax.pay.ui.def;

import android.support.annotation.Nullable;

import com.pax.pay.ui.def.base.SecurityRespStatusImpl;
import com.pax.us.pay.ui.core.helper.SecurityHelper;
import com.paxus.view.utils.ViewUtils;

public class EnterCardAllDigitsActivity extends EnterSecurityInfoActivity implements SecurityHelper.ISecurityListener {
    private SecurityHelper helper;

    @Override
    protected void loadOtherParam() {
        setPrompt(getString(R.string.prompt_input_all_digit));
        helper = new SecurityHelper(this, new SecurityRespStatusImpl(this));
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
    protected void sendNext() {
        helper.sendNext();
    }

    @Override
    protected void sendNext(int offsetX, int offsetY, int width, int height) {
        int fontSize = ViewUtils.px2sp(this, mEditText.getTextSize());
        helper.setSecurityArea(offsetX, offsetY, width, height, fontSize);
    }

    @Override
    public void onShowAmount(long amount) {
        setTotalAmount(amount);
    }

    @Override
    public void onShowCurrency(@Nullable String currency, boolean isPoint) {
        if (isPoint) {
            setPoint(true);
        } else {
            setCurrencyName(currency);
            setPoint(false);
        }
    }

}
