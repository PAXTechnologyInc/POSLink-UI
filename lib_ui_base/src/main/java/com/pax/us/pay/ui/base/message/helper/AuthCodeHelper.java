package com.pax.us.pay.ui.base.message.helper;

import com.pax.us.pay.ui.constant.parameter.Request;

import java.util.HashMap;
import java.util.Map;

public class AuthCodeHelper extends BaseHelper {
    public void sendObjNext(String authCode) {
        Map<String, String> authCodeMap = new HashMap<>();
        authCodeMap.put(Request.Text.PARA_AUTH_CODE, authCode);
        super.sendObjNext(authCodeMap);
    }
}
