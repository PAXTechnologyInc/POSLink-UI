package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;

public class ReferenceNumHelper extends BaseActionHelper {

    public void sendNext(String referenceNumber) {
        Bundle bundle = new Bundle();
        bundle.putString(EntryRequest.PARAM_REFERENCE_NUMBER, referenceNumber);
        super.sendNext(bundle);
    }
}
