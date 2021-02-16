package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IHasPhyKeyboardListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;

public class EnterTransNumHelper extends BaseActionHelper {
    public EnterTransNumHelper(@Nullable IEnterTransNumberListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(long transNumCode) {
        Bundle bundle = new Bundle();
        bundle.putLong(EntryRequest.PARAM_TRANS_NUMBER, transNumCode);
        super.sendNext(bundle);
    }

    public interface IEnterTransNumberListener extends IMessageListener, IHasPhyKeyboardListener {
    }
}
