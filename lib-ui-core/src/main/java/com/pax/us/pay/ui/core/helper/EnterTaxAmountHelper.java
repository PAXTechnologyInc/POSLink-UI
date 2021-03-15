package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IAmountListener;
import com.pax.us.pay.ui.core.api.ICurrencyListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.pax.us.pay.ui.core.api.IUIListener;

public class EnterTaxAmountHelper extends BaseActionHelper {

    public EnterTaxAmountHelper(@Nullable IEnterTaxAmountListener listener, @Nullable IRespStatus respStatus) {
        super(listener, respStatus);
    }

    @Override
    protected void showUI(@Nullable IUIListener uiListener, @NonNull Bundle bundle) {
        super.showUI(uiListener, bundle);
        if (uiListener instanceof ICurrencyListener) {
            String currency = bundle.getString(EntryExtraData.PARAM_CURRENCY, "USD");
            ((ICurrencyListener) uiListener).onShowCurrency(currency, currency.equals(CurrencyType.POINT));
        }
        if (uiListener instanceof IAmountListener && bundle.containsKey(EntryExtraData.PARAM_BASE_AMOUNT)) {
            ((IAmountListener) uiListener).onShowAmount(bundle.getLong(EntryExtraData.PARAM_BASE_AMOUNT));
        }
    }

    public void sendNext(long taxAmount) {
        Bundle bundle = new Bundle();
        bundle.putLong(EntryRequest.PARAM_TAX_AMOUNT, taxAmount);
        super.sendNext(bundle);
    }

    public interface IEnterTaxAmountListener extends IMessageListener, ICurrencyListener, IAmountListener {
    }
}
