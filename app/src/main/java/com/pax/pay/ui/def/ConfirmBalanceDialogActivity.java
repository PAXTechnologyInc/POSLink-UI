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
import com.pax.pay.ui.def.eventbus.ConfirmDialogEndEvent;
import com.pax.pay.ui.def.eventbus.EventBusUtil;
import com.pax.us.pay.ui.core.helper.ConfirmBalanceHelper;
import com.pax.us.pay.ui.message.CurrencyConverter;
import com.paxus.view.BaseAppCompatActivity;
import com.paxus.view.dialog.CustomAlertDialog;
import com.paxus.view.dialog.DialogUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class ConfirmBalanceDialogActivity extends BaseAppCompatActivity implements ConfirmBalanceHelper.IConFirmBalanceListener {

    private ConfirmBalanceHelper helper = null;
    private boolean isPoint;
    private CustomAlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        EventBusUtil.doEvent(new ConfirmDialogEndEvent());
        EventBusUtil.register(this);
        helper = new ConfirmBalanceHelper(this, new FinishRespStatusImpl(this));
    }

    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message, String transMode) {
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
        EventBusUtil.unregister(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEndEvent(ConfirmDialogEndEvent event) {
        EventBusUtil.unregister(this);
        finish();
    }

    @Override
    public void onShowAmount(long amount) {

        String message;
        if (!isPoint)
            message = CurrencyConverter.convert(amount, "");
        else
            message = getResources().getString(R.string.point) + " " + String.valueOf(amount);
        showConfirmDialog(getResources().getString(R.string.confirm_balance), message, getResources().getString(R.string.dialog_ok));
    }

    @Override
    public void onShowCurrency(@Nullable String currency, boolean isPoint) {
        this.isPoint = isPoint;
        if (!isPoint) {
            CurrencyConverter.setDefCurrency(currency);
        }
    }

    protected void showConfirmDialog(String title, String contentMsg, String positiveMessage) {
        new Handler().post(() -> {
            if (dialog != null) {
                dialog.setContent(contentMsg);
                dialog.setConfirmText(positiveMessage);
            } else {
                dialog = new CustomAlertDialog(ConfirmBalanceDialogActivity.this, CustomAlertDialog.NORMAL_TYPE);
                dialog.setConfirmClickListener(alertDialog -> {
                    dialog.dismiss();
                    dialog = null;
                    sendNext(true);
                }).setKeycodeBackClickListener(alertDialog -> {
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
