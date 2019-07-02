package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;

public class EnterInvoiceNumHelper extends BaseActionHelper {
    public EnterInvoiceNumHelper(@Nullable IEnterInvoiceNumListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(String invoiceNum) {
        Bundle bundle = new Bundle();
        bundle.putString(EntryRequest.PARAM_INVOICE_NUMBER, invoiceNum);
        super.sendNext(bundle);
    }


    public interface IEnterInvoiceNumListener extends IMessageListener {
    }
}
