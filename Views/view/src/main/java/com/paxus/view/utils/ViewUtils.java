package com.paxus.view.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.paxus.view.R;
import com.paxus.view.WatermarkDrawable;

public class ViewUtils {

    public static final int FAILED_DIALOG_SHOW_TIME = 5;

    private static final String WATERMARK_TAG = "watermark";

    private static boolean immersiveSticky = false;

    public static void addWaterMarkView(Activity activity, String content) {
        if (activity != null) {
            ViewGroup rootView = activity.findViewById(android.R.id.content);
            if (null == rootView.findViewWithTag(WATERMARK_TAG)) {
                View waterMarkView = LayoutInflater.from(activity).inflate(R.layout.layout_watermark, null);
                Drawable bitmapDrawable = ViewUtils.genWaterMark(activity, content);
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

    public static void addWaterMarkView(ViewGroup viewGroup, String content) {
        if (!TextUtils.isEmpty(content) && null == viewGroup.findViewWithTag(WATERMARK_TAG)) {
            View waterMarkView = new FrameLayout(viewGroup.getContext());
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
            waterMarkView.setLayoutParams(params);
            Drawable bitmapDrawable = genWaterMark(viewGroup.getContext(), content);
            waterMarkView.setBackground(bitmapDrawable);
            //avoid re-draw
            waterMarkView.setTag(WATERMARK_TAG);
            viewGroup.addView(waterMarkView);
        }
    }

    public static void removeWaterMarkView(ViewGroup viewGroup) {
        View watermark = viewGroup.findViewWithTag(WATERMARK_TAG);
        if (watermark != null) {
            viewGroup.removeView(watermark);
        }
    }

    public static Drawable genWaterMark(Context context, String content) {
        WatermarkDrawable drawable = new WatermarkDrawable(content);
        drawable.setTextSize(sp2px(context, 18));
        return drawable;
    }

    // support multiple lines
    // see BPOSANDJAX-171
    public static BitmapDrawable genWaterMarkOld(String content) {
        TextPaint paint = new TextPaint();
        paint.setColor(Color.GRAY);
        paint.setAlpha(75);
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSize(50);
        StaticLayout contentLayout = new StaticLayout(content, paint, 600,
                Layout.Alignment.ALIGN_CENTER, 1, 0, true);

        //new height, anticlockwise
        //[x0,y0] = [0,contentH], [x1, y1]=[contentW, contentH], [x2,y2]=[newW,newH]
        //y2 = (y1 - y0) * cos(a) + (x1 - x0) * sin(a) + y0
        float degrees = 30;
        int contentW = contentLayout.getWidth();
        int contentH = contentLayout.getHeight();

        int newH = (int) (contentW * Math.sin(Math.toRadians(degrees)) + contentH);

        Bitmap bitmap = Bitmap.createBitmap(contentW, newH, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.TRANSPARENT);

        canvas.save();
        canvas.translate(0, newH - contentH); // move to the start point to the old position
        canvas.rotate(-30); //anticlockwise rotation
        contentLayout.draw(canvas);
        canvas.restore();

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
        int dp = Math.round(px / displayMetrics.density); //dp = px / (dpi / 160). dpi = DisplayMetrics.density * 160
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

    public static synchronized boolean canNavigationBarImmersiveSticky() {
        return immersiveSticky;
    }

    public static synchronized void enableNavigationBarImmersiveSticky(boolean immersiveSticky) {
        ViewUtils.immersiveSticky = immersiveSticky;
    }

    public static void showNavigationBar(View decorView) {
        int uiFlags = View.SYSTEM_UI_FLAG_VISIBLE;
        decorView.setSystemUiVisibility(uiFlags);
    }

    public static void hideNavigationBar(View decorView) {
        int uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiFlags);
    }

    public static boolean canShowSoftInputOnFocus() {
        return !"A30".equals(Build.MODEL);
    }

    public static boolean isDebuggable(Context context) {
        return (0 != (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE));
    }
}
