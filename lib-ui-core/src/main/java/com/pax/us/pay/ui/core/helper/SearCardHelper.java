package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryInput;
import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IAmountListener;
import com.pax.us.pay.ui.core.api.ICardListener;
import com.pax.us.pay.ui.core.api.ICurrencyListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.pax.us.pay.ui.core.api.IUIListener;

public class SearCardHelper extends BaseActionHelper {

    public SearCardHelper(@Nullable ISearchCardListener listener, @Nullable IRespStatus respStatus) {
        super(listener, respStatus);
    }

    @Override
    public void sendNext() {
        super.sendNext();
    }

    public void setSecurityArea(int x, int y, int width, int height) {
        Bundle bundle = new Bundle();
        bundle.putInt(EntryRequest.PARAM_X, x);
        bundle.putInt(EntryRequest.PARAM_Y, y);
        bundle.putInt(EntryRequest.PARAM_WIDTH, width);
        bundle.putInt(EntryRequest.PARAM_HEIGHT, height);
        super.setSecurityArea(bundle);
    }

    @Override
    protected void showUI(@Nullable IUIListener uiListener, @NonNull Bundle bundle) {
        super.showUI(uiListener, bundle);
        if (uiListener instanceof ICurrencyListener && bundle.containsKey(EntryInput.PARAM_CURRENCY)) {
            String currency = bundle.getString(EntryInput.PARAM_CURRENCY);
            if (currency.length() > 0)
                ((ICurrencyListener) uiListener).onShowCurrency(currency);
        }

        if (uiListener instanceof IAmountListener && bundle.containsKey(EntryInput.PARAM_DISP_AMOUNT)) {
            ((IAmountListener) uiListener).onShowAmount(bundle.getLong(EntryInput.PARAM_DISP_AMOUNT));
        }

        if (uiListener instanceof ICardListener && (
                bundle.containsKey(EntryInput.PARAM_ENABLE_MANUAL) ||
                        bundle.containsKey(EntryInput.PARAM_ENABLE_SWIPE) ||
                        bundle.containsKey(EntryInput.PARAM_ENABLE_INSERT) ||
                        bundle.containsKey(EntryInput.PARAM_ENABLE_TAP))) {
            ((ICardListener) uiListener).onShowCard(
                    bundle.getBoolean(EntryInput.PARAM_ENABLE_MANUAL, false),
                    bundle.getBoolean(EntryInput.PARAM_ENABLE_SWIPE, false),
                    bundle.getBoolean(EntryInput.PARAM_ENABLE_INSERT, false),
                    bundle.getBoolean(EntryInput.PARAM_ENABLE_TAP, false)
            );
        }
    }

    public interface ISearchCardListener extends IMessageListener, ICurrencyListener, IAmountListener, ICardListener {
    }
}
