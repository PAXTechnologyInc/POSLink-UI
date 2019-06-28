package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.ICardListener;
import com.pax.us.pay.ui.core.api.IRespStatus;

public class SecurityHelper extends BaseActionHelper {

    public SecurityHelper(@Nullable ICardListener listener, @Nullable IRespStatus respStatus) {
        super(listener, respStatus);
    }

    @Override
    public void sendNext() {
        super.sendNext();
    }

    public void setSecurityArea(int x, int y, int width, int height) {
        Bundle bundle = new Bundle();
        bundle.putInt(EntryRequest.PARAM_X, x);
        bundle.putInt(EntryRequest.PARAM_Y, y);
        bundle.putInt(EntryRequest.PARAM_WIDTH, width);
        bundle.putInt(EntryRequest.PARAM_HEIGHT, height);
        super.setSecurityArea(bundle);
    }
}
