package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.TextUtils;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;

/**
 * @author Jolie Yang 03/19/2021
 */

public class ShowTextBoxHelper extends BaseActionHelper {
    public ShowTextBoxHelper(@Nullable IShowTextBoxHelper uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(){
        super.sendNext();
    }

    public void sendNext(String btnNumber) {
        Bundle bundle = new Bundle();
        if(!TextUtils.isEmpty(btnNumber))
            bundle.putString(EntryRequest.PARAM_BUTTON_NUMBER, btnNumber);
        bundle.putString(EntryRequest.PARAM_SIGN_STATUS, "1");
        super.sendNext(bundle);
    }

    public void sendNext(String signStatus, short[] traceData) {
        Bundle bundle = new Bundle();
        bundle.putString(EntryRequest.PARAM_SIGN_STATUS, signStatus);
        if (traceData!=null)
            bundle.putShortArray(EntryRequest.PARAM_SIGNATURE, traceData);
        super.sendNext(bundle);
    }

    public interface IShowTextBoxHelper extends IMessageListener {
    }

}
