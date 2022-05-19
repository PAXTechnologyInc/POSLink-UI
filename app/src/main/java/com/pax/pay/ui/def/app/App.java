package com.pax.pay.ui.def.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;


import com.pax.pay.ui.def.BuildConfig;
import com.pax.us.pay.ui.component.activity.ActivityLifeCheck;
import com.paxus.utils.log.LogHelper;
import com.paxus.utils.log.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * App
 * instance for init base components, like router.
 *
 * @author Kim.L
 */
public class App extends Application implements  Application.ActivityLifecycleCallbacks {
    private List<Activity> activityList;
    //private static App mApp;

    @Override
    public void onCreate() {
        super.onCreate();

        ActivityLifeCheck.setAppFrontGround(true);
        ActivityLifeCheck.setUsingDefUI(true);
        registerActivityLifecycleCallbacks(this);
    }

    private void initRouter() {
        // These two lines must be written before init, otherwise these configurations will be invalid in the init process
//        if (BuildConfig.DEBUG) {
//            // Print log
//            ARouter.openLog();
//            // Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)
//            ARouter.openDebug();
//        }
//        // As early as possible, it is recommended to initialize in the Application
//        ARouter.init(this);
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        initLog(base);

        // it must be invoked here, cuz content provider was created before application onCreate
        initRouter();
    }

    private void initLog(Context context) {
        //Fix BPOSANDJAX-35
        LogHelper.init("PoslinkUIDemo_log", context.getExternalFilesDir(null).getAbsolutePath(), new LogHelper.LoggerCallback() {
            @Override
            public void onAppCrashed() {
            }
        });
        String appName = context.getApplicationInfo().loadLabel(context.getPackageManager()).toString();
        Logger.e("init:" + appName + "; version name:" + BuildConfig.VERSION_NAME + "; version code:" + BuildConfig.VERSION_CODE);
    }


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if (activityList == null) {
            activityList = new ArrayList<>();
        }

        // forbid font scale according the system setting
        Resources resources = activity.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();

        android.content.res.Configuration configuration = resources.getConfiguration();
        float fontScale = 1.0f;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            float densityDpi =  (float) DisplayMetrics.DENSITY_DEVICE_STABLE/(float) DisplayMetrics.DENSITY_DEFAULT;
            if (resources != null && (configuration.fontScale != fontScale || metrics.density != densityDpi )) {
                configuration.fontScale = fontScale;
                configuration.densityDpi =  (int) (densityDpi * 160);
                if ("Aries8".equals(Build.MODEL)) {
                    configuration.screenWidthDp = 1280;
                }
            }
        }else{
            if (resources != null && configuration.fontScale != fontScale) {
                configuration.fontScale = fontScale;
            }
        }
        resources.updateConfiguration(configuration, metrics);


        activityList.add(activity);
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
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        if (activityList != null) {
            activityList.remove(activity);
        }
    }

}
