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
package com.pax.pay.poslink.ui.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

import com.pax.pay.poslink.ui.demo.event.EndEvent;
import com.pax.pay.poslink.ui.demo.event.EventBusUtil;
import com.pax.us.pay.ui.constant.status.BatchStatus;
import com.pax.us.pay.ui.constant.status.CardStatus;
import com.pax.us.pay.ui.constant.status.InformationStatus;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class DialogActivity extends AppCompatActivity {

    private static ConditionVariable mCv;
    private Handler handler;
    private Runnable run;

    public static void start(Context context, Intent intent) {
        //mCv = new ConditionVariable();
        Intent starter = new Intent(context, DialogActivity.class);
        starter.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
        starter.setAction(intent.getAction());
        starter.putExtra("value", intent.getStringExtra("value"));
        context.startActivity(starter);
        // mCv.block();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        handleIntent(getIntent());
        EventBusUtil.register(this);
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onEventAsync(EndEvent event) {
        if (event != null) {
            hideDialog();
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //mCv.open();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
        //mCv.open();
    }

    private void handleIntent(Intent intent) {
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            finish();
            return;
        }
        Log.i("StatusReceiver", "action :" + action);

        if (processDialogListener == null) {
            processDialogListener = new ProcessDialogListenerImpl(this);
        }
        //hideDialog();
        switch (action) {
            case InformationStatus.TRANS_ONLINE_STARTED:
                showProcessDialog("Trans Online...");
                break;
            case CardStatus.CARD_PROCESS_STARTED:
                showProcessDialog("CARD PROCESS START...");
                break;
            case InformationStatus.TRANS_ONLINE_FINISHED:
            case CardStatus.CARD_PROCESS_COMPLETED:
            case BatchStatus.BATCH_CLOSE_COMPLETED:
                hideDialog();
                finish();
                break;
            case InformationStatus.CARD_REMOVAL_REQUIRED:
                showWarnDialog("Remove card!");
                break;
            case InformationStatus.SEE_PHONE:
                showWarnDialog("Please See Phone");
                break;
            case InformationStatus.CARD_REMOVED:
                hideDialog();
                finish();
                break;
            case BatchStatus.BATCH_CLOSE_STARTED:
                showProcessDialog("BatchStatus Close START...");
                break;
            case BatchStatus.BATCH_SF_UPLOADING:
                showProcessDialog("Uploading Trans...");
                break;
//            case BatchStatus.BATCH_UPDATE_MSG:
//                String message = intent.getStringExtra("value");
//                updateMessage(message);
//                break;
        }
    }

    private void showProcessDialog(String message) {
        processDialogListener.onShowProgress(message);
    }

    private void showWarnDialog(String message) {
        processDialogListener.onShowWarn(message);
    }

    private void updateMessage(String message) {
        processDialogListener.onUpdateMessage(message);
    }

    private void hideDialog() {
        if (processDialogListener != null) {
            processDialogListener.onHideProgress();
            processDialogListener = null;
        }
    }

    @Override
    protected void onDestroy() {
        EventBusUtil.unregister(this);
        super.onDestroy();
    }
}
