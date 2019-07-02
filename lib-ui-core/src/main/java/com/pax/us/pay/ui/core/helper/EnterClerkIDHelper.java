package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;

public class EnterClerkIDHelper extends BaseActionHelper {
    public EnterClerkIDHelper(@Nullable IEnterClerkIDListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(String clerkId) {
        Bundle bundle = new Bundle();
        bundle.putString(EntryRequest.PARAM_CLERK_ID, clerkId);
        super.sendNext(bundle);
    }

    public interface IEnterClerkIDListener extends IMessageListener {
    }

}
