package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;

public class EnterOrderNumHelper extends BaseActionHelper {

    public EnterOrderNumHelper(@Nullable IEnterOrderNumListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(String orderNumber) {
        Bundle bundle = new Bundle();
        bundle.putString(EntryRequest.PARAM_ORDER_NUMBER, orderNumber);
        super.sendNext(bundle);
    }

    public interface IEnterOrderNumListener extends IMessageListener {
    }

}
