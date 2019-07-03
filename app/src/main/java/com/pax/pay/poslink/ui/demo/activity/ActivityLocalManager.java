package com.pax.pay.poslink.ui.demo.activity;

import android.app.Activity;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.Stack;

public class ActivityLocalManager {
    private static Stack<WeakReference<Activity>> activityStack;
    private static ActivityLocalManager instance;

    private ActivityLocalManager() {
    }

    public static ActivityLocalManager getInstance() {
        if (instance == null) {
            instance = new ActivityLocalManager();
        }
        return instance;
    }

    /**
     * add Activity into stack
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<WeakReference<Activity>>();
        }
        WeakReference<Activity> activityWeakReference = new WeakReference<>(activity);
        activityStack.add(activityWeakReference);
    }

    public WeakReference<Activity> getCurrentActivity() {
        if (activityStack != null) {
            return activityStack.lastElement();
        }
        return null;
    }

    /**
     * close all Activity
     */
    public void finishAllActivity() {
        if (activityStack != null) {
            for (int i = 0, size = activityStack.size(); i < size; i++) {
                if (null != activityStack.get(i).get()) {
                    Log.i("AppManager", "finish Activity : " + activityStack.get(i).get().getLocalClassName());
                    activityStack.get(i).get().finish();
                }
            }
            activityStack.clear();
        }
    }
}

