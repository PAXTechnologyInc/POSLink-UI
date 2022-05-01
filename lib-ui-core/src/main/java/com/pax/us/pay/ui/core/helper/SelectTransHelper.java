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
import com.pax.us.pay.ui.core.api.ITransListener;
import com.pax.us.pay.ui.core.api.IUIListener;

public class SelectTransHelper extends BaseActionHelper {
    public SelectTransHelper(@Nullable ISelectTransListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(long transNumber) {
        Bundle bundle = new Bundle();
        bundle.putLong(EntryRequest.PARAM_TRANS_NUMBER, transNumber);
        super.sendNext(bundle);
    }

    @Override
    protected void showUI(@Nullable IUIListener uiListener, @NonNull Bundle bundle) {
        super.showUI(uiListener, bundle);
        if (uiListener instanceof ITransListener) {
            if (bundle.containsKey("transUri")) {
                String url = bundle.getString("transUri");
                String selection = bundle.getString("transSelection");
                String[] selectionArgs = bundle.getStringArray("transSelectionArguments");
                if (!TextUtils.isEmpty(url))
                    ((ITransListener) uiListener).onShowTrans(url, selection, selectionArgs);
            }
        }
    }

    public interface ISelectTransListener extends IMessageListener, ITransListener {
    }

}
