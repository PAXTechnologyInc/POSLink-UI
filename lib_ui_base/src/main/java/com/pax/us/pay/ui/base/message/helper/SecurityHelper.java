package com.pax.us.pay.ui.base.message.helper;

import com.pax.us.pay.ui.constant.parameter.Request;

import java.util.HashMap;
import java.util.Map;

public class SecurityHelper extends BaseHelper {
    public void sendObjNext() {
        super.sendObjNext();
    }

    public void sendObjNext(int x, int y, int width, int height) {
        Map<String, Integer> integerMap = new HashMap<>();
        integerMap.put(Request.Security.PARA_X, Integer.valueOf(x));
        integerMap.put(Request.Security.PARA_Y, Integer.valueOf(y));
        integerMap.put(Request.Security.PARA_WIDTH, Integer.valueOf(width));
        integerMap.put(Request.Security.PARA_HEIGHT, Integer.valueOf(height));
        super.sendObjNext(integerMap);
    }
}
