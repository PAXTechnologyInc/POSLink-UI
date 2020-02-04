package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;

public class EnterFleetDepartmentNumberHelper extends BaseActionHelper {

    public EnterFleetDepartmentNumberHelper(@Nullable IEnterFleetDepartmentNumberListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(String departmentNumber) {
        Bundle bundle = new Bundle();
        bundle.putString(EntryRequest.PARAM_FLEET_DEPARTMENT_NUMBER, departmentNumber);
        super.sendNext(bundle);
    }

    public interface IEnterFleetDepartmentNumberListener extends IMessageListener {
    }

}
