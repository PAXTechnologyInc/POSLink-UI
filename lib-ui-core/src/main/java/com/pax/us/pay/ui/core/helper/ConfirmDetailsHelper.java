package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IDetailsListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.pax.us.pay.ui.core.api.IUIListener;

public class ConfirmDetailsHelper extends BaseActionHelper {
    public ConfirmDetailsHelper(@Nullable IConfirmDetailsListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    @Override
    public void sendNext() {
        super.sendNext();
    }

    @Override
    protected void showUI(@Nullable IUIListener uiListener, @NonNull Bundle bundle) {
        super.showUI(uiListener, bundle);
        String[] key = bundle.getStringArray(EntryExtraData.PARAM_INFORMATION_KEY);
        String[] value = bundle.getStringArray(EntryExtraData.PARAM_INFORMATION_VALUE);
        if ((key != null) && (key.length > 0) && (value != null) && (value.length > 0) && (key.length == value.length)) {
            if (uiListener instanceof IDetailsListener) {
                ((IDetailsListener) uiListener).onShowDetails(key, value);
            }
        } else
            throw new IllegalStateException("Information Format Error");
    }

    public interface IConfirmDetailsListener extends IMessageListener, IDetailsListener {
    }

}
