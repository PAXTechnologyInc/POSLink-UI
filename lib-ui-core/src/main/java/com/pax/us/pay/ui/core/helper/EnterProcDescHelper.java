package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;

public class EnterProcDescHelper extends BaseActionHelper {

    public EnterProcDescHelper(@Nullable IEnterProcDescListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(String procDes) {
        Bundle bundle = new Bundle();
        bundle.putString(EntryRequest.PARAM_PROC_DESC, procDes);
        super.sendNext(bundle);
    }

    public interface IEnterProcDescListener extends IMessageListener {
    }

}
