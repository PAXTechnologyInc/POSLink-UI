package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;

import com.pax.us.pay.ui.constant.parameter.Request;
import com.pax.us.pay.ui.core.BaseActionHelper;

public class ReferenceNumHelper extends BaseActionHelper {

    public void sendNext(String referenceNumber) {
        Bundle bundle = new Bundle();
        bundle.putString(Request.Text.PARA_REFERENCE_NUMBER, referenceNumber);
        super.sendNext(bundle);
    }
}
