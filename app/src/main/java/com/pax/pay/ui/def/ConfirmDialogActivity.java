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

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.pax.pay.ui.def.eventbus.ConfirmDialogEndEvent;
import com.pax.pay.ui.def.eventbus.EventBusUtil;
import com.paxus.view.dialog.CustomAlertDialog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public abstract class ConfirmDialogActivity extends AppCompatActivity {

    private String title;
    private String contentMsg;
    private CustomAlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        EventBusUtil.doEvent(new ConfirmDialogEndEvent());
        EventBusUtil.register(this);
        title = "";
        contentMsg = "";
        loadParam(getIntent());
        //receiver = new LocalBroadcastReceiver();
    }

    @Override
    protected void onDestroy() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
        EventBusUtil.unregister(this);
        super.onDestroy();
    }

    @Override
    public void finish() {
        super.finish();
        //remove activity animation
        overridePendingTransition(0, 0);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEndEvent(ConfirmDialogEndEvent event) {
        EventBusUtil.unregister(this);
        finish();
    }

    protected void showSelectDialog(String positiveMessage, String negativeMessage) {
        new Handler().post(() -> {
            if (dialog != null) {
                dialog.setContent(contentMsg);
                dialog.setCancelText(negativeMessage);
                dialog.setConfirmText(positiveMessage);
            } else {
                dialog = new CustomAlertDialog(ConfirmDialogActivity.this, CustomAlertDialog.NORMAL_TYPE);
                dialog.setCancelClickListener(alertDialog -> {
                    dialog.dismiss();
                    dialog = null;
                    sendNext(false);
                }).setConfirmClickListener(alertDialog -> {
                    dialog.dismiss();
                    dialog = null;
                    sendNext(true);
                }).setKeycodeBackClickListener(alertDialog -> {
                    dialog.dismiss();
                    dialog = null;
                    sendAbort();
                }).create();
                if (!TextUtils.isEmpty(title)) {
                    dialog.setTitle(title);
                }
                dialog.setContent(contentMsg);
                dialog.setCancelButtonColor(R.drawable.btn_bg_dark);
                dialog.showCancelButton(true);
                dialog.setCancelText(negativeMessage);
                dialog.setCancelButtonColor(R.drawable.btn_bg_dark);
                dialog.showConfirmButton(true);
                dialog.setConfirmText(positiveMessage);
                dialog.show();
            }
        });
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContentMsg(String contentMsg) {
        this.contentMsg = contentMsg;
    }

    protected abstract void loadParam(Intent intent);

    protected abstract void sendNext(boolean flag);

    protected abstract void sendAbort();

}
