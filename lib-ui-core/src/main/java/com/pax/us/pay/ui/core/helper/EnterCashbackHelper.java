package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IAmountListener;
import com.pax.us.pay.ui.core.api.ICashbackOptionListener;
import com.pax.us.pay.ui.core.api.ICurrencyListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.pax.us.pay.ui.core.api.IUIListener;

public class EnterCashbackHelper extends BaseActionHelper {

    public EnterCashbackHelper(@Nullable IEnterCashbackListener listener, @Nullable IRespStatus respStatus) {
        super(listener, respStatus);
    }

    @Override
    protected void showUI(@Nullable IUIListener uiListener, @NonNull Bundle bundle) {
        String[] rateOptions = null, options = null;
        super.showUI(uiListener, bundle);
        if (uiListener instanceof ICurrencyListener) {
            String currency = bundle.getString(EntryExtraData.PARAM_CURRENCY, "USD");
            ((ICurrencyListener) uiListener).onShowCurrency(currency, false);
        }

        if (uiListener instanceof IAmountListener && bundle.containsKey(EntryExtraData.PARAM_TOTAL_AMOUNT)) {
            ((IAmountListener) uiListener).onShowAmount(bundle.getLong(EntryExtraData.PARAM_TOTAL_AMOUNT));
        }

        if (uiListener instanceof ICashbackOptionListener && bundle.containsKey(EntryExtraData.PARAM_CASHBACK_RATE_OPTIONS)) {
            rateOptions = bundle.getStringArray(EntryExtraData.PARAM_CASHBACK_RATE_OPTIONS);
        }

        if (uiListener instanceof ICashbackOptionListener && bundle.containsKey(EntryExtraData.PARAM_CASHBACK_OPTIONS)) {
            options = bundle.getStringArray(EntryExtraData.PARAM_CASHBACK_OPTIONS);
        }

        if (options != null && options.length > 0) {
            if ((rateOptions != null && rateOptions.length > 0)) {
                if (rateOptions.length == options.length) {
                    if (options != null && options.length > 0) {
                        ((ICashbackOptionListener) uiListener).onShowCashbackOptions(options, rateOptions);
                    }
                }
            } else {
                ((ICashbackOptionListener) uiListener).onShowCashbackOptions(options, null);
            }
        }
    }

    public void sendNext(long amount) {
        Bundle bundle = new Bundle();
        bundle.putLong(EntryRequest.PARAM_CASHBACK_AMOUNT, amount);
        super.sendNext(bundle);
    }

    public interface IEnterCashbackListener extends IMessageListener, ICurrencyListener, IAmountListener, ICashbackOptionListener {
    }
}
