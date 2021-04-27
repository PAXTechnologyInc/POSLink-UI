package com.paxus.view.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paxus.view.R;

public class ViewUtils {

    public static final int FAILED_DIALOG_SHOW_TIME = 5;

    private static final String WATERMARK_TAG = "watermark";

    public static void addWaterMarkView(Activity activity, String content) {
        if (activity != null) {
            ViewGroup rootView = activity.findViewById(android.R.id.content);
            if (null == rootView.findViewWithTag(WATERMARK_TAG)) {
                View waterMarkView = LayoutInflater.from(activity).inflate(R.layout.layout_watermark, null);
                BitmapDrawable bitmapDrawable = ViewUtils.genWaterMark(content);
                waterMarkView.setBackground(bitmapDrawable);
                //avoid re-draw
                waterMarkView.setTag(WATERMARK_TAG);
                rootView.addView(waterMarkView);
            }
        }
    }

    public static void removeWaterMarkView(Activity activity) {
        if (activity != null) {
            ViewGroup rootView = activity.findViewById(android.R.id.content);
            View watermark = rootView.findViewWithTag(WATERMARK_TAG);
            if (watermark != null) {
                rootView.removeView(watermark);
            }
        }
    }

    public static BitmapDrawable genWaterMark(String content) {
        Bitmap bitmap = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.TRANSPARENT);
        Paint paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setAlpha(75);
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSize(50);
        Path path = new Path();
        path.moveTo(30, 270);
        path.lineTo(300, 0);
        canvas.drawTextOnPath(content, path, 0, 0, paint);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
        bitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        bitmapDrawable.setDither(true);
        return bitmapDrawable;
    }

    public static int dp2px(Context context, int dp) {
        final float scale = getScreenDensity(context);
        return (int) (dp * scale + 0.5);
    }

    public static int sp2px(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int px2Dp(Context context, int px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }

    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static float getScreenDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static boolean isScreenOrientationPortrait(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }
}
