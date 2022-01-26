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

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;

import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
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
import com.paxus.utils.log.Logger;
import com.paxus.view.BaseAppCompatActivity;
import com.paxus.view.dialog.DialogUtils;
import com.paxus.view.utils.ViewUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class PasswordDialogActivity extends BaseAppCompatActivity implements SecurityHelper.ISecurityListener {

    SecurityHelper helper;
    private InputSecurityDialog inputPwdDialog;
    private RespStatusImpl respStatusImpl;
    private boolean first, showFlag;
    private String prompt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new View(this));
        //setContentView(R.layout.activity_setting);
        //Don't close the previous Activity, take it as background
        //EventBusUtil.doEvent(new ActivityEndEvent());
       // WindowManager.LayoutParams lp = getWindow().getAttributes();
       // lp.alpha = 0.6f;
       // getWindow().setAttributes(lp);
        EventBusUtil.register(this);

        prompt = getString(R.string.enter_pwd);

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
        respStatusImpl = new RespStatusImpl(this);
        helper = new SecurityHelper(this, respStatusImpl);
        first = true;
        showFlag = true;
        inputPwdDialog = new InputSecurityDialog(this, 16, prompt);
        inputPwdDialog.create();
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
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (first & hasFocus){
            first = false;

            DialogUtils.showDialog(this, inputPwdDialog);
            inputPwdDialog.setCanceledOnTouchOutside(false);

            EditText editText = inputPwdDialog.findViewById(R.id.pwd_input_et);
            ViewTreeObserver observer = editText.getViewTreeObserver();
            observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    int offset = 0;
                    DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
                    if ("Aries6".equals(Build.MODEL) && metrics.density > 1.6f ) {
                        DisplayMetrics screen = new DisplayMetrics();
                        Window window = getWindow();
                        window.getWindowManager().getDefaultDisplay().getRealMetrics(screen);
                        int navigateBarHeight = screen.heightPixels - metrics.heightPixels;
                        int origNavBardHeight = getNavigationBarHeight(PasswordDialogActivity.this);
                        offset = navigateBarHeight - origNavBardHeight + 6;
                    }
                    editText.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    int[] location = new int[2];
                    //editText.getLocationInWindow(location);
                    editText.getLocationOnScreen(location);
                    int x = location[0];
                    int y = location[1]-offset;
                    int barHeight = 0;
                    boolean immersiveSticky = (getWindow().getDecorView().getSystemUiVisibility() &
                            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY) > 0;
                    Rect visibleFrame = new Rect();
                    getWindow().getDecorView().getWindowVisibleDisplayFrame(visibleFrame);
                    if(!immersiveSticky){
                        barHeight = visibleFrame.top;  //statusBar's height
                    }else {
                        Point point = new Point();
                        getWindowManager().getDefaultDisplay().getRealSize(point);
                        if (point.y != visibleFrame.bottom) {//A920
                            barHeight = getNavigationBarHeight(PasswordDialogActivity.this);
                        }
                    }

                    Logger.d("sendSecurity x=" + x + " y=" + y + " barHeight=" + barHeight);

                    sendNext(x, y - barHeight, editText.getWidth(), editText.getHeight());
                    editText.setVisibility(View.VISIBLE);

                    int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
                    respStatusImpl.setToastYOffset(y - (screenHeight - barHeight) / 2); //APMN-221
                }
            });

        }
        if (hasFocus && ViewUtils.canNavigationBarImmersiveSticky()) {
            ViewUtils.hideNavigationBar(getWindow().getDecorView());
        }
    }

    public static int getNavigationBarHeight(Context context) {
        int navigationBarHeight = -1;
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height","dimen", "android");
        if (resourceId > 0) {
            navigationBarHeight = resources.getDimensionPixelSize(resourceId);
        }
        return navigationBarHeight;
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
