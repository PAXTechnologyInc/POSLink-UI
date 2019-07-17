package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IPrinterStatusListener;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.pax.us.pay.ui.core.api.IUIListener;

public class SelectPrinterStatusHelper extends BaseActionHelper {
    public SelectPrinterStatusHelper(@Nullable ISelectPrinterStatusListener uiListener, @Nullable IRespStatus respStatus) {
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
        if (uiListener instanceof IPrinterStatusListener && bundle.containsKey(EntryExtraData.PARAM_PRINT_STATUS) &&
                bundle.containsKey(EntryExtraData.PARAM_OPTIONS)) {
            String printerStatus = bundle.getString(EntryExtraData.PARAM_PRINT_STATUS);
            String[] options = bundle.getStringArray(EntryExtraData.PARAM_OPTIONS);
            if ((!TextUtils.isEmpty(printerStatus)) && (options != null && options.length > 0)) {
                ((IPrinterStatusListener) uiListener).onShowPrinterStatus(printerStatus, options);
            }
        }
    }

    public interface ISelectPrinterStatusListener extends IMessageListener, IPrinterStatusListener {
    }

}
