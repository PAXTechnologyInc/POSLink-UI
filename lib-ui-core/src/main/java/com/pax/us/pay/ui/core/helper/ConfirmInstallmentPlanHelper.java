package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.pax.us.pay.ui.core.api.IUIListener;

public class ConfirmInstallmentPlanHelper extends BaseActionHelper {
    public ConfirmInstallmentPlanHelper(@Nullable IUIListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(int index) {
        Bundle bundle = new Bundle();
        bundle.putInt(EntryRequest.PARAM_INDEX, index);
        super.sendNext(bundle);
    }

}
