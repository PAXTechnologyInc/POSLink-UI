package com.pax.us.pay.ui.core.message.helper;

import android.os.Bundle;

import com.pax.us.pay.ui.constant.parameter.Request;
import com.pax.us.pay.ui.core.message.BaseActionHelper;

public class OptionsHelper extends BaseActionHelper {
    public void sendNext(int index) {
        Bundle bundle = new Bundle();
        bundle.putInt(Request.Option.PARA_INDEX, index);
        super.sendNext(bundle);
    }
}
