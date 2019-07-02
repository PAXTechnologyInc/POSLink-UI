package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryInput;
import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IAmountListener;
import com.pax.us.pay.ui.core.api.ICurrencyListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.pax.us.pay.ui.core.api.IUIListener;

public class EnterFSAAmountHelper extends BaseActionHelper {

    public EnterFSAAmountHelper(@Nullable IEnterFSAAmountListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(long healthAmt, long clinicAmt, long prescriptionAmt, long dentalAmt
            , long visionAmt, long transitAmt, long totalAmount) {
        if (healthAmt + transitAmt > totalAmount) {
            decline(101901, "FSA AMOUNT EXCEED LIMIT");
            return;
        } else if (clinicAmt + prescriptionAmt + dentalAmt + visionAmt > healthAmt) {
            decline(101902, "SUB HEALTH CARE AMOUNT EXCEED LIMIT");
            return;
        }

        Bundle bundle = new Bundle();
        bundle.putLong(EntryRequest.PARAM_HEALTH_CARE_AMOUNT, healthAmt);
        bundle.putLong(EntryRequest.PARAM_CLINIC_AMOUNT, clinicAmt);
        bundle.putLong(EntryRequest.PARAM_PRESCRIPTION_AMOUNT, prescriptionAmt);
        bundle.putLong(EntryRequest.PARAM_DENTAL_AMOUNT, dentalAmt);
        bundle.putLong(EntryRequest.PARAM_VISION_AMOUNT, visionAmt);
        bundle.putLong(EntryRequest.PARAM_TRANSIT_AMOUNT, transitAmt);
        super.sendNext(bundle);
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
    }

    public interface IEnterFSAAmountListener extends IMessageListener, ICurrencyListener, IAmountListener {
    }

}
