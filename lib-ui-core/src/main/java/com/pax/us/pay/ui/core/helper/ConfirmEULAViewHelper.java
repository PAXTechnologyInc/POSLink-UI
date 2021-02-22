package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IEULAViewListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.pax.us.pay.ui.core.api.IUIListener;

public class ConfirmEULAViewHelper extends BaseActionHelper {
    public ConfirmEULAViewHelper(@Nullable IConfirmEULAViewListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(boolean flag) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(EntryRequest.PARAM_CONFIRMED, flag);
        super.sendNext(bundle);
    }

    @Override
    protected void showUI(@Nullable IUIListener uiListener, @NonNull Bundle bundle) {
        super.showUI(uiListener, bundle);
        if (uiListener instanceof IEULAViewListener && bundle.containsKey(EntryExtraData.PARAM_EULA_URI)) {
            String eulaUri = bundle.getString(EntryExtraData.PARAM_EULA_URI);
            if (!TextUtils.isEmpty(eulaUri)) {
                ((IEULAViewListener) uiListener).onShowEULAView(eulaUri);
            }
        }
    }

    public interface IConfirmEULAViewListener extends IMessageListener, IEULAViewListener {
    }

}
