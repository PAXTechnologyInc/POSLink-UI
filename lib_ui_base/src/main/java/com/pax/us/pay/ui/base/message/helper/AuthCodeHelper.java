package com.pax.us.pay.ui.base.message.helper;

import android.os.Bundle;

import com.pax.us.pay.ui.constant.parameter.Request;

public class AuthCodeHelper extends BaseHelper {
    public void sendObjNext(String authCode) {
        Bundle bundle = new Bundle();
        bundle.putString(Request.Text.PARA_AUTH_CODE, authCode);
        super.sendObjNext(bundle);
    }
}
