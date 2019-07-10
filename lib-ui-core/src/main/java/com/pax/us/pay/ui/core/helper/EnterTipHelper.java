package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IAmountListener;
import com.pax.us.pay.ui.core.api.ICurrencyListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.pax.us.pay.ui.core.api.ITipOptionListener;
import com.pax.us.pay.ui.core.api.IUIListener;

public class EnterTipHelper extends BaseActionHelper {

    public EnterTipHelper(@Nullable IEnterTipListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(long amount) {
        Bundle bundle = new Bundle();
        bundle.putLong(EntryRequest.PARAM_TIP, amount);
        super.sendNext(bundle);
    }

    @Override
    protected void showUI(@Nullable IUIListener uiListener, @NonNull Bundle bundle) {
        super.showUI(uiListener, bundle);
        if (uiListener instanceof ICurrencyListener && bundle.containsKey(EntryExtraData.PARAM_CURRENCY)) {
            String currency = bundle.getString(EntryExtraData.PARAM_CURRENCY, "USD");
            if (currency.equals(CurrencyType.POINT))
                ((ICurrencyListener) uiListener).onShowPoint();
            else
                ((ICurrencyListener) uiListener).onShowCurrency(currency);
        }

        if (uiListener instanceof IAmountListener && bundle.containsKey(EntryExtraData.PARAM_TOTAL_AMOUNT)) {
            Object obj = bundle.get(EntryExtraData.PARAM_TOTAL_AMOUNT);
            if (obj != null)
                ((IAmountListener) uiListener).onShowAmount((long) obj);
        }

        if (uiListener instanceof ITipOptionListener && bundle.containsKey(EntryExtraData.PARAM_TIP_OPTIONS)) {
            String[] options = bundle.getStringArray(EntryExtraData.PARAM_TIP_OPTIONS);
            if (options != null && options.length > 0)
                ((ITipOptionListener) uiListener).onShowTipOptions(options);
        }
    }

    public interface IEnterTipListener extends IMessageListener, ICurrencyListener, IAmountListener, ITipOptionListener {
    }

}
