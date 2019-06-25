package com.pax.us.pay.ui.base.message.helper;

import android.os.Bundle;

import com.pax.us.pay.ui.constant.parameter.Request;

public class InvoiceNumHelper extends BaseHelper {
    public void sendObjNext(String invoiceNum) {
        Bundle bundle = new Bundle();
        bundle.putString(Request.Text.PARA_INVOICE_NUMBER, invoiceNum);
        super.sendObjNext(bundle);
    }
}
