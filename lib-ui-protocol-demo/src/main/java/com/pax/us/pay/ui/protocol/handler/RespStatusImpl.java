package com.pax.us.pay.ui.protocol.handler;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Toast;

import com.pax.us.pay.ui.protocol.api.IRespStatus;

import java.lang.ref.WeakReference;

public class RespStatusImpl implements IRespStatus {

    private final WeakReference<Activity> activityWeakReference;

    public RespStatusImpl(Activity activity) {
        this.activityWeakReference = new WeakReference<>(activity);
    }

    @Override
    public void onAccepted() {
//        final Activity activity = activityWeakReference.get();
//        if (activity != null) {
//            activity.finish();
//        }
    }

    @Override
    public void onDeclined(final long code, @Nullable final String message) {
        final Activity activity = activityWeakReference.get();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String buff;
                    if (TextUtils.isEmpty(message))
                        buff = "Trans Failed! Error Code : " + code;
                    else
                        buff = message + "\n Error Code : " + code;
                    Toast.makeText(activity, buff, Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
