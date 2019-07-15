package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;

public class EnterCustomerCodeHelper extends BaseActionHelper {

    public EnterCustomerCodeHelper(@Nullable IEnterCustomerCodeListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(String customerCode) {
        Bundle bundle = new Bundle();
        bundle.putString(EntryRequest.PARAM_CUSTOMER_CODE, customerCode);
        super.sendNext(bundle);
    }

    public interface IEnterCustomerCodeListener extends IMessageListener {
    }

}
