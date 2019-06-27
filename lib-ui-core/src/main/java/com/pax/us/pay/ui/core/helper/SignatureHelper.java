package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;

import com.pax.us.pay.ui.constant.parameter.Request;
import com.pax.us.pay.ui.core.BaseActionHelper;

public class SignatureHelper extends BaseActionHelper {
    public void sendNext(short[] traceData) {
        Bundle bundle = new Bundle();
        bundle.putShortArray(Request.Text.PARA_SIGNATURE, traceData);
        super.sendNext(bundle);
    }
}
