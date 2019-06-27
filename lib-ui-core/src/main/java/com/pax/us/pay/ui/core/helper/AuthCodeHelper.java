package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;

import com.pax.us.pay.ui.constant.parameter.Request;
import com.pax.us.pay.ui.core.BaseActionHelper;

public class AuthCodeHelper extends BaseActionHelper {
    public void sendNext(String authCode) {
        Bundle bundle = new Bundle();
        bundle.putString(Request.Text.PARA_AUTH_CODE, authCode);
        super.sendNext(bundle);
    }
}
