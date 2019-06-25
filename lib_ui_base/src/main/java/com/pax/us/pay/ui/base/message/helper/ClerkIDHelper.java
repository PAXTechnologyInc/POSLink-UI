package com.pax.us.pay.ui.base.message.helper;

import android.os.Bundle;

import com.pax.us.pay.ui.constant.parameter.Request;

public class ClerkIDHelper extends BaseHelper {
    public void sendObjNext(String clerkId) {
        Bundle bundle = new Bundle();
        bundle.putString(Request.Text.PARA_CLERK_ID, clerkId);
        super.sendObjNext(bundle);
    }
}
