package com.pax.pay.ui.def;

import androidx.annotation.Nullable;

import com.pax.pay.ui.def.base.SecurityRespStatusImpl;
import com.pax.us.pay.ui.core.helper.EnterVCodeHelper;
import com.paxus.utils.StringUtils;
import com.paxus.view.utils.ViewUtils;


public class EnterVCodeActivity extends EnterSecurityInfoActivity implements EnterVCodeHelper.IEnterVCodeListener {
    EnterVCodeHelper helper;

    @Override
    protected void loadOtherParam() {
        //setDefaultParam(0, 4, false);
        setPrompt(getResources().getString(R.string.pls_input_vcode));
        helper = new EnterVCodeHelper(this, new SecurityRespStatusImpl(this));
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
        helper.setSecurityArea(offsetX, offsetY, width, height, fontSize, "FF0000");
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

    @Override
    public void onShowVCodeName(@Nullable String vCodeName) {
        if (!StringUtils.isEmpty(vCodeName)) {
            setPrompt(vCodeName);
        }
    }
}
