/*
 * ============================================================================
 * COPYRIGHT
 *              Pax CORPORATION PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with Pax Corporation and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *      Copyright (C) 2017 - ? Pax Corporation. All rights reserved.
 * Module Date: 2017-5-19
 * Module Author: Kim.L
 * Description:
 *
 * ============================================================================
 */
package com.pax.pay.ui.def.utils;

import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.paxus.view.quickclick.QuickClickProtection;

public abstract class EditorActionListener implements TextView.OnEditorActionListener {

    private QuickClickProtection quickClickProtection = QuickClickProtection.getInstance();

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            if (quickClickProtection.isStarted()) {
                return true;
            }
            quickClickProtection.start();
            //ExecutorsManager.getInstance().submitUiThread(EditorActionListener.this::onKeyOk);
            return true;
        } else if (actionId == EditorInfo.IME_ACTION_NONE) {
            if (quickClickProtection.isStarted()) {
                return true;
            }
            quickClickProtection.start();
            //ExecutorsManager.getInstance().submitUiThread(EditorActionListener.this::onKeyCancel);
            return true;
        }
        return false;
    }

    protected abstract void onKeyOk();

    protected abstract void onKeyCancel();
}
