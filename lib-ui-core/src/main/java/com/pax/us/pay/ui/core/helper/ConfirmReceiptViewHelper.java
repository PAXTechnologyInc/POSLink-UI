package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IReceiptViewListener;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.pax.us.pay.ui.core.api.ITimeoutListener;
import com.pax.us.pay.ui.core.api.IUIListener;

public class ConfirmReceiptViewHelper extends BaseActionHelper {
    public ConfirmReceiptViewHelper(@Nullable IConfirmReceiptViewListener uiListener, @Nullable IRespStatus respStatus) {
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
        if (uiListener instanceof IReceiptViewListener && bundle.containsKey(EntryExtraData.PARAM_RECEIPT_URI)) {
            String receiptUri = bundle.getString(EntryExtraData.PARAM_RECEIPT_URI);
            if (!TextUtils.isEmpty(receiptUri)) {
                ((IReceiptViewListener) uiListener).onShowReceiptView(receiptUri);
                if (uiListener instanceof ITimeoutListener && bundle.containsKey(EntryExtraData.PARAM_TIMEOUT)) {
                    long timeOut = bundle.getLong(EntryExtraData.PARAM_TIMEOUT, 30);
                    ((ITimeoutListener) uiListener).onShowTimeout(timeOut);
                }
            }
        }
    }

    public interface IConfirmReceiptViewListener extends IMessageListener, IReceiptViewListener, ITimeoutListener {
    }

}
