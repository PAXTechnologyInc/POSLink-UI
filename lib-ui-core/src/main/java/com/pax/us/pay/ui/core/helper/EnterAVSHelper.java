package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;

public class EnterAVSHelper extends BaseActionHelper {
    public EnterAVSHelper(@Nullable IEnterAvsListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(String line1Text, String line2Text) {
        Bundle bundle = new Bundle();
        bundle.putString(EntryRequest.PARAM_ADDRESS, line1Text);
        bundle.putString(EntryRequest.PARAM_ZIP_CODE, line2Text);
        super.sendNext(bundle);
    }

    public interface IEnterAvsListener extends IMessageListener {
    }

}
