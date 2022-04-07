package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IAmountListener;
import com.pax.us.pay.ui.core.api.ICurrencyListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.INoTipSelectionListener;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.pax.us.pay.ui.core.api.ITipNameListener;
import com.pax.us.pay.ui.core.api.ITipOptionListener;
import com.pax.us.pay.ui.core.api.ITipsListener;
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
        String[] rateOptions = null, options = null;
        super.showUI(uiListener, bundle);
        if (uiListener instanceof ICurrencyListener) {
            String currency = bundle.getString(EntryExtraData.PARAM_CURRENCY, "USD");
            if (currency.equals(CurrencyType.POINT))
                ((ICurrencyListener) uiListener).onShowCurrency(currency, true);
            else
                ((ICurrencyListener) uiListener).onShowCurrency(currency, false);
        }

        if (uiListener instanceof IAmountListener && bundle.containsKey(EntryExtraData.PARAM_BASE_AMOUNT)) {
            ((IAmountListener) uiListener).onShowAmount(bundle.getLong(EntryExtraData.PARAM_BASE_AMOUNT));
        }

        if (uiListener instanceof ITipNameListener && bundle.containsKey(EntryExtraData.PARAM_TIP_NAME)) {
            ((ITipNameListener) uiListener).onShowTipName(bundle.getString(EntryExtraData.PARAM_TIP_NAME));
        }

        if (uiListener instanceof ITipsListener && bundle.containsKey(EntryExtraData.PARAM_TIP_NAMES)) {
            String [] names = bundle.getStringArray(EntryExtraData.PARAM_TIP_NAMES);
            long [] tipAmounts = null;
            if (bundle.containsKey(EntryExtraData.PARAM_TIP_AMOUNTS)) {
                String[] strTipAmounts = bundle.getStringArray(EntryExtraData.PARAM_TIP_AMOUNTS);
                if (null != strTipAmounts && strTipAmounts.length>0) {
                    tipAmounts = new long[strTipAmounts.length];
                    for (int i = 0; i < strTipAmounts.length; i++) {
                        if (TextUtils.isEmpty(strTipAmounts[i]))
                            tipAmounts[i] = 0;
                        else
                            tipAmounts[i] = Long.valueOf(strTipAmounts[i]);
                    }
                }
            }
            if(names != null && names.length>0){
                ((ITipsListener) uiListener).onShowTips(names, tipAmounts);
            }
        }

        if (uiListener instanceof ITipOptionListener && bundle.containsKey(EntryExtraData.PARAM_TIP_RATE_OPTIONS)) {
            rateOptions = bundle.getStringArray(EntryExtraData.PARAM_TIP_RATE_OPTIONS);
        }

        if (uiListener instanceof ITipOptionListener && bundle.containsKey(EntryExtraData.PARAM_TIP_OPTIONS)) {
            options = bundle.getStringArray(EntryExtraData.PARAM_TIP_OPTIONS);
        }

        if (options != null && options.length > 0) {
            if ((rateOptions != null && rateOptions.length > 0)) {
                if (rateOptions.length == options.length) {
                    if (options != null && options.length > 0) {
                        ((ITipOptionListener) uiListener).onShowTipOptions(options, rateOptions);
                    }
                }
            } else {
                ((ITipOptionListener) uiListener).onShowTipOptions(options, null);
            }
        }

        if (uiListener instanceof INoTipSelectionListener && bundle.containsKey(EntryExtraData.PARAM_ENABLE_NO_TIP_SELECTION)) {
            ((INoTipSelectionListener) uiListener).onShowEnableNoTipSelection(bundle.getBoolean(EntryExtraData.PARAM_ENABLE_NO_TIP_SELECTION, false));
        }
    }

    public interface IEnterTipListener extends IMessageListener, ICurrencyListener, IAmountListener, ITipNameListener, ITipOptionListener, ITipsListener,INoTipSelectionListener {
    }

}
