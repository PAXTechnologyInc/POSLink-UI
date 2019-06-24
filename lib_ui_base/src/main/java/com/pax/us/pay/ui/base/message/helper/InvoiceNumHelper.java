package com.pax.us.pay.ui.base.message.helper;

import com.pax.us.pay.ui.constant.parameter.Request;

import java.util.HashMap;
import java.util.Map;

public class InvoiceNumHelper extends BaseHelper {
    public void sendObjNext(String invoiceNum) {
        Map<String, String> map = new HashMap<>();
        map.put(Request.Text.PARA_INVOICE_NUMBER, invoiceNum);
        super.sendObjNext(map);
    }
}
