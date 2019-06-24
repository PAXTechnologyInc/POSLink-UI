package com.pax.us.pay.ui.base.message.helper;

import com.pax.us.pay.ui.constant.parameter.Request;

import java.util.HashMap;
import java.util.Map;

public class AddressHelper extends BaseHelper {
    public void sendObjNext(String address) {
        Map<String, String> addressMap = new HashMap<>();
        addressMap.put(Request.Text.PARA_ADDRESS, address);
        super.sendObjNext(addressMap);
    }
}
