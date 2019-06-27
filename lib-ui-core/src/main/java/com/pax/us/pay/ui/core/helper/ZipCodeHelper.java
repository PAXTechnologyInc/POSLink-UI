package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;

import com.pax.us.pay.ui.constant.parameter.Request;
import com.pax.us.pay.ui.core.BaseActionHelper;

public class ZipCodeHelper extends BaseActionHelper {
    public void sendNext(String zipCode) {
        Bundle bundle = new Bundle();
        bundle.putString(Request.Text.PARA_ZIP_CODE, zipCode);
        super.sendNext(bundle);
    }
}
