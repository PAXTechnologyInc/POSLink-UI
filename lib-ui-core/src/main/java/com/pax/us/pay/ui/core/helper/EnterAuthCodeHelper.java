package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;

public class EnterAuthCodeHelper extends BaseActionHelper {
    public EnterAuthCodeHelper(@Nullable IEnterAuthCodeListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(String authCode) {
        Bundle bundle = new Bundle();
        bundle.putString(EntryRequest.PARAM_AUTH_CODE, authCode);
        super.sendNext(bundle);
    }

    public interface IEnterAuthCodeListener extends IMessageListener {
    }

}
