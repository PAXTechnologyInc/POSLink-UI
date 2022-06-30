package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IOptionListener;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.pax.us.pay.ui.core.api.IUIListener;

public class ConfirmOptionsHelper extends BaseActionHelper {
    public ConfirmOptionsHelper(@Nullable IComfirmOptionListener uiListener, @Nullable IRespStatus respStatus) {
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
        if (uiListener instanceof IOptionListener && bundle.containsKey(EntryExtraData.PARAM_OPTIONS)) {
            String[] options = bundle.getStringArray(EntryExtraData.PARAM_OPTIONS);
            if (options != null && options.length > 0)
                ((IOptionListener) uiListener).onShowOptions(options);
        }
    }

    public interface IComfirmOptionListener extends IMessageListener, IOptionListener {
    }

}
