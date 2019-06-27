package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;

import com.pax.us.pay.ui.constant.parameter.Request;
import com.pax.us.pay.ui.core.BaseActionHelper;

public class SecurityHelper extends BaseActionHelper {

    @Override
    public void sendNext() {
        super.sendNext();
    }

    public void setSecurityArea(int x, int y, int width, int height) {
        Bundle bundle = new Bundle();
        bundle.putInt(Request.Security.PARA_X, x);
        bundle.putInt(Request.Security.PARA_Y, y);
        bundle.putInt(Request.Security.PARA_WIDTH, width);
        bundle.putInt(Request.Security.PARA_HEIGHT, height);
        super.setSecurityArea(bundle);
    }
}
