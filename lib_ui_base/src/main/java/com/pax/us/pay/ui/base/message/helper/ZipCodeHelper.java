package com.pax.us.pay.ui.base.message.helper;

import android.os.Bundle;

import com.pax.us.pay.ui.constant.parameter.Request;

public class ZipCodeHelper extends BaseHelper {
    public void sendObjNext(String zipCode) {
        Bundle bundle = new Bundle();
        bundle.putString(Request.Text.PARA_ZIP_CODE, zipCode);
        super.sendObjNext(bundle);
    }
}
