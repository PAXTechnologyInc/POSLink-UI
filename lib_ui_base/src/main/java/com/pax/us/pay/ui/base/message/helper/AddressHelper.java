package com.pax.us.pay.ui.base.message.helper;

import android.os.Bundle;

import com.pax.us.pay.ui.constant.parameter.Request;

public class AddressHelper extends BaseHelper {
    public void sendObjNext(String address) {
        Bundle bundle = new Bundle();
        bundle.putString(Request.Text.PARA_ADDRESS, address);
        super.sendObjNext(bundle);
    }
}
