package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;

public class EnterFleetUserIDHelper extends BaseActionHelper {

    public EnterFleetUserIDHelper(@Nullable IEnterFleetUserIDListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(String userID) {
        Bundle bundle = new Bundle();
        bundle.putString(EntryRequest.PARAM_FLEET_USER_ID, userID);
        super.sendNext(bundle);
    }

    public interface IEnterFleetUserIDListener extends IMessageListener {
    }

}
