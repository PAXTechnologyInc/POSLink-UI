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
import com.pax.us.pay.ui.core.api.IHasPhyKeyboardListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.INoTipSelectionListener;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.pax.us.pay.ui.core.api.ITipNameListener;
import com.pax.us.pay.ui.core.api.IUIListener;

public class EnterTotalAmountHelper extends BaseActionHelper {

    public EnterTotalAmountHelper(@Nullable IEnterTotalAmountListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(long amount) {
        Bundle bundle = new Bundle();
        bundle.putLong(EntryRequest.PARAM_TOTAL_AMOUNT, amount);
        super.sendNext(bundle);
    }

    @Override
    protected void showUI(@Nullable IUIListener uiListener, @NonNull Bundle bundle) {
        super.showUI(uiListener, bundle);
        if (uiListener instanceof ICurrencyListener) {
            String currency = bundle.getString(EntryExtraData.PARAM_CURRENCY, "USD");
            ((ICurrencyListener) uiListener).onShowCurrency(currency, currency.equals(CurrencyType.POINT));
        }
        if (uiListener instanceof ITipNameListener && bundle.containsKey(EntryExtraData.PARAM_TIP_NAME)) {
            ((ITipNameListener) uiListener).onShowTipName(bundle.getString(EntryExtraData.PARAM_TIP_NAME));
        }

        if (uiListener instanceof IAmountListener && bundle.containsKey(EntryExtraData.PARAM_BASE_AMOUNT)) {
            ((IAmountListener) uiListener).onShowAmount(bundle.getLong(EntryExtraData.PARAM_BASE_AMOUNT));
        }

        if (uiListener instanceof INoTipSelectionListener && bundle.containsKey(EntryExtraData.PARAM_ENABLE_NO_TIP_SELECTION)) {
            ((INoTipSelectionListener) uiListener).onShowEnableNoTipSelection(bundle.getBoolean(EntryExtraData.PARAM_ENABLE_NO_TIP_SELECTION, false));
        }


    }

    public interface IEnterTotalAmountListener extends IMessageListener, ICurrencyListener, IAmountListener, ITipNameListener, INoTipSelectionListener,IHasPhyKeyboardListener {
    }

}
