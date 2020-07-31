package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IInterfaceStyleListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IOptionListener;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.pax.us.pay.ui.core.api.IUIListener;

public class SelectMerchantHelper extends BaseActionHelper {
    public SelectMerchantHelper(@Nullable ISelectMerchantOptionListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(int index) {
        Bundle bundle = new Bundle();
        bundle.putInt(EntryRequest.PARAM_INDEX, index);
        super.sendNext(bundle);
    }

    @Override
    protected void showUI(@Nullable IUIListener uiListener, @NonNull Bundle bundle) {
        super.showUI(uiListener, bundle);
        if (uiListener instanceof IInterfaceStyleListener && bundle.containsKey(EntryExtraData.PARAM_INTERFACE_STYLES)) {
            String style = bundle.getString(EntryExtraData.PARAM_INTERFACE_STYLES);
            if (!TextUtils.isEmpty(style)) {
                ((IInterfaceStyleListener) uiListener).onShowInterfaceStyle(style);
            }
        }

        if (uiListener instanceof IOptionListener && bundle.containsKey(EntryExtraData.PARAM_OPTIONS)) {
            String[] options = bundle.getStringArray(EntryExtraData.PARAM_OPTIONS);
            if (options != null && options.length > 0)
                ((IOptionListener) uiListener).onShowOptions(options);
        }
    }

    public interface ISelectMerchantOptionListener extends IMessageListener, IOptionListener, IInterfaceStyleListener {
    }

}
