package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IHasPhyKeyboardListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;

public class EnterDescZipCodeHelper extends BaseActionHelper {

    public EnterDescZipCodeHelper(@Nullable IEnterDescZipCodeListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(String zipCode) {
        Bundle bundle = new Bundle();
        bundle.putString(EntryRequest.PARAM_DEST_ZIP_CODE, zipCode);
        super.sendNext(bundle);
    }

    public interface IEnterDescZipCodeListener extends IMessageListener, IHasPhyKeyboardListener {
    }

}
