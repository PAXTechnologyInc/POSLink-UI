package com.pax.us.pay.ui.base.message.helper;

import com.pax.us.pay.ui.constant.parameter.Request;

import java.util.HashMap;
import java.util.Map;

public class ZipCodeHelper extends BaseHelper {
    public void sendObjNext(String zipCode) {
        Map<String, String> zipMap = new HashMap<>();
        zipMap.put(Request.Text.PARA_ZIP_CODE, zipCode);
        super.sendObjNext(zipMap);
    }
}
