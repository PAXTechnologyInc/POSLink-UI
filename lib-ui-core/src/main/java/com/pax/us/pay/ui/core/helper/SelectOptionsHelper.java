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

public class SelectOptionsHelper extends BaseActionHelper {
    public SelectOptionsHelper(@Nullable ISelectOptionListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(int index) {
        Bundle bundle = new Bundle();
        bundle.putInt(EntryRequest.PARAM_INDEX, index);
        super.sendNext(bundle);
    }

    public void sendNext() {
        super.sendNext();
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

    public interface ISelectOptionListener extends IMessageListener, IOptionListener {
    }

}
