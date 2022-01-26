package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IHasPhyKeyboardListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;

public class EnterServerIdHelper extends BaseActionHelper {
    public EnterServerIdHelper(@Nullable IEnterServerIdListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(String serverID) {
        Bundle bundle = new Bundle();
        bundle.putString(EntryRequest.PARAM_SERVER_ID, serverID);
        super.sendNext(bundle);
    }

    public interface IEnterServerIdListener extends IMessageListener, IHasPhyKeyboardListener {
    }
}
