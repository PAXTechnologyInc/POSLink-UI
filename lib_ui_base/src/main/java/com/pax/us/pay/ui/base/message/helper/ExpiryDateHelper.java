package com.pax.us.pay.ui.base.message.helper;

import com.pax.us.pay.ui.constant.parameter.Request;

import java.util.HashMap;
import java.util.Map;

public class ExpiryDateHelper extends BaseHelper {
    public void sendObjNext(String date) {
        Map<String, String> dateMap = new HashMap<>();
        dateMap.put(Request.Text.PARA_EXPIRY_DATE, date.replace("/", ""));
        super.sendObjNext(dateMap);
    }
}
