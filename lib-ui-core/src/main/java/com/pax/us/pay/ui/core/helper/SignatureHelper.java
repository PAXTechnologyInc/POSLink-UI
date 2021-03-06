package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IAmountListener;
import com.pax.us.pay.ui.core.api.ICurrencyListener;
import com.pax.us.pay.ui.core.api.IEnableCancelButtonListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.pax.us.pay.ui.core.api.ISignatureLineListener;
import com.pax.us.pay.ui.core.api.ITimeoutListener;
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
        if ((uiListener instanceof ICurrencyListener) &&
                (uiListener instanceof IAmountListener && bundle.containsKey(EntryExtraData.PARAM_TOTAL_AMOUNT))) {
            String currency = bundle.getString(EntryExtraData.PARAM_CURRENCY, "USD");
            ((ICurrencyListener) uiListener).onShowCurrency(currency, currency.equals(CurrencyType.POINT));

            ((IAmountListener) uiListener).onShowAmount(bundle.getLong(EntryExtraData.PARAM_TOTAL_AMOUNT));
        }

        if(uiListener instanceof ISignatureLineListener) {
            String signLine1 = null;
            String signLine2 = null;
            if (bundle.containsKey(EntryExtraData.PARAM_SIGNLINE1))
                signLine1 = bundle.getString(EntryExtraData.PARAM_SIGNLINE1, "");
            if (bundle.containsKey(EntryExtraData.PARAM_SIGNLINE2))
                signLine2 = bundle.getString(EntryExtraData.PARAM_SIGNLINE2, "");

            if (!TextUtils.isEmpty(signLine1) || !TextUtils.isEmpty(signLine2))
                ((ISignatureLineListener)uiListener).onShowSignatureLine(signLine1, signLine2);
        }

        if (uiListener instanceof IEnableCancelButtonListener) {
            ((IEnableCancelButtonListener) uiListener).onShowCancelButton(bundle.getBoolean(EntryExtraData.PARAM_ENABLE_CANCEL, false));
        }

        if (uiListener instanceof ITimeoutListener) {
            ((ITimeoutListener) uiListener).onShowTimeout(bundle.getLong(EntryExtraData.PARAM_TIMEOUT, 30 * 1000));
        }

    }

    public interface ISignatureListener extends IMessageListener, ICurrencyListener, IAmountListener, IEnableCancelButtonListener, ITimeoutListener, ISignatureLineListener {
    }
}
