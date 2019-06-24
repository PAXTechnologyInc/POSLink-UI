package com.pax.us.pay.ui.base.message.helper;

import com.pax.us.pay.ui.constant.parameter.Request;

import java.util.HashMap;
import java.util.Map;

public class TipHelper extends BaseHelper {
    public void sendObjNext(long amount) {
        Map<String, Long> amountMap = new HashMap<>();
        amountMap.put(Request.Text.PARA_TIP, amount);
        super.sendObjNext(amountMap);
    }
}
