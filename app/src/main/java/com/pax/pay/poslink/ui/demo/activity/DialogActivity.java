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
import com.pax.us.pay.ui.constant.status.Uncategory;

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
        //EventBusUtil.register(this);
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
            case CardStatus.CARD_PROCESS_ERROR:
                showWarnDialog("Card Error!");
                showTimeOut(2);
                break;
            case CardStatus.CARD_REMOVAL_REQUIRED:
                showWarnDialog("Please Remove card!");
                break;
            case CardStatus.CARD_QUICK_REMOVAL_REQUIRED:
                showWarnDialog("Please Remove card Quickly!");
                break;
            case CardStatus.CARD_INSERT_REQUIRED:
                showWarnDialog("Please Insert Card!");
                showTimeOut(2);
                break;
            case CardStatus.CARD_SWIPE_REQUIRED:
                showWarnDialog("Please Swipe Card!");
                showTimeOut(2);
                break;
            case CardStatus.CARD_TAP_REQUIRED:
                showWarnDialog("Please Tap Card!");
                showTimeOut(2);
                break;
            case CardStatus.SEE_PHONE:
                showWarnDialog("Please See Phone");
                showTimeOut(2);
                break;
            case Uncategory.ACTIVATE_STARTED:
                showProcessDialog("Trans Online...");
                break;
            case Uncategory.CAPK_UPDATE_STARTED:
                showProcessDialog("CAPK Update...");
                break;
            case Uncategory.PRINT_STARTED:
                showProcessDialog("Printing...");
                break;
            case BatchStatus.BATCH_CLOSE_STARTED:
                showProcessDialog("Batch Close START...");
                break;
            case BatchStatus.BATCH_SF_UPLOADING:
                showProcessDialog("Uploading Trans...");
                //To Do need display SF type, uploading count, total count
                break;
            case BatchStatus.BATCH_SF_STARTED:
                showProcessDialog("Upload Start");
                break;

            case InformationStatus.ERROR:
                long errCode = intent.getLongExtra(StatusData.PARAM_CODE, 0);
                String errMessage = intent.getStringExtra(StatusData.PARAM_MSG);
                StringBuffer dispMsg = new StringBuffer();
                if (TextUtils.isEmpty(errMessage))
                    dispMsg.append("Transaction Failed!\n Error Code:").append(errCode);
                else {
                    dispMsg.append(errMessage).append("\n Error Code:").append(errCode);
                }
                showMessage(dispMsg.toString());
                showTimeOut(5);
                break;
            case InformationStatus.TRANS_ONLINE_FINISHED:
            case CardStatus.CARD_PROCESS_COMPLETED:
            case CardStatus.CARD_REMOVED:
            case Uncategory.ACTIVATE_COMPLETED:
            case Uncategory.CAPK_UPDATE_COMPLETED:
            case Uncategory.PRINT_COMPLETED:
            case BatchStatus.BATCH_SF_COMPLETED:
            case BatchStatus.BATCH_CLOSE_COMPLETED:
                hideDialog();
                //finish();
                break;
            case InformationStatus.TRANS_COMPLETED:
                Bundle bundle = intent.getExtras();
                long resultCode = bundle.getLong(StatusData.PARAM_CODE, 0);
                String resultMessage = bundle.getString(StatusData.PARAM_MSG);
                String displayMessage;
                int timeOut;
                if (resultCode == 0) {
                    if (!TextUtils.isEmpty(resultMessage))
                        displayMessage = resultMessage;
                    else
                        displayMessage = "Transaction Successes!";
                    timeOut = 2;
                } else if (resultCode == -3) {
                    finish();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //ActivityLocalManager.getInstance().finishAllActivity();
                            moveTaskToBack(false);
                            //for Debug
                            Log.i("EventBus", "Abort Activity");
                            EventBusUtil.doEvent(new EndEvent());
                        }
                    }, 200);
                    break;
                } else {
                    if (!TextUtils.isEmpty(resultMessage))
                        displayMessage = resultMessage + "\nError Code : " + String.valueOf(resultCode);
                    else
                        displayMessage = "Transaction Failed!" + "\nError Code : " + String.valueOf(resultCode);
                    timeOut = 5;
                }
                showMessage(displayMessage);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        hideDialog();
                        //For Debug
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Log.i("EventBus", "Complete Activity");
                                EventBusUtil.doEvent(new EndEvent());
                            }
                        }, 200);
                        finish();
                    }
                }, timeOut * 1000);


                break;
        }
    }

    private void showTimeOut(int timeOut) {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                hideDialog();
            }
        }, timeOut * 1000);
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
        super.onDestroy();
    }

}
