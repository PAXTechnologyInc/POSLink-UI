package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IHasPhyKeyboardListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;

public class EnterFleetVehicleIDHelper extends BaseActionHelper {

    public EnterFleetVehicleIDHelper(@Nullable IEnterFleetVehicleIDListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(String vehicleID) {
        Bundle bundle = new Bundle();
        bundle.putString(EntryRequest.PARAM_FLEET_VEHICLE_ID, vehicleID);
        super.sendNext(bundle);
    }

    public interface IEnterFleetVehicleIDListener extends IMessageListener, IHasPhyKeyboardListener {
    }

}
