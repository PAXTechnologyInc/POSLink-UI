package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryExtraData;
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
        if (uiListener instanceof ICurrencyListener) {
            String currency = bundle.getString(EntryExtraData.PARAM_CURRENCY, "");
            if (currency.length() > 0)
                ((ICurrencyListener) uiListener).onShowCurrency(currency);
        }

        if (uiListener instanceof IAmountListener && bundle.containsKey(EntryExtraData.PARAM_DISP_AMOUNT)) {
            ((IAmountListener) uiListener).onShowAmount(bundle.getLong(EntryExtraData.PARAM_DISP_AMOUNT));
        }
    }

    public interface ISignatureListener extends IMessageListener, ICurrencyListener, IAmountListener {
    }
}
