package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;

public class ClerkIDHelper extends BaseActionHelper {
    public void sendNext(String clerkId) {
        Bundle bundle = new Bundle();
        bundle.putString(EntryRequest.PARAM_CLERK_ID, clerkId);
        super.sendNext(bundle);
    }
}
