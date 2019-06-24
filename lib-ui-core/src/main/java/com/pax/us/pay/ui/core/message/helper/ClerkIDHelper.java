package com.pax.us.pay.ui.core.message.helper;

import android.os.Bundle;

import com.pax.us.pay.ui.constant.parameter.Request;
import com.pax.us.pay.ui.core.message.BaseActionHelper;

public class ClerkIDHelper extends BaseActionHelper {
    public void sendObjNext(String clerkId) {
        Bundle bundle = new Bundle();
        bundle.putString(Request.Text.PARA_CLERK_ID, clerkId);
        super.sendObjNext(bundle);
    }
}
