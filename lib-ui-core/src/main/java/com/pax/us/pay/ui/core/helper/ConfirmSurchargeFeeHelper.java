package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.pax.us.pay.ui.core.api.IUIListener;

/**
 * @author arvind
 */

public class ConfirmSurchargeFeeHelper extends BaseActionHelper {
    public ConfirmSurchargeFeeHelper(@Nullable IUIListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(long feeAmount) {
        Bundle bundle = new Bundle();
        bundle.putLong(EntryRequest.PARAM_SURCHARGE_FEE, feeAmount);
        super.sendNext(bundle);
    }

}
