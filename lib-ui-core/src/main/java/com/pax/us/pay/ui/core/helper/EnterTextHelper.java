package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.TextUtils;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IHasPhyKeyboardListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;

public class EnterTextHelper extends BaseActionHelper {

    public EnterTextHelper(@Nullable IEnterTextHelper listener, @Nullable IRespStatus respStatus) {
        super(listener, respStatus);
    }

    public void sendNext(String value) {
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(value)) {
            bundle.putString(EntryRequest.PARAM_INPUT_VALUE, value);
        }
        super.sendNext(bundle);
    }

    public interface IEnterTextHelper extends IMessageListener, IHasPhyKeyboardListener {
    }
}
