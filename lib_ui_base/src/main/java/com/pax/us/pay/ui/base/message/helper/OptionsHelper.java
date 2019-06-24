package com.pax.us.pay.ui.base.message.helper;

import com.pax.us.pay.ui.constant.parameter.Request;

import java.util.HashMap;
import java.util.Map;

public class OptionsHelper extends BaseHelper {
    public void sendObjNext(int index) {
        Map<String, Integer> optionsMap = new HashMap<>();
        optionsMap.put(Request.Option.PARA_INDEX, index);
        super.sendObjNext(optionsMap);
    }
}
