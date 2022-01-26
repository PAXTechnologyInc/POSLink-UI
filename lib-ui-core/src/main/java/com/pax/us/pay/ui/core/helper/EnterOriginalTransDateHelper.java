package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;

public class EnterOriginalTransDateHelper extends BaseActionHelper {
    public EnterOriginalTransDateHelper(@Nullable IEnterOriginalTransDateListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(String origDate) {
        Bundle bundle = new Bundle();
        bundle.putString(EntryRequest.PARAM_ORIG_DATE, origDate.replace("/", ""));
        super.sendNext(bundle);
    }

    public interface IEnterOriginalTransDateListener extends IMessageListener {
    }
}
