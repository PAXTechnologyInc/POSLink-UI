package com.pax.us.pay.ui.base.message.helper;

import android.os.Bundle;

import com.pax.us.pay.ui.constant.parameter.Request;

public class TipHelper extends BaseHelper {
    public void sendObjNext(long amount) {
        Bundle bundle = new Bundle();
        bundle.putLong(Request.Text.PARA_TIP, amount);
        super.sendObjNext(bundle);
    }
}
