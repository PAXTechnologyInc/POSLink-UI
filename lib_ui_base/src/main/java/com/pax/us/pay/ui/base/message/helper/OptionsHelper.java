package com.pax.us.pay.ui.base.message.helper;

import android.os.Bundle;

import com.pax.us.pay.ui.constant.parameter.Request;

public class OptionsHelper extends BaseHelper {
    public void sendObjNext(int index) {
        Bundle bundle = new Bundle();
        bundle.putInt(Request.Option.PARA_INDEX, index);
        super.sendObjNext(bundle);
    }
}
