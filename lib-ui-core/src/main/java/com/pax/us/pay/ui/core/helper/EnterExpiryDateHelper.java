package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IHasPhyKeyboardListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;

public class EnterExpiryDateHelper extends BaseActionHelper {
    public EnterExpiryDateHelper(@Nullable IEnterExpiryDateListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(String date) {
        Bundle bundle = new Bundle();
        bundle.putString(EntryRequest.PARAM_EXPIRY_DATE, date.replace("/", ""));
        super.sendNext(bundle);
    }

    public interface IEnterExpiryDateListener extends IMessageListener, IHasPhyKeyboardListener {
    }
}
