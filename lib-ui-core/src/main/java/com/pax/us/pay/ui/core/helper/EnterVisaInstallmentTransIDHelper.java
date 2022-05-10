package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IHasPhyKeyboardListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;

public class EnterVisaInstallmentTransIDHelper extends BaseActionHelper {

    public EnterVisaInstallmentTransIDHelper(@Nullable EnterVisaInstallmentTransIDHelper.IEnterVisaInstallmentTransIDListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(String transID) {
        Bundle bundle = new Bundle();
        bundle.putString(EntryRequest.PARAM_VISA_TRANSID, transID);
        super.sendNext(bundle);
    }

    public interface IEnterVisaInstallmentTransIDListener extends IMessageListener, IHasPhyKeyboardListener {
    }
}
