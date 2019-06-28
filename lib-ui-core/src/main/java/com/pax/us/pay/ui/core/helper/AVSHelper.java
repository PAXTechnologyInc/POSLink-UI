package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;

public class AVSHelper extends BaseActionHelper {
    public void sendNext(String line1Text, String line2Text) {
        Bundle bundle = new Bundle();
        bundle.putString(EntryRequest.PARAM_ADDRESS, line1Text);
        bundle.putString(EntryRequest.PARAM_ZIP_CODE, line2Text);
        super.sendNext(bundle);
    }
}
