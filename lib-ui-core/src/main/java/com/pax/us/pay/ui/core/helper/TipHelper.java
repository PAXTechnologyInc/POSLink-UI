package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;

import com.pax.us.pay.ui.constant.parameter.Request;
import com.pax.us.pay.ui.core.BaseActionHelper;

public class TipHelper extends BaseActionHelper {
    public void sendNext(long amount) {
        Bundle bundle = new Bundle();
        bundle.putLong(Request.Text.PARA_TIP, amount);
        super.sendNext(bundle);
    }
}
