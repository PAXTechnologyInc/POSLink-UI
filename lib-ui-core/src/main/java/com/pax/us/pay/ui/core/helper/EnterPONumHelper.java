package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;

public class EnterPONumHelper extends BaseActionHelper {

    public EnterPONumHelper(@Nullable IEnterPONumListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(String poNumber) {
        Bundle bundle = new Bundle();
        bundle.putString(EntryRequest.PARAM_PO_NUMBER, poNumber);
        super.sendNext(bundle);
    }

    public interface IEnterPONumListener extends IMessageListener {
    }

}
