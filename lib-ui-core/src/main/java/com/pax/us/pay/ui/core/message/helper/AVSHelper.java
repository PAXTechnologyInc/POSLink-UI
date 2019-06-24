package com.pax.us.pay.ui.core.message.helper;

import android.os.Bundle;

import com.pax.us.pay.ui.constant.parameter.Request;
import com.pax.us.pay.ui.core.message.BaseActionHelper;

public class AVSHelper extends BaseActionHelper {
    public void sendObjNext(String line1Text, String line2Text) {
        Bundle bundle = new Bundle();
        bundle.putString(Request.Text.PARA_ADDRESS, line1Text);
        bundle.putString(Request.Text.PARA_ZIP_CODE, line2Text);
        super.sendObjNext(bundle);
    }
}
