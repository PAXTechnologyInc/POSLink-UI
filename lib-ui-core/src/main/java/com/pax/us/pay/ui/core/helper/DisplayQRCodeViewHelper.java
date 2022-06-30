package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.text.TextUtils;

import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.pax.us.pay.ui.core.api.ITimeoutListener;
import com.pax.us.pay.ui.core.api.IUIListener;

public class DisplayQRCodeViewHelper extends BaseActionHelper {
    public DisplayQRCodeViewHelper(@Nullable IConfirmQRCodeViewListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(boolean flag) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(EntryRequest.PARAM_CONFIRMED, flag);
        super.sendNext(bundle);
    }

    @Override
    protected void showUI(@Nullable IUIListener uiListener, @NonNull Bundle bundle) {
        super.showUI(uiListener, bundle);

        String qrCodeContent = bundle.getString(EntryExtraData.PARAM_QR_CODE_CONTENT);
        if (!TextUtils.isEmpty(qrCodeContent)) {
            ((IConfirmQRCodeViewListener) uiListener).onShowQRCode(qrCodeContent);
            if (bundle.containsKey(EntryExtraData.PARAM_TIMEOUT)) {
                long timeOut = bundle.getLong(EntryExtraData.PARAM_TIMEOUT, 20 * 1000);
                ((ITimeoutListener) uiListener).onShowTimeout(timeOut);
            }
        }
    }

    public interface IConfirmQRCodeViewListener extends IMessageListener, ITimeoutListener {
        void onShowQRCode(@NonNull String content);
    }

}
