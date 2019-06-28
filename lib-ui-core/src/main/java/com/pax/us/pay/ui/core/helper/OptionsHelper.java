package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;

public class OptionsHelper extends BaseActionHelper {
    public void sendNext(int index) {
        Bundle bundle = new Bundle();
        bundle.putInt(EntryRequest.PARAM_INDEX, index);
        super.sendNext(bundle);
    }
}
