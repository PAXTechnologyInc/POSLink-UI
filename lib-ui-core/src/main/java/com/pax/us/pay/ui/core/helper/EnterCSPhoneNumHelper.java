package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IHasPhyKeyboardListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;

public class EnterCSPhoneNumHelper extends BaseActionHelper {

    public EnterCSPhoneNumHelper(@Nullable IEnterCSPhoneNumListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(String phoneNumber) {
        Bundle bundle = new Bundle();
        bundle.putString(EntryRequest.PARAM_PHONE_NUMBER, phoneNumber);
        super.sendNext(bundle);
    }

    public interface IEnterCSPhoneNumListener extends IMessageListener, IHasPhyKeyboardListener {
    }

}
