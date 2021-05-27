package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.pax.us.pay.ui.core.api.IUIListener;

public class ConfirmInstallmentPlanHelper extends BaseActionHelper {
    public ConfirmInstallmentPlanHelper(@Nullable IUIListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(String planOption) {
        Bundle bundle = new Bundle();
        bundle.putString(EntryRequest.PARAM_INSTALLMENT_SELECT_OPTION, planOption);
        super.sendNext(bundle);
    }

}
