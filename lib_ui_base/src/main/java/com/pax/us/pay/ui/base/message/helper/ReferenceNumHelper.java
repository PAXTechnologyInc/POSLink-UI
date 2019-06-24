package com.pax.us.pay.ui.base.message.helper;

import com.pax.us.pay.ui.constant.parameter.Request;

import java.util.HashMap;
import java.util.Map;

public class ReferenceNumHelper extends BaseHelper {
    public void sendObjNext(String referenceNumber) {
        Map<String, String> map = new HashMap<>();
        map.put(Request.Text.PARA_REFERENCE_NUMBER, referenceNumber);
        super.sendObjNext(map);
    }
}
