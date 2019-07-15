package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;

public class EnterTableNumHelper extends BaseActionHelper {

    public EnterTableNumHelper(@Nullable IEnterTableNumListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(String tableNumber) {
        Bundle bundle = new Bundle();
        bundle.putString(EntryRequest.PARAM_TABLE_NUMBER, tableNumber);
        super.sendNext(bundle);
    }

    public interface IEnterTableNumListener extends IMessageListener {
    }

}
