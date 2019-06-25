package com.pax.us.pay.ui.base.message.helper;

import android.os.Bundle;

import com.pax.us.pay.ui.constant.parameter.Request;

public class TransNumHelper extends BaseHelper {
    public void sendObjNext(String transNumCode) {
        Bundle bundle = new Bundle();
        bundle.putInt(Request.Text.PARA_TRANS_NO, Integer.valueOf(transNumCode));
        super.sendObjNext(bundle);
    }
}
