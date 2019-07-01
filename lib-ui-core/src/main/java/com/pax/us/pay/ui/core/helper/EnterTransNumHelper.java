package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.pax.us.pay.ui.core.api.IUIListener;

public class EnterTransNumHelper extends BaseActionHelper {
    public EnterTransNumHelper(@Nullable IUIListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(int transNumCode) {
        Bundle bundle = new Bundle();
        bundle.putInt(EntryRequest.PARAM_TRANS_NUMBER, transNumCode);
        super.sendNext(bundle);
    }

    public interface IEnterZipCodeListener extends IMessageListener {
    }
}
