package com.pax.pay.ui.def.base;

import android.app.Activity;
import android.support.annotation.Nullable;

import com.pax.pay.ui.def.utils.UIControl;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.paxus.view.utils.ToastHelper;

import java.lang.ref.WeakReference;

public class RespStatusImpl implements IRespStatus {

    private final WeakReference<Activity> activityWeakReference;

    public RespStatusImpl(Activity activity) {
        this.activityWeakReference = new WeakReference<>(activity);
    }

    @Override
    public void onAccepted() {
        //close activity which don't involved any transaction.
        if (!UIControl.getInstance().isTransStarted()) {
            Activity activity = activityWeakReference.get();
            if (activity != null) {
                activity.finish();
            }
        }
    }

    @Override
    public void onDeclined(long code, @Nullable String message) {
        Activity activity = activityWeakReference.get();
        if (activity != null) {
            activity.runOnUiThread(() -> {
                String buff = message == null ? "Trans Failed!" : message;
                //ANBP-469 don't prompt error code
                //buff += "\n Error Code : " + code;
                //Toast.makeText(activity, buff, Toast.LENGTH_LONG).show();
                // Arvind: Fix for https://pax-us.atlassian.net/browse/ANBP-670
                ToastHelper.showMessage(activity, buff);
            });
        }
    }
}
