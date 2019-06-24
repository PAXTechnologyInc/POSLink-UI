package com.pax.us.pay.ui.base.message.helper;

import com.pax.us.pay.ui.constant.parameter.Request;

import java.util.HashMap;
import java.util.Map;

public class ClerkIDHelper extends BaseHelper {
    public void sendObjNext(String clerkId) {
        Map<String, String> map = new HashMap<>();
        map.put(Request.Text.PARA_CLERK_ID, clerkId);
        super.sendObjNext(map);
    }
}
