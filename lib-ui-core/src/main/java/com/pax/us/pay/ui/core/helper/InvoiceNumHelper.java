package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;

import com.pax.us.pay.ui.constant.parameter.Request;
import com.pax.us.pay.ui.core.BaseActionHelper;

public class InvoiceNumHelper extends BaseActionHelper {
    public void sendNext(String invoiceNum) {
        Bundle bundle = new Bundle();
        bundle.putString(Request.Text.PARA_INVOICE_NUMBER, invoiceNum);
        super.sendNext(bundle);
    }
}
