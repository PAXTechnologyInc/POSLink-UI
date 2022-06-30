package com.pax.pay.ui.def.base;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;

import com.pax.pay.ui.def.R;
import com.pax.pay.ui.def.utils.UIControl;
import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.paxus.view.utils.ToastHelper;

import java.lang.ref.WeakReference;

public class SecurityRespStatusImpl implements IRespStatus {

    private final WeakReference<Activity> activityWeakReference;


    public SecurityRespStatusImpl(Activity activity) {
        this.activityWeakReference = new WeakReference<>(activity);
    }

    @Override
    public void onAccepted() {
        //close activity which don't involved any transaction.
        Activity activity = activityWeakReference.get();
        if (!UIControl.getInstance().isTransStarted()) {
            if (activity != null) {
                activity.finish();
            }
        }
        SharedPreferences preferences = activity.getSharedPreferences(EntryRequest.class.getName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(activity.getLocalClassName(), activity.getResources().getString(R.string.finished));
        editor.commit();

        ((BaseActivity) activity).notifyObservers(false);
    }

    @Override
    public void onDeclined(long code, @Nullable String message) {
        Activity activity = activityWeakReference.get();
        if (activity != null) {
            activity.runOnUiThread(() -> {
                String buff = message == null ? activity.getResources().getString(R.string.trans_failed) : message;
                //ANBP-469 don't prompt error code
                //buff += "\n Error Code : " + code;
                //Toast.makeText(activity, buff, Toast.LENGTH_LONG).show();
                // Arvind: Fix for https://pax-us.atlassian.net/browse/ANBP-670
                ToastHelper.showMessage(activity, buff);
            });
        }
    }
}
