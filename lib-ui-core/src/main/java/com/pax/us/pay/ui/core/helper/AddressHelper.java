package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;

public class AddressHelper extends BaseActionHelper {

    public void sendNext(String address) {
        Bundle bundle = new Bundle();
        bundle.putString(EntryRequest.PARAM_ADDRESS, address);
        super.sendNext(bundle);
    }
}
