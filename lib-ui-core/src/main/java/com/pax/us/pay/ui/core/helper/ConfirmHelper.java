package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IInformationListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.pax.us.pay.ui.core.api.IUIListener;

public class ConfirmHelper extends BaseActionHelper {
    public ConfirmHelper(@Nullable IConfirmListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    @Override
    public void sendNext() {
        super.sendNext();
    }

    @Override
    protected void showUI(@Nullable IUIListener uiListener, @NonNull Bundle bundle) {
        super.showUI(uiListener, bundle);
        String[] key = bundle.getStringArray(EntryExtraData.PARAM_INFORMATION_KET);
        String[] value = bundle.getStringArray(EntryExtraData.PARAM_INFORMATION_VALUE);
        if ((key != null) && (key.length > 0) && (value != null) && (value.length > 0) && (key.length == value.length)) {
            if (uiListener instanceof IInformationListener) {
                ((IInformationListener) uiListener).onShowInformation(key, value);
            }
        } else
            throw new IllegalStateException("Information Format Error");
    }

    public interface IConfirmListener extends IMessageListener, IInformationListener {
    }

}
