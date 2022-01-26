/*
 * ============================================================================
 * = COPYRIGHT
 *          PAX Computer Technology(Shenzhen) CO., LTD PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or nondisclosure
 *   agreement with PAX Computer Technology(Shenzhen) CO., LTD and may not be copied or
 *   disclosed except in accordance with the terms in that agreement.
 *     Copyright (C) 2018-? PAX Computer Technology(Shenzhen) CO., LTD All rights reserved.

 * Module Date: 2019-3-7
 * Module Auth: Justin.Z
 * Description:

 * Revision History:
 * Date                   Author                       Action
 * 2019-3-7               Justin.Z                      Create
 * ============================================================================
 */
package com.pax.pay.ui.def;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;

import android.text.TextUtils;

import com.pax.pay.ui.def.base.FinishRespStatusImpl;
import com.pax.us.pay.ui.core.helper.ConfirmMessageHelper;
import com.paxus.view.BaseAppCompatActivity;
import com.paxus.view.dialog.CustomAlertDialog;
import com.paxus.view.dialog.DialogUtils;


public class ConfirmContinueDialogActivity extends BaseAppCompatActivity implements ConfirmMessageHelper.IConfirmMessageListener {

    private ConfirmMessageHelper helper = null;
    private String title;
    private String contentMsg;
    private CustomAlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        contentMsg = getResources().getString(R.string.confirm_eula_continue);
        helper = new ConfirmMessageHelper(this, new FinishRespStatusImpl(this));
    }

    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message, String transMode) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(() -> {
            String positiveMessage = getResources().getString(R.string.confirm_continue);
            showConfirmDialog(positiveMessage);
        }, 100);
    }


    @Override
    protected void onStart() {
        helper.start(this, getIntent());
        super.onStart();
    }

    protected void sendNext(boolean flag) {
        helper.sendNext(flag);
    }

    protected void sendAbort() {
        helper.sendAbort();
    }

    @Override
    protected void onStop() {
        helper.stop();
        super.onStop();
    }

    @Override
    public void finish() {
        super.finish();
        //remove activity animation
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onDestroy() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
        super.onDestroy();
    }

    protected void showConfirmDialog(String positiveMessage) {
        new Handler().post(() -> {
            if (dialog != null) {
                dialog.setContent(contentMsg);
                dialog.setConfirmText(positiveMessage);
            } else {
                dialog = new CustomAlertDialog(ConfirmContinueDialogActivity.this, CustomAlertDialog.ERROR_TYPE);
                dialog.setConfirmClickListener(alertDialog -> {
                    dialog.dismiss();
                    dialog = null;
                    sendNext(true);
                }).create();

                if (!TextUtils.isEmpty(title)) {
                    dialog.setTitle(title);
                }
                dialog.setContent(contentMsg);
                dialog.showConfirmButton(true);
                dialog.setConfirmText(positiveMessage);
                DialogUtils.showDialog(this, dialog);
            }
        });
    }

}
