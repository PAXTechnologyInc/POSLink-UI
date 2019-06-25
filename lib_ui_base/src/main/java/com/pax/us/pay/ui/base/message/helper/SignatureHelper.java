package com.pax.us.pay.ui.base.message.helper;

import android.os.Bundle;

import com.pax.us.pay.ui.constant.parameter.Request;

public class SignatureHelper extends BaseHelper {
    public void sendObjNext(short[] traceData) {
        Bundle bundle = new Bundle();
        bundle.putShortArray(Request.Text.PARA_SIGNATURE, traceData);
        super.sendObjNext(bundle);

    }
}
