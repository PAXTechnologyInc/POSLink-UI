package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryInput;
import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
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
        super.showUI(uiListener, bundle);
        if (uiListener instanceof ICurrencyListener && bundle.containsKey(EntryInput.PARAM_CURRENCY)) {
            ((ICurrencyListener) uiListener).onShowCurrency(bundle.getString(EntryInput.PARAM_CURRENCY));
        }
        if (uiListener instanceof ICashbackOptionListener && bundle.containsKey(EntryInput.PARAM_CASHBACK_OPTIONS)) {
            ((ICashbackOptionListener) uiListener).onShowCashbackOptions(bundle.getStringArray(EntryInput.PARAM_CASHBACK_OPTIONS));
        }
    }

    public void sendNext(long amount) {
        Bundle bundle = new Bundle();
        bundle.putLong(EntryRequest.PARAM_CASHBACK_AMOUNT, amount);
        super.sendNext(bundle);
    }

    public interface IEnterCashbackListener extends IMessageListener, ICurrencyListener, ICashbackOptionListener {
    }
}
