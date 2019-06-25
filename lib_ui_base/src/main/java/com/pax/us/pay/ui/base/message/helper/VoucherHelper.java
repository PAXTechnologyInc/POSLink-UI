package com.pax.us.pay.ui.base.message.helper;

import android.os.Bundle;

import com.pax.us.pay.ui.constant.parameter.Request;

public class VoucherHelper extends BaseHelper {
    public void sendObjNext(String line1Text, String line2Text) {
        Bundle bundle = new Bundle();
        bundle.putString(Request.Text.PARA_VOUCHER_NO, line1Text);
        bundle.putString(Request.Text.PARA_AUTH_CODE, line2Text);
        super.sendObjNext(bundle);
    }
}
