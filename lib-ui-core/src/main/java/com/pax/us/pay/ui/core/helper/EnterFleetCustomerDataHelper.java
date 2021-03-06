package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;

public class EnterFleetCustomerDataHelper extends BaseActionHelper {

    public EnterFleetCustomerDataHelper(@Nullable IEnterFleetCustomerDataListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(String customerData) {
        Bundle bundle = new Bundle();
        bundle.putString(EntryRequest.PARAM_FLEET_CUSTOMER_DATA, customerData);
        super.sendNext(bundle);
    }

    public interface IEnterFleetCustomerDataListener extends IMessageListener {
    }

}
