package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IAmountListener;
import com.pax.us.pay.ui.core.api.ICardListener;
import com.pax.us.pay.ui.core.api.ICurrencyListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.pax.us.pay.ui.core.api.ITapLogoListener;
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

    public void setSecurityArea(int x, int y, int width, int height, int fontSize) {
        Bundle bundle = new Bundle();
        bundle.putInt(EntryRequest.PARAM_X, x);
        bundle.putInt(EntryRequest.PARAM_Y, y);
        bundle.putInt(EntryRequest.PARAM_WIDTH, width);
        bundle.putInt(EntryRequest.PARAM_HEIGHT, height);
        bundle.putInt(EntryRequest.PARAM_FONT_SIZE, fontSize);
        super.setSecurityArea(bundle);
    }

    @Override
    protected void showUI(@Nullable IUIListener uiListener, @NonNull Bundle bundle) {
        super.showUI(uiListener, bundle);
        if ((uiListener instanceof ICurrencyListener) &&
                (uiListener instanceof IAmountListener && bundle.containsKey(EntryExtraData.PARAM_TOTAL_AMOUNT))) {
            String currency = bundle.getString(EntryExtraData.PARAM_CURRENCY, "USD");
            if (currency.equals(CurrencyType.POINT))
                ((ICurrencyListener) uiListener).onShowCurrency(currency, true);
            else
                ((ICurrencyListener) uiListener).onShowCurrency(currency, false);

            ((IAmountListener) uiListener).onShowAmount(bundle.getLong(EntryExtraData.PARAM_TOTAL_AMOUNT));

        }

        if (uiListener instanceof ICardListener) {
            ((ICardListener) uiListener).onShowLight( bundle.getBoolean(EntryExtraData.PARAM_ENABLE_CONTACTLESS_LIGHT, true));

            ((ICardListener) uiListener).onShowCard(
                    bundle.getBoolean(EntryExtraData.PARAM_ENABLE_MANUAL, true),
                    bundle.getBoolean(EntryExtraData.PARAM_ENABLE_SWIPE, true),
                    bundle.getBoolean(EntryExtraData.PARAM_ENABLE_INSERT, true),
                    bundle.getBoolean(EntryExtraData.PARAM_ENABLE_TAP, true)
            );
        }

        if (uiListener instanceof ITapLogoListener) {
            ((ITapLogoListener) uiListener).onShowCardPay(
                    bundle.getBoolean(EntryExtraData.PARAM_ENABLE_APPLEPAY, false),
                    bundle.getBoolean(EntryExtraData.PARAM_ENABLE_GOOGLEPAY, false),
                    bundle.getBoolean(EntryExtraData.PARAM_ENABLE_SUMSUNGPAY, false),
                    bundle.getBoolean(EntryExtraData.PARAM_ENABLE_NFCPAY, false)
            );
        }
    }

    public interface ISearchCardListener extends IMessageListener, ICurrencyListener, IAmountListener, ICardListener, ITapLogoListener {
    }
}
