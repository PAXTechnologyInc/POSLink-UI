package com.pax.us.pay.ui.base.message.helper;

import com.pax.us.pay.ui.constant.parameter.Request;

import java.util.HashMap;
import java.util.Map;

public class AVSHelper extends BaseHelper {
    public void sendObjNext(String line1Text, String line2Text) {
        Map<String, String> voucherMap = new HashMap<>();
        voucherMap.put(Request.Text.PARA_ADDRESS, line1Text);
        voucherMap.put(Request.Text.PARA_ZIP_CODE, line2Text);
        super.sendObjNext(voucherMap);
    }
}
