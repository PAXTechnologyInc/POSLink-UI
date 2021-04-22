/*
 * ============================================================================
 * COPYRIGHT
 *              Pax CORPORATION PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with Pax Corporation and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *      Copyright (C) 2017 - ? Pax Corporation. All rights reserved.
 * Module Date: 2017-11-23
 * Module Author: Kim.L
 * Description:
 *
 * ============================================================================
 */

package com.pax.pay.ui.def.view;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.animation.Animation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


public class ClssLight extends AppCompatImageView {

    public static final int OFF = 0;
    public static final int ON = 1;
    public static final int BLINK = 2;

    @IntDef({OFF, ON, BLINK})
    @Retention(RetentionPolicy.SOURCE)
    public @interface STATUS {
    }

    public ClssLight(Context context) {
        this(context, null);
    }

    public ClssLight(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClssLight(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setEnabled(false);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        clearAnimation();
    }

    public void setStatus(@STATUS int status, final Animation blinking) {
        if (status == BLINK) {
            setEnabled(true);
            startAnimation(blinking);
        } else {
            clearAnimation();
            setEnabled(status == ON);
        }
    }
}
