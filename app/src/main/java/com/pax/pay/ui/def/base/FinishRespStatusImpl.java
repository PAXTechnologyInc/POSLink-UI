package com.pax.pay.ui.def.base;

import android.app.Activity;

import com.paxus.utils.log.Logger;

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
            Logger.d("finished activity: " + activity);
            activity.finish();
        }
    }
}
