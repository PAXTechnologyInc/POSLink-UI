package com.pax.us.pay.ui.base.message.helper;

import android.os.Bundle;

import com.pax.us.pay.ui.constant.parameter.Request;

public class ReferenceNumHelper extends BaseHelper {
    public void sendObjNext(String referenceNumber) {
        Bundle bundle = new Bundle();
        bundle.putString(Request.Text.PARA_REFERENCE_NUMBER, referenceNumber);
        super.sendObjNext(bundle);
    }
}
