package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IHasPhyKeyboardListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.pax.us.pay.ui.core.api.IUIListener;

/**
 * Created by ZZ on 3/18/2021
 */
public class EnterOctReferenceNumHelper extends BaseActionHelper {
    public EnterOctReferenceNumHelper(@Nullable IUIListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(String referenceNumber) {
        Bundle bundle = new Bundle();
        bundle.putString(EntryRequest.PARAM_OCT_REFERENCE_NUMBER, referenceNumber);
        super.sendNext(bundle);
    }

    public interface IEnterOctReferenceNumHelper extends IMessageListener, IHasPhyKeyboardListener {
    }
}
