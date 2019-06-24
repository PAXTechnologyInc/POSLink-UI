package com.pax.us.pay.ui.base.message.helper;

import com.pax.us.pay.ui.constant.parameter.Request;

import java.util.HashMap;
import java.util.Map;

public class TransNumHelper extends BaseHelper {
    public void sendObjNext(String transNumCode) {
        Map<String, Integer> integerMap = new HashMap<>();
        integerMap.put(Request.Text.PARA_TRANS_NO, Integer.valueOf(transNumCode));
        super.sendObjNext(integerMap);
    }
}
