package com.pax.us.pay.ui.core.message.helper;

import android.os.Bundle;

import com.pax.us.pay.ui.constant.parameter.Request;
import com.pax.us.pay.ui.core.message.BaseActionHelper;

public class ZipCodeHelper extends BaseActionHelper {
    public void sendObjNext(String zipCode) {
        Bundle bundle = new Bundle();
        bundle.putString(Request.Text.PARA_ZIP_CODE, zipCode);
        super.sendObjNext(bundle);
    }
}
