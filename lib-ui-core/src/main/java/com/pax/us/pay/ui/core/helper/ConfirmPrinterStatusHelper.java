package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.text.TextUtils;

import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IPrinterStatusListener;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.pax.us.pay.ui.core.api.IUIListener;

public class ConfirmPrinterStatusHelper extends BaseActionHelper {
    public ConfirmPrinterStatusHelper(@Nullable IConfirmPrinterStatusListener uiListener, @Nullable IRespStatus respStatus) {
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
        if (uiListener instanceof IPrinterStatusListener && bundle.containsKey(EntryExtraData.PARAM_PRINT_STATUS)) {
            String printerStatus = bundle.getString(EntryExtraData.PARAM_PRINT_STATUS);
            if (!TextUtils.isEmpty(printerStatus)) {
                ((IPrinterStatusListener) uiListener).onShowPrinterStatus(printerStatus);
            }
        }
    }

    public interface IConfirmPrinterStatusListener extends IMessageListener, IPrinterStatusListener {
    }

}
