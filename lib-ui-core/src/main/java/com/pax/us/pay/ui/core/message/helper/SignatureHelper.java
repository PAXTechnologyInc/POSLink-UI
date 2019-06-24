package com.pax.us.pay.ui.core.message.helper;

import android.os.Bundle;

import com.pax.us.pay.ui.constant.parameter.Request;
import com.pax.us.pay.ui.core.message.BaseActionHelper;

public class SignatureHelper extends BaseActionHelper {
    public void sendObjNext(short[] traceData) {
        Bundle bundle = new Bundle();
        bundle.putShortArray(Request.Text.PARA_SIGNATURE, traceData);
        super.sendObjNext(bundle);
    }
}
