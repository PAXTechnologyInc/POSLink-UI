package com.pax.us.pay.ui.core.message.helper;

import android.os.Bundle;

import com.pax.us.pay.ui.constant.parameter.Request;
import com.pax.us.pay.ui.core.message.BaseActionHelper;

public class VoucherHelper extends BaseActionHelper {
    public void sendObjNext(String voucherNo, String authCode) {
        Bundle bundle = new Bundle();
        bundle.putString(Request.Text.PARA_VOUCHER_NO, voucherNo);
        bundle.putString(Request.Text.PARA_AUTH_CODE, authCode);
        super.sendObjNext(bundle);
    }
}
