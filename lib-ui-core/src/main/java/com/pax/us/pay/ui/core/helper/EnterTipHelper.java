package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryInput;
import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
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
        if (uiListener instanceof ICurrencyListener && bundle.containsKey(EntryInput.PARAM_CURRENCY)) {
            ((ICurrencyListener) uiListener).onShowCurrency(bundle.getString(EntryInput.PARAM_CURRENCY));
        }

        if (uiListener instanceof ITipOptionListener && bundle.containsKey(EntryInput.PARAM_TIP_OPTIONS)) {
            ((ITipOptionListener) uiListener).onShowTipOptions(bundle.getStringArray(EntryInput.PARAM_TIP_OPTIONS));
        }
    }

    public interface IEnterTipListener extends IMessageListener, ICurrencyListener, ITipOptionListener {
    }

}
