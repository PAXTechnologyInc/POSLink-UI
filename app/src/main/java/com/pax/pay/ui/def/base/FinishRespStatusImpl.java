package com.pax.pay.ui.def.base;

import android.app.Activity;
import android.util.Log;

import java.lang.ref.WeakReference;

public class FinishRespStatusImpl extends RespStatusImpl {

    private final WeakReference<Activity> activityWeakReference;

    public FinishRespStatusImpl(Activity activity) {
        super(activity);
        this.activityWeakReference = new WeakReference<>(activity);
    }

    @Override
    public void onAccepted() {
        //close activity which don't involved any transaction.
        Activity activity = activityWeakReference.get();
        if (activity != null) {
            Log.i("FinishRespStatusImpl", "finished activity: " + activity);
            activity.finish();
        }
    }
}
