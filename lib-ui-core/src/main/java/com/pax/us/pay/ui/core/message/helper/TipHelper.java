package com.pax.us.pay.ui.core.message.helper;

import android.os.Bundle;

import com.pax.us.pay.ui.constant.parameter.Request;
import com.pax.us.pay.ui.core.message.BaseActionHelper;

public class TipHelper extends BaseActionHelper {
    public void sendObjNext(long amount) {
        Bundle bundle = new Bundle();
        bundle.putLong(Request.Text.PARA_TIP, amount);
        super.sendObjNext(bundle);
    }
}
