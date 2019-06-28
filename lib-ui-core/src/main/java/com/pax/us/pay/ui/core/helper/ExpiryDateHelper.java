package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;

public class ExpiryDateHelper extends BaseActionHelper {
    public void sendNext(String date) {
        Bundle bundle = new Bundle();
        bundle.putString(EntryRequest.PARAM_EXPIRY_DATE, date.replace("/", ""));
        super.sendNext(bundle);
    }
}
