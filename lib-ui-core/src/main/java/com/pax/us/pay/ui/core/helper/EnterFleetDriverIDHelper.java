package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IHasPhyKeyboardListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;

public class EnterFleetDriverIDHelper extends BaseActionHelper {

    public EnterFleetDriverIDHelper(@Nullable IEnterFleetDriverIDListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(String driverId) {
        Bundle bundle = new Bundle();
        bundle.putString(EntryRequest.PARAM_FLEET_DRIVER_ID, driverId);
        super.sendNext(bundle);
    }

    public interface IEnterFleetDriverIDListener extends IMessageListener, IHasPhyKeyboardListener {
    }

}
