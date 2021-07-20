package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IDccListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IOptionListener;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.pax.us.pay.ui.core.api.IUIListener;

public class ConfirmDccHelper extends BaseActionHelper {
    public ConfirmDccHelper(@Nullable ConfirmDccHelper.IConFirmDccListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(boolean flag) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(EntryRequest.PARAM_CONFIRMED, flag);
        super.sendNext(bundle);
    }

    @Override
    protected void showUI(@Nullable IUIListener uiListener, @NonNull Bundle bundle) {
        super.showUI(uiListener, bundle);
        if (uiListener instanceof IDccListener)  {
            String amountMessage = bundle.getString(EntryExtraData.PARAM_AMOUNT_MESSAGE, "");
            String cardCurrency = bundle.getString(EntryExtraData.PARAM_CURRENCY_ALPHA_CODE, "USD");
            String exchangeRate = bundle.getString(EntryExtraData.PARAM_EXCHANGE_RATE, "");
            String margin = bundle.getString(EntryExtraData.PARAM_MARGIN, "");
            String foreignAmountMessage = bundle.getString(EntryExtraData.PARAM_FOREIGN_AMOUNT_MESSAGE, "");
            ((IDccListener) uiListener).onShowDcc(amountMessage, cardCurrency, exchangeRate, margin, foreignAmountMessage);

            if (uiListener instanceof IOptionListener) {
                Boolean confirmWithCurrency = bundle.getBoolean(EntryExtraData.PARAM_CONFIRM_WITH_CURRENCY, false);
                String[] options = new String[2];
                if (confirmWithCurrency && !TextUtils.isEmpty(cardCurrency)) {
                    options[0] = "USD";
                    options[1] = cardCurrency;
                }else{
                    options[0] = "Agree";
                    options[1] = "Cancel";
                }
                ((IOptionListener) uiListener).onShowOptions(options);
            }
        }
    }

    public interface IConFirmDccListener extends IMessageListener, IDccListener, IOptionListener {
    }

} 