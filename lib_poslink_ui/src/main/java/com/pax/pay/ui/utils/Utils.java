package com.pax.pay.ui.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Looper;

import com.pax.pay.ui.log.Logger;

import java.lang.reflect.Field;

public class Utils {

    /**
     * 得到设备屏幕的宽度
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 得到设备屏幕的高度
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 得到设备的密度
     */
    public static float getScreenDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static boolean checkMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    /**
     * 密度转换为像素值
     */
    public static int dp2px(Context context, int dp) {
        final float scale = getScreenDensity(context);
        return (int) (dp * scale + 0.5);
    }

    /**
     * 返回当前屏幕是否为竖屏。
     */
    public static boolean isScreenOrientationPortrait(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    /**
     * 获取本地软件版本号
     */
    public static int getLocalVersion(Context ctx) {
        int localVersion = 0;
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Logger.e(e.getMessage());
        }
        return localVersion;
    }

    /**
     * 获取本地软件版本号名称
     */
    public static String getLocalVersionName(Context ctx) {
        String localVersion = "";
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Logger.e(e.getMessage());
        }
        return localVersion;
    }

    /**
     * Get Application Name
     */
    public static String getAppName(Context ctx) {
        PackageManager packageManager = ctx.getPackageManager();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(ctx.getPackageName(), PackageManager.GET_META_DATA);
            return (String) packageManager.getApplicationLabel(applicationInfo);
        } catch (Exception e) {
            Logger.e(e.getMessage());
            return null;
        }
    }

    public static Object getValueFromObj(Object obj, String fieldName) {
        Class clz = obj.getClass();
        try {
            Field field = clz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            Logger.e(e.getMessage());
        }
        return null;
    }

}
