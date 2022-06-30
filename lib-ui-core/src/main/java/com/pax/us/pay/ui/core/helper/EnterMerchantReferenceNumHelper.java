package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IHasPhyKeyboardListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;

public class EnterMerchantReferenceNumHelper extends BaseActionHelper {

    public EnterMerchantReferenceNumHelper(@Nullable IEnterMerchantReferenceNumListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(String referenceNumber) {
        Bundle bundle = new Bundle();
        bundle.putString(EntryRequest.PARAM_MERCHANT_REFERENCE_NUMBER, referenceNumber);
        super.sendNext(bundle);
    }

    public interface IEnterMerchantReferenceNumListener extends IMessageListener, IHasPhyKeyboardListener {
    }

}
