package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IHasPhyKeyboardListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;

public class EnterFleetVehicleNumberHelper extends BaseActionHelper {

    public EnterFleetVehicleNumberHelper(@Nullable IEnterFleetVehicleNumberListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(String vehicleNumber) {
        Bundle bundle = new Bundle();
        bundle.putString(EntryRequest.PARAM_FLEET_VEHICLE_NUMBER, vehicleNumber);
        super.sendNext(bundle);
    }

    public interface IEnterFleetVehicleNumberListener extends IMessageListener, IHasPhyKeyboardListener {
    }

}
