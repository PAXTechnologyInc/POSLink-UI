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
package com.pax.pay.ui.def.local;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

import com.pax.pay.ui.def.R;
import com.pax.us.pay.ui.component.constant.InternalDialogStatus;
import com.paxus.utils.StringUtils;
import com.paxus.view.dialog.CustomAlertDialog;
import com.paxus.view.dialog.DialogUtils;

import java.util.Timer;
import java.util.TimerTask;


public class LocalDialogActivity extends AppCompatActivity {

    private Runnable run;
    private Timer timer;
    private boolean resultSent = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        handleIntent(getIntent());
    }

    @Override
    protected void onResume() {
        super.onResume();
        resultSent = false;
    }

    private void handleIntent(Intent intent) {
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            finish();
            return;
        }
        Log.i("LocalDialogActivity", "action :" + action);

        switch (action) {
            case InternalDialogStatus.ACTION_PROMPT_DIALOG:
                Bundle bundle = intent.getExtras();
                String title = bundle.getString(InternalDialogStatus.PARAM_MESSAGE);
                String confirmBtnMsg = bundle.getString(InternalDialogStatus.PARAM_POSITIVE);
                String cancelBtnMsg = bundle.getString(InternalDialogStatus.PARAM_NEGATIVE);
                showMessage(this, title, cancelBtnMsg, confirmBtnMsg);
                break;
            case InternalDialogStatus.ACTION_PROMPT_SELECT_DIALOG:
                Bundle mBundle = intent.getExtras();
                String mTitle = mBundle.getString(InternalDialogStatus.PARAM_TITLE);
                String[] mList = mBundle.getStringArray(InternalDialogStatus.PARAM_ITEM_LIST);
                String timeoutStr = mBundle.getString(InternalDialogStatus.PARAM_TIMEOUT);
                long timeout = -1;
                if (!StringUtils.isEmpty(timeoutStr)) {
                    timeout = Long.parseLong(timeoutStr);
                }
                if (timeout != -1) {
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            Intent intent = new Intent();
                            intent.addCategory("com.pax.us.pay.category.INTERNAL");
                            intent.setPackage(getPackageName());
                            intent.setAction(InternalDialogStatus.ACTION_PROMPT_SELECT_DIALOG_RESPONSE);
                            intent.putExtra(InternalDialogStatus.PARAM_RESPONSE_CODE, InternalDialogStatus.RESPONSE_TIMEOUT);
                            sendBroadcast(intent);
                            finish();
                        }
                    }, timeout);
                }
                showMessage(this, mList, mTitle);
                break;
        }
    }

    private void showMessage(Context context, String content, String cancelText, String confirmText) {
        Handler handler = new Handler();
        run = new Runnable() {
            @Override
            public void run() {
                CustomAlertDialog dialog = new CustomAlertDialog(context, CustomAlertDialog.NORMAL_TYPE);
                dialog.setCancelClickListener(alertDialog -> {
                    alertDialog.dismiss();
                    setResult(RESULT_CANCELED);
                    finish();
                })
                        .setConfirmClickListener(alertDialog -> {
                            alertDialog.dismiss();
                            setResult(RESULT_OK);
                            finish();
                        })
                        .create();
                dialog.setContent(content);
                dialog.showCancelButton(true);
                dialog.setCancelText(cancelText);
                dialog.showConfirmButton(true);
                dialog.setConfirmText(confirmText);
                dialog.show();
            }
        };
        handler.post(run);
    }

    private void showMessage(Context context, String[] list, String title) {
        DialogUtils.showSelectDialog(LocalDialogActivity.this, title, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.addCategory("com.pax.us.pay.category.INTERNAL");
                intent.setPackage(getPackageName());
                intent.setAction(InternalDialogStatus.ACTION_PROMPT_SELECT_DIALOG_RESPONSE);
                intent.putExtra(InternalDialogStatus.PARAM_ITEM_SELECTED, which);
                sendBroadcast(intent);
                finish();
            }
                }, true,
                new DialogInterface.OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface dialog) {
                        Intent intent = new Intent();
                        intent.addCategory("com.pax.us.pay.category.INTERNAL");
                        intent.setPackage(getPackageName());
                        intent.setAction(InternalDialogStatus.ACTION_PROMPT_SELECT_DIALOG_RESPONSE);
                        intent.putExtra(InternalDialogStatus.PARAM_RESPONSE_CODE, InternalDialogStatus.RESPONSE_ABORTED);
                        sendBroadcast(intent);
                        finish();
                    }
                }, true,
                list, -1);
    }

    @Override
    protected void onStop() {
        if (!resultSent && InternalDialogStatus.ACTION_PROMPT_SELECT_DIALOG.equals(getIntent().getAction())) {
            Intent intent = new Intent();
            intent.addCategory("com.pax.us.pay.category.INTERNAL");
            intent.setPackage(getPackageName());
            intent.setAction(InternalDialogStatus.ACTION_PROMPT_SELECT_DIALOG_RESPONSE);
            intent.putExtra(InternalDialogStatus.PARAM_RESPONSE_CODE, InternalDialogStatus.RESPONSE_ABORTED);
            sendBroadcast(intent);
            finish();
        }
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void finish() {
        super.finish();
        //remove activity animation
        overridePendingTransition(0, 0);
        resultSent = true;
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

}
