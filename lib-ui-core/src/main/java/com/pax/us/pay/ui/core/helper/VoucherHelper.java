package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;

public class VoucherHelper extends BaseActionHelper {
    public VoucherHelper(@Nullable IEnterVoucherListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(String voucherNo) {
        Bundle bundle = new Bundle();
        bundle.putString(EntryRequest.PARAM_VOUCHER_NUMBER, voucherNo);
        super.sendNext(bundle);
    }

    public interface IEnterVoucherListener extends IMessageListener {
    }
}
