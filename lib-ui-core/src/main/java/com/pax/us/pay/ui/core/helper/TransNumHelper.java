package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;

public class TransNumHelper extends BaseActionHelper {
    public void sendNext(int transNumCode) {
        Bundle bundle = new Bundle();
        bundle.putInt(EntryRequest.PARAM_TRANS_NO, transNumCode);
        super.sendNext(bundle);
    }
}
