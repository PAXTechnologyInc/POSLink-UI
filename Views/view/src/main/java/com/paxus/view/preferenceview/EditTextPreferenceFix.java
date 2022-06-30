/*
 * ============================================================================
 * COPYRIGHT
 *              Pax CORPORATION PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with Pax Corporation and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *      Copyright (C) 2017 - ? Pax Corporation. All rights reserved.
 * Module Date: 2017-3-21
 * Module Author: Kim.L
 * Description: workaround for EditTextPreference, cuz the keyboard cannot be hidden after dialog is gone.
 * AET-79
 *
 * ============================================================================
 */
package com.paxus.view.preferenceview;

import android.content.Context;

import androidx.preference.EditTextPreference;

import android.util.AttributeSet;

import com.paxus.view.R;


public abstract class EditTextPreferenceFix extends EditTextPreference {


    public EditTextPreferenceFix(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public EditTextPreferenceFix(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditTextPreferenceFix(Context context) {
        this(context, null);
    }

    @Override
    public int getDialogLayoutResource() {
        return R.layout.layout_pref_edittext_dialog;
    }

    @Override
    public void onAttached() {
        super.onAttached();
        setSummary(getText());
    }

    @Override
    public void setText(String text) {
        super.setText(text);
        setSummary(text);
    }
}
