/*
 * ============================================================================
 * COPYRIGHT
 *              Pax CORPORATION PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with Pax Corporation and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *      Copyright (C) 2017 - ? Pax Corporation. All rights reserved.
 * Module Date: 2017-2-9
 * Module Author: Kim.L
 * Description:
 *
 * ============================================================================
 */
package com.pax.pay.ui.utils;

import android.text.Editable;
import android.text.TextWatcher;

public class TextValueWatcher<T> implements TextWatcher {

    protected T maxValue;
    protected boolean mEditing;
    protected boolean mIsForward = true;
    protected String mPreStr;
    protected OnCompareListener<T> compareListener;
    protected OnTextChangedListener textChangedListener;
    protected OnErrorListener<T> errorListener;

    public TextValueWatcher() {
        mEditing = false;
    }

    public TextValueWatcher(T maxValue) {
        mEditing = false;
        this.maxValue = maxValue;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        if (!mEditing) {
            mIsForward = (after >= count);
            mPreStr = s.toString();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (!mEditing) {
            mEditing = true;
            String temp = s.toString();
            if (compareListener != null && compareListener.onCompare(temp, maxValue)) {
                if (textChangedListener != null)
                    textChangedListener.afterTextChanged(temp);
            } else {
                s.replace(0, s.length(), mPreStr, 0, mPreStr.length());
                if (errorListener != null)
                    errorListener.onError(temp, maxValue);
            }
            mEditing = false;
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //do nothing
    }

    public void setOnCompareListener(OnCompareListener<T> listener) {
        this.compareListener = listener;
    }

    public T getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(T value) {
        maxValue = value;
    }

    public interface OnCompareListener<T> {
        boolean onCompare(String value, T max);
    }

    public void setOnTextChangedListener(OnTextChangedListener listener) {
        this.textChangedListener = listener;
    }

    public interface OnTextChangedListener {
        void afterTextChanged(String value);
    }

    public void setOnErrorListener(OnErrorListener<T> listener) {
        this.errorListener = listener;
    }

    public interface OnErrorListener<T> {
        void onError(String value, T max);
    }
}
