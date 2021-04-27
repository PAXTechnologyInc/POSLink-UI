package com.pax.pay.ui.def.base;

import android.app.Activity;
import android.os.Build;
import android.util.Log;

import com.paxus.utils.log.Logger;

import java.util.Stack;

/**
 * Created by ZZ on 8/20/2020
 */
public class ActivityStack {
    private static final String TAG = "ActivityStack";
    private static ActivityStack instance;
    private final Stack<Activity> activities;

    private ActivityStack() {
        activities = new Stack<>();
    }

    public static ActivityStack getInstance() {
        if (instance == null)
            instance = new ActivityStack();

        return instance;
    }

    public boolean contains(Activity activity) {
        return activities.contains(activity);
    }

    public void pop() {
        try {
            Activity activity = activities.lastElement();
            if (activity != null) {
                removeAndFinish(activity);
            }
        } catch (Exception e) {
            Log.w(TAG, e);
        }
    }

    public boolean isExist(Activity orgActivity) {
        return activities.contains(orgActivity);
    }

    /**
     * pop the stack in sequence
     */
    public void popTo(Activity activity) {
        if (activity != null) {
            while (true) {
                Activity lastCurrent = top();
                if (activity == top()) {
                    return;
                }
                removeAndFinish(lastCurrent);
            }
        }
    }

    public Activity top() {
        try {
            return activities.lastElement();
        } catch (Exception e) {
            Logger.e(TAG, e);
        }
        return null;
    }

    public void push(Activity activity) {
        activities.add(activity);
    }

    /**
     * check the activity exist in currently stack.
     */
    public boolean find(Class<?> cls) {
        for (Activity activity : activities) {
            if (activity.getClass().equals(cls)) {
                return true;
            }
        }
        return false;
    }


    /**
     * To finish the all activities.
     */
    public void popAll() {
        if (activities == null) {
            return;
        }
        while (!activities.isEmpty()) {
            Activity activity = bottom();
            if (activity == null) {
                continue;
            }
            removeAndFinish(activity);
        }
    }

    public Activity bottom() {
        try {
            return activities.firstElement();
        } catch (Exception e) {
            Logger.e(TAG, e);
        }
        return null;
    }

    public void removeAndFinish(Activity activity) {
        Logger.d("removeAndFinish " + activity.getClass());
        activity.finish();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.releaseInstance();
        }
        activities.remove(activity);
    }
}
