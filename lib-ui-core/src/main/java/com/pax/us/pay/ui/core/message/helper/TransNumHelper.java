package com.pax.us.pay.ui.core.message.helper;

import android.os.Bundle;

import com.pax.us.pay.ui.constant.parameter.Request;
import com.pax.us.pay.ui.core.message.BaseActionHelper;

public class TransNumHelper extends BaseActionHelper {
    public void sendObjNext(int transNumCode) {
        Bundle bundle = new Bundle();
        bundle.putInt(Request.Text.PARA_TRANS_NO, transNumCode);
        super.sendObjNext(bundle);
    }
}
