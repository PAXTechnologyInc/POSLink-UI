package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IHasPhyKeyboardListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;

public class EnterFleetLicenseNumberHelper extends BaseActionHelper {

    public EnterFleetLicenseNumberHelper(@Nullable IEnterFleetLicenseNumberListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(String licenseNumber) {
        Bundle bundle = new Bundle();
        bundle.putString(EntryRequest.PARAM_FLEET_LICENSE_NUMBER, licenseNumber);
        super.sendNext(bundle);
    }

    public interface IEnterFleetLicenseNumberListener extends IMessageListener, IHasPhyKeyboardListener {
    }

}
