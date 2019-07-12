package com.pax.pay.poslink.ui.demo.base;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.pax.pay.poslink.ui.demo.event.EndEvent;
import com.pax.pay.poslink.ui.demo.event.EventBusUtil;
import com.pax.us.pay.ui.core.api.IRespStatus;

import java.lang.ref.WeakReference;

public class RespStatusImpl implements IRespStatus {

    private final WeakReference<Activity> activityWeakReference;

    public RespStatusImpl(Activity activity) {
        this.activityWeakReference = new WeakReference<>(activity);
    }

    @Override
    public void onAccepted() {
        EventBusUtil.doEvent(new EndEvent());
        if (activityWeakReference.get() != null)
            activityWeakReference.get().finish();
    }

    @Override
    public void onDeclined(long code, @Nullable String message) {
        Activity activity = activityWeakReference.get();
        if (activity != null) {
            activity.runOnUiThread(() -> {
                String buff;
                if (message == null)
                    buff = "Trans Failed! Error Code : " + code;
                else
                    buff = message + "\n Error Code : " + code;
                Toast.makeText(activity, buff, Toast.LENGTH_LONG).show();
            });
        }
    }
}
