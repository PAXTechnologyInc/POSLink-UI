package com.pax.pay.poslink.ui.demo.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.pax.pay.poslink.ui.demo.event.EndAllEvent;
import com.pax.pay.poslink.ui.demo.event.EndEvent;
import com.pax.pay.poslink.ui.demo.event.EventBusUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.WeakReference;

public class UIActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

    // save current activity
    private WeakReference<Activity> mCurrActivity = null;
    // save last activity
    private WeakReference<Activity> mLastActivity = null;

    UIActivityLifecycleCallbacks() {

    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {

        if (activity != null) {
            mCurrActivity = new WeakReference(activity);
            EventBusUtil.doEvent(new EndEvent());
            if (!mCurrActivity.get().getLocalClassName().contains("DialogActivity")) {
                Log.i("EventBus", "register " + mCurrActivity.get().getLocalClassName());
                EventBusUtil.register(this);
                mLastActivity = new WeakReference(activity);
            }
        }

    }

    @Override
    public void onActivityStarted(Activity activity) {
    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEndEvent(EndEvent event) {
        if (mLastActivity.get() != null) {
            Log.i("EventBus", "end activity " + mLastActivity.get().getLocalClassName());
            if (!mCurrActivity.get().getLocalClassName().contains("DialogActivity")) {
                mLastActivity.get().finish();
                EventBusUtil.unregister(this);
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEndAllEvent(EndAllEvent event) {
        if (mLastActivity.get() != null) {
            Log.i("EventBus", "end activity " + mLastActivity.get().getLocalClassName());
            mLastActivity.get().finish();
            EventBusUtil.unregister(this);
        }
    }

}
