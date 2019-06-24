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

package com.pax.pay.ui.def_ui.view;

import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;

import com.pax.pay.ui.def_ui.R;


public class ClssLightsView extends LinearLayout {

    private final ClssLight[] lights = new ClssLight[4];

    private AlphaAnimation blinking;

    public ClssLightsView(Context context) {
        this(context, null);
    }

    public ClssLightsView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClssLightsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater mInflater = LayoutInflater.from(context);
        View myView = mInflater.inflate(R.layout.layout_clss_light, null);
        LayoutParams parentParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        parentParams.setLayoutDirection(HORIZONTAL);
        addView(myView, parentParams);

        init();
    }

    private void init() {
        blinking = new AlphaAnimation(1, 0);
        blinking.setDuration(500);
        blinking.setRepeatCount(Animation.INFINITE);
        blinking.setRepeatMode(Animation.REVERSE);

        lights[0] = findViewById(R.id.light1);
        lights[1] = findViewById(R.id.light2);
        lights[2] = findViewById(R.id.light3);
        lights[3] = findViewById(R.id.light4);
    }

    public void setLights(final @IntRange(from = -1, to = 3) int index, @ClssLight.STATUS int status) {

        for (int i = 0; i < lights.length; ++i) {
            if (index == i) {
                lights[i].setStatus(status, blinking);
            } else {
                lights[i].setStatus(ClssLight.OFF, null);
            }
        }
    }

    public void setLight(final @IntRange(from = 0, to = 3) int index, @ClssLight.STATUS int status) {
        lights[index].setStatus(status, blinking);
    }
}
