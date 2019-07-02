package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryInput;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IInformationListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.pax.us.pay.ui.core.api.IUIListener;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class ConfirmHelper extends BaseActionHelper {
    public ConfirmHelper(@Nullable IConfirmListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    @Override
    public void sendNext() {
        super.sendNext();
    }

    @Override
    protected void showUI(@Nullable IUIListener uiListener, @NonNull Bundle bundle) {
        super.showUI(uiListener, bundle);
        if (uiListener instanceof IInformationListener) {
            if (bundle.size() > 0) {
                Set<String> keySet = bundle.keySet();
                Map<String, String> map = new LinkedHashMap<>();
                for (String key : keySet) {
                    if (key.equals(EntryInput.PARAM_TRANS_TYPE) || key.equals(EntryInput.PARAM_PACKAGE) || key.equals(EntryInput.PARAM_OPTIONS)) {
                        continue;
                    } else {
                        map.put(key, bundle.getString(key));
                    }
                }
                if (map.size() > 0) {
                    ((IInformationListener) uiListener).onShowInformation(map);
                }
            }

        }
    }

    public interface IConfirmListener extends IMessageListener, IInformationListener {
    }

}
