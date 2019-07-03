package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.ICashbackOptionListener;
import com.pax.us.pay.ui.core.api.ICurrencyListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.pax.us.pay.ui.core.api.IUIListener;

public class CashbackAmountHelper extends BaseActionHelper {

    protected CashbackAmountHelper(@Nullable ICashbackListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(long amount) {
        Bundle bundle = new Bundle();
        bundle.putLong(EntryRequest.PARAM_CASHBACK_AMOUNT, amount);
        super.sendNext(bundle);
    }

    @Override
    protected void showUI(@Nullable IUIListener uiListener, @NonNull Bundle bundle) {
        super.showUI(uiListener, bundle);
        if (uiListener instanceof ICurrencyListener) {
            String currency = bundle.getString(EntryExtraData.PARAM_CURRENCY, "");
            if (currency.length() > 0)
                ((ICashbackListener) uiListener).onShowCurrency(currency);
        }
        if (uiListener instanceof ICashbackOptionListener && bundle.containsKey(EntryExtraData.PARAM_CASHBACK_OPTIONS)) {
            String[] options = bundle.getStringArray(EntryExtraData.PARAM_CASHBACK_OPTIONS);
            if (options != null && options.length > 0)
                ((ICashbackOptionListener) uiListener).onShowCashbackOptions(options);
        }
    }

    public interface ICashbackListener extends IMessageListener, ICurrencyListener {
    }
}
