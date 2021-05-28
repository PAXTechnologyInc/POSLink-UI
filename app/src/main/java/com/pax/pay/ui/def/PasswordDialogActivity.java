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
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.EditText;

import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.pay.ui.def.dialog.InputSecurityDialog;
import com.pax.pay.ui.def.eventbus.ActivityEndEvent;
import com.pax.pay.ui.def.eventbus.ConfirmDialogEndEvent;
import com.pax.pay.ui.def.eventbus.EventBusUtil;
import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.enumeration.AdminPasswordType;
import com.pax.us.pay.ui.core.helper.SecurityHelper;
import com.paxus.utils.StringUtils;
import com.paxus.view.dialog.DialogUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class PasswordDialogActivity extends AppCompatActivity implements SecurityHelper.ISecurityListener {

    SecurityHelper helper;
    private InputSecurityDialog inputPwdDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new View(this));
        //setContentView(R.layout.activity_setting);
        //Don't close the previous Activity, take it as background
        //EventBusUtil.doEvent(new ActivityEndEvent());
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.6f;
        getWindow().setAttributes(lp);
        EventBusUtil.register(this);

        String prompt = getString(R.string.enter_pwd);

        String merchantName = getIntent().getStringExtra(EntryExtraData.PARAM_MERCHANT_NAME);
        String passwordType = getIntent().getStringExtra(EntryExtraData.PARAM_ADMIN_PASSWORD_TYPE);
        if(AdminPasswordType.MANAGER.equals(passwordType)){
            prompt = getString(R.string.prompt_manager_pwd);
        }else if(AdminPasswordType.OPERATOR.equals(passwordType)){
            prompt = getString(R.string.prompt_operator_pwd);
            if (!StringUtils.isEmpty(merchantName)) {
                prompt =getString(R.string.prompt_merchant_pwd,merchantName);
            }
        }else {
            if (!StringUtils.isEmpty(merchantName)) {
                prompt =getString(R.string.prompt_merchant_pwd,merchantName);
            }
        }

        // don't use FinishRespStatusImpl, to avoid flash screen
        helper = new SecurityHelper(this, new RespStatusImpl(this));
        inputPwdDialog = new InputSecurityDialog(this, 16, prompt);
        inputPwdDialog.create();
        DialogUtils.showDialog(this, inputPwdDialog);
        inputPwdDialog.setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onStart() {
        helper.start(this, getIntent());
        super.onStart();
    }

    @Override
    protected void onStop() {
        helper.stop();
        if (inputPwdDialog != null) {
            inputPwdDialog.dismiss();
            inputPwdDialog = null;
        }
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        EditText editText = inputPwdDialog.getConvertView().findViewById(R.id.pwd_input_et);
        ViewTreeObserver observer = editText.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                editText.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int[] location = new int[2];
                //editText.getLocationInWindow(location);
                editText.getLocationOnScreen(location);
                int x = location[0];
                int y = location[1];
                int barHeight = 0;
                int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
                if (resourceId > 0) {
                    barHeight = getResources().getDimensionPixelSize(resourceId);
                }
                sendNext(x, y - barHeight, editText.getWidth(), editText.getHeight());
                editText.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            helper.sendAbort();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onShowAmount(long amount) {

    }

    @Override
    public void onShowCurrency(@Nullable String currency, boolean isPoint) {

    }

    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message, String transMode) {

    }

    //@Override
    public void sendNext(int x, int y, int width, int hight) {
        helper.setSecurityArea(x, y, width, hight);
    }

    @Override
    public void finish() {
        super.finish();
        //remove activity animation
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onDestroy() {
        if (inputPwdDialog != null) {
            inputPwdDialog.dismiss();
            inputPwdDialog = null;
        }
        EventBusUtil.unregister(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEndEvent(ActivityEndEvent event) {
        EventBusUtil.unregister(this);
        if (inputPwdDialog != null) {
            inputPwdDialog.dismiss();
            inputPwdDialog = null;
        }
        finish();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEndEvent(ConfirmDialogEndEvent event) {
        EventBusUtil.unregister(this);
        if (inputPwdDialog != null) {
            inputPwdDialog.dismiss();
            inputPwdDialog = null;
        }
        finish();
    }
}
