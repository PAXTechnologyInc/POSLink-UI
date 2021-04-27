/*
 * ============================================================================
 * COPYRIGHT
 *              Pax CORPORATION PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with Pax Corporation and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *      Copyright (C) 2016 - ? Pax Corporation. All rights reserved.
 * Module Date: 2016-12-1
 * Module Author: Steven.W
 * Description:
 *
 * ============================================================================
 */
package com.paxus.view.dialog;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.paxus.view.R;


public class SuccessTickView extends View {
    private float mDensity = -1;
    private final float constRadius = dip2px(1.2f);
    private final float constRectWeight = dip2px(3);
    private final float constLeftRectW = dip2px(15);
    private final float constRightRectW = dip2px(25);
    private final float minLeftRectW = dip2px(3.3f);
    private final float maxRightRectW = constRightRectW + dip2px(6.7f);
    private Paint mPaint;
    private float mMaxLeftRectWidth;
    private float mLeftRectWidth;
    private float mRightRectWidth;
    private boolean mLeftRectGrowMode;

    public SuccessTickView(Context context) {
        super(context);
        init();
    }

    public SuccessTickView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(R.color.primary));
        mLeftRectWidth = constLeftRectW;
        mRightRectWidth = constRightRectW;
        mLeftRectGrowMode = false;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        int totalW = getWidth();
        int totalH = getHeight();
        // rotate canvas first
        canvas.rotate(45, totalW / 2, totalH / 2);

        totalW /= 1.2;
        totalH /= 1.4;
        mMaxLeftRectWidth = (totalW + constLeftRectW) / 2 + constRectWeight - 1;

        RectF leftRect = new RectF();
        if (mLeftRectGrowMode) {
            leftRect.left = 0;
            leftRect.right = leftRect.left + mLeftRectWidth;
            leftRect.top = (totalH + constRightRectW) / 2;
            leftRect.bottom = leftRect.top + constRectWeight;
        } else {
            leftRect.right = (totalW + constLeftRectW) / 2 + constRectWeight - 1;
            leftRect.left = leftRect.right - mLeftRectWidth;
            leftRect.top = (totalH + constRightRectW) / 2;
            leftRect.bottom = leftRect.top + constRectWeight;
        }

        canvas.drawRoundRect(leftRect, constRadius, constRadius, mPaint);

        RectF rightRect = new RectF();
        rightRect.bottom = (totalH + constRightRectW) / 2 + constRectWeight - 1;
        rightRect.left = (totalW + constLeftRectW) / 2;
        rightRect.right = rightRect.left + constRectWeight;
        rightRect.top = rightRect.bottom - mRightRectWidth;
        canvas.drawRoundRect(rightRect, constRadius, constRadius, mPaint);
    }

    public float dip2px(float dpValue) {
        if (Float.compare(mDensity, -1) == 0) {
            mDensity = getResources().getDisplayMetrics().density;
        }
        return dpValue * mDensity + 0.5f;
    }

    public void startTickAnim() {
        // hide tick
        mLeftRectWidth = 0;
        mRightRectWidth = 0;
        invalidate();
        Animation tickAnim = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                super.applyTransformation(interpolatedTime, t);
                if (0.54 < interpolatedTime && 0.7 >= interpolatedTime) { // grow left and right rect to right
                    mLeftRectGrowMode = true;
                    mLeftRectWidth = mMaxLeftRectWidth * ((interpolatedTime - 0.54f) / 0.16f);
                    if (0.65 < interpolatedTime) {
                        mRightRectWidth = maxRightRectW * ((interpolatedTime - 0.65f) / 0.19f);
                    }
                    invalidate();
                } else if (0.7 < interpolatedTime && 0.84 >= interpolatedTime) { // shorten left rect from right, still
                    // grow right rect
                    mLeftRectGrowMode = false;
                    mLeftRectWidth = mMaxLeftRectWidth * (1 - ((interpolatedTime - 0.7f) / 0.14f));
                    mLeftRectWidth = mLeftRectWidth < minLeftRectW ? minLeftRectW : mLeftRectWidth;
                    mRightRectWidth = maxRightRectW * ((interpolatedTime - 0.65f) / 0.19f);
                    invalidate();
                } else if (0.84 < interpolatedTime && 1 >= interpolatedTime) { // restore left rect width, shorten right
                    // rect to const
                    mLeftRectGrowMode = false;
                    mLeftRectWidth = minLeftRectW + (constLeftRectW - minLeftRectW)
                            * ((interpolatedTime - 0.84f) / 0.16f);
                    mRightRectWidth = constRightRectW + (maxRightRectW - constRightRectW)
                            * (1 - ((interpolatedTime - 0.84f) / 0.16f));
                    invalidate();
                }
            }
        };
        tickAnim.setDuration(750);
        tickAnim.setStartOffset(100);
        startAnimation(tickAnim);
    }
}