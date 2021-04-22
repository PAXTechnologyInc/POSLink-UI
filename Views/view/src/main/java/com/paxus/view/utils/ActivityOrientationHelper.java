package com.paxus.view.utils;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.hardware.SensorManager;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.view.OrientationEventListener;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;

/**
 * Created by Kim.L 2020/06/29
 * Please add "orientation|screenSize" to android:configChanges for activities you need in manifest, it can avoid re-creating activity
 */
public class ActivityOrientationHelper {
    public static final int NORMAL = 0;
    public static final int FORCE_PORTRAIT = 1;
    public static final int FORCE_LANDSCAPE = 2;

    @IntDef(value = {NORMAL, FORCE_PORTRAIT, FORCE_LANDSCAPE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ForceType {
    }

    @NonNull
    private final WeakReference<Activity> activityWeakReference;
    @NonNull
    private final OrientationEventListener mOrientationListener;
    private int oldOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;
    private int forceType = NORMAL;

    private ActivityOrientationHelper(Activity activity) {
        activityWeakReference = new WeakReference<>(activity);
        mOrientationListener = new OrientationEventListener(activity, SensorManager.SENSOR_DELAY_NORMAL) {
            @Override
            public void onOrientationChanged(int orientation) {
                if (activityWeakReference.get() == null) {
                    //sync state : activity is destroyed, disable listener
                    disable();
                    return;
                }

                if (orientation == OrientationEventListener.ORIENTATION_UNKNOWN) {
                    //keep the current Activity orientation
                    return;
                }
                //hardcoded angle ranges of 4 directions are tested from PAX terminal.
                if (orientation > 350 || orientation < 10) {
                    if (canRequestPortrait()) {
                        requestOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    }
                } else if (orientation > 170 && orientation < 190) {
                    if (canRequestPortrait()) {
                        requestOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
                    }
                } else if (orientation > 80 && orientation < 100) {
                    if (canRequestLandscape()) {
                        requestOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
                    }
                } else if (orientation > 260 && orientation < 280) {
                    if (canRequestLandscape()) {
                        requestOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    }
                }
            }
        };
    }

    /**
     * Please invoke this from onResume
     *
     * @return
     */
    public boolean enable() {
        if (mOrientationListener.canDetectOrientation()) {
            mOrientationListener.enable();
            return true;
        }
        return false;
    }

    /**
     * Please invoke this from onPause
     */
    public void disable() {
        mOrientationListener.disable();
    }

    private boolean canRequestPortrait() {
        return forceType == NORMAL || forceType == FORCE_PORTRAIT;
    }

    private boolean canRequestLandscape() {
        return forceType == NORMAL || forceType == FORCE_LANDSCAPE;
    }

    private void requestOrientation(int orientation) {
        if (oldOrientation != orientation && activityWeakReference.get() != null) {
            activityWeakReference.get().setRequestedOrientation(orientation);
            oldOrientation = orientation;
        }
    }

    public static final class Builder {
        private final ActivityOrientationHelper helper;

        public Builder(Activity activity) {
            helper = new ActivityOrientationHelper(activity);
        }

        public Builder setForceType(@ForceType int forceType) {
            helper.forceType = forceType;
            return this;
        }

        public ActivityOrientationHelper build() {
            return helper;
        }
    }
}
