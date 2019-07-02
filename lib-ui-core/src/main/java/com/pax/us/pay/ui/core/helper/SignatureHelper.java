package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryInput;
import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IAmountListener;
import com.pax.us.pay.ui.core.api.ICurrencyListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.pax.us.pay.ui.core.api.IUIListener;

public class SignatureHelper extends BaseActionHelper {
    public SignatureHelper(@Nullable ISignatureListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(short[] traceData) {
        Bundle bundle = new Bundle();
        bundle.putShortArray(EntryRequest.PARAM_SIGNATURE, traceData);
        super.sendNext(bundle);
    }

    @Override
    protected void showUI(@Nullable IUIListener uiListener, @NonNull Bundle bundle) {
        super.showUI(uiListener, bundle);
        if (uiListener instanceof ICurrencyListener && bundle.containsKey(EntryInput.PARAM_CURRENCY)) {
            ((ICurrencyListener) uiListener).onShowCurrency(bundle.getString(EntryInput.PARAM_CURRENCY));
        }

        if (uiListener instanceof IAmountListener && bundle.containsKey(EntryInput.PARAM_DISP_AMOUNT)) {
            ((IAmountListener) uiListener).onShowAmount(bundle.getLong(EntryInput.PARAM_DISP_AMOUNT));
        }
    }

    public interface ISignatureListener extends IMessageListener, ICurrencyListener, IAmountListener {
    }
}
