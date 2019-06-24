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
package com.pax.pay.ui.def_ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

import com.pax.pay.ui.def_ui.eventbus.EventBusConstant;
import com.pax.pay.ui.def_ui.eventbus.EventBusUtil;
import com.pax.pay.ui.def_ui.view.ProcessDialogListener;
import com.pax.pay.ui.def_ui.view.ProcessDialogListenerImpl;
import com.pax.us.pay.ui.core.constant.status.Batch;
import com.pax.us.pay.ui.core.constant.status.Information;
import com.pax.us.pay.ui.core.message.UIMessageManager;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class DialogActivity extends AppCompatActivity {
    private static ProcessDialogListener processDialogListener;

    private Handler handler;
    private Runnable run;
    private static ConditionVariable mCv;


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
        setContentView(R.layout.activity_custom);

        //StatusBarCompat.compat(this, R.color.colorPrimary);
        handleIntent(getIntent());
        EventBusUtil.register(this);

        UIMessageManager.getInstance().registerAction(this, new UIMessageManager.ActionFinish() {
            @Override
            public void onFinished() {
                hideDialog();
                finish();
            }
        });
        //mCv.open();
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onEventAsync(String result) {
        if (EventBusConstant.END_EVENT.equals(result)) {
            hideDialog();
            finish();
        } else {
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
        Log.i("BroadcastReceiver", "action :" + action);

        if (processDialogListener == null) {
            processDialogListener = new ProcessDialogListenerImpl(this);
        }
        //hideDialog();
        switch (action) {
            case Information.TRANS_ONLINE:
                showProcessDialog("Trans Online...");
                break;
            case Information.EMV_PROCESS_STARTED:
                showProcessDialog("EMV START...");
                break;
            case Information.TRANS_ONLINE_FINISHED:
            case Information.EMV_PROCESS_FINISHED:
            case Batch.BATCH_CLOSE_COMPLETED:
                hideDialog();
                finish();
                break;
            case Information.CARD_TO_REMOVE:
                showWarnDialog("Remove card!");
                break;
            case Information.SEE_PHONE:
                showWarnDialog("Please See Phone");
                break;
            case Information.CARD_REMOVED:
                hideDialog();
                finish();
                break;
            case Batch.BATCH_CLOSE_STARTED:
                showProcessDialog("Batch Close START...");
                break;
            case Batch.BATCH_SF_UPLOADING:
                showProcessDialog("Uploading Trans...");
                break;
//            case Batch.BATCH_UPDATE_MSG:
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
        //UIMessageCenter.getInstance(this).unregisterTransactionFinishListener();
        UIMessageManager.getInstance().unregisterAction(this);
        EventBusUtil.unregister(this);
        super.onDestroy();
    }
}
