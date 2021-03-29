package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;

public class ShowDialogFormHelper extends BaseActionHelper {

    public ShowDialogFormHelper(@Nullable IShowDialogFormHelper listener, @Nullable IRespStatus respStatus) {
        super(listener, respStatus);
    }

    public void sendNext(String selected) {
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(selected)) {
            bundle.putString(EntryRequest.PARAM_LABEL_SELECTED, selected);
        }
        super.sendNext(bundle);
    }

    public interface IShowDialogFormHelper extends IMessageListener {
    }
}
