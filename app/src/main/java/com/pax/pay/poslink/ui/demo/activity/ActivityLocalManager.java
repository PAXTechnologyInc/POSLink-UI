package com.pax.pay.poslink.ui.demo.activity;

import android.app.Activity;

import java.lang.ref.WeakReference;

public class ActivityLocalManager {
    // private static Stack<WeakReference<Activity>> activityStack;
    private static WeakReference<Activity> currActivity;
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

//        if (currActivity != null) {
//            currActivity.get().finish();
//        }
        currActivity = new WeakReference<>(activity);
    }

    public WeakReference<Activity> getCurrentActivity() {

        if (currActivity != null)
            return currActivity;
        return null;
    }

    /**
     * close all Activity
     */
    public void finishAllActivity() {
//
        if (currActivity != null)
            currActivity.get().finish();
    }
}
