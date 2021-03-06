package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IHasPhyKeyboardListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;

public class EnterTaxIdHelper extends BaseActionHelper {
    public EnterTaxIdHelper(@Nullable IEnterTaxIdListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(String taxID) {
        Bundle bundle = new Bundle();
        bundle.putString(EntryRequest.PARAM_MERCHANT_TAX_ID, taxID);
        super.sendNext(bundle);
    }

    public interface IEnterTaxIdListener extends IMessageListener, IHasPhyKeyboardListener {
    }
}
