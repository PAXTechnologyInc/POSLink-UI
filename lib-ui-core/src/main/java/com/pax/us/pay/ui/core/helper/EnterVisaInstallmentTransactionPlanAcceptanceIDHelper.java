package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IHasPhyKeyboardListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.pax.us.pay.ui.core.api.IUIListener;

public class EnterVisaInstallmentTransactionPlanAcceptanceIDHelper extends BaseActionHelper {

    public EnterVisaInstallmentTransactionPlanAcceptanceIDHelper(@Nullable IUIListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(String planAcceptanceID) {
        Bundle bundle = new Bundle();
        bundle.putString(EntryRequest.PARAM_VISA_PLAN_ACCEPTANCE_ID, planAcceptanceID);
        super.sendNext(bundle);
    }

    public interface IEnterVisaInstallmentTransactionPlanAcceptanceIDListener extends IMessageListener, IHasPhyKeyboardListener {
    }
}
