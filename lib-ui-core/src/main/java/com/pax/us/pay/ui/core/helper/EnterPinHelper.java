package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.text.TextUtils;

import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IAmountListener;
import com.pax.us.pay.ui.core.api.ICurrencyListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IPinListener;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.pax.us.pay.ui.core.api.IUIListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnterPinHelper extends BaseActionHelper {

    public EnterPinHelper(@Nullable IEnterPinListener listener, @Nullable IRespStatus respStatus) {
        super(listener, respStatus);
    }

    @Override
    public void sendNext() {
        super.sendNext();
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
        if (uiListener instanceof IPinListener) {
            boolean showVirtualPinPad = bundle.getBoolean(EntryExtraData.PARAM_SHOW_VIRTUAL_PINPAD, false);
            ((IPinListener) uiListener).onShowVirtualPinpad(showVirtualPinPad);

            boolean isExternalPinpad = bundle.getBoolean(EntryExtraData.PARAM_IS_EXTERNAL_PINPAD);
            String pinStyles = bundle.getString(EntryExtraData.PARAM_PIN_STYLES, "NORMAL");
            boolean isOnline = bundle.getBoolean(EntryExtraData.PARAM_IS_ONLINE_PIN);
            String pinRange = bundle.getString(EntryExtraData.PARAM_PIN_RANGE);
            boolean isPinBypass = true;
            if (TextUtils.isEmpty(pinRange)) {
                isPinBypass = true;
            } else {
                String regEx = "\\d{2}";
                Pattern pattern = Pattern.compile(regEx);
                Matcher matcher = pattern.matcher(pinRange);
                pinRange = matcher.replaceAll("");
                regEx = "[0]";
                pattern = Pattern.compile(regEx);
                matcher = pattern.matcher(pinRange);
                isPinBypass = matcher.find();
            }
            ((IPinListener) uiListener).onShowPin(pinStyles, isOnline, isPinBypass, isExternalPinpad);
        }
    }

    public void setSecurityArea(int x, int y, int width, int height) {
        Bundle bundle = new Bundle();
        bundle.putInt(EntryRequest.PARAM_X, x);
        bundle.putInt(EntryRequest.PARAM_Y, y);
        bundle.putInt(EntryRequest.PARAM_WIDTH, width);
        bundle.putInt(EntryRequest.PARAM_HEIGHT, height);
        super.setSecurityArea(bundle);
    }

    public interface IEnterPinListener extends IMessageListener, ICurrencyListener, IAmountListener, IPinListener {
    }
}
