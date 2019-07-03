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
package com.pax.pay.poslink.ui.demo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

import com.pax.pay.poslink.ui.demo.Dialog.MessageDialog;
import com.pax.pay.poslink.ui.demo.R;
import com.pax.pay.poslink.ui.demo.event.EndEvent;
import com.pax.pay.poslink.ui.demo.event.EventBusUtil;
import com.pax.us.pay.ui.constant.status.BatchStatus;
import com.pax.us.pay.ui.constant.status.CardStatus;
import com.pax.us.pay.ui.constant.status.InformationStatus;
import com.pax.us.pay.ui.constant.status.StatusData;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.WeakReference;


public class DialogActivity extends AppCompatActivity {

    private static ConditionVariable mCv;
    private Handler handler;
    private Runnable run;
    private static MessageDialog messageDialog;
    private static WeakReference<Context> currContext;

    public static void start(Context context, Intent intent) {
        //mCv = new ConditionVariable();
        currContext = new WeakReference<>(context);
        Intent starter = new Intent(context, DialogActivity.class);
        starter.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
        starter.setAction(intent.getAction());
        if (intent.getExtras() != null)
            starter.putExtras(intent.getExtras());
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
            case InformationStatus.TRANS_COMPLETED:
                Bundle bundle = intent.getExtras();
                int resultCode = bundle.getInt(StatusData.PARAM_CODE);
                String resultMessage = bundle.getString(StatusData.PARAM_MSG);
                String displayMessage;
                int timeOut;
                if (resultCode == 0) {
                    if ((resultMessage != null) && (!resultMessage.equals("")))
                        displayMessage = resultMessage;
                    else
                        displayMessage = "Transaction Successes!";
                    timeOut = 2;
                } else {
                    if ((resultMessage != null) && (!resultMessage.equals("")))
                        displayMessage = resultMessage + "\nError Code : " + String.valueOf(resultCode);
                    else
                        displayMessage = "Transaction Failed!";
                    timeOut = 5;
                }
                showMessage(displayMessage);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        hideDialog();
                        finish();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                LocalActivityManager.getInstance().finishAllActivity();
                                //moveTaskToBack(true);
                            }
                        }, 200);
                    }
                }, timeOut * 1000);

                break;
        }
    }

    private void showProcessDialog(String message) {
        showMessage(message);
    }

    private void showWarnDialog(String message) {
        showMessage("WARNING : " + message);
    }

    private void updateMessage(String message) {
        if (messageDialog != null)
            messageDialog.setContent(message);
        ;
    }

    private void hideDialog() {
        if (messageDialog != null) {
            messageDialog.dismiss();
            messageDialog = null;
        }
    }

    private void showMessage(String msg) {
        Handler handler = new Handler();
        run = new Runnable() {
            @Override
            public void run() {
                if (messageDialog != null) {
                    messageDialog.setContent(msg);
                } else {
                    messageDialog = new MessageDialog(DialogActivity.this);
                    messageDialog.setContent(msg);
                    messageDialog.show();
                }
            }
        };
        handler.post(run);
    }

    @Override
    protected void onDestroy() {
        EventBusUtil.unregister(this);
        super.onDestroy();
    }

}
