/*
 * ============================================================================
 * COPYRIGHT
 *              Pax CORPORATION PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with Pax Corporation and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *      Copyright (C) 2017 - ? Pax Corporation. All rights reserved.
 * Module Date: 2017-2-21
 * Module Author: Kim.L
 * Description:
 *
 * ============================================================================
 */
package com.pax.us.pay.ui.component.keyboard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.pax.us.pay.ui.component.R;

import java.lang.reflect.Method;
import java.util.List;


public class CustomKeyboardView extends KeyboardView {

    private final Paint paint = new Paint();
    private final Context mContext;
    private Drawable mKeyBgDrawable;
    private float weight = 1.0f;

    public CustomKeyboardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomKeyboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initResources();

        if (isInEditMode()) {
            return;
        }
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CustomKeyboardView);
        if (array.hasValue(R.styleable.CustomKeyboardView_width_weight)) {
            setWeight(array.getFloat(R.styleable.CustomKeyboardView_width_weight, 1.0f));
        }

        if (getWeight() > 1.0f) {
            setWeight(1.0f);
        }

        array.recycle();
    }

    private void initResources() {
        mKeyBgDrawable = ContextCompat.getDrawable(mContext, R.drawable.btn_bg_dark);
    }

    @SuppressLint({"DrawAllocation"})
    @Override
    public void onDraw(Canvas canvas) {
        List<Keyboard.Key> keys = getKeyboard().getKeys();
        for (Keyboard.Key key : keys) {
            canvas.save();

            int offsetY = 0;
            if (key.y == 0) {
                offsetY = 1;
            }
            int initDrawY = key.y + offsetY;
            Rect rect = new Rect(key.x, initDrawY, key.x + key.width, key.y + key.height);
            canvas.clipRect(rect);

            drawIcon(canvas, key, rect, initDrawY);
            drawText(canvas, key, initDrawY);
            canvas.restore();
        }
    }

    private void drawIcon(Canvas canvas, Keyboard.Key key, Rect rect, int initDrawY) {
        Drawable drawable = null;
        if (key.codes != null && key.codes.length != 0) {
            drawable = mKeyBgDrawable;
        }

        if (drawable != null && null == key.icon) {
            int[] state = key.getCurrentDrawableState();
            drawable.setState(state);
            drawable.setBounds(rect);
            drawable.draw(canvas);
        }

        if (key.icon != null) {
            Rect newRect;
            if ((key.width > key.height) && (!"A30".equals(Build.MODEL))) {
                newRect = new Rect(key.x + (int) (key.width - 0.8 * key.height) / 2, initDrawY + (int) (0.1 * key.height), key.x + (int) (key.width + 0.8 * key.height) / 2, (int) (key.y + 0.9 * key.height));
            } else {
                newRect = new Rect(key.x + (int) (0.1 * key.width), initDrawY + (int) (key.height - 0.8 * key.width) / 2, key.x + (int) (0.9 * key.width), key.y + (int) (key.height + 0.8 * key.width) / 2);
            }
            int[] state = key.getCurrentDrawableState();
            key.icon.setState(state);
            key.icon.setBounds(newRect);
            key.icon.draw(canvas);
        }
    }

    /**
     * 返回当前屏幕是否为竖屏。
     */
    public static boolean isScreenOrientationPortrait(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    private void drawText(Canvas canvas, Keyboard.Key key, int initDrawY) {
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
        if (!isScreenOrientationPortrait(mContext)) {
            paint.setTextSize(mContext.getResources().getDimension(R.dimen.font_size_key_large));
        } else {
            paint.setTextSize(mContext.getResources().getDimension(R.dimen.font_size_key));
        }

        paint.setColor(null == key.icon ? Color.BLACK : Color.WHITE);

        canvas.drawText(
                key.label != null ? key.label.toString() : "",
                key.x + (key.width / 2),
                initDrawY + (key.height + paint.getTextSize() - paint.descent()) / 2,
                paint
        );
    }

    @Override
    public boolean onTouchEvent(MotionEvent me) {
        //AET-245 246
        //just ignore all multi-touch
        return me.getPointerCount() > 1 || onModifiedTouchEvent(me, true);
    }

    private boolean onModifiedTouchEvent(MotionEvent me, boolean possiblePoly) {
        try {
            Method method = getClass().getSuperclass().getDeclaredMethod("onModifiedTouchEvent", me.getClass(), boolean.class);
            method.setAccessible(true);
            return (boolean) method.invoke(this, me, possiblePoly);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

}
