package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;

public class EnterAddressHelper extends BaseActionHelper {

    public EnterAddressHelper(@Nullable IEnterAddressListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(String address) {
        Bundle bundle = new Bundle();
        bundle.putString(EntryRequest.PARAM_ADDRESS, address);
        super.sendNext(bundle);
    }

    public interface IEnterAddressListener extends IMessageListener {
    }

}
