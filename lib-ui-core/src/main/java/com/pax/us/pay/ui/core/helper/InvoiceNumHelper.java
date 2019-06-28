package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;

public class InvoiceNumHelper extends BaseActionHelper {
    public void sendNext(String invoiceNum) {
        Bundle bundle = new Bundle();
        bundle.putString(EntryRequest.PARAM_INVOICE_NUMBER, invoiceNum);
        super.sendNext(bundle);
    }
}
