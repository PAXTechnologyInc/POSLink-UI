package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.ICurrencyListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.pax.us.pay.ui.core.api.IUIListener;

public class EnterAmountHelper extends BaseActionHelper {

    public EnterAmountHelper(@Nullable IEnterAmountListener listener, @Nullable IRespStatus respStatus) {
        super(listener, respStatus);
    }

    @Override
    protected void showUI(@Nullable IUIListener uiListener, @NonNull Bundle bundle) {
        super.showUI(uiListener, bundle);
        if (uiListener instanceof ICurrencyListener) {
            String currency = bundle.getString(EntryExtraData.PARAM_CURRENCY, "USD");
            if (currency.equals(CurrencyType.POINT))
                ((ICurrencyListener) uiListener).onShowPoint();
            else
                ((ICurrencyListener) uiListener).onShowCurrency(currency);
        }
    }

    public void sendNext(long amount) {
        Bundle bundle = new Bundle();
        bundle.putLong(EntryRequest.PARAM_AMOUNT, amount);
        super.sendNext(bundle);
    }

    public interface IEnterAmountListener extends IMessageListener, ICurrencyListener {
    }
}
