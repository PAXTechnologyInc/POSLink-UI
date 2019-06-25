package com.pax.us.pay.ui.base.message.helper;

import android.os.Bundle;

import com.pax.us.pay.ui.constant.parameter.Request;

public class ExpiryDateHelper extends BaseHelper {
    public void sendObjNext(String date) {
        Bundle bundle = new Bundle();
        bundle.putString(Request.Text.PARA_EXPIRY_DATE, date.replace("/", ""));
        super.sendObjNext(bundle);
    }
}
