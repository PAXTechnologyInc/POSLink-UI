package com.pax.us.pay.ui.base.message.helper;

import android.os.Bundle;

import com.pax.us.pay.ui.constant.parameter.Request;

public class SecurityHelper extends BaseHelper {
    public void sendObjNext() {
        super.sendObjNext();
    }

    public void sendObjNext(int x, int y, int width, int height) {
        Bundle bundle = new Bundle();
        bundle.putInt(Request.Security.PARA_X, x);
        bundle.putInt(Request.Security.PARA_Y, y);
        bundle.putInt(Request.Security.PARA_WIDTH, width);
        bundle.putInt(Request.Security.PARA_HEIGHT, height);
        super.sendObjNext(bundle);
    }
}
