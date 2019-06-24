package com.pax.us.pay.ui.base.message.helper;

import com.pax.us.pay.ui.constant.parameter.Request;

import java.util.HashMap;
import java.util.Map;

public class SignatureHelper extends BaseHelper {
    public void sendObjNext(short[] traceData) {
        Map<String, short[]> shortMap = new HashMap<>();
        shortMap.put(Request.Text.PARA_SIGNATURE, traceData);
        super.sendObjNext(shortMap);
    }
}
